FROM jelastic/maven:3.9.5-openjdk-21 AS build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn dependency:go-offline

COPY src/ ./src/

RUN mvn clean package -Dmaven.test.skip

FROM openjdk:21-jdk-slim

VOLUME /app

COPY --from=build /app/target/*.jar order-parser.jar

EXPOSE 8080

CMD ["java", "-jar", "order-parser.jar"]