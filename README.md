# Travel Agency System
 
## Overview
 
The Travel Agency System is a Spring Boot-based application designed to efficiently manage customers, tours, and addresses. It incorporates robust validation mechanisms, authentication, and centralized error handling to ensure data integrity and security.
 
## Features
 
* **Customer Management**: Enables the creation, updating, and retrieval of customer details.
 
* **Tour Management**: Facilitates the association of customers with tours and the management of tour information.
 
* **Address Management**: Handles both permanent and communication addresses for customers.
 
* **Data Validation**: Implements entity-level and DTO-level validations to maintain data integrity.
 
* **Authentication**: Secures application endpoints using JWT-based authentication.
 
* **Centralized Error Handling**: Provides a consistent approach to managing validation and application-specific exceptions.
 
## Technologies Used
 
* **Backend**: Spring Boot, Hibernate, JPA
 
* **Validation**: Jakarta Bean Validation API
 
* **Authentication**: Spring Security with JWT (JSON Web Tokens)
 
* **Database**: MySQL (or any JPA-compatible database)
 
* **Build Tool**: Maven
 
* **Java Version**: 17 or higher
 
## Project Structure

```

src/

├── main/

│   ├── java/

│   │   └── com/

│   │       └── kk/

│   │           ├── controller/        # Contains REST controllers to handle HTTP requests.

│   │           ├── dtos/              # Data Transfer Objects (DTOs) for data transfer between layers.

│   │           ├── entity/            # Entity classes representing database tables.

│   │           ├── exception/         # Custom exception classes for specific error handling.

│   │           ├── security/          # Security configurations and JWT handling logic.

│   │           ├── service/           # Business logic and service layer implementations.

│   │           ├── validations/       # Validation groups for conditional validation rules.

│   │           └── utils/             # Utility classes for common functionality.

│   ├── resources/

│   │   ├── application.properties   # Application configuration (database, server settings, etc.).

│   │   └── static/                # Static resources (if applicable).

│   └── webapp/                  # Web application files (if applicable).

└── test/                        # Unit and integration tests.

```

## Validation Flow
 
* **DTO-Level Validation**: Ensures the integrity of data received in requests before processing.
 
* **Entity-Level Validation**: Validates data before it is persisted to the database.
 
* **Central Error Handling**: Utilizes the `ErrorHandler` class to manage and format validation errors and custom exceptions.
 
## Authentication Flow
 
* **JWT-Based Authentication**: Secures API endpoints using JSON Web Tokens.
 
* **Authentication Entry Point**: Handles unauthorized access attempts with custom error responses.
 
## How to Run
 
1.  Clone the repository:
 
    ```bash

    git clone [https://github.com/your-repo/travel-agency-system.git](https://github.com/your-repo/travel-agency-system.git)

    cd travel-agency-system
 
    ```
 
    *(Replace `https://github.com/your-repo/travel-agency-system.git` with the actual repository URL)*
 
2.  Configure the database:

    Modify the `application.properties` file to configure your MySQL (or other JPA-compatible) database connection details.
 
3.  Build the project using Maven:
 
    ```bash

    mvn clean install
 
    ```
 
4.  Run the Spring Boot application:
 
    ```bash

    mvn spring-boot:run
 
    ```
 
5.  Access the application:

    The application will typically be accessible at `http://localhost:8080`.
 
## API Endpoints
 
### Customer Endpoints

- `GET /api/travel-agency/customers` - Retrieve all customers.

- `GET /api/travel-agency/customers/{id}` - Retrieve a customer by ID.

- `GET /api/travel-agency/customers/lastname/{name}` - Retrieve customers by last name.

- `GET /api/travel-agency/customers/tour/{name}` - Retrieve customers by tour package.

- `POST /api/travel-agency/customers` - Add a new customer (requires `ADMIN` role).

- `PUT /api/travel-agency/customers/{id}` - Update an existing customer by ID (requires `ADMIN` role).

- `DELETE /api/travel-agency/customers/{id}` - Delete a customer by ID (requires `ADMIN` role).
 
### Tour Endpoints

- `GET /api/travel-agency/tours` - Retrieve all tours.

- `GET /api/travel-agency/tours/{name}` - Retrieve a tour by name.

- `POST /api/travel-agency/tours` - Add a new tour (requires `ADMIN` role).

- `PUT /api/travel-agency/tours/{id}` - Update an existing tour by ID (requires `ADMIN` role).
 
### Authentication Endpoints
 
* `POST /auth/login`: Authenticate user credentials and retrieve a JWT token.
 
## Error Handling
 
* **Validation Errors**: When validation fails, the application returns structured JSON responses detailing the validation errors.
 
* **Custom Exceptions**: Application-specific errors are handled by the `ErrorHandler`, returning appropriate HTTP status codes and informative error messages.
 
 
## Contact
 
For any questions or issues, please contact [kranthikiran43604@gmail.com](mailto:kranthikiran43604@gmail.com)
 
