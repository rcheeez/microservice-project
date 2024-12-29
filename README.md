# Product Microservice

The **Product Microservice** is a Spring Boot-based application designed to manage product-related operations. It provides a REST API for performing CRUD operations on products and supports purchasing products with the application of a coupon code for discounts.

---

## Features

- **CRUD Operations**: Create, Read, Update, and Delete products.
- **Coupon Integration**: Apply a coupon code at the time of purchase for discounts.
- **Database Support**: Connects to a MySQL database for persistent data storage.

---

## Technologies Used

- **Spring Boot**  
- **Spring Data JPA**  
- **H2/MySQL Database**  
- **REST APIs**

---

## Endpoints

1. **Product Management**  
   - `POST /api/product/save` - Add a new product  
   - `GET /api/product/get/{id}` - Retrieve product details by ID  
   - `PUT /api/product/update/{id}` - Update product details  
   - `DELETE /api/products/delete/{id}` - Delete a product  

2. **Purchase Product**  
   - `POST /api/product/purchase` - Purchase a product with a coupon code applied  

---

## Prerequisites

- Java 17+
- Maven
- MySQL (for production)
- Docker (optional, for containerization)

---

## Running the Application

1. **Clone the Repository**  
   ```bash
   git clone <repository-url>
   cd product-microservice
   ```

2. **Build and Run**  
   ```bash
   mvn clean install
   java -jar target/product-microservice.jar
   ```

3. **Run with Docker**  
   ```bash
   docker build -t product-microservice .
   docker run -p 8080:8080 product-microservice
   ```

---

## License

This project is licensed under the [MIT License](LICENSE).  

---
