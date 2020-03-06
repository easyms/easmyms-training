FROM openjdk:8-jre-alpine

LABEL maintainer="elhoussine.bouhdadi@gmail.com"

ENV PORT="8093"
VOLUME /tmp

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} service.jar

EXPOSE $PORT

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom \
				-jar /service.jar \ 
				--server.port=${PORT}