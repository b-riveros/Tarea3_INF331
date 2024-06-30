# Tarea3_INF331

Este proyecto implementa un sistema de gestión de reservas, salas y usuarios utilizando Java y Maven. El sistema permite realizar operaciones básicas de creación, listado, actualización y eliminación de reservas, salas y usuarios mediante una interfaz de línea de comandos.

## Funcionalidades Implementadas

### Gestión de Reservas

El módulo de gestión de reservas permite:

- Crear nuevas reservas especificando el código de la sala, el identificador del usuario y la fecha.
- Listar todas las reservas registradas.
- Actualizar una reserva existente mediante su código de sala y el identificador del usuario.
- Eliminar una reserva utilizando el código de la sala y el identificador del usuario asociados.

### Gestión de Salas

El módulo de gestión de salas permite:

- Agregar nuevas salas con un código único, nombre y ubicación.
- Listar todas las salas registradas.
- Actualizar los datos de una sala existente por su código.
- Eliminar una sala usando su código.

### Gestión de Usuarios

El módulo de gestión de usuarios permite:

- Registrar nuevos usuarios con un identificador único, nombre, departamento y descripción.
- Listar todos los usuarios registrados.
- Actualizar los datos de un usuario existente mediante su identificador.
- Eliminar un usuario utilizando su identificador.

## Ejecución y Pruebas

Para ejecutar el proyecto y realizar pruebas, sigue estos pasos:

1. **Clona el repositorio:**
```bash
git clone https://github.com/b-riveros/Tarea3_INF331.git
```

2. **Compila el proyecto:**
```bash
mvn clean compile
```

3. **Ejecuta las pruebas:**
```bash
mvn test
```

## Consideraciones Adicionales

Algunas consideraciones para mejorar el proyecto:

- Faltan las pruebas para asegurar que las funciones de listado no muestren datos cuando las colecciones están vacías.
- Validar que no se pueda agregar una sala o usuario con un código o identificador que ya exista, para garantizar que sean únicos.

## Reporte de pruebas
```bash
[INFO] Results:
[INFO] 
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.457 s
[INFO] Finished at: 2024-06-30T01:33:13-04:00
[INFO] ------------------------------------------------------------------------
```

