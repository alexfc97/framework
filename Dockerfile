FROM openjdk:8
ADD target/WebApplication.jar WebApplication.jar
EXPOSE EXPOSE 8081
ENTRYPOINT ["java","-jar","webapplication.jar"]
