FROM openjdk:8
EXPOSE 8080
AND target/shopme-java-backend.jar shopme-java-backend.jar
ENTRYPOINT ["java", "-jar", "/shopme-java-backend.jar"]