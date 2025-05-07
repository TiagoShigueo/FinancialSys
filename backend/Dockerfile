# Etapa 1: Build do JAR com Maven
FROM maven:3.9-eclipse-temurin AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Container final com o JAR
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/backend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
