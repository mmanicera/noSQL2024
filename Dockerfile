# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/historialmedico.jar app.jar

# Expone el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
