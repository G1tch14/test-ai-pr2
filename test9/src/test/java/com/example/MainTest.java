package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Main class.
 */
class MainTest {
    
    @Test
    void testFormatMessage() {
        // Test basic message formatting
        String result = Main.formatMessage("Hello");
        assertEquals("Hello!!!", result);
        
        // Test with empty string
        result = Main.formatMessage("");
        assertEquals("!!!", result);
        
        // Test with null (should handle gracefully)
        result = Main.formatMessage(null);
        assertEquals("null!!!", result);
    }
    
    @Test
    void testMainMethod() {
        // This test just ensures the main method doesn't throw exceptions
        assertDoesNotThrow(() -> Main.main(new String[]{}));
        assertDoesNotThrow(() -> Main.main(new String[]{"arg1", "arg2"}));
    }
}