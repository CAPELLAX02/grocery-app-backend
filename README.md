# Grocery Store - Backend Project

This project is a Spring Boot-based backend template designed for frontend and mobile developers to unleash their creativity on the client-side. It provides essential APIs for user authentication, product management, order processing, and more.

---

## Motivation

The **Grocery Store - Backend Project** aims to simplify the backend setup for developers working on e-commerce applications. By offering a robust API template, this project allows developers to focus on crafting unique user experiences on the frontend while leveraging pre-built, scalable backend features. Key motivations include:

1. Reducing development overhead for client-side teams.
2. Demonstrating modern backend design best practices.
3. Providing a ready-to-use API that integrates essential e-commerce functionality.

Whether you're developing a grocery delivery app or exploring backend concepts, this project provides a reliable starting point.

---

## API Documentation
For a detailed API reference, you can check out the [API Documentation](docs/GroceryStore_API_Documentation_v1.0.pdf). The API Documentation also includes the **Postman** collection of the whole backend project.

---

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Sample Data](#sample-data)
- [Environment Variables](#environment-variables)
- [Contribution](#contribution)

---

## Features

- **Authentication and Authorization**
    - JWT-based token authentication.
    - Token refresh mechanism for seamless user sessions.

- **Product Management**
    - Endpoints to fetch products, view details, and manage reviews.

- **Cart and Wishlist**
    - APIs to manage shopping carts and user wishlists.

- **Order Processing**
    - Create and view order details.

- **Email Notifications**
    - Integrated email service for account activation and password recovery.

- **Dockerized Deployment**
    - Ready-to-run setup using Docker Compose.

- **Comprehensive API Documentation**
    - Detailed documentation to help developers utilize the backend effectively.

---

## Technologies Used

- **Spring Boot** for backend framework.
- **Spring Security** for a robust security infrastructure.
- **Spring Data MongoDB** for database interactions.
- **MongoDB** for database.
- **JWT** for authentication.
- **Lombok** for reducing boilerplate code.
- **Thymeleaf** for server-side rendering.
- **Java Mail Sender** for email functionality.
- **Docker** for containerization.

---

## Setup Instructions

### Prerequisites

1. Install **Docker** and **Docker Compose**.
2. Ensure you have **Java 17** and **Maven** installed if running locally.

### Run with Docker

1. **Clone the repository:**
   ```bash
   git clone https://github.com/CAPELLAX02/grocery-app-backend.git
   cd grocery-app-backend
   ```

2. **Start the application with Docker Compose:**
   ```bash
   sudo docker compose up -d
   ```

3. **Access the application:**
- Base URL of the API will be live on `http://localhost:8080/api/v1`.

---

## Sample Data

- There is a sample `products.json` file in the `/src/main/resources/static` directory. 
- You can import this json file to your MongoDB database via either MongoDB Atlas or MongoDB Compass.
- Additionally, you can edit all the product fields in the json file before loading the sample data to the database. For example, for better user experience, you can change the dummy `imageURL` links with actual product image URLs. 
 
---

## Environment Variables

There is a `yml` file named `example.application.yml` in the `src/main/resources` directory. You should change the name of this file as `application.yml`, and change the following environment variables for your convenience:

#### `spring.data.mongodb.uri`: 
- The MongoDB connection URI.
- Example: `mongodb+srv://username:password@cluster.mongodb.net/database_name`.
- To set up MongoDB Atlas, [follow this guide](https://www.mongodb.com/products/platform/atlas-database).

#### `spring.data.mongodb.database`: 
- The name of your MongoDB database.

#### `spring.mail.host`: 
- The SMTP host for sending emails. For example, use `smtp.gmail.com` for Gmail.

#### `spring.mail.port`: 
- The SMTP port. For Gmail's SMTP, this is typically `587`.

#### `spring.mail.username`: 
- Your email address used for sending emails.

#### `spring.mail.password`:  
- The app-specific password generated by your email provider.
- For Gmail, you can create this password under your Google Account settings `(Security > App Passwords)`.

#### `security.jwt.access-token.secret`: 
- A secret key for signing JWT tokens. 
- You can generate an appropriate jwt secret using:
- ```bash
  openssl rand -base64 129 | tr -d '\n'
  ```

#### `security.jwt.access-token.expiration`: 
- Access token expiration time in milliseconds. 
- Example: `86400000` for 24 hours.

#### `security.jwt.refresh-token.expiration`: 
- Refresh token expiration time in milliseconds.
- Example: `604800000` for 7 days.

### Here’s an example `application.yml` configuration:
```yaml
spring:
  
  # other configurations...
  
  data:
    mongodb:
      uri: YOUR_MONGODB_URI_WITH_CREDENTIALS/YOUR_DB_NAME
      database: YOUR_DB_NAME
      
  # other configurations...
  
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-email-app-password
    
  # other configurations...
  
security:
  jwt:
    access-token:
      secret: YOUR_GENERATED_SECRET
      expiration: 86400000
    refresh-token:
      expiration: 604800000
      
# other configurations...
```

---

## Contribution

We welcome contributions to make this project even better. Here's how you can contribute:

1. **Fork the repository:**

- Click the **"Fork"** button on the top-right corner of this page.
- Clone your forked repository:

  ```bash
  git clone https://github.com/your-username/grocery-app-backend.git
  cd grocery-app-backend
  ```

2. **Create a new branch for your changes:**
-
  ```bash
  git checkout -b feature/your-feature-name
  ```

3. **Make your changes and commit them:**
-
  ```bash
  git add .
  git commit -m "Add your message here"
  ```

4. **Push your changes to your fork:**
-
  ```bash
  git push origin feature/your-feature-name
  ```

5. **Submit a Pull Request:**

- Open a pull request from your feature branch to the main repository.








