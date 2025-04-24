Here's a clean and professional `README.md` file for your Spring Boot + MongoDB CRUD API project:

---

# 🛠️ Let's Play – Spring Boot CRUD API with MongoDB

This project is a **RESTful API** built using **Spring Boot** and **MongoDB**. It includes **user** and **product** management with full **CRUD operations**, **role-based authentication**, and essential **security measures**.

---

## 🚀 Features

- ✅ User registration & login
- ✅ Token-based authentication with **JWT**
- ✅ Role-based authorization (`admin` / `user`)
- ✅ CRUD operations for **Users** and **Products**
- ✅ Public access to **GET /products**
- ✅ Input validation & error handling
- ✅ Secure password storage (hashing & salting)
- ✅ MongoDB injection protection
- ✅ No password exposed in API responses

---

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data MongoDB**
- **JWT (JSON Web Tokens)**
- **Lombok**
- **Maven**

---

## 📦 Database Schema

```mermaid
classDiagram
    User "1" -- "n" Product : Owns
    User : +String id
    User : +String name
    User : +String email
    User : +String password
    User : +String role
    Product : +String id
    Product : +String name
    Product : +String description
    Product : +Double price
    Product : +String userId
```

---

## 📡 API Endpoints

### 🔐 Auth

| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| POST   | `/api/users/`      | Register new user        |
| POST   | `/api/users/login` | Authenticate user & get JWT |

### 👤 User (Admin only)

| Method | Endpoint                  | Description            |
|--------|---------------------------|------------------------|
| GET    | `/api/users`              | List all users         |
| DELETE | `/api/users/{id}`         | Delete user            |

### 👤 User (Admin + Owner)

| Method | Endpoint                   | Description            |
|--------|----------------------------|------------------------|
| GET    | `/api/users/{id}`          | Get user by ID         |
| PUT    | `/api/users/{id}`          | Update user            |
| GET    | `/api/users/{id}/products` | Get product by user ID |
| PUT    | `/api/products/{id}`       | Update product         |
| DELETE | `/api/products/{id}`       | Delete product         |


### 📦 Product (Non-Authenticated users)

| Method | Endpoint             | Description                |
|--------|----------------------|----------------------------|
| GET    | `/api/products`      | List all products (public) |
| GET    | `/api/products/{id}` | Get product by ID          |


### 📦 Product (Authenticated users)

| Method | Endpoint             | Description                |
|--------|----------------------|----------------------------|
| POST   | `/api/products`      | Create new product         |

---

## 🔐 Security

- Passwords hashed with **BCrypt**
- Inputs validated to avoid **MongoDB injection**
- Only JWT-authenticated users can perform write operations
- Role-based API access control
- Sensitive user data hidden from API responses

---

## 🔄 Error Handling

All exceptions are caught and return appropriate JSON responses:

- `400 Bad Request`: Invalid input
- `401 Unauthorized`: Invalid or missing token
- `403 Forbidden`: Access denied due to role
- `404 Not Found`: Resource not found
- `409 Conflict`: Email or resource already exists

✅ No `5XX` errors are exposed to users.

---

## ✨ Bonus (Optional Features Implemented)

- ✅ Custom **CORS** policies
- ✅ **Rate limiting** using Spring filters or external libraries like Bucket4J *(only on login)*

---

## 🧪 Testing

Tested using:

- **Postman** for endpoint functionality
- Manual validation of error codes
- JWT access & refresh token lifecycle
- Role-based access control
- Edge cases & injection attempts

---

## 🧰 How to Run

### ⚙️ Prerequisites

- Java 17+
- Maven
- MongoDB running locally (or use MongoDB Atlas)

### ▶️ Run the project

```bash
git clone https://github.com/mamadbah2/lets-play-api.git
cd lets-play-api
mvn clean install
mvn spring-boot:run
```

---

## 🛡️ Environment Variables

Set in `application.properties`:

```properties
spring.application.name=lets-play
spring.data.mongodb.uri=mongodb+srv://########:########@cluster0.wlwhrhg.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
spring.data.mongodb.database=letsplay
spring.security.user.name=bobo
spring.security.user.password=password
# HTTPS Configuration
server.ssl.key-store=src/main/resources/keystore.p12
server.ssl.key-alias=letsplaykey
server.ssl.key-store-type=PKCS12
server.ssl.key-store-password=password
# Redis
spring.data.redis.port=6379
spring.data.redis.host=localhost
spring.cache.type=redis
```

---

## 📚 Resources

- [Spring Initializer](https://start.spring.io/)
- [JWT Tutorial](https://jwt.io/introduction/)
- [MongoDB Security Best Practices](https://www.mongodb.com/security)

---

## 📄 License

This project is not licensed.

---

Un informaticien ne crie pas, il URL