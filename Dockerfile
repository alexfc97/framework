FROM openjdk:8
ADD target/webapplication.jar webapplication.jar
EXPOSE EXPOSE 8081
ENTRYPOINT ["java","-jar","webapplication.jar"]
