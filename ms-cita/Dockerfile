FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
COPY ms-cita/pom.xml ms-cita/pom.xml

RUN mvn -f ms-cita/pom.xml dependency:go-offline

COPY ms-cita/src ms-cita/src

RUN mvn -f ms-cita/pom.xml clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/ms-cita/target/*.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]