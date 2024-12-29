# API Gateway Microservice  

The **API Gateway** microservice acts as a central access point, routing requests to various microservices within the system. Built using Spring Boot and Netflix Zuul/Spring Cloud Gateway, it streamlines communication between clients and backend services.  

## Features  
- Centralized entry point for all microservices.  
- Dynamic routing based on service discovery (Netflix Eureka).  
- Load balancing and resiliency.  
- Path-based routing for microservice endpoints.  
- Handles cross-cutting concerns like authentication and logging.  

## Prerequisites  
- Java 17  
- Maven 3+  
- Netflix Eureka Server (for service discovery).  

## Configuration  
Update the `application.yml` file with appropriate configurations:  
```yaml
server:
  port: 8181

spring:
  application:
    name: api-gateway

eureka:
  client:
    service-url:
      defaultZone: http://<EUREKA_SERVER_HOST>:8761/eureka/
```

## Usage  
1. Build the project:  
   ```bash
   mvn clean install
   ```  
2. Run the application:  
   ```bash
   java -jar target/api-gateway-<version>.jar
   ```  
3. Access endpoints via the gateway:  
   - Product Service: `http://localhost:8181/product-service/<endpoint>`  
   - Coupon Service: `http://localhost:8181/coupon-service/<endpoint>`  

## Docker Support  
To containerize the application, use the provided `Dockerfile`
Build and run the Docker image:  
```bash
docker build -t api-gateway .
docker run -p 8181:8181 api-gateway
```  

## License  
This project is licensed under the MIT License.  
