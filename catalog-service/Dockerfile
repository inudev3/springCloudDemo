FROM openjdk:18.0.2.1-jdk
VOLUME /tmp
COPY build/libs/catalog-service-1.0.jar catalog-service.jar
ENTRYPOINT ["java", "-jar", "catalog-service.jar"]
