---
# 🤖 AGENT.MD — Memoria Técnica del Proyecto
> Archivo privado. No commitear. Mantenido por el agente IA.
> Última actualización: 2026-06-15 | Total errores: 4

## 1. 📋 Contexto del Proyecto
API REST para un sistema de reserva de habitaciones de hotel. Provee endpoints para gestionar clientes, habitaciones, y reservaciones.

## 2. 🛠️ Stack Tecnológico  
- **Lenguaje:** Java 21
- **Framework:** Spring Boot 3.3.4
- **Base de Datos:** MySQL (H2 para tests locales)
- **Herramientas de Build:** Maven
- **Otros:** Lombok, Spring Security, Spring Data JPA, JUnit 5, JaCoCo, SonarQube

## 3. 🗂️ Estructura del Proyecto
Patrón MVC/Capas con los siguientes paquetes en `com.hotel.reservahabitaciones`:
- `Controller`: Endpoints REST
- `Service`: Lógica de negocio
- `Repository`: Interfaces de Spring Data JPA
- `Model`: Entidades JPA y DTOs
- `Mapper`: Mapeo entre Entidades y DTOs
- `Exception`: Manejo de errores globales
- `Securiy`: Configuración de Spring Security

## 4. 📐 Convenciones y Estándares
- Uso de DTOs para transferencia de datos y Mappers para conversiones.
- Los paquetes están en PascalCase (Capitalized), mantener consistencia si no se planea refactorizar.

## 5. 📦 Dependencias Críticas
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-web`
- `spring-boot-starter-security`
- `mysql-connector-j`
- `com.h2database:h2` (Test)

## 6. ⚙️ Configuración del Entorno
- Base de datos MySQL configurada mediante variables de entorno y/o properties.
- Base de datos H2 en memoria configurada en `application.properties` en `src/test/resources/` para los tests.
- Herramientas de análisis de código: SonarQube (`http://localhost:9000`) y JaCoCo.

## 7. 🐛 Registro de Errores y Soluciones
| Fecha      | ID    | Error            | Causa Raíz     | Solución Aplicada | Prevención Futura |
|------------|-------|------------------|----------------|-------------------|-------------------|
| 2026-06-15 | 001   | IEmpleado.java no compila | Falta de punto y coma (`;`) al final de la declaración del método tras refactor | Se añadió el punto y coma en la interfaz | Compilar siempre después de cambios de renombrado o refactoring |
| 2026-06-15 | 002   | PermisoServiceImpl.java no compila | Falta importar `java.util.Optional` | Se agregó el import adecuado en la clase del servicio | Usar el IDE para actualizar imports automáticamente |
| 2026-06-15 | 003   | Incompatibilidad de tipos de retorno | Los métodos de Servicio retornaban `ClienteSimplificadoDto` o `ReservacionSimplificadoDto` mientras que Controller y Tests esperaban la versión `DTO` regular | Se ajustaron los tipos de retorno en Test y Controller y se modificaron los `assertEquals` | Validar referencias completas y usos del método tras cambiar su firma |
| 2026-06-15 | 004   | Falla al cargar Spring Context en tests | El método `existsByNumeroHabitacion` en el repository esperaba un `String` en vez del tipo nativo `int` | Se cambió el tipo del parámetro del repositorio a `int` | Mantener consistencia estricta entre el tipo del campo de la entidad JPA y los métodos del repositorio |

## 8. 📚 Lecciones Aprendidas
- **Refactorizaciones de DTOs:** Cuando se divide un DTO en uno de "entrada" y otro de "salida" (ej. `SimplificadoDto`), las aserciones de los tests deben reevaluarse por completo en lugar de simplemente cambiar la variable del tipo esperado, ya que las propiedades diferirán.
- **Validación del Contexto de Spring:** Pequeñas inconsistencias en las firmas de los repositorios de Spring Data JPA (por ejemplo, tipos de parámetros incorrectos) provocan la falla total de carga del ApplicationContext, deteniendo todos los tests de integración en seco.

## 9. 🚨 Reglas Absolutas del Agente
- Validar siempre `agent.md` con MODO AUDIT antes de modificarlo.
- No repetir errores registrados en la sección 7.
- Proteger y referenciar siempre la configuración en este archivo.

