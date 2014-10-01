-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.19 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных airlinedb
CREATE DATABASE IF NOT EXISTS `airlinedb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `airlinedb`;


-- Дамп структуры для таблица airlinedb.crew
CREATE TABLE IF NOT EXISTS `crew` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_crew_flight1_idx` (`flight_id`),
  KEY `fk_crew_employee1` (`employee_id`),
  CONSTRAINT `fk_crew_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_crew_flight1` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.crew: ~12 rows (приблизительно)
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` (`id`, `flight_id`, `employee_id`) VALUES
	(207, 400, 1),
	(208, 400, 2),
	(211, 403, 6),
	(212, 403, 8),
	(213, 402, 13),
	(214, 402, 65),
	(215, 404, 13),
	(216, 404, 65),
	(217, 405, 1),
	(218, 405, 2),
	(219, 408, 67),
	(220, 408, 68);
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;


-- Дамп структуры для таблица airlinedb.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `position_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`position_id`),
  KEY `fk_employee_position1_idx` (`position_id`),
  CONSTRAINT `fk_employee_position1` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.employee: ~29 rows (приблизительно)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `name`, `surname`, `position_id`, `status`) VALUES
	(1, 'Петр', 'Афанасьев', 1, 0),
	(2, 'Борис', 'Бармалеев', 1, 0),
	(3, 'Светлана', 'Бизонова', 4, 0),
	(4, 'Катерина', 'Мотовская', 3, 0),
	(5, 'Зураб', 'Юсупов', 4, 0),
	(6, 'Денис', 'Сидоров', 1, 1),
	(7, 'Евгений', 'Кот', 2, 0),
	(8, 'Григорий', 'Козлов', 1, 1),
	(9, 'Анастасия', 'Кисова', 4, 0),
	(10, 'Дарья', 'Котова', 4, 0),
	(11, 'Константин', 'Шарапов', 2, 0),
	(12, 'Olga', 'Мурзина', 3, 0),
	(13, 'Ольга', 'Тихонова', 1, 0),
	(38, 'Ольга', 'Тарасова', 4, 0),
	(48, 'Ольга', 'Дорн', 2, 0),
	(63, 'Дарья', 'Шарапова', 4, 0),
	(64, 'Елена', 'Ершикова', 4, 0),
	(65, 'Ирина', 'Зайцева', 1, 0),
	(66, 'Инга', 'Возникова', 4, 0),
	(67, 'Алексей', 'Гастелло', 1, 1),
	(68, 'Раиса', 'Захарова', 1, 1),
	(69, 'Раиса', 'Захарова', 3, 0),
	(70, 'Игорь', 'Селиванов', 1, 0),
	(71, 'Афанасий', 'Яконов', 1, 0),
	(72, 'Алексей', 'Cвитлаков', 2, 0),
	(73, 'Геогий', 'Шваюк', 1, 0),
	(74, 'Андрей', 'Кожаненко', 1, 0),
	(75, 'Анастасия', 'Глуховская', 3, 0),
	(76, 'Алексей', 'Ермолов', 1, 0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


-- Дамп структуры для таблица airlinedb.flight
CREATE TABLE IF NOT EXISTS `flight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `from` varchar(45) NOT NULL,
  `to` varchar(45) NOT NULL,
  `plane_staff_plane` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`plane_staff_plane`),
  KEY `fk_flight_plane1_idx` (`plane_staff_plane`),
  CONSTRAINT `fk_flight_plane1` FOREIGN KEY (`plane_staff_plane`) REFERENCES `plane_staff` (`plane`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=299114 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.flight: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` (`id`, `date`, `from`, `to`, `plane_staff_plane`, `status`) VALUES
	(400, '2014-09-11', 'Лондон', 'Берлин', 3, 2),
	(402, '2014-09-24', 'Москва', 'Берлин', 3, 2),
	(403, '2014-09-09', 'Париж', 'Чикаго', 3, 1),
	(404, '2014-09-02', 'Гродно', 'Берлин', 3, 2),
	(405, '2014-09-25', 'Витебск', 'Брест', 3, 2),
	(406, '2014-09-11', 'Гродно', 'Витебск', 3, 0),
	(408, '2014-09-09', 'Гродно', 'Чикаго', 3, 1);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;


-- Дамп структуры для таблица airlinedb.plane_staff
CREATE TABLE IF NOT EXISTS `plane_staff` (
  `plane` int(11) NOT NULL AUTO_INCREMENT,
  `pilot` int(11) NOT NULL DEFAULT '1',
  `navigator` int(11) NOT NULL DEFAULT '1',
  `radioman` int(11) NOT NULL DEFAULT '1',
  `steward` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`plane`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.plane_staff: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `plane_staff` DISABLE KEYS */;
INSERT INTO `plane_staff` (`plane`, `pilot`, `navigator`, `radioman`, `steward`) VALUES
	(1, 2, 1, 1, 1),
	(2, 2, 1, 0, 2),
	(3, 2, 0, 0, 0);
/*!40000 ALTER TABLE `plane_staff` ENABLE KEYS */;


-- Дамп структуры для таблица airlinedb.position
CREATE TABLE IF NOT EXISTS `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.position: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`, `position`) VALUES
	(1, 'pilot'),
	(2, 'navigator'),
	(3, 'radioman'),
	(4, 'steward');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


-- Дамп структуры для таблица airlinedb.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.role: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role`) VALUES
	(1, 'admin'),
	(2, 'dispatcher');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Дамп структуры для таблица airlinedb.user
CREATE TABLE IF NOT EXISTS `user` (
  `login` varchar(12) NOT NULL,
  `pass` varchar(12) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`login`,`role_id`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы airlinedb.user: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`login`, `pass`, `name`, `surname`, `role_id`) VALUES
	('admin', 'admin', 'Сергей', 'Земляков', 1),
	('dispatcher', 'dispatcher', 'Василий', 'Пупкин', 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
