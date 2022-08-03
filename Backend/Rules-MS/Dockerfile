FROM openjdk:8u131-jdk-alpine
EXPOSE 8090
COPY ./target/rules-service.jar rules-service.jar 
ENTRYPOINT ["java","-jar","rules-service.jar"]