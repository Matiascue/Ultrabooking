FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY target/*.jar flight-service.jar
EXPOSE 8082

ENTRYPOINT ["java","-jar","/app/flight-service.jar"]