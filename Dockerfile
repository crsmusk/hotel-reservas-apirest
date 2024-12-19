FROM eclipse-temurin:21.0.4_7-jdk as build 

WORKDIR /root

COPY pom.xml .
COPY .mvn ./.mvn
COPY mvnw .

RUN sed -i 's/\r$//' mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean install -DskipTests


FROM eclipse-temurin:21.0.4_7-jdk 

WORKDIR /root

COPY --from=build /root/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]