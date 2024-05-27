# Consultancy Company Web Application

## Introduction

This project is a secure and scalable web application developed using Java EE8, Maven, and NetBeans IDE. It is designed to facilitate the operations of a consultancy company, providing various features for managing client interactions, project workflows, and administrative tasks.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Detailed Code Analysis](#detailed-code-analysis)
  - [Overview](#overview)
  - [Code Structure](#code-structure)
  - [Key Components](#key-components)
  - [Security](#security)
  - [Database](#database)
  - [Build and Deployment](#build-and-deployment)
- [Contributors](#contributors)

## Features

- **Client Management**: Add, update, and manage client details.
- **Project Management**: Create and track projects, assign tasks, and monitor progress.
- **User Authentication**: Secure login and user role management.
- **Scalability**: Built with scalability in mind to handle growing amounts of data and users.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Adnan25z/ConsulatancyCompany.git
   
2. Clone the repository:
   ```bash
   cd ConsulatancyCompany

3. Build the project using Maven:
   ```bash
   mvn clean install

## Usage
1. Deploy the application on a Java EE-compatible server (GlassFish).
2. Access the application via the web browser at http://localhost:8080/ConsulatancyCompany.

## Dependencies
- Java EE 8
- Maven
- NetBeans IDE
- MySQL or any other compatible database

## Configuartion
1. Configure the database connection in persistence.xml located in src/main/resources/META-INF/.
2. Set up your server configuration to match the project requirements.

## Detailed Code Analysis

### Overview

The project is a Java EE-based web application intended for managing a consultancy company. It includes features for client and project management, user authentication, and more.

### Code Structure

1. **Model Layer**: Contains the entity classes representing the database tables.
2. **DAO Layer**: Data Access Objects for interacting with the database.
3. **Service Layer**: Business logic of the application.
4. **Controller Layer**: Servlets and managed beans handling HTTP requests.
5. **View Layer**: JSPs and JSF pages for the user interface.

### Key Components

- **Entities**: Java classes annotated with JPA annotations to map to database tables.
- **EJBs**: Stateless session beans for business logic and transactions.
- **Servlets**: Handle client requests and route to appropriate services.
- **JSPs/JSF**: For front-end, providing dynamic content rendering.

### Security

- **User Authentication**: Implemented using Java EE security annotations.
- **Role-Based Access Control**: Different user roles with specific permissions.

### Database

- **Persistence**: Configured using JPA with a MySQL database. The `persistence.xml` file contains the database connection settings.
- **Entities**: Each table in the database is represented by an entity class.

### Build and Deployment

- **Maven**: Used for build automation, managing dependencies, and packaging the application.
- **Deployment**: The application can be deployed on any Java EE-compatible application server. I used GlassFish.

## Contributors
- Adnan Zafar





