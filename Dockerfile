FROM openjdk:8
EXPOSE 8084
ADD ./target/auth-ms.jar auth-ms.jar
ENTRYPOINT ["java","-jar","auth-ms.jar"]