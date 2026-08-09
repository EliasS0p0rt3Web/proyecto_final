FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .

COPY ms-notificacion/pom.xml ms-notificacion/pom.xml

RUN mvn -f ms-notificacion/pom.xml dependency:go-offline

COPY ms-notificacion/src ms-notificacion/src

RUN mvn -f ms-notificacion/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/ms-notificacion/target/*.jar app.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "app.jar"]