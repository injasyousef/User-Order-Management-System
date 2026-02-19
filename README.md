# 📦 User Order Management System

A full-stack web application built with:

- **Backend:** Spring Boot + Spring Security + JWT + JPA + Hibernate  
- **Frontend:** React + TypeScript + Axios  
- **Database:** MySQL (or H2 for development)

**ERD**


<img width="681" height="211" alt="ERD" src="https://github.com/user-attachments/assets/12b6caf0-a4a7-40c0-8130-0961bc4cc9e8" />


The system allows users to:

- Register and authenticate (JWT-based authentication)
- View their profile
- Create orders
- View their own orders
- Secure endpoints using JWT authorization

---

# 🚀 Setup Instructions

## 1️⃣ Clone the Repository

```bash
git clone <your-repository-url>
cd user-order-management-system
```

---

# 🖥 Backend Setup (Spring Boot)

## 📌 Requirements

- Java 17+
- Maven
- MySQL (or H2)

---

## ⚙️ Environment Variables

Configure the following in your `application.properties`:

```properties
# Server
server.port=9090

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/user_order_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
application.security.jwt.secret-key=your_secret_key_here
application.security.jwt.expiration=86400000
application.security.jwt.refresh-token.expiration=604800000
```

Replace:

- `your_mysql_username`
- `your_mysql_password`
- `your_secret_key_here`

with your actual values.

---

# 🗄 Database Setup

## Option 1: MySQL (Recommended)

1. Open MySQL.
2. Create the database:

```sql
CREATE DATABASE user_order_db;
```

3. Make sure credentials match `application.properties`.

Spring Boot will automatically create tables because:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

## Option 2: H2 (Development Only)

Replace database configuration with:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

# ▶️ Run Backend

From backend directory:

```bash
mvn clean install
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:9090
```

Swagger UI (if enabled):

```
http://localhost:9090/swagger-ui.html
```

---

# 🌐 Frontend Setup (React + TypeScript)

## 📌 Requirements

- Node.js (v18+ recommended)
- npm

---

## 📦 Install Dependencies

Navigate to frontend folder:

```bash
cd frontend
npm install
```

---

## ▶️ Run Frontend

```bash
npm run dev
```

Frontend runs at:

```
http://localhost:5173
```

Make sure backend is running on port `9090`.

---

# 🔐 Authentication Flow

1. User logs in via:
   ```
   POST /api/v1/auth/authenticate
   ```
2. Backend returns:
   - access_token
   - refresh_token
3. Frontend stores JWT in `localStorage`.
4. Axios interceptor automatically adds:

```
Authorization: Bearer <token>
```

to secured requests.

---

# 📡 API Endpoints

## 🔐 Authentication

```
POST /api/v1/auth/authenticate
POST /api/v1/auth/refresh-token
```

## 👤 Users

```
GET /users/me
POST /users/signup
GET /users
```

## 📦 Orders

```
GET /api/orders
POST /api/orders
GET /api/orders/{id}
DELETE /api/orders/{id}
```

All order endpoints require authentication.

---

# 📁 Project Structure

```
backend/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── config/
 └── auth/

frontend/
 ├── api/
 ├── pages/
 ├── components/
 ├── context/
 ├── types/
 └── hooks/
```

---

# 🧠 Assumptions & Notes

- JWT is used for authentication and authorization.
- Users can only access their own orders.
- Passwords are encrypted using BCrypt.
- Stateless authentication (no sessions).
- CORS is configured to allow frontend (localhost:5173).
- Database schema auto-updates via Hibernate.


# 👨‍💻 Author
Yousef Injass

Developed as a full-stack project using Spring Boot and React.
