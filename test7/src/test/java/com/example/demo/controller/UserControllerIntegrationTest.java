package com.example.demo.controller;

import com.example.demo.domain.dto.UserRequest;
import com.example.demo.domain.dto.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for UserController.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        // Arrange
        UserRequest userRequest = new UserRequest("integrationuser", "integration@example.com", "Integration Test User");

        // Act & Assert
        MvcResult result = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("integrationuser"))
                .andExpect(jsonPath("$.email").value("integration@example.com"))
                .andExpect(jsonPath("$.fullName").value("Integration Test User"))
                .andReturn();

        // Verify response can be deserialized
        String responseContent = result.getResponse().getContentAsString();
        UserResponse response = objectMapper.readValue(responseContent, UserResponse.class);
        
        assertNotNull(response);
        assertNotNull(response.id());
        assertEquals("integrationuser", response.username());
        assertEquals("integration@example.com", response.email());
        assertEquals("Integration Test User", response.fullName());
    }

    @Test
    void createUser_ShouldReturnBadRequest_WhenValidationFails() throws Exception {
        // Arrange - invalid email
        UserRequest invalidRequest = new UserRequest("", "invalid-email", "");

        // Act & Assert
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.username").exists())
                .andExpect(jsonPath("$.errors.email").exists())
                .andExpect(jsonPath("$.errors.fullName").exists());
    }

    @Test
    void createUser_ShouldReturnBadRequest_WhenUsernameAlreadyExists() throws Exception {
        // Arrange - create first user
        UserRequest firstUser = new UserRequest("duplicateuser", "first@example.com", "First User");
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstUser)))
                .andExpect(status().isCreated());

        // Act & Assert - try to create user with same username
        UserRequest duplicateUser = new UserRequest("duplicateuser", "second@example.com", "Second User");
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateUser)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Username already exists: duplicateuser"));
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() throws Exception {
        // Arrange - create a user first
        UserRequest userRequest = new UserRequest("getuser", "get@example.com", "Get Test User");
        MvcResult createResult = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        UserResponse createdUser = objectMapper.readValue(
                createResult.getResponse().getContentAsString(), UserResponse.class);

        // Act & Assert - get the created user
        mockMvc.perform(get("/api/users/{id}", createdUser.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUser.id()))
                .andExpect(jsonPath("$.username").value("getuser"))
                .andExpect(jsonPath("$.email").value("get@example.com"))
                .andExpect(jsonPath("$.fullName").value("Get Test User"));
    }

    @Test
    void getUserById_ShouldReturnBadRequest_WhenUserNotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/users/{id}", 999L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User not found with ID: 999"));
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() throws Exception {
        // Arrange - create multiple users
        UserRequest user1 = new UserRequest("user1", "user1@example.com", "User One");
        UserRequest user2 = new UserRequest("user2", "user2@example.com", "User Two");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isCreated());

        // Act & Assert
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void updateUser_ShouldUpdateUserSuccessfully() throws Exception {
        // Arrange - create a user first
        UserRequest createRequest = new UserRequest("updateuser", "update@example.com", "Update Test User");
        MvcResult createResult = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        UserResponse createdUser = objectMapper.readValue(
                createResult.getResponse().getContentAsString(), UserResponse.class);

        // Act & Assert - update the user
        UserRequest updateRequest = new UserRequest("updateduser", "updated@example.com", "Updated Test User");
        mockMvc.perform(put("/api/users/{id}", createdUser.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUser.id()))
                .andExpect(jsonPath("$.username").value("updateduser"))
                .andExpect(jsonPath("$.email").value("updated@example.com"))
                .andExpect(jsonPath("$.fullName").value("Updated Test User"));
    }

    @Test
    void deleteUser_ShouldDeleteUserSuccessfully() throws Exception {
        // Arrange - create a user first
        UserRequest userRequest = new UserRequest("deleteuser", "delete@example.com", "Delete Test User");
        MvcResult createResult = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        UserResponse createdUser = objectMapper.readValue(
                createResult.getResponse().getContentAsString(), UserResponse.class);

        // Act & Assert - delete the user
        mockMvc.perform(delete("/api/users/{id}", createdUser.id()))
                .andExpect(status().isNoContent());

        // Verify user is deleted
        mockMvc.perform(get("/api/users/{id}", createdUser.id()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User not found with ID: " + createdUser.id()));
    }
}