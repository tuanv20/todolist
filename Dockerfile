#Pulling openjdk as the base image for the container
FROM openjdk:18
#Changing the working directory within the container
WORKDIR /app
#Copying .jar executable for Springboot App from target folder
COPY target/springboottodolist-0.0.1-SNAPSHOT.jar /app
#Exposing port 8081
EXPOSE 8081
#Running the .jar executable from within the container
CMD ["java", "-jar", "springboottodolist-0.0.1-SNAPSHOT.jar"]