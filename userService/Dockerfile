FROM openjdk:18.0.2.1-jdk
VOLUME /tmp
COPY build/libs/user-service-1.0.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]
