FROM openjdk:8-jre-alpine

#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

COPY target/*.jar app.jar

ENV JAVA_OPTS=''
ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar