@echo off
echo Hello World Console Application
echo ================================

REM Check if Java is available
where java >nul 2>nul
if %errorlevel% equ 0 (
    echo Java found
    java -version 2>&1 | findstr /B "java version" || java -version 2>&1 | findstr /B "openjdk version"
    
    REM Create output directory
    if not exist out mkdir out
    
    REM Compile the Java code
    echo Compiling...
    javac -d out src\main\java\com\example\helloworld\HelloWorldApp.java
    
    if %errorlevel% equ 0 (
        echo Compilation successful!
        echo.
        echo Running application...
        echo ----------------------
        java -cp out com.example.helloworld.HelloWorldApp
    ) else (
        echo Compilation failed!
        exit /b 1
    )
) else (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 11 or higher to run this application
    exit /b 1
)