### Coupon Microservice

The **Coupon Microservice** is a Spring Boot application that handles all operations related to coupons. It supports generating, validating, and performing CRUD operations for coupons.

---

#### **Features**
- Generate a new coupon.
- Validate a coupon's details.
- Create, Read, Update, and Delete (CRUD) coupon records.

---

#### **Endpoints**
1. **Generate Coupon**  
   `POST /api/coupon/generate`  
   - Creates a new coupon with unique details.

2. **Validate Coupon**  
   `GET /api/coupon/validate/{couponCode}`  
   - Checks the validity of a coupon.

3. **CRUD Operations**  
   - `GET /api/coupon/all` - Retrieve all coupons.  
   - `GET api/coupon/get/{couponCode}` - Retrieve a specific coupon by CouponCode.    
   - `DELETE /api/coupon/delete/{id}` - Delete a coupon.
---

#### **Technology Stack**
- **Language**: Java  
- **Framework**: Spring Boot  
- **Database**: MySQL (H2 for development/testing)  
- **Build Tool**: Maven  
- **Containerization**: Docker

---

#### **How to Run**

1. **Clone the Repository**  
   ```bash
   git clone <repository-url>
   cd coupon-microservice
   ```

2. **Build the Application**  
   ```bash
   mvn clean install
   ```

3. **Run with Docker**  
   Ensure Docker is installed and running.  
   ```bash
   docker build -t coupon-microservice .
   docker run -p 8001:8001 coupon-microservice
   ```

4. **Access the Service**  
   The application runs on [http://localhost:8001](http://localhost:8001).

---
