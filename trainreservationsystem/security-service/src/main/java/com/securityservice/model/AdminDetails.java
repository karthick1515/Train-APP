package com.securityservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "admindetails")
public class AdminDetails {
    @Id
    private String id;
    private  String username;
    private String password;
    private  String email;
    private String role="ROLE_ADMIN";
}
