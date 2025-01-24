package com.securityservice.service;
import com.securityservice.client.MailClient;

import com.securityservice.dto.JwtResponse;
import com.securityservice.dto.LoginRequest;
import com.securityservice.exception.UserAlreadyRegisteredException;
import com.securityservice.model.AdminDetails;
import com.securityservice.model.UserInfo;
import com.securityservice.jwt.JwtHelper;
import com.securityservice.repository.AdminDetailsRepository;
import com.securityservice.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collection;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private AdminDetailsRepository adminDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserInfo saveUser(UserInfo details) throws UserAlreadyRegisteredException {
        List<UserInfo> allUsers = userDetailsRepository.findAll();
        for(UserInfo user:allUsers){
            if(user.getEmail()==details.getEmail()){
                throw new UserAlreadyRegisteredException("User with "+details.getEmail()+" is already registered!!");
            }
        }
        details.setPassword(passwordEncoder.encode(details.getPassword()));
        mailClient.sendEmail(details.getEmail());
        return userDetailsRepository.save(details);
        
        //return "User added to the system";
    }
    public String saveAdmin(AdminDetails details) throws UserAlreadyRegisteredException {
        List<AdminDetails> allAdmins = adminDetailsRepository.findAll();
        for(AdminDetails admin:allAdmins){
            if(admin.getEmail()==details.getEmail()){
                throw new UserAlreadyRegisteredException("Admin with "+details.getEmail()+" is already registered!!");
            }
        }

        details.setPassword(passwordEncoder.encode(details.getPassword()));
        adminDetailsRepository.save(details);
        return "User added to the system";
    }
    
    public UserInfo updateUserProfile(String email,UserInfo userInfo){
        UserInfo userInfo1 = userDetailsRepository.findByEmail(email).get();
        userInfo1.setAddress(userInfo.getAddress());
        userInfo1.setAge(userInfo.getAge());
        userInfo1.setUsername(userInfo.getUsername());
        userInfo1.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo1.setPhoneNumber(userInfo.getPhoneNumber());
        userInfo1.setGender(userInfo.getGender());
       return  userDetailsRepository.save(userInfo1);
    }

    public JwtResponse signinAndToken(LoginRequest loginRequest)  {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            CustomUserDetails customUserDetails=(CustomUserDetails)authenticate.getPrincipal();
            String username = customUserDetails.getUsername();
            String token= generateToken(loginRequest.getEmail());
                        

            Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();
            
            String role = authorities.stream().map(GrantedAuthority::getAuthority).findFirst().get();
            if(role.equals("ROLE_USER")) {
            	 UserInfo userInfo = userDetailsRepository.findByEmail(loginRequest.getEmail()).get();
            	 String email = userInfo.getEmail();
            	 Long phoneNumber = userInfo.getPhoneNumber();
            	 int age = userInfo.getAge();
            	 String gender = userInfo.getGender();
            	 String address= userInfo.getAddress();
            	 return new JwtResponse(token,email,phoneNumber,username,age,gender,address,role);
            }
            return new JwtResponse(token,username,role);
            

        } else {
            throw new RuntimeException("Invalid Username Or password");
        }
    }

    public String generateToken(String username) {

        return jwtHelper.generateToken(username);
    }

    public Boolean validateToken(String token) {
        return jwtHelper.validateToken(token);
    }

}
