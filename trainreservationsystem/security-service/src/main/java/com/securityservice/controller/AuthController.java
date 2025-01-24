package com.securityservice.controller;

import com.securityservice.dto.JwtResponse;

import com.securityservice.dto.LoginRequest;
import com.securityservice.exception.UserAlreadyRegisteredException;
import com.securityservice.model.AdminDetails;
import com.securityservice.model.UserInfo;
import com.securityservice.service.AuthService;
import com.securityservice.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/train/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/adminregister")
    public String addNewUser(@RequestBody AdminDetails admin) throws UserAlreadyRegisteredException {
        return service.saveAdmin(admin);
    }

    @PostMapping("/userregister")
    public UserInfo addNewUser(@RequestBody UserInfo user) throws UserAlreadyRegisteredException {
        return service.saveUser(user);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signinAndToken(@RequestBody LoginRequest loginRequest) {
      return new ResponseEntity<>(service.signinAndToken(loginRequest),HttpStatus.OK);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        Boolean checkTokenValid = service.validateToken(token);
        if (checkTokenValid){
            return "Token is valid";
        }else {
            return "Token is not valid";
        }
    }
}
