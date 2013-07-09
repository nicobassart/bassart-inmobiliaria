-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: inmobiliaria
-- ------------------------------------------------------
-- Server version	5.5.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `inmobiliaria`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `inmobiliaria` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `inmobiliaria`;

--
-- Table structure for table `alquileres`
--

DROP TABLE IF EXISTS `alquileres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alquileres` (
  `idalquiler` int(11) NOT NULL,
  `idPersonaDueno` int(11) DEFAULT NULL,
  `idPersonaInquilino` int(11) DEFAULT NULL,
  `idPersonaGarante` int(11) DEFAULT NULL,
  `idInmueble` int(11) DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `importeDeposito` int(11) DEFAULT NULL,
  `porcentajeDueno` int(11) DEFAULT NULL,
  `gas` tinyint(1) DEFAULT NULL,
  `internet` tinyint(1) DEFAULT NULL,
  `agua` tinyint(1) DEFAULT NULL,
  `cable` tinyint(1) DEFAULT NULL,
  `abl` tinyint(1) DEFAULT NULL,
  `telefono` tinyint(1) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idalquiler`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquileres`
--

LOCK TABLES `alquileres` WRITE;
/*!40000 ALTER TABLE `alquileres` DISABLE KEYS */;
/*!40000 ALTER TABLE `alquileres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `alquileres_inmueble`
--

DROP TABLE IF EXISTS `alquileres_inmueble`;
/*!50001 DROP VIEW IF EXISTS `alquileres_inmueble`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `alquileres_inmueble` (
  `idPersonaInquilino` int(11),
  `idinmueble` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fechaInicioContrato` datetime,
  `estado` varchar(1),
  `fechaFinContrato` datetime,
  `calle` varchar(45),
  `calleNro` varchar(45),
  `callePiso` varchar(45),
  `calleDpto` varchar(45)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `alquileres_inmueble_persona`
--

DROP TABLE IF EXISTS `alquileres_inmueble_persona`;
/*!50001 DROP VIEW IF EXISTS `alquileres_inmueble_persona`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `alquileres_inmueble_persona` (
  `idPersonaInquilino` int(11),
  `idinmueble` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fechaInicioContrato` datetime,
  `estado` varchar(1),
  `fechaFinContrato` datetime,
  `calle` varchar(45),
  `calleNro` varchar(45),
  `callePiso` varchar(45),
  `calleDpto` varchar(45),
  `idpersona` int(11),
  `nombre` varchar(45),
  `apellido` varchar(45),
  `tipoDoc` varchar(3),
  `nroDoc` int(9),
  `pe_discriminador` varchar(2),
  `tel_fijo` varchar(45),
  `tel_celular` varchar(45),
  `tel_opcional` varchar(45),
  `email` varchar(45)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_inquilinos`
--

DROP TABLE IF EXISTS `cuotas_inquilinos`;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_inquilinos` (
  `fecha` datetime,
  `mesanio` int(11),
  `idPersonaInquilino` int(11),
  `valor` decimal(11,2),
  `idinmueble` int(11),
  `cuotaId` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_inquilinos_persona`
--

DROP TABLE IF EXISTS `cuotas_inquilinos_persona`;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos_persona`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_inquilinos_persona` (
  `nombre` varchar(45),
  `apellido` varchar(45),
  `mesanio` int(11),
  `valor` decimal(11,2),
  `idinmueble` int(11),
  `cuotaId` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fecha` datetime,
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_inquilinos_persona_inmueble`
--

DROP TABLE IF EXISTS `cuotas_inquilinos_persona_inmueble`;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos_persona_inmueble`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_inquilinos_persona_inmueble` (
  `nombre` varchar(45),
  `apellido` varchar(45),
  `mesanio` int(11),
  `valor` decimal(11,2),
  `calle` varchar(45),
  `calleNro` varchar(45),
  `callePiso` varchar(45),
  `calleDpto` varchar(45),
  `idinmueble` int(11),
  `idcuota` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fecha` datetime,
  `idPersonaDueno` varchar(45),
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_inquilinos_persona_inmueble_dueno`
--

DROP TABLE IF EXISTS `cuotas_inquilinos_persona_inmueble_dueno`;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos_persona_inmueble_dueno`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_inquilinos_persona_inmueble_dueno` (
  `nombre` varchar(45),
  `apellido` varchar(45),
  `nombreDueno` varchar(45),
  `apellidoDueno` varchar(45),
  `mesanio` int(11),
  `valor` decimal(11,2),
  `calle` varchar(45),
  `calleNro` varchar(45),
  `callePiso` varchar(45),
  `calleDpto` varchar(45),
  `idinmueble` int(11),
  `idcuota` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `idPersonaDueno` varchar(45),
  `fecha` datetime,
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_pagas_inquilinos`
--

DROP TABLE IF EXISTS `cuotas_pagas_inquilinos`;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_pagas_inquilinos` (
  `fecha` datetime,
  `mesanio` int(11),
  `idPersonaInquilino` int(11),
  `valor` decimal(11,2),
  `idinmueble` int(11),
  `cuotaId` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_pagas_inquilinos_persona`
--

DROP TABLE IF EXISTS `cuotas_pagas_inquilinos_persona`;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos_persona`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_pagas_inquilinos_persona` (
  `nombre` varchar(45),
  `apellido` varchar(45),
  `mesanio` int(11),
  `valor` decimal(11,2),
  `idinmueble` int(11),
  `cuotaId` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fecha` datetime,
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_pagas_inquilinos_persona_inmueble`
--

DROP TABLE IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble`;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_pagas_inquilinos_persona_inmueble` (
  `nombre` varchar(45),
  `apellido` varchar(45),
  `mesanio` int(11),
  `valor` decimal(11,2),
  `calle` varchar(45),
  `calleNro` varchar(45),
  `callePiso` varchar(45),
  `calleDpto` varchar(45),
  `idinmueble` int(11),
  `idcuota` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `fecha` datetime,
  `idPersonaDueno` varchar(45),
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `cuotas_pagas_inquilinos_persona_inmueble_dueno`
--

DROP TABLE IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble_dueno`;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble_dueno`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cuotas_pagas_inquilinos_persona_inmueble_dueno` (
  `nombre` varchar(45),
  `apellido` varchar(45),
  `nombreDueno` varchar(45),
  `apellidoDueno` varchar(45),
  `mesanio` int(11),
  `valor` decimal(11,2),
  `calle` varchar(45),
  `calleNro` varchar(45),
  `callePiso` varchar(45),
  `calleDpto` varchar(45),
  `idinmueble` int(11),
  `idcuota` int(11),
  `idalquiler` int(11),
  `porcentajeDueno` int(11),
  `idPersonaDueno` varchar(45),
  `fecha` datetime,
  `fechaInicioContrato` datetime,
  `fechaFinContrato` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `cuotasinquilino`
--

DROP TABLE IF EXISTS `cuotasinquilino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuotasinquilino` (
  `idcuotasinquilino` int(11) NOT NULL,
  `mesanio` int(11) DEFAULT NULL,
  `porcetaje` int(11) DEFAULT NULL,
  `valor` decimal(11,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `idalquiler` int(11) DEFAULT NULL,
  `idpago` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcuotasinquilino`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Contiene el aumento de los alquieleres en el periodoque dure';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotasinquilino`
--

LOCK TABLES `cuotasinquilino` WRITE;
/*!40000 ALTER TABLE `cuotasinquilino` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuotasinquilino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inmueble`
--

DROP TABLE IF EXISTS `inmueble`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inmueble` (
  `idinmueble` int(11) NOT NULL,
  `idPersonaDueno` varchar(45) DEFAULT NULL,
  `calleNro` varchar(45) DEFAULT NULL,
  `callePiso` varchar(45) DEFAULT NULL,
  `calleDpto` varchar(45) DEFAULT NULL,
  `calle` varchar(45) DEFAULT NULL,
  `provincia` int(11) DEFAULT NULL,
  `localidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idinmueble`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inmueble`
--

LOCK TABLES `inmueble` WRITE;
/*!40000 ALTER TABLE `inmueble` DISABLE KEYS */;
/*!40000 ALTER TABLE `inmueble` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `idpago` int(11) NOT NULL,
  `fechaPago` datetime DEFAULT NULL,
  `importe` varchar(45) DEFAULT NULL,
  `nota` varchar(60) DEFAULT NULL,
  `importeAbl` varchar(45) DEFAULT NULL,
  `importeGas` varchar(45) DEFAULT NULL,
  `importeInternet` varchar(45) DEFAULT NULL,
  `importeTelefono` varchar(45) DEFAULT NULL,
  `importeAgua` varchar(45) DEFAULT NULL,
  `importeCable` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpago`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `tipoDoc` varchar(3) DEFAULT NULL,
  `nroDoc` int(9) DEFAULT NULL,
  `pe_discriminador` varchar(2) DEFAULT NULL,
  `tel_fijo` varchar(45) DEFAULT NULL,
  `tel_celular` varchar(45) DEFAULT NULL,
  `tel_opcional` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `idprovincias` int(2) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (0,'BUENOS AIRES'),(1,'CATAMARCA'),(2,'CHACO'),(3,'CHUBUT'),(4,'CORDOBA'),(5,'CORRIENTES'),(6,'ENTRE RIOS'),(7,'FORMOSA'),(8,'JUJUY'),(9,'LA PAMPA'),(10,'LA RIOJA'),(11,'MENDOZA'),(12,'MISIONES'),(13,'NEUQUEN'),(14,'RIO NEGRO'),(15,'SALTA'),(16,'SAN JUAN'),(17,'SAN LUIS'),(18,'SANTA CRUZ'),(19,'SANTA FE'),(20,'SANTIAGO DEL ESTERO'),(21,'TIERRA DEL FUEGO'),(22,'TUCUMAN');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'inmobiliaria'
--

--
-- Current Database: `inmobiliaria`
--

USE `inmobiliaria`;

--
-- Final view structure for view `alquileres_inmueble`
--

/*!50001 DROP TABLE IF EXISTS `alquileres_inmueble`*/;
/*!50001 DROP VIEW IF EXISTS `alquileres_inmueble`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `alquileres_inmueble` AS select `alq`.`idPersonaInquilino` AS `idPersonaInquilino`,`alq`.`idInmueble` AS `idinmueble`,`alq`.`idalquiler` AS `idalquiler`,`alq`.`porcentajeDueno` AS `porcentajeDueno`,`alq`.`fechaInicio` AS `fechaInicioContrato`,`alq`.`estado` AS `estado`,`alq`.`fechaFin` AS `fechaFinContrato`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto` from (`alquileres` `alq` left join `inmueble` `inm` on((`alq`.`idInmueble` = `inm`.`idinmueble`))) where (`inm`.`idinmueble` = `alq`.`idInmueble`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `alquileres_inmueble_persona`
--

/*!50001 DROP TABLE IF EXISTS `alquileres_inmueble_persona`*/;
/*!50001 DROP VIEW IF EXISTS `alquileres_inmueble_persona`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `alquileres_inmueble_persona` AS select `v1`.`idPersonaInquilino` AS `idPersonaInquilino`,`v1`.`idinmueble` AS `idinmueble`,`v1`.`idalquiler` AS `idalquiler`,`v1`.`porcentajeDueno` AS `porcentajeDueno`,`v1`.`fechaInicioContrato` AS `fechaInicioContrato`,`v1`.`estado` AS `estado`,`v1`.`fechaFinContrato` AS `fechaFinContrato`,`v1`.`calle` AS `calle`,`v1`.`calleNro` AS `calleNro`,`v1`.`callePiso` AS `callePiso`,`v1`.`calleDpto` AS `calleDpto`,`persona`.`idpersona` AS `idpersona`,`persona`.`nombre` AS `nombre`,`persona`.`apellido` AS `apellido`,`persona`.`tipoDoc` AS `tipoDoc`,`persona`.`nroDoc` AS `nroDoc`,`persona`.`pe_discriminador` AS `pe_discriminador`,`persona`.`tel_fijo` AS `tel_fijo`,`persona`.`tel_celular` AS `tel_celular`,`persona`.`tel_opcional` AS `tel_opcional`,`persona`.`email` AS `email` from (`alquileres_inmueble` `v1` left join `persona` on((`persona`.`idpersona` = `v1`.`idPersonaInquilino`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_inquilinos`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_inquilinos`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_inquilinos` AS select `cuo`.`fecha` AS `fecha`,`cuo`.`mesanio` AS `mesanio`,`alq`.`idPersonaInquilino` AS `idPersonaInquilino`,`cuo`.`valor` AS `valor`,`alq`.`idInmueble` AS `idinmueble`,`cuo`.`idcuotasinquilino` AS `cuotaId`,`alq`.`idalquiler` AS `idalquiler`,`alq`.`porcentajeDueno` AS `porcentajeDueno`,`alq`.`fechaInicio` AS `fechaInicioContrato`,`alq`.`fechaFin` AS `fechaFinContrato` from (`alquileres` `alq` join `cuotasinquilino` `cuo`) where ((now() between `alq`.`fechaInicio` and `alq`.`fechaFin`) and (`cuo`.`idalquiler` = `alq`.`idalquiler`) and isnull(`cuo`.`idpago`) and (`alq`.`estado` = 'A')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_inquilinos_persona`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_inquilinos_persona`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos_persona`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_inquilinos_persona` AS select `p`.`nombre` AS `nombre`,`p`.`apellido` AS `apellido`,`alq1`.`mesanio` AS `mesanio`,`alq1`.`valor` AS `valor`,`alq1`.`idinmueble` AS `idinmueble`,`alq1`.`cuotaId` AS `cuotaId`,`alq1`.`idalquiler` AS `idalquiler`,`alq1`.`porcentajeDueno` AS `porcentajeDueno`,`alq1`.`fecha` AS `fecha`,`alq1`.`fechaInicioContrato` AS `fechaInicioContrato`,`alq1`.`fechaFinContrato` AS `fechaFinContrato` from (`cuotas_inquilinos` `alq1` left join `persona` `p` on((`alq1`.`idPersonaInquilino` = `p`.`idpersona`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_inquilinos_persona_inmueble`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_inquilinos_persona_inmueble`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos_persona_inmueble`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_inquilinos_persona_inmueble` AS select `alq2`.`nombre` AS `nombre`,`alq2`.`apellido` AS `apellido`,`alq2`.`mesanio` AS `mesanio`,`alq2`.`valor` AS `valor`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto`,`alq2`.`idinmueble` AS `idinmueble`,`alq2`.`cuotaId` AS `idcuota`,`alq2`.`idalquiler` AS `idalquiler`,`alq2`.`porcentajeDueno` AS `porcentajeDueno`,`alq2`.`fecha` AS `fecha`,`inm`.`idPersonaDueno` AS `idPersonaDueno`,`alq2`.`fechaInicioContrato` AS `fechaInicioContrato`,`alq2`.`fechaFinContrato` AS `fechaFinContrato` from (`cuotas_inquilinos_persona` `alq2` left join `inmueble` `inm` on((`alq2`.`idinmueble` = `inm`.`idinmueble`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_inquilinos_persona_inmueble_dueno`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_inquilinos_persona_inmueble_dueno`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_inquilinos_persona_inmueble_dueno`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_inquilinos_persona_inmueble_dueno` AS select `inm`.`nombre` AS `nombre`,`inm`.`apellido` AS `apellido`,`perd`.`nombre` AS `nombreDueno`,`perd`.`apellido` AS `apellidoDueno`,`inm`.`mesanio` AS `mesanio`,`inm`.`valor` AS `valor`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto`,`inm`.`idinmueble` AS `idinmueble`,`inm`.`idcuota` AS `idcuota`,`inm`.`idalquiler` AS `idalquiler`,`inm`.`porcentajeDueno` AS `porcentajeDueno`,`inm`.`idPersonaDueno` AS `idPersonaDueno`,`inm`.`fecha` AS `fecha`,`inm`.`fechaInicioContrato` AS `fechaInicioContrato`,`inm`.`fechaFinContrato` AS `fechaFinContrato` from (`cuotas_inquilinos_persona_inmueble` `inm` left join `persona` `perd` on((`inm`.`idPersonaDueno` = `perd`.`idpersona`))) where (`inm`.`fecha` <= now()) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_pagas_inquilinos`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_pagas_inquilinos`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_pagas_inquilinos` AS select `cuo`.`fecha` AS `fecha`,`cuo`.`mesanio` AS `mesanio`,`alq`.`idPersonaInquilino` AS `idPersonaInquilino`,`cuo`.`valor` AS `valor`,`alq`.`idInmueble` AS `idinmueble`,`cuo`.`idcuotasinquilino` AS `cuotaId`,`alq`.`idalquiler` AS `idalquiler`,`alq`.`porcentajeDueno` AS `porcentajeDueno`,`alq`.`fechaInicio` AS `fechaInicioContrato`,`alq`.`fechaFin` AS `fechaFinContrato` from (`alquileres` `alq` join `cuotasinquilino` `cuo`) where ((now() between `alq`.`fechaInicio` and `alq`.`fechaFin`) and (`cuo`.`idalquiler` = `alq`.`idalquiler`) and (`cuo`.`idpago` >= 0) and (`alq`.`estado` = 'A')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_pagas_inquilinos_persona`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_pagas_inquilinos_persona`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos_persona`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_pagas_inquilinos_persona` AS select `p`.`nombre` AS `nombre`,`p`.`apellido` AS `apellido`,`alq1`.`mesanio` AS `mesanio`,`alq1`.`valor` AS `valor`,`alq1`.`idinmueble` AS `idinmueble`,`alq1`.`cuotaId` AS `cuotaId`,`alq1`.`idalquiler` AS `idalquiler`,`alq1`.`porcentajeDueno` AS `porcentajeDueno`,`alq1`.`fecha` AS `fecha`,`alq1`.`fechaInicioContrato` AS `fechaInicioContrato`,`alq1`.`fechaFinContrato` AS `fechaFinContrato` from (`cuotas_pagas_inquilinos` `alq1` left join `persona` `p` on((`alq1`.`idPersonaInquilino` = `p`.`idpersona`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_pagas_inquilinos_persona_inmueble`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_pagas_inquilinos_persona_inmueble` AS select `alq2`.`nombre` AS `nombre`,`alq2`.`apellido` AS `apellido`,`alq2`.`mesanio` AS `mesanio`,`alq2`.`valor` AS `valor`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto`,`alq2`.`idinmueble` AS `idinmueble`,`alq2`.`cuotaId` AS `idcuota`,`alq2`.`idalquiler` AS `idalquiler`,`alq2`.`porcentajeDueno` AS `porcentajeDueno`,`alq2`.`fecha` AS `fecha`,`inm`.`idPersonaDueno` AS `idPersonaDueno`,`alq2`.`fechaInicioContrato` AS `fechaInicioContrato`,`alq2`.`fechaFinContrato` AS `fechaFinContrato` from (`cuotas_pagas_inquilinos_persona` `alq2` left join `inmueble` `inm` on((`alq2`.`idinmueble` = `inm`.`idinmueble`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `cuotas_pagas_inquilinos_persona_inmueble_dueno`
--

/*!50001 DROP TABLE IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble_dueno`*/;
/*!50001 DROP VIEW IF EXISTS `cuotas_pagas_inquilinos_persona_inmueble_dueno`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cuotas_pagas_inquilinos_persona_inmueble_dueno` AS select `inm`.`nombre` AS `nombre`,`inm`.`apellido` AS `apellido`,`perd`.`nombre` AS `nombreDueno`,`perd`.`apellido` AS `apellidoDueno`,`inm`.`mesanio` AS `mesanio`,`inm`.`valor` AS `valor`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto`,`inm`.`idinmueble` AS `idinmueble`,`inm`.`idcuota` AS `idcuota`,`inm`.`idalquiler` AS `idalquiler`,`inm`.`porcentajeDueno` AS `porcentajeDueno`,`inm`.`idPersonaDueno` AS `idPersonaDueno`,`inm`.`fecha` AS `fecha`,`inm`.`fechaInicioContrato` AS `fechaInicioContrato`,`inm`.`fechaFinContrato` AS `fechaFinContrato` from (`cuotas_pagas_inquilinos_persona_inmueble` `inm` left join `persona` `perd` on((`inm`.`idPersonaDueno` = `perd`.`idpersona`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-07 21:25:39
