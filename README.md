#  PlantSwap Application

PlantSwap is a backend REST API built with **Java**, **Spring Boot**, and **MongoDB**.
It simulates a plant exchange and selling platform where users can create accounts, list plants, and manage transactions.

This project demonstrates backend development concepts such as:

* RESTful API design
* CRUD operations
* MongoDB integration
* Request validation
* API testing with Postman

---

##  Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Data MongoDB
* Jakarta Validation
* MongoDB
* Maven

---

##  Installation Requirements

To run this project locally, you need:

* JDK
* IntelliJ IDEA (CE or UE)
* MongoDB (Compass or local server)
* Maven

---

## ▶️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/shamsalsaffar/Plantswap_appliction.git
```

---

### 2. Open the project

Open the project in **IntelliJ IDEA**.

---

### 3. Start MongoDB

Make sure MongoDB is running on:

```text
localhost:27017
```

---

### 4. Configure database connection

In `src/main/resources/application.properties`:

```properties
spring.application.name=Plantswap
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=plantswap
```

---

### 5. Run the application

```bash
mvn clean install
mvn spring-boot:run
```

---

##  Project Functionality

The API includes three main areas:

* User management
* Plant management
* Transaction management

---

##  Database Structure

The application uses MongoDB with these collections:

* **users** → stores user information
* **plants** → stores plant listings
* **transactions** → stores purchase transactions

---

##  API Endpoints

### 👤 User Management

Base path: `/api/user`

| Method | Endpoint         | Description    |
| ------ | ---------------- | -------------- |
| POST   | `/api/user`      | Create user    |
| GET    | `/api/user`      | Get all users  |
| GET    | `/api/user/{id}` | Get user by ID |
| PUT    | `/api/user/{id}` | Update user    |
| DELETE | `/api/user/{id}` | Delete user    |

#### Example – Create User

```json
{
  "name": "Shams AlSaffar",
  "email": "shams@example.com",
  "password": "123456",
  "phone": "0701234567"
}
```

---

###  Plant Management

Base path: `/api/plants`

| Method | Endpoint                      | Description           |
| ------ | ----------------------------- | --------------------- |
| POST   | `/api/plants`                 | Create plant          |
| POST   | `/api/plants/{userId}`        | Create plant for user |
| GET    | `/api/plants`                 | Get all plants        |
| GET    | `/api/plants/{id}`            | Get plant by ID       |
| GET    | `/api/plants/status/{status}` | Get plants by status  |
| PUT    | `/api/plants/{id}`            | Update plant          |
| DELETE | `/api/plants/{id}`            | Delete plant          |

#### Example – Create Plant

```json
{
  "name": "Monstera",
  "trivialName": "Swiss Cheese Plant",
  "age": 2,
  "plantSize": "MEDIUM",
  "plantType": "INDOOR",
  "waterRequirement": "MEDIUM",
  "lightRequirement": "INDIRECT",
  "difficultyLevel": "EASY",
  "plantStatus": "AVAILABLE",
  "payMethod": "SALE",
  "price": 150
}
```

---

###  Transaction Management

Base path: `/api/transactions`

| Method | Endpoint                     | Description           |
| ------ | ---------------------------- | --------------------- |
| POST   | `/api/transactions/purchase` | Create transaction    |
| GET    | `/api/transactions`          | Get all transactions  |
| GET    | `/api/transactions/{id}`     | Get transaction by ID |
| PUT    | `/api/transactions/{id}`     | Update transaction    |
| DELETE | `/api/transactions/{id}`     | Delete transaction    |

#### Example – Create Transaction

```json
{
  "buyerId": "buyer123",
  "sellerId": "seller456",
  "plantId": "plant789",
  "status": "PENDING"
}
```

---

##  Business Rules

* A user cannot have more than **10 active plant listings**
* Plants marked for exchange cannot be sold
* Both parties must agree before an exchange is completed
* Plant price must be between **50 and 1000 SEK**

---

##  API Documentation (Postman)



```

```

---

## ⚠️ Known Limitations

* A user can currently buy or exchange plants with themselves
* Plants can have both price and exchange settings at the same time

### Suggested Improvements

* Prevent transactions where buyer = seller
* Improve validation depending on plant type (sale vs exchange)

---

##  Future Improvements

* Add Service layer
* Add DTOs
* Add authentication (JWT)
* Improve validation
* Add Swagger documentation
* Write unit tests

---

##  Author

**Shams AlSaffar**

---

##  Purpose of the Project

This project was built to practice:

* Java backend development
* Spring Boot
* REST API design
* MongoDB
* API testing with Postman