## 10. 🧪 Testing — Mejores Prácticas (Java Spring Boot)

### 10.1. Tipos de tests y cuándo usarlos
- **Unitarios (`@ExtendWith(MockitoExtension.class)`):** Para probar la lógica de negocio pura de forma rápida y aislada (Capa `Service`, mappers, utils). No levantan el contexto de Spring.
- **Integración con DB (`@DataJpaTest`):** Exclusivo para probar repositorios, consultas JPQL nativas y mapeo de base de datos.
- **Integración Web (`@WebMvcTest`):** Para probar los Controladores y la serialización JSON. Levanta sólo la capa web, requiriendo Mocks para los servicios.
- **Integración Completa (`@SpringBootTest`):** Para flujos completos desde el controlador (o servicio) hasta la base de datos real o en memoria. Lentos, usar con moderación.

### 10.2. Anotaciones esenciales
- `@Test`: Marca un método como un caso de prueba de JUnit 5.
- `@Mock`: Crea un mock de una dependencia para un test unitario.
- `@InjectMocks`: Inyecta los `@Mock` generados en la clase bajo prueba.
- `@MockBean`: Reemplaza un bean en el ApplicationContext de Spring con un mock (útil en tests de integración).
- `@Sql`: Ejecuta scripts SQL (por ejemplo, para poblar datos base antes de la prueba).

### 10.3. Estructura recomendada de un test
Se recomienda usar el patrón **AAA** (Arrange, Act, Assert):
```java
@Test
void testGetById() {
    // 1. Arrange: Preparar datos y mocks
    Long id = 1L;
    when(repository.findById(id)).thenReturn(Optional.of(entidadMock));

    // 2. Act: Ejecutar el método a probar
    DtoResultado resultado = service.obtenerPorId(id);

    // 3. Assert: Verificar el resultado
    assertNotNull(resultado);
    assertEquals("Esperado", resultado.getValor());
    verify(repository, times(1)).findById(id);
}
```

### 10.4. Convenciones de nomenclatura
Los métodos de prueba deben ser descriptivos:
- `test<NombreMetodo>_<Escenario>_<ResultadoEsperado>` (Ej: `testObtenerPorId_IdInexistente_ThrowsException`).
- Se recomienda el uso de camelCase o snake_case dependiendo de la convención de la organización, pero debe ser consistente.

### 10.5. Uso de Mockito
Mockito permite simular el comportamiento de clases externas.
- Usar `when(...).thenReturn(...)` para indicar qué debe devolver un mock al ser invocado.
- Usar `verify(mock).metodo(...)` para comprobar si el método fue realmente llamado en el flujo del código.
- Usar `any(Clase.class)` para parámetros donde no importe el valor exacto.

### 10.6. Tests de integración vs unitarios
- **Integración:** Lentos, configuran la base de datos (H2) y levantan Spring. Validan la conexión entre componentes y consultas SQL.
- **Unitarios:** Veloces, se ejecutan en milisegundos. Deben abarcar el 80% de los tests de la aplicación. Validar toda regla de negocio compleja con tests unitarios.

### 10.7. Ejemplos comentados
```java
// Ejemplo Test de Integración
@SpringBootTest
@Sql(scripts = "/scripts/datos.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class IntegracionTest {
    @Autowired
    private MiServicio servicio; // Inyecta la implementación real

    @Test
    void testFlujoReal() {
        var res = servicio.obtenerReal(1L);
        assertNotNull(res);
    }
}
```

### 10.8. Errores comunes y cómo evitarlos
- **`NullPointerException` en Mockito:** Olvidar usar `@ExtendWith(MockitoExtension.class)` provocará que los mocks no se inicialicen.
- **Contextos lentos:** Evitar abusar de `@SpringBootTest` y `@MockBean` en cada clase, ya que cada nueva combinación reinicia el contexto de Spring en lugar de cachearlo.
- **Falta de limpieza de BD:** Si usas `@SpringBootTest`, utiliza un entorno limpio (H2 in memory) y borra los datos, o usa la anotación `@Transactional` en las pruebas de repositorio para hacer *rollback* al finalizar el test.
- **Asertos Frágiles:** Usar `assertEquals` entre DTOs completos puede fallar al añadir nuevos campos a la aplicación. Es más seguro verificar únicamente los campos relevantes para cada test específico.
---
