# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.5.0 procurement system built with Java 21, using PostgreSQL for data persistence and JWT for authentication. The system manages procurement requests with a dual approval workflow (manager and director approval) across different departments.

## Key Technologies

- **Framework**: Spring Boot 3.5.0 with Spring Security
- **Language**: Java 21
- **Database**: PostgreSQL (port 5333, database: procurement)
- **Authentication**: JWT tokens with custom security configuration
- **Build Tool**: Maven
- **Key Dependencies**: Spring Data JPA, Lombok, BCrypt, JJWT

## Development Commands

### Build and Run
```bash
# Build the project
mvn clean compile

# Run the application (starts on port 8081)
mvn spring-boot:run

# Build JAR
mvn clean package

# Run tests
mvn test
```

### Database
- PostgreSQL connection: `localhost:5333/procurement`
- Username: `postgres`, Password: `password`
- JPA DDL mode is set to `validate` - schema changes require manual SQL scripts
- Database schema is defined in `database_schema.sql`

## Architecture

### Package Structure
```
com.system.procurement/
├── config/          # Security and JWT configuration
├── controller/      # REST endpoints (currently only AuthController)
├── dto/            # Data transfer objects for API requests/responses
├── entity/         # JPA entities (User entity implemented)
├── exception/      # Custom exceptions
├── repository/     # Spring Data JPA repositories
├── service/        # Business logic interfaces
├── service/impl/   # Business logic implementations
└── util/           # Utility classes (JWT handling)
```

### Core Entities (Planned)
- **User**: Authentication and role management (director/manager/user)
- **Department**: Department management with budget tracking
- **Purchase Request**: Procurement requests with dual approval workflow
- **Department Managers**: Many-to-many relationship for department management

### Security Configuration
- JWT-based authentication with 24-hour token expiration
- Security filter configured to allow `/api/auth/**` endpoints publicly
- BCrypt password encoding
- JWT secret configured in application.yaml (should be externalized for production)

### Current Implementation Status
- ✅ User registration and login endpoints
- ✅ JWT token generation and validation
- ✅ Basic security configuration
- ❌ Department management APIs (planned)
- ❌ Purchase request APIs (planned)
- ❌ Approval workflow (planned)

## API Endpoints (Current)
- `POST /api/auth/login` - User authentication
- `POST /api/auth/registry` - User registration (creates users with 'pending' status)

## Development Notes

### User Management
- New users are created with 'pending' status by default
- Password encoding uses BCrypt
- User roles: 'director', 'manager', 'user'
- Email uniqueness is enforced at database level

### Database Schema
- Follow the detailed ERD in README.md for implementing remaining entities
- Use `database_schema.sql` as reference for table structure
- Department budget tracking and purchase request approval workflow are core features

### Code Style
- Uses Lombok for reducing boilerplate (getters/setters)
- Jakarta validation annotations for input validation
- Service layer pattern with interfaces and implementations
- Repository pattern using Spring Data JPA

### Testing
- Spring Boot Test framework is configured
- Spring Security Test available for endpoint security testing