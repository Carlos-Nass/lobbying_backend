package com.example.lobbying.userTests;

import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.user.UserRequestDTO;
import com.example.lobbying.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceCreateUserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    public UserServiceCreateUserTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("John", "Doe", "john.doe@example.com","password123", 0);
        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode(userRequestDTO.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(userRequestDTO);

        assertNotNull(createdUser);
        assertEquals("John", createdUser.getName());
        assertEquals("Doe", createdUser.getSurname());
        assertEquals("john.doe@example.com", createdUser.getEmail());
        assertEquals("encodedPassword", createdUser.getPassword());
    }
}

