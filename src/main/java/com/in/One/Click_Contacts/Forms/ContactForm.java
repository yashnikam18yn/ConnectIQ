package com.in.One.Click_Contacts.Forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactForm {

    @NotBlank(message = "Name is requried")
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Contact Number is required")
    private String mobileNumber;

    @NotBlank(message = "Address Required")
    private String address;

    

    private String description;
    private boolean favorite;
    private String webSiteLink;


    // process profile image 
    //size validate
    //resolution validate
    private MultipartFile contactImage;



}
