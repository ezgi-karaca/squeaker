package com.squeaker.squeaker.service;

import com.squeaker.squeaker.entity.User;
import com.squeaker.squeaker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(1L, "user1", "pass1", "user1@example.com");
        user2 = new User(2L, "user2", "pass2", "user2@example.com");
    }

    @Test
    void getAllUsers() {
        List<User> fakeUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(fakeUsers);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Long searchId = 1L;
        when(userRepository.findById(searchId)).thenReturn(Optional.of(user1));

        User result = userService.findById(searchId);

        assertNotNull(result);
        assertEquals(searchId, result.getId());
        assertEquals("user1", result.getUsername());

        verify(userRepository, times(1)).findById(searchId);
    }

    @Test
    void findById_UserNotFound() {

        Long searchId = 99L;
        when(userRepository.findById(searchId)).thenReturn(Optional.empty());

        User result = userService.findById(searchId);

        assertNull(result);
        verify(userRepository, times(1)).findById(searchId);
    }
}