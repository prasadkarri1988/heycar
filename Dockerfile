FROM openjdk:8
ADD target/heycar.jar app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar