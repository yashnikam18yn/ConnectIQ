package com.in.One.Click_Contacts.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.in.One.Click_Contacts.Entities.Contact;
import com.in.One.Click_Contacts.Entities.User;
import com.in.One.Click_Contacts.Forms.ContactForm;
import com.in.One.Click_Contacts.Helpers.Helper;
import com.in.One.Click_Contacts.Services.ContactService;
import com.in.One.Click_Contacts.Services.ImageService;
import com.in.One.Click_Contacts.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        // contactForm.setName("Yash Nikam");
        // contactForm.setFavorite(true);

        return "User/add_contact";
    }

    
    
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session){

        // get data and process it...
        //System.out.println(contactForm);

        var username = Helper.getEmailOfLoggedInUser(authentication);
        //convert from into contact object
        Contact contact = new Contact();


        // validate the form
        if(result.hasErrors()){
            return "User/add_contact";
        }

        System.out.printf("File Information:-",contactForm.getContactImage().getOriginalFilename());
        // ContactForm --> Contact
        
        User user=userService.getUserByEmail(username);


        //process the contact picture
        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(),filename);


        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setMobileNumber(contactForm.getMobileNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setWebSiteLink(contactForm.getWebSiteLink());
        contact.setFavorite(contactForm.isFavorite());
        contact.setCloudinaryImagePublicId(fileURL);
        //set contact picture url
        contact.setPicture(fileURL);

        //set the user
        contact.setUser(user);

        // save into database
        contactService.save(contact);


        // redirect to the page

        //session.setAttribute(name:"message",value:"Your Contact Added successfully" );

        return "redirect:/user/contacts/add";
    }

    // view contact page

    @RequestMapping
    public String viewContacts(
    @RequestParam(value="page", defaultValue = "0") int page,
    @RequestParam(value="size", defaultValue = "10") int size,
    Model model, Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> contacts=contactService.getByUser(user,page,size);

        model.addAttribute("contacts", contacts);

        return "User/contacts";
    }

    //delete contact 
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") String contactId){
        contactService.delete(contactId);
        return "redirect:/user/contacts";
    }

    //update contact page
    @RequestMapping("/update/{contactId}")
    public String updateContact(@PathVariable("contactId") String contactId, Model model){
        var contact = contactService.getById(contactId);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setMobileNumber(contact.getMobileNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebSiteLink(contact.getWebSiteLink());


        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId",contactId);

        return "User/update_contact";
    }
}
