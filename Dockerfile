FROM openjdk:latest
COPY ./target/semGroup18-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semGroup18-0.2.0.0-jar-with-dependencies.jar"]