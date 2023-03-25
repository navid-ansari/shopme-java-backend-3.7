FROM openjdk:19.0.2
EXPOSE 8080
ADD target/shopme-java-backend.jar shopme-java-backend.jar
ENTRYPOINT ["java", "-jar", "/shopme-java-backend.jar"]