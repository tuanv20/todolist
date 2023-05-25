FROM openjdk:18
WORKDIR /app
COPY springboottodolist-0.0.1-SNAPSHOT.jar /app
EXPOSE 8081
CMD ["java", "-jar", "springboottodolist-0.0.1-SNAPSHOT.jar"]