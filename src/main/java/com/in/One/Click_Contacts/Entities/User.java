package com.in.One.Click_Contacts.Entities;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "user")
@Table(name = "users")
@Data
public class User implements UserDetails{

    @Id
    private String userId;

    @Column(name = "user_name", nullable=false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // Changing column types to TEXT (no need to specify length for TEXT)
    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(columnDefinition = "TEXT")
    private String profilePic;

    @Column(length = 10)
    private String mobileNumber;

    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean mobileNumberVerified = false;

    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;

    private String providerUserId; 

    private String emailToken;

    // Uncomment and adjust if using relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[USER,ADMIN]
        // Collection of SimpGrantedAuthority[roles{ADMIN,USER}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
       return this.email;
    }
    

    @Override
    public String getPassword() {
        return this.password;
    }
}
