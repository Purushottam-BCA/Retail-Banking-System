FROM openjdk:8
EXPOSE 8087
ADD ./target/transaction-ms.jar transaction-ms.jar
ENTRYPOINT ["java","-jar","transaction-ms.jar"]