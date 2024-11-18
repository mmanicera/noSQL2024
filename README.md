# Historial Médico

Este proyecto es una aplicación para gestionar historiales médicos utilizando Java, Spring Boot y MongoDB.

## Tabla de Contenidos

- [Configuración Necesaria](#configuración-necesaria)
- [Intercambio de Datos](#intercambio-de-datos)
  - [Paciente](#paciente)
  - [Registro Médico](#registro-médico)
  - [Obtener Historial Médico](#obtener-historial-médico)
  - [Obtener Registros por Criterio](#obtener-registros-por-criterio)
- [Elección de la Base de Datos](#elección-de-la-base-de-datos)
  - [Cualidades de MongoDB](#cualidades-de-mongodb)
- [Diseño](#diseño)

## Configuración Necesaria

Para ejecutar la aplicación, necesitas:

- Un IDE (Eclipse)
- JDK 17 o superior
- Maven
- MongoDB

Una vez que tengas todo lo anterior, haz clic derecho en `HistorialMedicoApplication.java`, ve a `Run as` y selecciona `Java application`.

## Intercambio de Datos

Los datos se intercambian en formato JSON. A continuación, se presentan ejemplos de carga de Paciente y Registro médico, así como descripciones de los métodos GET para obtener tanto el historial médico como los registros bajo ciertos criterios.

### Paciente

- **Método:** POST
- **URL:** `http://localhost:8080/api/pacientes`
- **Request body:**
  ```json
  {
    "ci": "87654320",
    "nombre": "María",
    "apellido": "Gómez",
    "fechaNacimiento": "1992-11-25",
    "sexo": "Femenino"
  }

Registro Médico

    Método: POST
    URL: http://localhost:8080/api/registros?ci=CEDULA (Intercambiar CEDULA por un valor)
    Request body:

    json

    {
      "fecha": "2024-11-11T10:00:00",
      "tipo": "Examen",
      "diagnostico": "Hipertensión",
      "medico": "Dr. Carlos Valdo",
      "institucion": "Hospital Celeste",
      "descripcion": "Paciente presenta síntomas de dolor de cabeza y fatiga.",
      "medicacion": "Losartán 50mg"
    }

Obtener Historial Médico

    Método: GET
    URL: http://localhost:8080/api/historiales?ci=87654321&page=1&pageSize=5

Notas:

    ci es la cédula de identidad del paciente.
    page indica la página que queremos ver (la primera pág es la 1).
    pageSize indica el tamaño de cada página.

Obtener Registros por Criterio

    Método: GET
    URL: http://localhost:8080/api/registros/criterio?diagnostico=Hipertensión&size=5&page=0

Notas:

    page indica la página que queremos ver (la primera pág es la 1).
    pageSize indica el tamaño de cada página.

Puedes encontrar la colección de Postman compartida aquí.
Elección de la Base de Datos

Decidimos utilizar MongoDB porque almacena la información en JSONs, lo cual es intuitivo y fácil de aplicar dado que la comunicación de la aplicación es con este mismo método. La integración con MongoDB es sencilla y amigable.
Cualidades de MongoDB

    Modelo de Documentos Flexible
        Estructura adaptable: MongoDB permite almacenar los datos en documentos JSON/BSON, ideal para registros médicos con campos opcionales y diferentes estructuras.
        Evolución de esquema: Fácil actualización de la estructura de documentos sin migraciones complicadas.

    Alto Rendimiento y Escalabilidad
        Escalabilidad horizontal: Maneja grandes volúmenes de datos sin sacrificar rendimiento.
        Alta disponibilidad: A través de replicación y configuraciones de clusters distribuidos.

    Facilidad de Integración con Spring Boot
        Spring Data MongoDB: Simplifica el desarrollo con abstracciones de repositorio.
        Manejo de excepciones personalizado: Mejora la gestión de errores.

    Soporte para JSON nativo
        Intercambio de datos simplificado: Natural con servicios REST en formato JSON.

En conclusión, MongoDB fue una elección sólida para el proyecto de historial médico porque ofrece flexibilidad en la estructura de datos, rendimiento y escalabilidad, y se integra de manera efectiva con framework Spring Boot que facilita el desarrollo de aplicaciones RESTful.
Diseño

Optamos por un diseño modular y escalable, utilizando dos colecciones: pacientes y registrosMedicos. La relación entre pacientes y registros médicos se realiza mediante la clave ciPaciente, permitiendo flexibilidad para manejar grandes volúmenes de datos sin limitar el tamaño de un solo documento.

Este modelo es más flexible y escalable para un historial médico que puede crecer significativamente, permitiendo almacenar registros médicos en documentos separados.