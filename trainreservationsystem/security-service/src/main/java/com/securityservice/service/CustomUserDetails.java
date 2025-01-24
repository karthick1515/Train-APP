package com.securityservice.service;

import com.securityservice.model.AdminDetails;


import com.securityservice.model.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static CustomUserDetails getUser(UserInfo userInfo) {
        List<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userInfo.getRole()));
        System.out.println(authorities);
        return new CustomUserDetails(userInfo.getUsername(), userInfo.getEmail(), userInfo.getPassword(), authorities);
    }

    public static CustomUserDetails getAdmin(AdminDetails adminDetails) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(adminDetails.getRole()));
        System.out.println(authorities);
        return new CustomUserDetails(adminDetails.getUsername(), adminDetails.getEmail(), adminDetails.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}