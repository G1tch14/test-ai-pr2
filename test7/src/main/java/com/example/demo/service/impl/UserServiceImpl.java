package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserRequest;
import com.example.demo.domain.dto.UserResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of UserService.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        log.debug("Creating user with username: {}", userRequest.username());
        
        validateUserRequest(userRequest);
        
        User user = User.builder()
                .username(userRequest.username())
                .email(userRequest.email())
                .fullName(userRequest.fullName())
                .build();
        
        User savedUser = userRepository.save(user);
        log.info("User created with ID: {}", savedUser.getId());
        
        return mapToResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        log.debug("Getting user by ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        
        return mapToResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        log.debug("Getting all users");
        
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        log.debug("Updating user with ID: {}", id);
        
        validateUserRequest(userRequest);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        
        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setFullName(userRequest.fullName());
        
        User updatedUser = userRepository.save(user);
        log.info("User updated with ID: {}", updatedUser.getId());
        
        return mapToResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.debug("Deleting user with ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        
        userRepository.deleteById(id);
        log.info("User deleted with ID: {}", id);
    }

    /**
     * Validate user request data.
     *
     * @param userRequest the user request to validate
     */
    private void validateUserRequest(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.username())) {
            throw new RuntimeException("Username already exists: " + userRequest.username());
        }
        
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new RuntimeException("Email already exists: " + userRequest.email());
        }
    }

    /**
     * Map User entity to UserResponse DTO.
     *
     * @param user the user entity
     * @return the user response DTO
     */
    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName()
        );
    }
}