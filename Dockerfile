FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -Dmaven.test.skip=true
EXPOSE 10000
CMD ["java", "-jar", "target/study-agent-0.0.1-SNAPSHOT.jar"]