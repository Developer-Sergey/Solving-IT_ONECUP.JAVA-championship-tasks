FROM maven:3.8.6 AS build
COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17

RUN mkdir /app
WORKDIR /app

COPY --from=build /home/app/target/Checker-1.jar /app/Checker-1.jar
COPY src /app/src

EXPOSE 9081

ENTRYPOINT ["java", "-jar", "/app/Checker-1.jar"]
