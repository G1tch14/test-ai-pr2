@echo off
echo Building Java Console Application...
javac HelloWorld.java
if %ERRORLEVEL% EQU 0 (
    echo Build successful!
    echo.
    echo To run the application, use: java HelloWorld
) else (
    echo Build failed!
    exit /b 1
)