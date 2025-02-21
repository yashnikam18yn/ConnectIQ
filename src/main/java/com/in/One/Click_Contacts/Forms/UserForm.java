package com.in.One.Click_Contacts.Forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import lombok.Builder;
import lombok.Data;

@Data
public class UserForm {

    @NotBlank(message = "User name is required.")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password field is required")
    @Size(min = 4,message = "Minimum 4 Character")
    private String password;

    @NotBlank(message = "Mobile number is required")
    @Size(min = 10, message = "Minimum 10 Character")
    private String mobileNumber;

    @NotBlank(message = "About field is required")
    private String about;
}
