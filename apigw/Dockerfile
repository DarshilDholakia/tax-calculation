FROM openjdk:11

ARG APP_NAME="apigw"
ARG JAR_FILE="/build/libs/${APP_NAME}.jar"

COPY ${JAR_FILE} ApiGW.jar
ENTRYPOINT ["java","-jar", "ApiGW.jar"]