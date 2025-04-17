# user-service
The user-service microservice manages users.

## TechStack
* Java, Spring Boot
* PostgreSQL, Flyway, Spring Data JPA
* Maven, JUnit 5, Testcontainers

## Prerequisites
* JDK 21 or later
* Docker ([installation instructions](https://docs.docker.com/engine/install/))
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* PostgreSQL
 
Refer [docker-compose based infra setup](https://github.com/feature-tracker/docker-infra) for running dependent services.

## How to get started?

```shell
$ git clone https://github.com/feature-tracker/user-service.git
$ cd user-service

# Run tests
$ ./mvnw verify

# Format code
$ ./mvnw spotless:apply

# Run application
# Once the dependent services (PostgreSQL, etc) are started, 
# you can run/debug UserServiceApplication.java from your IDE.
```
