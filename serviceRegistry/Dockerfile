# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /eureka-server

# Copy the Maven build output
COPY target/serviceRegistry-0.0.1-SNAPSHOT.jar eureka-server.jar

# Expose the Eureka server port
EXPOSE 8761

# Command to run the Eureka server
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
