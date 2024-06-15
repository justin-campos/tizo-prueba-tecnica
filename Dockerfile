FROM openjdk:17-alpine
COPY ./build/libs/tizoreto-0.0.1-SNAPSHOT.jar tizo.jar
CMD ["java", "-jar", "/tizo.jar"]