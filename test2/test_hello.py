#!/usr/bin/env python3
"""
Test for the hello world application.
"""

import subprocess
import sys

def test_hello_world_output():
    """Test that the hello world application prints the correct output."""
    result = subprocess.run([sys.executable, 'hello.py'], 
                          capture_output=True, text=True)
    
    # Check that the program ran successfully
    assert result.returncode == 0, f"Program failed with exit code {result.returncode}"
    
    # Check the output
    expected_output = "Hello, World!\n"
    assert result.stdout == expected_output, f"Expected '{expected_output}', got '{result.stdout}'"
    
    print("✓ All tests passed!")

if __name__ == "__main__":
    test_hello_world_output()