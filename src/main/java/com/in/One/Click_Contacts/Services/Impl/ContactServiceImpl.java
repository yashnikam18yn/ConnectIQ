package com.in.One.Click_Contacts.Services.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.in.One.Click_Contacts.Entities.Contact;
import com.in.One.Click_Contacts.Entities.User;
import com.in.One.Click_Contacts.Helpers.ResourceNotFoundException;
import com.in.One.Click_Contacts.Repositories.ContactRepo;
import com.in.One.Click_Contacts.Services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String uId = UUID.randomUUID().toString();
        contact.setId(uId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
       return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
       return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not found"));
    }

    @Override
    public void delete(String id) {
        var contact=contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not found"));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> getByUserId(String userId) {
     
        return contactRepo.findByUserId(userId);
    }

    @Override
    public List<Contact> search(String name, String email, String mobileNumber) {
     
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size) {
        var pageable=PageRequest.of(page, size);
        return contactRepo.findByUser(user,pageable);
    }

}
