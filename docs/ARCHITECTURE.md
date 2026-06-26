# Arquitectura del proyecto Hotel Reservas API REST

## Visión general

Este proyecto implementa una API REST para la gestión de habitaciones y reservaciones de un hotel. Se construye sobre Spring Boot 3.3.4 y emplea una arquitectura en capas con JPA para persistencia y Spring Security para autorización.

## Capas principales

- `Controller` — exposición de rutas HTTP y entrada/salida de datos.
- `Service` — lógica de negocio y validaciones.
- `Repository` — acceso a datos con Spring Data JPA.
- `Model` — entidades JPA y DTOs.
- `Mapper` — conversión entre DTOs de entrada/salida y entidades.
- `Security` — configuración de autenticación/autorización.
- `Exception` — manejadores globales y excepciones personalizadas.

## Flujo de datos típico

1. El cliente realiza una petición HTTP al controlador.
2. El controlador valida la entrada y delega al servicio correspondiente.
3. El servicio aplica reglas de negocio, validaciones y transacciones.
4. El repositorio ejecuta consultas JPA contra la base de datos.
5. El servicio transforma el resultado a DTO y el controlador devuelve la respuesta HTTP.

## Modelo de datos clave

- `Reservacion` — representa una reserva de habitación y contiene referencias a `Cliente` y a colecciones de `Habitacion` activas y anteriores.
- `Habitacion` — representa una habitación del hotel y mantiene su estado de disponibilidad.
- `Cliente`, `Empleado`, `Usuario`, `Rol` y `Permiso` — representan los actores del sistema y su seguridad.

## Decisiones relevantes

- Se prioriza una relación unidireccional entre `Reservacion` y `Habitacion` para reducir el acoplamiento y simplificar la persistencia.
- La consulta JPQL `findOverlappingReservation` en `ReservaRepository` sirve para detectar reservas que se solapan en una fecha concreta.
- El uso de `@Lock(LockModeType.PESSIMISTIC_WRITE)` en operaciones críticas evita condiciones de carrera al reservar habitaciones.
- La propiedad `spring.jpa.hibernate.ddl-auto=update` está configurada en `application.properties` para desarrollo. En producción se recomienda usar scripts SQL controlados.

## Recomendaciones de documentación interna

- Agregar Javadoc en:
  - interfaces de servicio en `Service/Interface`
  - repositorios clave como `ReservaRepository`
  - servicios con lógica de reserva como `ReservacionServiceImpl`
  - controladores REST expuestos públicamente.
- Añadir una definición OpenAPI/Swagger para documentar automáticamente los endpoints existentes.
- Registrar la licencia del proyecto en un archivo `LICENSE` y en el `pom.xml`.
- Documentar la convención de branching y commits en un archivo `CONTRIBUTING.md`.
