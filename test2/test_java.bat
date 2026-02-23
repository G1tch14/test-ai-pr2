@echo off
echo Testing Java Console Application...
echo.

echo Step 1: Compile the Java program...
javac HelloWorld.java
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to compile HelloWorld.java
    exit /b 1
)

echo Step 2: Run the program and capture output...
java HelloWorld > output.txt 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to run HelloWorld
    exit /b 1
)

echo Step 3: Check the output...
findstr /C:"Hello, World!" output.txt > nul
if %ERRORLEVEL% EQU 0 (
    echo SUCCESS: Program output contains "Hello, World!"
    echo.
    type output.txt
    del output.txt
    exit /b 0
) else (
    echo ERROR: Program output does not contain "Hello, World!"
    echo.
    type output.txt
    del output.txt
    exit /b 1
)