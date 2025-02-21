package com.in.One.Click_Contacts.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.in.One.Click_Contacts.Entities.User;
import com.in.One.Click_Contacts.Helpers.Helper;
import com.in.One.Click_Contacts.Services.UserService;

// it contain method which execute for each request...

@ControllerAdvice
public class RootController {


    @Autowired
    private UserService userService;

     @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){

        if(authentication == null){
            return;
        }
        String name = Helper.getEmailOfLoggedInUser(authentication);   
        System.out.println("Name:--"+name);
        User user=userService.getUserByEmail(name);
                
        System.out.println("Email"+ user.getEmail());
        System.out.println("Name"+user.getName());
        model.addAttribute("loggedUser",user);
        
    }

}
