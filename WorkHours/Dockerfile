# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build output
COPY target/WorkHours-0.0.1-SNAPSHOT.jar app.jar

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
