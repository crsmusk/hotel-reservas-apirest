

# Hotel Reservas API REST

API REST de gestión hotelera para el control de habitaciones, reservas, clientes, empleados y permisos.

## Badges
No hay badges de CI/CD o licencia verificados en el repositorio actual.

## Tabla de contenidos
- [Descripción general](#descripción-general)
- [Tecnologías y stack](#tecnologías-y-stack)
- [Requisitos previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [Tests](#tests)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Documentación de API](#documentación-de-api)
- [Decisiones arquitectónicas relevantes](#decisiones-arquitectónicas-relevantes)
- [Cómo contribuir](#cómo-contribuir)
- [Licencia](#licencia)

## Descripción general

Hotel Reservas API REST es una aplicación backend construida con Spring Boot 3.3.4 para administrar el inventario de habitaciones, las reservaciones de clientes y el control de roles/permisos en un hotel.

El proyecto expone endpoints REST para crear, consultar, actualizar y eliminar recursos clave como habitaciones, reservas, clientes, empleados, roles, permisos y usuarios.

## Tecnologías y stack

- Java 21
- Spring Boot 3.3.4
- Spring Data JPA
- Spring Web
- Spring Security
- Spring Validation
- MySQL 8.0 (runtime)
- H2 (pruebas)
- Lombok
- Maven (wrapper incluido)
- JUnit 5
- JaCoCo
- SonarQube Maven Plugin
- Docker Compose

## Requisitos previos

- JDK 21
- Maven 3.x o uso del wrapper `./mvnw`
- MySQL 8.0 para ejecución local con base de datos real
- Docker y Docker Compose si se ejecuta en contenedor

## Instalación

1. Clonar el repositorio:

```bash
git clone <repository-url>
cd hotel-reservas-apirest
```

> Reemplaza `<repository-url>` con la URL real del repositorio.

2. Compilar el proyecto con el wrapper de Maven:

```bash
./mvnw clean install
```

En Windows:

```powershell
.\mvnw.cmd clean install
```

### Con Docker Compose

1. Crear una copia de `.env.template` llamada `.env`.
2. Ajustar las variables de entorno en `.env`.
3. Levantar el servicio:

```bash
docker-compose up -d
```

## Configuración

La aplicación importa variables de entorno desde un archivo `.env` opcional mediante `spring.config.import=optional:file:.env[.properties]`.

Variables principales:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/apirest?useSSL=false&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=<usuario>
SPRING_DATASOURCE_PASSWORD=<contraseña>
SPRING_DATASOURCE_DB=apirest
```

El archivo `src/main/resources/application.properties` controla la configuración de JPA y el origen de datos:

- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.open-in-view=false`
- `spring.datasource.url=${SPRING_DATASOURCE_URL}`
- `spring.datasource.username=${SPRING_DATASOURCE_USERNAME}`
- `spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}`
- `spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect`

## Ejecución

Ejecutar localmente:

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Con Docker Compose:

```bash
docker-compose up -d
```

## Tests

Ejecutar la suite de pruebas unitarias e de integración:

```bash
./mvnw test
```

Generar el reporte de cobertura con JaCoCo:

```bash
./mvnw verify
```

El reporte de cobertura se escribe en:

```text
target/site/jacoco/index.html
```

## Estructura del proyecto

```
src/main/java/com/hotel/reservahabitaciones/
├── Controller/          # Controladores REST
├── Exception/           # Manejo de excepciones y handler global
├── Mapper/              # Mapstruct / mapeo entre DTOs y entidades
├── Model/
│   ├── DTOs/            # DTOs de entrada y salida
│   └── Entities/        # Entidades JPA
├── Repository/          # Repositorios Spring Data JPA
├── Securiy/             # Configuración de seguridad
└── Service/
    ├── Impl/            # Implementaciones de servicios
    └── Interface/       # Interfaces de servicio

src/main/resources/
├── application.properties
├── .env.template        # Variables de entorno de ejemplo

docker-compose.yml
pom.xml
README.md
docs/ARCHITECTURE.md   # Documentación de arquitectura interna
```

## Documentación de API

No se detectó configuración de Swagger/OpenAPI en el código fuente actual. La documentación de API se debe complementar con una definición OpenAPI si se desea soporte de documentación autoservicio.

### Endpoints principales

- `/hotel/habitaciones` — CRUD y búsquedas de habitaciones
- `/hotel/reservacion` — CRUD, búsqueda por fechas, cambio de habitación y actualización de salida
- `/hotel/clientes` — CRUD y búsqueda de clientes
- `/hotel/empleados` — CRUD y búsqueda de empleados
- `/hotel/roles` — CRUD y gestión de permisos de roles
- `/hotel/permisos` — CRUD y búsqueda de permisos
- `/hotel/usuarios` — CRUD de usuarios y gestión de roles/contraseñas

## Decisiones arquitectónicas relevantes

- Arquitectura en capas: `Controller` → `Service` → `Repository` → `Model`.
- La entidad `Reservacion` gestiona habitaciones activas y habitaciones anteriores mediante relaciones JPA y consultas específicas.
- La consulta JPQL `findOverlappingReservation` evita dependencias inversas complejas en la relación entre `Reservacion` y `Habitacion`.
- Se emplea `@Lock(LockModeType.PESSIMISTIC_WRITE)` en el repositorio para garantizar consistencia en la reserva de habitaciones.
- `spring.jpa.open-in-view` está deshabilitado, lo que favorece la detección temprana de problemas de carga perezosa.

Más detalle en `docs/ARCHITECTURE.md`.

## Cómo contribuir

1. Crear un branch con un nombre descriptivo.
2. Ejecutar pruebas con `./mvnw test` antes de enviar cambios.
3. Abrir un pull request contra la rama principal del repositorio.

[PENDIENTE: convención de ramas y convención de commits del equipo]

## Licencia

[PENDIENTE: licencia no especificada en el repositorio actual]

