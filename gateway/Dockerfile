FROM openjdk:18.0.2.1-jdk
VOLUME /tmp
COPY build/libs/gateway-1.0.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]
