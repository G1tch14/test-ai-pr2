package com.example.demo.controller;

import com.example.demo.domain.dto.UserRequest;
import com.example.demo.domain.dto.UserResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for user operations.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Create a new user.
     *
     * @param userRequest the user creation request
     * @return the created user response
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get user by ID.
     *
     * @param id the user ID
     * @return the user response
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all users.
     *
     * @return list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responses = userService.getAllUsers();
        return ResponseEntity.ok(responses);
    }

    /**
     * Update user by ID.
     *
     * @param id the user ID
     * @param userRequest the user update request
     * @return the updated user response
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete user by ID.
     *
     * @param id the user ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}