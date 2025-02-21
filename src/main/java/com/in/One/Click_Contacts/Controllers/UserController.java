package com.in.One.Click_Contacts.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.One.Click_Contacts.Entities.User;
import com.in.One.Click_Contacts.Helpers.Helper;
import com.in.One.Click_Contacts.Services.UserService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


   

    //user dashboard
    @RequestMapping("/dashboard")
    public String dashboradpage(){
        return "User/dashboard";
    }


    //user profile page
    @RequestMapping("/profile")
    public String profilePage(Model model, Authentication authentication){
        
        return "User/profile";
    }

    //user add contact

    // user view contact

    //user delete contact

    //user edit contact

    //user search contact


}
