FROM openjdk:11

ARG APP_NAME="config-server"
ARG APP_VERSION="0.0.1-SNAPSHOT"
ARG JAR_FILE="/build/libs/${APP_NAME}-all-${APP_VERSION}.jar"

COPY ${JAR_FILE} ConfigServer.jar
ENTRYPOINT ["java","-jar", "ConfigServer.jar"]