CREATE DATABASE IF NOT EXISTS banco;
USE banco;
 
 CREATE TABLE IF NOT EXISTS cuentas(
 cuenta INT NOT NULL AUTO_INCREMENT,
 nombre_cliente VARCHAR(30) NOT NULL,
 saldo DOUBLE UNSIGNED DEFAULT 0.0,
 tipo_cuenta ENUM('A', 'C') NOT NULL,
 PRIMARY KEY (cuenta),
 CONSTRAINT saldo_positivo CHECK (saldo >= 0)
 );
 
 CREATE TABLE IF NOT EXISTS movimientos(
 id_movimiento INT NOT NULL AUTO_INCREMENT,
 cuenta INT NOT NULL,
 movimiento ENUM('D', 'E') NOT NULL,
 importe DOUBLE NOT NULL,
 PRIMARY KEY (id_movimiento), 
 FOREIGN KEY (cuenta) REFERENCES cuentas (cuenta)
	ON DELETE CASCADE
	ON UPDATE CASCADE
 );
 
    
INSERT INTO cuentas (nombre_cliente, saldo, tipo_cuenta)
VALUES	('Fernanda Gonzales', 100000, 'A'),
		('Tomás Gomez', 500750, 'A'),
        ('Anahí Almada', 0, 'C'),
        ('Antonella Fisch', 100.25, 'A'),
        ('Teresa Rodriguez', 1642900, 'C'),
        ('Agustín Martinez', 0.10, 'C'),
        ('Fernanda Lopez', 0, 'C'),
        ('Armando Paredes', 0, 'A');
        