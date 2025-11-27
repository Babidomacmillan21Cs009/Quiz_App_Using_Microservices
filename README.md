🎯 Quiz Application – Microservices (Spring Boot)

A simple Quiz Application built using Spring Boot Microservices, Eureka Server, API Gateway, Feign Client, and MySQL.

The application has two main services:

Question Service – manages questions

Quiz Service – creates quizzes, fetches questions, and calculates scores

🚀 Tech Stack

Java 17

Spring Boot

Spring Cloud (Eureka, Feign, Gateway)

MySQL

JPA / Hibernate

Maven

🧱 Services Overview
1. Question Service

Add / update / delete questions

Get questions by category

Generate random questions for a quiz

Convert Question → DTO to hide right answers

Calculate score

2. Quiz Service

Create quiz

Fetch quiz questions using Feign Client

Submit quiz and get score

Store quiz details in database

3. Service Registry

Eureka Server for service discovery

4. API Gateway

Single entry point

Routes requests to microservices

📌 Endpoints
Question Service

/question/allQuestions
/question/addQuestion
/question/updateQuestion
/question/deleteQuestion/{id}
/question/category/{category}
/question/generate
/question/getQuestions
/question/getScore

Quiz Service

/quiz/create
/quiz/getQuiz/{id}
/quiz/submit/{id}

⚙️ How to Run

Start Eureka Server

Run Question Service

Run Quiz Service

Run API Gateway

Test APIs using Postman or browser

✔ Features

Clean microservices architecture

Feign Client communication

Separate databases for each service

Random question generation

Score calculation

DTO to hide answers
