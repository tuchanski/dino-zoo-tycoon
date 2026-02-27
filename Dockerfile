FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN apt-get update && apt-get install -y \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libx11-6 \
    && rm -rf /var/lib/apt/lists/*

COPY . .

RUN ls -lah

RUN mkdir out
RUN javac -cp "postgresql-42.7.4.jar" -d out $(find src -name "*.java")

CMD ["java", "-cp", "out:postgresql-42.7.4.jar", "application.Main"]