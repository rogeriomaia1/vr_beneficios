FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar
WORKDIR /opt/beneficios

COPY ${JAR_FILE} vr_beneficios.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java -jar vr_beneficios.jar 
