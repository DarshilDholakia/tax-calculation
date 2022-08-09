FROM openjdk:11

ARG APP_NAME="person"
ARG JAR_FILE="/build/libs/${APP_NAME}.jar"

COPY ${JAR_FILE} Person.jar
ENTRYPOINT ["java","-jar", "Person.jar"]