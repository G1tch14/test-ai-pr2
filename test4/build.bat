@echo off
echo Building Hello World Console Application...
echo ===========================================

REM Check if Java is available
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 11 or higher
    exit /b 1
)

where javac >nul 2>nul
if %errorlevel% neq 0 (
    echo Error: Java compiler (javac) is not installed or not in PATH
    echo Please install Java Development Kit (JDK) 11 or higher
    exit /b 1
)

echo Java found
java -version 2>&1 | findstr /B "java version" || java -version 2>&1 | findstr /B "openjdk version"
echo Java compiler found
javac -version

REM Clean previous builds
echo Cleaning previous builds...
if exist out rmdir /s /q out
if exist target rmdir /s /q target

REM Create directories
mkdir out\classes
mkdir target

REM Compile main code
echo Compiling source code...
javac -d out\classes src\main\java\com\example\helloworld\HelloWorldApp.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b 1
)

REM Create JAR file
echo Creating JAR file...
jar cfe target\hello-world.jar com.example.helloworld.HelloWorldApp -C out\classes .

if %errorlevel% equ 0 (
    echo.
    echo Build successful!
    echo JAR file created: target\hello-world.jar
    echo.
    echo To run the application:
    echo   java -jar target\hello-world.jar
    echo   or
    echo   run.bat
) else (
    echo Failed to create JAR file!
    exit /b 1
)