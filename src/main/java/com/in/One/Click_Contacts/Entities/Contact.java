package com.in.One.Click_Contacts.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Contact {
    @Id
    private String id;

    @Column(name = "user_name", nullable=false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 10)
    private String mobileNumber;

    @Column(length = 1000)
    private String address;

    // Changing large VARCHAR columns to TEXT to avoid row size issues
    @Column(columnDefinition = "TEXT")
    private String picture;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "favorite")
    private boolean favorite = false;

    @Column(columnDefinition = "TEXT")
    private String webSiteLink;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Optional: Specify the foreign key column name
    private User user;

    private String cloudinaryImagePublicId;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();
}
