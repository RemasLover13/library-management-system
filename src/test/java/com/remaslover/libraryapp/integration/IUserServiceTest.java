package com.remaslover.libraryapp.integration;


import com.remaslover.libraryapp.entity.User;
import com.remaslover.libraryapp.repository.UserRepository;
import com.remaslover.libraryapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IUserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("Ivan Ivanov", "2000");
        userRepository.save(testUser);
    }

    @Test
    public void testGetUserById() {
        User foundUser = userService.getUserById(testUser.getId());
        assertNotNull(foundUser, "User should not be null");
        assertEquals(testUser.getId(), foundUser.getId(), "User ID should match");
        assertEquals(testUser.getFio(), foundUser.getFio(), "User fio should match");
    }

    @Test
    public void testGetUsers() {
        List<User> users = userService.getUsers();
        assertNotNull(users, "Users list should not be null");
        assertFalse(users.isEmpty(), "Users list should not be empty");
    }

    @Test
    public void testSaveUser() {
        User newUser = new User("Ivan Ivanov", "2000");
        User savedUser = userService.save(newUser);
        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals(newUser.getFio(), savedUser.getFio(), "Saved user's name should match");
    }

    @Test
    public void testDeleteUser() {
        Long userId = testUser.getId();
        userService.delete(userId);
        assertFalse(userRepository.existsById(userId), "User should be deleted");
    }

}
