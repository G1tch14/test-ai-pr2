package com.example.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for StringUtils class.
 */
class StringUtilsTest {
    
    @Test
    void testIsEmpty() {
        // Test null
        assertTrue(StringUtils.isEmpty(null));
        
        // Test empty string
        assertTrue(StringUtils.isEmpty(""));
        
        // Test whitespace only
        assertTrue(StringUtils.isEmpty("   "));
        
        // Test non-empty string
        assertFalse(StringUtils.isEmpty("Hello"));
        assertFalse(StringUtils.isEmpty("  Hello  "));
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void testIsEmptyWithVariousInputs(String input) {
        assertTrue(StringUtils.isEmpty(input));
    }
    
    @Test
    void testCapitalize() {
        // Test basic capitalization
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("Hello", StringUtils.capitalize("Hello"));
        assertEquals("HELLO", StringUtils.capitalize("hELLO"));
        
        // Test with spaces
        assertEquals("Hello world", StringUtils.capitalize("hello world"));
        
        // Test with leading/trailing spaces
        assertEquals("Hello", StringUtils.capitalize("  hello  "));
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void testCapitalizeWithEmptyInput(String input) {
        assertEquals("", StringUtils.capitalize(input));
    }
    
    @Test
    void testReverse() {
        // Test basic reversal
        assertEquals("olleh", StringUtils.reverse("hello"));
        assertEquals("dlrow olleh", StringUtils.reverse("hello world"));
        
        // Test palindrome
        assertEquals("racecar", StringUtils.reverse("racecar"));
        
        // Test with spaces
        assertEquals("   olleh", StringUtils.reverse("hello   "));
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void testReverseWithEmptyInput(String input) {
        assertEquals("", StringUtils.reverse(input));
    }
}