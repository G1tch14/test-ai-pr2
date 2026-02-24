# Java Project

A basic Java project structure with Maven build configuration.

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               ├── Main.java
│               └── util/
│                   └── StringUtils.java
└── test/
    └── java/
        └── com/
            └── example/
                ├── MainTest.java
                └── util/
                    └── StringUtilsTest.java
```

## Build Configuration

This project uses Maven as the build tool with the following configuration:

- Java 17
- JUnit 5 for testing
- Mockito for mocking in tests

## Building the Project

To build the project:

```bash
mvn clean compile
```

## Running Tests

To run all tests:

```bash
mvn test
```

## Running the Application

To run the main application:

```bash
mvn compile exec:java -Dexec.mainClass="com.example.Main"
```

Or compile and run manually:

```bash
mvn compile
java -cp target/classes com.example.Main
```

## Dependencies

- JUnit 5.9.2
- Mockito 5.3.1

## Code Style

The project follows standard Java coding conventions:
- Classes and methods have Javadoc comments
- Methods are kept short (under 30 lines)
- Package structure follows domain naming convention
- Tests cover main functionality with edge cases