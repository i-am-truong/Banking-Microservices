# Configuration Management Documentation

## Overview
This document describes the comprehensive configuration management solution implemented for the Banking Microservices architecture. The solution provides centralized, secure, and maintainable configuration across all services.

## Architecture

### Components
1. **Config Server** (Port 8888)
   - Spring Cloud Config Server
   - Centralized configuration management
   - Configuration encryption/decryption
   - Basic authentication security

2. **Microservices** (Config Clients)
   - Accounts Service (Port 8080)
   - Cards Service (Port 9000)
   - Loans Service (Port 8090)

## Configuration Management Features

### 1. Externalized Configuration
- All configuration moved outside of application code
- Environment-specific configuration files (dev, prod)
- Configuration profiles for different environments
- Dynamic configuration updates without service restarts

### 2. Configuration Server
- **URL**: http://localhost:8888
- **Authentication**: Basic Auth (configuser/configpass)
- **Storage**: Native filesystem (classpath:/config-repo/)
- **Encryption**: Symmetric key encryption for sensitive data

### 3. Environment Profiles

#### Development Profile (`dev`)
- H2 in-memory database
- Debug logging enabled
- Development-specific messages
- Console access enabled

#### Production Profile (`prod`)
- MySQL database configuration
- Encrypted sensitive values
- Production logging levels
- Environment variable support

### 4. Security Features
- Basic authentication for config server access
- Symmetric key encryption for sensitive data
- Environment variable override support
- Encrypted database passwords in production

## Configuration Structure

```
config-server/src/main/resources/config-repo/
├── accounts.yml           # Common accounts configuration
├── accounts-dev.yml       # Development environment
├── accounts-prod.yml      # Production environment
├── cards.yml             # Common cards configuration
├── cards-dev.yml         # Development environment
├── cards-prod.yml        # Production environment
├── loans.yml             # Common loans configuration
├── loans-dev.yml         # Development environment
└── loans-prod.yml        # Production environment
```

## Usage

### Starting the Services

1. **Start Config Server First**:
   ```bash
   cd config-server
   ./mvnw spring-boot:run
   ```

2. **Start Microservices**:
   ```bash
   cd accounts
   ./mvnw spring-boot:run
   ```

### Configuration Endpoints

Each service exposes configuration information through these endpoints:

- `GET /{service}/config-info` - Returns externalized configuration
- `GET /{service}/application-name` - Returns application name
- `GET /actuator/env` - Spring Boot actuator environment endpoint

### Config Server Endpoints

- `GET /{application}/{profile}` - Get configuration for application and profile
- `GET /{application}/{profile}/{label}` - Get configuration with Git label
- `POST /encrypt` - Encrypt a value
- `POST /decrypt` - Decrypt a value

### Examples

#### Retrieve Accounts Development Configuration
```bash
curl http://configuser:configpass@localhost:8888/accounts/dev
```

#### Encrypt a Value
```bash
curl -X POST http://configuser:configpass@localhost:8888/encrypt -d "mysecretvalue"
```

#### Test Configuration from Service
```bash
curl http://localhost:8080/config-info
```

## Environment-Specific Configuration

### Development Environment
- **Profile**: `dev`
- **Database**: H2 in-memory
- **Logging**: Debug level
- **Features**: H2 console enabled

### Production Environment
- **Profile**: `prod`
- **Database**: MySQL with encrypted passwords
- **Logging**: Info/Warn levels
- **Features**: Production optimizations

## Security Considerations

### 1. Authentication
- Config server requires basic authentication
- Default credentials: `configuser/configpass`
- Should be changed in production environments

### 2. Encryption
- Symmetric key encryption: `bernie-bank-symmetric-key`
- Encrypted values use `{cipher}` prefix
- Automatic decryption by config server

### 3. Environment Variables
- Production passwords use environment variable overrides
- Format: `${ENV_VAR_NAME:default_value}`
- Secure secrets management in containers

## Deployment

### Docker Deployment
```yaml
version: '3.8'
services:
  config-server:
    image: "bernietruong/config-server:latest"
    ports:
      - "8888:8888"
  
  accounts:
    image: "bernietruong/accounts:accounts"
    depends_on:
      - config-server
    environment:
      SPRING_CONFIG_IMPORT: "configserver:http://config-server:8888"
```

### Environment Variables for Production
```bash
export DB_USERNAME=accounts_user
export DB_PASSWORD=secure_production_password
export SPRING_PROFILES_ACTIVE=prod
```

## Monitoring and Management

### Health Checks
- Config server: `http://localhost:8888/actuator/health`
- Services: `http://localhost:{port}/actuator/health`

### Configuration Refresh
Services support configuration refresh via:
```bash
curl -X POST http://localhost:{port}/actuator/refresh
```

## Troubleshooting

### Common Issues

1. **Service fails to start**
   - Verify config server is running
   - Check authentication credentials
   - Validate configuration import URL

2. **Configuration not loading**
   - Verify application name matches config file prefix
   - Check active profile setting
   - Ensure config server connectivity

3. **Encryption/Decryption errors**
   - Verify encryption key configuration
   - Check cipher value format
   - Ensure encryption is enabled

### Logs to Check
- Config server: Look for configuration retrieval requests
- Services: Check for config import success/failure
- Network: Verify connectivity between services

## Best Practices

1. **Security**
   - Use strong encryption keys in production
   - Rotate credentials regularly
   - Use environment variables for sensitive data

2. **Configuration Management**
   - Keep common configuration in base files
   - Use environment-specific overrides
   - Version control configuration changes

3. **Deployment**
   - Start config server before dependent services
   - Use health checks for service dependencies
   - Implement proper secret management

## Future Enhancements

1. **Git Backend**: Move to Git-based configuration repository
2. **Asymmetric Encryption**: Implement RSA key pairs for enhanced security
3. **Service Discovery**: Integrate with service discovery mechanisms
4. **Configuration Webhooks**: Implement automatic refresh triggers
5. **Configuration Validation**: Add schema validation for configurations