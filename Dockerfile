FROM adoptopenjdk/openjdk11
RUN apt-get update && apt-get install -y maven
WORKDIR /docker-practice-on-carina-demo
COPY ./src /docker-practice-on-carina-demo/src
COPY ./pom.xml /docker-practice-on-carina-demo/pom.xml
ENV SUITE=api

ENTRYPOINT ["mvn", "clean", "test", "-Dsuite=${SUITE}"]