FROM openjdk:13-jdk-alpine3.10
EXPOSE 8080
ARG JAR_FILE=build/libs/linkconverter-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE}  link-converter-application.jar
ENTRYPOINT ["java","-jar","link-converter-application.jar"]
