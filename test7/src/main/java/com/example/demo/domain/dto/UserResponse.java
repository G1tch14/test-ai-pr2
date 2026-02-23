package com.example.demo.domain.dto;

/**
 * DTO for user responses.
 */
public record UserResponse(
        Long id,
        String username,
        String email,
        String fullName
) {}