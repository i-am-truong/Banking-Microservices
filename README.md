# Banking Microservices with Configuration Management

This project implements a comprehensive configuration management solution for a banking microservices architecture using Spring Cloud Config.

## Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Config Server │    │   Accounts      │    │     Cards       │    │     Loans       │
│   (Port 8888)   │◄───│   (Port 8080)   │    │   (Port 9000)   │    │   (Port 8090)   │
│                 │    │                 │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
```

## Features

### ✅ Externalized Configuration
- All configuration moved outside application code
- Environment-specific profiles (dev, test, prod)
- Dynamic configuration updates without service restarts

### ✅ Configuration Server
- Spring Cloud Config Server with Basic Auth
- Centralized configuration repository
- Configuration encryption for sensitive data
- RESTful configuration management APIs

### ✅ Microservices Integration
- Spring Cloud Config Client integration
- Type-safe configuration with DTOs
- Configuration endpoints for monitoring
- Refresh scope for dynamic updates

### ✅ Security & Environment Management
- Encrypted sensitive configuration values
- Environment variable support
- Production-ready configurations
- Access control for configuration server

## Quick Start

1. **Start Config Server**:
   ```bash
   cd config-server
   ./mvnw spring-boot:run
   ```

2. **Start Microservices**:
   ```bash
   cd accounts
   ./mvnw spring-boot:run
   ```

3. **Test Configuration**:
   ```bash
   curl http://localhost:8080/config-info
   ```

4. **Run Test Script**:
   ```bash
   ./test-config-management.sh
   ```

## Configuration Profiles

- **dev**: Development environment with H2 database and debug logging
- **test**: Test environment with updated configuration messages
- **prod**: Production environment with MySQL and encrypted passwords

## Documentation

See [CONFIGURATION_MANAGEMENT.md](CONFIGURATION_MANAGEMENT.md) for detailed documentation including:
- Complete setup instructions
- Configuration structure
- Security considerations
- Troubleshooting guide
- Best practices

## Services

| Service | Port | Description |
|---------|------|-------------|
| config-server | 8888 | Spring Cloud Config Server |
| accounts | 8080 | Banking Accounts Microservice |
| cards | 9000 | Banking Cards Microservice |
| loans | 8090 | Banking Loans Microservice |

## Key Configuration Endpoints

- `GET /{service}/config-info` - Service configuration
- `GET /actuator/env` - Environment properties
- `POST /actuator/refresh` - Refresh configuration
- `GET /{application}/{profile}` - Config server endpoint

## Technologies Used

- Spring Boot 3.5.4
- Spring Cloud Config 2025.0.0
- Spring Security (Basic Auth)
- Maven
- Java 17
- H2 Database (dev/test)
- MySQL (production)

## Benefits Achieved

- ✅ Consistent configuration across all services
- ✅ Secure secrets management with encryption
- ✅ Easy deployment across multiple environments
- ✅ Reduced configuration errors and inconsistencies
- ✅ Better operational visibility and control
- ✅ Simplified configuration updates and rollbacks