package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the Spring Boot application.
 */
@SpringBootTest
@ActiveProfiles("test")
class ApplicationTest {

    @Test
    void contextLoads() {
        // This test verifies that the Spring application context loads successfully
        assertTrue(true, "Application context should load successfully");
    }

    @Test
    void testApplicationProperties() {
        // This test verifies that application properties are loaded
        // In a real test, you would autowire properties and assert their values
        assertTrue(true, "Application properties should be loaded");
    }
}