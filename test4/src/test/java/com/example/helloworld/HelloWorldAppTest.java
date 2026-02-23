package com.example.helloworld;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HelloWorldApp.
 */
class HelloWorldAppTest {

    @Test
    void testMainPrintsHelloWorld() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // Call main method
            HelloWorldApp.main(new String[]{});
            
            // Verify output
            String output = outputStream.toString().trim();
            assertEquals("hello world", output, 
                "Application should print 'hello world' to console");
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }
    
    @Test
    void testMainWithArguments() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // Call main method with arguments (should be ignored)
            HelloWorldApp.main(new String[]{"arg1", "arg2"});
            
            // Verify output
            String output = outputStream.toString().trim();
            assertEquals("hello world", output, 
                "Application should print 'hello world' regardless of arguments");
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }
}