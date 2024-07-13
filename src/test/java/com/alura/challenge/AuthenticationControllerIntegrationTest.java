package com.alura.challenge;

import com.alura.challenge.model.User;
import com.alura.challenge.repository.UserRepository;
import com.alura.challenge.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
//        userRepository.save(user);
    }

    @Test
    public void testAuthenticate() throws Exception {
        String authRequestJson = "{\"username\": \"user\", \"password\": \"password\"}";

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authRequestJson))
                .andExpect(status().isOk());
    }
}
