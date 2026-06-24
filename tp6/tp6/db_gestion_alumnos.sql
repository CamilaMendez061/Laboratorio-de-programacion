CREATE DATABASE IF NOT EXISTS db_gestion_alumnos;
USE db_gestion_alumnos;

CREATE TABLE carreras (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE alumnos (
    id INT NOT NULL AUTO_INCREMENT,
    apellido_nombre VARCHAR(30) NOT NULL,
    dni INT NOT NULL,
    email VARCHAR(30) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    carrera_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (carrera_id) REFERENCES carreras(id)
);

INSERT INTO carreras (id, nombre) VALUES
(1,'Analista de Sistemas'),
(2,'Licenciatura en Sistemas'),
(3,'Ingeniería en Recursos Naturales Renovables'),
(4,'Tecnicatura en Análisis Químico'),
(5,'Ingeniería Química'),
(6,'Profesorado en Matemática'),
(7,'Licenciatura en Administración'),
(8,'Licenciatura en Trabajo Social'),
(9,'Licenciatura en Comunicación Social'),
(10,'Tecnicatura y Licenciatura en Turismo'),
(11,'Profesorado en Letras'),
(12,'Profesorado en Historia'),
(13,'Profesorado en Geografía'),
(14,'Licenciatura en Psicopedagogía'),
(15,'Licenciatura en Enfermería');

INSERT INTO alumnos (id, apellido_nombre, dni, email, telefono, carrera_id) VALUES
(1,'Perez Juan',20987654,'perez_j@gmail.com','15444666',10),
(2,'Gomez Marta',41556688,'martagomez@gmail.com','15112233',8),
(3,'Rodriguez Teresa Amalia',10415889,'tere_rodriguez@gmail.com','15151617',15),
(4,'Flores Blanca Margarita',33123456,'margarita_flores@gmail.com','15002234',11),
(5,'Martinez Emanuel',42777123,'martinez_ema@hotmail.com','15468912',5),
(6,'Gonzalez Alberto',27894561,'albert_g@outlook.com','15002245',4),
(7,'Parker Peter',38000145,'peter_spider@gmail.com','15998877',2),
(8,'Fernandez Fernanda',39223568,'fer-fer@outlook.com','15748962',1),
(9,'Quito Armando Esteban',26014025,'armando_quito@gmail.com','15668847',1),
(10,'Pietro Germán',46587777,'germanpietro@hotmail.com','15898978',13),
(11,'Zarate Andrés',20203204,'andres_zzz@gmail.com','15789642',7),
(14,'Villar Diego Ramón',28555456,'ramoncito@gmail.com','15564478',14);
