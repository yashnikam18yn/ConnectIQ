package com.in.One.Click_Contacts.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.in.One.Click_Contacts.Entities.Contact;
import com.in.One.Click_Contacts.Entities.User;

public interface ContactService {

    // save the contact
    Contact save(Contact contact);

    // update contact
    Contact update(Contact contact);

    //get all contact
    List<Contact> getAll();

    // get contact by id
    Contact getById(String id);

    //delete contact
    void delete(String id);

    // get contacts by userId
    List<Contact> getByUserId(String userId);

    //search contact
    List<Contact> search(String name, String email, String mobileNumber);
    

    Page<Contact> getByUser(User user, int page, int size);

    

}
