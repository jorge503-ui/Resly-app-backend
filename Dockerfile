FROM openjdk:8-jdk-alpine

VOLUME tmp
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
COPY /firebase_config.json /FBConfig/firebase_config.json

ENV GOOGLE_APPLICATION_CREDENTIALS=/FBConfig/firebase_config.json

RUN mkdir /tmp/resly-log

ENV log4j_appender_file_File=/tmp/resly-log

COPY target/*.jar app.jar

ENV JAVA_OPTS=''
#ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
ENTRYPOINT java $JAVA_OPTS -jar /app.jar