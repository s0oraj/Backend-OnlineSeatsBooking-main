# Use an OpenJDK runtime as a base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the target directory to the container
COPY target/OnlineSeatBook-0.0.1-SNAPSHOT.jar OnlineSeatBook-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot application uses (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "OnlineSeatBook-0.0.1-SNAPSHOT.jar"]
