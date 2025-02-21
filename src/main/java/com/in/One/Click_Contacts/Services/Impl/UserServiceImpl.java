package com.in.One.Click_Contacts.Services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.in.One.Click_Contacts.Entities.User;
import com.in.One.Click_Contacts.Helpers.AppConstants;
import com.in.One.Click_Contacts.Helpers.Helper;
import com.in.One.Click_Contacts.Helpers.ResourceNotFoundException;
import com.in.One.Click_Contacts.Repositories.UserRepo;
import com.in.One.Click_Contacts.Services.EmailService;
import com.in.One.Click_Contacts.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        // Generate a random ID for the user
        String uId = UUID.randomUUID().toString();
        user.setUserId(uId);

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set the user's role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        user.setEnabled(true);



        User savedUser = userRepo.save(user);

        //String emailToken = UUID.randomUUID().toString();

        //String emailLink = Helper.getLinkForEmailVerification(emailToken);

        //emailService.sendEmail(user.getEmail(), "Verify Your Mail", emailLink);

        
        return savedUser;

    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        userRepo.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean isUserExist(String userId) {
        return userRepo.findById(userId).isPresent();
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        // Update the existing user's details
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // Re-encode password
        existingUser.setMobileNumber(user.getMobileNumber());
        existingUser.setAbout(user.getAbout());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setMobileNumberVerified(user.isMobileNumberVerified());
        existingUser.setProvider(user.getProvider());
        existingUser.setProviderUserId(user.getProviderUserId());

        // Save the updated user
        return Optional.ofNullable(userRepo.save(existingUser));
    }

    @Override
    public User getUserByEmail(String email) {
       return userRepo.findByEmail(email).orElse(null);
    }
}
