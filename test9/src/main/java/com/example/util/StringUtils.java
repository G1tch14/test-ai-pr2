package com.example.util;

/**
 * Utility class for string operations.
 */
public class StringUtils {
    
    /**
     * Checks if a string is empty or null.
     * 
     * @param str the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Capitalizes the first letter of a string.
     * 
     * @param str the string to capitalize
     * @return the capitalized string, or empty string if input is null/empty
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return "";
        }
        
        String trimmed = str.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1);
    }
    
    /**
     * Reverses a string.
     * 
     * @param str the string to reverse
     * @return the reversed string, or empty string if input is null/empty
     */
    public static String reverse(String str) {
        if (isEmpty(str)) {
            return "";
        }
        
        return new StringBuilder(str).reverse().toString();
    }
}