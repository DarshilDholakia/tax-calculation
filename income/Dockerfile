FROM openjdk:11

ARG APP_NAME="income"
ARG JAR_FILE="/build/libs/${APP_NAME}.jar"

COPY ${JAR_FILE} Income.jar
ENTRYPOINT ["java","-jar", "Income.jar"]