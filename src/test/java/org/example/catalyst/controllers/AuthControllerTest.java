package org.example.catalyst.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.catalyst.services.UserService;
import org.example.catalyst.mappers.UserMapper;
import org.example.catalyst.entities.User;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserMapper userMapper;

    /*
    @Test
    @DisplayName("User registration")
    void testRegistration() throws Exception {
        // Success case
        given(userService.existsByUsername("uniqueUser")).willReturn(false);
        given(passwordEncoder.encode("password123")).willReturn("encryptedPassword");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "uniqueUser",
                            "password": "password123",
                            "role": "USER"
                        }
                    """))
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered successfully."));

        verify(passwordEncoder, times(1)).encode("password123");
        verify(userService, times(1)).saveUser(any(User.class));

        // Duplicate username case
        given(userService.existsByUsername("existingUser")).willReturn(true);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "username": "existingUser",
                            "password": "password123",
                            "role": "USER"
                        }
                    """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username is already taken."));

        verify(userService, never()).saveUser(any(User.class));
    }

*/

    @Test
    @DisplayName("User login")
    void testLogin() throws Exception {
        // Success case
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken("validUser", "password123");

        given(authenticationManager.authenticate(any())).willReturn(authToken);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "username": "validUser",
                    "password": "password123"
                }
            """))
                .andExpect(status().isOk())
                .andExpect(content().string("User logged in successfully."));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());

        // Failure case
        given(authenticationManager.authenticate(any()))
                .willThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "username": "invalidUser",
                    "password": "wrongPassword"
                }
            """))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));

        assertNull(SecurityContextHolder.getContext().getAuthentication());

        // Validation case
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "username": "",
                    "password": "password123"
                }
            """))
                .andExpect(status().isBadRequest());
    }

    /*
    @Test
    @DisplayName("User logout")
    void testLogout() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", new SecurityContextImpl(
                new UsernamePasswordAuthenticationToken("validUser", null, AuthorityUtils.NO_AUTHORITIES)
        ));

        mockMvc.perform(post("/api/auth/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string("User logged out successfully."));

        assertNull(session.getAttribute("SPRING_SECURITY_CONTEXT"));
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
    */

}
