FROM openjdk:18.0.2.1-jdk
VOLUME /tmp
COPY build/libs/order-service-1.0.jar order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]
