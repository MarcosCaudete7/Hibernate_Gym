
DROP DATABASE IF EXISTS gimnasiodb;
CREATE DATABASE gimnasiodb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gimnasiodb;

-- -----------------------------------------------------
-- Table: perfil_fisico
-- -----------------------------------------------------
CREATE TABLE perfil_fisico (
  id_perfil BIGINT AUTO_INCREMENT PRIMARY KEY,
  peso FLOAT,
  altura FLOAT,
  objetivo VARCHAR(50)
);

-- -----------------------------------------------------
-- Table: socio
-- -----------------------------------------------------
CREATE TABLE socio (
  id_socio BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  fecha_inscripcion DATE,
  id_perfil BIGINT UNIQUE,
  CONSTRAINT fk_socio_perfil FOREIGN KEY (id_perfil)
    REFERENCES perfil_fisico(id_perfil)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table: entrenador
-- -----------------------------------------------------
CREATE TABLE entrenador (
  id_entrenador BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  especialidad VARCHAR(100),
  telefono VARCHAR(20)
);

-- -----------------------------------------------------
-- Table: clase
-- -----------------------------------------------------
CREATE TABLE clase (
  id_clase BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  horario VARCHAR(50),
  cupo INT,
  id_entrenador BIGINT,
  CONSTRAINT fk_clase_entrenador FOREIGN KEY (id_entrenador)
    REFERENCES entrenador(id_entrenador)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table: socio_clase (N:M)
-- -----------------------------------------------------
CREATE TABLE socio_clase (
  id_socio BIGINT NOT NULL,
  id_clase BIGINT NOT NULL,
  fechaRegistro DATE,
  PRIMARY KEY (id_socio, id_clase),
  CONSTRAINT fk_socio_clase_socio FOREIGN KEY (id_socio)
    REFERENCES socio(id_socio)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_socio_clase_clase FOREIGN KEY (id_clase)
    REFERENCES clase(id_clase)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Datos de ejemplo
-- -----------------------------------------------------
-- Entrenadores
INSERT INTO entrenador (nombre, especialidad, telefono) VALUES
('Laura Gómez', 'Yoga', '612345678'),
('Carlos Pérez', 'CrossFit', '678901234');

-- Clases
INSERT INTO clase (nombre, horario, cupo, id_entrenador) VALUES
('Yoga Avanzado', 'Lunes 10:00-11:00', 15, 1),
('CrossFit Básico', 'Martes 18:00-19:00', 20, 2),
('Pilates', 'Miércoles 12:00-13:00', 12, NULL);

-- Perfiles físicos
INSERT INTO perfil_fisico (peso, altura, objetivo) VALUES
(70.5, 1.75, 'Pérdida de peso'),
(82.0, 1.80, 'Ganar masa muscular');

-- Socios
INSERT INTO socio (nombre, email, fecha_inscripcion, id_perfil) VALUES
('María López', 'maria@example.com', '2025-01-10', 1),
('Javier Ruiz', 'javier@example.com', '2025-02-05', 2);

-- Socios inscritos en clases
INSERT INTO socio_clase (id_socio, id_clase, fechaRegistro) VALUES
(1, 1, '2025-02-12'),
(1, 3, '2025-03-15'),
(2, 2, '2025-02-20');

-- -----------------------------------------------------
-- Consultas de comprobación
-- -----------------------------------------------------
-- Ver clases con entrenador asignado
SELECT c.nombre AS Clase, e.nombre AS Entrenador
FROM clase c
LEFT JOIN entrenador e ON c.id_entrenador = e.id_entrenador;

-- Ver socios y sus clases
SELECT s.nombre AS Socio, c.nombre AS Clase, c.horario
FROM socio s
JOIN socio_clase sc ON s.id_socio = sc.id_socio
JOIN clase c ON sc.id_clase = c.id_clase;
