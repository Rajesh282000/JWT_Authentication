🔐 JWT Authentication Server

A Spring Boot-based authentication and authorization system implementing JWT (JSON Web Token) for secure user login, signup, and role-based access control.

✅ Features

✔ User Registration & Login with encrypted passwords (BCrypt)
✔ JWT Token-based Authentication for stateless security
✔ Role-based Access Control using Spring Security
✔ Custom Authentication Filter for token validation
✔ Exception Handling for unauthorized access
✔ REST API Endpoints for authentication & protected routes

🏗 Project Structure
├── pom.xml                         # Maven configuration
├── mvnw / mvnw.cmd                 # Maven Wrapper scripts
├── src
│   ├── main
│   │   ├── java/com/jwt
│   │   │   ├── JwtauthenticationserverApplication.java  # Main application
│   │   │   ├── config/                                 # Security configurations
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   ├── controller/                              # API Controllers
│   │   │   │   ├── AuthController.java
│   │   │   │   └── SimpleController.java
│   │   │   ├── Service/                                 # Authentication Services
│   │   │   │   ├── AuthService.java
│   │   │   │   └── AuthServiceImpl.java
│   │   │   ├── dto/                                     # Data Transfer Objects
│   │   │   ├── entity/                                  # Entities (User, Role)
│   │   │   └── repo/                                    # Repositories
│   │   └── resources/application.properties             # Configuration
│   └── test/java/com/jwt/JwtauthenticationserverApplicationTests.java

⚙️ Tech Stack

Java 17

Spring Boot (Web, Security, JPA)

JWT for authentication

MySQL / H2 Database

Lombok (optional for boilerplate reduction)

🚀 Getting Started
1️⃣ Clone the repository
git clone https://github.com/your-username/jwt-authentication-server.git
cd jwt-authentication-server

2️⃣ Configure Database

Update application.properties with your DB details:

spring.datasource.url=jdbc:mysql://localhost:3306/jwt_auth
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your-secret-key
jwt.expiration=86400000

3️⃣ Build & Run
./mvnw spring-boot:run

🔗 API Endpoints
Authentication
Method	Endpoint	Description
POST	/api/auth/signup	Register new user
POST	/api/auth/login	Login and get JWT
Protected Routes
Method	Endpoint	Role Required
GET	/api/admin	ADMIN
GET	/api/user	USER
✅ How JWT Works

User logs in → Server generates JWT

Client stores token (localStorage or headers)

On each request → Token sent in Authorization: Bearer <token> header

Server validates token & authorizes user
