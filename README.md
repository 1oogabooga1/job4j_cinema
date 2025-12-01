# Job4j Cinema 

## Overview
**Job4j Cinema** — a simple single-theater ticket booking web application implemented as a course project.  
The goal of the task: implement user registration/login, display of movies & sessions, and the ticket purchase flow with basic concurrency protection and UX for success/failure.

#### This implementation uses:
- **Spring Boot** 
- **Thymeleaf** 
- **Bootstrap** 
- **Liquibase**
- **Sql2o**
- **PostgreSQL** 

## Features (User-facing):
- User registration & login (session-based authentication)  
- Browse movie catalog (Cineteca)  
- View schedule (sessions) and session details  
- Ticket purchase flow: choose row and seat → Buy / Cancel  
- Success page after purchase 
- Failure page if seat already booked (clear message + link back to booking)  
- Only authenticated users may buy tickets — anonymous users are redirected to login

## Pages / Views
- **Cinema** — general info about the resource 
- **Film Sessions** — list of sessions and related movies; click a session → buy page  
- **Cineteca** — list of movies available in the theater  
- **Purchase page** — session + movie info + two selects (row, seat) + `Buy` / `Cancel`  
- **Purchase success** — friendly message, e.g. “You successfully bought a ticket for seat X in row Y”  
- **Purchase failure** — friendly message, e.g. “Purchase failed. The seat may already be taken. Try booking again.”  
- **Register** — registration page with 3 fieilds - "name", "login" and "password" and 2 buttons - "register" and "cancel"
- **Login** — login page with 2 fields - "login" and "password", 2 buttons - "login" and "cancel", 1 link - "register"
- **Navbar**: Logo → Home, Film Sessions, Cineteca, Register/Login (if anonymous) or Username/Logout (if authenticated)

## Tech Stack
- Backend: **Java 17**, **Spring Boot**  
- Templates: **Thymeleaf**, **Bootstrap** for responsive UI  
- Persistence: **Sql2o** (DAO layer) + **PostgreSQL** (H2 for tests / dev optional)  
- Migrations: **Liquibase**  
- Build & CI: **Maven**, **GitHub Actions**  
- Testing: **JUnit 5**, **Mock testing**

## How to run 
1. Clone repository:
 ```bash
 git clone https://github.com/1oogabooga1/job4j_cinema.git
 cd job4j_cinema
 ```
2. Configure DB (PostgreSQL). Example env variables (or use application-local.yml):
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/job4j_cinema
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=false
```
3. Build & run:
```bash
mvn clean package
java -jar target/job4j_cinema.jar
```
4. Open in browser:
http://localhost:8080/

## Links 
| Page   | Link   |
|--------|--------|
| Film Session | Text   |
| Film Session | Text   |
