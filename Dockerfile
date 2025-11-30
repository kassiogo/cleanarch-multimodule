# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the project files
COPY . .

# Build the project (skipping tests to speed up build)
RUN mvn clean install -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built jar from the infrastructure module
COPY --from=build /app/infrastructure/target/*.jar app.jar

# Expose the port the app runs on (default Spring Boot is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
