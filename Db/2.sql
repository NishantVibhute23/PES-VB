/*
SQLyog Community
MySQL - 5.5.33 : Database - vollyball_01022019
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `batch` */

DROP TABLE IF EXISTS `batch`;

CREATE TABLE `batch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `trainer` varchar(255) DEFAULT NULL,
  `medical_officer` varchar(255) DEFAULT NULL,
  `analyzer` varchar(255) DEFAULT NULL,
  `venue` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `age_group` varchar(200) NOT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `batch` */

/*Table structure for table `batchmatchplayer` */

DROP TABLE IF EXISTS `batchmatchplayer`;

CREATE TABLE `batchmatchplayer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batchteam_id` int(11) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `batchteam_id` (`batchteam_id`),
  KEY `trainee_id` (`trainee_id`),
  CONSTRAINT `batchmatchplayer_ibfk_1` FOREIGN KEY (`batchteam_id`) REFERENCES `batchteam` (`id`),
  CONSTRAINT `batchmatchplayer_ibfk_2` FOREIGN KEY (`trainee_id`) REFERENCES `trainee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `batchmatchplayer` */

/*Table structure for table `batchteam` */

DROP TABLE IF EXISTS `batchteam`;

CREATE TABLE `batchteam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `batch_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `batchteam_ibfk_1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `batchteam` */

/*Table structure for table `competition` */

DROP TABLE IF EXISTS `competition`;

CREATE TABLE `competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `venue` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `age_group` varchar(200) NOT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `competition` */

insert  into `competition`(`id`,`name`,`venue`,`start_date`,`end_date`,`age_group`,`isDeleted`) values 
(1,'abc','adsf','2019-02-01','2019-02-13','Open',0);

/*Table structure for table `m_rating` */

DROP TABLE IF EXISTS `m_rating`;

