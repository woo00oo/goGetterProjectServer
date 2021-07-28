FROM adoptopenjdk/openjdk15
COPY build/libs/goGetterServer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]