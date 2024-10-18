package com.example.lobbying.userTests;

import com.example.lobbying.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceVerifyPasswordTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    public UserServiceVerifyPasswordTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerifyPassword() {
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        Boolean result = userService.verifyPassword("password123", "encodedPassword");

        assertTrue(result);
    }
}
