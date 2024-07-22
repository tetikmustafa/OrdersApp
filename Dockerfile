FROM openjdk:24-jdk

ARG JAR_FILE=target/OrdersApp.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/app.jar"]