/*
SQLyog Community
MySQL - 5.5.33 : Database - vollyball1
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
(1,'Vollyball Season 2018','India','2018-10-01','2018-10-31','Open',0);

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
  PRIMARY KEY (`id`),
  KEY `skill_desc_criteria_id` (`skill_desc_criteria_id`),
  CONSTRAINT `m_skill_desc_criteria_point_ibfk_1` FOREIGN KEY (`skill_desc_criteria_id`) REFERENCES `m_skills_desc_criteria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_skill_desc_criteria_point` */

insert  into `m_skill_desc_criteria_point`(`id`,`type`,`abbreviation`,`skill_desc_criteria_id`) values 
(1,'','JF',1),
(2,'','JP',1),
(3,'','SF',1),
(4,'','SS',1),
(5,'','JS',1),
(6,'','DC',2),
(7,'','C',3),
(8,'','L',3),
(9,'','1',4),
(10,'','5',4),
(11,'','6',4),
(12,'','1',5),
(13,'','2',5),
(14,'','3',5),
(15,'','4',5),
(16,'','5',5),
(17,'','6',5),
(18,'','5',6),
(19,'','4',6),
(20,'','3',6),
(21,'','2',6),
(22,'','OH',7),
(23,'','MB',7),
(24,'','L',7),
(25,'','U',7),
(26,'','0',8),
(27,'','RC',9),
(28,'','SC',9),
(29,'','FR',10),
(30,'','FM',10),
(31,'','FL',10),
(32,'','RR',10),
(33,'','RM',10),
(34,'','RL',10),
(35,'','IN',11),
(36,'','OT',11),
(37,'','BT',11),
(38,'','OL',11),
(39,'','D',11),
(40,'','BC',11),
(41,'','4C',12),
(42,'','3C',12),
(43,'','2C',12),
(44,'','5C',12),
(45,'','DC',13),
(46,'','PP',13),
(47,'','SP',13),
(48,'','MB',13),
(49,'','BP',13),
(50,'','NSE',13),
(51,'','NEL',13),
(52,'','LOW',14),
(53,'','MEDIUM',14),
(54,'','HIGH',14),
(55,'','1',15),
(56,'','2',15),
(57,'','3',15),
(58,'','4',15),
(59,'','5',15),
(60,'','6',15),
(61,'','1',16),
(62,'','2',16),
(63,'','3',16),
(64,'','4',16),
(65,'','5',16),
(66,'','6',16),
(67,'','K1',17),
(68,'','K2',17),
(69,'','TPS',17),
(70,'','OH',18),
(71,'','MB',18),
(72,'','U',18),
(73,'','S',18),
(74,'','SGL',19),
(75,'','DBL',19),
(76,'','TPL',19),
(77,'','1-2-3',20),
(78,'','1-3-2',20),
(79,'','2-1-2',20),
(80,'','2-0-4',20),
(81,'','3-1-2',20),
(82,'','3-0-3',20),
(83,'','3-2-1',20),
(84,'','1',21),
(85,'','2',21),
(86,'','3',21),
(87,'','4',21),
(88,'','5',21),
(89,'','6',21),
(90,'','S',22),
(91,'','OH',22),
(92,'','MB',22),
(93,'','L',22),
(94,'','U',22),
(95,'','00:00',23),
(96,'','FR',24),
(97,'','FM',24),
(98,'','FL',24),
(99,'','RR',24),
(100,'','RM',24),
(101,'','RL',24),
(102,'','C',25),
(103,'','L',25),
(104,'','Commit',26),
(105,'','Read and react',26),
(106,'','Stack or zone',26),
(107,'','Commit',27),
(108,'','Read and react',27),
(109,'','Stack or zone',27),
(110,'','Kill',28),
(111,'','Soft',28),
(112,'','LC',28),
(113,'','LO',28),
(114,'','LI',28),
(115,'','CB',28),
(116,'','C',29),
(117,'','L',29),
(118,'','IN',29),
(119,'','OT',29),
(120,'','BT',29),
(121,'','OL',29),
(122,'','D',29),
(123,'','BC',29),
(124,'','4C',30),
(125,'','3C',30),
(126,'','2C',30),
(127,'','5C',30),
(128,'','LOW',31),
(129,'','MEDIUM',31),
(130,'','HIGH',31),
(131,'','1',32),
(132,'','2',32),
(133,'','3',32),
(134,'','4',32),
(135,'','5',32),
(136,'','6',32),
(137,'','2',33),
(138,'','3',33),
(139,'','4',33),
(140,'','1',34),
(141,'','2',34),
(142,'','3',34),
(143,'','4',34),
(144,'','5',34),
(145,'','6',34),
(146,'','LOC',34),
(147,'','ROC',34),
(148,'','BOC',34),
(149,'','K1',35),
(150,'','K2',35),
(151,'','TP',35),
(152,'','OH',36),
(153,'','MB',36),
(154,'','U',36),
(155,'','S',36),
(156,'','SGL',37),
(157,'','DBL',37),
(158,'','TPL',37),
(159,'','1-2-3',38),
(160,'','1-3-2',38),
(161,'','2-1-2',38),
(162,'','2-0-4',38),
(163,'','3-1-2',38),
(164,'','3-0-3',38),
(165,'','3-2-1',38),
(166,'','H',39),
(167,'','OPP',39),
(168,'','00:00',40),
(169,'','FR',41),
(170,'','FM',41),
(171,'','FL',41),
(172,'','RR',41),
(173,'','RM',41),
(174,'','RL',41),
(175,'','JS',42),
(176,'','RB',42),
(177,'','FP',42),
(178,'','HP',42),
(179,'','BC',42),
(180,'','HIGH',43),
(181,'','MEDIUM',43),
(182,'','LOW',43),
(183,'','ON',44),
(184,'','CN',44),
(185,'','AN',44),
(186,'','LT',44),
(187,'','5',45),
(188,'','4',45),
(189,'','3',45),
(190,'','2',45),
(191,'','Favourable',46),
(192,'','Semi Favourable',46),
(193,'','Non Favourable',46),
(194,'','1',47),
(195,'','2',47),
(196,'','3',47),
(197,'','4',47),
(198,'','5',47),
(199,'','6',47),
(200,'','1',48),
(201,'','2',48),
(202,'','3',48),
(203,'','4',48),
(204,'','5',48),
(205,'','6',48),
(206,'','4C',49),
(207,'','3C',49),
(208,'','2C',49),
(209,'','5C',49),
(210,'','SGL',50),
(211,'','DBL',50),
(212,'','TPL',50),
(213,'','NB',50),
(214,'','K1',51),
(215,'','K2',51),
(216,'','TP',51),
(217,'','OH',52),
(218,'','MB',52),
(219,'','U',52),
(220,'','S',52),
(221,'','00:00',53),
(222,'','JF',54),
(223,'','JS',54),
(224,'','JP',54),
(225,'','SF',54),
(226,'','SS',54),
(227,'','2',55),
(228,'','3',55),
(229,'','4',55),
(230,'','5',55),
(231,'','1',56),
(232,'','2',56),
(233,'','3',56),
(234,'','4',56),
(235,'','5',56),
(236,'','6',56),
(237,'','1',57),
(238,'','2',57),
(239,'','3',57),
(240,'','4',57),
(241,'','5',57),
(242,'','6',57),
(243,'','OH',58),
(244,'','MB',58),
(245,'','U',58),
(246,'','S',58),
(247,'','L',58),
(248,'','Favourable',59),
(249,'','Semi Favourable',59),
(250,'','Non Favourable',59),
(251,'','ON',60),
(252,'','CN',60),
(253,'','AN',60),
(254,'','LT',60),
(265,'','00:00',63),
(266,'','FR',64),
(267,'','FM',64),
(268,'','FL',64),
(269,'','RR',64),
(270,'','RM',64),
(271,'','RL',64),
(272,'','IN',65),
(273,'','OT',65),
(274,'','BT',65),
(275,'','OL',65),
(276,'','D',65),
(277,'','BC',65),
(278,'','HIGH',66),
(279,'','MEDIUM',66),
(280,'','LOW',66),
(281,'','5C',67),
(282,'','4C',67),
(283,'','3C',67),
(284,'','2C',67),
(285,'','1C',67),
(286,'','4',68),
(287,'','3',68),
(288,'','2',68),
(289,'','SGL',69),
(290,'','DBL',69),
(291,'','TPL',69),
(292,'','LO',70),
(293,'','LC',70),
(294,'','1-2-3',71),
(295,'','1-3-2',71),
(296,'','2-1-2',71),
(297,'','2-0-4',71),
(298,'','3-1-2',71),
(299,'','3-0-3',71),
(300,'','3-2-1',71),
(301,'','1',72),
(302,'','2',72),
(303,'','3',72),
(304,'','4',72),
(305,'','5',72),
(306,'','6',72),
(307,'','1',73),
(308,'','2',73),
(309,'','3',73),
(310,'','4',73),
(311,'','5',73),
(312,'','6',73),
(313,'','OH',74),
(314,'','MB',74),
(315,'','U',74),
(316,'','S',74),
(317,'','L',74),
(318,'','ON',75),
(319,'','CN',75),
(320,'','AN',75),
(321,'','LT',75),
(322,'','Favourable',76),
(323,'','Semi Favourable',76),
(324,'','Non Favourable',76),
(325,'','K1',77),
(326,'','K2',77),
(327,'','TP',77),
(328,'','FR',78),
(329,'','FM',78),
(330,'','FL',78),
(331,'','RR',78),
(332,'','RM',78),
(333,'','RL',78),
(341,'','00:00',80),
(342,'','C',81),
(343,'','L',81),
(344,'','',82),
(345,'','',83),
(346,'','',84),
(347,'','',85),
(348,'','',86),
(349,'','',87),
(350,'','C',88),
(351,'','L',88),
(352,'','ANF',44),
(353,'','ANF',60),
(354,'','1C',12),
(355,'','NC',12),
(356,'','1C',30),
(357,'','NC',30),
(358,'','1C',49),
(359,'','nC',49),
(361,'','NC',67),
(362,'','BT',13),
(363,'','R',11),
(364,'','BTL',11),
(365,'','R',29),
(366,'','BTL',29),
(367,'','R',65),
(368,'','BTL',65),
(369,'','R',42),
(370,'','R',28),
(371,'','2-1-3',20),
(372,'','Non Organised',20),
(373,'','2-1-3',38),
(374,'','Non Organised',38),
(375,'','2-1-3',71),
(376,'','Non Organised',71),
(377,'','C',70),
(378,'','S',70),
(379,'','ODB',14),
(380,'','ODB',31),
(381,'','ODB',43),
(383,'','ODB',66),
(384,'','1-2-3',89),
(385,'','1-3-2',89),
(386,'','2-1-2',89),
(387,'','2-0-4',89),
(388,'','3-1-2',89),
(389,'','3-0-3',89),
(400,'','3-2-1',89),
(401,'','2-1-3',89),
(402,'','Non Organised',89),
(403,'','HA',90),
(404,'','HB',90),
(405,'','HD',90),
(406,'','OA',90),
(407,'','1',91),
(408,'','2',91),
(409,'','3',91),
(410,'','4',91),
(411,'','5',91),
(412,'','6',91),
(413,'','LOC',91),
(414,'','ROC',91),
(415,'','BOC',91),
(416,'','1',92),
(417,'','2',92),
(418,'','3',92),
(419,'','4',92),
(420,'','5',92),
(421,'','6',92),
(422,'','1',93),
(423,'','2',93),
(424,'','3',93),
(425,'','4',93),
(426,'','5',93),
(427,'','6',93),
(428,'','C',94),
(429,'','S',94),
(430,'','D',94),
(431,'','NB',19),
(432,'','NB',37),
(433,'','NB',69),
(434,'','1-2-3',95),
(435,'','1-3-2',95),
(436,'','2-1-2',95),
(487,'','2-0-4',95),
(488,'','3-1-2',95),
(489,'','3-0-3',95),
(590,'','3-2-1',95),
(591,'','2-1-3',95),
(592,'','Non Organised',95),
(593,'','OB',90),
(594,'','None',26),
(595,'','None',27);

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
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `m_skills_desc_criteria_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `m_skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_skills_desc_criteria` */

insert  into `m_skills_desc_criteria`(`id`,`type`,`skill_id`) values 
(1,' Type & Techniques of Service',1),
(2,' Serve Tactics',1),
(3,' Direction of Service',1),
(4,' Serve From Zone',1),
(5,' Serve To Zone',1),
(6,' Reception formation',1),
(7,' Receiver Position',1),
(8,' Score at the time of ace serve',1),
(9,' Serve in situation',1),
(10,' Opponent Setter Position',1),
(11,' Type & Techniques of attack',2),
(12,' Attack Combination',2),
(13,' Attacking Tactics',2),
(14,' Attack on Tempo',2),
(15,' Attacking From Zone',2),
(16,'Attacking To Zone',2),
(17,' Attack in phase',2),
(18,' Attackers position',2),
(19,'No. of Blockers',2),
(20,' Opponent Defence Formation',2),
(21,' Attack Defended Zone',2),
(22,' Defender Role',2),
(23,' Score at time of attack',2),
(24,' Setter position',2),
(25,'Direction of Attack',2),
(26,'Type of Block',2),
(27,' Type of Block',3),
(28,'Technique of Block',3),
(29,'Block on Type of Attack',3),
(30,' Block on Combination of attack',3),
(31,' Block on Attacking Tempo',3),
(32,'Opponent Attacking Zone',3),
(33,' Blocking Zone',3),
(34,'Block Deflected ball at Zone',3),
(35,' Blocking in phase',3),
(36,' Blockers Position',3),
(37,' No. of Blockers',3),
(38,' Defence Formation',3),
(39,' Block Defended court',3),
(40,' Score at time of Block',3),
(41,'Opponent Setter Position',3),
(42,' Type of Set',4),
(43,' Set Tempo',4),
(44,' Reception at',4),
(45,'Reception Formation',4),
(46,'Parabolla of received ball',4),
(47,'Set delivery from Zone',4),
(48,'Set delivery to Zone',4),
(49,'Combination of attack',4),
(50,'No. of Blockers',4),
(51,'Game of phase',4),
(52,'Attackers position',4),
(53,'Score at the time of set',4),
(54,'Type of Serve',5),
(55,'Reception Formation',5),
(56,'Reception From Zone',5),
(57,'Reception To Zone',5),
(58,'Receiver Position',5),
(59,'Parabola of Received ball for setter',5),
(60,'Reception at',5),
(63,'Score at the time of Reception',5),
(64,'Setter Position',5),
(65,'Type of Attack by opponent',6),
(66,'Attack on Tempo',6),
(67,'Combination of Attack',6),
(68,'Blocking at Zone',6),
(69,'No. of Blockers',6),
(70,'Block Cover',6),
(71,'Defence System ',6),
(72,'Defence Sent From Zone',6),
(73,'Defence Sent To Zone',6),
(74,'Defenders Role',6),
(75,'Defence Ball At',6),
(76,'Parabola of Defended Ball for Setter',6),
(77,' Defence in phase',6),
(78,' Setter position',6),
(80,' Score at time of Defence',6),
(81,'Direction of Attack',6),
(82,'Diagram Points',1),
(83,'Diagram Points',2),
(84,'Diagram Points',3),
(85,'Diagram Points',4),
(86,'Diagram Points',5),
(87,'Diagram Points',6),
(88,'Direction Of Block',3),
(89,'Attack Cover',4),
(90,'Type of Defended Ball',6),
(91,'Ball Reflected Zone',2),
(92,'Attack Approach Run from',2),
(93,'Attack Approach Run to',2),
(94,'Type of approach run',2),
(95,'Attack Cover',6);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_set` */

