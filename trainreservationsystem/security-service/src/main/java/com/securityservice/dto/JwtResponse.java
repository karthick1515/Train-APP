package com.securityservice.dto;

import lombok.*;

import java.util.List;

import com.securityservice.model.UserInfo;

@Getter
@Setter
@ToString
public class JwtResponse {
	 
     private String token;
     private String email;
     private Long phoneNumber;
     private String username;
     private int age;
     private String gender;
     private String address;
     private String role;
    
    public JwtResponse() {
		super();
	}
    
    public JwtResponse(String token, String email, Long phoneNumber, String username, int age, String gender,
			String address, String role) {
		super();
		this.token = token;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.role = role;
	}
	

	public JwtResponse(String token, String username, String role) {
		super();
		this.token = token;
		this.username = username;
		this.role = role;
	}



	
	

	
    
    
       
}
