FROM maven:3.8.3-openjdk-17-slim AS builder
WORKDIR /quinino
EXPOSE 8080
COPY . .

RUN mvn package

FROM openjdk:17-jdk-slim AS runner
WORKDIR /quinino
EXPOSE 8080
COPY --from=builder /quinino/target/quinino-app-docker.jar .
CMD java -jar quinino-app-docker.jar