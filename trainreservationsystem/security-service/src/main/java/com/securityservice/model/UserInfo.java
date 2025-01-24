package com.securityservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userdetails")
public class UserInfo {
    @Id
    private String id;
    private String username;
    private String email;
    private Long phoneNumber;
    private int age;
    private String gender;
    private String address;
    private String password;
    private String role="ROLE_USER";
}

