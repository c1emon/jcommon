FROM gradle:jdk17 AS builder
COPY ./ /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=builder /home/gradle/source/build/libs/app.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]