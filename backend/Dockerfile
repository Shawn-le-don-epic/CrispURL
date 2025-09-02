# Use an official OpenJDK runtime as a parent image
# We choose one that is slim and based on Java 21
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file from our local 'target' folder into the container
# The jar file contains our compiled application and all its dependencies
COPY target/crispurl-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on. Render will use this.
EXPOSE 8081

# The command to run when the container starts
# This executes our Java application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### **Step 2: Update `application.properties` for Render**

Render, like AWS, provides a port for your application to listen on. We just need to make a small tweak to the `application.properties` file to be compatible with Render's environment.

**Action:** Open `backend/src/main/resources/application.properties` and ensure the line is:

```properties
server.port=${PORT:8081}
