package com.securityservice.service;


import com.securityservice.model.AdminDetails;
import com.securityservice.model.UserInfo;
import com.securityservice.repository.AdminDetailsRepository;
import com.securityservice.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    AdminDetailsRepository adminDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Check if it's a regular user
        Optional<UserInfo> user = userDetailsRepository.findByEmail(email);

        if (user.isPresent()) {
            // Create and return user UserDetails
            UserInfo userInfo = user.get();
            System.out.println(userInfo);
            return CustomUserDetails.getUser(userInfo);
        }
        // Check if it's an admin
        Optional<AdminDetails> adminDetails = adminDetailsRepository.findByEmail(email);

        if (adminDetails.isPresent()) {
            // Create and return admin UserDetails
            AdminDetails admin = adminDetails.get();
            System.out.println(admin);
            return CustomUserDetails.getAdmin(admin);
        }

        // If neither an admin nor a user is found, throw an exception
        throw new UsernameNotFoundException("User not found with email : " + email);
    }
}