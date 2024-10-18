# Use a imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho no container
COPY build/libs/chat-0.0.1-SNAPSHOT.jar /app/chat-0.0.1-SNAPSHOT.jar

# Define o comando de entrada padrão que será executado quando o container iniciar
ENTRYPOINT ["java","-jar","/app/chat-0.0.1-SNAPSHOT.jar"]
