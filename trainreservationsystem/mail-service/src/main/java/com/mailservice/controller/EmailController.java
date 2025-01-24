package com.mailservice.controller;

import com.mailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendEmail(@RequestParam String toEmail){
        emailService.sendSimpleEmail(toEmail);
        return  new ResponseEntity<String>("Mail Send", HttpStatus.OK);
    }
}
