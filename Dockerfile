FROM maven:3.8.4-openjdk-17 as builder

COPY ./ /usr/src/myapp

WORKDIR /usr/src/myapp

# Build project and package it
RUN mvn clean package

FROM openjdk:17

COPY --from=builder /usr/src/myapp/target/clustered-data-warehouse-1.0-SNAPSHOT.jar /usr/app/clustered-data-warehouse-1.0-SNAPSHOT.jar

WORKDIR /usr/app

CMD ["java", "-jar", "clustered-data-warehouse-1.0-SNAPSHOT.jar"]
