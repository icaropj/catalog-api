FROM openjdk:8-jdk-alpine
MAINTAINER br.com.fiap
COPY target/catalog-api-1.0.jar catalog-api-1.0.jar
ENTRYPOINT ["java","-jar","/catalog-api-1.0.jar"]