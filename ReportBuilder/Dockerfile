FROM maven:3.8.6 AS build
COPY src /home/app/src
COPY pom.xml /home/app 
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17

RUN mkdir /opt/results

RUN mkdir /app
WORKDIR /app
COPY --from=build /home/app/target/ReportBuilder-1.jar /app/ReportBuilder-1.jar
EXPOSE 9081

ENTRYPOINT ["java", "-jar", "/app/ReportBuilder-1.jar"]
