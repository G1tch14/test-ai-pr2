# Example Spring Boot Application

## Configuration

This application uses Spring Boot with multiple configuration options.

### Configuration Files

1. **application.properties** - Primary configuration file with property-based configuration
2. **application.yml** - Alternative YAML-based configuration file
3. **logback-spring.xml** - Advanced logging configuration with structured JSON logging
4. **.env.example** - Template for environment variables

### Environment Variables

The application is configured to use environment variables for sensitive data. Copy `.env.example` to `.env` and update the values:

```bash
cp .env.example .env
# Edit .env with your configuration
```

### Database Configuration

By default, the application uses an in-memory H2 database for development. To use a different database:

1. Update the environment variables in `.env`:
   - `DATABASE_URL`: JDBC connection URL
   - `DATABASE_USERNAME`: Database username
   - `DATABASE_PASSWORD`: Database password
   - `DATABASE_DRIVER`: JDBC driver class name
   - `JPA_DIALECT`: Hibernate dialect

2. Supported databases:
   - **H2** (default): `jdbc:h2:mem:testdb`
   - **PostgreSQL**: `jdbc:postgresql://localhost:5432/dbname`
   - **MySQL**: `jdbc:mysql://localhost:3306/dbname`

### Logging Configuration

The application includes structured logging with:
- Console output with colored formatting
- File-based logging with rotation (10MB max, 30 days retention)
- JSON logging for log aggregation systems
- Profile-specific logging levels

### Running the Application

#### Development Mode
```bash
# Set environment variables
export SPRING_PROFILES_ACTIVE=dev

# Run the application
./mvnw spring-boot:run
# or
java -jar target/example-app.jar
```

#### Production Mode
```bash
# Set production environment variables
export SPRING_PROFILES_ACTIVE=prod
export JPA_DDL_AUTO=validate
export JPA_SHOW_SQL=false

# Run the application
java -jar target/example-app.jar
```

### Configuration Properties

#### Key Properties

| Property | Description | Default |
|----------|-------------|---------|
| `server.port` | HTTP server port | 8080 |
| `spring.datasource.url` | Database URL | `jdbc:h2:mem:testdb` |
| `spring.jpa.hibernate.ddl-auto` | Database schema strategy | `update` |
| `spring.jpa.show-sql` | Show SQL statements | `true` |
| `logging.level.com.example` | Application log level | `DEBUG` |
| `spring.profiles.active` | Active Spring profiles | `dev` |

#### Profile-Specific Configuration

- **dev**: Development profile with debug logging and H2 console
- **prod**: Production profile with optimized settings
- **test**: Test profile with minimal logging

### H2 Database Console

When running in development mode with H2 database:
- Access: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

### Health and Metrics

The application includes Spring Boot Actuator endpoints:
- Health: http://localhost:8080/actuator/health
- Info: http://localhost:8080/actuator/info
- Metrics: http://localhost:8080/actuator/metrics

### Building and Testing

```bash
# Build the application
./mvnw clean package

# Run tests
./mvnw test

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Security Notes

1. Default credentials are for development only. Change them in production.
2. Environment variables should be managed securely (not committed to version control).
3. The H2 console should be disabled in production.