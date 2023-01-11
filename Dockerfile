FROM openjdk:11

# The port your service will listen on
EXPOSE 8080

# Copy the service JAR
COPY target/car-pooling-1.0.0-SNAPSHOT.jar /car-pooling.jar

# The command to run
CMD ["java", "-jar", "car-pooling.jar"]
