# Use an official OpenJDK runtime as a parent image
# We choose one that is slim and based on Java 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file from our local 'target' folder into the container
# The jar file contains our compiled application and all its dependencies
COPY target/crispurl-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on. Render will use this.
# This should match the port exposed in application.properties
EXPOSE 8081

# The command to run when the container starts
# This executes our Java application
ENTRYPOINT ["java", "-jar", "app.jar"]

