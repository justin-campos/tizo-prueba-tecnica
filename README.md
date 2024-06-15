# Sistema de Gestión de Reservas y Menú de Restaurante.

Este proyecto es una API RESTful que desarrollé para gestionar reservas y el menú de un restaurante. Utilicé Spring Boot, una base de datos SQL y Docker Compose para su despliegue. La API está documentada con Swagger.
## Enfoque del proyecto

### Arquitectura de Capas

El proyecto sigue una arquitectura de capas para separar las responsabilidades y facilitar el mantenimiento y la escalabilidad. Las capas principales son:

- **Capa de Controladores (Controllers):** Gestiona las peticiones HTTP y llama a los servicios correspondientes.
- **Capa de Servicios (Services):** Contiene la lógica de negocio y realiza operaciones en la base de datos.
- **Capa de Repositorios (Repositories):** Interactúa con la base de datos para realizar operaciones CRUD.


### Base de Datos

Utilice la base de datos SQL Server en un contenedor de Docker con un volumen test_vol para almacenar la información de las reservas y el menú del restaurante. Creé las entidades necesarias para almacenar esta información.

### Reservas

Implementé endpoints para crear una nueva reserva, obtener la información de una reserva por su ID, listar todas las reservas realizadas, cancelar una reserva y eliminar una reserva por su ID.

### Menú

Para el menú, implementé endpoints para listar todos los platos disponibles y obtener la información de un plato por su ID.

### Validaciones

Se han implementado validaciones para garantizar la integridad de los datos y prevenir errores. Algunas de las validaciones incluyen:

- **Reservas:** Validar que los campos obligatorios (nombre del cliente, número de personas, fecha y hora) estén presentes y sean válidos.
- **Platos del Menú:** Validar que los campos obligatorios (nombre, descripción y precio) estén presentes y sean válidos.

#### Excepciones y ControllerAdvice

Se utiliza ControllerAdvice para manejar las excepciones de manera centralizada y proporcionar respuestas coherentes en caso de errores. Esto ayuda a mantener un código limpio y facilita el mantenimiento.



### Documentación de la API


**URL de la Documentación Swagger:**

```bash
http://localhost:8080/swagger-ui.html
```

Esta URL te llevará a la interfaz de Swagger, donde encontrarás una descripción detallada de cada endpoint, incluyendo sus métodos, parámetros y códigos de respuesta. También podrás probar los endpoints directamente desde la interfaz.


## Configuración del Proyecto

### Docker Compose

El proyecto utiliza Docker Compose para gestionar los contenedores. Asegúrate de tener Docker instalado y luego sigue estos pasos:

1. Clona el repositorio:

    ```bash
    git clone <github.com/justin-campos>
    ```

2. Levanta los contenedores:

    ```bash
    docker-compose up -d
    ```

   Esto iniciará un contenedor para la base de datos SQL Server.

### Base de Datos

El contenedor de la base de datos SQL Server se configura con un volumen `test_vol` para persistir los datos. La estructura de la base de datos se crea automáticamente al iniciar la aplicación Spring Boot.



