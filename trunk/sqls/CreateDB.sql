

delimiter $$

CREATE DATABASE `inmobiliaria` /*!40100 DEFAULT CHARACTER SET utf8 */$$

delimiter $$

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
  PRIMARY KEY (`idalquiler`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `cuotasinquilino` (
  `idcuotasinquilino` int(11) NOT NULL,
  `mesanio` int(11) DEFAULT NULL,
  `porcetaje` int(11) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `idalquiler` int(11) DEFAULT NULL,
  `idpago` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcuotasinquilino`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Contiene el aumento de los alquieleres en el periodoque dure'$$

delimiter $$

CREATE TABLE `inmueble` (
  `idinmueble` int(11) NOT NULL,
  `idPersonaDueno` varchar(45) DEFAULT NULL,
  `calleNro` varchar(45) DEFAULT NULL,
  `callePiso` varchar(45) DEFAULT NULL,
  `calleDpto` varchar(45) DEFAULT NULL,
  `calle` varchar(45) DEFAULT NULL,
  `provincia` int(11) DEFAULT NULL,
  `localidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idinmueble`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `pagos` (
  `idpago` int(11) NOT NULL,
  `idAlquiler` int(11) DEFAULT NULL,
  `fechaPago` datetime DEFAULT NULL,
  `importe` varchar(45) DEFAULT NULL,
  `nota` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idpago`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `tipoDoc` int(2) DEFAULT NULL,
  `nroDoc` int(9) DEFAULT NULL,
  `pe_discriminador` varchar(2) DEFAULT NULL,
  `tel_fijo` varchar(45) DEFAULT NULL,
  `tel_celular` varchar(45) DEFAULT NULL,
  `tel_opcional` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8$$

delimiter $$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cuotas_inquilinos` AS select `cuo`.`mesanio` AS `mesanio`,`alq`.`idPersonaInquilino` AS `idPersonaInquilino`,`cuo`.`valor` AS `valor`,`alq`.`idInmueble` AS `idinmueble`,`cuo`.`idcuotasinquilino` AS `cuotaId`,`alq`.`idalquiler` AS `idalquiler`,`alq`.`porcentajeDueno` AS `porcentajeDueno` from (`alquileres` `alq` join `cuotasinquilino` `cuo`) where ((now() between `alq`.`fechaInicio` and `alq`.`fechaFin`) and (`cuo`.`idalquiler` = `alq`.`idalquiler`) and isnull(`cuo`.`idpago`))$$

delimiter $$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cuotas_inquilinos_persona` AS select `p`.`nombre` AS `nombre`,`p`.`apellido` AS `apellido`,`alq1`.`mesanio` AS `mesanio`,`alq1`.`valor` AS `valor`,`alq1`.`idinmueble` AS `idinmueble`,`alq1`.`cuotaId` AS `cuotaId`,`alq1`.`idalquiler` AS `idalquiler`,`alq1`.`porcentajeDueno` AS `porcentajeDueno` from (`cuotas_inquilinos` `alq1` left join `persona` `p` on((`alq1`.`idPersonaInquilino` = `p`.`idpersona`)))$$

delimiter $$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cuotas_inquilinos_persona_inmueble` AS select `alq2`.`nombre` AS `nombre`,`alq2`.`apellido` AS `apellido`,`alq2`.`mesanio` AS `mesanio`,`alq2`.`valor` AS `valor`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto`,`alq2`.`idinmueble` AS `idinmueble`,`alq2`.`cuotaId` AS `idcuota`,`alq2`.`idalquiler` AS `idalquiler`,`alq2`.`porcentajeDueno` AS `porcentajeDueno`,`inm`.`idPersonaDueno` AS `idPersonaDueno` from (`cuotas_inquilinos_persona` `alq2` left join `inmueble` `inm` on((`alq2`.`idinmueble` = `inm`.`idinmueble`)))$$

delimiter $$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cuotas_inquilinos_persona_inmueble_dueno` AS select `inm`.`nombre` AS `nombre`,`inm`.`apellido` AS `apellido`,`perd`.`nombre` AS `nombreDueno`,`perd`.`apellido` AS `apellidoDueno`,`inm`.`mesanio` AS `mesanio`,`inm`.`valor` AS `valor`,`inm`.`calle` AS `calle`,`inm`.`calleNro` AS `calleNro`,`inm`.`callePiso` AS `callePiso`,`inm`.`calleDpto` AS `calleDpto`,`inm`.`idinmueble` AS `idinmueble`,`inm`.`idcuota` AS `idcuota`,`inm`.`idalquiler` AS `idalquiler`,`inm`.`porcentajeDueno` AS `porcentajeDueno`,`inm`.`idPersonaDueno` AS `idPersonaDueno` from (`cuotas_inquilinos_persona_inmueble` `inm` left join `persona` `perd` on((`inm`.`idPersonaDueno` = `perd`.`idpersona`))) where (`inm`.`mesanio` <= date_format(now(),'%m%X '))$$





