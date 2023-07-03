FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/deliveryproject.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


