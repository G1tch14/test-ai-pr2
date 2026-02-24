package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserRequest;
import com.example.demo.domain.dto.UserResponse;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest("testuser", "test@example.com", "Test User");
        user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .fullName("Test User")
                .build();
    }

    @Test
    void createUser_ShouldCreateUserSuccessfully() {
        // Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserResponse response = userService.createUser(userRequest);

        // Assert
        assertNotNull(response);
        assertEquals(user.getId(), response.id());
        assertEquals(user.getUsername(), response.username());
        assertEquals(user.getEmail(), response.email());
        assertEquals(user.getFullName(), response.fullName());

        verify(userRepository, times(1)).existsByUsername(userRequest.username());
        verify(userRepository, times(1)).existsByEmail(userRequest.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_ShouldThrowException_WhenUsernameExists() {
        // Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.createUser(userRequest));

        assertEquals("Username already exists: " + userRequest.username(), exception.getMessage());
        verify(userRepository, times(1)).existsByUsername(userRequest.username());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserResponse response = userService.getUserById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(user.getId(), response.id());
        assertEquals(user.getUsername(), response.username());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.getUserById(1L));

        assertEquals("User not found with ID: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        // Arrange
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserResponse> responses = userService.getAllUsers();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(user.getId(), responses.get(0).id());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void updateUser_ShouldUpdateUserSuccessfully() {
        // Arrange
        UserRequest updateRequest = new UserRequest("updateduser", "updated@example.com", "Updated User");
        User updatedUser = User.builder()
                .id(1L)
                .username("updateduser")
                .email("updated@example.com")
                .fullName("Updated User")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        UserResponse response = userService.updateUser(1L, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(updatedUser.getId(), response.id());
        assertEquals(updatedUser.getUsername(), response.username());
        assertEquals(updatedUser.getEmail(), response.email());
        assertEquals(updatedUser.getFullName(), response.fullName());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).existsByUsername(updateRequest.username());
        verify(userRepository, times(1)).existsByEmail(updateRequest.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser_ShouldDeleteUserSuccessfully() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(true);

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.deleteUser(1L));

        assertEquals("User not found with ID: 1", exception.getMessage());
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, never()).deleteById(any());
    }
}