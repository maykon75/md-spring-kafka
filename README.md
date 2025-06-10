# md-spring-kafka

## Description
A Spring Boot application that uses Apache Kafka to produce and consume messages. 
Consumed messages are persisted in an H2 in-memory database.

## Technologies
- Java 21
- Spring Boot 3.5.0
- Maven
- Apache Kafka
- H2 Database
- JPA
- Swagger (OpenAPI) for API documentation

## Requirements
- Java 21 installed
- Maven 3.9.x or higher installed
- Docker and Docker Compose installed (see src/main/resources/docker-compose.yml)

## Build and Run

### Build the project
`mvn clean install`
### Run the project
`mvn spring-boot:run`
### Docker installed
`docker-compose up -d`