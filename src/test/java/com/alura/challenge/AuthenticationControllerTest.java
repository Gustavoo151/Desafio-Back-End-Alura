package com.alura.challenge;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alura.challenge.controller.AuthenticationController;
import com.alura.challenge.model.AuthenticationRequest;
import com.alura.challenge.model.AuthenticationResponse;
import com.alura.challenge.security.JwtUtil;
import com.alura.challenge.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    private AuthenticationRequest authenticationRequest;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationRequest = new AuthenticationRequest("user", "password");
        userDetails = new User("user", "password", new ArrayList<>());
    }

    @Test
    public void testCreateAuthenticationToken() throws Exception {
        when(userDetailsService.loadUserByUsername("user")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-token");

        AuthenticationResponse response = authenticationController.createAuthenticationToken(authenticationRequest);

        assertNotNull(response);
        assertEquals("dummy-token", response.getJwt());
    }
}
