FROM openjdk:21
EXPOSE 8080:8080
RUN mkdir /app
COPY --chown=node:node ./package*.json ./
COPY ./build/libs/*-all.jar /app/ktor-docker-sample.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]