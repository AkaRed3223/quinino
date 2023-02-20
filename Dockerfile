FROM maven:3.8.3-openjdk-17-slim AS builder
WORKDIR /quinino
COPY . .

RUN mvn package

FROM maven:3.8.3-openjdk-17-slim AS runner
WORKDIR /quinino
EXPOSE 8080
COPY --from=builder /quinino/target/quinino-app-docker.jar .
CMD java -jar quinino-app-docker.jar