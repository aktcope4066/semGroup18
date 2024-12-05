FROM openjdk:latest
COPY ./target/semGroup18.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semGroup18.jar"]