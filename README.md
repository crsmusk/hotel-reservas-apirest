# README para Hotel Reservas API REST

Basándome en el análisis del proyecto, aquí está el README completo:

---

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

## 📊 Análisis de Código

Ejecutar análisis de SonarQube:
```bash
./mvnw sonar:sonar
``` [12](#0-11) 

## 🐳 Docker

El proyecto incluye configuración Docker Compose con:
- Servicio de aplicación (puerto 8080)
- Base de datos MySQL 8.0 (puerto 3307)
- Volúmenes persistentes para datos [13](#0-12) 

## 📝 Notas

- La aplicación se ejecuta por defecto en el puerto 8080
- La base de datos MySQL se expone en el puerto 3307 para evitar conflictos
- Se requiere configurar las variables de entorno para la conexión a la base de datos
- El proyecto incluye Spring Security, por lo que se necesitará autenticación para acceder a los endpoints

---

## Notes

Este README está basado completamente en el análisis del código fuente del proyecto. Las secciones incluyen toda la información relevante sobre:
- Configuración del proyecto desde `pom.xml`
- Endpoints documentados desde los controladores
- Configuración de Docker desde `docker-compose.yml`
- Estructura del proyecto basada en los directorios encontrados

El README original existente estaba incompleto, así que he creado una versión más completa y profesional que incluye instalación, configuración, documentación de API, testing y deployment.

### Citations

**File:** README.md (L1-1)
```markdown
Este sistema esta hecho pa la gestión hotelera y el manejo de reservaciones. Permite a el hotel administrar su inventario de habitaciones, gestionar las reservas de los clientes y realizar un seguimiento  de las reservaciones.
```

**File:** README.md (L4-17)
```markdown
Gestión de Habitaciones

Seguimiento completo del inventario de habitaciones
Múltiples tipos y categorías de habitaciones
Monitoreo del estado de habitaciones (disponible/ocupada)
Seguimiento de características de habitaciones:

Tamaño (metros cuadrados)
Capacidad
Características de accesibilidad
Precio por noche
Preferencias de habitación
Tipo de habitación

```

**File:** README.md (L20-33)
```markdown
Sistema de Reservaciones

Verificación de disponibilidad en tiempo real
Creación y gestión de reservaciones
Sistema de reservas basado en fechas
Capacidad de cambio de habitación
Soporte para reservas múltiples
Búsqueda avanzada de reservas por:

Fecha de entrada
Fecha de salida
Disponibilidad
Tipo de habitación

```

**File:** README.md (L34-40)
```markdown
Gestión de Usuarios

Gestión de perfiles de clientes
Administración de empleados
Control de acceso basado en roles
Sistema de permisos

```

**File:** pom.xml (L7-8)
```text
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.4</version>
```

**File:** pom.xml (L30-30)
```text
		<java.version>21</java.version>
```

**File:** pom.xml (L31-32)
```text
		<sonar.projectKey>SpringApp</sonar.projectKey>
		<sonar.host.url>http://localhost:9000</sonar.host.url>
```

**File:** pom.xml (L42-68)
```text
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

**File:** pom.xml (L73-97)
```text
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.10</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>post-unit-test</id>
                        <phase>test</phase>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                </executions>
```

**File:** docker-compose.yml (L1-28)
```yaml
version: '3.8'
services:
  reservas:
    image: reservas
    ports:
      - 8080:80 
    environment:
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
      SPRING_DATASOURCE_DB: ${SPRING_DATASOURCE_DB}
    volumes:
      - ./src:/app/src
    networks:
      - default
   
  db:
    image: mysql:8.0
    environment:
     MYSQL_DATABASE: apirest
     MYSQL_ROOT_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
    ports:
      - 3307:3306
    volumes:
      - data_db:/var/lib/mysql
    networks:
      - default
      
```

**File:** src/main/java/com/hotel/reservahabitaciones/Controller/HabitacionController.java (L14-92)
```java
@RequestMapping("/hotel/habitaciones")
public class HabitacionController {


    private  HabitacionServiceImpl habitacionService;
    @Autowired
    public void setHabitacionService(HabitacionServiceImpl habitacionService){
        this.habitacionService=habitacionService;
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDTO>>getAll(){
        return new ResponseEntity<>(habitacionService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(habitacionService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void>save(@RequestBody HabitacionDTO habitacionDTO){
        habitacionService.save(habitacionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDTO>update(@PathVariable Long id,@RequestBody HabitacionDTO habitacionDTO){
        return new ResponseEntity<>(habitacionService.update(id,habitacionDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        habitacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/buscar-habitacion-por-tipo/{tipo}")
    public ResponseEntity<List<HabitacionDTO>>getByroomType(@PathVariable String tipo){
        return new ResponseEntity<>(habitacionService.getByTypeRoom(tipo),HttpStatus.OK);
    }


    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<HabitacionDTO>>getRommroomAvailable(){
        return new ResponseEntity<>(habitacionService.getByRoomAvailable(),HttpStatus.OK);
    }

    @GetMapping("/habitaciones-ocupadas")
    public ResponseEntity<List<HabitacionDTO>>getRoomOccupied(){
        return new ResponseEntity<>(habitacionService.getByRoomUnaVailable(),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-Preferencia/{preferencia}")
    public ResponseEntity<List<HabitacionDTO>>getByRoomPreference(@PathVariable String preferencia){
        return new ResponseEntity<>(habitacionService.getByPreference(preferencia),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-capacidad/{capacidad}")
    public ResponseEntity<List<HabitacionDTO>>getByRoomCapacity(@PathVariable int capacidad){
        return new ResponseEntity<>(habitacionService.getByCapacity(capacidad),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-tamaño/{tamano}")
    public ResponseEntity<List<HabitacionDTO>>getBySizeRoom(@PathVariable int tamano){
          return new ResponseEntity<>(habitacionService.getBySize(tamano),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-precio-mayorQue/{precio}")
    public ResponseEntity<List<HabitacionDTO>>getByPriceGreaterThan(@PathVariable BigDecimal precio){
        return new ResponseEntity<>(habitacionService.getByPriceGreaterThan(precio),HttpStatus.OK);
    }


    @GetMapping("/buscar-por-precio-menorQue/{precio}")
    public ResponseEntity<List<HabitacionDTO>>getByPriceLessThan(@PathVariable BigDecimal precio){
        return new ResponseEntity<>(habitacionService.getByPriceLessThan(precio),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-accesibilidad/{accesibilidad}")
    public ResponseEntity<List<HabitacionDTO>>getByaccessibility(@PathVariable String accesibilidad){
        return new ResponseEntity<>(habitacionService.getByAccesibility(accesibilidad),HttpStatus.OK);
    }
}
```

**File:** src/main/java/com/hotel/reservahabitaciones/Controller/ReservacionController.java (L14-75)
```java
@RequestMapping("/hotel/reservacion")
public class ReservacionController {


    private ReservacionServiceImpl reservacionService;
    @Autowired
    public void setReservacionService(ReservacionServiceImpl reservacionService){
        this.reservacionService=reservacionService;
    }

    @GetMapping
    public ResponseEntity<List<ReservacionDTO>>getAll(){
        return new ResponseEntity<>(reservacionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(reservacionService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-entradas-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByInputBeforeThan(@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.getByInputBeforeThan(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-entradas-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>> getByInPutAfterThan(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.getByInPutAfterThan(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByOutPutAfterThan(@PathVariable LocalDate fecha){
      return new ResponseEntity<>(reservacionService.getByOutPutAfterThan(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByOutPutBeforeThan(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.getByOutPutBeforeThan(fecha),HttpStatus.OK);
    }

    @PutMapping("/Cambiar-habitacion/{id}/{idHabitacionActual}/{idNuevaHabitacion}/{salida}")
    public ResponseEntity<ReservacionDTO>changeRoom(@PathVariable Long id,
                                                    @PathVariable Long idHabitacionActual,
                                                    @PathVariable Long idNuevaHabitacion,
                                                    @PathVariable LocalDate salida){
        return new ResponseEntity<>(reservacionService.changeRoom(id,idHabitacionActual,idNuevaHabitacion,salida),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void>save(@RequestBody ReservacionDTO reservacionDTO){
        reservacionService.save(reservacionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-salida/{fecha}")
    public ResponseEntity<ReservacionDTO>changeOutPut(@PathVariable Long id,@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.updateOutPut(id,fecha),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        reservacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
```
