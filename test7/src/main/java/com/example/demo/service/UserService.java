package com.example.demo.service;

import com.example.demo.domain.dto.UserRequest;
import com.example.demo.domain.dto.UserResponse;

import java.util.List;

/**
 * Service interface for user operations.
 */
public interface UserService {

    /**
     * Create a new user.
     *
     * @param userRequest the user creation request
     * @return the created user response
     */
    UserResponse createUser(UserRequest userRequest);

    /**
     * Get user by ID.
     *
     * @param id the user ID
     * @return the user response
     */
    UserResponse getUserById(Long id);

    /**
     * Get all users.
     *
     * @return list of all users
     */
    List<UserResponse> getAllUsers();

    /**
     * Update user by ID.
     *
     * @param id the user ID
     * @param userRequest the user update request
     * @return the updated user response
     */
    UserResponse updateUser(Long id, UserRequest userRequest);

    /**
     * Delete user by ID.
     *
     * @param id the user ID
     */
    void deleteUser(Long id);
}