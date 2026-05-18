# Estágio de build
FROM docker.io/eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copia gradle wrapper e arquivos de build para cache de dependências
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts

RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew installDist --no-daemon

# Estágio de execução
FROM docker.io/eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/build/install/app /app

RUN chmod +x /app/bin/app

EXPOSE 9000

ENTRYPOINT ["/app/bin/app"]