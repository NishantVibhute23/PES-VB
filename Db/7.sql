/*
SQLyog Community
MySQL - 5.5.33 : Database - vollyball2
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
(1,'Vollyball Season 2018','Delhi	','2018-10-01','2018-10-31','Open',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_set` */

insert  into `match_evaluation_set`(`id`,`match_evaluation_team_id`,`set_no`,`homescore`,`opponentscore`,`won_by`,`start_time`,`end_time`,`evaluator`,`date`) values 
(1,1,1,0,1,0,'17:00:00','00:00:00','John Smith','2018-11-14');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `match_evaluation_set_latest_rotation` */

insert  into `match_evaluation_set_latest_rotation`(`id`,`pos1playerId`,`pos2playerId`,`pos3playerId`,`pos4playerId`,`pos5playerId`,`pos6playerId`,`match_evaluation_set_id`,`pos1OppplayerId`,`pos2OppplayerId`,`pos3OppplayerId`,`pos4OppplayerId`,`pos5OppplayerId`,`pos6OppplayerId`) values 
(1,1,2,3,4,5,6,1,18,13,14,15,16,17);

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

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
(13,1,1,25),
(14,1,1,26),
(15,1,1,27),
(16,1,2,13),
(17,1,2,14),
(18,1,2,15),
(19,1,2,16),
(20,1,2,17),
(21,1,2,18),
(22,1,2,19),
(23,1,2,20),
(24,1,2,21),
(25,1,2,22),
(26,1,2,23),
(27,1,2,24);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `rally` */

insert  into `rally`(`id`,`number`,`home_score`,`opponent_score`,`start_time`,`end_time`,`evaluation_id`,`start_by`,`wonby`) values 
(1,1,0,1,'12:03:21','12:17:00',1,1,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `rally_details` */

insert  into `rally_details`(`id`,`skill`,`chest_no`,`rating`,`order_num`,`rally_id`,`code`) values 
(1,1,1,2,1,1,'q-7-x'),
(2,2,3,3,1,1,'e-11-c'),
(3,3,4,1,1,1,'t-3-z');

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

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
(12,83,'11-',2),
(13,11,'',2),
(14,12,'',2),
(15,14,'',2),
(16,17,'',2),
(17,19,'',2),
(18,20,'',2),
(19,26,'',2),
(20,94,'',2),
(21,13,'',2),
(22,15,'',2),
(23,16,'',2),
(24,18,'MB',2),
(25,21,'',2),
(26,22,'',2),
(27,23,'0 : 0',2),
(28,24,'RR',2),
(29,25,'',2),
(30,91,'',2),
(31,92,'',2),
(32,93,'',2),
(33,84,'3-',3),
(34,27,'',3),
(35,28,'',3),
(36,29,'',3),
(37,30,'',3),
(38,31,'',3),
(39,35,'',3),
(40,37,'',3),
(41,38,'',3),
(42,32,'',3),
(43,33,'',3),
(44,34,'',3),
(45,36,'',3),
(46,39,'',3),
(47,40,'0 : 0',3),
(48,41,'RM',3),
(49,88,'',3);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `rally_rotation_order` */

insert  into `rally_rotation_order`(`id`,`pos1playerId`,`pos2playerId`,`pos3playerId`,`pos4playerId`,`pos5playerId`,`pos6playerId`,`pos1playerIdOpp`,`pos2playerIdOpp`,`pos3playerIdOpp`,`pos4playerIdOpp`,`pos5playerIdOpp`,`pos6playerIdOpp`,`rallyId`) values 
(1,1,2,3,4,5,6,18,13,14,15,16,17,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `set_plus_minus` */

insert  into `set_plus_minus`(`id`,`opponent_error`,`team_fault`,`match_evaluation_id`) values 
(1,'0','0',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

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
(12,6,17,NULL,NULL,NULL,NULL,NULL,1,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `setrotationorder` */

insert  into `setrotationorder`(`id`,`position`,`playerId`,`match_evaluation_id`,`type`) values 
(1,1,1,1,1),
(2,2,2,1,1),
(3,3,3,1,1),
(4,4,4,1,1),
(5,5,5,1,1),
(6,6,6,1,1),
(7,7,27,1,1),
(8,1,18,1,2),
(9,2,13,1,2),
(10,3,14,1,2),
(11,4,15,1,2),
(12,5,16,1,2),
(13,6,17,1,2),
(14,7,19,1,2);

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
(4,4,1,'U','U'),
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
(80,77,17,'ST','ST'),
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
(1,'John Smith','root','john@gmail.com','',0,'','2018-11-10 12:21:55');

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
