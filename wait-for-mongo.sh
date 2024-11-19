#!/bin/bash

# Espera hasta que MongoDB esté disponible
until nc -z -v -w30 mongodb 27017; do
  echo "Esperando a MongoDB..."
  sleep 5
done

echo "MongoDB está disponible. Iniciando la aplicación Spring Boot..."

# Ejecutar la aplicación Spring Boot
exec java -jar /app/app.jar
