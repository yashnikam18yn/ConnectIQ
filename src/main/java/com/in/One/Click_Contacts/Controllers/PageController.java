package com.in.One.Click_Contacts.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.beans.BeanUtils;
import com.in.One.Click_Contacts.Entities.User;
import com.in.One.Click_Contacts.Forms.UserForm;
import com.in.One.Click_Contacts.Services.Impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//import ch.qos.logback.core.model.Model;


@Controller
public class PageController {


    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String home(){
        System.out.println("Home Page Handler...");
        return "home";
    }
    

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About Page Loading..");
        return "about";
    }

    @RequestMapping("/service")
    public String servicePage(){
        System.out.println("Service Page Loading..");
        return "services";
    }
    
    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("Contact Page Loading..");
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return new String("login");
    }

    @RequestMapping("/register")
    public String signupPage(Model model) {
        UserForm userform = new UserForm();
        
        model.addAttribute("userForm", userform);
        return "register";
    }
    


    // register User
    @PostMapping("/do-register")
    public String signUpUser(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
        System.out.println("----------------User SignUp success---------------");
        System.out.println("---Data---"+userForm);

        //fetch form data  (make userform class and recive the data in the form of class)



        //validate form data
        // if(bindingResult.hasErrors()){
        //     return "register";
        // }


        //save database
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setMobileNumber(userForm.getMobileNumber());
        user.setAbout(userForm.getAbout());

        User saveduser=userServiceImpl.saveUser(user);
        System.out.println("-----User Saved------"+saveduser);
      

        //message success
        session.setAttribute("message", "Sign-up Successfull....");
        //redirect to login page

        return "redirect:/register";
    }
    
}
