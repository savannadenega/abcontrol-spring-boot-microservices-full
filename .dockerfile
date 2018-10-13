FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/pfe-rating-api-*.jar /app.jar 
ENV PROFILE=""
ENTRYPOINT [ "sh", "-c", "java $PROFILE -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
