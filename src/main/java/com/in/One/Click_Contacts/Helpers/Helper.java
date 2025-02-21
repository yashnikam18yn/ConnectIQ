package com.in.One.Click_Contacts.Helpers;

//import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){


        //Principal principal = (Principal) authentication.getPrincipal();
        // Login with email and password
        if (authentication instanceof OAuth2AuthenticationToken) {
            
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";
            // Login with google
            if (clientId.equalsIgnoreCase("google")) {
             // sign with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();
            }

        return username;
        }else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }
    }


    public static String getLinkForEmailVerification(String emailToken){

        String link = "http://localhost:8080/auth/verify-email?token="+emailToken;

        return link;
    }
}
