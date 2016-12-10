/*
SQLyog Community v12.3.0 (64 bit)
MySQL - 5.7.16-log : Database - obligatorio_disapp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`obligatorio_disapp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `obligatorio_disapp`;

/*Table structure for table `administradores` */

DROP TABLE IF EXISTS `administradores`;

CREATE TABLE `administradores` (
  `oid` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `nombreCompleto` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administradores` */

insert  into `administradores`(`oid`,`nombre`,`pass`,`nombreCompleto`) values 
(89,'a','a','Analía Pereyra'),
(88,'b','b','Blanca Moreira'),
(87,'c','c','Claudia Tabárez'),
(86,'d','d','Dilma Rousseff'),
(85,'e','e','Emilia Suárez'),
(84,'f','f','Fabiana Guerra'),
(83,'g','g','Graciela García'),
(82,'h','h','Heidy Montero'),
(81,'i','i','Ilda De León'),
(80,'j','j','Judith Barsi');

/*Table structure for table `fichastableromovimiento` */

DROP TABLE IF EXISTS `fichastableromovimiento`;

CREATE TABLE `fichastableromovimiento` (
  `oid` int(11) NOT NULL,
  `numeroFichaJugada` int(11) NOT NULL AUTO_INCREMENT,
  `codPartida` varchar(100) NOT NULL,
  `numeroMovimiento` int(11) NOT NULL,
  `valorDer` int(11) NOT NULL,
  `valorIzq` int(11) NOT NULL,
  PRIMARY KEY (`codPartida`,`numeroMovimiento`,`numeroFichaJugada`),
  KEY `numroFichaJugada` (`numeroFichaJugada`),
  CONSTRAINT `fichastableromovimiento_ibfk_1` FOREIGN KEY (`codPartida`, `numeroMovimiento`) REFERENCES `movimiento` (`codPartida`, `numeroMovimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `fichastableromovimiento` */

insert  into `fichastableromovimiento`(`oid`,`numeroFichaJugada`,`codPartida`,`numeroMovimiento`,`valorDer`,`valorIzq`) values 
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',1,5,0),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',2,5,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',2,5,5),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',3,0,2),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',3,5,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',3,5,5),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',4,2,3),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',4,0,2),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',4,5,0),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',4,5,5),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',5,3,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',5,2,3),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',5,0,2),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',5,5,0),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',5,5,5),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',6,3,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',6,2,3),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',6,0,2),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',6,5,0),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',6,5,5),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',6,6,5),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',7,0,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',7,3,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',7,2,3),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',7,0,2),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',7,5,0),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',7,5,5),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',7,6,5),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',8,0,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',8,3,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',8,2,3),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',8,0,2),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',8,5,0),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',8,5,5),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',8,6,5),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',8,3,6),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',9,0,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',9,3,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',9,2,3),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',9,0,2),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',9,5,0),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',9,5,5),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',9,6,5),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',9,3,6),
(100,9,'Sat Dec 10 00:01:31 UYST 2016_a',9,4,3),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',10,0,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',10,3,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',10,2,3),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',10,0,2),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',10,5,0),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',10,5,5),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',10,6,5),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',10,3,6),
(100,9,'Sat Dec 10 00:01:31 UYST 2016_a',10,4,3),
(100,10,'Sat Dec 10 00:01:31 UYST 2016_a',10,1,4),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',11,0,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',11,3,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',11,2,3),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',11,0,2),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',11,5,0),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',11,5,5),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',11,6,5),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',11,3,6),
(100,9,'Sat Dec 10 00:01:31 UYST 2016_a',11,4,3),
(100,10,'Sat Dec 10 00:01:31 UYST 2016_a',11,1,4),
(100,11,'Sat Dec 10 00:01:31 UYST 2016_a',11,3,1),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',12,0,0),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',12,3,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',12,2,3),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',12,0,2),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',12,5,0),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',12,5,5),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',12,6,5),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',12,3,6),
(100,9,'Sat Dec 10 00:01:31 UYST 2016_a',12,4,3),
(100,10,'Sat Dec 10 00:01:31 UYST 2016_a',12,1,4),
(100,11,'Sat Dec 10 00:01:31 UYST 2016_a',12,3,1),
(100,12,'Sat Dec 10 00:01:31 UYST 2016_a',12,3,3),
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',13,0,4),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',13,0,0),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',13,3,0),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',13,2,3),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',13,0,2),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',13,5,0),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',13,5,5),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',13,6,5),
(100,9,'Sat Dec 10 00:01:31 UYST 2016_a',13,3,6),
(100,10,'Sat Dec 10 00:01:31 UYST 2016_a',13,4,3),
(100,11,'Sat Dec 10 00:01:31 UYST 2016_a',13,1,4),
(100,12,'Sat Dec 10 00:01:31 UYST 2016_a',13,3,1),
(100,13,'Sat Dec 10 00:01:31 UYST 2016_a',13,3,3),
(101,1,'Sat Dec 10 00:07:41 UYST 2016_a',1,4,4),
(101,1,'Sat Dec 10 00:07:41 UYST 2016_a',2,4,0),
(101,2,'Sat Dec 10 00:07:41 UYST 2016_a',2,4,4),
(101,1,'Sat Dec 10 00:07:41 UYST 2016_a',3,4,0),
(101,2,'Sat Dec 10 00:07:41 UYST 2016_a',3,4,4),
(101,3,'Sat Dec 10 00:07:41 UYST 2016_a',3,3,4),
(101,1,'Sat Dec 10 00:07:41 UYST 2016_a',4,4,0),
(101,2,'Sat Dec 10 00:07:41 UYST 2016_a',4,4,4),
(101,3,'Sat Dec 10 00:07:41 UYST 2016_a',4,3,4),
(101,4,'Sat Dec 10 00:07:41 UYST 2016_a',4,5,3),
(102,1,'Sat Dec 10 00:08:16 UYST 2016_c',1,4,0),
(102,1,'Sat Dec 10 00:08:16 UYST 2016_c',2,4,0),
(102,2,'Sat Dec 10 00:08:16 UYST 2016_c',2,5,4),
(102,1,'Sat Dec 10 00:08:16 UYST 2016_c',3,4,0),
(102,2,'Sat Dec 10 00:08:16 UYST 2016_c',3,5,4),
(102,3,'Sat Dec 10 00:08:16 UYST 2016_c',3,5,5),
(102,1,'Sat Dec 10 00:08:16 UYST 2016_c',4,4,0),
(102,2,'Sat Dec 10 00:08:16 UYST 2016_c',4,5,4),
(102,3,'Sat Dec 10 00:08:16 UYST 2016_c',4,5,5),
(102,4,'Sat Dec 10 00:08:16 UYST 2016_c',4,0,5),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',1,4,4),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',2,4,6),
(104,2,'Sat Dec 10 00:09:21 UYST 2016_d',2,4,4),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',3,4,6),
(104,2,'Sat Dec 10 00:09:21 UYST 2016_d',3,4,4),
(104,3,'Sat Dec 10 00:09:21 UYST 2016_d',3,5,4),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',4,4,6),
(104,2,'Sat Dec 10 00:09:21 UYST 2016_d',4,4,4),
(104,3,'Sat Dec 10 00:09:21 UYST 2016_d',4,5,4),
(104,4,'Sat Dec 10 00:09:21 UYST 2016_d',4,6,5),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',5,6,6),
(104,2,'Sat Dec 10 00:09:21 UYST 2016_d',5,4,6),
(104,3,'Sat Dec 10 00:09:21 UYST 2016_d',5,4,4),
(104,4,'Sat Dec 10 00:09:21 UYST 2016_d',5,5,4),
(104,5,'Sat Dec 10 00:09:21 UYST 2016_d',5,6,5),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',6,6,6),
(104,2,'Sat Dec 10 00:09:21 UYST 2016_d',6,4,6),
(104,3,'Sat Dec 10 00:09:21 UYST 2016_d',6,4,4),
(104,4,'Sat Dec 10 00:09:21 UYST 2016_d',6,5,4),
(104,5,'Sat Dec 10 00:09:21 UYST 2016_d',6,6,5),
(104,6,'Sat Dec 10 00:09:21 UYST 2016_d',6,1,6),
(105,1,'Sat Dec 10 00:26:52 UYST 2016_d',1,2,0),
(105,1,'Sat Dec 10 00:26:52 UYST 2016_d',2,2,0),
(105,2,'Sat Dec 10 00:26:52 UYST 2016_d',2,4,2),
(105,1,'Sat Dec 10 00:26:52 UYST 2016_d',3,2,0),
(105,2,'Sat Dec 10 00:26:52 UYST 2016_d',3,4,2),
(105,3,'Sat Dec 10 00:26:52 UYST 2016_d',3,3,4),
(105,1,'Sat Dec 10 00:26:52 UYST 2016_d',4,0,5),
(105,2,'Sat Dec 10 00:26:52 UYST 2016_d',4,2,0),
(105,3,'Sat Dec 10 00:26:52 UYST 2016_d',4,4,2),
(105,4,'Sat Dec 10 00:26:52 UYST 2016_d',4,3,4);

