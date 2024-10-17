# Welcome to the RESTMates project repository!

RESTMates is an apartment rental and booking web application that allows users to browse available apartments, view detailed information about each property, and book them for specific dates. Users can register and log in to manage their bookings and preferences.

Developed by:
- [![szidzse](https://github.com/szidzse.png?size=50)](https://github.com/szidzse)
- [![Vazul15](https://github.com/Vazul15.png?size=50)](https://github.com/Vazul15)

![home](https://github.com/user-attachments/assets/c6d6fabf-591c-405d-bf74-b05b68562386)

## Table of Contents
- [Overview](#overview)
- [Technologies](#technologies)
- [Getting Started](#getting-Started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Features](#features)
  - [Dockerization](#dockerization)
  - [User Management](#user-management)
  - [Frontend Integration](#frontend-integration)
- [Usage](#usage)

## Overview
RESTMates is an apartment rental platform with a robust backend and an appealing frontend. Users can register, login, upload new accommodations, make reservations. This project demonstrates comprehensive full-stack development, including database management, RESTful API design, user authentication, and moderin user interface.

### Backend:
- [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
- [![Spring Web MVC](https://img.shields.io/badge/Spring%20Web%20MVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/guides/gs/serving-web-content/)
- [![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
- [![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
- [![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)

### Database:
- [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

### Frontend:
- [![React Vite](https://img.shields.io/badge/React%20Vite-4D7E8D?style=for-the-badge&logo=react&logoColor=white)](https://vitejs.dev/)
- [![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)](https://www.w3schools.com/css/)

### Containerization:
- [![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

## Getting Started

### Prerequisites
#### To Run the Project with Docker:
  - [![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

#### To Run Backend and Frontend Separately:
  - [![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
  - [![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=maven&logoColor=white)](https://maven.apache.org/)
  - [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
  - [![Node.js](https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)](https://nodejs.org/)
  - [![NPM](https://img.shields.io/badge/NPM-CB3837?style=for-the-badge&logo=npm&logoColor=white)](https://www.npmjs.com/)

### Installation

#### Running the Project with Docker
1. Clone the repository from GitHub into your desired folder:
   ```bash
    git clone https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-szidzse

    # navigate into the project directory
    cd <foldername>
   ```
2. Set environment variables in `.env.sample`:
   ```bash
   # db env variables
   POSTGRES_DB=YOUR_DB_NAME
   POSTGRES_USER=YOUR_DB_USERNAME
   POSTGRES_PASSWORD=YOUR_DB_PASSWORD

   ## backend env variables
   SPRING_DATASOURCE_USERNAME=YOUR_DB_USERNAME
   SPRING_DATASOURCE_PASSWORD=YOUR_DB_PASSWORD
   CODECOOL_APP_JWTSECRET=YOUR_JWT_SECRET_KEY
   ```
   *The JWT secret key should be 64 characters long and should only include alphanumeric characters (A-Z, a-z, 0-9). It is advisable to avoid using special characters such as `-`, `/`, `+`, and `=` to prevent potential issues with encryption and encoding. You can generate one on this website: https://asecuritysite.com/encryption/plain*

3. Rename `.env.sample` to `.env`:
   ```
   mv .env.sample .env
   ```

4. Build and start the containers:
   ```bash
   docker compose up --build
   ```

   *Docker will automatically set up the database and run the backend and frontend services.*

#### Running Backend and Frontend Separately

##### Backend Setup:

   - Navigate to the backend directory:
     ```bash
      cd backend
     ```

   - Build the project:
     ```bash
      mvn clean install
     ```

   - Set environment variables:
     - Update application.properties (src/main/resources/application.properties) with your database credentials and security data or you can use terminal commands:
      - Option 1: Using PowerShell
        ```bash
        $env:DATABASE_URL="jdbc:postgresql://localhost:5432/YOUR_DATABASE_URL"
        $env:DATABASE_USERNAME="YOUR_DATABASE_USERNAME"
        $env:DATABASE_PASSWORD="YOUR_DATABASE_PASSWORD"
        $env:JWTSECRETKEY="YOUR_JWT_SECRET_KEY"
        ```
      *The JWT secret key should be 64 characters long and should only include alphanumeric characters (A-Z, a-z, 0-9). It is advisable to avoid using special characters such as `-`, `/`, `+`, and `=` to prevent potential issues with encryption and encoding. You can generate one on this website: https://asecuritysite.com/encryption/plain*

      - Option 2: Using Command Prompt
        ```bash
        set DATABASE_URL=jdbc:postgresql://localhost:5432/YOUR_DATABASE_URL
        set DATABASE_USERNAME=YOUR_DATABASE_USERNAME
        set DATABASE_PASSWORD=YOUR_DATABASE_PASSWORD
        set JWTSECRETKEY=YOUR_JWT_SECRET_KEY
        ```
       *JWT Secret key should be 64 characters long.*

   - Run the application:
     ```bash
      mvn spring-boot:run
     ```

##### Frontend Setup:

   - Navigate to the frontend directory:
     ```bash
      cd ../frontend
     ```

   - Install dependencies:
     ```bash
      npm install
     ```

   - Start the development server:
       ```bash
        npm run dev
       ```

## Features
  ### Dockerization
  - The application is containerized using Docker.
  - Docker Compose manages multi-container setups, including the PostgreSQL database.
  - Running with Docker Compose eliminates the need to manually create the database or configure environment variables locally.

  ### User Management
  - User Registration and Authentication using Spring Security and JWT tokens.
  - Features include:
    - User Registration: Allows new users to sign up.
    - Authentication: Users log in to receive a JWT token.
    - Authorization: Secures endpoints to authenticated users.
    - Role-based Access Control: Differentiates between user and admin roles.

  ### Frontend Integration
  - Built with Vite-React and Material Tailwind for a responsive and dynamic user interface.
  - Features include:
    - Responsive design for various screen sizes.
    - RESTful API integration for smooth data exchange between frontend and backend.
    - Client-side routing with React Router for multiple pages.

## Usage
Using El Proyecte Grande:
  - Register an Account:
    - Detail: Sign up to create a new account.
    - Visual: ![signup](https://github.com/user-attachments/assets/bf069c5a-d875-469b-b544-6c218eb3793e)
  - Log In:
    - Detail: Use your credentials to log in.
    - Visual: ![signin](https://github.com/user-attachments/assets/00eeaa16-bbb1-425b-8409-f45f42ae2314)
  - See accommodation details:
    - Detail: Display accommodation details on a different page.
    - Visuals: ![details](https://github.com/user-attachments/assets/f8b094dd-781b-47b3-b570-a8b874a4ed71)
  - Make reservation:
    - Detail: Booking accommodation for the appropriate period.
    - Visual: ![booking](https://github.com/user-attachments/assets/d0d50ba2-3126-40a4-b905-b6e4fc452f01)
  - Manage apartments:
    - Detail: Manage your apartments.
    - Visual: ![manage](https://github.com/user-attachments/assets/e1d1c6e0-3e1b-418d-a6a1-0f7190c42552)
  - See profile:
    - Detail: View your profiel and reservations.
    - Visuals: ![reservation](https://github.com/user-attachments/assets/955bf5a4-b620-4c41-a778-c2732eae1952)

