FROM ubuntu:latest
LABEL authors="vanmetj"
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java", "-jar", "/demo.jar", "top", "-b"]
