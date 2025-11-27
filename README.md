🎯 Quiz Application – Microservices
📘 What It Does

This project is a microservices-based Quiz Application where quizzes are created from random questions based on a selected category.
Users can take quizzes, submit answers, and get their final score.

⭐ Main Features

Create quiz by selecting category and number of questions

Fetch quiz questions (without exposing correct answers)

Submit answers and calculate score

Manage questions: add, update, delete, fetch by category

Random question generation using MySQL

Communication between services using Feign Client

Service discovery using Eureka Server

Routing handled by API Gateway

🛠 Tech Used

Java 17

Spring Boot

Spring Cloud Eureka

Spring Cloud API Gateway

OpenFeign Client

MySQL Database

JPA / Hibernate

Maven

📚 Skills Learned / Used

Microservices architecture design

Inter-service communication using Feign Client

Load-balanced routing with API Gateway

Service discovery using Eureka

DTO-based data transfer

Clean controller–service–repository layered backend

REST API development with proper request/response models

▶️ How to Run

Start MySQL and configure DB in each service’s application.properties

Start Eureka Server

Start API Gateway

Start Question Service

Start Quiz Service

Test all APIs using Postman or browser

📌 API Endpoints
| Endpoint                        | Method | Description                       |
| ------------------------------- | ------ | --------------------------------- |
| `/question/allQuestions`        | GET    | Get all questions                 |
| `/question/addQuestion`         | POST   | Add a new question                |
| `/question/updateQuestion`      | PUT    | Update a question                 |
| `/question/deleteQuestion/{id}` | DELETE | Delete a question                 |
| `/question/category/{category}` | GET    | Get questions by category         |
| `/question/generate`            | GET    | Get random question IDs           |
| `/question/getQuestions`        | POST   | Get questions by IDs (DTO format) |
| `/question/getScore`            | POST   | Calculate score                   |


🧩 Quiz Service
| Endpoint             | Method | Description               |
| -------------------- | ------ | ------------------------- |
| `/quiz/create`       | POST   | Create a quiz             |
| `/quiz/getQuiz/{id}` | GET    | Get quiz questions (DTO)  |
| `/quiz/submit/{id}`  | POST   | Submit quiz and get score |


✔ Example Flow

Create a quiz → /quiz/create

Fetch quiz questions → /quiz/getQuiz/{id}

Submit answers → /quiz/submit/{id}

Get final score
