package com.in.One.Click_Contacts.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.One.Click_Contacts.Entities.User;


// it have and all methods related to the Database
@Repository
public interface UserRepo extends JpaRepository<User, String>{

    
    Optional<User> findByEmail(String email);

}
