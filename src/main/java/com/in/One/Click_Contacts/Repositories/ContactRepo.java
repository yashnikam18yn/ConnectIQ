package com.in.One.Click_Contacts.Repositories;

import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.in.One.Click_Contacts.Entities.Contact;
import com.in.One.Click_Contacts.Entities.User;

import java.util.List;



@Repository
public interface ContactRepo extends JpaRepository<Contact, String>{
    // find contact by user

    //custom method to find list of contact
    Page<Contact> findByUser(User user, Pageable pageable);


    //custom query to find list of contact
    @Query("select c from Contact c where c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);
}
