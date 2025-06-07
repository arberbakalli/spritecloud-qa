# Use an official Maven image with JDK
FROM maven:3.9.4-eclipse-temurin-22

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the project and run tests
RUN mvn clean test

# Generate the Allure report
RUN mvn allure:report

# Expose a port for serving the Allure report (optional)
EXPOSE 8080

# Command to serve the Allure report
CMD ["mvn", "allure:serve"]