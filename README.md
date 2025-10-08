

# 🏨 Hotel Reservas API REST

Sistema de gestión hotelera y manejo de reservaciones desarrollado con Spring Boot. Permite al hotel administrar su inventario de habitaciones, gestionar las reservas de los clientes y realizar un seguimiento de las reservaciones. [1](#0-0) 

## 📋 Descripción

Esta API REST proporciona un sistema completo para la administración de hoteles que incluye:

### 🛏️ Gestión de Habitaciones
- Seguimiento completo del inventario de habitaciones
- Múltiples tipos y categorías de habitaciones
- Monitoreo del estado de habitaciones (disponible/ocupada)
- Búsqueda avanzada por: tipo, capacidad, tamaño, precio, accesibilidad y preferencias [2](#0-1) 

### 📅 Sistema de Reservaciones
- Verificación de disponibilidad en tiempo real
- Creación y gestión de reservaciones
- Sistema de reservas basado en fechas
- Capacidad de cambio de habitación
- Búsqueda de reservas por fecha de entrada y salida [3](#0-2) 

### 👥 Gestión de Usuarios
- Gestión de perfiles de clientes y empleados
- Control de acceso basado en roles
- Sistema de permisos [4](#0-3) 

## 🛠️ Tecnologías Utilizadas

- **Java**: 21
- **Spring Boot**: 3.3.4
- **Spring Data JPA**: Persistencia de datos
- **Spring Security**: Autenticación y autorización
- **MySQL**: 8.0
- **Lombok**: Reducción de código boilerplate
- **Maven**: Gestión de dependencias
- **JUnit 5**: Testing
- **JaCoCo**: Cobertura de código
- **SonarQube**: Análisis de calidad de código
- **Docker Compose**: Containerización [5](#0-4) [6](#0-5) [7](#0-6) 

## 📦 Requisitos Previos

- Java 21
- Maven 3.6+
- Docker y Docker Compose (opcional)
- MySQL 8.0 (si no usas Docker)

## 🚀 Instalación

### Con Docker Compose

1. Clona el repositorio:
```bash
git clone https://github.com/crsmusk/hotel-reservas-apirest.git
cd hotel-reservas-apirest
```

2. Configura las variables de entorno en un archivo `.env`:
```env
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/apirest
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=tu_password
SPRING_DATASOURCE_DB=apirest
```

3. Ejecuta con Docker Compose:
```bash
docker-compose up -d
``` [8](#0-7) 

### Sin Docker

1. Configura tu base de datos MySQL

2. Ejecuta con Maven:
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## 📡 Endpoints de la API

### Habitaciones (`/hotel/habitaciones`)

- `GET /hotel/habitaciones` - Listar todas las habitaciones
- `GET /hotel/habitaciones/{id}` - Obtener habitación por ID
- `POST /hotel/habitaciones` - Crear nueva habitación
- `PUT /hotel/habitaciones/{id}` - Actualizar habitación
- `DELETE /hotel/habitaciones/{id}` - Eliminar habitación
- `GET /hotel/habitaciones/buscar-habitacion-por-tipo/{tipo}` - Buscar por tipo
- `GET /hotel/habitaciones/habitaciones-disponibles` - Habitaciones disponibles
- `GET /hotel/habitaciones/habitaciones-ocupadas` - Habitaciones ocupadas
- `GET /hotel/habitaciones/buscar-por-Preferencia/{preferencia}` - Buscar por preferencia
- `GET /hotel/habitaciones/buscar-por-capacidad/{capacidad}` - Buscar por capacidad
- `GET /hotel/habitaciones/buscar-por-tamaño/{tamano}` - Buscar por tamaño
- `GET /hotel/habitaciones/buscar-por-precio-mayorQue/{precio}` - Precio mayor que
- `GET /hotel/habitaciones/buscar-por-precio-menorQue/{precio}` - Precio menor que
- `GET /hotel/habitaciones/buscar-por-accesibilidad/{accesibilidad}` - Buscar por accesibilidad [9](#0-8) 

### Reservaciones (`/hotel/reservacion`)

- `GET /hotel/reservacion` - Listar todas las reservaciones
- `GET /hotel/reservacion/{id}` - Obtener reservación por ID
- `POST /hotel/reservacion` - Crear nueva reservación
- `DELETE /hotel/reservacion/{id}` - Eliminar reservación
- `GET /hotel/reservacion/buscar-entradas-antes-de/{fecha}` - Entradas antes de fecha
- `GET /hotel/reservacion/buscar-entradas-despues-de/{fecha}` - Entradas después de fecha
- `GET /hotel/reservacion/buscar-salida-despues-de/{fecha}` - Salidas después de fecha
- `GET /hotel/reservacion/buscar-salida-antes-de/{fecha}` - Salidas antes de fecha
- `PUT /hotel/reservacion/Cambiar-habitacion/{id}/{idHabitacionActual}/{idNuevaHabitacion}/{salida}` - Cambiar habitación
- `PUT /hotel/reservacion/actualizar-salida/{fecha}` - Actualizar fecha de salida [10](#0-9) 

## 🏗️ Estructura del Proyecto

```
src/main/java/com/hotel/reservahabitaciones/
├── Controller/          # Controladores REST
│   ├── ClienteController.java
│   ├── EmpleadoController.java
│   ├── HabitacionController.java
│   ├── PermisoController.java
│   ├── ReservacionController.java
│   ├── RolController.java
│   └── UsuarioController.java
├── Model/              # Modelos y DTOs
│   ├── DTOs/
│   └── Entities/
├── Repository/         # Repositorios JPA
├── Service/           # Lógica de negocio
├── Mapper/            # Mapeo de entidades a DTOs
├── Security/          # Configuración de seguridad
└── Exception/         # Manejo de excepciones
```

## 🧪 Testing

Ejecutar tests:
```bash
./mvnw test
```

Generar reporte de cobertura con JaCoCo:
```bash
./mvnw verify
```

El reporte se genera en `target/site/jacoco/index.html` [11](#0-10) 