CREATE TABLE `m_rating` (
  `id` int(11) NOT NULL,
  `grade` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_rating` */

insert  into `m_rating`(`id`,`grade`) values 
(1,'Poor'),
(2,'Below Average'),
(3,'Average'),
(4,'Above Average'),
(5,'Excellent');

/*Table structure for table `m_skill_desc_criteria_point` */

DROP TABLE IF EXISTS `m_skill_desc_criteria_point`;

CREATE TABLE `m_skill_desc_criteria_point` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `abbreviation` varchar(20) NOT NULL,
  `skill_desc_criteria_id` int(11) NOT NULL,
  `shortcutId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_desc_criteria_id` (`skill_desc_criteria_id`),
  CONSTRAINT `m_skill_desc_criteria_point_ibfk_1` FOREIGN KEY (`skill_desc_criteria_id`) REFERENCES `m_skills_desc_criteria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_skill_desc_criteria_point` */

insert  into `m_skill_desc_criteria_point`(`id`,`type`,`abbreviation`,`skill_desc_criteria_id`,`shortcutId`) values 
(1,'','JF',1,48),
(2,'','JP',1,50),
(3,'','SF',1,51),
(4,'','SS',1,52),
(5,'','JS',1,49),
(6,'','DC',2,0),
(7,'','C',3,0),
(8,'','L',3,0),
(9,'','1',4,0),
(10,'','5',4,0),
(11,'','6',4,0),
(12,'','1',5,0),
(13,'','2',5,0),
(14,'','3',5,0),
(15,'','4',5,0),
(16,'','5',5,0),
(17,'','6',5,0),
(18,'','5',6,8),
(19,'','4',6,9),
(20,'','3',6,10),
(21,'','2',6,11),
(22,'','OH',7,2),
(23,'','MB',7,3),
(24,'','L',7,0),
(25,'','U',7,4),
(26,'','0',8,0),
(27,'','RC',9,0),
(28,'','SC',9,0),
(29,'','FR',10,0),
(30,'','FM',10,0),
(31,'','FL',10,0),
(32,'','RR',10,43),
(33,'','RM',10,0),
(34,'','RL',10,0),
(35,'','IN',11,36),
(36,'','OT',11,37),
(37,'','BT',11,38),
(38,'','OL',11,39),
(39,'','D',11,40),
(40,'','BC',11,41),
(41,'','4C',12,13),
(42,'','3C',12,14),
(43,'','2C',12,15),
(44,'','5C',12,12),
(45,'','DC',13,0),
(46,'','PP',13,0),
(47,'','SP',13,0),
(48,'','MB',13,0),
(49,'','BP',13,0),
(50,'','NSE',13,0),
(51,'','NEL',13,0),
(52,'','LOW',14,16),
(53,'','MEDIUM',14,17),
(54,'','HIGH',14,18),
(55,'','1',15,0),
(56,'','2',15,0),
(57,'','3',15,0),
(58,'','4',15,0),
(59,'','5',15,0),
(60,'','6',15,0),
(61,'','1',16,0),
(62,'','2',16,0),
(63,'','3',16,0),
(64,'','4',16,0),
(65,'','5',16,0),
(66,'','6',16,0),
(67,'','K1',17,19),
(68,'','K2',17,20),
(69,'','TPS',17,21),
(70,'','OH',18,22),
(71,'','MB',18,23),
(72,'','U',18,24),
(73,'','S',18,25),
(74,'','SGL',19,26),
(75,'','DBL',19,27),
(76,'','TPL',19,28),
(77,'','1-2-3',20,29),
(78,'','1-3-2',20,30),
(79,'','2-1-2',20,31),
(80,'','2-0-4',20,32),
(81,'','3-1-2',20,33),
(82,'','3-0-3',20,34),
(83,'','3-2-1',20,35),
(84,'','1',21,0),
(85,'','2',21,0),
(86,'','3',21,0),
(87,'','4',21,0),
(88,'','5',21,0),
(89,'','6',21,0),
(90,'','S',22,1),
(91,'','OH',22,2),
(92,'','MB',22,3),
(93,'','L',22,0),
(94,'','U',22,4),
(95,'','00:00',23,0),
(96,'','FR',24,0),
(97,'','FM',24,0),
(98,'','FL',24,0),
(99,'','RR',24,43),
(100,'','RM',24,0),
(101,'','RL',24,0),
(102,'','C',25,0),
(103,'','L',25,0),
(104,'','Commit',26,42),
(105,'','Read and react',26,43),
(106,'','Stack or zone',26,44),
(107,'','Commit',27,42),
(108,'','Read and react',27,43),
(109,'','Stack or zone',27,44),
(110,'','Kill',28,64),
(111,'','Soft',28,65),
(112,'','LC',28,63),
(113,'','LO',28,62),
(114,'','LI',28,66),
(115,'','CB',28,67),
(116,'','C',29,0),
(117,'','L',29,0),
(118,'','IN',29,36),
(119,'','OT',29,37),
(120,'','BT',29,38),
(121,'','OL',29,39),
(122,'','D',29,40),
(123,'','BC',29,41),
(124,'','4C',30,13),
(125,'','3C',30,14),
(126,'','2C',30,15),
(127,'','5C',30,12),
(128,'','LOW',31,16),
(129,'','MEDIUM',31,17),
(130,'','HIGH',31,18),
(131,'','1',32,0),
(132,'','2',32,0),
(133,'','3',32,0),
(134,'','4',32,0),
(135,'','5',32,0),
(136,'','6',32,0),
(137,'','2',33,0),
(138,'','3',33,0),
(139,'','4',33,0),
(140,'','1',34,0),
(141,'','2',34,0),
(142,'','3',34,0),
(143,'','4',34,0),
(144,'','5',34,0),
(145,'','6',34,0),
(146,'','LOC',34,0),
(147,'','ROC',34,0),
(148,'','BOC',34,0),
(149,'','K1',35,19),
(150,'','K2',35,20),
(151,'','TP',35,21),
(152,'','OH',36,0),
(153,'','MB',36,0),
(154,'','U',36,0),
(155,'','S',36,0),
(156,'','SGL',37,26),
(157,'','DBL',37,27),
(158,'','TPL',37,28),
(159,'','1-2-3',38,29),
(160,'','1-3-2',38,30),
(161,'','2-1-2',38,31),
(162,'','2-0-4',38,32),
(163,'','3-1-2',38,33),
(164,'','3-0-3',38,34),
(165,'','3-2-1',38,35),
(166,'','H',39,0),
(167,'','OPP',39,0),
(168,'','00:00',40,0),
(169,'','FR',41,0),
(170,'','FM',41,0),
(171,'','FL',41,0),
(172,'','RR',41,43),
(173,'','RM',41,0),
(174,'','RL',41,0),
(175,'','JS',42,53),
(176,'','RB',42,54),
(177,'','FP',42,55),
(178,'','HP',42,56),
(179,'','BC',42,57),
(180,'','HIGH',43,18),
(181,'','MEDIUM',43,17),
(182,'','LOW',43,16),
(183,'','ON',44,58),
(184,'','CN',44,59),
(185,'','AN',44,60),
(186,'','LT',44,61),
(187,'','5',45,8),
(188,'','4',45,9),
(189,'','3',45,10),
(190,'','2',45,11),
(191,'','Favourable',46,45),
(192,'','Semi Favourable',46,46),
(193,'','Non Favourable',46,47),
(194,'','1',47,0),
(195,'','2',47,0),
(196,'','3',47,0),
(197,'','4',47,0),
(198,'','5',47,0),
(199,'','6',47,0),
(200,'','1',48,0),
(201,'','2',48,0),
(202,'','3',48,0),
(203,'','4',48,0),
(204,'','5',48,0),
(205,'','6',48,0),
(206,'','4C',49,13),
(207,'','3C',49,14),
(208,'','2C',49,15),
(209,'','5C',49,12),
(210,'','SGL',50,26),
(211,'','DBL',50,27),
(212,'','TPL',50,28),
(213,'','NB',50,84),
(214,'','K1',51,19),
(215,'','K2',51,20),
(216,'','TP',51,21),
(217,'','OH',52,0),
(218,'','MB',52,0),
(219,'','U',52,0),
(220,'','S',52,0),
(221,'','00:00',53,0),
(222,'','JF',54,48),
(223,'','JS',54,49),
(224,'','JP',54,50),
(225,'','SF',54,51),
(226,'','SS',54,52),
(227,'','2',55,11),
(228,'','3',55,10),
(229,'','4',55,9),
(230,'','5',55,8),
(231,'','1',56,0),
(232,'','2',56,0),
(233,'','3',56,0),
(234,'','4',56,0),
(235,'','5',56,0),
(236,'','6',56,0),
(237,'','1',57,0),
(238,'','2',57,0),
(239,'','3',57,0),
(240,'','4',57,0),
(241,'','5',57,0),
(242,'','6',57,0),
(243,'','OH',58,2),
(244,'','MB',58,3),
(245,'','U',58,4),
(246,'','S',58,1),
(247,'','L',58,5),
(248,'','Favourable',59,45),
(249,'','Semi Favourable',59,46),
(250,'','Non Favourable',59,47),
(251,'','ON',60,58),
(252,'','CN',60,59),
(253,'','AN',60,60),
(254,'','LT',60,61),
(265,'','00:00',63,0),
(266,'','FR',64,0),
(267,'','FM',64,0),
(268,'','FL',64,0),
(269,'','RR',64,43),
(270,'','RM',64,0),
(271,'','RL',64,0),
(272,'','IN',65,36),
(273,'','OT',65,37),
(274,'','BT',65,38),
(275,'','OL',65,39),
(276,'','D',65,40),
(277,'','BC',65,41),
(278,'','HIGH',66,18),
(279,'','MEDIUM',66,17),
(280,'','LOW',66,16),
(281,'','5C',67,12),
(282,'','4C',67,13),
(283,'','3C',67,14),
(284,'','2C',67,15),
(285,'','1C',67,72),
(286,'','4',68,0),
(287,'','3',68,0),
(288,'','2',68,0),
(289,'','SGL',69,26),
(290,'','DBL',69,27),
(291,'','TPL',69,28),
(292,'','LO',70,62),
(293,'','LC',70,63),
(294,'','1-2-3',71,29),
(295,'','1-3-2',71,30),
(296,'','2-1-2',71,31),
(297,'','2-0-4',71,32),
(298,'','3-1-2',71,33),
(299,'','3-0-3',71,34),
(300,'','3-2-1',71,35),
(301,'','1',72,0),
(302,'','2',72,0),
(303,'','3',72,0),
(304,'','4',72,0),
(305,'','5',72,0),
(306,'','6',72,0),
(307,'','1',73,0),
(308,'','2',73,0),
(309,'','3',73,0),
(310,'','4',73,0),
(311,'','5',73,0),
(312,'','6',73,0),
(313,'','OH',74,2),
(314,'','MB',74,3),
(315,'','U',74,4),
(316,'','S',74,1),
(317,'','L',74,5),
(318,'','ON',75,58),
(319,'','CN',75,59),
(320,'','AN',75,60),
(321,'','LT',75,61),
(322,'','Favourable',76,45),
(323,'','Semi Favourable',76,46),
(324,'','Non Favourable',76,47),
(325,'','K1',77,19),
(326,'','K2',77,20),
(327,'','TP',77,21),
(328,'','FR',78,0),
(329,'','FM',78,0),
(330,'','FL',78,0),
(331,'','RR',78,43),
(332,'','RM',78,0),
(333,'','RL',78,0),
(341,'','00:00',80,0),
(342,'','C',81,0),
(343,'','L',81,0),
(344,'','',82,0),
(345,'','',83,0),
(346,'','',84,0),
(347,'','',85,0),
(348,'','',86,0),
(349,'','',87,0),
(350,'','C',88,0),
(351,'','L',88,0),
(352,'','ANF',44,70),
(353,'','ANF',60,70),
(355,'','NC',12,71),
(357,'','NC',30,71),
(359,'','nC',49,71),
(361,'','NC',67,71),
(362,'','BT',13,0),
(363,'','R',11,73),
(364,'','BTL',11,74),
(365,'','R',29,73),
(366,'','BTL',29,74),
(367,'','R',65,73),
(368,'','BTL',65,74),
(369,'','R',42,73),
(370,'','R',28,73),
(371,'','2-1-3',20,75),
(372,'','Non Organised',20,76),
(373,'','2-1-3',38,75),
(374,'','Non Organised',38,76),
(375,'','2-1-3',71,75),
(376,'','Non Organised',71,76),
(377,'','C',70,67),
(378,'','S',70,77),
(383,'','ODB',66,87),
(403,'','HA',90,78),
(404,'','HB',90,79),
(405,'','HD',90,80),
(406,'','OA',90,85),
(407,'','1',91,0),
(408,'','2',91,0),
(409,'','3',91,0),
(410,'','4',91,0),
(411,'','5',91,0),
(412,'','6',91,0),
(413,'','LOC',91,0),
(414,'','ROC',91,0),
(415,'','BOC',91,0),
(416,'','1',92,0),
(417,'','2',92,0),
(418,'','3',92,0),
(419,'','4',92,0),
(420,'','5',92,0),
(421,'','6',92,0),
(422,'','1',93,0),
(423,'','2',93,0),
(424,'','3',93,0),
(425,'','4',93,0),
(426,'','5',93,0),
(427,'','6',93,0),
(428,'','C',94,81),
(429,'','S',94,82),
(430,'','D',94,83),
(431,'','NB',19,84),
(432,'','NB',37,84),
(433,'','NB',69,84),
(434,'','1-2-3',95,29),
(435,'','1-3-2',95,30),
(436,'','2-1-2',95,31),
(487,'','2-0-4',95,32),
(488,'','3-1-2',95,33),
(489,'','3-0-3',95,34),
(590,'','3-2-1',95,35),
(591,'','2-1-3',95,75),
(592,'','Non Organised',95,76),
(593,'','OB',90,86),
(594,'','None',26,88),
(595,'','SJ',29,90),
(596,'','OC',60,95),
(597,'','Back Set',42,91),
(598,'','Spot Jump',94,90),
(599,'','OD',90,92),
(600,'','OC',44,94),
(601,'','OC',75,94),
(602,'','TOC',75,95),
(603,'','NA',65,0),
(604,'','NA',66,0),
(605,'','NA',67,0),
(606,'','NA',68,0),
(607,'','NA',69,0),
(608,'','NA',70,0),
(609,'','NA',71,0),
(610,'','NA',95,0);

/*Table structure for table `m_skill_details` */

DROP TABLE IF EXISTS `m_skill_details`;

CREATE TABLE `m_skill_details` (
  `id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `rating_id` int(11) NOT NULL,
  `name` varchar(500) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`),
  KEY `rating_id` (`rating_id`),
  CONSTRAINT `m_skill_details_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `m_skills` (`id`),
  CONSTRAINT `m_skill_details_ibfk_2` FOREIGN KEY (`rating_id`) REFERENCES `m_rating` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_skill_details` */

insert  into `m_skill_details`(`id`,`skill_id`,`rating_id`,`name`,`description`) values 
(1,1,1,'Service Poor',''),
(2,1,2,'Advantage in Pass Reaches to the oppenent Secondary Target',''),
(3,1,3,'Advantage in Pass Reaches to the oppenent Secondary Target',''),
(4,1,4,'Free ball to Service side',''),
(5,1,5,'Ace Serve',''),
(6,2,1,'Service Error',''),
(7,2,2,'Advantage in Pass Reaches to the oppenent Secondary Target',''),
(8,2,3,'Advantage in Dig Reaches to the oppenent Secondary Target',''),
(9,2,4,'Free ball to Attack side',''),
(10,2,5,'Ace Attack',''),
(11,3,1,'Block Error',''),
(12,3,2,'Advantage out Pass Reaches to the oppenent Primary Target. Free ball for opponents. No effective block. Block reflected ball becomes set for opponents',''),
(13,3,3,'Advantage in Dig Reaches to the Secondary Target of Opponent. Blocked out Ball reaches out of court of blockers Side. No perfect Attack',''),
(14,3,4,'Free ball to Block side',''),
(15,3,5,'Kill Block',''),
(16,4,1,'Set Error',''),
(17,4,2,'Advantage out organised Group block situation under easy pass or dig. Non effective set for attacker. Free ball situation to the opponents side',''),
(18,4,3,'Advantage in unorganised Double Block Situation',''),
(19,4,4,'Organised Single block situation',''),
(20,4,5,'No Block Situation. Most favourable set for attacker. Best conversion of poor pass into effective set. Setter act as attacker.',''),
(21,5,1,'Reception Error',''),
(22,5,2,'Advantage in Pass Reaches to the oppenent Secondary Target',''),
(23,5,3,'Advantage out Serve reception reaches secondary target 2 and setter does not execute set for desire attack combination. Only high set attack is possible against serve reception.',''),
(24,5,4,'Serve reception reaches secondary target 1 and setter has limited scope to execute a set for combinations of attack',''),
(25,5,5,'Serve reception reaches primary target and setter has full scope to execute a set for all possible combination of attack. Reception becomes set for setter to attack or imitate attack and perform set. Most favourable situation to organise attack with all possible options.',''),
(26,6,1,'Dig Error',''),
(27,6,2,'Advantage out Free ball situaion to oppenets. Easy dig reaches to the secondary target. Non effective pass',''),
(28,6,3,'Advantage in Easy dig reaches to primary target. Dig become sudden set for attacker',''),
(29,6,4,'Received most difficult ball with extraordinary dig action and ball reaches secondary target. Tactical dig becomes set for attackers.',''),
(30,6,5,'Received most difficult ball extraordinary dig action and ball reached primary target','');

/*Table structure for table `m_skills` */

DROP TABLE IF EXISTS `m_skills`;

CREATE TABLE `m_skills` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_skills` */

insert  into `m_skills`(`id`,`name`) values 
(1,'Service'),
(2,'Attack'),
(3,'Block'),
(4,'Set'),
(5,'Reception'),
(6,'Defence'),
(7,'OP+'),
(8,'TF-'),
(9,'NE');

/*Table structure for table `m_skills_desc_criteria` */

DROP TABLE IF EXISTS `m_skills_desc_criteria`;

CREATE TABLE `m_skills_desc_criteria` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `viewable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `m_skills_desc_criteria_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `m_skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_skills_desc_criteria` */

insert  into `m_skills_desc_criteria`(`id`,`type`,`skill_id`,`viewable`) values 
(1,' Type & Techniques of Service',1,1),
(2,' Serve Tactics',1,0),
(3,' Direction of Service',1,0),
(4,' Serve From Zone',1,0),
(5,' Serve To Zone',1,0),
(6,' Reception formation',1,1),
(7,' Receiver Position',1,0),
(8,' Score at the time of ace serve',1,0),
(9,' Serve in situation',1,0),
(10,' Opponent Setter Position',1,0),
(11,' Type & Techniques of attack',2,1),
(12,' Attack Combination',2,1),
(13,' Attacking Tactics',2,0),
(14,' Attack on Tempo',2,1),
(15,' Attacking From Zone',2,0),
(16,'Attacking To Zone',2,0),
(17,' Attack in phase',2,1),
(18,' Attackers position',2,0),
(19,'No. of Blockers',2,1),
(20,' Opponent Defence Formation',2,1),
(21,' Attack Defended Zone',2,0),
(22,' Defender Role',2,0),
(23,' Score at time of attack',2,0),
(24,' Setter position',2,0),
(25,'Direction of Attack',2,0),
(26,'Type of Block',2,1),
(27,' Type of Block',3,1),
(28,'Technique of Block',3,1),
(29,'Block on Type of Attack',3,1),
(30,' Block on Combination of attack',3,1),
(31,' Block on Attacking Tempo',3,1),
(32,'Opponent Attacking Zone',3,0),
(33,' Blocking Zone',3,0),
(34,'Block Deflected ball at Zone',3,0),
(35,' Blocking in phase',3,1),
(36,' Blockers Position',3,0),
(37,' No. of Blockers',3,1),
(38,' Defence Formation',3,1),
(39,' Block Defended court',3,0),
(40,' Score at time of Block',3,0),
(41,'Opponent Setter Position',3,0),
(42,' Type of Set',4,1),
(43,' Set Tempo',4,1),
(44,' Reception at',4,1),
(45,'Reception Formation',4,1),
(46,'Parabolla of received ball',4,1),
(47,'Set delivery from Zone',4,0),
(48,'Set delivery to Zone',4,0),
(49,'Combination of attack',4,1),
(50,'No. of Blockers',4,1),
(51,'Game of phase',4,1),
(52,'Attackers position',4,0),
(53,'Score at the time of set',4,0),
(54,'Type of Serve',5,1),
(55,'Reception Formation',5,1),
(56,'Reception From Zone',5,0),
(57,'Reception To Zone',5,0),
(58,'Receiver Position',5,0),
(59,'Parabola of Received ball for setter',5,1),
(60,'Reception at',5,1),
(63,'Score at the time of Reception',5,0),
(64,'Setter Position',5,0),
(65,'Type of Attack by opponent',6,1),
(66,'Attack on Tempo',6,1),
(67,'Combination of Attack',6,1),
(68,'Blocking at Zone',6,0),
(69,'No. of Blockers',6,1),
(70,'Block Cover',6,1),
(71,'Defence System ',6,1),
(72,'Defence Sent From Zone',6,0),
(73,'Defence Sent To Zone',6,0),
(74,'Defenders Role',6,0),
(75,'Defence Ball At',6,1),
(76,'Parabola of Defended Ball for Setter',6,1),
(77,' Defence in phase',6,1),
(78,' Setter position',6,0),
(80,' Score at time of Defence',6,0),
(81,'Direction of Attack',6,0),
(82,'Diagram Points',1,0),
(83,'Diagram Points',2,0),
(84,'Diagram Points',3,0),
(85,'Diagram Points',4,0),
(86,'Diagram Points',5,0),
(87,'Diagram Points',6,0),
(88,'Direction Of Block',3,0),
(90,'Type of Defended Ball',6,1),
(91,'Ball Reflected Zone',2,0),
(92,'Attack Approach Run from',2,0),
(93,'Attack Approach Run to',2,0),
(94,'Type of approach run',2,1),
(95,'Attack Cover',6,1);

/*Table structure for table `match_evaluation_set` */

DROP TABLE IF EXISTS `match_evaluation_set`;

CREATE TABLE `match_evaluation_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_evaluation_team_id` int(11) NOT NULL,
  `set_no` int(11) NOT NULL,
  `homescore` int(11) DEFAULT '0',
  `opponentscore` int(11) DEFAULT '0',
  `won_by` int(11) DEFAULT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `evaluator` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_evaluation_team_id` (`match_evaluation_team_id`),
  CONSTRAINT `match_evaluation_set_ibfk_1` FOREIGN KEY (`match_evaluation_team_id`) REFERENCES `match_evaluation_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_set` */

insert  into `match_evaluation_set`(`id`,`match_evaluation_team_id`,`set_no`,`homescore`,`opponentscore`,`won_by`,`start_time`,`end_time`,`evaluator`,`date`) values 
(1,1,1,10,15,2,'16:14:00','16:23:00','Nishant','2019-02-01'),
(2,1,2,19,17,1,'16:17:00','16:18:00','Nishant','2019-02-01'),
(3,1,3,3,3,0,'16:24:00','00:00:00','NIshnat','2019-02-01');

/*Table structure for table `match_evaluation_set_latest_rotation` */

DROP TABLE IF EXISTS `match_evaluation_set_latest_rotation`;

CREATE TABLE `match_evaluation_set_latest_rotation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pos1playerId` int(11) DEFAULT NULL,
  `pos2playerId` int(11) DEFAULT NULL,
  `pos3playerId` int(11) DEFAULT NULL,
  `pos4playerId` int(11) DEFAULT NULL,
  `pos5playerId` int(11) DEFAULT NULL,
  `pos6playerId` int(11) DEFAULT NULL,
  `match_evaluation_set_id` int(11) DEFAULT NULL,
  `pos1OppplayerId` int(11) DEFAULT NULL,
  `pos2OppplayerId` int(11) DEFAULT NULL,
  `pos3OppplayerId` int(11) DEFAULT NULL,
  `pos4OppplayerId` int(11) DEFAULT NULL,
  `pos5OppplayerId` int(11) DEFAULT NULL,
  `pos6OppplayerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_evaluation_set_id` (`match_evaluation_set_id`),
  CONSTRAINT `match_evaluation_set_latest_rotation_ibfk_1` FOREIGN KEY (`match_evaluation_set_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_set_latest_rotation` */

insert  into `match_evaluation_set_latest_rotation`(`id`,`pos1playerId`,`pos2playerId`,`pos3playerId`,`pos4playerId`,`pos5playerId`,`pos6playerId`,`match_evaluation_set_id`,`pos1OppplayerId`,`pos2OppplayerId`,`pos3OppplayerId`,`pos4OppplayerId`,`pos5OppplayerId`,`pos6OppplayerId`) values 
(1,4,5,6,7,2,3,1,12,10,9,11,13,14),
(2,3,4,5,2,6,7,2,12,11,10,14,9,13),
(3,1,2,3,4,5,6,3,10,11,12,13,8,9);

/*Table structure for table `match_evaluation_team` */

DROP TABLE IF EXISTS `match_evaluation_team`;

CREATE TABLE `match_evaluation_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `evaluation_team_id` int(11) NOT NULL,
  `opponent_team_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `match_id` (`match_id`),
  CONSTRAINT `match_evaluation_team_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_team` */

insert  into `match_evaluation_team`(`id`,`match_id`,`evaluation_team_id`,`opponent_team_id`) values 
(1,1,1,2),
(2,1,2,1);

/*Table structure for table `match_players` */

DROP TABLE IF EXISTS `match_players`;

CREATE TABLE `match_players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `team1` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team1` (`team1`),
  KEY `player_id` (`player_id`),
  KEY `match_id` (`match_id`),
  CONSTRAINT `match_players_ibfk_1` FOREIGN KEY (`team1`) REFERENCES `teams` (`id`),
  CONSTRAINT `match_players_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`),
  CONSTRAINT `match_players_ibfk_3` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `match_players` */

insert  into `match_players`(`id`,`match_id`,`team1`,`player_id`) values 
(1,1,1,1),
(2,1,1,2),
(3,1,1,3),
(4,1,1,4),
(5,1,1,5),
(6,1,1,6),
(7,1,1,7),
(8,1,2,8),
(9,1,2,9),
(10,1,2,10),
(11,1,2,11),
(12,1,2,12),
(13,1,2,13),
(14,1,2,14);

/*Table structure for table `matches` */

DROP TABLE IF EXISTS `matches`;

CREATE TABLE `matches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `competition_id` int(11) NOT NULL,
  `team1` int(11) NOT NULL,
  `team2` int(11) NOT NULL,
  `dayNumber` int(11) DEFAULT NULL,
  `matchNumber` int(11) DEFAULT NULL,
  `phase` varchar(50) DEFAULT NULL,
  `place` varchar(500) DEFAULT NULL,
  `won_by` int(11) DEFAULT NULL,
  `isdeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `team1` (`team1`),
  KEY `team2` (`team2`),
  KEY `competition_id` (`competition_id`),
  CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`team1`) REFERENCES `teams` (`id`),
  CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`team2`) REFERENCES `teams` (`id`),
  CONSTRAINT `matches_ibfk_3` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `matches` */

insert  into `matches`(`id`,`name`,`date`,`time`,`competition_id`,`team1`,`team2`,`dayNumber`,`matchNumber`,`phase`,`place`,`won_by`,`isdeleted`) values 
(1,NULL,'2019-02-01','03:04:00',1,1,2,1,1,'Final-','Mumbai',NULL,0);

/*Table structure for table `players` */

DROP TABLE IF EXISTS `players`;

CREATE TABLE `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chest_num` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `position` int(11) DEFAULT NULL,
  `is_captain` int(11) DEFAULT NULL,
  `team_id` int(11) NOT NULL,
  `isdeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `team_id` (`team_id`),
  CONSTRAINT `players_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `players` */

insert  into `players`(`id`,`chest_num`,`name`,`position`,`is_captain`,`team_id`,`isdeleted`) values 
(1,'1','Claudian Fernandes',2,0,1,0),
(2,'2','Stephen Lobo',6,0,1,0),
(3,'3','Sanjay More',3,0,1,0),
(4,'4','Milind Jathar',3,0,1,0),
(5,'5','Amol Chavan',5,0,1,0),
(6,'6','Sanket Chalke',4,1,1,0),
(7,'7','Gajesh Rawal',6,0,1,0),
(8,'1','Bhushan Koli',2,0,2,0),
(9,'2','Sagar Gurav',4,0,2,0),
(10,'3','Satish Kumar',6,1,2,0),
(11,'4','Rohan Katkar',4,0,2,0),
(12,'5','Avinash Maske',5,0,2,0),
(13,'6','Rajendra Dave',6,0,2,0),
(14,'7','Sunil Torse',4,0,2,0);

/*Table structure for table `pool` */

DROP TABLE IF EXISTS `pool`;

CREATE TABLE `pool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `competition_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `competition_id` (`competition_id`),
  CONSTRAINT `pool_ibfk_1` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pool` */

/*Table structure for table `rally` */

DROP TABLE IF EXISTS `rally`;

CREATE TABLE `rally` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `home_score` int(11) DEFAULT '0',
  `opponent_score` int(11) DEFAULT '0',
  `start_time` varchar(50) DEFAULT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `evaluation_id` int(11) NOT NULL,
  `start_by` int(11) DEFAULT NULL,
  `wonby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `evaluation_id` (`evaluation_id`),
  CONSTRAINT `rally_ibfk_1` FOREIGN KEY (`evaluation_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

/*Data for the table `rally` */

insert  into `rally`(`id`,`number`,`home_score`,`opponent_score`,`start_time`,`end_time`,`evaluation_id`,`start_by`,`wonby`) values 
(1,1,1,0,'16:15:01','16:15:06',1,1,1),
(2,2,2,0,'16:15:08','16:15:17',1,1,1),
(3,3,2,1,'16:15:21','16:15:23',1,1,2),
(4,4,2,2,'16:15:24','16:15:43',1,2,2),
(5,5,3,2,'16:15:51','16:15:51',1,2,1),
(6,6,3,3,'16:15:52','16:15:52',1,2,2),
(7,7,3,4,'16:15:59','16:16:17',1,2,2),
(8,8,3,5,'16:16:25','16:16:37',1,2,2),
(9,9,4,5,'16:16:52','16:17:12',1,2,1),
(10,10,5,5,'16:17:22','16:17:22',1,2,1),
(11,11,5,6,'16:17:23','16:17:23',1,2,2),
(12,12,5,7,'16:17:25','16:17:25',1,2,2),
(13,13,5,8,'16:17:26','16:17:26',1,2,2),
(14,14,6,8,'16:17:26','16:17:26',1,2,1),
(15,15,6,9,'16:17:27','16:17:27',1,2,2),
(16,16,7,9,'16:17:27','16:17:27',1,2,1),
(17,17,7,10,'16:17:28','16:17:28',1,2,2),
(18,18,8,10,'16:17:29','16:17:29',1,2,1),
(19,19,8,11,'16:17:30','16:17:30',1,2,2),
(20,20,8,12,'16:17:32','16:17:32',1,2,2),
(21,21,9,12,'16:17:33','16:17:33',1,2,1),
(22,22,10,12,'16:17:34','16:17:34',1,2,1),
(23,23,10,13,'16:17:36','16:17:36',1,2,2),
(24,24,10,14,'16:17:40','16:17:40',1,2,2),
(25,25,10,15,'16:17:41','16:17:41',1,2,2),
(26,1,1,0,'16:18:02','16:18:02',2,2,1),
(27,2,1,1,'16:18:03','16:18:03',2,2,2),
(28,3,2,1,'16:18:04','16:18:04',2,2,1),
(29,4,2,2,'16:18:04','16:18:04',2,2,2),
(30,5,3,2,'16:18:04','16:18:04',2,2,1),
(31,6,3,3,'16:18:05','16:18:05',2,2,2),
(32,7,4,3,'16:18:06','16:18:06',2,2,1),
(33,8,4,4,'16:18:06','16:18:06',2,2,2),
(34,9,5,4,'16:18:07','16:18:07',2,2,1),
(35,10,5,5,'16:18:07','16:18:07',2,2,2),
(36,11,6,5,'16:18:08','16:18:08',2,2,1),
(37,12,6,6,'16:18:08','16:18:08',2,2,2),
(38,13,7,6,'16:18:09','16:18:09',2,2,1),
(39,14,7,7,'16:18:09','16:18:09',2,2,2),
(40,15,8,7,'16:18:11','16:18:11',2,2,1),
(41,16,8,8,'16:18:12','16:18:12',2,2,2),
(42,17,9,8,'16:18:12','16:18:12',2,2,1),
(43,18,9,9,'16:18:13','16:18:13',2,2,2),
(44,19,10,9,'16:18:14','16:18:14',2,2,1),
(45,20,10,10,'16:18:14','16:18:14',2,2,2),
(46,21,11,10,'16:18:15','16:18:15',2,2,1),
(47,22,11,11,'16:18:15','16:18:15',2,2,2),
(48,23,12,11,'16:18:16','16:18:16',2,2,1),
(49,24,12,12,'16:18:16','16:18:16',2,2,2),
(50,25,13,12,'16:18:17','16:18:17',2,2,1),
(51,26,13,13,'16:18:17','16:18:17',2,2,2),
(52,27,14,13,'16:18:18','16:18:18',2,2,1),
(53,28,14,14,'16:18:18','16:18:18',2,2,2),
(54,29,15,14,'16:18:19','16:18:19',2,2,1),
(55,30,15,15,'16:18:19','16:18:19',2,2,2),
(56,31,16,15,'16:18:20','16:18:20',2,2,1),
(57,32,16,16,'16:18:22','16:18:22',2,2,2),
(58,33,17,16,'16:18:23','16:18:23',2,2,1),
(59,34,17,17,'16:18:23','16:18:23',2,2,2),
(60,35,18,17,'16:18:24','16:18:24',2,2,1),
(61,36,19,17,'16:18:25','16:18:25',2,2,1),
(62,1,1,0,'16:24:22','16:24:23',3,1,1),
(63,2,1,1,'16:24:25','16:24:27',3,1,2),
(64,3,1,2,'16:24:29','16:24:36',3,1,2),
(65,4,2,2,'16:24:37','16:24:38',3,1,1),
(66,5,3,2,'16:24:40','16:24:41',3,1,1),
(67,6,3,3,'16:24:42','16:24:45',3,1,2);

/*Table structure for table `rally_details` */

DROP TABLE IF EXISTS `rally_details`;

CREATE TABLE `rally_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill` int(11) NOT NULL,
  `chest_no` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `order_num` int(11) NOT NULL,
  `rally_id` int(11) NOT NULL,
  `code` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rally_id` (`rally_id`),
  CONSTRAINT `rally_details_ibfk_1` FOREIGN KEY (`rally_id`) REFERENCES `rally` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

/*Data for the table `rally_details` */

insert  into `rally_details`(`id`,`skill`,`chest_no`,`rating`,`order_num`,`rally_id`,`code`) values 
(1,1,2,5,1,1,'Q-2-S1-2R-2--B'),
(2,1,4,3,1,2,'q-4-c'),
(3,3,5,5,1,2,'t-5-b'),
(4,1,2,1,1,3,'q-2-z'),
(5,5,5,4,1,4,'w-5-v'),
(6,4,7,2,1,4,'r-7-x'),
(7,6,1,1,1,4,'y-3-z'),
(8,7,0,5,1,5,'u'),
(9,8,0,1,1,6,'i'),
(10,5,4,3,1,7,'w-4-c'),
(11,4,6,2,1,7,'r-6-x'),
(12,2,7,3,1,7,'e-7-c'),
(13,3,5,1,1,7,'t-5-z'),
(14,5,4,3,1,8,'w-4-c'),
(15,4,7,4,1,8,'r-7-v'),
(16,2,5,1,1,8,'e-5-z'),
(17,5,3,5,1,9,'w-3-b'),
(18,4,7,3,1,9,'r-7-c'),
(19,6,4,5,1,9,'y-4-b'),
(20,2,4,3,1,9,'e-4-c'),
(21,2,7,5,1,9,'e-7-b'),
(22,7,0,5,1,10,'u'),
(23,8,0,1,1,11,'i'),
(24,8,0,1,1,12,'i'),
(25,8,0,1,1,13,'i'),
(26,7,0,5,1,14,'u'),
(27,8,0,1,1,15,'i'),
(28,7,0,5,1,16,'u'),
(29,8,0,1,1,17,'i'),
(30,7,0,5,1,18,'u'),
(31,8,0,1,1,19,'i'),
(32,8,0,1,1,20,'i'),
(33,7,0,5,1,21,'u'),
(34,7,0,5,1,22,'u'),
(35,8,0,1,1,23,'i'),
(36,8,0,1,1,24,'i'),
(37,8,0,1,1,25,'i'),
(38,7,0,5,1,26,'u'),
(39,8,0,1,1,27,'i'),
(40,7,0,5,1,28,'u'),
(41,8,0,1,1,29,'i'),
(42,7,0,5,1,30,'u'),
(43,8,0,1,1,31,'i'),
(44,7,0,5,1,32,'u'),
(45,8,0,1,1,33,'i'),
(46,7,0,5,1,34,'u'),
(47,8,0,1,1,35,'i'),
(48,7,0,5,1,36,'u'),
(49,8,0,1,1,37,'i'),
(50,7,0,5,1,38,'u'),
(51,8,0,1,1,39,'i'),
(52,7,0,5,1,40,'u'),
(53,8,0,1,1,41,'i'),
(54,7,0,5,1,42,'u'),
(55,8,0,1,1,43,'i'),
(56,7,0,5,1,44,'u'),
(57,8,0,1,1,45,'i'),
(58,7,0,5,1,46,'u'),
(59,8,0,1,1,47,'i'),
(60,7,0,5,1,48,'u'),
(61,8,0,1,1,49,'i'),
(62,7,0,5,1,50,'u'),
(63,8,0,1,1,51,'i'),
(64,7,0,5,1,52,'u'),
(65,8,0,1,1,53,'i'),
(66,7,0,5,1,54,'u'),
(67,8,0,1,1,55,'i'),
(68,7,0,5,1,56,'u'),
(69,8,0,1,1,57,'i'),
(70,7,0,5,1,58,'u'),
(71,8,0,1,1,59,'i'),
(72,7,0,5,1,60,'u'),
(73,7,0,5,1,61,'u'),
(74,1,1,5,1,62,'Q-1-S1-5R-A-1-H52A-O6B-B'),
(75,1,1,1,1,63,'Q-1-S3-3R-1-H52A-O1B-Z'),
(76,1,1,3,1,64,'Q-1-S4-1-H12B-O4D-C'),
(77,2,5,1,2,64,'E-5-A2-Q1-MM-5--Z'),
(78,1,1,5,1,65,'Q-1-S5-1-H52B-O4D-B'),
(79,1,1,5,1,66,'Q-1-S2-1-H12C-O5B-B'),
(80,1,1,1,1,67,'Q-1-S4-1-H12B-O6D-Z');

/*Table structure for table `rally_details_criteria` */

DROP TABLE IF EXISTS `rally_details_criteria`;

CREATE TABLE `rally_details_criteria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_desc_criteria_id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL,
  `rally_details_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rally_details_id` (`rally_details_id`),
  KEY `skill_desc_criteria_id` (`skill_desc_criteria_id`),
  CONSTRAINT `rally_details_criteria_ibfk_1` FOREIGN KEY (`rally_details_id`) REFERENCES `rally_details` (`id`),
  CONSTRAINT `rally_details_criteria_ibfk_2` FOREIGN KEY (`skill_desc_criteria_id`) REFERENCES `m_skills_desc_criteria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=latin1;

/*Data for the table `rally_details_criteria` */

insert  into `rally_details_criteria`(`id`,`skill_desc_criteria_id`,`type`,`rally_details_id`) values 
(1,82,'2-',1),
(2,1,'JF',1),
(3,6,'2',1),
(4,2,'',1),
(5,3,'',1),
(6,4,'',1),
(7,5,'',1),
(8,7,'',1),
(9,8,'',1),
(10,9,'RC',1),
(11,10,'RM',1),
(12,82,'4-',2),
(13,1,'',2),
(14,6,'',2),
(15,2,'',2),
(16,3,'',2),
(17,4,'',2),
(18,5,'',2),
(19,7,'',2),
(20,8,'1 : 0',2),
(21,9,'RC',2),
(22,10,'RM',2),
(23,84,'5-',3),
(24,27,'',3),
(25,28,'',3),
(26,29,'',3),
(27,30,'',3),
(28,31,'',3),
(29,35,'',3),
(30,37,'',3),
(31,38,'',3),
(32,32,'',3),
(33,33,'',3),
(34,34,'',3),
(35,36,'',3),
(36,39,'',3),
(37,40,'1 : 0',3),
(38,41,'RM',3),
(39,88,'',3),
(40,82,'2-',4),
(41,1,'',4),
(42,6,'',4),
(43,2,'',4),
(44,3,'',4),
(45,4,'',4),
(46,5,'',4),
(47,7,'',4),
(48,8,'2 : 0',4),
(49,9,'RC',4),
(50,10,'RM',4),
(51,86,'5-',5),
(52,54,'',5),
(53,55,'',5),
(54,59,'',5),
(55,60,'',5),
(56,56,'',5),
(57,57,'',5),
(58,58,'MB',5),
(59,63,'2 : 1',5),
(60,64,'RM',5),
(61,85,'7-',6),
(62,42,'',6),
(63,43,'',6),
(64,44,'',6),
(65,45,'',6),
(66,46,'',6),
(67,49,'',6),
(68,50,'',6),
(69,51,'',6),
(70,47,'',6),
(71,48,'',6),
(72,52,'',6),
(73,53,'2 : 1',6),
(74,87,'3-1-',7),
(75,90,'',7),
(76,65,'',7),
(77,66,'',7),
(78,67,'',7),
(79,69,'',7),
(80,70,'',7),
(81,71,'',7),
(82,75,'',7),
(83,76,'',7),
(84,77,'',7),
(85,95,'',7),
(86,68,'',7),
(87,72,'',7),
(88,73,'',7),
(89,74,'',7),
(90,78,'RM',7),
(91,80,'2 : 1',7),
(92,81,'',7),
(93,86,'4-',10),
(94,54,'',10),
(95,55,'',10),
(96,59,'',10),
(97,60,'',10),
(98,56,'',10),
(99,57,'',10),
(100,58,'U',10),
(101,63,'3 : 3',10),
(102,64,'RM',10),
(103,85,'6-',11),
(104,42,'',11),
(105,43,'',11),
(106,44,'',11),
(107,45,'',11),
(108,46,'',11),
(109,49,'',11),
(110,50,'',11),
(111,51,'',11),
(112,47,'',11),
(113,48,'',11),
(114,52,'',11),
(115,53,'3 : 3',11),
(116,83,'7-',12),
(117,11,'',12),
(118,12,'',12),
(119,14,'',12),
(120,17,'',12),
(121,19,'',12),
(122,20,'',12),
(123,26,'',12),
(124,94,'',12),
(125,13,'',12),
(126,15,'',12),
(127,16,'',12),
(128,18,'S',12),
(129,21,'',12),
(130,22,'',12),
(131,23,'3 : 3',12),
(132,24,'RM',12),
(133,25,'',12),
(134,91,'',12),
(135,92,'',12),
(136,93,'',12),
(137,84,'5-',13),
(138,27,'',13),
(139,28,'',13),
(140,29,'',13),
(141,30,'',13),
(142,31,'',13),
(143,35,'',13),
(144,37,'',13),
(145,38,'',13),
(146,32,'',13),
(147,33,'',13),
(148,34,'',13),
(149,36,'',13),
(150,39,'',13),
(151,40,'3 : 3',13),
(152,41,'RL',13),
(153,88,'',13),
(154,86,'4-',14),
(155,54,'',14),
(156,55,'',14),
(157,59,'',14),
(158,60,'',14),
(159,56,'',14),
(160,57,'',14),
(161,58,'U',14),
(162,63,'3 : 4',14),
(163,64,'RM',14),
(164,85,'7-',15),
(165,42,'',15),
(166,43,'',15),
(167,44,'',15),
(168,45,'',15),
(169,46,'',15),
(170,49,'',15),
(171,50,'',15),
(172,51,'',15),
(173,47,'',15),
(174,48,'',15),
(175,52,'',15),
(176,53,'3 : 4',15),
(177,83,'5-5-',16),
(178,11,'',16),
(179,12,'',16),
(180,14,'',16),
(181,17,'',16),
(182,19,'',16),
(183,20,'',16),
(184,26,'',16),
(185,94,'',16),
(186,13,'',16),
(187,15,'',16),
(188,16,'',16),
(189,18,'MB',16),
(190,21,'',16),
(191,22,'',16),
(192,23,'3 : 4',16),
(193,24,'RM',16),
(194,25,'',16),
(195,91,'',16),
(196,92,'',16),
(197,93,'',16),
(198,86,'3-',17),
(199,54,'',17),
(200,55,'',17),
(201,59,'',17),
(202,60,'',17),
(203,56,'',17),
(204,57,'',17),
(205,58,'U',17),
(206,63,'3 : 5',17),
(207,64,'RM',17),
(208,85,'7-',18),
(209,42,'',18),
(210,43,'',18),
(211,44,'',18),
(212,45,'',18),
(213,46,'',18),
(214,49,'',18),
(215,50,'',18),
(216,51,'',18),
(217,47,'',18),
(218,48,'',18),
(219,52,'',18),
(220,53,'3 : 5',18),
(221,87,'4-',19),
(222,90,'',19),
(223,65,'',19),
(224,66,'',19),
(225,67,'',19),
(226,69,'',19),
(227,70,'',19),
(228,71,'',19),
(229,75,'',19),
(230,76,'',19),
(231,77,'',19),
(232,95,'',19),
(233,68,'',19),
(234,72,'',19),
(235,73,'',19),
(236,74,'',19),
(237,78,'RM',19),
(238,80,'3 : 5',19),
(239,81,'',19),
(240,83,'4-',20),
(241,11,'',20),
(242,12,'',20),
(243,14,'',20),
(244,17,'',20),
(245,19,'',20),
(246,20,'',20),
(247,26,'',20),
(248,94,'',20),
(249,13,'',20),
(250,15,'',20),
(251,16,'',20),
(252,18,'U',20),
(253,21,'',20),
(254,22,'',20),
(255,23,'3 : 5',20),
(256,24,'RM',20),
(257,25,'',20),
(258,91,'',20),
(259,92,'',20),
(260,93,'',20),
(261,83,'7-',21),
(262,11,'',21),
(263,12,'',21),
(264,14,'',21),
(265,17,'',21),
(266,19,'',21),
(267,20,'',21),
(268,26,'',21),
(269,94,'',21),
(270,13,'',21),
(271,15,'',21),
(272,16,'',21),
(273,18,'S',21),
(274,21,'',21),
(275,22,'',21),
(276,23,'3 : 5',21),
(277,24,'RM',21),
(278,25,'',21),
(279,91,'',21),
(280,92,'',21),
(281,93,'',21),
(282,82,'1-H52A-O6B',74),
(283,1,'JF',74),
(284,6,'5',74),
(285,2,'DC',74),
(286,3,'C',74),
(287,4,'5',74),
(288,5,'6',74),
(289,7,'OH',74),
(290,8,'',74),
(291,9,'RC',74),
(292,10,'RM',74),
(293,82,'1-H52A-O1B',75),
(294,1,'JP',75),
(295,6,'3',75),
(296,2,'',75),
(297,3,'',75),
(298,4,'1',75),
(299,5,'6',75),
(300,7,'L',75),
(301,8,'',75),
(302,9,'RC',75),
(303,10,'RM',75),
(304,82,'1-H12B-O4D',76),
(305,1,'SF',76),
(306,6,'',76),
(307,2,'',76),
(308,3,'',76),
(309,4,'1',76),
(310,5,'4',76),
(311,7,'',76),
(312,8,'',76),
(313,9,'RC',76),
(314,10,'RL',76),
(315,83,'5-',77),
(316,11,'OT',77),
(317,12,'',77),
(318,14,'',77),
(319,17,'K1',77),
(320,19,'',77),
(321,20,'',77),
(322,26,'',77),
(323,94,'',77),
(324,13,'',77),
(325,15,'',77),
(326,16,'',77),
(327,18,'MB',77),
(328,21,'',77),
(329,22,'',77),
(330,23,'',77),
(331,24,'FR',77),
(332,25,'',77),
(333,91,'',77),
(334,92,'',77),
(335,93,'',77),
(336,82,'1-H52B-O4D',78),
(337,1,'SS',78),
(338,6,'',78),
(339,2,'',78),
(340,3,'',78),
(341,4,'5',78),
(342,5,'4',78),
(343,7,'',78),
(344,8,'',78),
(345,9,'RC',78),
(346,10,'FL',78),
(347,82,'1-H12C-O5B',79),
(348,1,'JS',79),
(349,6,'',79),
(350,2,'',79),
(351,3,'',79),
(352,4,'',79),
(353,5,'',79),
(354,7,'',79),
(355,8,'',79),
(356,9,'RC',79),
(357,10,'FL',79),
(358,82,'1-H12B-O6D',80),
(359,1,'SF',80),
(360,6,'',80),
(361,2,'',80),
(362,3,'',80),
(363,4,'1',80),
(364,5,'6',80),
(365,7,'',80),
(366,8,'',80),
(367,9,'RC',80),
(368,10,'FL',80);

/*Table structure for table `rally_rotation_order` */

DROP TABLE IF EXISTS `rally_rotation_order`;

CREATE TABLE `rally_rotation_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pos1playerId` int(11) DEFAULT NULL,
  `pos2playerId` int(11) DEFAULT NULL,
  `pos3playerId` int(11) DEFAULT NULL,
  `pos4playerId` int(11) DEFAULT NULL,
  `pos5playerId` int(11) DEFAULT NULL,
  `pos6playerId` int(11) DEFAULT NULL,
  `pos1playerIdOpp` int(11) DEFAULT NULL,
  `pos2playerIdOpp` int(11) DEFAULT NULL,
  `pos3playerIdOpp` int(11) DEFAULT NULL,
  `pos4playerIdOpp` int(11) DEFAULT NULL,
  `pos5playerIdOpp` int(11) DEFAULT NULL,
  `pos6playerIdOpp` int(11) DEFAULT NULL,
  `rallyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rallyId` (`rallyId`),
  CONSTRAINT `rally_rotation_order_ibfk_1` FOREIGN KEY (`rallyId`) REFERENCES `rally` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

/*Data for the table `rally_rotation_order` */

insert  into `rally_rotation_order`(`id`,`pos1playerId`,`pos2playerId`,`pos3playerId`,`pos4playerId`,`pos5playerId`,`pos6playerId`,`pos1playerIdOpp`,`pos2playerIdOpp`,`pos3playerIdOpp`,`pos4playerIdOpp`,`pos5playerIdOpp`,`pos6playerIdOpp`,`rallyId`) values 
(1,2,3,4,5,6,7,14,12,10,9,11,13,1),
(2,2,3,4,5,6,7,14,12,10,9,11,13,2),
(3,2,3,4,5,6,7,14,12,10,9,11,13,3),
(4,2,3,4,5,6,7,12,10,9,11,13,14,4),
(5,2,3,4,5,6,7,12,10,9,11,13,14,5),
(6,3,4,5,6,7,2,12,10,9,11,13,14,6),
(7,3,4,5,6,7,2,12,10,9,11,13,14,7),
(8,3,4,5,6,7,2,12,10,9,11,13,14,8),
(9,3,4,5,6,7,2,12,10,9,11,13,14,9),
(10,4,5,6,7,2,3,12,10,9,11,13,14,10),
(11,5,6,7,2,3,4,12,10,9,11,13,14,11),
(12,5,6,7,2,3,4,12,10,9,11,13,14,12),
(13,5,6,7,2,3,4,12,10,9,11,13,14,13),
(14,5,6,7,2,3,4,12,10,9,11,13,14,14),
(15,6,7,2,3,4,5,12,10,9,11,13,14,15),
(16,6,7,2,3,4,5,12,10,9,11,13,14,16),
(17,7,2,3,4,5,6,12,10,9,11,13,14,17),
(18,7,2,3,4,5,6,12,10,9,11,13,14,18),
(19,2,3,4,5,6,7,12,10,9,11,13,14,19),
(20,2,3,4,5,6,7,12,10,9,11,13,14,20),
(21,2,3,4,5,6,7,12,10,9,11,13,14,21),
(22,3,4,5,6,7,2,12,10,9,11,13,14,22),
(23,4,5,6,7,2,3,12,10,9,11,13,14,23),
(24,4,5,6,7,2,3,12,10,9,11,13,14,24),
(25,4,5,6,7,2,3,12,10,9,11,13,14,25),
(26,3,4,5,2,6,7,12,11,10,14,9,13,26),
(27,4,5,2,6,7,3,12,11,10,14,9,13,27),
(28,4,5,2,6,7,3,12,11,10,14,9,13,28),
(29,5,2,6,7,3,4,12,11,10,14,9,13,29),
(30,5,2,6,7,3,4,12,11,10,14,9,13,30),
(31,2,6,7,3,4,5,12,11,10,14,9,13,31),
(32,2,6,7,3,4,5,12,11,10,14,9,13,32),
(33,6,7,3,4,5,2,12,11,10,14,9,13,33),
(34,6,7,3,4,5,2,12,11,10,14,9,13,34),
(35,7,3,4,5,2,6,12,11,10,14,9,13,35),
(36,7,3,4,5,2,6,12,11,10,14,9,13,36),
(37,3,4,5,2,6,7,12,11,10,14,9,13,37),
(38,3,4,5,2,6,7,12,11,10,14,9,13,38),
(39,4,5,2,6,7,3,12,11,10,14,9,13,39),
(40,4,5,2,6,7,3,12,11,10,14,9,13,40),
(41,5,2,6,7,3,4,12,11,10,14,9,13,41),
(42,5,2,6,7,3,4,12,11,10,14,9,13,42),
(43,2,6,7,3,4,5,12,11,10,14,9,13,43),
(44,2,6,7,3,4,5,12,11,10,14,9,13,44),
(45,6,7,3,4,5,2,12,11,10,14,9,13,45),
(46,6,7,3,4,5,2,12,11,10,14,9,13,46),
(47,7,3,4,5,2,6,12,11,10,14,9,13,47),
(48,7,3,4,5,2,6,12,11,10,14,9,13,48),
(49,3,4,5,2,6,7,12,11,10,14,9,13,49),
(50,3,4,5,2,6,7,12,11,10,14,9,13,50),
(51,4,5,2,6,7,3,12,11,10,14,9,13,51),
(52,4,5,2,6,7,3,12,11,10,14,9,13,52),
(53,5,2,6,7,3,4,12,11,10,14,9,13,53),
(54,5,2,6,7,3,4,12,11,10,14,9,13,54),
(55,2,6,7,3,4,5,12,11,10,14,9,13,55),
(56,2,6,7,3,4,5,12,11,10,14,9,13,56),
(57,6,7,3,4,5,2,12,11,10,14,9,13,57),
(58,6,7,3,4,5,2,12,11,10,14,9,13,58),
(59,7,3,4,5,2,6,12,11,10,14,9,13,59),
(60,7,3,4,5,2,6,12,11,10,14,9,13,60),
(61,3,4,5,2,6,7,12,11,10,14,9,13,61),
(62,1,2,3,4,5,6,8,9,10,11,12,13,62),
(63,1,2,3,4,5,6,8,9,10,11,12,13,63),
(64,1,2,3,4,5,6,9,10,11,12,13,8,64),
(65,1,2,3,4,5,6,10,11,12,13,8,9,65),
(66,1,2,3,4,5,6,10,11,12,13,8,9,66),
(67,1,2,3,4,5,6,10,11,12,13,8,9,67);

/*Table structure for table `set_plus_minus` */

DROP TABLE IF EXISTS `set_plus_minus`;

CREATE TABLE `set_plus_minus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opponent_error` varchar(50) DEFAULT NULL,
  `team_fault` varchar(50) DEFAULT NULL,
  `match_evaluation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_evaluation_id` (`match_evaluation_id`),
  CONSTRAINT `set_plus_minus_ibfk_1` FOREIGN KEY (`match_evaluation_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `set_plus_minus` */

insert  into `set_plus_minus`(`id`,`opponent_error`,`team_fault`,`match_evaluation_id`) values 
(1,'7','11',1),
(2,'19','17',2),
(3,'0','0',3);

/*Table structure for table `set_substitution` */

DROP TABLE IF EXISTS `set_substitution`;

CREATE TABLE `set_substitution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` int(11) DEFAULT NULL,
  `rotation_player_id` int(11) DEFAULT NULL,
  `substitute_player_id` int(11) DEFAULT NULL,
  `point1` varchar(50) DEFAULT NULL,
  `point2` varchar(50) DEFAULT NULL,
  `point1_at_rally_id` int(11) DEFAULT NULL,
  `point2_at_rally_id` int(11) DEFAULT NULL,
  `match_evaluation_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rotation_player_id` (`rotation_player_id`),
  KEY `substitute_player_id` (`substitute_player_id`),
  KEY `match_evaluation_id` (`match_evaluation_id`),
  CONSTRAINT `set_substitution_ibfk_1` FOREIGN KEY (`rotation_player_id`) REFERENCES `players` (`id`),
  CONSTRAINT `set_substitution_ibfk_2` FOREIGN KEY (`substitute_player_id`) REFERENCES `players` (`id`),
  CONSTRAINT `set_substitution_ibfk_3` FOREIGN KEY (`match_evaluation_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `set_substitution` */

insert  into `set_substitution`(`id`,`position`,`rotation_player_id`,`substitute_player_id`,`point1`,`point2`,`point1_at_rally_id`,`point2_at_rally_id`,`match_evaluation_id`,`type`) values 
(1,1,2,NULL,NULL,NULL,NULL,NULL,1,1),
(2,2,3,NULL,NULL,NULL,NULL,NULL,1,1),
(3,3,4,NULL,NULL,NULL,NULL,NULL,1,1),
(4,4,5,NULL,NULL,NULL,NULL,NULL,1,1),
(5,5,6,NULL,NULL,NULL,NULL,NULL,1,1),
(6,6,7,NULL,NULL,NULL,NULL,NULL,1,1),
(7,1,14,NULL,NULL,NULL,NULL,NULL,1,2),
(8,2,12,NULL,NULL,NULL,NULL,NULL,1,2),
(9,3,10,NULL,NULL,NULL,NULL,NULL,1,2),
(10,4,9,NULL,NULL,NULL,NULL,NULL,1,2),
(11,5,11,NULL,NULL,NULL,NULL,NULL,1,2),
(12,6,13,NULL,NULL,NULL,NULL,NULL,1,2),
(13,1,3,NULL,NULL,NULL,NULL,NULL,2,1),
(14,2,4,NULL,NULL,NULL,NULL,NULL,2,1),
(15,3,5,NULL,NULL,NULL,NULL,NULL,2,1),
(16,4,2,NULL,NULL,NULL,NULL,NULL,2,1),
(17,5,6,NULL,NULL,NULL,NULL,NULL,2,1),
(18,6,7,NULL,NULL,NULL,NULL,NULL,2,1),
(19,1,12,NULL,NULL,NULL,NULL,NULL,2,2),
(20,2,11,NULL,NULL,NULL,NULL,NULL,2,2),
(21,3,10,NULL,NULL,NULL,NULL,NULL,2,2),
(22,4,14,NULL,NULL,NULL,NULL,NULL,2,2),
(23,5,9,NULL,NULL,NULL,NULL,NULL,2,2),
(24,6,13,NULL,NULL,NULL,NULL,NULL,2,2),
(25,1,1,NULL,NULL,NULL,NULL,NULL,3,1),
(26,2,2,NULL,NULL,NULL,NULL,NULL,3,1),
(27,3,3,NULL,NULL,NULL,NULL,NULL,3,1),
(28,4,4,NULL,NULL,NULL,NULL,NULL,3,1),
(29,5,5,NULL,NULL,NULL,NULL,NULL,3,1),
(30,6,6,NULL,NULL,NULL,NULL,NULL,3,1),
(31,1,8,NULL,NULL,NULL,NULL,NULL,3,2),
(32,2,9,NULL,NULL,NULL,NULL,NULL,3,2),
(33,3,10,NULL,NULL,NULL,NULL,NULL,3,2),
(34,4,11,NULL,NULL,NULL,NULL,NULL,3,2),
(35,5,12,NULL,NULL,NULL,NULL,NULL,3,2),
(36,6,13,NULL,NULL,NULL,NULL,NULL,3,2);

/*Table structure for table `set_timeout` */

DROP TABLE IF EXISTS `set_timeout`;

CREATE TABLE `set_timeout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` int(11) DEFAULT NULL,
  `timeOut` varchar(50) DEFAULT NULL,
  `A` varchar(50) DEFAULT NULL,
  `B` varchar(50) DEFAULT NULL,
  `match_evaluation_id` int(11) DEFAULT NULL,
  `timeout_at_rally` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_evaluation_id` (`match_evaluation_id`),
  CONSTRAINT `set_timeout_ibfk_1` FOREIGN KEY (`match_evaluation_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `set_timeout` */

/*Table structure for table `setrotationorder` */

DROP TABLE IF EXISTS `setrotationorder`;

CREATE TABLE `setrotationorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` int(11) NOT NULL,
  `playerId` int(11) DEFAULT NULL,
  `match_evaluation_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `playerId` (`playerId`),
  KEY `match_evaluation_id` (`match_evaluation_id`),
  CONSTRAINT `setrotationorder_ibfk_1` FOREIGN KEY (`playerId`) REFERENCES `players` (`id`),
  CONSTRAINT `setrotationorder_ibfk_2` FOREIGN KEY (`match_evaluation_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `setrotationorder` */

insert  into `setrotationorder`(`id`,`position`,`playerId`,`match_evaluation_id`,`type`) values 
(1,1,2,1,1),
(2,2,3,1,1),
(3,3,4,1,1),
(4,4,5,1,1),
(5,5,6,1,1),
(6,6,7,1,1),
(7,7,1,1,1),
(8,1,14,1,2),
(9,2,12,1,2),
(10,3,10,1,2),
(11,4,9,1,2),
(12,5,11,1,2),
(13,6,13,1,2),
(14,7,8,1,2),
(15,1,3,2,1),
(16,2,4,2,1),
(17,3,5,2,1),
(18,4,2,2,1),
(19,5,6,2,1),
(20,6,7,2,1),
(21,7,1,2,1),
(22,1,12,2,2),
(23,2,11,2,2),
(24,3,10,2,2),
(25,4,14,2,2),
(26,5,9,2,2),
(27,6,13,2,2),
(28,7,8,2,2),
(29,1,1,3,1),
(30,2,2,3,1),
(31,3,3,3,1),
(32,4,4,3,1),
(33,5,5,3,1),
(34,6,6,3,1),
(35,7,7,3,1),
(36,1,8,3,2),
(37,2,9,3,2),
(38,3,10,3,2),
(39,4,11,3,2),
(40,5,12,3,2),
(41,6,13,3,2),
(42,7,14,3,2);

/*Table structure for table `shortcut_settings` */

DROP TABLE IF EXISTS `shortcut_settings`;

CREATE TABLE `shortcut_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shortcutId` int(11) DEFAULT NULL,
  `headingId` int(11) DEFAULT NULL,
  `code` varchar(100) NOT NULL,
  `abbr` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;

/*Data for the table `shortcut_settings` */

insert  into `shortcut_settings`(`id`,`shortcutId`,`headingId`,`code`,`abbr`) values 
(1,1,1,'S','S'),
(2,2,1,'A','OH'),
(3,3,1,'M','MB'),
(4,4,1,'U','UV'),
(5,5,1,'LB','LB'),
(6,6,2,'H','H'),
(7,7,2,'O','Opp'),
(8,8,3,'5R','5R'),
(9,9,3,'4R','4R'),
(10,10,3,'3R','3R'),
(11,11,3,'2R','2R'),
(12,12,4,'55','5C'),
(13,13,4,'44','4C'),
(14,14,4,'33','3C'),
(15,15,4,'22','2C'),
(16,71,4,'NN','NC'),
(17,72,4,'1C','1C'),
(18,16,5,'T1','Low'),
(19,17,5,'T2','Medium'),
(20,18,5,'T3','High'),
(21,87,5,'T4','ODB'),
(22,19,6,'Q1','K1'),
(23,20,6,'Q2','K2'),
(24,21,6,'Q3','TP'),
(25,22,7,'AA','OOH'),
(26,23,7,'MM','OMB'),
(27,24,7,'UU','OU'),
(28,25,7,'SS','OS'),
(29,26,8,'E1','SGL'),
(30,27,8,'E2','DBL'),
(31,28,8,'E3','TPL'),
(32,84,8,'E4','NB'),
(33,29,9,'D1','ODF'),
(34,30,9,'D11','ODF'),
(35,31,9,'D2','ODF'),
(36,32,9,'D22','ODF'),
(37,33,9,'D3','ODF'),
(38,34,9,'D33','ODF'),
(39,35,9,'D333','ODF'),
(40,75,9,'D23','ODF'),
(41,76,9,'DN','ODF'),
(42,36,10,'A1','IN'),
(43,37,10,'A2','OT'),
(44,38,10,'A3','BT'),
(45,39,10,'A4','OL'),
(46,40,10,'A5','D'),
(47,41,10,'A6','BC'),
(48,73,10,'A7','R'),
(49,74,10,'A8','BTL'),
(50,42,11,'B1','CB'),
(51,43,11,'B2','RR'),
(52,44,11,'B3','ZB'),
(53,88,11,'B4','None'),
(54,45,12,'F','F'),
(55,46,12,'F1','SF'),
(56,47,12,'F0','NF'),
(57,48,13,'S1','JF'),
(58,49,13,'S2','JS'),
(59,50,13,'S3','JP'),
(60,51,13,'S4','SF'),
(61,52,13,'S5','SS'),
(62,53,14,'W1','JS'),
(63,54,14,'W2','RB'),
(64,55,14,'W3','FP'),
(65,56,14,'W4','HP'),
(66,57,14,'W5','BC'),
(67,68,14,'W6','BS'),
(68,69,14,'W7','FS'),
(69,91,14,'W6','BS'),
(70,58,15,'N1','ON'),
(71,59,15,'N2','CN'),
(72,60,15,'N3','AN'),
(73,61,15,'N4','LT'),
(74,70,15,'N5','ANF'),
(75,89,15,'NA','NA'),
(76,93,15,'N6','ANSF'),
(77,94,15,'N7','OC'),
(78,95,15,'N8','TOC'),
(79,62,16,'LO','LO'),
(80,63,16,'LK','LC'),
(81,64,17,'K','Kill'),
(82,65,17,'D','SF'),
(83,66,17,'LI','LI'),
(84,67,17,'CB','CB'),
(85,77,17,'ST','ST'),
(86,78,18,'HA','HA'),
(87,79,18,'HB','HB'),
(88,80,18,'HD','HD'),
(89,85,18,'OA','OA'),
(90,86,18,'OB','OB'),
(91,92,18,'OD','OD'),
(92,81,19,'C1','C'),
(93,82,19,'C2','S'),
(94,83,19,'C3','D'),
(95,90,19,'C4','SP');

/*Table structure for table `teams` */

DROP TABLE IF EXISTS `teams`;

CREATE TABLE `teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `shortcode` varchar(50) DEFAULT NULL,
  `competition_id` int(11) NOT NULL,
  `coach` varchar(255) DEFAULT NULL,
  `asst_coach` varchar(255) DEFAULT NULL,
  `trainer` varchar(255) DEFAULT NULL,
  `medical_officer` varchar(255) DEFAULT NULL,
  `analyzer` varchar(255) DEFAULT NULL,
  `isdeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `competition_id` (`competition_id`),
  CONSTRAINT `teams_ibfk_1` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `teams` */

insert  into `teams`(`id`,`name`,`shortcode`,`competition_id`,`coach`,`asst_coach`,`trainer`,`medical_officer`,`analyzer`,`isdeleted`) values 
(1,'Guru','GT',1,'guru','','','','',0),
(2,'Nishant','NV',1,'Nishant','','','','',0);

/*Table structure for table `trainee` */

DROP TABLE IF EXISTS `trainee`;

CREATE TABLE `trainee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `batch_id` int(11) NOT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `trainee_ibfk_1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `trainee` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `email_id` varchar(255) NOT NULL,
  `keycode` varchar(255) NOT NULL,
  `is_activated` int(11) NOT NULL,
  `mac_address` varchar(500) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`email_id`,`keycode`,`is_activated`,`mac_address`,`created_on`) values 
(1,'root','root','root@gmail.com','',0,'','2019-02-01 15:34:15');

/*Table structure for table `vollycourtcoordinates` */

DROP TABLE IF EXISTS `vollycourtcoordinates`;

CREATE TABLE `vollycourtcoordinates` (
  `id` int(11) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `from_pos` int(11) DEFAULT NULL,
  `to_pos` int(11) DEFAULT NULL,
  `x1` int(11) DEFAULT NULL,
  `y1` int(11) DEFAULT NULL,
  `x2` int(11) DEFAULT NULL,
  `y2` int(11) DEFAULT NULL,
  `x3` int(11) DEFAULT NULL,
  `y3` int(11) DEFAULT NULL,
  `x4` int(11) DEFAULT NULL,
  `y4` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vollycourtcoordinates` */

insert  into `vollycourtcoordinates`(`id`,`Type`,`from_pos`,`to_pos`,`x1`,`y1`,`x2`,`y2`,`x3`,`y3`,`x4`,`y4`) values 
(1,'Service',1,1,90,331,294,263,410,224,611,155),
(2,'Service',1,2,90,331,294,233,410,177,461,155),
(3,'Service',1,3,90,331,294,282,410,257,461,246),
(4,'Service',1,4,90,331,294,332,410,332,461,332),
(5,'Service',1,5,90,331,294,332,410,332,611,332),
(6,'Service',1,6,90,331,294,296,410,277,611,242),
(7,'Service',5,1,90,155,294,155,410,155,611,155),
(8,'Service',5,2,90,155,294,155,410,155,461,155),
(9,'Service',5,3,90,155,294,204,410,232,461,245),
(10,'Service',5,4,90,155,294,251,410,306,461,330),
(11,'Service',5,5,90,155,294,222,410,261,611,330),
(12,'Service',5,6,90,155,294,188,410,206,611,243),
(13,'Service',6,1,90,242,294,208,410,190,611,155),
(14,'Service',6,2,90,242,294,193,410,164,461,155),
(15,'Service',6,3,90,242,294,242,410,242,461,242),
(16,'Service',6,4,90,242,294,290,410,317,461,331),
(17,'Service',6,5,90,242,294,276,410,296,611,331),
(18,'Service',6,6,90,242,294,242,410,242,611,242),
(19,'Attack',1,1,212,331,294,297,410,244,611,155),
(20,'Attack',1,2,212,331,294,273,410,188,461,155),
(21,'Attack',1,3,212,331,294,304,410,264,461,242),
(22,'Attack',1,4,212,331,294,330,410,330,461,331),
(23,'Attack',1,5,212,331,294,330,410,330,611,331),
(24,'Attack',1,6,212,331,294,314,410,290,611,242),
(25,'Attack',2,1,361,331,410,300,525,216,611,155),
(26,'Attack',2,2,361,331,410,247,445,177,461,155),
(27,'Attack',2,3,361,331,410,286,444,255,461,242),
(28,'Attack',2,4,361,331,410,330,452,330,461,331),
(29,'Attack',2,5,361,331,410,330,452,330,611,331),
(30,'Attack',2,6,361,331,410,314,525,274,611,242),
(31,'Attack',3,1,361,242,410,225,525,186,611,155),
(32,'Attack',3,2,361,242,410,200,443,168,461,155),
(33,'Attack',3,3,361,242,410,243,443,245,461,242),
(34,'Attack',3,4,361,242,410,280,448,320,461,331),
(35,'Attack',3,5,361,242,410,257,448,272,611,331),
(36,'Attack',3,6,361,242,410,240,525,242,611,242),
(37,'Attack',4,1,361,155,410,157,451,155,611,155),
(38,'Attack',4,2,361,155,410,157,451,155,461,155),
(39,'Attack',4,3,361,155,410,197,447,233,461,242),
(40,'Attack',4,4,361,155,410,235,447,307,461,331),
(41,'Attack',4,5,361,155,410,187,451,215,611,331),
(42,'Attack',4,6,361,155,410,173,451,186,611,242),
(43,'Attack',5,1,212,155,294,155,410,155,611,155),
(44,'Attack',5,2,212,155,294,155,410,155,461,155),
(45,'Attack',5,3,212,155,294,185,410,228,461,242),
(46,'Attack',5,4,212,155,294,208,410,292,461,331),
(47,'Attack',5,5,212,155,294,189,410,239,611,331),
(48,'Attack',5,6,212,155,294,172,410,198,611,242),
(49,'Attack',6,1,212,242,294,227,410,200,611,155),
(50,'Attack',6,2,212,242,294,218,410,170,461,155),
(51,'Attack',6,3,212,242,294,242,410,242,461,242),
(52,'Attack',6,4,212,242,294,269,410,312,461,331),
(53,'Attack',6,5,212,242,294,260,410,285,611,331),
(54,'Attack',6,6,212,242,294,242,410,242,611,242),
(55,'SetH',1,1,212,331,212,331,212,331,212,331),
(56,'SetM',1,1,212,331,212,331,212,331,212,331),
(57,'SetL',1,1,212,331,212,331,212,331,212,331),
(58,'SetH',1,2,212,331,411,309,430,320,395,331),
(59,'SetM',1,2,212,331,411,309,430,320,395,331),
(60,'SetL',1,2,212,331,411,309,430,320,395,331),
(61,'SetH',1,3,212,331,411,284,430,262,395,240),
(62,'SetM',1,3,212,331,411,284,430,262,395,240),
(63,'SetL',1,3,212,331,411,284,430,262,395,240),
(64,'SetH',1,4,212,331,411,200,430,175,395,150),
(65,'SetM',1,4,212,331,411,200,430,175,395,150),
(66,'SetL',1,4,212,331,411,200,430,175,395,150),
(67,'SetH',1,5,212,331,411,274,430,212,285,155),
(68,'SetM',1,5,212,331,411,274,430,212,285,155),
(69,'SetL',1,5,212,331,411,274,430,212,285,155),
(70,'SetH',1,6,212,331,411,312,430,290,285,242),
(71,'SetM',1,6,212,331,411,312,430,290,285,242),
(72,'SetL',1,6,212,331,411,312,430,290,285,242),
(73,'SetH',2,1,361,331,430,300,360,309,285,331),
(74,'SetM',2,1,361,331,430,300,360,309,285,331),
(75,'SetL',2,1,361,331,430,300,360,309,285,331),
(76,'SetH',2,2,361,331,361,331,361,331,361,331),
(77,'SetM',2,2,361,331,361,331,361,331,361,331),
(78,'SetL',2,2,361,331,361,331,361,331,361,331),
(79,'SetH',2,3,361,331,411,313,430,285,395,242),
(80,'SetM',2,3,361,331,411,313,430,285,395,242),
(81,'SetL',2,3,361,331,411,313,430,285,395,242),
(82,'SetH',2,4,361,331,411,266,430,208,395,155),
(83,'SetM',2,4,361,331,411,266,430,208,395,155),
(84,'SetL',2,4,361,331,411,266,430,208,395,155),
(85,'SetH',2,5,361,331,411,300,430,250,285,155),
(86,'SetM',2,5,361,331,411,300,430,250,285,155),
(87,'SetL',2,5,361,331,411,300,430,250,285,155),
(88,'SetH',2,6,361,331,411,320,430,290,285,242),
(89,'SetM',2,6,361,331,411,320,430,290,285,242),
(90,'SetL',2,6,361,331,411,320,430,290,285,242),
(91,'SetH',3,1,361,242,411,265,430,295,285,331),
(92,'SetM',3,1,361,242,411,265,430,295,285,331),
(93,'SetL',3,1,361,242,411,265,430,295,285,331),
(94,'SetH',3,2,361,242,411,265,430,300,395,331),
(95,'SetM',3,2,361,242,411,265,430,300,395,331),
(96,'SetL',3,2,361,242,411,265,430,300,395,331),
(97,'SetH',3,3,361,242,361,242,361,242,361,242),
(98,'SetM',3,3,361,242,361,242,361,242,361,242),
(99,'SetL',3,3,361,242,361,242,361,242,361,242),
(100,'SetH',3,4,361,242,411,215,430,190,395,155),
(101,'SetM',3,4,361,242,411,215,430,190,395,155),
(102,'SetL',3,4,361,242,411,215,430,190,395,155),
(103,'SetH',3,5,361,242,411,225,430,200,285,155),
(104,'SetM',3,5,361,242,411,225,430,200,285,155),
(105,'SetL',3,5,361,242,411,225,430,200,285,155),
(106,'SetH',3,6,361,242,411,236,430,218,285,242),
(107,'SetM',3,6,361,242,411,236,430,218,285,242),
(108,'SetL',3,6,361,242,411,236,430,218,285,242),
(109,'SetH',4,1,361,155,411,164,430,190,285,331),
(110,'SetM',4,1,361,155,411,164,430,190,285,331),
(111,'SetL',4,1,361,155,411,164,430,190,285,331),
(112,'SetH',4,2,361,155,411,200,430,262,395,331),
(113,'SetM',4,2,361,155,411,200,430,262,395,331),
(114,'SetL',4,2,361,155,411,200,430,262,395,331),
(115,'SetH',4,3,361,155,411,185,430,210,395,242),
(116,'SetM',4,3,361,155,411,185,430,210,395,242),
(117,'SetL',4,3,361,155,411,185,430,210,395,242),
(118,'SetH',4,4,361,155,361,155,361,155,361,155),
(119,'SetM',4,4,361,155,361,155,361,155,361,155),
(120,'SetL',4,4,361,155,361,155,361,155,361,155),
(121,'SetH',4,5,361,155,411,165,430,190,285,155),
(122,'SetM',4,5,361,155,411,165,430,190,285,155),
(123,'SetL',4,5,361,155,411,165,430,190,285,155),
(124,'SetH',4,6,361,155,411,178,430,200,285,242),
(125,'SetM',4,6,361,155,411,178,430,200,285,242),
(126,'SetL',4,6,361,155,411,178,430,200,285,242),
(127,'SetH',5,1,212,155,411,220,430,260,285,331),
(128,'SetM',5,1,212,155,411,220,430,260,285,331),
(129,'SetL',5,1,212,155,411,220,430,260,285,331),
(130,'SetH',5,2,212,155,411,240,430,300,395,331),
(131,'SetM',5,2,212,155,411,240,430,300,395,331),
(132,'SetL',5,2,212,155,411,240,430,300,395,331),
(133,'SetH',5,3,212,155,411,200,430,220,395,242),
(134,'SetM',5,3,212,155,411,200,430,220,395,242),
(135,'SetL',5,3,212,155,411,200,430,220,395,242),
(136,'SetH',5,4,212,155,411,190,430,162,395,155),
(137,'SetM',5,4,212,155,411,190,430,162,395,155),
(138,'SetL',5,4,212,155,411,190,430,162,395,155),
(139,'SetH',5,5,212,155,212,155,212,155,212,155),
(140,'SetM',5,5,212,155,212,155,212,155,212,155),
(141,'SetL',5,5,212,155,212,155,212,155,212,155),
(142,'SetH',5,6,212,155,411,180,430,210,285,242),
(143,'SetM',5,6,212,155,411,180,430,210,285,242),
(144,'SetL',5,6,212,155,411,180,430,210,285,242),
(145,'SetH',6,1,212,242,411,260,430,296,285,331),
(146,'SetM',6,1,212,242,411,260,430,296,285,331),
(147,'SetL',6,1,212,242,411,260,430,296,285,331),
(148,'SetH',6,2,212,242,411,267,430,310,395,331),
(149,'SetM',6,2,212,242,411,267,430,310,395,331),
(150,'SetL',6,2,212,242,411,267,430,310,395,331),
(151,'SetH',6,3,212,242,411,210,430,230,395,242),
(152,'SetM',6,3,212,242,411,210,430,230,395,242),
(153,'SetL',6,3,212,242,411,210,430,230,395,242),
(154,'SetH',6,4,212,242,411,206,430,165,395,155),
(155,'SetM',6,4,212,242,411,206,430,165,395,155),
(156,'SetL',6,4,212,242,411,206,430,165,395,155),
(157,'SetH',6,5,212,242,411,220,430,187,285,155),
(158,'SetM',6,5,212,242,411,220,430,187,285,155),
(159,'SetL',6,5,212,242,411,220,430,187,285,155),
(160,'SetH',6,6,212,242,212,242,212,242,212,242),
(161,'SetM',6,6,212,242,212,242,212,242,212,242),
(162,'SetL',6,6,212,242,212,242,212,242,212,242),
(163,'Reception',1,1,212,331,212,331,212,331,212,331),
(164,'Reception',1,2,212,331,270,331,319,331,361,331),
(165,'Reception',1,3,212,331,274,292,325,256,361,242),
(166,'Reception',1,4,212,331,256,282,317,205,361,155),
(167,'Reception',1,5,212,331,212,284,212,190,212,155),
(168,'Reception',1,6,212,331,212,284,212,264,212,242),
(169,'Reception',2,1,361,331,307,331,260,331,212,331),
(170,'Reception',2,2,361,331,361,331,361,331,361,331),
(171,'Reception',2,3,361,331,361,298,361,297,361,242),
(172,'Reception',2,4,361,331,361,298,361,196,361,155),
(173,'Reception',2,5,361,331,314,276,259,209,212,155),
(174,'Reception',2,6,361,331,293,292,250,265,212,242),
(175,'Reception',3,1,361,242,294,281,255,307,212,331),
(176,'Reception',3,2,361,242,361,266,361,303,361,331),
(177,'Reception',3,3,361,242,361,242,361,242,361,242),
(178,'Reception',3,4,361,242,361,217,361,173,361,155),
(179,'Reception',3,5,361,242,303,211,251,176,212,155),
(180,'Reception',3,6,361,242,294,246,248,245,212,242),
(181,'Reception',4,1,361,155,315,211,266,273,212,331),
(182,'Reception',4,2,361,155,361,198,361,290,361,331),
(183,'Reception',4,3,361,155,361,198,361,220,361,242),
(184,'Reception',4,4,361,155,361,155,361,155,361,155),
(185,'Reception',4,5,361,155,294,160,253,155,212,155),
(186,'Reception',4,6,361,155,294,197,252,221,212,242),
(187,'Reception',5,1,212,155,212,210,212,280,212,331),
(188,'Reception',5,2,212,155,267,217,323,285,361,331),
(189,'Reception',5,3,212,155,280,196,316,216,361,242),
(190,'Reception',5,4,212,155,270,156,320,155,361,155),
(191,'Reception',5,5,212,155,212,155,212,155,212,155),
(192,'Reception',5,6,212,155,212,177,212,220,212,242),
(193,'Reception',6,1,212,242,212,270,212,306,212,331),
(194,'Reception',6,2,212,242,261,272,314,303,361,331),
(195,'Reception',6,3,212,242,268,242,319,242,361,242),
(196,'Reception',6,4,212,242,287,197,316,176,361,155),
(197,'Reception',6,5,212,242,212,200,212,175,212,155),
(198,'Reception',6,6,212,242,212,242,212,242,212,242),
(199,'Defence',1,1,212,331,212,331,212,331,212,331),
(200,'Defence',1,2,212,331,270,331,319,331,361,331),
(201,'Defence',1,3,212,331,274,292,325,256,361,242),
(202,'Defence',1,4,212,331,256,282,317,205,361,155),
(203,'Defence',1,5,212,331,212,284,212,190,212,155),
(204,'Defence',1,6,212,331,212,284,212,264,212,242),
(205,'Defence',2,1,361,331,307,331,260,331,212,331),
(206,'Defence',2,2,361,331,361,331,361,331,361,331),
(207,'Defence',2,3,361,331,361,298,361,297,361,242),
(208,'Defence',2,4,361,331,361,298,361,196,361,155),
(209,'Defence',2,5,361,331,314,276,259,209,212,155),
(210,'Defence',2,6,361,331,293,292,250,265,212,242),
(211,'Defence',3,1,361,242,294,281,255,307,212,331),
(212,'Defence',3,2,361,242,361,266,361,303,361,331),
(213,'Defence',3,3,361,242,361,242,361,242,361,242),
(214,'Defence',3,4,361,242,361,217,361,173,361,155),
(215,'Defence',3,5,361,242,303,211,251,176,212,155),
(216,'Defence',3,6,361,242,294,246,248,245,212,242),
(217,'Defence',4,1,361,155,315,211,266,273,212,331),
(218,'Defence',4,2,361,155,361,198,361,290,361,331),
(219,'Defence',4,3,361,155,361,198,361,220,361,242),
(220,'Defence',4,4,361,155,361,155,361,155,361,155),
(221,'Defence',4,5,361,155,294,160,253,155,212,155),
(222,'Defence',4,6,361,155,294,197,252,221,212,242),
(223,'Defence',5,1,212,155,212,210,212,280,212,331),
(224,'Defence',5,2,212,155,267,217,323,285,361,331),
(225,'Defence',5,3,212,155,280,196,316,216,361,242),
(226,'Defence',5,4,212,155,270,156,320,155,361,155),
(227,'Defence',5,5,212,155,212,155,212,155,212,155),
(228,'Defence',5,6,212,155,212,177,212,220,212,242),
(229,'Defence',6,1,212,242,212,270,212,306,212,331),
(230,'Defence',6,2,212,242,261,272,314,303,361,331),
(231,'Defence',6,3,212,242,268,242,319,242,361,242),
(232,'Defence',6,4,212,242,287,197,316,176,361,155),
(233,'Defence',6,5,212,242,212,200,212,175,212,155),
(234,'Defence',6,6,212,242,212,242,212,242,212,242),
(235,'BlockAttack',1,2,611,155,611,155,361,331,361,331),
(236,'BlockAttack',1,3,611,155,611,155,361,242,361,242),
(237,'BlockAttack',1,4,611,155,611,155,361,155,361,155),
(238,'BlockAttack',2,2,461,155,461,155,361,331,361,331),
(239,'BlockAttack',2,3,461,155,461,155,361,242,361,242),
(240,'BlockAttack',2,4,461,155,461,155,361,155,361,155),
(241,'BlockAttack',3,2,461,242,461,242,361,331,361,331),
(242,'BlockAttack',3,3,461,242,461,242,361,242,361,242),
(243,'BlockAttack',3,4,461,242,461,242,361,155,361,155),
(244,'BlockAttack',4,2,461,331,461,331,361,331,361,331),
(245,'BlockAttack',4,3,461,331,461,331,361,242,361,242),
(246,'BlockAttack',4,4,461,331,461,331,361,155,361,155),
(247,'BlockAttack',5,2,611,331,611,331,361,331,361,331),
(248,'BlockAttack',5,3,611,331,611,331,361,242,361,242),
(249,'BlockAttack',5,4,611,331,611,331,361,155,361,155),
(250,'BlockAttack',6,2,611,242,611,242,361,331,361,331),
(251,'BlockAttack',6,3,611,242,611,242,361,242,361,242),
(252,'BlockAttack',6,4,611,242,611,242,361,155,361,155),
(253,'BlockRH',2,1,361,331,361,331,212,331,212,331),
(254,'BlockRH',2,2,361,331,361,331,361,331,361,331),
(255,'BlockRH',2,3,361,331,361,331,361,242,361,242),
(256,'BlockRH',2,4,361,331,361,331,361,155,361,155),
(257,'BlockRH',2,5,361,331,361,331,212,155,212,155),
(258,'BlockRH',2,6,361,331,361,331,212,242,212,242),
(259,'BlockRH',2,7,361,331,361,331,295,80,295,80),
(260,'BlockRH',2,8,361,331,361,331,295,405,295,405),
(261,'BlockRH',2,9,361,331,361,331,55,242,55,242),
(262,'BlockRH',3,1,361,242,361,242,212,331,212,331),
(263,'BlockRH',3,2,361,242,361,242,361,331,361,331),
(264,'BlockRH',3,3,361,242,361,242,361,242,361,242),
(265,'BlockRH',3,4,361,242,361,242,361,155,361,155),
(266,'BlockRH',3,5,361,242,361,242,212,155,212,155),
(267,'BlockRH',3,6,361,242,361,242,212,242,212,242),
(268,'BlockRH',3,7,361,242,361,242,295,80,295,80),
(269,'BlockRH',3,8,361,242,361,242,295,405,295,405),
(270,'BlockRH',3,9,361,242,361,242,55,242,55,242),
(271,'BlockRH',4,1,361,155,361,155,212,331,212,331),
(272,'BlockRH',4,2,361,155,361,155,361,331,361,331),
(273,'BlockRH',4,3,361,155,361,155,361,242,361,242),
(274,'BlockRH',4,4,361,155,361,155,361,155,361,155),
(275,'BlockRH',4,5,361,155,361,155,212,155,212,155),
(276,'BlockRH',4,6,361,155,361,155,212,242,212,242),
(277,'BlockRH',4,7,361,155,361,155,295,80,295,80),
(278,'BlockRH',4,8,361,155,361,155,295,405,295,405),
(279,'BlockRH',4,9,361,155,361,155,55,242,55,242),
(280,'BlockRO',2,1,361,331,361,331,611,155,611,155),
(281,'BlockRO',2,2,361,331,361,331,461,155,461,155),
(282,'BlockRO',2,3,361,331,361,331,461,242,461,242),
(283,'BlockRO',2,4,361,331,361,331,461,331,461,331),
(284,'BlockRO',2,5,361,331,361,331,611,331,611,331),
(285,'BlockRO',2,6,361,331,361,331,611,242,611,242),
(286,'BlockRO',2,7,361,331,361,331,525,405,525,405),
(287,'BlockRO',2,8,361,331,361,331,525,80,525,80),
(288,'BlockRO',2,9,361,331,361,331,745,242,745,242),
(289,'BlockRO',3,1,361,242,361,242,611,155,611,155),
(290,'BlockRO',3,2,361,242,361,242,461,155,461,155),
(291,'BlockRO',3,3,361,242,361,242,461,242,461,242),
(292,'BlockRO',3,4,361,242,361,242,461,331,461,331),
(293,'BlockRO',3,5,361,242,361,242,611,331,611,331),
(294,'BlockRO',3,6,361,242,361,242,611,242,611,242),
(295,'BlockRO',3,7,361,242,361,242,525,405,525,405),
(296,'BlockRO',3,8,361,242,361,242,525,80,525,80),
(297,'BlockRO',3,9,361,242,361,242,745,242,745,242),
(298,'BlockRO',4,1,361,155,361,155,611,155,611,155),
(299,'BlockRO',4,2,361,155,361,155,461,155,461,155),
(300,'BlockRO',4,3,361,155,361,155,461,242,461,242),
(301,'BlockRO',4,4,361,155,361,155,461,331,461,331),
(302,'BlockRO',4,5,361,155,361,155,611,331,611,331),
(303,'BlockRO',4,6,361,155,361,155,611,242,611,242),
(304,'BlockRO',4,7,361,155,361,155,525,405,525,405),
(305,'BlockRO',4,8,361,155,361,155,525,80,525,80),
(306,'BlockRO',4,9,361,155,361,155,745,242,745,242);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
