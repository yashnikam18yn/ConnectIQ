package com.in.One.Click_Contacts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.in.One.Click_Contacts.Services.Impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

//     UserDetails user1 = User
//     .withDefaultPasswordEncoder()
//     .username("admin123")  // Change to .username instead of .withUsername
//     .password("admin123")
//     .roles("USER")  // You need to assign at least one role
//     .build();

//     @Bean
//     public UserDetailsService userDetailsService(){
//     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
//    return inMemoryUserDetailsManager;
//     }


        @Autowired
        private SecurityCustomUserDetailService securityCustomUserDetailService;


        @Autowired
        private OauthSuccessHandler handler;

        // configuration of authentication provider for spring security..
        @Bean
        public DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

            //User Detail Object:
            daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);

            //User Password Object:
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

            return daoAuthenticationProvider;
        }



        @Bean   
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

            //---url configuration----
            httpSecurity.authorizeHttpRequests(authorize ->{
                //authorize.requestMatchers("/home","/register").permitAll();
                authorize.requestMatchers("/user/**").authenticated();
                authorize.anyRequest().permitAll();
            });


            // use 
            httpSecurity.formLogin(formLogin ->{
                formLogin.loginPage("/login");
                formLogin.loginProcessingUrl("/authenticate");
                formLogin.successForwardUrl("/user/profile");
                formLogin.failureForwardUrl("/login?error=true");

                formLogin.usernameParameter("email");
                formLogin.passwordParameter("password");
            });

            httpSecurity.csrf(AbstractHttpConfigurer::disable);
            httpSecurity.logout(logoutForm ->{
                logoutForm.logoutUrl("/logout");
                logoutForm.logoutSuccessUrl("/login?logout=true");
            });


            // oauth Configuration for login with google
            httpSecurity.oauth2Login(oauth ->{
                oauth.loginPage("/login");
                oauth.successHandler(handler);
            });

            return httpSecurity.build(); 
        }


        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

}
