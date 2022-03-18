FROM adoptopenjdk/openjdk11:alpine-slim
ADD target/spring-webflux-card-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.jar"]