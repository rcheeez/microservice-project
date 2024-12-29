FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests=true

FROM openjdk:17-jdk-slim

COPY --from=builder /app/target/ProductService-0.0.1-SNAPSHOT.jar product-service.jar

EXPOSE 8000

CMD [ "java", "-jar", "product-service.jar" ]