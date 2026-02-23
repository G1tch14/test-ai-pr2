#!/bin/bash

# Build script for Hello World Console Application

echo "Building Hello World Console Application..."
echo "==========================================="

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed or not in PATH"
    echo "Please install Java 11 or higher"
    exit 1
fi

if ! command -v javac &> /dev/null; then
    echo "Error: Java compiler (javac) is not installed or not in PATH"
    echo "Please install Java Development Kit (JDK) 11 or higher"
    exit 1
fi

echo "Java found: $(java -version 2>&1 | head -1)"
echo "Java compiler found: $(javac -version 2>&1)"

# Clean previous builds
echo "Cleaning previous builds..."
rm -rf out target

# Create directories
mkdir -p out/classes
mkdir -p target

# Compile main code
echo "Compiling source code..."
javac -d out/classes src/main/java/com/example/helloworld/HelloWorldApp.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

# Create JAR file
echo "Creating JAR file..."
jar cfe target/hello-world.jar com.example.helloworld.HelloWorldApp -C out/classes .

if [ $? -eq 0 ]; then
    echo ""
    echo "Build successful!"
    echo "JAR file created: target/hello-world.jar"
    echo ""
    echo "To run the application:"
    echo "  java -jar target/hello-world.jar"
    echo "  or"
    echo "  ./run.sh"
else
    echo "Failed to create JAR file!"
    exit 1
fi