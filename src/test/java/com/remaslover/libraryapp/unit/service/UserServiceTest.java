package com.remaslover.libraryapp.unit.service;

import com.remaslover.libraryapp.entity.Book;
import com.remaslover.libraryapp.entity.User;
import com.remaslover.libraryapp.repository.UserRepository;
import com.remaslover.libraryapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private Book testBook;

    @BeforeEach
    public void setUp() {

        testBook = new Book(1L, "Oyasumi Punpun", "Inio Asano", 2007, testUser);
        testUser = new User(1L, "John Doe", "john@example.com", List.of(testBook));
    }

    @Test
    public void testGetUserById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User result = userService.getUserById(1L);

        assertNotNull(result, "User should not be null");
        assertEquals(testUser.getId(), result.getId(), "User ID should match");
        assertEquals(testUser.getFio(), result.getFio(), "User name should match");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User result = userService.getUserById(1L);

        assertNull(result, "User should be null when not found");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUsers() {
        List<User> users = new ArrayList<>();
        users.add(testUser);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getUsers();

        assertNotNull(result, "Users list should not be null");
        assertEquals(1, result.size(), "There should be exactly one user in the list");
        assertEquals(testUser.getId(), result.get(0).getId(), "User ID should match");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);

        User result = userService.save(testUser);

        assertNotNull(result, "Saved user should not be null");
        assertEquals(testUser.getId(), result.getId(), "User ID should match");
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);

        User result = userService.update(testUser);

        assertNotNull(result, "Updated user should not be null");
        assertEquals(testUser.getId(), result.getId(), "User ID should match");
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
