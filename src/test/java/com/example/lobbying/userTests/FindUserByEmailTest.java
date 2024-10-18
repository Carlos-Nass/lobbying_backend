package com.example.lobbying.userTests;

import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceFindByEmailTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceFindByEmailTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setEmail("john.doe@example.com");
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("john.doe@example.com");

        assertTrue(result.isPresent());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }
}
