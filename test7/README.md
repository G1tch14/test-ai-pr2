# Spring Boot Demo Application

A Spring Boot application demonstrating layered architecture with proper separation of concerns.

## Features

- Spring Boot 3.2.0
- Spring Data JPA with H2 database
- RESTful API with proper DTO pattern
- Comprehensive testing (unit & integration)
- Global exception handling
- Input validation
- Layered architecture (Controller → Service → Repository)

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java          # Main application class
├── config/
│   └── GlobalExceptionHandler.java
├── controller/
│   └── UserController.java
├── domain/
│   ├── User.java                # JPA entity
│   └── dto/
│       ├── UserRequest.java     # Request DTO
│       └── UserResponse.java    # Response DTO
├── repository/
│   └── UserRepository.java      # Spring Data JPA repository
└── service/
    ├── UserService.java         # Service interface
    └── impl/
        └── UserServiceImpl.java # Service implementation
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### Build the Application

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access H2 Console

Navigate to `http://localhost:8080/h2-console` with the following settings:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create a new user |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users` | Get all users |
| PUT | `/api/users/{id}` | Update user by ID |
| DELETE | `/api/users/{id}` | Delete user by ID |

### Example Requests

**Create User:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe"
  }'
```

**Get User:**
```bash
curl http://localhost:8080/api/users/1
```

**Update User:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_updated",
    "email": "john.updated@example.com",
    "fullName": "John Updated"
  }'
```

**Delete User:**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

## Testing

### Run Unit Tests

```bash
mvn test
```

### Run Integration Tests

```bash
mvn verify
```

### Test Coverage

The project includes comprehensive test coverage with:
- Unit tests for service layer
- Integration tests for controller layer
- Test coverage reports (run `mvn site` to generate)

## Architecture Guidelines

This project follows these architectural principles:

1. **Layered Architecture**: Strict separation between Controller, Service, and Repository layers
2. **DTO Pattern**: Never expose domain entities directly in API
3. **Dependency Injection**: Use constructor injection for dependencies
4. **Transaction Management**: Service methods are transactional where appropriate
5. **Input Validation**: Validate all user input at controller level
6. **Error Handling**: Global exception handler for consistent error responses

## Code Quality

- Follows Java naming conventions
- Maximum method length: 30 lines
- Javadoc for all public methods
- No magic numbers - use named constants
- Comprehensive logging with SLF4J

## Dependencies

- Spring Boot Starter Web
- Spring Data JPA
- H2 Database (in-memory)
- Lombok (for reducing boilerplate code)
- Spring Boot Starter Validation
- Spring Boot Starter Test