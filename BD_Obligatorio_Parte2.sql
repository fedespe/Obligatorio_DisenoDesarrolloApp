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

/*Table structure for table `administradores` */

DROP TABLE IF EXISTS `administradores`;

CREATE TABLE `administradores` (
  `oid` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `pass` VARCHAR(50) NOT NULL,
  `nombreCompleto` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `administradores` */



/*Table structure for table `jugadores` */

DROP TABLE IF EXISTS `jugadores`;

CREATE TABLE `jugadores` (
  `oid` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `pass` VARCHAR(50) NOT NULL,
  `nombreCompleto` VARCHAR(50) NOT NULL,
  `saldo` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `jugadores` */



/*Table structure for table `parametros` */

DROP TABLE IF EXISTS `parametros`;

CREATE TABLE `parametros` (
  `Nombre` VARCHAR(50) NOT NULL,
  `Valor` INT(11) NOT NULL,
  PRIMARY KEY (`Nombre`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8;

/*Data for the table `parametros` */

INSERT  INTO `parametros`(`Nombre`,`Valor`) VALUES ('oid',100);

/*Table structure for table `partida` */

DROP TABLE IF EXISTS `partida`;

CREATE TABLE `partida` (
  `oid` INT(11) NOT NULL,
  `codPartida` VARCHAR(100) NOT NULL,
  `nombreJugador1` VARCHAR(50) NOT NULL,
  `nombreJugador2` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`codPartida`),
  KEY `FK_Jugador1` (`nombreJugador1`),
  KEY `FK_Jugador2` (`nombreJugador2`),
  CONSTRAINT `FK_Jugador1` FOREIGN KEY (`nombreJugador1`) REFERENCES `jugadores` (`nombre`),
  CONSTRAINT `FK_Jugador2` FOREIGN KEY (`nombreJugador2`) REFERENCES `jugadores` (`nombre`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `partida` */

/*Table structure for table `movimiento` */

DROP TABLE IF EXISTS `movimiento`;

CREATE TABLE `movimiento` (
  `oid` INT(11) NOT NULL,
  `numeroMovimiento` INT(11) NOT NULL AUTO_INCREMENT,
  `codPartida` VARCHAR(100) NOT NULL,
  `ganador` VARCHAR(50) NOT NULL,
  `fecha` DATE NOT NULL,
  `pozo` DECIMAL(12,2) NOT NULL,
  `nombreJugador` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`numeroMovimiento`,`codPartida`),
  KEY `FK_Partida` (`codPartida`),
  KEY `FK_JugadorGanador` (`ganador`),
  KEY `FK_Jugador` (`nombreJugador`),
  CONSTRAINT `FK_Jugador` FOREIGN KEY (`nombreJugador`) REFERENCES `jugadores` (`nombre`),
  CONSTRAINT `FK_JugadorGanador` FOREIGN KEY (`ganador`) REFERENCES `jugadores` (`nombre`),
  CONSTRAINT `FK_Partida` FOREIGN KEY (`codPartida`) REFERENCES `partida` (`codPartida`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `movimiento` */

/*Table structure for table `fichastableromovimiento` */

DROP TABLE IF EXISTS `fichastableromovimiento`;

CREATE TABLE `fichastableromovimiento` (
  `oid` INT(11) NOT NULL,
  `numroFichaJugada` INT(11) NOT NULL AUTO_INCREMENT,
  `codPartida` VARCHAR(100) NOT NULL,
  `numeroMovimiento` INT(11) NOT NULL,
  `valorDer` INT(11) NOT NULL,
  `valorIzq` INT(11) NOT NULL,
  PRIMARY KEY (`codPartida`,`numeroMovimiento`,`numroFichaJugada`),
  KEY `numroFichaJugada` (`numroFichaJugada`),
  CONSTRAINT `fichastableromovimiento_ibfk_1` FOREIGN KEY (`codPartida`, `numeroMovimiento`) REFERENCES `movimiento` (`numeroMovimiento`, `codPartida`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `fichastableromovimiento` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
