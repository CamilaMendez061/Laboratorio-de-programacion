-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: db_gestion_alumnos
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido_nombre` varchar(30) NOT NULL,
  `dni` int NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `carrera_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5oh6efmksfdmbwyhjft54l72` (`carrera_id`),
  CONSTRAINT `FKf5oh6efmksfdmbwyhjft54l72` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (1,'Perez Juan',20987654,'perez_j@gmail.com','15444666',10),(2,'Gomez Marta',41556688,'martagomez@gmail.com','15112233',8),(3,'Rodriguez Teresa Amalia',10415889,'tere_rodriguez@gmail.com','15151617',15),(4,'Flores Blanca Margarita',33123456,'margarita_flores@gmail.com','15002234',11),(5,'Martinez Emanuel',42777123,'martinez_ema@hotmail.com','15468912',5),(6,'Gonzalez Alberto ',27894561,'albert_g@outlook.com','15002245',4),(7,'Parker Peter',38000145,'peter_spider@gmail.com','15998877',2),(8,'Fernandez Fernanda',39223568,'fer-fer@outlook.com','15748962',1),(9,'Quito Armando Esteban',26014025,'armando_quito@gmail.com','15668847',1),(10,'Pietro Germán',46587777,'germanpietro@hotmail.com','15898978',13),(11,'Zarate Andrés ',20203204,'andres_zzz@gmail.com','15789642',7),(14,'Villar Diego Ramón',28555456,'ramoncito@gmail.com','15564478',14);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
INSERT INTO `carreras` VALUES (1,'Analista de Sistemas'),(2,'Licenciatura en Sistemas'),(3,'Ingeniería en Recursos Naturales Renovables'),(4,'Tecnicatura en Análisis Químico'),(5,'Ingeniería Química'),(6,'Profesorado en Matemática'),(7,'Licenciatura en Administración'),(8,'Licenciatura en Trabajo Social'),(9,'Licenciatura en Comunicación Social'),(10,'Tecnicatura y Licenciatura en Turismo'),(11,'Profesorado en Letras'),(12,'Profesorado en Historia'),(13,'Profesorado en Geografía'),(14,'Licenciatura en Psicopedagogía'),(15,'Licenciatura en Enfermería');
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-23 16:43:48
