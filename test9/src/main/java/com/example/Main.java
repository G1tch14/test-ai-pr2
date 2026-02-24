package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Spring Boot application.
 * This class serves as the entry point for the REST API.
 */
@SpringBootApplication
public class Main {
    
    /**
     * Main method - entry point of the Spring Boot application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Spring Boot Application Started!");
    }
    
    /**
     * Formats a message by adding exclamation marks.
     * This method is kept for backward compatibility.
     * 
     * @param message the message to format
     * @return formatted message with exclamation marks
     */
    public static String formatMessage(String message) {
        return message + "!!!";
    }
}