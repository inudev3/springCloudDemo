FROM openjdk:18.0.2.1-jdk
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/config-service-1.0.jar config-service.jar
ENTRYPOINT ["java", "-jar", "config-service.jar"]
