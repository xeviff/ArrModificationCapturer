FROM maven:alpine as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn clean package

FROM openjdk:8-jdk-alpine
ENV TZ="Europe/Madrid"
COPY --from=build /usr/app/target/ArrModificationCapturer-1.0-jar-with-dependencies.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar