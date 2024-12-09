FROM eclipse-temurin:21.0.4_7-jdk


EXPOSE 8080

WORKDIR /root

COPY pom.xml .
COPY .mvn ./.mvn
COPY mvnw .
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean install -DskipTests

ENTRYPOINT [ "java","-jar","/root/target/reservahabitaciones-0.0.1-SNAPSHOT.jar" ]
