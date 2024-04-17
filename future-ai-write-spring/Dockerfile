FROM openjdk:17
LABEL authors="futureai_max"

COPY *.jar /app.jar
CMD ["--server.port=9000","--spring.profiles.active=docker"]

EXPOSE 9000

ENTRYPOINT ["java","-jar","/app.jar"]