/*Table structure for table `jugadores` */

DROP TABLE IF EXISTS `jugadores`;

CREATE TABLE `jugadores` (
  `oid` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `nombreCompleto` varchar(50) NOT NULL,
  `saldo` decimal(12,2) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jugadores` */

insert  into `jugadores`(`oid`,`nombre`,`pass`,`nombreCompleto`,`saldo`) values 
(99,'a','a','Alvaro Fernández',450.00),
(98,'b','b','Bruno Díaz',400.00),
(97,'c','c','Carlos González',350.00),
(96,'d','d','Darío Pérez',200.00),
(95,'e','e','Emiliano Lasa',250.00),
(94,'f','f','Federico Speroni',200.00),
(93,'g','g','Gustavo Valverde',150.00),
(92,'h','h','Hugo Collazo',100.00),
(91,'i','i','Ismael Espósito',50.00),
(90,'j','j','Javier Montero',0.00);

/*Table structure for table `movimiento` */

DROP TABLE IF EXISTS `movimiento`;

CREATE TABLE `movimiento` (
  `oid` int(11) NOT NULL,
  `numeroMovimiento` int(11) NOT NULL AUTO_INCREMENT,
  `codPartida` varchar(100) NOT NULL,
  `ganador` varchar(50) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `pozo` decimal(12,2) NOT NULL,
  `nombreJugador` varchar(50) NOT NULL,
  PRIMARY KEY (`numeroMovimiento`,`codPartida`),
  KEY `FK_Partida` (`codPartida`),
  KEY `FK_JugadorGanador` (`ganador`),
  KEY `FK_Jugador` (`nombreJugador`),
  CONSTRAINT `FK_Jugador` FOREIGN KEY (`nombreJugador`) REFERENCES `jugadores` (`nombre`),
  CONSTRAINT `FK_JugadorGanador` FOREIGN KEY (`ganador`) REFERENCES `jugadores` (`nombre`),
  CONSTRAINT `FK_Partida` FOREIGN KEY (`codPartida`) REFERENCES `partida` (`codPartida`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `movimiento` */

insert  into `movimiento`(`oid`,`numeroMovimiento`,`codPartida`,`ganador`,`fecha`,`pozo`,`nombreJugador`) values 
(100,1,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:32',200.00,'a'),
(101,1,'Sat Dec 10 00:07:41 UYST 2016_a',NULL,'2016-12-10 00:07:41',200.00,'a'),
(102,1,'Sat Dec 10 00:08:16 UYST 2016_c',NULL,'2016-12-10 00:08:16',200.00,'c'),
(103,1,'Sat Dec 10 00:08:56 UYST 2016_d','d','2016-12-10 00:08:57',200.00,'d'),
(104,1,'Sat Dec 10 00:09:21 UYST 2016_d',NULL,'2016-12-10 00:09:21',200.00,'d'),
(105,1,'Sat Dec 10 00:26:52 UYST 2016_d',NULL,'2016-12-10 00:26:53',200.00,'d'),
(100,2,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:35',200.00,'a'),
(101,2,'Sat Dec 10 00:07:41 UYST 2016_a',NULL,'2016-12-10 00:07:47',200.00,'a'),
(102,2,'Sat Dec 10 00:08:16 UYST 2016_c',NULL,'2016-12-10 00:08:21',200.00,'c'),
(104,2,'Sat Dec 10 00:09:21 UYST 2016_d',NULL,'2016-12-10 00:09:26',200.00,'d'),
(105,2,'Sat Dec 10 00:26:52 UYST 2016_d',NULL,'2016-12-10 00:27:00',200.00,'d'),
(100,3,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:36',200.00,'b'),
(101,3,'Sat Dec 10 00:07:41 UYST 2016_a',NULL,'2016-12-10 00:07:50',200.00,'b'),
(102,3,'Sat Dec 10 00:08:16 UYST 2016_c',NULL,'2016-12-10 00:08:25',200.00,'f'),
(104,3,'Sat Dec 10 00:09:21 UYST 2016_d',NULL,'2016-12-10 00:09:28',200.00,'c'),
(105,3,'Sat Dec 10 00:26:52 UYST 2016_d',NULL,'2016-12-10 00:27:02',200.00,'b'),
(100,4,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:39',200.00,'a'),
(101,4,'Sat Dec 10 00:07:41 UYST 2016_a',NULL,'2016-12-10 00:07:52',200.00,'a'),
(102,4,'Sat Dec 10 00:08:16 UYST 2016_c',NULL,'2016-12-10 00:08:27',300.00,'c'),
(104,4,'Sat Dec 10 00:09:21 UYST 2016_d',NULL,'2016-12-10 00:09:30',200.00,'d'),
(105,4,'Sat Dec 10 00:26:52 UYST 2016_d',NULL,'2016-12-10 00:27:04',200.00,'d'),
(100,5,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:41',200.00,'b'),
(101,5,'Sat Dec 10 00:07:41 UYST 2016_a','a','2016-12-10 00:07:56',200.00,'b'),
(102,5,'Sat Dec 10 00:08:16 UYST 2016_c','f','2016-12-10 00:08:40',300.00,'f'),
(104,5,'Sat Dec 10 00:09:21 UYST 2016_d',NULL,'2016-12-10 00:09:33',200.00,'c'),
(105,5,'Sat Dec 10 00:26:52 UYST 2016_d','b','2016-12-10 00:27:07',200.00,'b'),
(100,6,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:45',200.00,'a'),
(104,6,'Sat Dec 10 00:09:21 UYST 2016_d',NULL,'2016-12-10 00:09:35',200.00,'d'),
(100,7,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:51',200.00,'b'),
(104,7,'Sat Dec 10 00:09:21 UYST 2016_d','c','2016-12-10 00:09:42',200.00,'c'),
(100,8,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:54',200.00,'a'),
(100,9,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:57',200.00,'b'),
(100,10,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:01:59',200.00,'a'),
(100,11,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:02:09',200.00,'b'),
(100,12,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:02:12',200.00,'a'),
(100,13,'Sat Dec 10 00:01:31 UYST 2016_a',NULL,'2016-12-10 00:02:15',200.00,'b'),
(100,14,'Sat Dec 10 00:01:31 UYST 2016_a','a','2016-12-10 00:02:17',200.00,'a');

/*Table structure for table `parametros` */

DROP TABLE IF EXISTS `parametros`;

CREATE TABLE `parametros` (
  `Nombre` varchar(50) NOT NULL,
  `Valor` int(11) NOT NULL,
  PRIMARY KEY (`Nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `parametros` */

insert  into `parametros`(`Nombre`,`Valor`) values 
('oid',106);

/*Table structure for table `partida` */

DROP TABLE IF EXISTS `partida`;

CREATE TABLE `partida` (
  `oid` int(11) NOT NULL,
  `codPartida` varchar(100) NOT NULL,
  `nombreJugador1` varchar(50) NOT NULL,
  `nombreJugador2` varchar(50) NOT NULL,
  PRIMARY KEY (`codPartida`),
  KEY `FK_Jugador1` (`nombreJugador1`),
  KEY `FK_Jugador2` (`nombreJugador2`),
  CONSTRAINT `FK_Jugador1` FOREIGN KEY (`nombreJugador1`) REFERENCES `jugadores` (`nombre`),
  CONSTRAINT `FK_Jugador2` FOREIGN KEY (`nombreJugador2`) REFERENCES `jugadores` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `partida` */

insert  into `partida`(`oid`,`codPartida`,`nombreJugador1`,`nombreJugador2`) values 
(100,'Sat Dec 10 00:01:31 UYST 2016_a','a','b'),
(101,'Sat Dec 10 00:07:41 UYST 2016_a','a','b'),
(102,'Sat Dec 10 00:08:16 UYST 2016_c','c','f'),
(103,'Sat Dec 10 00:08:56 UYST 2016_d','d','f'),
(104,'Sat Dec 10 00:09:21 UYST 2016_d','d','c'),
(105,'Sat Dec 10 00:26:52 UYST 2016_d','d','b');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
