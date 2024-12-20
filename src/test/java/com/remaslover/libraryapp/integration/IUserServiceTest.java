package com.remaslover.libraryapp.integration;


import com.remaslover.libraryapp.entity.User;
import com.remaslover.libraryapp.repository.UserRepository;
import com.remaslover.libraryapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
        // Создание пользователя для тестов
        testUser = new User("John Doe", "john@example.com");
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
        User newUser = new User("Jane Doe", "jane@example.com");
        User savedUser = userService.save(newUser);
        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals(newUser.getName(), savedUser.getName(), "Saved user's name should match");
    }

    @Test
    public void testDeleteUser() {
        Long userId = testUser.getId();
        userService.delete(userId);
        assertFalse(userRepository.existsById(userId), "User should be deleted");
    }

    // Пример теста с использованием MockMvc для HTTP запросов (если у вас есть контроллеры)
    @Test
    public void testGetUserById_Mvc() throws Exception {
        mockMvc.perform(get("/users/" + testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(testUser.getFio()));
    }
}
