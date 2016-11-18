/*
SQLyog Community v11.52 (32 bit)
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

/*Table structure for table `parametros` */

DROP TABLE IF EXISTS `parametros`;

CREATE TABLE `parametros` (
  `Nombre` varchar(50) NOT NULL,
  `Valor` int(11) NOT NULL,
  PRIMARY KEY (`Nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `parametros` */

insert  into `parametros`(`Nombre`,`Valor`) values ('oid',135);


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

insert  into `jugadores`(`oid`,`nombre`,`pass`,`nombreCompleto`,`saldo`) values (99,'a','a','Alvaro Fernández','668.00'),(98,'b','b','Bruno Díaz','0.00'),(97,'c','c','Carlos González','229.00'),(96,'d','d','Darío Pérez','400.00'),(95,'e','e','Emiliano Lasa','26.00'),(94,'f','f','Federico Speroni','627.00'),(93,'g','g','Gustavo Valverde','150.00'),(92,'h','h','Hugo Collazo','100.00'),(91,'i','i','Ismael Espósito','50.00'),(90,'j','j','Javier Montero','0.00');

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

insert  into `partida`(`oid`,`codPartida`,`nombreJugador1`,`nombreJugador2`) values (133,'Fri Nov 18 11:19:52 UYT 2016_a','a','f'),(134,'Fri Nov 18 11:25:53 UYT 2016_a','a','f');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `movimiento` */

insert  into `movimiento`(`oid`,`numeroMovimiento`,`codPartida`,`ganador`,`fecha`,`pozo`,`nombreJugador`) values (133,1,'Fri Nov 18 11:19:52 UYT 2016_a',NULL,'2016-11-18','200.00','a'),(134,1,'Fri Nov 18 11:25:53 UYT 2016_a',NULL,'2016-11-18','200.00','a'),(133,2,'Fri Nov 18 11:19:52 UYT 2016_a',NULL,'2016-11-18','200.00','a'),(134,2,'Fri Nov 18 11:25:53 UYT 2016_a',NULL,'2016-11-18','200.00','a'),(133,3,'Fri Nov 18 11:19:52 UYT 2016_a','f','2016-11-18','200.00','f'),(134,3,'Fri Nov 18 11:25:53 UYT 2016_a','a','2016-11-18','200.00','f');


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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `fichastableromovimiento` */

insert  into `fichastableromovimiento`(`oid`,`numeroFichaJugada`,`codPartida`,`numeroMovimiento`,`valorDer`,`valorIzq`) values (133,1,'Fri Nov 18 11:19:52 UYT 2016_a',1,1,1),(133,1,'Fri Nov 18 11:19:52 UYT 2016_a',2,1,6),(133,2,'Fri Nov 18 11:19:52 UYT 2016_a',2,1,1),(134,1,'Fri Nov 18 11:25:53 UYT 2016_a',1,6,4),(134,1,'Fri Nov 18 11:25:53 UYT 2016_a',2,4,3),(134,2,'Fri Nov 18 11:25:53 UYT 2016_a',2,6,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
