#!/bin/bash

# Simple build script for the Java project
echo "Building Java project..."

# Create output directory
mkdir -p target/classes

# Find all Java source files
find src/main/java -name "*.java" > sources.txt

# Compile Java files
echo "Compiling Java source files..."
javac -d target/classes @sources.txt

# Clean up
rm sources.txt

echo "Build complete! Compiled classes are in target/classes/"