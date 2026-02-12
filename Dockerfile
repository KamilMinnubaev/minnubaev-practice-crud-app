FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java", "-Dserver.port=${PORT:-10000}", "-Dserver.address=0.0.0.0", "-jar", "app.jar"]