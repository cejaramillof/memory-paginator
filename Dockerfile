FROM openjdk:12-alpine
WORKDIR /opt/src/paginator/
COPY target/paginator-*.war /publish.war

# run application with this command line
CMD ["java", "-jar",  "/publish.war"]