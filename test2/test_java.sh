#!/bin/bash
echo "Testing Java Console Application..."
echo ""

echo "Step 1: Compile the Java program..."
javac HelloWorld.java
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to compile HelloWorld.java"
    exit 1
fi

echo "Step 2: Run the program and capture output..."
java HelloWorld > output.txt 2>&1
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to run HelloWorld"
    exit 1
fi

echo "Step 3: Check the output..."
if grep -q "Hello, World!" output.txt; then
    echo "SUCCESS: Program output contains 'Hello, World!'"
    echo ""
    cat output.txt
    rm output.txt
    exit 0
else
    echo "ERROR: Program output does not contain 'Hello, World!'"
    echo ""
    cat output.txt
    rm output.txt
    exit 1
fi