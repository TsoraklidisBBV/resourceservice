FROM openjdk:17

# copy the source tree and the pom.xml to our new container
COPY . /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","resourceservice.jar"]
