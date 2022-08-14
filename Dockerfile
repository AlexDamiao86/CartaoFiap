FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring 
USER spring:spring
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}","-jar","/app.jar"]