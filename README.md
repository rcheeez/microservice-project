# Eureka Server - Service Discovery

This repository contains the Eureka Server, a Service Discovery server for microservices. It enables client microservices to register and discover other communication services.

## Features
- Centralized service registry for microservices.
- Enables service-to-service communication without hardcoding service addresses.
- Provides a user-friendly dashboard to monitor registered services.

## How It Works
1. **Registration**: Client microservices register with the Eureka Server by providing their metadata.
2. **Discovery**: Services query the server to discover other services.
3. **Health Checks**: Periodic health checks ensure services are available and healthy.

## Prerequisites
- Java 17 or higher
- Maven
- Spring Boot framework

## Running the Server
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/eureka-server.git
   cd eureka-server-registry
   ```
2. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. Access the Eureka dashboard at:
   ```
   http://localhost:8761
   ```

## Configuration
Update the `application.yml` file to customize the server settings:
```yaml
server:
  port: 8761
spring:
  application:
    name: eureka-server

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

## Docker Support
Build and run the Eureka Server using Docker:
1. Build the Docker image:
   ```bash
   docker build -t eureka-server .
   ```
2. Run the container:
   ```bash
   docker run -p 8761:8761 eureka-server
   ```

## License
This project is licensed under the MIT License.
