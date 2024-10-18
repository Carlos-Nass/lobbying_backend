package com.example.lobbying.userTests;

import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceDeleteUserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceDeleteUserTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }
}
