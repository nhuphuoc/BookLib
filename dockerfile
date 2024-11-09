FROM maven:3.8.7-openjdk-18-slim AS build
WORKDIR /app
COPY . .
USER root
RUN mvn install -Dmaven.test.skip=true && \
     mvn package -Dmaven.test.skip=true

FROM maven:3.8.7-openjdk-18-slim
WORKDIR /app
COPY --from=build /app/target/BookLibrary-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "./BookLibrary-0.0.1-SNAPSHOT.jar"]