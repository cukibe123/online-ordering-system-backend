# Online Ordering System (Backend)
This is a minimal Online Ordering System (Backend), implemented fully in Java (Spring Framework).

This project is constructed with the purpose to improve, get used to the concepts of Spring MVC, JPA, Hibernate, JUnit and Mockito in writing unit tests. Neon (PostgreSQL) is used as the serverless database for the storage of this project.

The project also has a frontend development. For the frontend of this project, please refer to this [repository](https://github.com/cukibe123/online-ordering-system-frontend)

# Project Structure
The project follows a domain-oriented layered architecture using Spring Boot, with clear separation between configuration, business logic, and data access.
```
online-ordering-system/
├── .idea/ # IDE configuration (IntelliJ)
├── .mvn/ # Maven wrapper files
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── tuanan/test/
│ │ │ ├── category/ # Category domain (controller, service, repo, etc.)
│ │ │ ├── config/ # Application & security configuration
│ │ │ ├── food/ # Food domain logic
│ │ │ ├── invoice/ # Invoice & billing logic
│ │ │ ├── orders/ # Order domain logic
│ │ │ └── OnlineOrderingSystemApplication.java
│ │ └── resources/
│ │ ├── application.propertise
│ │ └── static/
│ └── test/ # Unit & integration tests
├── target/ # Compiled output (generated)
├── pom.xml # Maven configuration
└── README.md
```
