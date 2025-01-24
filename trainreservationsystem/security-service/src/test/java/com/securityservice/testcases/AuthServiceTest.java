package com.securityservice.testcases;

import com.securityservice.model.UserInfo;
import com.securityservice.dto.LoginRequest;
import com.securityservice.dto.JwtResponse;
import com.securityservice.exception.UserAlreadyRegisteredException;
import com.securityservice.repository.UserDetailsRepository;
import com.securityservice.service.AuthService;
import com.securityservice.jwt.JwtHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        // Mock the Authentication object for successful authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Mock UserDetailsRepository methods
        when(userDetailsRepository.findAll()).thenReturn(new ArrayList<>());

        // Mock AuthenticationManager
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
    }

    @Test
    public void testSaveUser() throws UserAlreadyRegisteredException {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("test@example.com");
        userInfo.setPassword("password");

        when(userDetailsRepository.save(Mockito.any())).thenReturn(userInfo);

        UserInfo savedUserInfo = authService.saveUser(userInfo);
        assertEquals(userInfo.getEmail(), savedUserInfo.getEmail());
    }

    @Test
    public void testSaveAdmin() throws UserAlreadyRegisteredException {
        // Similar to testSaveUser, but for saving an admin
    }

    @Test
    public void testSigninAndToken() {
        // Mock JwtHelper to return a dummy token
        when(jwtHelper.generateToken(Mockito.anyString())).thenReturn("dummyToken");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        JwtResponse jwtResponse = authService.signinAndToken(loginRequest);
        assertEquals("dummyToken", jwtResponse.getToken());
    }
}

