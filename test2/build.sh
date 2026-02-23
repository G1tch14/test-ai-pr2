#!/bin/bash
echo "Building Java Console Application..."
javac HelloWorld.java
if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo ""
    echo "To run the application, use: java HelloWorld"
else
    echo "Build failed!"
    exit 1
fi