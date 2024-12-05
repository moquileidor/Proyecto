-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 05-12-2024 a las 22:00:52
-- Versión del servidor: 8.3.0
-- Versión de PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

DROP TABLE IF EXISTS `articulos`;
CREATE TABLE IF NOT EXISTS `articulos` (
  `codigo_articulo` int NOT NULL,
  `nombre_articulo` varchar(50) NOT NULL,
  `cantidad_articulo` int NOT NULL,
  `descripcion_articulo` varchar(255) NOT NULL,
  `codigo_habitacion` int DEFAULT NULL,
  PRIMARY KEY (`codigo_articulo`),
  KEY `fk_codigo_habitacion` (`codigo_habitacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`codigo_articulo`, `nombre_articulo`, `cantidad_articulo`, `descripcion_articulo`, `codigo_habitacion`) VALUES
(4, 'Mesa', 5, 'Madera', 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

DROP TABLE IF EXISTS `citas`;
CREATE TABLE IF NOT EXISTS `citas` (
  `id_cita` int NOT NULL AUTO_INCREMENT,
  `id_paciente` int NOT NULL,
  `nombre_paciente` varchar(250) NOT NULL,
  `id_medico` int NOT NULL,
  `nombre_medico` varchar(250) NOT NULL,
  `fecha_cita` datetime DEFAULT NULL,
  PRIMARY KEY (`id_cita`),
  KEY `id_paciente` (`id_paciente`),
  KEY `nombre_paciente` (`nombre_paciente`),
  KEY `id_medico` (`id_medico`),
  KEY `nombre_medico` (`nombre_medico`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `citas`
--

INSERT INTO `citas` (`id_cita`, `id_paciente`, `nombre_paciente`, `id_medico`, `nombre_medico`, `fecha_cita`) VALUES
(18, 2003, 'Cristiano Ronaldo', 32, 'Candido', '2024-12-17 13:10:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitaciones`
--

DROP TABLE IF EXISTS `habitaciones`;
CREATE TABLE IF NOT EXISTS `habitaciones` (
  `num_habitacion` int NOT NULL,
  `tipo_Habitacion` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `estado_Habitacion` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`num_habitacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `habitaciones`
--

INSERT INTO `habitaciones` (`num_habitacion`, `tipo_Habitacion`, `estado_Habitacion`) VALUES
(24, 'Individual', 'Ocupada'),
(56, 'Compartida', 'Disponible'),
(98, 'Compartida', 'Disponible'),
(200, 'Individual', 'Ocupada'),
(300, 'Individual', 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingresos_pacientes`
--

DROP TABLE IF EXISTS `ingresos_pacientes`;
CREATE TABLE IF NOT EXISTS `ingresos_pacientes` (
  `id_ingreso` int NOT NULL AUTO_INCREMENT,
  `id_paciente` int NOT NULL,
  `ciudad_domicilio` varchar(100) NOT NULL,
  `motivo_hospitalizacion` varchar(255) NOT NULL,
  `tiene_acompanante` tinyint(1) NOT NULL,
  `fecha_ingreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `num_habitacion` int NOT NULL,
  PRIMARY KEY (`id_ingreso`),
  KEY `fk_id_paciente` (`id_paciente`),
  KEY `fk_num_habitacion` (`num_habitacion`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ingresos_pacientes`
--

INSERT INTO `ingresos_pacientes` (`id_ingreso`, `id_paciente`, `ciudad_domicilio`, `motivo_hospitalizacion`, `tiene_acompanante`, `fecha_ingreso`, `num_habitacion`) VALUES
(9, 123456789, 'Cartagena', 'E', 0, '2024-11-29 20:02:41', 1),
(10, 1, 'Bogota', 'Migraña', 0, '2024-11-29 20:13:36', 300);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

DROP TABLE IF EXISTS `medicos`;
CREATE TABLE IF NOT EXISTS `medicos` (
  `id_medico` int NOT NULL,
  `nombre_medico` varchar(50) NOT NULL,
  `edad_medico` int NOT NULL,
  `telefono_medico` bigint NOT NULL,
  `fecha_nacimiento_medico` varchar(50) NOT NULL,
  PRIMARY KEY (`id_medico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `medicos`
--

INSERT INTO `medicos` (`id_medico`, `nombre_medico`, `edad_medico`, `telefono_medico`, `fecha_nacimiento_medico`) VALUES
(0, 'Dana', 23, 3448987, '2001-11-01'),
(32, 'Candido', 59, 23, '1965-06-15'),
(987, 'jjj', 9, 8888888, '1926-12-06'),
(12345, 'Messi', 34, 3001234567, '2024-11-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE IF NOT EXISTS `pacientes` (
  `id_paciente` int NOT NULL,
  `nombre_paciente` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `edad_paciente` int NOT NULL,
  `direccion_paciente` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telefono_paciente` bigint NOT NULL,
  `eps_paciente` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id_paciente`, `nombre_paciente`, `edad_paciente`, `direccion_paciente`, `telefono_paciente`, `eps_paciente`) VALUES
(0, 'Alfredo', 20, 'La paz', 22222, 'ddd'),
(1, 'Stiven Perez', 37, 'Ciruelos', 32343, 'Sura'),
(2003, 'Cristiano Ronaldo', 20, 'Moreira', 3043245, 'Sura'),
(123456789, 'Mauricio', 45, 'Calle 123 #45-67, Medellín', 1234567890, 'Salud Total'),
(1001052078, 'Maradona', 21, 'Bello', 3022739834, 'Nueva EPS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes_servicios`
--

DROP TABLE IF EXISTS `pacientes_servicios`;
CREATE TABLE IF NOT EXISTS `pacientes_servicios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_paciente` varchar(250) NOT NULL,
  `nombre_servicio` varchar(250) NOT NULL,
  `precio_servicio` decimal(10,2) DEFAULT NULL,
  `id_paciente` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nombre_paciente` (`nombre_paciente`),
  KEY `nombre_servicio` (`nombre_servicio`),
  KEY `fk_paciente` (`id_paciente`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pacientes_servicios`
--

INSERT INTO `pacientes_servicios` (`id`, `nombre_paciente`, `nombre_servicio`, `precio_servicio`, `id_paciente`) VALUES
(5, 'Juan Pérez', 'Consulta General', 10000.00, 0),
(6, 'Juan Pérez', 'Consulta General', 10000.00, 0),
(7, 'Juan Pérez', 'Consulta Domiciliaria', 30000.00, 0),
(8, 'Juan Pérez', 'Consulta General', 10000.00, 0),
(9, 'Jorge ', 'Consulta Especializada', 20000.00, 0),
(10, 'Mauro', 'Consulta Especializada', 20000.00, 1),
(11, 'Pepito', 'Consulta Especializada', 20000.00, 123456789),
(12, 'Pepito', 'Consulta General', 10000.00, 123456789),
(13, 'Cristiano Ronaldo', 'Consulta General', 10000.00, 2003);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas_pacientes`
--

DROP TABLE IF EXISTS `salidas_pacientes`;
CREATE TABLE IF NOT EXISTS `salidas_pacientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_paciente` int NOT NULL,
  `nombre_paciente` varchar(250) NOT NULL,
  `detalle_cobro` varchar(255) NOT NULL,
  `total_a_pagar` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_paciente` (`id_paciente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `salidas_pacientes`
--

INSERT INTO `salidas_pacientes` (`id`, `id_paciente`, `nombre_paciente`, `detalle_cobro`, `total_a_pagar`) VALUES
(1, 1, 'Mauro', 'Consulta Especializada', 20000),
(2, 123456789, 'Pepito', 'Consulta Especializada, Consulta General', 30000),
(3, 123456789, 'Pepito', 'Consulta Especializada, Consulta General', 30000),
(4, 123456789, 'Pepito', 'Consulta Especializada, Consulta General', 30000),
(5, 1, 'Mauro', 'Consulta Especializada', 20000),
(6, 1001052078, 'Maradona', '', 0),
(7, 0, 'Alfredo', 'Consulta General, Consulta General, Consulta Domiciliaria, Consulta General, Consulta Especializada', 80000),
(8, 1, 'Stiven Perez', 'Consulta Especializada', 20000),
(9, 123456789, 'Mauricio', 'Consulta Especializada, Consulta General', 30000),
(10, 1001052078, 'Maradona', '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

DROP TABLE IF EXISTS `servicios`;
CREATE TABLE IF NOT EXISTS `servicios` (
  `id_servicio` int NOT NULL,
  `nombre_servicio` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `precio_servicio` double NOT NULL,
  PRIMARY KEY (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id_servicio`, `nombre_servicio`, `precio_servicio`) VALUES
(1, 'Consulta General', 10000),
(2, 'Consulta Especializada', 20000),
(3, 'Consulta Domiciliaria', 30000);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD CONSTRAINT `fk_codigo_habitacion` FOREIGN KEY (`codigo_habitacion`) REFERENCES `habitaciones` (`num_habitacion`) ON DELETE SET NULL;

--
-- Filtros para la tabla `citas`
--
ALTER TABLE `citas`
  ADD CONSTRAINT `fk_citas_medico` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_citas_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
