FROM openjdk:8
EXPOSE 8080
ADD target/shopme-java-backend.jar shopme-java-backend.jar
ENTRYPOINT ["java", "-jar", "/shopme-java-backend.jar"]