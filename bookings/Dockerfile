FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY target/*.jar booking-service.jar
EXPOSE 8083

ENTRYPOINT ["java","-jar","/app/booking-service.jar"]