insert  into `match_evaluation_set`(`id`,`match_evaluation_team_id`,`set_no`,`homescore`,`opponentscore`,`won_by`,`start_time`,`end_time`,`evaluator`,`date`) values 
(1,1,1,25,15,1,'16:16:00','16:54:00','','2018-11-09'),
(2,1,2,0,2,0,'17:18:00','00:00:00','','2018-11-09'),
(3,1,3,0,1,0,'18:31:00','00:00:00','','2018-11-09'),
(4,2,1,0,0,0,'19:18:00','00:00:00','','2018-11-09'),
(5,2,2,0,0,0,'19:21:00','00:00:00','John','2018-11-09');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_set_latest_rotation` */

insert  into `match_evaluation_set_latest_rotation`(`id`,`pos1playerId`,`pos2playerId`,`pos3playerId`,`pos4playerId`,`pos5playerId`,`pos6playerId`,`match_evaluation_set_id`,`pos1OppplayerId`,`pos2OppplayerId`,`pos3OppplayerId`,`pos4OppplayerId`,`pos5OppplayerId`,`pos6OppplayerId`) values 
(1,1,2,3,4,5,6,1,13,14,15,16,17,18),
(2,6,1,2,3,10,7,2,18,13,14,15,20,17),
(3,1,2,3,4,5,6,3,18,13,20,15,16,17),
(4,18,13,14,15,16,17,4,1,2,3,4,5,6),
(5,17,18,13,14,15,16,5,6,1,2,3,4,5);

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `match_players` */

insert  into `match_players`(`id`,`match_id`,`team1`,`player_id`) values 
(1,1,1,1),
(2,1,1,2),
(3,1,1,3),
(4,1,1,4),
(5,1,1,5),
(6,1,1,6),
(7,1,1,7),
(8,1,1,8),
(9,1,1,9),
(10,1,1,10),
(11,1,1,11),
(12,1,1,12),
(13,1,2,13),
(14,1,2,14),
(15,1,2,15),
(16,1,2,16),
(17,1,2,17),
(18,1,2,18),
(19,1,2,19),
(20,1,2,20),
(21,1,2,21),
(22,1,2,22),
(23,1,2,23),
(24,1,2,24);

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
(1,NULL,'2018-10-30','11:05:00',1,1,2,30,1,'Final','Mumbai',NULL,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `players` */

insert  into `players`(`id`,`chest_num`,`name`,`position`,`is_captain`,`team_id`,`isdeleted`) values 
(1,'7','Ashwin',6,0,1,0),
(2,'12','Darsh',4,0,1,0),
(3,'11','Farid',5,0,1,0),
(4,'3','Ajit',3,0,1,0),
(5,'4','Ishaan',4,0,1,0),
(6,'8','Josef',5,0,1,0),
(7,'1','Satish',2,0,1,1),
(8,'9','Rohan',3,1,1,0),
(9,'2','Yogesh',4,0,1,0),
(10,'6','Avinash',4,0,1,0),
(11,'10','Viraj',5,0,1,0),
(12,'5','Rohit',6,0,1,0),
(13,'16','Jitesh',5,0,2,0),
(14,'9','Prashant',3,0,2,0),
(15,'1','Ganesh',4,0,2,0),
(16,'6','Ravi',5,0,2,0),
(17,'18','Vijay',6,1,2,0),
(18,'10','Kalyan',4,0,2,0),
(19,'8','Akshay',2,0,2,0),
(20,'7','Amey',4,0,2,0),
(21,'3','Rajesh',6,0,2,0),
(22,'5','Saurabh',3,0,2,0),
(23,'11','Mangesh',4,0,2,0),
(24,'4','Aryan',3,0,2,0),
(25,'13','Sandesh',2,0,1,1),
(26,'14','av',1,0,1,1),
(27,'1','Mangesh',2,0,1,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `rally` */

insert  into `rally`(`id`,`number`,`home_score`,`opponent_score`,`start_time`,`end_time`,`evaluation_id`,`start_by`,`wonby`) values 
(1,1,0,1,'16:21:03','16:21:10',1,1,2),
(2,2,1,1,'16:21:12','16:21:25',1,2,1),
(3,3,1,2,'16:21:28','16:21:41',1,1,2),
(4,4,2,2,'16:21:45','16:21:55',1,2,1),
(5,5,2,3,'16:21:56','16:22:02',1,1,2),
(6,6,2,4,'16:22:04','16:23:13',1,2,2),
(7,7,3,4,'16:23:16','16:23:31',1,2,1),
(8,8,3,5,'16:23:34','16:24:30',1,1,2),
(9,9,4,5,'16:24:32','16:24:50',1,2,1),
(10,10,4,6,'16:24:51','16:25:10',1,1,2),
(11,11,5,6,'16:25:13','16:25:13',1,2,1),
(12,12,5,7,'16:25:15','16:25:33',1,1,2),
(13,13,6,7,'16:45:07','16:45:20',1,2,1),
(14,14,7,7,'16:45:22','16:45:28',1,1,1),
(15,15,7,8,'16:45:30','16:45:33',1,1,2),
(16,16,8,8,'16:45:36','16:46:30',1,2,1),
(17,17,8,9,'16:46:31','16:46:33',1,1,2),
(18,18,9,9,'16:46:34','16:46:42',1,2,1),
(19,19,10,9,'16:46:44','16:47:01',1,1,1),
(20,20,11,9,'16:47:03','16:47:30',1,1,1),
(21,21,12,9,'16:47:32','16:48:29',1,1,1),
(22,22,13,9,'16:48:30','16:48:31',1,1,1),
(23,23,14,9,'16:48:56','16:49:08',1,1,1),
(24,24,15,9,'16:49:10','16:49:13',1,1,1),
(25,25,15,10,'16:49:14','16:49:21',1,1,2),
(26,26,16,10,'16:49:24','16:49:37',1,2,1),
(27,27,17,10,'16:49:39','16:49:42',1,1,1),
(28,28,18,10,'16:49:43','16:49:57',1,1,1),
(29,29,19,10,'16:49:59','16:50:00',1,1,1),
(30,30,19,11,'16:50:01','16:51:08',1,1,2),
(31,31,20,11,'16:51:11','16:51:34',1,2,1),
(32,32,20,12,'16:51:36','16:51:42',1,1,2),
(33,33,20,13,'16:51:44','16:52:03',1,2,2),
(34,34,21,13,'16:52:05','16:52:14',1,2,1),
(35,35,22,13,'16:52:42','16:52:53',1,1,1),
(36,36,22,14,'16:52:55','16:53:02',1,1,2),
(37,37,23,14,'16:53:03','16:53:24',1,2,1),
(38,38,24,14,'16:53:28','16:53:46',1,1,1),
(39,39,24,15,'16:53:48','16:53:53',1,1,2),
(40,40,25,15,'16:54:17','16:54:17',1,2,1),
(41,1,0,1,'17:41:00','17:41:04',2,1,2),
(42,2,0,2,'17:51:26','17:51:42',2,1,2),
(43,1,0,1,'18:40:46','18:42:24',3,1,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=latin1;

/*Data for the table `rally_details` */

insert  into `rally_details`(`id`,`skill`,`chest_no`,`rating`,`order_num`,`rally_id`,`code`) values 
(1,1,1,2,1,1,'q-7-x'),
(2,3,7,1,1,1,'t-1-z'),
(3,5,4,4,1,2,'w-3-v'),
(4,4,1,3,1,2,'r-7-c'),
(5,2,2,5,1,2,'e-12-b'),
(6,1,2,3,1,3,'q-12-c'),
(7,3,3,2,1,3,'t-11-x'),
(8,6,2,1,1,3,'y-12-z'),
(9,5,5,4,1,4,'w-4-v'),
(10,4,1,3,1,4,'r-7-c'),
(11,2,3,5,1,4,'e-11-b'),
(12,1,3,3,1,5,'q-11-c'),
(13,3,6,1,1,5,'t-8-z'),
(14,5,5,2,1,6,'w-4-x'),
(15,6,1,3,1,6,'y-7-c'),
(16,2,2,3,1,6,'e-12-c'),
(17,3,4,3,1,6,'t-3-c'),
(18,6,2,3,1,6,'y-12-c'),
(19,6,1,2,1,6,'y-7-x'),
(20,6,5,1,1,6,'y-4-z'),
(21,5,5,4,1,7,'w-4-v'),
(22,4,1,3,1,7,'r-7-c'),
(23,2,6,3,1,7,'e-8-c'),
(24,6,2,5,1,7,'y-12-b'),
(25,4,1,4,1,7,'r-7-v'),
(26,2,4,5,1,7,'e-3-b'),
(27,1,4,2,1,8,'q-3-x'),
(28,6,2,2,1,8,'y-12-x'),
(29,6,4,2,1,8,'y-3-x'),
(30,6,5,1,1,8,'y-4-z'),
(31,5,7,5,1,9,'w-1-b'),
(32,4,1,3,1,9,'r-7-c'),
(33,2,5,2,1,9,'e-4-x'),
(34,6,7,5,1,9,'y-1-b'),
(35,4,1,4,1,9,'r-7-v'),
(36,2,6,5,1,9,'e-8-b'),
(37,1,5,3,1,10,'q-4-c'),
(38,6,4,5,1,10,'y-3-b'),
(39,4,1,4,1,10,'r-7-v'),
(40,2,2,4,1,10,'e-12-v'),
(41,6,7,5,1,10,'y-1-b'),
(42,2,1,1,1,10,'e-7-z'),
(43,7,0,5,1,11,'u'),
(44,1,6,2,1,12,'q-8-x'),
(45,3,3,1,2,12,'T-11-B2-Z'),
(48,5,5,5,1,13,'w-4-b'),
(49,4,1,4,1,13,'r-7-v'),
(50,2,2,5,1,13,'e-12-b'),
(51,1,1,3,1,14,'q-7-c'),
(52,7,0,5,1,14,'u'),
(53,1,1,2,1,15,'q-7-x'),
(54,3,7,1,1,15,'t-1-z'),
(55,5,5,5,1,16,'w-4-b'),
(56,4,1,3,1,16,'r-7-c'),
(57,2,3,3,1,16,'e-11-c'),
(58,6,1,3,1,16,'y-7-c'),
(59,6,7,4,1,16,'y-1-v'),
(60,2,2,3,1,16,'e-12-c'),
(61,7,0,5,1,16,'u'),
(62,1,2,1,1,17,'q-12-z'),
(63,5,7,3,1,18,'w-1-c'),
(64,4,1,4,1,18,'r-7-v'),
(65,2,4,3,1,18,'e-3-c'),
(66,7,0,5,1,18,'u'),
(67,1,3,3,1,19,'q-11-c'),
(68,3,6,3,1,19,'t-8-c'),
(69,3,6,3,1,19,'t-8-c'),
(70,6,5,5,1,19,'y-4-b'),
(71,4,1,4,1,19,'r-7-v'),
(72,2,2,4,1,19,'e-12-v'),
(73,3,6,5,1,19,'t-8-b'),
(74,1,3,3,1,20,'q-11-c'),
(75,6,5,3,1,20,'y-4-c'),
(76,4,1,5,1,20,'r-7-b'),
(77,2,4,2,1,20,'e-3-x'),
(78,6,6,5,1,20,'y-8-b'),
(79,4,1,4,1,20,'r-7-v'),
(80,2,5,5,1,20,'e-4-b'),
(81,1,3,3,1,21,'q-11-c'),
(82,3,6,2,1,21,'t-8-x'),
(83,6,2,2,1,21,'y-12-x'),
(84,6,1,2,1,21,'y-7-x'),
(85,3,5,5,1,21,'t-4-b'),
(86,1,3,2,1,22,'Q-11-11--X'),
(87,7,0,5,2,22,'u'),
(88,1,3,3,1,23,'q-11-c'),
(89,6,2,5,1,23,'y-12-b'),
(90,4,1,4,1,23,'r-7-v'),
(91,2,6,5,1,23,'e-8-b'),
(92,1,3,3,1,24,'q-11-c'),
(93,7,0,5,1,24,'u'),
(94,1,3,3,1,25,'q-11-c'),
(95,3,5,1,1,25,'t-4-z'),
(96,5,5,3,1,26,'w-4-c'),
(97,4,1,3,1,26,'r-7-c'),
(98,2,6,5,1,26,'e-8-b'),
(99,1,4,5,1,27,'q-3-b'),
(100,1,4,2,1,28,'q-3-x'),
(101,3,5,4,1,28,'t-4-v'),
(102,6,7,3,1,28,'y-1-c'),
(103,4,1,3,1,28,'r-7-c'),
(104,2,5,3,1,28,'e-4-c'),
(105,7,0,5,1,28,'u'),
(106,1,4,5,1,29,'q-3-b'),
(107,1,4,3,1,30,'q-3-c'),
(108,6,2,2,1,30,'y-12-x'),
(109,6,5,4,1,30,'y-4-v'),
(110,2,2,2,1,30,'e-12-x'),
(111,3,6,2,1,30,'t-8-x'),
(112,8,0,1,1,30,'i'),
(113,5,5,5,1,31,'w-4-b'),
(114,4,1,3,1,31,'r-7-c'),
(115,2,6,3,1,31,'e-8-c'),
(116,6,7,4,1,31,'y-1-v'),
(117,4,1,4,1,31,'r-7-v'),
(118,2,6,3,1,31,'e-8-c'),
(119,6,2,4,1,31,'y-12-v'),
(120,4,1,3,1,31,'r-7-c'),
(121,2,5,5,1,31,'e-4-b'),
(122,1,5,2,1,32,'q-4-x'),
(123,2,1,3,1,32,'e-7-c'),
(124,6,6,1,1,32,'y-8-z'),
(125,5,2,2,1,33,'w-12-x'),
(126,6,1,4,1,33,'y-7-v'),
(127,2,2,3,1,33,'e-12-c'),
(128,6,2,2,1,33,'y-12-x'),
(129,6,4,3,1,33,'y-3-c'),
(130,6,1,1,1,33,'y-7-z'),
(131,5,7,4,1,34,'w-1-v'),
(132,6,1,2,1,34,'y-7-x'),
(133,2,2,3,1,34,'e-12-c'),
(134,2,2,5,1,34,'e-12-b'),
(135,1,6,3,1,35,'q-8-c'),
(136,6,5,5,1,35,'y-4-b'),
(137,4,1,3,1,35,'r-7-c'),
(138,2,2,3,1,35,'e-12-c'),
(139,7,0,5,1,35,'u'),
(140,1,6,3,1,36,'q-8-c'),
(141,6,3,5,1,36,'y-11-b'),
(142,2,1,1,1,36,'e-7-z'),
(143,5,5,5,1,37,'w-4-b'),
(144,4,1,3,1,37,'r-7-c'),
(145,2,3,3,1,37,'e-11-c'),
(146,3,3,5,1,37,'t-11-b'),
(147,1,1,3,1,38,'q-7-c'),
(148,3,4,4,1,38,'t-3-v'),
(149,6,5,2,1,38,'y-4-x'),
(150,6,2,3,1,38,'y-12-c'),
(151,6,5,3,1,38,'y-4-c'),
(152,7,0,5,1,38,'u'),
(153,1,1,3,1,39,'q-7-c'),
(154,3,4,1,1,39,'t-3-z'),
(155,7,0,5,1,40,'u'),
(156,1,6,1,1,41,'q-8-z'),
(157,1,6,3,1,42,'q-8-c'),
(159,3,2,1,2,42,'t-12-z'),
(160,1,1,3,1,43,'Q-7-S1-7-H62D-O61B--C'),
(161,3,5,1,2,43,'t-4-o3b-h51b-h62d-z');

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
) ENGINE=InnoDB AUTO_INCREMENT=2394 DEFAULT CHARSET=latin1;

/*Data for the table `rally_details_criteria` */

insert  into `rally_details_criteria`(`id`,`skill_desc_criteria_id`,`type`,`rally_details_id`) values 
(1,82,'7-',1),
(2,1,'',1),
(3,6,'',1),
(4,2,'',1),
(5,3,'',1),
(6,4,'',1),
(7,5,'',1),
(8,7,'',1),
(9,8,'0 : 0',1),
(10,9,'RC',1),
(11,10,'RM',1),
(12,84,'1-',2),
(13,27,'',2),
(14,28,'',2),
(15,29,'',2),
(16,30,'',2),
(17,31,'',2),
(18,35,'',2),
(19,37,'',2),
(20,38,'',2),
(21,32,'',2),
(22,33,'',2),
(23,34,'',2),
(24,36,'',2),
(25,39,'',2),
(26,40,'0 : 0',2),
(27,41,'RM',2),
(28,88,'',2),
(29,86,'3-',3),
(30,54,'',3),
(31,55,'',3),
(32,59,'',3),
(33,60,'',3),
(34,56,'',3),
(35,57,'',3),
(36,58,'U',3),
(37,63,'0 : 1',3),
(38,64,'RR',3),
(39,85,'7-',4),
(40,42,'',4),
(41,43,'',4),
(42,44,'',4),
(43,45,'',4),
(44,46,'',4),
(45,49,'',4),
(46,50,'',4),
(47,51,'',4),
(48,89,'',4),
(49,47,'',4),
(50,48,'',4),
(51,52,'',4),
(52,53,'0 : 1',4),
(53,83,'12-',5),
(54,11,'',5),
(55,12,'',5),
(56,14,'',5),
(57,17,'',5),
(58,19,'',5),
(59,20,'',5),
(60,26,'',5),
(61,94,'',5),
(62,13,'',5),
(63,15,'',5),
(64,16,'',5),
(65,18,'OH',5),
(66,21,'',5),
(67,22,'',5),
(68,23,'0 : 1',5),
(69,24,'RR',5),
(70,25,'',5),
(71,91,'',5),
(72,92,'',5),
(73,93,'',5),
(74,82,'12-',6),
(75,1,'',6),
(76,6,'',6),
(77,2,'',6),
(78,3,'',6),
(79,4,'',6),
(80,5,'',6),
(81,7,'',6),
(82,8,'1 : 1',6),
(83,9,'SC',6),
(84,10,'RL',6),
(85,84,'11-',7),
(86,27,'',7),
(87,28,'',7),
(88,29,'',7),
(89,30,'',7),
(90,31,'',7),
(91,35,'',7),
(92,37,'',7),
(93,38,'',7),
(94,32,'',7),
(95,33,'',7),
(96,34,'',7),
(97,36,'',7),
(98,39,'',7),
(99,40,'1 : 1',7),
(100,41,'RL',7),
(101,88,'',7),
(102,87,'12-',8),
(103,65,'',8),
(104,66,'',8),
(105,67,'',8),
(106,69,'',8),
(107,70,'',8),
(108,71,'',8),
(109,75,'',8),
(110,76,'',8),
(111,77,'',8),
(112,90,'',8),
(113,95,'',8),
(114,68,'',8),
(115,72,'',8),
(116,73,'',8),
(117,74,'',8),
(118,78,'RM',8),
(119,80,'1 : 1',8),
(120,81,'',8),
(121,86,'4-',9),
(122,54,'',9),
(123,55,'',9),
(124,59,'',9),
(125,60,'',9),
(126,56,'',9),
(127,57,'',9),
(128,58,'OH',9),
(129,63,'1 : 2',9),
(130,64,'RM',9),
(131,85,'7-',10),
(132,42,'',10),
(133,43,'',10),
(134,44,'',10),
(135,45,'',10),
(136,46,'',10),
(137,49,'',10),
(138,50,'',10),
(139,51,'',10),
(140,89,'',10),
(141,47,'',10),
(142,48,'',10),
(143,52,'',10),
(144,53,'1 : 2',10),
(145,83,'11-',11),
(146,11,'',11),
(147,12,'',11),
(148,14,'',11),
(149,17,'',11),
(150,19,'',11),
(151,20,'',11),
(152,26,'',11),
(153,94,'',11),
(154,13,'',11),
(155,15,'',11),
(156,16,'',11),
(157,18,'MB',11),
(158,21,'',11),
(159,22,'',11),
(160,23,'1 : 2',11),
(161,24,'RM',11),
(162,25,'',11),
(163,91,'',11),
(164,92,'',11),
(165,93,'',11),
(166,82,'11-',12),
(167,1,'',12),
(168,6,'',12),
(169,2,'',12),
(170,3,'',12),
(171,4,'',12),
(172,5,'',12),
(173,7,'',12),
(174,8,'2 : 2',12),
(175,9,'SC',12),
(176,10,'FL',12),
(177,84,'8-',13),
(178,27,'',13),
(179,28,'',13),
(180,29,'',13),
(181,30,'',13),
(182,31,'',13),
(183,35,'',13),
(184,37,'',13),
(185,38,'',13),
(186,32,'',13),
(187,33,'',13),
(188,34,'',13),
(189,36,'',13),
(190,39,'',13),
(191,40,'2 : 2',13),
(192,41,'FL',13),
(193,88,'',13),
(194,86,'4-',14),
(195,54,'',14),
(196,55,'',14),
(197,59,'',14),
(198,60,'',14),
(199,56,'',14),
(200,57,'',14),
(201,58,'OH',14),
(202,63,'2 : 3',14),
(203,64,'RL',14),
(204,87,'7-',15),
(205,65,'',15),
(206,66,'',15),
(207,67,'',15),
(208,69,'',15),
(209,70,'',15),
(210,71,'',15),
(211,75,'',15),
(212,76,'',15),
(213,77,'',15),
(214,90,'',15),
(215,95,'',15),
(216,68,'',15),
(217,72,'',15),
(218,73,'',15),
(219,74,'',15),
(220,78,'RL',15),
(221,80,'2 : 3',15),
(222,81,'',15),
(223,83,'12-',16),
(224,11,'',16),
(225,12,'',16),
(226,14,'',16),
(227,17,'',16),
(228,19,'',16),
(229,20,'',16),
(230,26,'',16),
(231,94,'',16),
(232,13,'',16),
(233,15,'',16),
(234,16,'',16),
(235,18,'OH',16),
(236,21,'',16),
(237,22,'',16),
(238,23,'2 : 3',16),
(239,24,'RL',16),
(240,25,'',16),
(241,91,'',16),
(242,92,'',16),
(243,93,'',16),
(244,84,'3-',17),
(245,27,'',17),
(246,28,'',17),
(247,29,'',17),
(248,30,'',17),
(249,31,'',17),
(250,35,'',17),
(251,37,'',17),
(252,38,'',17),
(253,32,'',17),
(254,33,'',17),
(255,34,'',17),
(256,36,'',17),
(257,39,'',17),
(258,40,'2 : 3',17),
(259,41,'FM',17),
(260,88,'',17),
(261,87,'12-',18),
(262,65,'',18),
(263,66,'',18),
(264,67,'',18),
(265,69,'',18),
(266,70,'',18),
(267,71,'',18),
(268,75,'',18),
(269,76,'',18),
(270,77,'',18),
(271,90,'',18),
(272,95,'',18),
(273,68,'',18),
(274,72,'',18),
(275,73,'',18),
(276,74,'',18),
(277,78,'RL',18),
(278,80,'2 : 3',18),
(279,81,'',18),
(280,87,'7-',19),
(281,65,'',19),
(282,66,'',19),
(283,67,'',19),
(284,69,'',19),
(285,70,'',19),
(286,71,'',19),
(287,75,'',19),
(288,76,'',19),
(289,77,'',19),
(290,90,'',19),
(291,95,'',19),
(292,68,'',19),
(293,72,'',19),
(294,73,'',19),
(295,74,'',19),
(296,78,'RL',19),
(297,80,'2 : 3',19),
(298,81,'',19),
(299,87,'4-',20),
(300,65,'',20),
(301,66,'',20),
(302,67,'',20),
(303,69,'',20),
(304,70,'',20),
(305,71,'',20),
(306,75,'',20),
(307,76,'',20),
(308,77,'',20),
(309,90,'',20),
(310,95,'',20),
(311,68,'',20),
(312,72,'',20),
(313,73,'',20),
(314,74,'',20),
(315,78,'RL',20),
(316,80,'2 : 3',20),
(317,81,'',20),
(318,86,'4-',21),
(319,54,'',21),
(320,55,'',21),
(321,59,'',21),
(322,60,'',21),
(323,56,'',21),
(324,57,'',21),
(325,58,'OH',21),
(326,63,'2 : 4',21),
(327,64,'RL',21),
(328,85,'7-',22),
(329,42,'',22),
(330,43,'',22),
(331,44,'',22),
(332,45,'',22),
(333,46,'',22),
(334,49,'',22),
(335,50,'',22),
(336,51,'',22),
(337,89,'',22),
(338,47,'',22),
(339,48,'',22),
(340,52,'',22),
(341,53,'2 : 4',22),
(342,83,'8-',23),
(343,11,'',23),
(344,12,'',23),
(345,14,'',23),
(346,17,'',23),
(347,19,'',23),
(348,20,'',23),
(349,26,'',23),
(350,94,'',23),
(351,13,'',23),
(352,15,'',23),
(353,16,'',23),
(354,18,'MB',23),
(355,21,'',23),
(356,22,'',23),
(357,23,'2 : 4',23),
(358,24,'RL',23),
(359,25,'',23),
(360,91,'',23),
(361,92,'',23),
(362,93,'',23),
(363,87,'12-',24),
(364,65,'',24),
(365,66,'',24),
(366,67,'',24),
(367,69,'',24),
(368,70,'',24),
(369,71,'',24),
(370,75,'',24),
(371,76,'',24),
(372,77,'',24),
(373,90,'',24),
(374,95,'',24),
(375,68,'',24),
(376,72,'',24),
(377,73,'',24),
(378,74,'',24),
(379,78,'RL',24),
(380,80,'2 : 4',24),
(381,81,'',24),
(382,85,'7-',25),
(383,42,'',25),
(384,43,'',25),
(385,44,'',25),
(386,45,'',25),
(387,46,'',25),
(388,49,'',25),
(389,50,'',25),
(390,51,'',25),
(391,89,'',25),
(392,47,'',25),
(393,48,'',25),
(394,52,'',25),
(395,53,'2 : 4',25),
(396,83,'3-',26),
(397,11,'',26),
(398,12,'',26),
(399,14,'',26),
(400,17,'',26),
(401,19,'',26),
(402,20,'',26),
(403,26,'',26),
(404,94,'',26),
(405,13,'',26),
(406,15,'',26),
(407,16,'',26),
(408,18,'U',26),
(409,21,'',26),
(410,22,'',26),
(411,23,'2 : 4',26),
(412,24,'RL',26),
(413,25,'',26),
(414,91,'',26),
(415,92,'',26),
(416,93,'',26),
(417,82,'3-',27),
(418,1,'',27),
(419,6,'',27),
(420,2,'',27),
(421,3,'',27),
(422,4,'',27),
(423,5,'',27),
(424,7,'',27),
(425,8,'3 : 4',27),
(426,9,'SC',27),
(427,10,'FM',27),
(428,87,'12-',28),
(429,65,'',28),
(430,66,'',28),
(431,67,'',28),
(432,69,'',28),
(433,70,'',28),
(434,71,'',28),
(435,75,'',28),
(436,76,'',28),
(437,77,'',28),
(438,90,'',28),
(439,95,'',28),
(440,68,'',28),
(441,72,'',28),
(442,73,'',28),
(443,74,'',28),
(444,78,'FL',28),
(445,80,'3 : 4',28),
(446,81,'',28),
(447,87,'3-',29),
(448,65,'',29),
(449,66,'',29),
(450,67,'',29),
(451,69,'',29),
(452,70,'',29),
(453,71,'',29),
(454,75,'',29),
(455,76,'',29),
(456,77,'',29),
(457,90,'',29),
(458,95,'',29),
(459,68,'',29),
(460,72,'',29),
(461,73,'',29),
(462,74,'',29),
(463,78,'FL',29),
(464,80,'3 : 4',29),
(465,81,'',29),
(466,87,'4-',30),
(467,65,'',30),
(468,66,'',30),
(469,67,'',30),
(470,69,'',30),
(471,70,'',30),
(472,71,'',30),
(473,75,'',30),
(474,76,'',30),
(475,77,'',30),
(476,90,'',30),
(477,95,'',30),
(478,68,'',30),
(479,72,'',30),
(480,73,'',30),
(481,74,'',30),
(482,78,'FL',30),
(483,80,'3 : 4',30),
(484,81,'',30),
(485,86,'1-',31),
(486,54,'',31),
(487,55,'',31),
(488,59,'',31),
(489,60,'',31),
(490,56,'',31),
(491,57,'',31),
(492,58,'L',31),
(493,63,'3 : 5',31),
(494,64,'FL',31),
(495,85,'7-',32),
(496,42,'',32),
(497,43,'',32),
(498,44,'',32),
(499,45,'',32),
(500,46,'',32),
(501,49,'',32),
(502,50,'',32),
(503,51,'',32),
(504,89,'',32),
(505,47,'',32),
(506,48,'',32),
(507,52,'',32),
(508,53,'3 : 5',32),
(509,83,'4-',33),
(510,11,'',33),
(511,12,'',33),
(512,14,'',33),
(513,17,'',33),
(514,19,'',33),
(515,20,'',33),
(516,26,'',33),
(517,94,'',33),
(518,13,'',33),
(519,15,'',33),
(520,16,'',33),
(521,18,'OH',33),
(522,21,'',33),
(523,22,'',33),
(524,23,'3 : 5',33),
(525,24,'FL',33),
(526,25,'',33),
(527,91,'',33),
(528,92,'',33),
(529,93,'',33),
(530,87,'1-',34),
(531,65,'',34),
(532,66,'',34),
(533,67,'',34),
(534,69,'',34),
(535,70,'',34),
(536,71,'',34),
(537,75,'',34),
(538,76,'',34),
(539,77,'',34),
(540,90,'',34),
(541,95,'',34),
(542,68,'',34),
(543,72,'',34),
(544,73,'',34),
(545,74,'',34),
(546,78,'FL',34),
(547,80,'3 : 5',34),
(548,81,'',34),
(549,85,'7-',35),
(550,42,'',35),
(551,43,'',35),
(552,44,'',35),
(553,45,'',35),
(554,46,'',35),
(555,49,'',35),
(556,50,'',35),
(557,51,'',35),
(558,89,'',35),
(559,47,'',35),
(560,48,'',35),
(561,52,'',35),
(562,53,'3 : 5',35),
(563,83,'8-',36),
(564,11,'',36),
(565,12,'',36),
(566,14,'',36),
(567,17,'',36),
(568,19,'',36),
(569,20,'',36),
(570,26,'',36),
(571,94,'',36),
(572,13,'',36),
(573,15,'',36),
(574,16,'',36),
(575,18,'MB',36),
(576,21,'',36),
(577,22,'',36),
(578,23,'3 : 5',36),
(579,24,'FL',36),
(580,25,'',36),
(581,91,'',36),
(582,92,'',36),
(583,93,'',36),
(584,82,'4-',37),
(585,1,'',37),
(586,6,'',37),
(587,2,'',37),
(588,3,'',37),
(589,4,'',37),
(590,5,'',37),
(591,7,'',37),
(592,8,'4 : 5',37),
(593,9,'SC',37),
(594,10,'FR',37),
(595,87,'3-',38),
(596,65,'',38),
(597,66,'',38),
(598,67,'',38),
(599,69,'',38),
(600,70,'',38),
(601,71,'',38),
(602,75,'',38),
(603,76,'',38),
(604,77,'',38),
(605,90,'',38),
(606,95,'',38),
(607,68,'',38),
(608,72,'',38),
(609,73,'',38),
(610,74,'',38),
(611,78,'FM',38),
(612,80,'4 : 5',38),
(613,81,'',38),
(614,85,'7-',39),
(615,42,'',39),
(616,43,'',39),
(617,44,'',39),
(618,45,'',39),
(619,46,'',39),
(620,49,'',39),
(621,50,'',39),
(622,51,'',39),
(623,89,'',39),
(624,47,'',39),
(625,48,'',39),
(626,52,'',39),
(627,53,'4 : 5',39),
(628,83,'12-',40),
(629,11,'',40),
(630,12,'',40),
(631,14,'',40),
(632,17,'',40),
(633,19,'',40),
(634,20,'',40),
(635,26,'',40),
(636,94,'',40),
(637,13,'',40),
(638,15,'',40),
(639,16,'',40),
(640,18,'OH',40),
(641,21,'',40),
(642,22,'',40),
(643,23,'4 : 5',40),
(644,24,'FM',40),
(645,25,'',40),
(646,91,'',40),
(647,92,'',40),
(648,93,'',40),
(649,87,'1-',41),
(650,65,'',41),
(651,66,'',41),
(652,67,'',41),
(653,69,'',41),
(654,70,'',41),
(655,71,'',41),
(656,75,'',41),
(657,76,'',41),
(658,77,'',41),
(659,90,'',41),
(660,95,'',41),
(661,68,'',41),
(662,72,'',41),
(663,73,'',41),
(664,74,'',41),
(665,78,'FM',41),
(666,80,'4 : 5',41),
(667,81,'',41),
(668,83,'7-',42),
(669,11,'',42),
(670,12,'',42),
(671,14,'',42),
(672,17,'',42),
(673,19,'',42),
(674,20,'',42),
(675,26,'',42),
(676,94,'',42),
(677,13,'',42),
(678,15,'',42),
(679,16,'',42),
(680,18,'S',42),
(681,21,'',42),
(682,22,'',42),
(683,23,'4 : 5',42),
(684,24,'FM',42),
(685,25,'',42),
(686,91,'',42),
(687,92,'',42),
(688,93,'',42),
(689,82,'8-',44),
(690,1,'',44),
(691,6,'',44),
(692,2,'',44),
(693,3,'',44),
(694,4,'',44),
(695,5,'',44),
(696,7,'',44),
(697,8,'5 : 6',44),
(698,9,'SC',44),
(699,10,'RR',44),
(700,84,'',45),
(701,27,'',45),
(702,28,'',45),
(703,29,'',45),
(704,30,'',45),
(705,31,'',45),
(706,35,'',45),
(707,37,'',45),
(708,38,'',45),
(709,32,'',45),
(710,33,'',45),
(711,34,'',45),
(712,36,'',45),
(713,39,'',45),
(714,40,'',45),
(715,41,'RR',45),
(716,88,'',45),
(741,86,'4-',48),
(742,54,'',48),
(743,55,'',48),
(744,59,'',48),
(745,60,'',48),
(746,56,'',48),
(747,57,'',48),
(748,58,'OH',48),
(749,63,'5 : 7',48),
(750,64,'FR',48),
(751,85,'7-',49),
(752,42,'',49),
(753,43,'',49),
(754,44,'',49),
(755,45,'',49),
(756,46,'',49),
(757,49,'',49),
(758,50,'',49),
(759,51,'',49),
(760,89,'',49),
(761,47,'',49),
(762,48,'',49),
(763,52,'',49),
(764,53,'5 : 7',49),
(765,83,'12-',50),
(766,11,'',50),
(767,12,'',50),
(768,14,'',50),
(769,17,'',50),
(770,19,'',50),
(771,20,'',50),
(772,26,'',50),
(773,94,'',50),
(774,13,'',50),
(775,15,'',50),
(776,16,'',50),
(777,18,'OH',50),
(778,21,'',50),
(779,22,'',50),
(780,23,'5 : 7',50),
(781,24,'FR',50),
(782,25,'',50),
(783,91,'',50),
(784,92,'',50),
(785,93,'',50),
(786,82,'7-',51),
(787,1,'',51),
(788,6,'',51),
(789,2,'',51),
(790,3,'',51),
(791,4,'',51),
(792,5,'',51),
(793,7,'',51),
(794,8,'6 : 7',51),
(795,9,'SC',51),
(796,10,'RM',51),
(797,82,'7-',53),
(798,1,'',53),
(799,6,'',53),
(800,2,'',53),
(801,3,'',53),
(802,4,'',53),
(803,5,'',53),
(804,7,'',53),
(805,8,'7 : 7',53),
(806,9,'RC',53),
(807,10,'RM',53),
(808,84,'1-',54),
(809,27,'',54),
(810,28,'',54),
(811,29,'',54),
(812,30,'',54),
(813,31,'',54),
(814,35,'',54),
(815,37,'',54),
(816,38,'',54),
(817,32,'',54),
(818,33,'',54),
(819,34,'',54),
(820,36,'',54),
(821,39,'',54),
(822,40,'7 : 7',54),
(823,41,'RM',54),
(824,88,'',54),
(825,86,'4-',55),
(826,54,'',55),
(827,55,'',55),
(828,59,'',55),
(829,60,'',55),
(830,56,'',55),
(831,57,'',55),
(832,58,'OH',55),
(833,63,'7 : 8',55),
(834,64,'RR',55),
(835,85,'7-',56),
(836,42,'',56),
(837,43,'',56),
(838,44,'',56),
(839,45,'',56),
(840,46,'',56),
(841,49,'',56),
(842,50,'',56),
(843,51,'',56),
(844,89,'',56),
(845,47,'',56),
(846,48,'',56),
(847,52,'',56),
(848,53,'7 : 8',56),
(849,83,'11-',57),
(850,11,'',57),
(851,12,'',57),
(852,14,'',57),
(853,17,'',57),
(854,19,'',57),
(855,20,'',57),
(856,26,'',57),
(857,94,'',57),
(858,13,'',57),
(859,15,'',57),
(860,16,'',57),
(861,18,'MB',57),
(862,21,'',57),
(863,22,'',57),
(864,23,'7 : 8',57),
(865,24,'RR',57),
(866,25,'',57),
(867,91,'',57),
(868,92,'',57),
(869,93,'',57),
(870,87,'7-',58),
(871,65,'',58),
(872,66,'',58),
(873,67,'',58),
(874,69,'',58),
(875,70,'',58),
(876,71,'',58),
(877,75,'',58),
(878,76,'',58),
(879,77,'',58),
(880,90,'',58),
(881,95,'',58),
(882,68,'',58),
(883,72,'',58),
(884,73,'',58),
(885,74,'',58),
(886,78,'RR',58),
(887,80,'7 : 8',58),
(888,81,'',58),
(889,87,'1-',59),
(890,65,'',59),
(891,66,'',59),
(892,67,'',59),
(893,69,'',59),
(894,70,'',59),
(895,71,'',59),
(896,75,'',59),
(897,76,'',59),
(898,77,'',59),
(899,90,'',59),
(900,95,'',59),
(901,68,'',59),
(902,72,'',59),
(903,73,'',59),
(904,74,'',59),
(905,78,'RR',59),
(906,80,'7 : 8',59),
(907,81,'',59),
(908,83,'12-',60),
(909,11,'',60),
(910,12,'',60),
(911,14,'',60),
(912,17,'',60),
(913,19,'',60),
(914,20,'',60),
(915,26,'',60),
(916,94,'',60),
(917,13,'',60),
(918,15,'',60),
(919,16,'',60),
(920,18,'OH',60),
(921,21,'',60),
(922,22,'',60),
(923,23,'7 : 8',60),
(924,24,'RR',60),
(925,25,'',60),
(926,91,'',60),
(927,92,'',60),
(928,93,'',60),
(929,82,'12-',62),
(930,1,'',62),
(931,6,'',62),
(932,2,'',62),
(933,3,'',62),
(934,4,'',62),
(935,5,'',62),
(936,7,'',62),
(937,8,'8 : 8',62),
(938,9,'SC',62),
(939,10,'RL',62),
(940,86,'1-',63),
(941,54,'',63),
(942,55,'',63),
(943,59,'',63),
(944,60,'',63),
(945,56,'',63),
(946,57,'',63),
(947,58,'L',63),
(948,63,'8 : 9',63),
(949,64,'RM',63),
(950,85,'7-',64),
(951,42,'',64),
(952,43,'',64),
(953,44,'',64),
(954,45,'',64),
(955,46,'',64),
(956,49,'',64),
(957,50,'',64),
(958,51,'',64),
(959,89,'',64),
(960,47,'',64),
(961,48,'',64),
(962,52,'',64),
(963,53,'8 : 9',64),
(964,83,'3-',65),
(965,11,'',65),
(966,12,'',65),
(967,14,'',65),
(968,17,'',65),
(969,19,'',65),
(970,20,'',65),
(971,26,'',65),
(972,94,'',65),
(973,13,'',65),
(974,15,'',65),
(975,16,'',65),
(976,18,'U',65),
(977,21,'',65),
(978,22,'',65),
(979,23,'8 : 9',65),
(980,24,'RM',65),
(981,25,'',65),
(982,91,'',65),
(983,92,'',65),
(984,93,'',65),
(985,82,'11-',67),
(986,1,'',67),
(987,6,'',67),
(988,2,'',67),
(989,3,'',67),
(990,4,'',67),
(991,5,'',67),
(992,7,'',67),
(993,8,'9 : 9',67),
(994,9,'SC',67),
(995,10,'FL',67),
(996,84,'8-',68),
(997,27,'',68),
(998,28,'',68),
(999,29,'',68),
(1000,30,'',68),
(1001,31,'',68),
(1002,35,'',68),
(1003,37,'',68),
(1004,38,'',68),
(1005,32,'',68),
(1006,33,'',68),
(1007,34,'',68),
(1008,36,'',68),
(1009,39,'',68),
(1010,40,'9 : 9',68),
(1011,41,'FL',68),
(1012,88,'',68),
(1013,84,'8-',69),
(1014,27,'',69),
(1015,28,'',69),
(1016,29,'',69),
(1017,30,'',69),
(1018,31,'',69),
(1019,35,'',69),
(1020,37,'',69),
(1021,38,'',69),
(1022,32,'',69),
(1023,33,'',69),
(1024,34,'',69),
(1025,36,'',69),
(1026,39,'',69),
(1027,40,'9 : 9',69),
(1028,41,'FL',69),
(1029,88,'',69),
(1030,87,'4-',70),
(1031,65,'',70),
(1032,66,'',70),
(1033,67,'',70),
(1034,69,'',70),
(1035,70,'',70),
(1036,71,'',70),
(1037,75,'',70),
(1038,76,'',70),
(1039,77,'',70),
(1040,90,'',70),
(1041,95,'',70),
(1042,68,'',70),
(1043,72,'',70),
(1044,73,'',70),
(1045,74,'',70),
(1046,78,'RL',70),
(1047,80,'9 : 9',70),
(1048,81,'',70),
(1049,85,'7-',71),
(1050,42,'',71),
(1051,43,'',71),
(1052,44,'',71),
(1053,45,'',71),
(1054,46,'',71),
(1055,49,'',71),
(1056,50,'',71),
(1057,51,'',71),
(1058,89,'',71),
(1059,47,'',71),
(1060,48,'',71),
(1061,52,'',71),
(1062,53,'9 : 9',71),
(1063,83,'12-',72),
(1064,11,'',72),
(1065,12,'',72),
(1066,14,'',72),
(1067,17,'',72),
(1068,19,'',72),
(1069,20,'',72),
(1070,26,'',72),
(1071,94,'',72),
(1072,13,'',72),
(1073,15,'',72),
(1074,16,'',72),
(1075,18,'OH',72),
(1076,21,'',72),
(1077,22,'',72),
(1078,23,'9 : 9',72),
(1079,24,'RL',72),
(1080,25,'',72),
(1081,91,'',72),
(1082,92,'',72),
(1083,93,'',72),
(1084,84,'8-',73),
(1085,27,'',73),
(1086,28,'',73),
(1087,29,'',73),
(1088,30,'',73),
(1089,31,'',73),
(1090,35,'',73),
(1091,37,'',73),
(1092,38,'',73),
(1093,32,'',73),
(1094,33,'',73),
(1095,34,'',73),
(1096,36,'',73),
(1097,39,'',73),
(1098,40,'9 : 9',73),
(1099,41,'FL',73),
(1100,88,'',73),
(1101,82,'11-',74),
(1102,1,'',74),
(1103,6,'',74),
(1104,2,'',74),
(1105,3,'',74),
(1106,4,'',74),
(1107,5,'',74),
(1108,7,'',74),
(1109,8,'10 : 9',74),
(1110,9,'RC',74),
(1111,10,'FL',74),
(1112,87,'4-',75),
(1113,65,'',75),
(1114,66,'',75),
(1115,67,'',75),
(1116,69,'',75),
(1117,70,'',75),
(1118,71,'',75),
(1119,75,'',75),
(1120,76,'',75),
(1121,77,'',75),
(1122,90,'',75),
(1123,95,'',75),
(1124,68,'',75),
(1125,72,'',75),
(1126,73,'',75),
(1127,74,'',75),
(1128,78,'RL',75),
(1129,80,'10 : 9',75),
(1130,81,'',75),
(1131,85,'7-',76),
(1132,42,'',76),
(1133,43,'',76),
(1134,44,'',76),
(1135,45,'',76),
(1136,46,'',76),
(1137,49,'',76),
(1138,50,'',76),
(1139,51,'',76),
(1140,89,'',76),
(1141,47,'',76),
(1142,48,'',76),
(1143,52,'',76),
(1144,53,'10 : 9',76),
(1145,83,'3-',77),
(1146,11,'',77),
(1147,12,'',77),
(1148,14,'',77),
(1149,17,'',77),
(1150,19,'',77),
(1151,20,'',77),
(1152,26,'',77),
(1153,94,'',77),
(1154,13,'',77),
(1155,15,'',77),
(1156,16,'',77),
(1157,18,'U',77),
(1158,21,'',77),
(1159,22,'',77),
(1160,23,'10 : 9',77),
(1161,24,'RL',77),
(1162,25,'',77),
(1163,91,'',77),
(1164,92,'',77),
(1165,93,'',77),
(1166,87,'8-',78),
(1167,65,'',78),
(1168,66,'',78),
(1169,67,'',78),
(1170,69,'',78),
(1171,70,'',78),
(1172,71,'',78),
(1173,75,'',78),
(1174,76,'',78),
(1175,77,'',78),
(1176,90,'',78),
(1177,95,'',78),
(1178,68,'',78),
(1179,72,'',78),
(1180,73,'',78),
(1181,74,'',78),
(1182,78,'RL',78),
(1183,80,'10 : 9',78),
(1184,81,'',78),
(1185,85,'7-',79),
(1186,42,'',79),
(1187,43,'',79),
(1188,44,'',79),
(1189,45,'',79),
(1190,46,'',79),
(1191,49,'',79),
(1192,50,'',79),
(1193,51,'',79),
(1194,89,'',79),
(1195,47,'',79),
(1196,48,'',79),
(1197,52,'',79),
(1198,53,'10 : 9',79),
(1199,83,'4-',80),
(1200,11,'',80),
(1201,12,'',80),
(1202,14,'',80),
(1203,17,'',80),
(1204,19,'',80),
(1205,20,'',80),
(1206,26,'',80),
(1207,94,'',80),
(1208,13,'',80),
(1209,15,'',80),
(1210,16,'',80),
(1211,18,'OH',80),
(1212,21,'',80),
(1213,22,'',80),
(1214,23,'10 : 9',80),
(1215,24,'RL',80),
(1216,25,'',80),
(1217,91,'',80),
(1218,92,'',80),
(1219,93,'',80),
(1220,82,'11-',81),
(1221,1,'',81),
(1222,6,'',81),
(1223,2,'',81),
(1224,3,'',81),
(1225,4,'',81),
(1226,5,'',81),
(1227,7,'',81),
(1228,8,'11 : 9',81),
(1229,9,'RC',81),
(1230,10,'FL',81),
(1231,84,'8-',82),
(1232,27,'',82),
(1233,28,'',82),
(1234,29,'',82),
(1235,30,'',82),
(1236,31,'',82),
(1237,35,'',82),
(1238,37,'',82),
(1239,38,'',82),
(1240,32,'',82),
(1241,33,'',82),
(1242,34,'',82),
(1243,36,'',82),
(1244,39,'',82),
(1245,40,'11 : 9',82),
(1246,41,'FL',82),
(1247,88,'',82),
(1248,87,'12-',83),
(1249,65,'',83),
(1250,66,'',83),
(1251,67,'',83),
(1252,69,'',83),
(1253,70,'',83),
(1254,71,'',83),
(1255,75,'',83),
(1256,76,'',83),
(1257,77,'',83),
(1258,90,'',83),
(1259,95,'',83),
(1260,68,'',83),
(1261,72,'',83),
(1262,73,'',83),
(1263,74,'',83),
(1264,78,'RL',83),
(1265,80,'11 : 9',83),
(1266,81,'',83),
(1267,87,'7-',84),
(1268,65,'',84),
(1269,66,'',84),
(1270,67,'',84),
(1271,69,'',84),
(1272,70,'',84),
(1273,71,'',84),
(1274,75,'',84),
(1275,76,'',84),
(1276,77,'',84),
(1277,90,'',84),
(1278,95,'',84),
(1279,68,'',84),
(1280,72,'',84),
(1281,73,'',84),
(1282,74,'',84),
(1283,78,'RL',84),
(1284,80,'11 : 9',84),
(1285,81,'',84),
(1286,84,'4-',85),
(1287,27,'',85),
(1288,28,'',85),
(1289,29,'',85),
(1290,30,'',85),
(1291,31,'',85),
(1292,35,'',85),
(1293,37,'',85),
(1294,38,'',85),
(1295,32,'',85),
(1296,33,'',85),
(1297,34,'',85),
(1298,36,'',85),
(1299,39,'',85),
(1300,40,'11 : 9',85),
(1301,41,'FL',85),
(1302,88,'',85),
(1303,82,'11-',86),
(1304,1,'',86),
(1305,6,'',86),
(1306,2,'',86),
(1307,3,'',86),
(1308,4,'',86),
(1309,5,'',86),
(1310,7,'',86),
(1311,8,'',86),
(1312,9,'RC',86),
(1313,10,'FL',86),
(1314,82,'11-',88),
(1315,1,'',88),
(1316,6,'',88),
(1317,2,'',88),
(1318,3,'',88),
(1319,4,'',88),
(1320,5,'',88),
(1321,7,'',88),
(1322,8,'13 : 9',88),
(1323,9,'RC',88),
(1324,10,'FL',88),
(1325,87,'12-',89),
(1326,65,'',89),
(1327,66,'',89),
(1328,67,'',89),
(1329,69,'',89),
(1330,70,'',89),
(1331,71,'',89),
(1332,75,'',89),
(1333,76,'',89),
(1334,77,'',89),
(1335,90,'',89),
(1336,95,'',89),
(1337,68,'',89),
(1338,72,'',89),
(1339,73,'',89),
(1340,74,'',89),
(1341,78,'RL',89),
(1342,80,'13 : 9',89),
(1343,81,'',89),
(1344,85,'7-',90),
(1345,42,'',90),
(1346,43,'',90),
(1347,44,'',90),
(1348,45,'',90),
(1349,46,'',90),
(1350,49,'',90),
(1351,50,'',90),
(1352,51,'',90),
(1353,89,'',90),
(1354,47,'',90),
(1355,48,'',90),
(1356,52,'',90),
(1357,53,'13 : 9',90),
(1358,83,'8-',91),
(1359,11,'',91),
(1360,12,'',91),
(1361,14,'',91),
(1362,17,'',91),
(1363,19,'',91),
(1364,20,'',91),
(1365,26,'',91),
(1366,94,'',91),
(1367,13,'',91),
(1368,15,'',91),
(1369,16,'',91),
(1370,18,'MB',91),
(1371,21,'',91),
(1372,22,'',91),
(1373,23,'13 : 9',91),
(1374,24,'RL',91),
(1375,25,'',91),
(1376,91,'',91),
(1377,92,'',91),
(1378,93,'',91),
(1379,82,'11-',92),
(1380,1,'',92),
(1381,6,'',92),
(1382,2,'',92),
(1383,3,'',92),
(1384,4,'',92),
(1385,5,'',92),
(1386,7,'',92),
(1387,8,'14 : 9',92),
(1388,9,'RC',92),
(1389,10,'FL',92),
(1390,82,'11-',94),
(1391,1,'',94),
(1392,6,'',94),
(1393,2,'',94),
(1394,3,'',94),
(1395,4,'',94),
(1396,5,'',94),
(1397,7,'',94),
(1398,8,'15 : 9',94),
(1399,9,'RC',94),
(1400,10,'FL',94),
(1401,84,'4-',95),
(1402,27,'',95),
(1403,28,'',95),
(1404,29,'',95),
(1405,30,'',95),
(1406,31,'',95),
(1407,35,'',95),
(1408,37,'',95),
(1409,38,'',95),
(1410,32,'',95),
(1411,33,'',95),
(1412,34,'',95),
(1413,36,'',95),
(1414,39,'',95),
(1415,40,'15 : 9',95),
(1416,41,'FL',95),
(1417,88,'',95),
(1418,86,'4-',96),
(1419,54,'',96),
(1420,55,'',96),
(1421,59,'',96),
(1422,60,'',96),
(1423,56,'',96),
(1424,57,'',96),
(1425,58,'OH',96),
(1426,63,'15 : 10',96),
(1427,64,'RL',96),
(1428,85,'7-',97),
(1429,42,'',97),
(1430,43,'',97),
(1431,44,'',97),
(1432,45,'',97),
(1433,46,'',97),
(1434,49,'',97),
(1435,50,'',97),
(1436,51,'',97),
(1437,89,'',97),
(1438,47,'',97),
(1439,48,'',97),
(1440,52,'',97),
(1441,53,'15 : 10',97),
(1442,83,'8-',98),
(1443,11,'',98),
(1444,12,'',98),
(1445,14,'',98),
(1446,17,'',98),
(1447,19,'',98),
(1448,20,'',98),
(1449,26,'',98),
(1450,94,'',98),
(1451,13,'',98),
(1452,15,'',98),
(1453,16,'',98),
(1454,18,'MB',98),
(1455,21,'',98),
(1456,22,'',98),
(1457,23,'15 : 10',98),
(1458,24,'RL',98),
(1459,25,'',98),
(1460,91,'',98),
(1461,92,'',98),
(1462,93,'',98),
(1463,82,'3-',99),
(1464,1,'',99),
(1465,6,'',99),
(1466,2,'',99),
(1467,3,'',99),
(1468,4,'',99),
(1469,5,'',99),
(1470,7,'',99),
(1471,8,'16 : 10',99),
(1472,9,'SC',99),
(1473,10,'FM',99),
(1474,82,'3-',100),
(1475,1,'',100),
(1476,6,'',100),
(1477,2,'',100),
(1478,3,'',100),
(1479,4,'',100),
(1480,5,'',100),
(1481,7,'',100),
(1482,8,'17 : 10',100),
(1483,9,'RC',100),
(1484,10,'FM',100),
(1485,84,'4-',101),
(1486,27,'',101),
(1487,28,'',101),
(1488,29,'',101),
(1489,30,'',101),
(1490,31,'',101),
(1491,35,'',101),
(1492,37,'',101),
(1493,38,'',101),
(1494,32,'',101),
(1495,33,'',101),
(1496,34,'',101),
(1497,36,'',101),
(1498,39,'',101),
(1499,40,'17 : 10',101),
(1500,41,'FM',101),
(1501,88,'',101),
(1502,87,'1-',102),
(1503,65,'',102),
(1504,66,'',102),
(1505,67,'',102),
(1506,69,'',102),
(1507,70,'',102),
(1508,71,'',102),
(1509,75,'',102),
(1510,76,'',102),
(1511,77,'',102),
(1512,90,'',102),
(1513,95,'',102),
(1514,68,'',102),
(1515,72,'',102),
(1516,73,'',102),
(1517,74,'',102),
(1518,78,'FL',102),
(1519,80,'17 : 10',102),
(1520,81,'',102),
(1521,85,'7-',103),
(1522,42,'',103),
(1523,43,'',103),
(1524,44,'',103),
(1525,45,'',103),
(1526,46,'',103),
(1527,49,'',103),
(1528,50,'',103),
(1529,51,'',103),
(1530,89,'',103),
(1531,47,'',103),
(1532,48,'',103),
(1533,52,'',103),
(1534,53,'17 : 10',103),
(1535,83,'4-',104),
(1536,11,'',104),
(1537,12,'',104),
(1538,14,'',104),
(1539,17,'',104),
(1540,19,'',104),
(1541,20,'',104),
(1542,26,'',104),
(1543,94,'',104),
(1544,13,'',104),
(1545,15,'',104),
(1546,16,'',104),
(1547,18,'OH',104),
(1548,21,'',104),
(1549,22,'',104),
(1550,23,'17 : 10',104),
(1551,24,'FL',104),
(1552,25,'',104),
(1553,91,'',104),
(1554,92,'',104),
(1555,93,'',104),
(1556,82,'3-',106),
(1557,1,'',106),
(1558,6,'',106),
(1559,2,'',106),
(1560,3,'',106),
(1561,4,'',106),
(1562,5,'',106),
(1563,7,'',106),
(1564,8,'18 : 10',106),
(1565,9,'RC',106),
(1566,10,'FM',106),
(1567,82,'3-',107),
(1568,1,'',107),
(1569,6,'',107),
(1570,2,'',107),
(1571,3,'',107),
(1572,4,'',107),
(1573,5,'',107),
(1574,7,'',107),
(1575,8,'19 : 10',107),
(1576,9,'RC',107),
(1577,10,'FM',107),
(1578,87,'12-',108),
(1579,65,'',108),
(1580,66,'',108),
(1581,67,'',108),
(1582,69,'',108),
(1583,70,'',108),
(1584,71,'',108),
(1585,75,'',108),
(1586,76,'',108),
(1587,77,'',108),
(1588,90,'',108),
(1589,95,'',108),
(1590,68,'',108),
(1591,72,'',108),
(1592,73,'',108),
(1593,74,'',108),
(1594,78,'FL',108),
(1595,80,'19 : 10',108),
(1596,81,'',108),
(1597,87,'4-',109),
(1598,65,'',109),
(1599,66,'',109),
(1600,67,'',109),
(1601,69,'',109),
(1602,70,'',109),
(1603,71,'',109),
(1604,75,'',109),
(1605,76,'',109),
(1606,77,'',109),
(1607,90,'',109),
(1608,95,'',109),
(1609,68,'',109),
(1610,72,'',109),
(1611,73,'',109),
(1612,74,'',109),
(1613,78,'FL',109),
(1614,80,'19 : 10',109),
(1615,81,'',109),
(1616,83,'12-',110),
(1617,11,'',110),
(1618,12,'',110),
(1619,14,'',110),
(1620,17,'',110),
(1621,19,'',110),
(1622,20,'',110),
(1623,26,'',110),
(1624,94,'',110),
(1625,13,'',110),
(1626,15,'',110),
(1627,16,'',110),
(1628,18,'OH',110),
(1629,21,'',110),
(1630,22,'',110),
(1631,23,'19 : 10',110),
(1632,24,'FL',110),
(1633,25,'',110),
(1634,91,'',110),
(1635,92,'',110),
(1636,93,'',110),
(1637,84,'8-',111),
(1638,27,'',111),
(1639,28,'',111),
(1640,29,'',111),
(1641,30,'',111),
(1642,31,'',111),
(1643,35,'',111),
(1644,37,'',111),
(1645,38,'',111),
(1646,32,'',111),
(1647,33,'',111),
(1648,34,'',111),
(1649,36,'',111),
(1650,39,'',111),
(1651,40,'19 : 10',111),
(1652,41,'FM',111),
(1653,88,'',111),
(1654,86,'4-',113),
(1655,54,'',113),
(1656,55,'',113),
(1657,59,'',113),
(1658,60,'',113),
(1659,56,'',113),
(1660,57,'',113),
(1661,58,'OH',113),
(1662,63,'19 : 11',113),
(1663,64,'FL',113),
(1664,85,'7-',114),
(1665,42,'',114),
(1666,43,'',114),
(1667,44,'',114),
(1668,45,'',114),
(1669,46,'',114),
(1670,49,'',114),
(1671,50,'',114),
(1672,51,'',114),
(1673,89,'',114),
(1674,47,'',114),
(1675,48,'',114),
(1676,52,'',114),
(1677,53,'19 : 11',114),
(1678,83,'8-',115),
(1679,11,'',115),
(1680,12,'',115),
(1681,14,'',115),
(1682,17,'',115),
(1683,19,'',115),
(1684,20,'',115),
(1685,26,'',115),
(1686,94,'',115),
(1687,13,'',115),
(1688,15,'',115),
(1689,16,'',115),
(1690,18,'MB',115),
(1691,21,'',115),
(1692,22,'',115),
(1693,23,'19 : 11',115),
(1694,24,'FL',115),
(1695,25,'',115),
(1696,91,'',115),
(1697,92,'',115),
(1698,93,'',115),
(1699,87,'1-',116),
(1700,65,'',116),
(1701,66,'',116),
(1702,67,'',116),
(1703,69,'',116),
(1704,70,'',116),
(1705,71,'',116),
(1706,75,'',116),
(1707,76,'',116),
(1708,77,'',116),
(1709,90,'',116),
(1710,95,'',116),
(1711,68,'',116),
(1712,72,'',116),
(1713,73,'',116),
(1714,74,'',116),
(1715,78,'FL',116),
(1716,80,'19 : 11',116),
(1717,81,'',116),
(1718,85,'7-',117),
(1719,42,'',117),
(1720,43,'',117),
(1721,44,'',117),
(1722,45,'',117),
(1723,46,'',117),
(1724,49,'',117),
(1725,50,'',117),
(1726,51,'',117),
(1727,89,'',117),
(1728,47,'',117),
(1729,48,'',117),
(1730,52,'',117),
(1731,53,'19 : 11',117),
(1732,83,'8-',118),
(1733,11,'',118),
(1734,12,'',118),
(1735,14,'',118),
(1736,17,'',118),
(1737,19,'',118),
(1738,20,'',118),
(1739,26,'',118),
(1740,94,'',118),
(1741,13,'',118),
(1742,15,'',118),
(1743,16,'',118),
(1744,18,'MB',118),
(1745,21,'',118),
(1746,22,'',118),
(1747,23,'19 : 11',118),
(1748,24,'FL',118),
(1749,25,'',118),
(1750,91,'',118),
(1751,92,'',118),
(1752,93,'',118),
(1753,87,'12-',119),
(1754,65,'',119),
(1755,66,'',119),
(1756,67,'',119),
(1757,69,'',119),
(1758,70,'',119),
(1759,71,'',119),
(1760,75,'',119),
(1761,76,'',119),
(1762,77,'',119),
(1763,90,'',119),
(1764,95,'',119),
(1765,68,'',119),
(1766,72,'',119),
(1767,73,'',119),
(1768,74,'',119),
(1769,78,'FL',119),
(1770,80,'19 : 11',119),
(1771,81,'',119),
(1772,85,'7-',120),
(1773,42,'',120),
(1774,43,'',120),
(1775,44,'',120),
(1776,45,'',120),
(1777,46,'',120),
(1778,49,'',120),
(1779,50,'',120),
(1780,51,'',120),
(1781,89,'',120),
(1782,47,'',120),
(1783,48,'',120),
(1784,52,'',120),
(1785,53,'19 : 11',120),
(1786,83,'4-',121),
(1787,11,'',121),
(1788,12,'',121),
(1789,14,'',121),
(1790,17,'',121),
(1791,19,'',121),
(1792,20,'',121),
(1793,26,'',121),
(1794,94,'',121),
(1795,13,'',121),
(1796,15,'',121),
(1797,16,'',121),
(1798,18,'OH',121),
(1799,21,'',121),
(1800,22,'',121),
(1801,23,'19 : 11',121),
(1802,24,'FL',121),
(1803,25,'',121),
(1804,91,'',121),
(1805,92,'',121),
(1806,93,'',121),
(1807,82,'4-',122),
(1808,1,'',122),
(1809,6,'',122),
(1810,2,'',122),
(1811,3,'',122),
(1812,4,'',122),
(1813,5,'',122),
(1814,7,'',122),
(1815,8,'20 : 11',122),
(1816,9,'SC',122),
(1817,10,'FR',122),
(1818,83,'7-',123),
(1819,11,'',123),
(1820,12,'',123),
(1821,14,'',123),
(1822,17,'',123),
(1823,19,'',123),
(1824,20,'',123),
(1825,26,'',123),
(1826,94,'',123),
(1827,13,'',123),
(1828,15,'',123),
(1829,16,'',123),
(1830,18,'S',123),
(1831,21,'',123),
(1832,22,'',123),
(1833,23,'20 : 11',123),
(1834,24,'FM',123),
(1835,25,'',123),
(1836,91,'',123),
(1837,92,'',123),
(1838,93,'',123),
(1839,87,'8-',124),
(1840,65,'',124),
(1841,66,'',124),
(1842,67,'',124),
(1843,69,'',124),
(1844,70,'',124),
(1845,71,'',124),
(1846,75,'',124),
(1847,76,'',124),
(1848,77,'',124),
(1849,90,'',124),
(1850,95,'',124),
(1851,68,'',124),
(1852,72,'',124),
(1853,73,'',124),
(1854,74,'',124),
(1855,78,'FM',124),
(1856,80,'20 : 11',124),
(1857,81,'',124),
(1858,86,'12-',125),
(1859,54,'',125),
(1860,55,'',125),
(1861,59,'',125),
(1862,60,'',125),
(1863,56,'',125),
(1864,57,'',125),
(1865,58,'OH',125),
(1866,63,'20 : 12',125),
(1867,64,'FM',125),
(1868,87,'7-',126),
(1869,65,'',126),
(1870,66,'',126),
(1871,67,'',126),
(1872,69,'',126),
(1873,70,'',126),
(1874,71,'',126),
(1875,75,'',126),
(1876,76,'',126),
(1877,77,'',126),
(1878,90,'',126),
(1879,95,'',126),
(1880,68,'',126),
(1881,72,'',126),
(1882,73,'',126),
(1883,74,'',126),
(1884,78,'FM',126),
(1885,80,'20 : 12',126),
(1886,81,'',126),
(1887,83,'12-',127),
(1888,11,'',127),
(1889,12,'',127),
(1890,14,'',127),
(1891,17,'',127),
(1892,19,'',127),
(1893,20,'',127),
(1894,26,'',127),
(1895,94,'',127),
(1896,13,'',127),
(1897,15,'',127),
(1898,16,'',127),
(1899,18,'OH',127),
(1900,21,'',127),
(1901,22,'',127),
(1902,23,'20 : 12',127),
(1903,24,'FM',127),
(1904,25,'',127),
(1905,91,'',127),
(1906,92,'',127),
(1907,93,'',127),
(1908,87,'12-',128),
(1909,65,'',128),
(1910,66,'',128),
(1911,67,'',128),
(1912,69,'',128),
(1913,70,'',128),
(1914,71,'',128),
(1915,75,'',128),
(1916,76,'',128),
(1917,77,'',128),
(1918,90,'',128),
(1919,95,'',128),
(1920,68,'',128),
(1921,72,'',128),
(1922,73,'',128),
(1923,74,'',128),
(1924,78,'FM',128),
(1925,80,'20 : 12',128),
(1926,81,'',128),
(1927,87,'3-',129),
(1928,65,'',129),
(1929,66,'',129),
(1930,67,'',129),
(1931,69,'',129),
(1932,70,'',129),
(1933,71,'',129),
(1934,75,'',129),
(1935,76,'',129),
(1936,77,'',129),
(1937,90,'',129),
(1938,95,'',129),
(1939,68,'',129),
(1940,72,'',129),
(1941,73,'',129),
(1942,74,'',129),
(1943,78,'FM',129),
(1944,80,'20 : 12',129),
(1945,81,'',129),
(1946,87,'7-',130),
(1947,65,'',130),
(1948,66,'',130),
(1949,67,'',130),
(1950,69,'',130),
(1951,70,'',130),
(1952,71,'',130),
(1953,75,'',130),
(1954,76,'',130),
(1955,77,'',130),
(1956,90,'',130),
(1957,95,'',130),
(1958,68,'',130),
(1959,72,'',130),
(1960,73,'',130),
(1961,74,'',130),
(1962,78,'FM',130),
(1963,80,'20 : 12',130),
(1964,81,'',130),
(1965,86,'1-',131),
(1966,54,'',131),
(1967,55,'',131),
(1968,59,'',131),
(1969,60,'',131),
(1970,56,'',131),
(1971,57,'',131),
(1972,58,'L',131),
(1973,63,'20 : 13',131),
(1974,64,'FM',131),
(1975,87,'7-',132),
(1976,65,'',132),
(1977,66,'',132),
(1978,67,'',132),
(1979,69,'',132),
(1980,70,'',132),
(1981,71,'',132),
(1982,75,'',132),
(1983,76,'',132),
(1984,77,'',132),
(1985,90,'',132),
(1986,95,'',132),
(1987,68,'',132),
(1988,72,'',132),
(1989,73,'',132),
(1990,74,'',132),
(1991,78,'FM',132),
(1992,80,'20 : 13',132),
(1993,81,'',132),
(1994,83,'12-',133),
(1995,11,'',133),
(1996,12,'',133),
(1997,14,'',133),
(1998,17,'',133),
(1999,19,'',133),
(2000,20,'',133),
(2001,26,'',133),
(2002,94,'',133),
(2003,13,'',133),
(2004,15,'',133),
(2005,16,'',133),
(2006,18,'OH',133),
(2007,21,'',133),
(2008,22,'',133),
(2009,23,'20 : 13',133),
(2010,24,'FM',133),
(2011,25,'',133),
(2012,91,'',133),
(2013,92,'',133),
(2014,93,'',133),
(2015,83,'12-',134),
(2016,11,'',134),
(2017,12,'',134),
(2018,14,'',134),
(2019,17,'',134),
(2020,19,'',134),
(2021,20,'',134),
(2022,26,'',134),
(2023,94,'',134),
(2024,13,'',134),
(2025,15,'',134),
(2026,16,'',134),
(2027,18,'OH',134),
(2028,21,'',134),
(2029,22,'',134),
(2030,23,'20 : 13',134),
(2031,24,'FM',134),
(2032,25,'',134),
(2033,91,'',134),
(2034,92,'',134),
(2035,93,'',134),
(2036,82,'8-',135),
(2037,1,'',135),
(2038,6,'',135),
(2039,2,'',135),
(2040,3,'',135),
(2041,4,'',135),
(2042,5,'',135),
(2043,7,'',135),
(2044,8,'21 : 13',135),
(2045,9,'SC',135),
(2046,10,'RR',135),
(2047,87,'4-',136),
(2048,65,'',136),
(2049,66,'',136),
(2050,67,'',136),
(2051,69,'',136),
(2052,70,'',136),
(2053,71,'',136),
(2054,75,'',136),
(2055,76,'',136),
(2056,77,'',136),
(2057,90,'',136),
(2058,95,'',136),
(2059,68,'',136),
(2060,72,'',136),
(2061,73,'',136),
(2062,74,'',136),
(2063,78,'FR',136),
(2064,80,'21 : 13',136),
(2065,81,'',136),
(2066,85,'7-',137),
(2067,42,'',137),
(2068,43,'',137),
(2069,44,'',137),
(2070,45,'',137),
(2071,46,'',137),
(2072,49,'',137),
(2073,50,'',137),
(2074,51,'',137),
(2075,89,'',137),
(2076,47,'',137),
(2077,48,'',137),
(2078,52,'',137),
(2079,53,'21 : 13',137),
(2080,83,'12-',138),
(2081,11,'',138),
(2082,12,'',138),
(2083,14,'',138),
(2084,17,'',138),
(2085,19,'',138),
(2086,20,'',138),
(2087,26,'',138),
(2088,94,'',138),
(2089,13,'',138),
(2090,15,'',138),
(2091,16,'',138),
(2092,18,'OH',138),
(2093,21,'',138),
(2094,22,'',138),
(2095,23,'21 : 13',138),
(2096,24,'FR',138),
(2097,25,'',138),
(2098,91,'',138),
(2099,92,'',138),
(2100,93,'',138),
(2101,82,'8-',140),
(2102,1,'',140),
(2103,6,'',140),
(2104,2,'',140),
(2105,3,'',140),
(2106,4,'',140),
(2107,5,'',140),
(2108,7,'',140),
(2109,8,'22 : 13',140),
(2110,9,'RC',140),
(2111,10,'RR',140),
(2112,87,'11-',141),
(2113,65,'',141),
(2114,66,'',141),
(2115,67,'',141),
(2116,69,'',141),
(2117,70,'',141),
(2118,71,'',141),
(2119,75,'',141),
(2120,76,'',141),
(2121,77,'',141),
(2122,90,'',141),
(2123,95,'',141),
(2124,68,'',141),
(2125,72,'',141),
(2126,73,'',141),
(2127,74,'',141),
(2128,78,'FR',141),
(2129,80,'22 : 13',141),
(2130,81,'',141),
(2131,83,'7-',142),
(2132,11,'',142),
(2133,12,'',142),
(2134,14,'',142),
(2135,17,'',142),
(2136,19,'',142),
(2137,20,'',142),
(2138,26,'',142),
(2139,94,'',142),
(2140,13,'',142),
(2141,15,'',142),
(2142,16,'',142),
(2143,18,'S',142),
(2144,21,'',142),
(2145,22,'',142),
(2146,23,'22 : 13',142),
(2147,24,'FR',142),
(2148,25,'',142),
(2149,91,'',142),
(2150,92,'',142),
(2151,93,'',142),
(2152,86,'4-',143),
(2153,54,'',143),
(2154,55,'',143),
(2155,59,'',143),
(2156,60,'',143),
(2157,56,'',143),
(2158,57,'',143),
(2159,58,'OH',143),
(2160,63,'22 : 14',143),
(2161,64,'FR',143),
(2162,85,'7-',144),
(2163,42,'',144),
(2164,43,'',144),
(2165,44,'',144),
(2166,45,'',144),
(2167,46,'',144),
(2168,49,'',144),
(2169,50,'',144),
(2170,51,'',144),
(2171,89,'',144),
(2172,47,'',144),
(2173,48,'',144),
(2174,52,'',144),
(2175,53,'22 : 14',144),
(2176,83,'11-',145),
(2177,11,'',145),
(2178,12,'',145),
(2179,14,'',145),
(2180,17,'',145),
(2181,19,'',145),
(2182,20,'',145),
(2183,26,'',145),
(2184,94,'',145),
(2185,13,'',145),
(2186,15,'',145),
(2187,16,'',145),
(2188,18,'MB',145),
(2189,21,'',145),
(2190,22,'',145),
(2191,23,'22 : 14',145),
(2192,24,'FR',145),
(2193,25,'',145),
(2194,91,'',145),
(2195,92,'',145),
(2196,93,'',145),
(2197,84,'11-',146),
(2198,27,'',146),
(2199,28,'',146),
(2200,29,'',146),
(2201,30,'',146),
(2202,31,'',146),
(2203,35,'',146),
(2204,37,'',146),
(2205,38,'',146),
(2206,32,'',146),
(2207,33,'',146),
(2208,34,'',146),
(2209,36,'',146),
(2210,39,'',146),
(2211,40,'22 : 14',146),
(2212,41,'RM',146),
(2213,88,'',146),
(2214,82,'7-',147),
(2215,1,'',147),
(2216,6,'',147),
(2217,2,'',147),
(2218,3,'',147),
(2219,4,'',147),
(2220,5,'',147),
(2221,7,'',147),
(2222,8,'23 : 14',147),
(2223,9,'SC',147),
(2224,10,'RM',147),
(2225,84,'3-',148),
(2226,27,'',148),
(2227,28,'',148),
(2228,29,'',148),
(2229,30,'',148),
(2230,31,'',148),
(2231,35,'',148),
(2232,37,'',148),
(2233,38,'',148),
(2234,32,'',148),
(2235,33,'',148),
(2236,34,'',148),
(2237,36,'',148),
(2238,39,'',148),
(2239,40,'23 : 14',148),
(2240,41,'RM',148),
(2241,88,'',148),
(2242,87,'4-',149),
(2243,65,'',149),
(2244,66,'',149),
(2245,67,'',149),
(2246,69,'',149),
(2247,70,'',149),
(2248,71,'',149),
(2249,75,'',149),
(2250,76,'',149),
(2251,77,'',149),
(2252,90,'',149),
(2253,95,'',149),
(2254,68,'',149),
(2255,72,'',149),
(2256,73,'',149),
(2257,74,'',149),
(2258,78,'RR',149),
(2259,80,'23 : 14',149),
(2260,81,'',149),
(2261,87,'12-',150),
(2262,65,'',150),
(2263,66,'',150),
(2264,67,'',150),
(2265,69,'',150),
(2266,70,'',150),
(2267,71,'',150),
(2268,75,'',150),
(2269,76,'',150),
(2270,77,'',150),
(2271,90,'',150),
(2272,95,'',150),
(2273,68,'',150),
(2274,72,'',150),
(2275,73,'',150),
(2276,74,'',150),
(2277,78,'RR',150),
(2278,80,'23 : 14',150),
(2279,81,'',150),
(2280,87,'4-',151),
(2281,65,'',151),
(2282,66,'',151),
(2283,67,'',151),
(2284,69,'',151),
(2285,70,'',151),
(2286,71,'',151),
(2287,75,'',151),
(2288,76,'',151),
(2289,77,'',151),
(2290,90,'',151),
(2291,95,'',151),
(2292,68,'',151),
(2293,72,'',151),
(2294,73,'',151),
(2295,74,'',151),
(2296,78,'RR',151),
(2297,80,'23 : 14',151),
(2298,81,'',151),
(2299,82,'7-',153),
(2300,1,'',153),
(2301,6,'',153),
(2302,2,'',153),
(2303,3,'',153),
(2304,4,'',153),
(2305,5,'',153),
(2306,7,'',153),
(2307,8,'24 : 14',153),
(2308,9,'RC',153),
(2309,10,'RM',153),
(2310,84,'3-',154),
(2311,27,'',154),
(2312,28,'',154),
(2313,29,'',154),
(2314,30,'',154),
(2315,31,'',154),
(2316,35,'',154),
(2317,37,'',154),
(2318,38,'',154),
(2319,32,'',154),
(2320,33,'',154),
(2321,34,'',154),
(2322,36,'',154),
(2323,39,'',154),
(2324,40,'24 : 14',154),
(2325,41,'RM',154),
(2326,88,'',154),
(2327,82,'8-',156),
(2328,1,'',156),
(2329,6,'',156),
(2330,2,'',156),
(2331,3,'',156),
(2332,4,'',156),
(2333,5,'',156),
(2334,7,'',156),
(2335,8,'0 : 0',156),
(2336,9,'RC',156),
(2337,10,'RR',156),
(2338,82,'8-',157),
(2339,1,'',157),
(2340,6,'',157),
(2341,2,'',157),
(2342,3,'',157),
(2343,4,'',157),
(2344,5,'',157),
(2345,7,'',157),
(2346,8,'0 : 1',157),
(2347,9,'RC',157),
(2348,10,'RM',157),
(2349,84,'12-',42),
(2350,27,'',42),
(2351,28,'',42),
(2352,29,'',42),
(2353,30,'',42),
(2354,31,'',42),
(2355,35,'',42),
(2356,37,'',42),
(2357,38,'',42),
(2358,32,'',42),
(2359,33,'',42),
(2360,34,'',42),
(2361,36,'',42),
(2362,39,'',42),
(2363,40,'0 : 2',42),
(2364,41,'RM',42),
(2365,88,'',42),
(2366,82,'7-H62D-O61B-',160),
(2367,1,'JF',160),
(2368,6,'',160),
(2369,2,'',160),
(2370,3,'C',160),
(2371,4,'6',160),
(2372,5,'6',160),
(2373,7,'',160),
(2374,8,'',160),
(2375,9,'RC',160),
(2376,10,'RM',160),
(2377,84,'4-O3B-H51B-H51B-H62D-',161),
(2378,27,'',161),
(2379,28,'',161),
(2380,29,'',161),
(2381,30,'',161),
(2382,31,'',161),
(2383,35,'',161),
(2384,37,'',161),
(2385,38,'',161),
(2386,32,'3',161),
(2387,33,'5',161),
(2388,34,'5',161),
(2389,36,'MB',161),
(2390,39,'H',161),
(2391,40,'0 : 0',161),
(2392,41,'RM',161),
(2393,88,'',161);

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `rally_rotation_order` */

insert  into `rally_rotation_order`(`id`,`pos1playerId`,`pos2playerId`,`pos3playerId`,`pos4playerId`,`pos5playerId`,`pos6playerId`,`pos1playerIdOpp`,`pos2playerIdOpp`,`pos3playerIdOpp`,`pos4playerIdOpp`,`pos5playerIdOpp`,`pos6playerIdOpp`,`rallyId`) values 
(1,1,2,3,4,5,6,18,13,14,15,16,17,1),
(2,1,2,3,4,5,6,13,14,15,16,17,18,2),
(3,2,3,4,5,6,1,13,14,15,16,17,18,3),
(4,2,3,4,5,6,1,14,15,16,17,18,13,4),
(5,3,4,5,6,1,2,14,15,16,17,18,13,5),
(6,3,4,5,6,1,2,15,16,17,18,13,14,6),
(7,3,4,5,6,1,2,15,16,17,18,13,14,7),
(8,4,5,6,1,2,3,15,16,17,18,13,14,8),
(9,4,5,6,1,2,3,16,17,18,13,14,15,9),
(10,5,6,1,2,3,4,16,17,18,13,14,15,10),
(11,5,6,1,2,3,4,17,18,13,14,15,16,11),
(12,6,1,2,3,4,5,17,18,13,14,15,16,12),
(13,6,1,2,3,4,5,18,13,14,15,16,17,13),
(14,1,2,3,4,5,6,18,13,14,15,16,17,14),
(15,1,2,3,4,5,6,18,13,14,15,16,17,15),
(16,1,2,3,4,5,6,13,14,15,16,17,18,16),
(17,2,3,4,5,6,1,13,14,15,16,17,18,17),
(18,2,3,4,5,6,1,14,15,16,17,18,13,18),
(19,3,4,5,6,1,2,14,15,16,17,18,13,19),
(20,3,4,5,6,1,2,14,15,16,17,18,13,20),
(21,3,4,5,6,1,2,14,15,16,17,18,13,21),
(22,3,4,5,6,1,2,14,15,16,17,18,13,22),
(23,3,4,5,6,1,2,14,15,16,17,18,13,23),
(24,3,4,5,6,1,2,14,15,16,17,18,13,24),
(25,3,4,5,6,1,2,14,15,16,17,18,13,25),
(26,3,4,5,6,1,2,15,16,17,18,13,14,26),
(27,4,5,6,1,2,3,15,16,17,18,13,14,27),
(28,4,5,6,1,2,3,15,16,17,18,13,14,28),
(29,4,5,6,1,2,3,15,16,17,18,13,14,29),
(30,4,5,6,1,2,3,15,16,17,18,13,14,30),
(31,4,5,6,1,2,3,16,17,18,13,14,15,31),
(32,5,6,1,2,3,4,16,17,18,13,14,15,32),
(33,5,6,1,2,3,4,17,18,13,14,15,16,33),
(34,5,6,1,2,3,4,17,18,13,14,15,16,34),
(35,6,1,2,3,4,5,17,18,13,14,15,16,35),
(36,6,1,2,3,4,5,17,18,13,14,15,16,36),
(37,6,1,2,3,4,5,18,13,14,15,16,17,37),
(38,1,2,3,4,5,6,18,13,14,15,16,17,38),
(39,1,2,3,4,5,6,18,13,14,15,16,17,39),
(40,1,2,3,4,5,6,13,14,15,16,17,18,40),
(41,6,1,2,3,4,7,17,18,13,14,15,20,41),
(42,6,1,2,3,10,7,18,13,14,15,20,17,42),
(43,1,2,3,4,5,6,18,13,20,15,16,17,43);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `set_plus_minus` */

insert  into `set_plus_minus`(`id`,`opponent_error`,`team_fault`,`match_evaluation_id`) values 
(1,'10','1',1),
(2,'0','0',2),
(3,'0','0',3),
(4,'0','0',4),
(5,'0','0',5);

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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

/*Data for the table `set_substitution` */

insert  into `set_substitution`(`id`,`position`,`rotation_player_id`,`substitute_player_id`,`point1`,`point2`,`point1_at_rally_id`,`point2_at_rally_id`,`match_evaluation_id`,`type`) values 
(1,1,1,NULL,NULL,NULL,NULL,NULL,1,1),
(2,2,2,NULL,NULL,NULL,NULL,NULL,1,1),
(3,3,3,NULL,NULL,NULL,NULL,NULL,1,1),
(4,4,4,NULL,NULL,NULL,NULL,NULL,1,1),
(5,5,5,NULL,NULL,NULL,NULL,NULL,1,1),
(6,6,6,NULL,NULL,NULL,NULL,NULL,1,1),
(7,1,18,NULL,NULL,NULL,NULL,NULL,1,2),
(8,2,13,NULL,NULL,NULL,NULL,NULL,1,2),
(9,3,14,NULL,NULL,NULL,NULL,NULL,1,2),
(10,4,15,NULL,NULL,NULL,NULL,NULL,1,2),
(11,5,16,NULL,NULL,NULL,NULL,NULL,1,2),
(12,6,17,NULL,NULL,NULL,NULL,NULL,1,2),
(13,1,6,NULL,NULL,NULL,NULL,NULL,2,1),
(14,2,1,NULL,NULL,NULL,NULL,NULL,2,1),
(15,3,2,NULL,NULL,NULL,NULL,NULL,2,1),
(16,4,3,NULL,NULL,NULL,NULL,NULL,2,1),
(17,5,4,10,'0 : 1',NULL,41,NULL,2,1),
(18,6,8,NULL,NULL,NULL,NULL,NULL,2,1),
(19,1,17,NULL,NULL,NULL,NULL,NULL,2,2),
(20,2,18,NULL,NULL,NULL,NULL,NULL,2,2),
(21,3,13,NULL,NULL,NULL,NULL,NULL,2,2),
(22,4,14,NULL,NULL,NULL,NULL,NULL,2,2),
(23,5,15,NULL,NULL,NULL,NULL,NULL,2,2),
(24,6,20,NULL,NULL,NULL,NULL,NULL,2,2),
(25,1,1,NULL,NULL,NULL,NULL,NULL,3,1),
(26,2,2,NULL,NULL,NULL,NULL,NULL,3,1),
(27,3,3,NULL,NULL,NULL,NULL,NULL,3,1),
(28,4,4,NULL,NULL,NULL,NULL,NULL,3,1),
(29,5,5,NULL,NULL,NULL,NULL,NULL,3,1),
(30,6,6,NULL,NULL,NULL,NULL,NULL,3,1),
(31,1,18,NULL,NULL,NULL,NULL,NULL,3,2),
(32,2,13,NULL,NULL,NULL,NULL,NULL,3,2),
(33,3,20,NULL,NULL,NULL,NULL,NULL,3,2),
(34,4,15,NULL,NULL,NULL,NULL,NULL,3,2),
(35,5,16,NULL,NULL,NULL,NULL,NULL,3,2),
(36,6,17,NULL,NULL,NULL,NULL,NULL,3,2),
(37,1,18,NULL,NULL,NULL,NULL,NULL,4,1),
(38,2,13,NULL,NULL,NULL,NULL,NULL,4,1),
(39,3,14,NULL,NULL,NULL,NULL,NULL,4,1),
(40,4,15,NULL,NULL,NULL,NULL,NULL,4,1),
(41,5,16,NULL,NULL,NULL,NULL,NULL,4,1),
(42,6,17,NULL,NULL,NULL,NULL,NULL,4,1),
(43,1,1,NULL,NULL,NULL,NULL,NULL,4,2),
(44,2,2,NULL,NULL,NULL,NULL,NULL,4,2),
(45,3,3,NULL,NULL,NULL,NULL,NULL,4,2),
(46,4,4,NULL,NULL,NULL,NULL,NULL,4,2),
(47,5,5,NULL,NULL,NULL,NULL,NULL,4,2),
(48,6,6,NULL,NULL,NULL,NULL,NULL,4,2),
(49,1,17,NULL,NULL,NULL,NULL,NULL,5,1),
(50,2,18,NULL,NULL,NULL,NULL,NULL,5,1),
(51,3,13,NULL,NULL,NULL,NULL,NULL,5,1),
(52,4,14,NULL,NULL,NULL,NULL,NULL,5,1),
(53,5,15,NULL,NULL,NULL,NULL,NULL,5,1),
(54,6,16,NULL,NULL,NULL,NULL,NULL,5,1),
(55,1,6,NULL,NULL,NULL,NULL,NULL,5,2),
(56,2,1,NULL,NULL,NULL,NULL,NULL,5,2),
(57,3,2,NULL,NULL,NULL,NULL,NULL,5,2),
(58,4,3,NULL,NULL,NULL,NULL,NULL,5,2),
(59,5,4,NULL,NULL,NULL,NULL,NULL,5,2),
(60,6,5,NULL,NULL,NULL,NULL,NULL,5,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `set_timeout` */

insert  into `set_timeout`(`id`,`position`,`timeOut`,`A`,`B`,`match_evaluation_id`,`timeout_at_rally`) values 
(1,1,'VollyClub','0','1',2,41),
(2,1,'Select','0','0',1,0),
(3,2,'Select','25','15',1,40),
(4,3,'VollyClub','25','15',1,40);

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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

/*Data for the table `setrotationorder` */

insert  into `setrotationorder`(`id`,`position`,`playerId`,`match_evaluation_id`,`type`) values 
(1,1,1,1,1),
(2,2,2,1,1),
(3,3,3,1,1),
(4,4,4,1,1),
(5,5,5,1,1),
(6,6,6,1,1),
(7,7,7,1,1),
(8,1,18,1,2),
(9,2,13,1,2),
(10,3,14,1,2),
(11,4,15,1,2),
(12,5,16,1,2),
(13,6,17,1,2),
(14,7,19,1,2),
(15,1,6,2,1),
(16,2,1,2,1),
(17,3,2,2,1),
(18,4,3,2,1),
(19,5,4,2,1),
(20,6,5,2,1),
(21,7,7,2,1),
(22,1,17,2,2),
(23,2,18,2,2),
(24,3,13,2,2),
(25,4,14,2,2),
(26,5,15,2,2),
(27,6,20,2,2),
(28,7,19,2,2),
(29,1,1,3,1),
(30,2,2,3,1),
(31,3,3,3,1),
(32,4,4,3,1),
(33,5,5,3,1),
(34,6,6,3,1),
(35,7,7,3,1),
(36,1,18,3,2),
(37,2,13,3,2),
(38,3,20,3,2),
(39,4,15,3,2),
(40,5,16,3,2),
(41,6,17,3,2),
(42,7,19,3,2),
(43,1,18,4,1),
(44,2,13,4,1),
(45,3,14,4,1),
(46,4,15,4,1),
(47,5,16,4,1),
(48,6,17,4,1),
(49,7,19,4,1),
(50,1,1,4,2),
(51,2,2,4,2),
(52,3,3,4,2),
(53,4,4,4,2),
(54,5,5,4,2),
(55,6,6,4,2),
(56,7,7,4,2),
(57,1,17,5,1),
(58,2,18,5,1),
(59,3,13,5,1),
(60,4,14,5,1),
(61,5,15,5,1),
(62,6,16,5,1),
(63,7,19,5,1),
(64,1,6,5,2),
(65,2,1,5,2),
(66,3,2,5,2),
(67,4,3,5,2),
(68,5,4,5,2),
(69,6,5,5,2),
(70,7,7,5,2);

/*Table structure for table `shortcut_settings` */

DROP TABLE IF EXISTS `shortcut_settings`;

CREATE TABLE `shortcut_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shortcutId` int(11) DEFAULT NULL,
  `headingId` int(11) DEFAULT NULL,
  `code` varchar(100) NOT NULL,
  `abbr` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;

/*Data for the table `shortcut_settings` */

insert  into `shortcut_settings`(`id`,`shortcutId`,`headingId`,`code`,`abbr`) values 
(1,1,1,'S','S'),
(2,2,1,'A','OH'),
(3,3,1,'M','MB'),
(4,4,1,'UV','U'),
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
(53,88,11,'B4','ZB'),
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
(69,58,15,'N1','ON'),
(70,59,15,'N2','CN'),
(71,60,15,'N3','AN'),
(72,61,15,'N4','LT'),
(73,70,15,'N5','ANF'),
(74,62,16,'LO','LO'),
(75,63,16,'LK','LC'),
(76,64,17,'K','Kill'),
(77,65,17,'D','SF'),
(78,66,17,'LI','LI'),
(79,67,17,'CB','CB'),
(80,77,17,'ST','S'),
(81,78,18,'HA','HA'),
(82,79,18,'HB','HB'),
(83,80,18,'HD','HD'),
(84,85,18,'OA','OA'),
(85,86,18,'OB','OB'),
(86,81,19,'C1','C'),
(87,82,19,'C2','S'),
(88,83,19,'C3','D');

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
(1,'VollyClub','VC',1,'Sachin','Akash','Vivek','','Bhushan',0),
(2,'UnitedVolly','UV',1,'Sarvesh','Harsh','Ramesh','Surendra','Aniket',0);

/*Table structure for table `teams_copy` */

DROP TABLE IF EXISTS `teams_copy`;

CREATE TABLE `teams_copy` (
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
  KEY `competition_id` (`competition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `teams_copy` */

insert  into `teams_copy`(`id`,`name`,`shortcode`,`competition_id`,`coach`,`asst_coach`,`trainer`,`medical_officer`,`analyzer`,`isdeleted`) values 
(1,'VollyClub','VC',1,'Sachin','Akash','Vivek','','Bhushan',0),
(2,'UnitedVolly','UV',1,'Sarvesh','Harsh','Ramesh','Surendra','Aniket',0);

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
(1,'John Smith','root','johnsmith@testmail.com','3B46-5733-08CA-9CBB-8CE7',1,'','2018-11-09 10:52:28');

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
