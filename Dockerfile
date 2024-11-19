# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el archivo JAR de la aplicación y el script de espera
COPY target/historialmedico.jar app.jar
COPY wait-for-mongo.sh /app/wait-for-mongo.sh

# Da permisos de ejecución al script
RUN apt-get update && apt-get install -y netcat && \
    chmod +x /app/wait-for-mongo.sh

# Expone el puerto en el que la aplicación escuchará
EXPOSE 8080

# Usamos el script para esperar a MongoDB y luego ejecutar la aplicación
ENTRYPOINT ["/app/wait-for-mongo.sh"]
