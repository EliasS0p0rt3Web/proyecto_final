FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
COPY ms-medico/pom.xml ms-medico/pom.xml

RUN mvn -f ms-medico/pom.xml dependency:go-offline

COPY ms-medico/src ms-medico/src

RUN mvn -f ms-medico/pom.xml clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/ms-medico/target/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]