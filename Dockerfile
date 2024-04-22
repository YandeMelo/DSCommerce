FROM openjdk:23-jdk-slim
COPY target/*.jar dscommercePGAdmin.jar
ENTRYPOINT [ "java", "-jar", "dscommercePGAdmin.jar" ]