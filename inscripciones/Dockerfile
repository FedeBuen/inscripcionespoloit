# Usa una imagen base con Maven y OpenJDK 17 para construir el proyecto
FROM maven:3.8.3-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias necesarias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia todo el contenido del proyecto y construye el JAR
COPY . .
RUN mvn clean package -DskipTests

# Usa una imagen más ligera de OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Copia el JAR generado desde el contenedor de construcción
COPY --from=build /app/target/inscripciones-0.0.1-SNAPSHOT.jar /app/inscripciones.jar

# Establece el directorio de trabajo en el nuevo contenedor
WORKDIR /app

# Expone el puerto 8080 para acceder a la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "inscripciones.jar"]
