package com.example.lobbying.userTests;

import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceGetUsersTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceGetUsersTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getUsers();

        assertEquals(2, result.size());
    }
}

