FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests=true

FROM openjdk:17-jdk-slim

COPY --from=builder /app/target/CouponService-0.0.1-SNAPSHOT.jar coupon-service.jar

EXPOSE 8001

CMD [ "java", "-jar", "coupon-service.jar" ]