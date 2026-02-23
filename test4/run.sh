#!/bin/bash

# Simple script to compile and run the Hello World application

echo "Hello World Console Application"
echo "================================"

# Check if Java is available
if command -v java &> /dev/null; then
    echo "Java found: $(java -version 2>&1 | head -1)"
    
    # Create output directory
    mkdir -p out
    
    # Compile the Java code
    echo "Compiling..."
    javac -d out src/main/java/com/example/helloworld/HelloWorldApp.java
    
    if [ $? -eq 0 ]; then
        echo "Compilation successful!"
        echo ""
        echo "Running application..."
        echo "----------------------"
        java -cp out com.example.helloworld.HelloWorldApp
    else
        echo "Compilation failed!"
        exit 1
    fi
else
    echo "Error: Java is not installed or not in PATH"
    echo "Please install Java 11 or higher to run this application"
    exit 1
fi