FROM openjdk:17-jdk-slim-buster
COPY target/courier_tracking-0.0.1-SNAPSHOT.jar courier-tracking.jar
ENTRYPOINT ["java", "-jar", "/courier-tracking.jar"]