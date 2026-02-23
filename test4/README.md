# Hello World Console Application

A simple console application that prints "hello world" to the console.

## Prerequisites

- Java 11 or higher (JDK for building, JRE for running)

## Quick Start

### On Linux/Mac:
```bash
# Make scripts executable
chmod +x run.sh build.sh

# Build the application
./build.sh

# Run the application
./run.sh
```

### On Windows:
```bash
# Build the application
build.bat

# Run the application
run.bat
```

## Building the Application

### Using the provided scripts (recommended):

**Linux/Mac:**
```bash
./build.sh
```

**Windows:**
```bash
build.bat
```

### Using Maven (if installed):

```bash
mvn clean compile
```

To build the executable JAR file with Maven:
```bash
mvn clean package
```

This will create a JAR file in the `target` directory named `hello-world-console-1.0.0.jar`.

## Running the Application

### Using the provided scripts:

**Linux/Mac:**
```bash
./run.sh
```

**Windows:**
```bash
run.bat
```

### Using the executable JAR:

```bash
java -jar target/hello-world.jar
```

### Direct execution:

```bash
java -cp out/classes com.example.helloworld.HelloWorldApp
```

### Using Maven:

```bash
mvn exec:java -Dexec.mainClass="com.example.helloworld.HelloWorldApp"
```

## Running Tests

To run the unit tests with Maven:

```bash
mvn test
```

## Project Structure

```
src/main/java/com/example/helloworld/HelloWorldApp.java - Main application class
src/test/java/com/example/helloworld/HelloWorldAppTest.java - Unit tests
pom.xml - Maven build configuration (optional)
run.sh / run.bat - Run scripts
build.sh / build.bat - Build scripts
README.md - This file
```

## Expected Output

When you run the application, you should see:

```
hello world
```

## Manual Compilation and Execution

If you prefer to compile and run manually:

1. Compile:
   ```bash
   javac -d out/classes src/main/java/com/example/helloworld/HelloWorldApp.java
   ```

2. Run:
   ```bash
   java -cp out/classes com.example.helloworld.HelloWorldApp
   ```