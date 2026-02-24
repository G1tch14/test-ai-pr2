package com.example;

/**
 * Main application class.
 */
public class Main {
    
    /**
     * Main method - entry point of the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello, Java Project!");
        
        // Example of using a utility method
        String message = "Welcome to the Java Project";
        System.out.println(formatMessage(message));
    }
    
    /**
     * Formats a message by adding exclamation marks.
     * 
     * @param message the message to format
     * @return formatted message with exclamation marks
     */
    public static String formatMessage(String message) {
        return message + "!!!";
    }
}