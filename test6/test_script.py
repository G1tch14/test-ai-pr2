#!/usr/bin/env python3
"""
Test script for task TASK-456533AB
This script demonstrates basic functionality for the test task.
"""

def hello_world():
    """Return a simple greeting."""
    return "Hello, World! This is a test for task TASK-456533AB."

def add_numbers(a, b):
    """Add two numbers together."""
    return a + b

def run_tests():
    """Run simple tests to verify functionality."""
    print("Running tests for task TASK-456533AB...")
    
    # Test hello_world
    greeting = hello_world()
    print(f"Test 1 - Greeting: {greeting}")
    assert "Hello, World!" in greeting
    
    # Test add_numbers
    result = add_numbers(2, 3)
    print(f"Test 2 - Addition: 2 + 3 = {result}")
    assert result == 5
    
    result = add_numbers(-1, 1)
    print(f"Test 3 - Addition: -1 + 1 = {result}")
    assert result == 0
    
    print("All tests passed!")

if __name__ == "__main__":
    run_tests()