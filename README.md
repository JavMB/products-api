# Products API - Implementación con Arquitectura Limpia

Esta es una API REST para la gestión de productos, desarrollada con **Spring Boot**. El objetivo principal de este proyecto es demostrar la aplicación práctica de patrones de diseño y arquitectura de software modernos, como la **Arquitectura Hexagonal**, **CQRS** y el patrón **Mediator** mientras aprendia.

## Filosofía del Diseño

En lugar de un CRUD tradicional, se ha optado por una arquitectura que prioriza la mantenibilidad, escalabilidad y el testeo.

- **Arquitectura Hexagonal (Puertos y Adaptadores)**: Desacopla el núcleo de la lógica de negocio de las implementaciones externas (como la base de datos o la API REST). Esto permite cambiar tecnologías sin impactar la lógica central. El dominio es agnóstico a la infraestructura.

- **CQRS (Command Query Responsibility Segregation)**: Se separan las operaciones de escritura (Comandos) de las de lectura (Consultas). Esto permite optimizar cada lado de forma independiente. Por ejemplo, las consultas pueden ser más simples y rápidas, mientras que los comandos pueden contener lógica de negocio compleja y validaciones.

- **Patrón Mediator**: Actúa como un intermediario que recibe todas las peticiones (Commands y Queries) y las delega a su `Handler` correspondiente. Esto evita que los controladores tengan dependencias directas con los servicios, reduciendo el acoplamiento y simplificando el código del controlador.

## Stack Tecnológico

- **Backend**: Java 21, Spring Boot 3.5.4, Spring Data JPA
- **Base de Datos**: PostgreSQL (para desarrollo/producción) y H2 (para tests)
- **Testing**: JUnit 5, Mockito, TestRestTemplate para tests de integración.
- **Herramientas**: MapStruct para mapeo de DTOs, Lombok, Docker, Maven.
- **Documentación**: OpenAPI 3 (Swagger).

## Estructura del Proyecto

La estructura de directorios refleja la Arquitectura Hexagonal y la separación de responsabilidades:

```
└── product/
    ├── application/
    │   ├── command/      # Lógica de escritura (Crear, Actualizar, Borrar)
    │   └── querys/       # Lógica de lectura (Obtener todos, Obtener por ID)
    ├── domain/
    │   ├── entity/       # Modelos de dominio puros
    │   └── port/         # Interfaces (puertos) para repositorios
    └── infrastructure/
        ├── api/          # Adaptadores de entrada (Controladores REST, DTOs)
        └── persistence/  # Adaptadores de salida (Implementación de repositorios con JPA)
```

## Testing

La estrategia de testing está diseñada para cubrir diferentes niveles de la aplicación:

- **Tests Unitarios**: Se centran en los `Handlers` de CQRS, usando mocks para los repositorios y dependencias externas. Esto asegura que la lógica de negocio funciona como se espera de forma aislada.
- **Tests de Integración**: Prueban los endpoints de la API de extremo a extremo. Levantan un contexto de Spring y utilizan una base de datos H2 en memoria para validar todo el flujo, desde la petición HTTP hasta la base de datos.

```bash
# Ejecutar solo los tests unitarios
mvn test

# Ejecutar los tests de integración (perfil 'it')
mvn verify -P it
```

## Configuración y Ejecución

El proyecto utiliza perfiles de Spring para gestionar diferentes configuraciones:

- `dev`: Para desarrollo local, se conecta a una base de datos PostgreSQL.
- `test`: Utilizado por los tests de integración, configura una base de datos H2 en memoria.
- `prod`: Perfil para producción, con configuraciones optimizadas.

### Ejecución con Docker

La forma más sencilla de levantar el entorno completo (API + Base de datos) es con Docker Compose:

```bash
docker-compose up -d
```

### Ejecución en Local

Si prefieres ejecutar la aplicación directamente con Maven:

```bash
# Asegúrate de tener una instancia de PostgreSQL corriendo
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Documentación de la API

La API está documentada con OpenAPI 3. Una vez que la aplicación está en ejecución, puedes acceder a la interfaz de Swagger UI para explorar y probar los endpoints:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

## Endpoints Principales

![img.png](src/main/resources/img.png)

