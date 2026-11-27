# Spring-WebFlux-UI-with-Spring-Security

[![Java CI with Maven](https://github.com/hendisantika/Spring-WebFlux-UI-with-Spring-Security/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/Spring-WebFlux-UI-with-Spring-Security/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.2.0-brightgreen?logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2026.0.0-brightgreen?logo=spring)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-blue?logo=dependabot)

A microservices-based demonstration project showcasing Spring WebFlux, Spring Security, JWT authentication, and service
discovery patterns using Spring Cloud Netflix Eureka.

## Architecture Overview

This project consists of 5 microservices that work together to demonstrate a complete microservices architecture:

### Services

#### 1. Spring Discovery Server (Port: 8761)

- **Technology**: Spring Cloud Netflix Eureka Server
- **Purpose**: Service registry and discovery
- **Credentials**: admin / password
- **URL**: http://localhost:8761
- **Features**:
    - Service registration and discovery
    - Basic authentication
    - Health monitoring dashboard

#### 2. Customer Service (Port: 8082)

- **Technology**: Spring Boot REST API with JPA/H2
- **Purpose**: Backend microservice providing customer data
- **Features**:
    - RESTful API endpoints
    - H2 in-memory database
    - Auto-registered with Eureka
    - H2 Console enabled

#### 3. Spring Gateway (Port: 8081)

- **Technology**: Spring Boot with Spring Security & JWT
- **Purpose**: API Gateway with JWT-based authentication
- **JWT Key**: zab23bc8934gh=1
- **Token Validity**: 30 minutes (1800000ms)
- **Features**:
    - JWT token generation and validation
    - User authentication endpoint (/authenticate)
    - Routes requests to Customer Service
    - User/Role management with JPA
    - CORS configuration for http://localhost:9080

#### 4. Spring Web (Port: 9080)

- **Technology**: Spring MVC with Thymeleaf
- **Purpose**: Traditional web application UI
- **Gateway URL**: http://localhost:8080
- **Features**:
    - Server-side rendered pages with Thymeleaf
    - Form-based authentication
    - Integrates with API Gateway for authentication
    - Customer listing interface
    - WebFlux reactive support

#### 5. Spring Web Flux (Port: 9081)

- **Technology**: Spring WebFlux with Thymeleaf
- **Purpose**: Reactive web application UI
- **Features**:
    - Reactive streaming with Project Reactor
    - Server-sent events for real-time updates
    - JWT-based authentication
    - Reactive customer listing (both sync and async)
    - Chunked rendering support

## Technology Stack

- **Spring Boot**: 4.2.0
- **Java**: 25 (Temurin)
- **Spring Cloud**: 2026.0.0
- **Spring Security**: 7.x (with JWT)
- **JJWT**: 0.13.0 (HS512, key derived via SHA-512 from `jwt.security.key`)
- **Spring WebFlux**: Reactive programming
- **Thymeleaf**: 3.x (spring6 dialect)
- **H2 Database**: 2.x in-memory database
- **Netflix Eureka**: Service discovery
- **JWT**: JSON Web Tokens for authentication
- **Maven**: Build tool
- **GitHub Actions**: CI build on JDK 25
- **Dependabot**: Automated Maven & GitHub Actions updates

## Prerequisites

- JDK 25 or higher
- Maven 3.9+

## Building the Project

All services can be built together using Maven:

```bash
mvn clean compile
```

Or with tests:

```bash
mvn clean install
```

## Running the Services

### Quick Start (Recommended)

Use the provided helper scripts to start/stop all services:

```bash
# Start all services in correct order
./start-all-services.sh

# Stop all services
./stop-all-services.sh
```

Logs will be written to the `logs/` directory.

### Manual Start

Services should be started in the following order:

### 1. Start Discovery Server

```bash
cd spring-discovery-server
mvn spring-boot:run
```

Wait until Eureka dashboard is accessible at http://localhost:8761

### 2. Start Customer Service

```bash
cd customer-service
mvn spring-boot:run
```

Verify registration in Eureka dashboard

### 3. Start Spring Gateway

```bash
cd spring-gateway
mvn spring-boot:run
```

Verify registration in Eureka dashboard

### 4. Start Spring Web (Optional)

```bash
cd spring-web
mvn spring-boot:run
```

Access at http://localhost:9080

### 5. Start Spring Web Flux (Optional)

```bash
cd spring-web-flux
mvn spring-boot:run
```

Access at http://localhost:9081

## Authentication

### JWT Authentication (Gateway)

To get a JWT token:

```bash
POST http://localhost:8081/authenticate
Content-Type: application/json

{
  "username": "user1",
  "password": "user1"
}
```

Response:

```json
{
  "jwt": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "user1",
  "roles": [
    "ROLE_USER"
  ]
}
```

### Using JWT Token

Include the token in subsequent requests:

```bash
GET http://localhost:8081/findAll
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## Service Endpoints

### Discovery Server

- Dashboard: http://localhost:8761
- Login: admin / password

### Customer Service

- Find All: http://localhost:8082/findAll
- Find by ID: http://localhost:8082/findCustomer?id={id}
- H2 Console: http://localhost:8082/h2-console

### Spring Gateway

- Authentication: http://localhost:8081/authenticate (POST)
- Find All Customers: http://localhost:8081/findAll (GET - requires JWT)
- Find Customer: http://localhost:8081/findCustomer?id={id} (GET - requires JWT)

### Spring Web

- Login: http://localhost:9080/login
- Customers: http://localhost:9080/findAllCustomers

### Spring Web Flux

- Login: http://localhost:9081/login
- Customers (sync): http://localhost:9081/findAllCustomers
- Customers (reactive): http://localhost:9081/findAllCustomersReactive

## Configuration

### Security Credentials

**Eureka Server**:

- Username: admin
- Password: password

**Gateway Users** (default):

- user1 / user1
- user2 / user2

### Database

All services use H2 in-memory database. No external database setup required.

## Continuous Integration

Every push and pull request to `master` runs the **Java CI with Maven** workflow
([`.github/workflows/maven.yml`](.github/workflows/maven.yml)):

- Builds and tests all modules with `mvn -B package` on **Temurin JDK 25**
- Caches the Maven repository for faster builds
- Submits the dependency graph to GitHub (optional, never fails the build)
- Can be triggered manually via `workflow_dispatch`

## Dependency Updates (Dependabot)

[`.github/dependabot.yml`](.github/dependabot.yml) keeps dependencies fresh every **Monday 21:00 (Asia/Jakarta)**:

| Ecosystem      | Directories                  | Groups                                                                 | Commit prefix  |
|----------------|------------------------------|------------------------------------------------------------------------|----------------|
| Maven          | `/` and each module          | `spring` (Spring Boot + Spring Cloud), `jwt`, `maven-minor-patch`      | `build(deps)`  |
| GitHub Actions | `/`                          | `github-actions` (all actions)                                         | `ci(deps)`     |

Spring Boot and Spring Cloud are grouped in one PR because each Spring Cloud release train only supports specific
Spring Boot versions (e.g. Spring Cloud 2026.0.x ↔ Spring Boot 4.2.x).

## Recent Updates

- Upgraded to **JDK 25** (local build & GitHub Actions)
- Upgraded to **Spring Boot 4.2.0** & **Spring Cloud 2026.0.0**
- Migrated to Spring Security 7 APIs (`DaoAuthenticationProvider(UserDetailsService)`, lambda DSL for `ServerHttpSecurity`)
- Migrated JWT handling to JJWT 0.13 API (`verifyWith`, `parseSignedClaims`, `SecretKey` signing)
- Moved `RestTemplateBuilder`/`TestRestTemplate` to the new `spring-boot-restclient` / `spring-boot-resttestclient` modules
- Fixed H2 2.x reserved keyword `USER` in the gateway schema (`NON_KEYWORDS=USER`)
- Enhanced Dependabot (grouped updates, GitHub Actions ecosystem, labels, conventional commit prefixes)
- Upgraded to Spring Boot 3.5.6
- Migrated from javax.* to jakarta.* packages
- Updated Spring Security configuration to use SecurityFilterChain
- Updated Spring Cloud to version 2023.0.2
- Replaced deprecated WebSecurityConfigurerAdapter
- Updated Thymeleaf to Spring 6 dialect
- All services successfully compiled and packaged

## Additional Documentation

For detailed service status, configurations, and troubleshooting, see:

- **[SERVICE_STATUS.md](SERVICE_STATUS.md)** - Comprehensive service status and health check guide
- Startup/Stop Scripts: `start-all-services.sh` and `stop-all-services.sh`

## Troubleshooting

### Services not discovering each other

- Ensure Discovery Server is running first
- Check Eureka dashboard at http://localhost:8761
- Verify eureka.client.serviceUrl.defaultZone in application.yml

### Authentication failures

- Verify JWT key is consistent across services
- Check token expiry time
- Ensure proper Bearer token format in Authorization header

### Build failures

- Ensure JDK 25 is being used (`java -version`)
- Run `mvn clean install` to refresh dependencies
- Check for port conflicts

## License

This project is for demonstration purposes.

## Author

- Hendi Santika
- Email: hendisantika@gmail.com
- Telegram: @hendisantika34
