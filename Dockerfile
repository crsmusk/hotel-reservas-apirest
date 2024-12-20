FROM eclipse-temurin:21.0.4_7-jdk as build 

WORKDIR /root

COPY pom.xml .
COPY .mvn ./.mvn
COPY mvnw .

RUN sed -i 's/\r$//' mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean install -DskipTests


#FROM gcr.io/distroless/java21-debian11

FROM  eclipse-temurin:21.0.4_7-jdk

WORKDIR /root

COPY --from=build /root/target/*.jar app.jar

RUN groupadd -r usuarios && useradd -r -g usuarios pepe
RUN mkdir -p /root/src && chown pepe:usuarios /root/src
USER pepe


ENTRYPOINT ["java", "-jar", "/root/target/reservahabitaciones-0.0.1-SNAPSHOT.jar"]