FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY target/*.jar user-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/user-service.jar"]