package com.mailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements  EmailService{
        @Autowired
        private JavaMailSender mailSender;
        String subject="TrainBuddy Online Rail Ticket Resrervation User Registration Confirmation";
        String body="We thank you for your registration at TrainBuddy Online Rail Ticket Reservation website";


        @Override
        public void sendSimpleEmail(String toEmail) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("fromemail@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
       //     System.out.println("Mail Send...");


        }

}