FROM openjdk:11
ARG JAR_FILE=target/*.jar
ARG WORKDIR=/usr/click-counter/
COPY ${JAR_FILE} ${WORKDIR}app.jar
WORKDIR $WORKDIR
EXPOSE 8080
ENTRYPOINT ["java","-jar", "app.jar"]
