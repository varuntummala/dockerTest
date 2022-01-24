FROM openjdk:8
EXPOSE 8082
WORKDIR ./User
COPY target/userDetails-0.0.1-SNAPSHOT.jar userDetails-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","userDetails-0.0.1-SNAPSHOT.jar"]