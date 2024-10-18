package com.example.lobbying.userTests;

import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceUpdatePasswordTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    public UserServiceUpdatePasswordTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdatePassword() {
        User user = new User();
        user.setPassword("oldPassword");

        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updatePassword(user, "newPassword");

        assertEquals("encodedNewPassword", result.getPassword());
    }
}
