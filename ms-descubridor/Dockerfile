FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
COPY ms-descubridor/pom.xml ms-descubridor/pom.xml

RUN mvn -f ms-descubridor/pom.xml dependency:go-offline

COPY ms-descubridor/src ms-descubridor/src

RUN mvn -f ms-descubridor/pom.xml clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/ms-descubridor/target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]