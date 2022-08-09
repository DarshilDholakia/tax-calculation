FROM openjdk:11

ARG APP_NAME="calculation"
ARG JAR_FILE="/build/libs/${APP_NAME}.jar"

COPY ${JAR_FILE} Calculation.jar
ENTRYPOINT ["java","-jar", "Calculation.jar"]