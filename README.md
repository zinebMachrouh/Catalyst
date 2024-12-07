# 🚀 Welcome to Catalyst!

## Hello, Full Stack Developers! 👋

Welcome to **Catalyst**, a secure and scalable REST API application designed to manage products, categories, and users efficiently. This project integrates modern technologies and deployment solutions to deliver a robust application framework.

---

## 🛠️ Project Context

As a Full Stack Developer, you are tasked with:

- Securing APIs using **stateful authentication**.
- Containerizing and deploying the application with **Docker** and **Jenkins**.
- Maintaining technical documentation.
- Training teams for operational efficiency.

---
## 🌐 SWAGGER UI
- **Swagger UI** is a tool that visually presents the API documentation. It provides a user-friendly interface for exploring the API endpoints and their functionalities.
- To access the **Swagger UI** for **Catalyst**, run the application and navigate to `http://localhost:8085/swagger-ui/index.html` in your browser.

## 🧩 Postman Collection

- **Postman** is a popular API client that allows you to test API endpoints and monitor responses.
- To access the **Postman Collection** for **Catalyst**, click [here](https://swiftride.postman.co/workspace/My-Workspace~5068411f-ba84-490f-827d-09a1db076e70/collection/33286297-5e3aab66-4a8d-441b-bdfa-80fa17444069).

---
## 🎯 Key Features

### **Product Management**
- List products with pagination (**USER** or **ADMIN**).
- Search products by designation with pagination and sorting (**USER** or **ADMIN**).
- Search products by category (**USER** or **ADMIN**).
- Filter products by category with pagination and sorting (**USER** or **ADMIN**).
- Add a new product (**ADMIN only**).
- Update an existing product (**ADMIN only**).
- Delete a product (**ADMIN only**).

### **Category Management**
- List categories with pagination (**USER** or **ADMIN**).
- Search categories by name with pagination and sorting (**USER** or **ADMIN**).
- List products of a category with pagination and sorting (**USER** or **ADMIN**).
- Add a new category (**ADMIN only**).
- Update an existing category (**ADMIN only**).
- Delete a category (**ADMIN only**).

### **User Management**
- Authenticate: `/api/auth/login`.
- Register a new user: `POST /api/auth/register`.
- List users (**ADMIN only**): `GET /api/admin/users`.
- Manage roles for a user (**ADMIN only**): `PUT /api/admin/users/{id}/roles`.

---

## 🔒 Security

- **Spring Security Stateful Authentication**:
    - Authentication based on sessions using `JdbcAuthentication`.
    - Passwords encrypted with `BCryptPasswordEncoder`.

- **Role-Based Access**:
    - `/api/admin/*` endpoints require `ADMIN` role.
    - `/api/user/*` endpoints require `USER` role.

- **Development and Production Profiles**:
    - **Test Profile**: Security bypassed or configured for basic authentication.
    - **Production Profile**: Full security with robust authentication.

---

## 🌐 API Endpoints

### **Products**
- `GET /api/user/products`: List products (USER/ADMIN).
- `GET /api/user/products/search`: Search products (USER/ADMIN).
- `POST /api/admin/products`: Add a product (ADMIN).
- `PUT /api/admin/products/{id}`: Update a product (ADMIN).
- `DELETE /api/admin/products/{id}`: Delete a product (ADMIN).

### **Categories**
- `GET /api/user/categories`: List categories (USER/ADMIN).
- `GET /api/user/categories/search`: Search categories (USER/ADMIN).
- `POST /api/admin/categories`: Add a category (ADMIN).
- `PUT /api/admin/categories/{id}`: Update a category (ADMIN).
- `DELETE /api/admin/categories/{id}`: Delete a category (ADMIN).

### **Users**
- `POST /api/auth/login`: User login.
- `POST /api/auth/register`: User registration.
- `GET /api/admin/users`: List users (ADMIN).
- `PUT /api/admin/users/{id}/roles`: Assign roles to a user (ADMIN).

---

## 🧩 Prerequisites

- **Java 21**
- **Maven** for dependency management
- **Spring Boot** for API development
- **PostgreSQL** or another relational database
- **Docker** and **Jenkins** for containerization and deployment
- **Postman** for API testing
- **Swagger UI** for API documentation

---

## ⚙️ Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/catalyst.git
   cd catalyst
    ```
   
2. Update the `application.yml` file in the `resources` directory with your database connection details.
3. Build and run the application using Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   
---
## 🚀 Deployment with Docker

1. **Dockerize the Application**:
   - Modify the `Dockerfile` to package the application.
   - Modify the `docker-compose.yml` file to define services.
   - Run `docker compose up -d --build` to build and run the application.
   - Access the application at `http://localhost:8085`.

2. **Deploy with Jenkins**:
   - Access the Jenkins dashboard at `http://localhost:9090`.
   - Create a new pipeline job and configure the pipeline script.
   - Run the pipeline to build and deploy the application.

## 🎉 Get Started with Catalyst Today!

For any questions, feedback, or suggestions, feel free to reach out to us. 📧
