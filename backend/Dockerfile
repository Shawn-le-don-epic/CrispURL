# --- Stage 1: Build the application ---
# Use a Maven image that includes JDK 21 to build the project
FROM maven:3.8.5-openjdk-21 AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven project file first to leverage Docker layer caching
COPY pom.xml .

# Copy the rest of the application source code
COPY src ./src

# Build the project and create the executable JAR file
# This runs 'mvn clean package' and skips running tests during the build
RUN mvn clean package -DskipTests


# --- Stage 2: Create the final, lightweight image ---
# Use a slim Java 21 runtime image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file that was created in the 'builder' stage
COPY --from=builder /app/target/crispurl-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application will run on
EXPOSE 8081

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]

