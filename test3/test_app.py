#!/usr/bin/env python3
"""
Test for the console application
"""

import subprocess
import sys

def test_hello_world():
    """Test that the application prints Hello World"""
    result = subprocess.run([sys.executable, 'app.py'], 
                          capture_output=True, 
                          text=True)
    
    # Check that the output contains "Hello World!"
    assert "Hello World!" in result.stdout, f"Expected 'Hello World!' in output, got: {result.stdout}"
    
    # Check that there were no errors
    assert result.returncode == 0, f"Application exited with code {result.returncode}"
    assert result.stderr == "", f"Application produced stderr: {result.stderr}"
    
    print("All tests passed!")

if __name__ == "__main__":
    test_hello_world()