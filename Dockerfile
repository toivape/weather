FROM openjdk:8-jdk

# VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories for Tomcat by default.
VOLUME /tmp

EXPOSE 8080

# Not very generic, so don't change application version :)
ADD build/libs/weather-1.0.0.jar weather.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/weather.jar"]