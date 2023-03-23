FROM adoptopenjdk/openjdk11:latest
COPY build/libs/*-SNAPSHOT.jar app.jar
RUN /bin/sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
ENV TZ="Asia/Ho_Chi_Minh"