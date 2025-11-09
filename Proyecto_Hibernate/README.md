# 🏋️‍♂️ Proyecto Gimnasio - Hibernate & MySQL

## 📘 Descripción

Este proyecto implementa una aplicación Java utilizando **Hibernate (JPA)** y **MySQL** para la gestión de un gimnasio.
El objetivo es demostrar la creación y mapeo de entidades con relaciones **1:1**, **1:N** y **N:M**, así como las operaciones básicas de **CRUD (Create, Read, Update, Delete)**.

El dominio representa la gestión de socios, entrenadores y clases de un gimnasio.
Cada socio puede inscribirse en múltiples clases, cada clase puede tener un entrenador asignado y cada socio posee un perfil físico asociado.

---

## 🧩 Estructura del Proyecto

**Paquete principal:** `org.example`

**Entidades del modelo (`org.example.model`):**
- `Socio` → representa a un socio del gimnasio.
- `Perfil` → información física (peso, altura y objetivo).
- `Entrenador` → personal del gimnasio que imparte clases.
- `Clase` → clases grupales ofrecidas en el gimnasio.
- `SocioClase` → tabla intermedia para la relación N:M entre socio y clase.
- `SocioClaseID` → clave compuesta embebida usada en `SocioClase`.
- `Objetivo` → enumeración que define el objetivo físico del socio.

**Configuración:**
- `persistence.xml` → contiene la configuración de conexión a la base de datos y el proveedor Hibernate.
- `Main.java` → clase principal para ejecutar el programa y probar el CRUD.

---

## 🗄️ Base de Datos

**Nombre:** `gimnasio`
**Motor:** MySQL 8.0+

**Relaciones principales:**
- **1:1** → `Socio` ↔ `Perfil`
- **1:N** → `Entrenador` ↔ `Clase`
- **N:M** → `Socio` ↔ `Clase` (a través de `SocioClase`)

**Script de creación y datos de ejemplo:**?
`gimnasio_dump.sql`

El script incluye:
- Creación de todas las tablas.
- Inserción de datos de prueba (socios, clases, entrenadores y perfiles).
- Relaciones entre las entidades.

---

## 🚀 Ejecución del Programa

1. Crear la base de datos ejecutando el script `gimnasio_dump.sql` en MySQL Workbench.
2. Verificar o ajustar las credenciales de conexión en `persistence.xml`.
3. Compilar y ejecutar el proyecto
4. Ejecutar la clase principal `Main.java` para probar el CRUD sobre la entidad `Clase`.

---

## 🧰 Funcionalidades CRUD

Se implementa un CRUD completo sobre la entidad **Clase**, con las siguientes operaciones:

- **Crear:** Añade una nueva clase a la base de datos.
- **Leer:** Lista las clases existentes (con o sin relaciones).
- **Actualizar:** Modifica datos de una clase (nombre, horario, cupo, entrenador).
- **Eliminar:** Borra una clase seleccionada.

Ejemplo de uso en `Main.java`:
```java
Clase nuevaClase = new Clase();
nuevaClase.setNombre("Yoga Avanzado");
nuevaClase.setHorario("Martes 10:00-11:00");
nuevaClase.setCupo(15);
em.persist(nuevaClase);

