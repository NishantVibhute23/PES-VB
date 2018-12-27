-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vollyball
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `batch`
--

DROP TABLE IF EXISTS `batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batchmatchplayer`
--

DROP TABLE IF EXISTS `batchmatchplayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batchmatchplayer`
--

LOCK TABLES `batchmatchplayer` WRITE;
/*!40000 ALTER TABLE `batchmatchplayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `batchmatchplayer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batchteam`
--

DROP TABLE IF EXISTS `batchteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `batchteam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `batch_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `batchteam_ibfk_1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batchteam`
--

LOCK TABLES `batchteam` WRITE;
/*!40000 ALTER TABLE `batchteam` DISABLE KEYS */;
/*!40000 ALTER TABLE `batchteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `venue` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `age_group` varchar(200) NOT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,'Test Competition','Mumbai','2018-12-08','2018-12-31','Open',0);
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_rating`
--

DROP TABLE IF EXISTS `m_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `m_rating` (
  `id` int(11) NOT NULL,
  `grade` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_rating`
--

LOCK TABLES `m_rating` WRITE;
/*!40000 ALTER TABLE `m_rating` DISABLE KEYS */;
INSERT INTO `m_rating` VALUES (1,'Poor'),(2,'Below Average'),(3,'Average'),(4,'Above Average'),(5,'Excellent');
/*!40000 ALTER TABLE `m_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_skill_desc_criteria_point`
--

DROP TABLE IF EXISTS `m_skill_desc_criteria_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `m_skill_desc_criteria_point` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `abbreviation` varchar(20) NOT NULL,
  `skill_desc_criteria_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_desc_criteria_id` (`skill_desc_criteria_id`),
  CONSTRAINT `m_skill_desc_criteria_point_ibfk_1` FOREIGN KEY (`skill_desc_criteria_id`) REFERENCES `m_skills_desc_criteria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_skill_desc_criteria_point`
--

LOCK TABLES `m_skill_desc_criteria_point` WRITE;
/*!40000 ALTER TABLE `m_skill_desc_criteria_point` DISABLE KEYS */;
INSERT INTO `m_skill_desc_criteria_point` VALUES (1,'','JF',1),(2,'','JP',1),(3,'','SF',1),(4,'','SS',1),(5,'','JS',1),(6,'','DC',2),(7,'','C',3),(8,'','L',3),(9,'','1',4),(10,'','5',4),(11,'','6',4),(12,'','1',5),(13,'','2',5),(14,'','3',5),(15,'','4',5),(16,'','5',5),(17,'','6',5),(18,'','5',6),(19,'','4',6),(20,'','3',6),(21,'','2',6),(22,'','OH',7),(23,'','MB',7),(24,'','L',7),(25,'','U',7),(26,'','0',8),(27,'','RC',9),(28,'','SC',9),(29,'','FR',10),(30,'','FM',10),(31,'','FL',10),(32,'','RR',10),(33,'','RM',10),(34,'','RL',10),(35,'','IN',11),(36,'','OT',11),(37,'','BT',11),(38,'','OL',11),(39,'','D',11),(40,'','BC',11),(41,'','4C',12),(42,'','3C',12),(43,'','2C',12),(44,'','5C',12),(45,'','DC',13),(46,'','PP',13),(47,'','SP',13),(48,'','MB',13),(49,'','BP',13),(50,'','NSE',13),(51,'','NEL',13),(52,'','LOW',14),(53,'','MEDIUM',14),(54,'','HIGH',14),(55,'','1',15),(56,'','2',15),(57,'','3',15),(58,'','4',15),(59,'','5',15),(60,'','6',15),(61,'','1',16),(62,'','2',16),(63,'','3',16),(64,'','4',16),(65,'','5',16),(66,'','6',16),(67,'','K1',17),(68,'','K2',17),(69,'','TPS',17),(70,'','OH',18),(71,'','MB',18),(72,'','U',18),(73,'','S',18),(74,'','SGL',19),(75,'','DBL',19),(76,'','TPL',19),(77,'','1-2-3',20),(78,'','1-3-2',20),(79,'','2-1-2',20),(80,'','2-0-4',20),(81,'','3-1-2',20),(82,'','3-0-3',20),(83,'','3-2-1',20),(84,'','1',21),(85,'','2',21),(86,'','3',21),(87,'','4',21),(88,'','5',21),(89,'','6',21),(90,'','S',22),(91,'','OH',22),(92,'','MB',22),(93,'','L',22),(94,'','U',22),(95,'','00:00',23),(96,'','FR',24),(97,'','FM',24),(98,'','FL',24),(99,'','RR',24),(100,'','RM',24),(101,'','RL',24),(102,'','C',25),(103,'','L',25),(104,'','Commit',26),(105,'','Read and react',26),(106,'','Stack or zone',26),(107,'','Commit',27),(108,'','Read and react',27),(109,'','Stack or zone',27),(110,'','Kill',28),(111,'','Soft',28),(112,'','LC',28),(113,'','LO',28),(114,'','LI',28),(115,'','CB',28),(116,'','C',29),(117,'','L',29),(118,'','IN',29),(119,'','OT',29),(120,'','BT',29),(121,'','OL',29),(122,'','D',29),(123,'','BC',29),(124,'','4C',30),(125,'','3C',30),(126,'','2C',30),(127,'','5C',30),(128,'','LOW',31),(129,'','MEDIUM',31),(130,'','HIGH',31),(131,'','1',32),(132,'','2',32),(133,'','3',32),(134,'','4',32),(135,'','5',32),(136,'','6',32),(137,'','2',33),(138,'','3',33),(139,'','4',33),(140,'','1',34),(141,'','2',34),(142,'','3',34),(143,'','4',34),(144,'','5',34),(145,'','6',34),(146,'','LOC',34),(147,'','ROC',34),(148,'','BOC',34),(149,'','K1',35),(150,'','K2',35),(151,'','TP',35),(152,'','OH',36),(153,'','MB',36),(154,'','U',36),(155,'','S',36),(156,'','SGL',37),(157,'','DBL',37),(158,'','TPL',37),(159,'','1-2-3',38),(160,'','1-3-2',38),(161,'','2-1-2',38),(162,'','2-0-4',38),(163,'','3-1-2',38),(164,'','3-0-3',38),(165,'','3-2-1',38),(166,'','H',39),(167,'','OPP',39),(168,'','00:00',40),(169,'','FR',41),(170,'','FM',41),(171,'','FL',41),(172,'','RR',41),(173,'','RM',41),(174,'','RL',41),(175,'','JS',42),(176,'','RB',42),(177,'','FP',42),(178,'','HP',42),(179,'','BC',42),(180,'','HIGH',43),(181,'','MEDIUM',43),(182,'','LOW',43),(183,'','ON',44),(184,'','CN',44),(185,'','AN',44),(186,'','LT',44),(187,'','5',45),(188,'','4',45),(189,'','3',45),(190,'','2',45),(191,'','Favourable',46),(192,'','Semi Favourable',46),(193,'','Non Favourable',46),(194,'','1',47),(195,'','2',47),(196,'','3',47),(197,'','4',47),(198,'','5',47),(199,'','6',47),(200,'','1',48),(201,'','2',48),(202,'','3',48),(203,'','4',48),(204,'','5',48),(205,'','6',48),(206,'','4C',49),(207,'','3C',49),(208,'','2C',49),(209,'','5C',49),(210,'','SGL',50),(211,'','DBL',50),(212,'','TPL',50),(213,'','NB',50),(214,'','K1',51),(215,'','K2',51),(216,'','TP',51),(217,'','OH',52),(218,'','MB',52),(219,'','U',52),(220,'','S',52),(221,'','00:00',53),(222,'','JF',54),(223,'','JS',54),(224,'','JP',54),(225,'','SF',54),(226,'','SS',54),(227,'','2',55),(228,'','3',55),(229,'','4',55),(230,'','5',55),(231,'','1',56),(232,'','2',56),(233,'','3',56),(234,'','4',56),(235,'','5',56),(236,'','6',56),(237,'','1',57),(238,'','2',57),(239,'','3',57),(240,'','4',57),(241,'','5',57),(242,'','6',57),(243,'','OH',58),(244,'','MB',58),(245,'','U',58),(246,'','S',58),(247,'','L',58),(248,'','Favourable',59),(249,'','Semi Favourable',59),(250,'','Non Favourable',59),(251,'','ON',60),(252,'','CN',60),(253,'','AN',60),(254,'','LT',60),(265,'','00:00',63),(266,'','FR',64),(267,'','FM',64),(268,'','FL',64),(269,'','RR',64),(270,'','RM',64),(271,'','RL',64),(272,'','IN',65),(273,'','OT',65),(274,'','BT',65),(275,'','OL',65),(276,'','D',65),(277,'','BC',65),(278,'','HIGH',66),(279,'','MEDIUM',66),(280,'','LOW',66),(281,'','5C',67),(282,'','4C',67),(283,'','3C',67),(284,'','2C',67),(285,'','1C',67),(286,'','4',68),(287,'','3',68),(288,'','2',68),(289,'','SGL',69),(290,'','DBL',69),(291,'','TPL',69),(292,'','LO',70),(293,'','LC',70),(294,'','1-2-3',71),(295,'','1-3-2',71),(296,'','2-1-2',71),(297,'','2-0-4',71),(298,'','3-1-2',71),(299,'','3-0-3',71),(300,'','3-2-1',71),(301,'','1',72),(302,'','2',72),(303,'','3',72),(304,'','4',72),(305,'','5',72),(306,'','6',72),(307,'','1',73),(308,'','2',73),(309,'','3',73),(310,'','4',73),(311,'','5',73),(312,'','6',73),(313,'','OH',74),(314,'','MB',74),(315,'','U',74),(316,'','S',74),(317,'','L',74),(318,'','ON',75),(319,'','CN',75),(320,'','AN',75),(321,'','LT',75),(322,'','Favourable',76),(323,'','Semi Favourable',76),(324,'','Non Favourable',76),(325,'','K1',77),(326,'','K2',77),(327,'','TP',77),(328,'','FR',78),(329,'','FM',78),(330,'','FL',78),(331,'','RR',78),(332,'','RM',78),(333,'','RL',78),(341,'','00:00',80),(342,'','C',81),(343,'','L',81),(344,'','',82),(345,'','',83),(346,'','',84),(347,'','',85),(348,'','',86),(349,'','',87),(350,'','C',88),(351,'','L',88),(352,'','ANF',44),(353,'','ANF',60),(354,'','1C',12),(355,'','NC',12),(356,'','1C',30),(357,'','NC',30),(358,'','1C',49),(359,'','nC',49),(361,'','NC',67),(362,'','BT',13),(363,'','R',11),(364,'','BTL',11),(365,'','R',29),(366,'','BTL',29),(367,'','R',65),(368,'','BTL',65),(369,'','R',42),(370,'','R',28),(371,'','2-1-3',20),(372,'','Non Organised',20),(373,'','2-1-3',38),(374,'','Non Organised',38),(375,'','2-1-3',71),(376,'','Non Organised',71),(377,'','C',70),(378,'','S',70),(379,'','ODB',14),(380,'','ODB',31),(381,'','ODB',43),(383,'','ODB',66),(384,'','1-2-3',89),(385,'','1-3-2',89),(386,'','2-1-2',89),(387,'','2-0-4',89),(388,'','3-1-2',89),(389,'','3-0-3',89),(400,'','3-2-1',89),(401,'','2-1-3',89),(402,'','Non Organised',89),(403,'','HA',90),(404,'','HB',90),(405,'','HD',90),(406,'','OA',90),(407,'','1',91),(408,'','2',91),(409,'','3',91),(410,'','4',91),(411,'','5',91),(412,'','6',91),(413,'','LOC',91),(414,'','ROC',91),(415,'','BOC',91),(416,'','1',92),(417,'','2',92),(418,'','3',92),(419,'','4',92),(420,'','5',92),(421,'','6',92),(422,'','1',93),(423,'','2',93),(424,'','3',93),(425,'','4',93),(426,'','5',93),(427,'','6',93),(428,'','C',94),(429,'','S',94),(430,'','D',94),(431,'','NB',19),(432,'','NB',37),(433,'','NB',69),(434,'','1-2-3',95),(435,'','1-3-2',95),(436,'','2-1-2',95),(487,'','2-0-4',95),(488,'','3-1-2',95),(489,'','3-0-3',95),(590,'','3-2-1',95),(591,'','2-1-3',95),(592,'','Non Organised',95),(593,'','OB',90),(594,'','None',26),(595,'','None',27);
/*!40000 ALTER TABLE `m_skill_desc_criteria_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_skill_details`
--

DROP TABLE IF EXISTS `m_skill_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_skill_details`
--

LOCK TABLES `m_skill_details` WRITE;
/*!40000 ALTER TABLE `m_skill_details` DISABLE KEYS */;
INSERT INTO `m_skill_details` VALUES (1,1,1,'Service Poor',''),(2,1,2,'Advantage in Pass Reaches to the oppenent Secondary Target',''),(3,1,3,'Advantage in Pass Reaches to the oppenent Secondary Target',''),(4,1,4,'Free ball to Service side',''),(5,1,5,'Ace Serve',''),(6,2,1,'Service Error',''),(7,2,2,'Advantage in Pass Reaches to the oppenent Secondary Target',''),(8,2,3,'Advantage in Dig Reaches to the oppenent Secondary Target',''),(9,2,4,'Free ball to Attack side',''),(10,2,5,'Ace Attack',''),(11,3,1,'Block Error',''),(12,3,2,'Advantage out Pass Reaches to the oppenent Primary Target. Free ball for opponents. No effective block. Block reflected ball becomes set for opponents',''),(13,3,3,'Advantage in Dig Reaches to the Secondary Target of Opponent. Blocked out Ball reaches out of court of blockers Side. No perfect Attack',''),(14,3,4,'Free ball to Block side',''),(15,3,5,'Kill Block',''),(16,4,1,'Set Error',''),(17,4,2,'Advantage out organised Group block situation under easy pass or dig. Non effective set for attacker. Free ball situation to the opponents side',''),(18,4,3,'Advantage in unorganised Double Block Situation',''),(19,4,4,'Organised Single block situation',''),(20,4,5,'No Block Situation. Most favourable set for attacker. Best conversion of poor pass into effective set. Setter act as attacker.',''),(21,5,1,'Reception Error',''),(22,5,2,'Advantage in Pass Reaches to the oppenent Secondary Target',''),(23,5,3,'Advantage out Serve reception reaches secondary target 2 and setter does not execute set for desire attack combination. Only high set attack is possible against serve reception.',''),(24,5,4,'Serve reception reaches secondary target 1 and setter has limited scope to execute a set for combinations of attack',''),(25,5,5,'Serve reception reaches primary target and setter has full scope to execute a set for all possible combination of attack. Reception becomes set for setter to attack or imitate attack and perform set. Most favourable situation to organise attack with all possible options.',''),(26,6,1,'Dig Error',''),(27,6,2,'Advantage out Free ball situaion to oppenets. Easy dig reaches to the secondary target. Non effective pass',''),(28,6,3,'Advantage in Easy dig reaches to primary target. Dig become sudden set for attacker',''),(29,6,4,'Received most difficult ball with extraordinary dig action and ball reaches secondary target. Tactical dig becomes set for attackers.',''),(30,6,5,'Received most difficult ball extraordinary dig action and ball reached primary target','');
/*!40000 ALTER TABLE `m_skill_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_skills`
--

DROP TABLE IF EXISTS `m_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `m_skills` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_skills`
--

LOCK TABLES `m_skills` WRITE;
/*!40000 ALTER TABLE `m_skills` DISABLE KEYS */;
INSERT INTO `m_skills` VALUES (1,'Service'),(2,'Attack'),(3,'Block'),(4,'Set'),(5,'Reception'),(6,'Defence'),(7,'OP+'),(8,'TF-'),(9,'NE');
/*!40000 ALTER TABLE `m_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_skills_desc_criteria`
--

DROP TABLE IF EXISTS `m_skills_desc_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `m_skills_desc_criteria` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `skill_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `m_skills_desc_criteria_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `m_skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_skills_desc_criteria`
--

LOCK TABLES `m_skills_desc_criteria` WRITE;
/*!40000 ALTER TABLE `m_skills_desc_criteria` DISABLE KEYS */;
INSERT INTO `m_skills_desc_criteria` VALUES (1,' Type & Techniques of Service',1),(2,' Serve Tactics',1),(3,' Direction of Service',1),(4,' Serve From Zone',1),(5,' Serve To Zone',1),(6,' Reception formation',1),(7,' Receiver Position',1),(8,' Score at the time of ace serve',1),(9,' Serve in situation',1),(10,' Opponent Setter Position',1),(11,' Type & Techniques of attack',2),(12,' Attack Combination',2),(13,' Attacking Tactics',2),(14,' Attack on Tempo',2),(15,' Attacking From Zone',2),(16,'Attacking To Zone',2),(17,' Attack in phase',2),(18,' Attackers position',2),(19,'No. of Blockers',2),(20,' Opponent Defence Formation',2),(21,' Attack Defended Zone',2),(22,' Defender Role',2),(23,' Score at time of attack',2),(24,' Setter position',2),(25,'Direction of Attack',2),(26,'Type of Block',2),(27,' Type of Block',3),(28,'Technique of Block',3),(29,'Block on Type of Attack',3),(30,' Block on Combination of attack',3),(31,' Block on Attacking Tempo',3),(32,'Opponent Attacking Zone',3),(33,' Blocking Zone',3),(34,'Block Deflected ball at Zone',3),(35,' Blocking in phase',3),(36,' Blockers Position',3),(37,' No. of Blockers',3),(38,' Defence Formation',3),(39,' Block Defended court',3),(40,' Score at time of Block',3),(41,'Opponent Setter Position',3),(42,' Type of Set',4),(43,' Set Tempo',4),(44,' Reception at',4),(45,'Reception Formation',4),(46,'Parabolla of received ball',4),(47,'Set delivery from Zone',4),(48,'Set delivery to Zone',4),(49,'Combination of attack',4),(50,'No. of Blockers',4),(51,'Game of phase',4),(52,'Attackers position',4),(53,'Score at the time of set',4),(54,'Type of Serve',5),(55,'Reception Formation',5),(56,'Reception From Zone',5),(57,'Reception To Zone',5),(58,'Receiver Position',5),(59,'Parabola of Received ball for setter',5),(60,'Reception at',5),(63,'Score at the time of Reception',5),(64,'Setter Position',5),(65,'Type of Attack by opponent',6),(66,'Attack on Tempo',6),(67,'Combination of Attack',6),(68,'Blocking at Zone',6),(69,'No. of Blockers',6),(70,'Block Cover',6),(71,'Defence System ',6),(72,'Defence Sent From Zone',6),(73,'Defence Sent To Zone',6),(74,'Defenders Role',6),(75,'Defence Ball At',6),(76,'Parabola of Defended Ball for Setter',6),(77,' Defence in phase',6),(78,' Setter position',6),(80,' Score at time of Defence',6),(81,'Direction of Attack',6),(82,'Diagram Points',1),(83,'Diagram Points',2),(84,'Diagram Points',3),(85,'Diagram Points',4),(86,'Diagram Points',5),(87,'Diagram Points',6),(88,'Direction Of Block',3),(89,'Attack Cover',4),(90,'Type of Defended Ball',6),(91,'Ball Reflected Zone',2),(92,'Attack Approach Run from',2),(93,'Attack Approach Run to',2),(94,'Type of approach run',2),(95,'Attack Cover',6);
/*!40000 ALTER TABLE `m_skills_desc_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_evaluation_set`
--

DROP TABLE IF EXISTS `match_evaluation_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_evaluation_set`
--

LOCK TABLES `match_evaluation_set` WRITE;
/*!40000 ALTER TABLE `match_evaluation_set` DISABLE KEYS */;
INSERT INTO `match_evaluation_set` VALUES (1,1,1,18,25,2,'12:23:00','13:09:00','Madhuri','2018-12-08'),(2,2,1,25,18,2,'10:18:00','12:17:00','Madhuri','2018-12-22');
/*!40000 ALTER TABLE `match_evaluation_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_evaluation_set_latest_rotation`
--

DROP TABLE IF EXISTS `match_evaluation_set_latest_rotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_evaluation_set_latest_rotation`
--

LOCK TABLES `match_evaluation_set_latest_rotation` WRITE;
/*!40000 ALTER TABLE `match_evaluation_set_latest_rotation` DISABLE KEYS */;
INSERT INTO `match_evaluation_set_latest_rotation` VALUES (1,1,5,3,8,2,7,1,15,21,19,14,18,13),(2,15,21,19,14,16,13,2,1,5,3,4,2,6);
/*!40000 ALTER TABLE `match_evaluation_set_latest_rotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_evaluation_team`
--

DROP TABLE IF EXISTS `match_evaluation_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `match_evaluation_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `evaluation_team_id` int(11) NOT NULL,
  `opponent_team_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `match_id` (`match_id`),
  CONSTRAINT `match_evaluation_team_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_evaluation_team`
--

LOCK TABLES `match_evaluation_team` WRITE;
/*!40000 ALTER TABLE `match_evaluation_team` DISABLE KEYS */;
INSERT INTO `match_evaluation_team` VALUES (1,1,1,2),(2,1,2,1);
/*!40000 ALTER TABLE `match_evaluation_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_players`
--

DROP TABLE IF EXISTS `match_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_players`
--

LOCK TABLES `match_players` WRITE;
/*!40000 ALTER TABLE `match_players` DISABLE KEYS */;
INSERT INTO `match_players` VALUES (1,1,1,1),(2,1,1,2),(3,1,1,3),(4,1,1,4),(5,1,1,5),(6,1,1,6),(7,1,1,7),(8,1,1,8),(9,1,1,9),(10,1,1,10),(11,1,1,11),(12,1,1,12),(13,1,2,13),(14,1,2,14),(15,1,2,15),(16,1,2,16),(17,1,2,17),(18,1,2,18),(19,1,2,19),(20,1,2,20),(21,1,2,21),(22,1,2,22),(23,1,2,23),(24,1,2,24);
/*!40000 ALTER TABLE `match_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (1,NULL,'2018-12-08','12:20:00',1,1,2,1,1,'Semi Final-1','Mumbai',NULL,0);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'7','MA',6,0,1,0),(2,'12','MB',4,0,1,0),(3,'11','MC',5,1,1,0),(4,'3','MD',3,0,1,0),(5,'4','ME',4,0,1,0),(6,'8','MF',5,0,1,0),(7,'1','MG',2,0,1,0),(8,'9','MH',3,0,1,0),(9,'2','MI',4,0,1,0),(10,'6','MJ',4,0,1,0),(11,'10','MK',5,0,1,0),(12,'5','ML',6,0,1,0),(13,'5','KA',5,0,2,0),(14,'6','KB',3,0,2,0),(15,'7','KC',6,0,2,0),(16,'10','KD',4,0,2,0),(17,'9','KE',5,0,2,0),(18,'18','KF',4,0,2,0),(19,'8','KG',5,1,2,0),(20,'12','KH',4,0,2,0),(21,'16','KI',3,0,2,0),(22,'3','KJ',4,0,2,0),(23,'17','KL',6,0,2,0),(24,'1','KM',2,0,2,0);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pool`
--

DROP TABLE IF EXISTS `pool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `competition_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `competition_id` (`competition_id`),
  CONSTRAINT `pool_ibfk_1` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pool`
--

LOCK TABLES `pool` WRITE;
/*!40000 ALTER TABLE `pool` DISABLE KEYS */;
/*!40000 ALTER TABLE `pool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rally`
--

DROP TABLE IF EXISTS `rally`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rally`
--

LOCK TABLES `rally` WRITE;
/*!40000 ALTER TABLE `rally` DISABLE KEYS */;
INSERT INTO `rally` VALUES (1,1,0,1,'12:25:30','12:27:17',1,1,2),(2,2,1,1,'12:27:26','12:43:31',1,2,1),(3,3,1,2,'12:43:36','12:44:53',1,1,2),(4,4,2,2,'12:57:14','12:58:29',1,2,1),(5,5,3,2,'12:59:26','13:17:50',1,1,1),(6,6,3,3,'13:19:42','13:26:13',1,1,2),(7,7,4,3,'13:27:29','13:44:13',1,2,1),(8,8,5,3,'13:52:26','13:58:42',1,1,1),(9,9,5,4,'13:58:55','14:04:09',1,1,2),(10,10,6,4,'14:06:06','14:06:06',1,2,1),(11,11,6,5,'14:06:23','14:07:24',1,1,2),(12,12,6,6,'14:07:52','14:09:48',1,2,2),(13,13,7,6,'14:10:21','14:10:21',1,2,1),(14,14,7,7,'14:11:05','14:25:05',1,1,2),(15,15,8,7,'14:26:07','14:32:03',1,2,1),(16,16,8,8,'14:32:22','14:35:38',1,1,2),(17,17,8,9,'15:17:04','15:19:33',1,2,2),(18,18,8,10,'15:43:15','15:44:33',1,2,2),(19,19,8,11,'15:50:36','15:51:47',1,2,2),(20,20,8,12,'15:52:48','16:05:18',1,2,2),(21,21,8,13,'16:07:37','16:17:12',1,2,2),(22,1,1,0,'10:20:41','10:20:41',2,2,1),(23,2,1,1,'10:22:30','10:25:27',2,1,2),(24,3,2,1,'10:26:54','10:28:53',2,2,1),(25,4,2,2,'10:29:46','10:32:23',2,1,2),(26,5,2,3,'10:32:31','10:37:18',2,2,2),(27,6,3,3,'10:37:37','10:39:20',2,2,1),(28,7,3,4,'10:39:27','10:43:55',2,1,2),(29,8,3,5,'10:44:02','10:46:15',2,2,2),(30,9,4,5,'10:49:43','10:51:29',2,2,1),(31,10,4,6,'10:52:45','10:53:12',2,1,2),(32,11,5,6,'10:53:18','11:01:05',2,2,1),(33,12,6,6,'11:01:10','11:01:24',2,1,1),(34,13,6,7,'11:01:55','11:02:14',2,1,2),(35,14,7,7,'11:02:46','11:09:37',2,2,1),(36,15,7,8,'11:09:43','11:11:56',2,1,2),(37,16,8,8,'11:12:02','11:13:21',2,2,1),(38,17,9,8,'11:13:36','11:17:17',2,1,1),(39,18,10,8,'11:18:00','11:18:17',2,1,1),(40,19,11,8,'11:18:23','11:18:35',2,1,1),(41,20,12,8,'11:18:42','11:19:41',2,1,1),(42,21,13,8,'11:23:33','11:26:08',2,1,1),(43,22,13,9,'11:26:14','11:26:59',2,1,2),(44,23,14,9,'11:27:32','11:28:43',2,2,1),(45,24,14,10,'11:32:07','11:32:29',2,1,2),(46,25,15,10,'11:32:39','11:32:39',2,2,1),(47,26,15,11,'11:32:53','11:34:56',2,1,2),(48,27,15,12,'11:35:04','11:35:29',2,2,2),(49,28,15,13,'11:37:25','11:38:35',2,2,2),(50,29,15,14,'11:41:09','11:41:29',2,2,2),(51,30,15,15,'11:42:55','11:44:37',2,2,2),(52,31,16,15,'11:45:19','11:46:39',2,2,1),(53,32,16,16,'11:46:45','11:47:00',2,1,2),(54,33,17,16,'11:47:07','11:48:43',2,2,1),(55,34,17,17,'11:48:48','11:51:27',2,1,2),(56,35,18,17,'11:52:00','11:52:46',2,2,1),(57,36,19,17,'11:52:52','11:56:11',2,1,1),(58,37,20,17,'11:56:18','11:57:09',2,1,1),(59,38,21,17,'11:57:19','11:57:33',2,1,1),(60,39,22,17,'12:06:11','12:10:06',2,1,1),(61,40,23,17,'12:10:16','12:10:34',2,1,1),(62,41,23,18,'12:10:39','12:11:37',2,1,2),(63,42,24,18,'12:11:49','12:13:23',2,2,1),(64,43,25,18,'12:16:02','12:17:59',2,1,1),(65,22,9,13,'12:35:33','12:37:01',1,2,1),(66,23,9,14,'12:37:04','12:37:21',1,1,2),(67,24,10,14,'12:37:25','12:37:25',1,2,1),(68,25,10,15,'12:37:29','12:37:47',1,1,2),(69,26,11,15,'12:37:50','12:39:45',1,2,1),(70,27,12,15,'12:39:48','12:40:24',1,1,1),(71,28,13,15,'12:40:27','12:41:10',1,1,1),(72,29,14,15,'12:41:13','12:41:27',1,1,1),(73,30,15,15,'12:41:29','12:43:52',1,1,1),(74,31,15,16,'12:44:33','12:45:26',1,1,2),(75,32,16,16,'12:45:38','12:45:38',1,2,1),(76,33,16,17,'12:45:41','12:46:31',1,1,2),(77,34,17,17,'12:46:35','12:49:31',1,2,1),(78,35,17,18,'12:50:17','12:51:36',1,1,2),(79,36,17,19,'12:51:43','12:54:11',1,2,2),(80,37,17,20,'12:54:45','12:56:06',1,2,2),(81,38,17,21,'12:56:29','12:56:53',1,2,2),(82,39,17,22,'13:02:01','13:06:07',1,2,2),(83,40,17,23,'13:06:18','13:06:51',1,2,2),(84,41,18,23,'13:06:56','13:07:39',1,2,1),(85,42,18,24,'13:07:46','13:08:36',1,1,2),(86,43,18,25,'13:08:41','13:09:53',1,2,2);
/*!40000 ALTER TABLE `rally` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rally_details`
--

DROP TABLE IF EXISTS `rally_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rally_details`
--

LOCK TABLES `rally_details` WRITE;
/*!40000 ALTER TABLE `rally_details` DISABLE KEYS */;
INSERT INTO `rally_details` VALUES (1,1,1,1,1,1,'q-7-s1-3r-h12a-h3b-z'),(2,5,2,3,1,2,'w-12-s4-3r-n5-f-h51b-h61b-c'),(3,4,1,3,1,2,'r-7-a7-t2-n5-f-3r-33-e2-q1-d1-h61b-h2a-c'),(4,2,5,5,1,2,'e-4-a1-33-t2-q1-e2-d23-b3-c1-h2a-o6d-b'),(5,1,5,2,1,3,'q-4-s3-3r-h62d-o6d-x'),(6,8,0,1,1,3,'i'),(7,5,7,5,1,4,'w-1-s3-3r-f1-h61d-h3d-n1-b'),(8,4,1,3,1,4,'r-7-a7-t2-n1-3r-f-22-e2-q3-dn-h3b-h4a-c'),(9,2,2,5,1,4,'e-12-a1-22-t2-q3-e2-d22-b1-c1-h4a-o6d-b'),(10,1,3,3,1,5,'q-11-s1-3r-h62a-o6d-c'),(11,6,2,5,1,5,'y-12-a5-t3-e2-nn-lk-dn-n2-f-q2-oa-dn-h4b-h3a-b'),(12,4,1,3,1,5,'r-7-w3-t2-n2-3r-f-22-e2-q2-dn-h3a-h2a-c'),(13,2,4,3,1,5,'e-3-h2a-a7-22-t2-q2-e2-d23-b1-c1-o11c-c'),(14,7,0,5,1,5,'u'),(15,1,3,2,1,6,'q-11-s1-3r-h62a-o5d-x'),(16,3,6,3,1,6,'t-8-b2-d-a1-33-t2-q3-e2-d23-o51c-o4c-h3b-h5c-c'),(17,6,3,1,1,6,'y-11-a1-t2-33-e2-d23-cb-q3-hb-z'),(18,5,5,3,1,7,'w-4-s5-h6a-3r-f1-n3-h61b-c'),(19,4,1,2,1,7,'r-7-a7-t2-n5-3r-f1-33-e2-q1-h61b-h4a-x'),(20,2,2,4,1,7,'e-12-a5-33-t3-q1-e2-d23-b1-c1-h4a-o2d-v'),(21,6,5,5,1,7,'y-4-HA-T2-33-e2-dn-n3-f-q3-h51a-h3d-b'),(22,4,1,5,1,7,'r-7-w2-t1-n5-f-33-e1-q3-h3d-h3a-b'),(23,2,6,3,1,7,'e-8-a2-33-t1-q3-q3-e1-d1-b1-c3-h3a-o51d-c'),(24,3,2,5,1,7,'t-12-b3-k-a1-nn-t2-q3-e1-dn-o2d-h4b-o61d-b'),(25,1,4,2,1,8,'q-3-s4-3r-h62c-o6d-x'),(26,6,7,5,1,8,'y-1-a5-t1-33-e1-s-d11-n2-f-q2-oa-h3c-h2a-b'),(27,4,1,3,1,8,'r-7-t2-a7-n5-f-22-e2-q2-dn-h2a-h4b-c'),(28,2,2,5,1,8,'e-12-a1-22-t2-q2-e2-d22-b3-c2-h4b-o2d-b'),(29,1,4,3,1,9,'q-3-s5-3r-h12d-o5d-c'),(30,3,2,1,1,9,'t-12-b3-lo-a2-nn-t2-q2-e2-d22-o2d-h4b-hl2b-z'),(31,7,0,5,1,10,'u'),(32,1,2,2,1,11,'q-12-s4-3r-h62d-o6c-x'),(33,8,0,1,1,11,'i'),(34,5,2,1,1,12,'w-12-s3-3r-h1b-z'),(35,7,0,5,1,13,'u'),(36,1,6,2,1,14,'q-8-s5-3r-h52d-o6c-x'),(37,6,1,3,1,14,'y-7-a5-t2-nn-e1-cb-d1-n3-f1-q2-oa-h2b-h11c-c'),(38,6,4,4,1,14,'y-3-hd-f-q2-n1-dn-h11c-h2b-v'),(39,2,1,3,1,14,'e-7-a5-nn-t2-q2-e1-dn-b1-h2b-o5d-c'),(40,3,1,1,1,14,'t-7-b3-lk-c1-nn-t2-q3-e2-d23-z'),(41,5,2,5,1,15,'w-12-s4-3r-f-n2-h61c-h3b-b'),(42,4,1,5,1,15,'r-7-w2-t1-n2-3r-f-22-e2-q1-h3b-h3a-b'),(43,2,3,3,1,15,'e-11-a1-22-t1-q1-e1-d23-b3-c1-h3b-o2c-c'),(44,7,0,5,1,15,'u'),(45,1,1,3,1,16,'q-7-s4-3r-h12a-o6d-c'),(46,3,3,1,1,16,'t-11-b3-d-a2-33-t1-q2-e1-dn-o3c-h3a-h51d-z'),(47,5,2,3,1,17,'w-12-s4-3r-f1-n3-h5b-h3d-c'),(48,4,1,4,1,17,'r-7-a7-t2-n3-f1-3r-33-e1-q1-h3d-h3a-v'),(49,2,3,4,1,17,'e-11-a3-33-t2-q1-e1-d1-b1-c3-h3a-o3d-v'),(50,6,5,5,1,17,'y-4-b'),(51,4,1,3,1,17,'r-7-a7-t2-f-22-e2-q3-h3d-h2a-c'),(52,2,5,1,1,17,'e-4-a1-22-t2-q3-e2-d23-b2-c2-h2a-o3c-z'),(53,5,7,1,1,18,'w-1-s4-3r-f0-h6c-hr1c-z'),(54,5,2,3,1,19,'w-12-s4-3r-f1-n3-h5a-h3d-c'),(55,4,1,3,1,19,'r-7-a7-t2-n3-3r-f-22-e2-q1-h3d-h4b-c'),(56,2,4,1,1,19,'e-3-a7-22-t2-q1-e2-d23-b3-c2-h4b-o2d-z'),(57,5,5,5,1,20,'w-4-s4-3r-n2-f-h61b-h2a-b'),(58,4,1,3,1,20,'r-7-a7-t2-n2-3r-f-22-e4-q1-dn-h2a-h2c-c'),(59,2,5,3,1,20,'e-4-a1-22-t2-q1-e4-d23-c2-h2c-o61b-c'),(60,3,3,4,1,20,'t-11-b2-k-a2-22-t2-q2-e2-d22-o4c-h3b-h61a-v'),(61,6,2,3,1,20,'y-12-a2-t2-22-e2-cb-d22-n3-f1-q3-hb-h61a-h11c-c'),(62,4,1,3,1,20,'r-7-a7-t2-n3-f1-nn-e4-q3-dn-h11c-h61b-c'),(63,2,2,1,1,20,'e-12-a7-nn-t2-q3-e4-dn-c2-h61b-o62a-z'),(64,5,7,5,1,21,'w-1-s4-3r-h61d-h3b-f-n2-b'),(65,4,1,4,1,21,'r-7-w2-t1-n1-3r-f-33-e2-q1-h3b-h3a-v'),(66,2,3,3,1,21,'e-11-a1-33-t1-q1-e2-d23-b1-c3-h3a-o11d-c'),(67,8,0,1,1,21,'i'),(68,7,0,5,1,22,'u'),(69,1,15,3,1,23,'q-7-s4-3r-h12a-o51a-c'),(70,6,15,1,1,23,'y-7-a1-t2-33-e2-cb-d23-q2-oa-d1-o2b-h11d-z'),(71,5,24,5,1,24,'w-1-s2-3r-f-n1-h1ah2a-b'),(72,4,15,4,1,24,'r-7-w2-t1-n1-3r-f-33-e1-q1-dn-h2a-h3a-v'),(73,2,19,5,1,24,'e-8-a1-33-t1-q1-e1-d23-b2-c2-h3a-o61d-b'),(74,1,21,3,1,25,'q-16-s2-3r-h62a-o61c-c'),(75,3,19,4,1,25,'t-8-b2-d-a2-22-t2-q1-e2-d23-o4c-h2b-h6b-v'),(76,6,21,1,1,25,'y-16-a2-t2-22-e2-cb-d23-q2-hb-d1-h6b-h6a-z'),(77,5,21,3,1,26,'w-16-s1-3r-f1-n3-h11c-h61a-c'),(78,4,19,3,1,26,'r-8-a7-t2-3r-f1-nn-e2-q1-dn-h61a-h4b-c'),(79,2,18,3,1,26,'e-18-a2-nn-t2-q1-e2-d23-b3-c1-h4b-o51d-c'),(80,3,18,4,1,26,'t-18-b2-k-a1-nn-t2-q3-e2-d23-o2d-h4b-h11b-v'),(81,6,15,5,1,26,'y-7-a1-t2-nn-e2-cb-d23-f-q3-hb-h11b-h2a-b'),(82,4,14,2,1,26,'r-6-a7-t3-n1-f-22-e2-q3-d11-h2a-h4d-x'),(83,2,18,1,1,26,'e-18-a2-22-t3-q3-e2-d23-b3-c1-h2a-o62d-z'),(84,5,18,3,1,27,'w-18-s1-3r-f-n1-h51a-h3b-c'),(85,4,15,3,1,27,'r-7-w4-t2-n1-3r-f-33-e2-q1-d1-h3b-h4a-c'),(86,2,18,5,1,27,'e-18-a2-33-t2-q1-e2-d23-b2-c1-h4a-h3d-b'),(87,1,19,3,1,28,'q-8-s4-3r-h52b-o6d-c'),(88,3,14,3,1,28,'t-6-b3-k-a5-33-t3-q1-e2-d23-o4c-h2b-o4b-c'),(89,3,13,3,1,28,'t-5-b1-cb-a2-22-t1-q3-e1-d1-o3c-h3b-h51b-c'),(90,6,19,3,1,28,'y-8-a2-t1-22-e1-cb-d1-n1-f-q3-hb-dn-h51b-h3a-c'),(91,4,15,4,1,28,'r-7-a7-t2-n1-d1-f-22-e1-q3-h3a-h2b-v'),(92,8,0,1,1,28,'i'),(93,5,21,4,1,29,'w-16-s4-3r-f-n3-h6b-h3c-v'),(94,4,15,4,1,29,'r-7-w2-t1-n1-3r-f-33-e1-q1-d11-h3b-h3a-v'),(95,2,13,2,1,29,'e-5-a5-33-t1-q1-e1-d11-b1-c2-h3a-o3b-x'),(96,3,14,1,1,29,'t-6-b3-lk-a1-22-t3-q2-e2-d22-o4c-h2b-hr2b-z'),(97,5,18,2,1,30,'w-18-s4-3r-f1-n3-h51c-h51b-x'),(98,4,15,4,1,30,'r-7-a7-t2-n3-3r-f1-33-e1-q1-dn-h51b-h2b-v'),(99,2,14,5,1,30,'e-6-a2-33-t2-q1-e1-dn-b1-c1-h2b-o4b-b'),(100,1,14,1,1,31,'q-6-s3-3r-h52b-o5b-z'),(101,5,21,5,1,32,'w-16-s4-3r-f-n1-h51b-h3b-b'),(102,4,15,5,1,32,'r-7-w4-t1-n1-3r-f-33-e2-q1-dn-h3b-h2a-b'),(103,2,13,5,1,32,'e-5-a3-33-t1-q1-e2-b2-c3-h2a-o11b-b'),(104,1,18,5,1,33,'q-18-s3-3r-h62b-o1a-b'),(105,1,18,1,1,34,'q-18-s3-3r-h62b-o2a-z'),(106,5,24,5,1,35,'w-1-s4-3r-f-n1-h61d-h3a-b'),(107,2,15,3,1,35,'e-7-a5-33-t2-q1-e4-dn-c1-h3a-o2c-c'),(108,6,18,3,1,35,'y-18-a5-t2-nn-e1-st-dn-n3-f1-q3-oa-dn-h51c-h61a-c'),(109,4,15,5,1,35,'r-7-a7-t2-n3-3r-f1-nn-e2-q3-d11-h61a-h4a-b'),(110,2,21,3,1,35,'e-16-a2-33-t2-q3-e2-d23-b3-c1-h4a-o2c-c'),(111,7,0,5,1,35,'u'),(112,1,13,3,1,36,'q-5-s4-3r-h52b-o61d-c'),(113,6,15,3,1,36,'y-7-a1-t2-22-e2-st-d23-n2-f-q2-oa-d11-h2a-h3a-c'),(114,4,19,3,1,36,'r-8-w5-t2-n2-d23-f-1c-e4-q2-h3a-h61a-c'),(115,2,18,1,1,36,'e-18-a1-1c-t2-q2-e4-dn-c2-h61b-h3a-z'),(116,5,18,5,1,37,'w-18-s1-3r-f-n2-h61c-h2d-b'),(117,4,15,4,1,37,'r-7-w4-t1-n2-3r-f-33-e1-q1-d11-h2d-h3a-v'),(118,2,19,5,1,37,'e-8-a2-33-t1-q1-e1-d11-b1-c2-h3a-o2a-b'),(119,1,15,3,1,38,'q-7-s4-3r-2r-h12b-o51b-c'),(120,3,19,4,1,38,'t-8-b1-k-a3-33-t1-q2-e1-d11-o3c-h3b-h2a-v'),(121,6,14,3,1,38,'y-6-a3-t1-33-e1-cb-d1-q3-hb-d11-h2a-o2b-c'),(122,3,19,5,1,38,'t-8-b2-k-a1-nn-t2-q3-e2-d23-o2c-h2b-o2c-b'),(123,1,15,5,1,39,'q-7-s4-3r-h12c-o61d-b'),(124,1,15,5,1,40,'q-7-s4-3r-h12c-o51d-b'),(125,1,15,2,1,41,'q-7-s4-3r-h12c-o11c-x'),(126,2,21,3,4,41,'e-16-a2-22-t3-q2-e2-d22-b3-c1-h4a-o61a-c'),(127,7,0,5,5,41,'u'),(128,6,15,5,2,41,'y-7-a1-t2-1c-e1-st-d22-n1-f-q2-oa-dn-h61c-h3a-b'),(129,4,14,3,3,41,'r-6-a7-t3-n1-f-22-e2-q2-d22-h3a-h4a-c'),(130,1,15,2,1,42,'q-7-s4-3r-h12c-o61b-x'),(131,4,14,3,1,42,'r-6-a7-t3-n3-f0-1c-e4-q3-d22-h11b-h61b-c'),(132,6,15,2,1,42,'y-7-a1-t1-33-e2-st-d22-n3-f0-q2-oa-dn-h11a-h11b-x'),(133,2,18,5,1,42,'e-18-a1-nn-t3-q3-e4-dn-c2-h61b-o6a-b'),(134,1,15,2,1,43,'q-7-s4-3r-h12c-o1c-x'),(135,6,15,1,1,43,'y-7-a5-t1-33-e4-dn-q2-oa-dn-h11b-h2c-z'),(136,5,24,5,1,44,'w-1-s3-3r-f-n1-h61c-h3a-b'),(137,4,15,4,1,44,'r-7-w1-t2-n1-3r-f-33-e1-q1-dn-h3a-h4a-v'),(138,2,14,5,1,44,'e-6-a2-nn-t2-q1-e1-d11-b2-c1-h4a-h61c-b'),(139,1,21,1,1,45,'q-16-s3-3r-h62b-o5b-z'),(140,7,0,5,1,46,'u'),(141,1,19,3,1,47,'q-8-s4-3r-h52c-o61b-c'),(142,6,15,3,1,47,'y-7-a6-t2-1c-e4-dn-n3-f1-q2-oa-dn-h11c-h3c-c'),(143,4,14,3,1,47,'r-6-a7-t3-n3-f1-1c-e2-q1-dn-h3a-h2a-c'),(144,2,18,1,1,47,'e-18-a2-1c-t3-q1-e2-d23-b2-c1-h4a-h2b-z'),(145,5,18,1,1,48,'w-18-s4-3r-f0-n2-h51c-o4d-z'),(146,5,24,2,1,49,'w-1-s4-3r-f1-n3-h11d-h2d-x'),(147,4,15,2,1,49,'r-7-w2-t1-n3-3r-f1-33-e1-q1-dn-h2d-h3a-x'),(148,8,0,1,1,49,'i'),(149,5,13,1,1,50,'w-5-h51a-h4c-s4-3r-z'),(150,5,24,5,1,51,'w-1-s4-3r-f-n1-h61b-h3b-b'),(151,4,15,3,1,51,'r-7-a7-t3-n1-3r-f-33-e2-q1-dn-h3b-h3b-c'),(152,2,14,4,1,51,'e-6-a1-33-t2-q1-e2-d23-b2-c1-h2b-o3a-v'),(153,8,0,1,1,51,'i'),(154,5,21,5,1,52,'w-16-s4-3r-f-11-h51c-h2a-b'),(155,4,15,4,1,52,'r-7-w1-t1-n1-3r-f-33-e1-q1-dn-h2a-h3a-v'),(156,2,13,5,1,52,'e-5-a2-33-t1-q1-e1-d11-b1-c3-h3a-o51d-b'),(157,1,14,1,1,53,'q-6-s3-3r-h52b-o62c-z'),(158,5,21,3,1,54,'w-16-s4-3r-f1-n3-h51b-h3c-c'),(159,4,15,5,1,54,'r-7-w4-t2-n3-3r-f1-22-e1-q1-dn-h3b-h2b-b'),(160,2,16,5,1,54,'e-10-a1-22-t2-q1-e1-d23-b1-c1-h2b-o11d-b'),(161,1,16,3,1,55,'q-10-s1-3r-h62b-o51a-c'),(162,6,16,5,1,55,'y-10-a1-t2-22-e2-cb-d22-n2-f-q2-oa-d11-h6b-h2a-b'),(163,4,15,3,1,55,'r-7-w5-t2-n2-3r-f1-33-e4-q1-dn-h2a-h11b-c'),(164,2,14,2,1,55,'e-6-a6-22-q1-t2-e4-dn-c2-h11b-o4a-x'),(165,6,16,1,1,55,'y-10-a6-t2-1c-e3-st-d33-q3-oa-dn-h6b-z'),(166,5,24,2,1,56,'w-1-s1-3r-f1-n1-h51b-h3a-x'),(167,2,15,5,1,56,'e-7-a5-nn-t2-q1-e1-d11-b2-c3-h3a-h2b-b'),(168,1,13,3,1,57,'q-5-s4-3r-h52b-o51a-c'),(169,3,15,3,1,57,'t-7-b3-li-a2-22-t2-q2-e2-d22-o2c-h2b-h51b-c'),(170,6,13,4,1,57,'y-5-a2-t2-22-e2-lk-d22-n4-f1-q2-hb-d11-h51b-h2a-v'),(171,4,19,2,1,57,'r-8-w5-t2-n4-f1-1c-q2-d22-h2a-h61b-x'),(172,3,19,5,1,57,'t-8-b1-k-a1-33-t1-q3-e2-d23-o3b-h3a-o3a-b'),(173,1,13,2,1,58,'q-5-s4-3r-h52c-o11b-x'),(174,6,13,3,1,58,'y-5-a5-t2-nn-e4-q2-oa-dn-h4c-o2a-c'),(175,7,0,5,1,58,'u'),(176,1,13,5,1,59,'q-5-s4-3r-h52c-h61d-b'),(177,1,13,3,1,60,'q-5-s4-3r-h52c-o61b-c'),(178,3,15,4,1,60,'t-7-b3-cb-a2-22-t2-q2-e2-d22-o2c-h2b-h2b-v'),(179,6,15,5,1,60,'y-7-a2-t2-22-e2-cb-d22-n2-f-q2-hd-dn-h2b-h2a-b'),(180,4,19,3,1,60,'r-8-a7-t3-n2-f-1c-e2-q2-dn-h3a-h4a-c'),(181,2,21,4,1,60,'e-16-a2-1c-t3-q2-e2-d23-b3-c1-h4a-o6a-v'),(182,6,14,5,1,60,'y-6-a2-t2-22-e4-dn-n1-f-q3-oa-dn-h11d-h2b-b'),(183,2,15,5,1,60,'e-7-a1-22-t2-q3-e1-d22-b3-c1-h2a-o11c-b'),(184,1,13,5,1,61,'q-5-s4-3r-h52c-o61a-b'),(185,1,13,2,1,62,'q-5-s4-3r-h52c-o61b-x'),(186,3,21,1,1,62,'t-16-b2-li-a5-nn-t2-q2-e2-d22-o3d-h3a-h2b-z'),(187,5,16,4,1,63,'w-10-s1-3r-f1-n2-h61c-h2a-v'),(188,4,15,3,1,63,'r-7-w2-t1-n2-3r-f1-33-e2-q1-dn-h2a-h3b-c'),(189,2,19,5,1,63,'e-8-a1-33-t1-q1-e2-d33-b2-c3-h3a-o11d-b'),(190,1,15,3,1,64,'q-7-s4-3r-h12a-o51d-c'),(191,6,16,5,1,64,'y-10-a6-t2-1c-e4-n1-f-q2-oa-dn-h61c-h3b-b'),(192,4,15,5,1,64,'r-7-w1-t2-n1-3r-f-33-e4-q2-h3b-h2b-b'),(193,2,14,5,1,64,'e-6-a1-33-t2-q2-e4-dn-c1-h2b-o61d-b'),(194,5,7,5,1,65,'w-1-s4-3r-f-n1-h1a-h3a-b'),(195,4,1,4,1,65,'r-7-w1-t1-n1-3r-f-33-e1-q1-d11-h3a-h4b-v'),(196,2,3,5,1,65,'e-11-a1-33-t1-q1-e1-d11-b1-c2-o4b-o11d-b'),(197,1,5,3,1,66,'q-4-s3-3r-h62b-o6c-c'),(198,8,0,1,1,66,'i'),(199,7,0,5,1,67,'u'),(200,1,3,1,1,68,'q-11-s1-3r-h62a-h3a-z'),(201,5,5,2,1,69,'w-4-s4-3r-f0-n3-h5b-h2b-x'),(202,6,2,4,1,69,'y-12-n3-f-q1-hd-h2b-h61a-v'),(203,2,5,3,1,69,'e-4-a7-nn-t3-q1-e4-dn-c2-h61a-o11d-c'),(204,7,0,5,1,69,'u'),(205,1,4,4,1,70,'q-3-s4-3r-h1a-o51d-v'),(206,2,2,5,1,70,'e-12-a7-nn-t2-q2-e4-dn-c2-h2b-o11c-b'),(207,1,4,2,1,71,'q-3-s4-3r-h12a-o11b-x'),(208,3,6,5,1,71,'t-8-b2-k-a2-33-t1-q2-e1-d11-o3d-h3a-o61d-b'),(209,1,4,5,1,72,'q-3-s4-3r-h12d-o51c-b'),(210,1,4,2,1,73,'q-3-s4-3r-h12d-o61d-x'),(211,3,2,3,1,73,'t-12-b3-lk-a1-33-t2-q2-e2-d23-o2d-h4a-h3c-c'),(212,6,1,2,1,73,'y-7-a1-t2-33-e2-lk-d23-n3-f0-q2-hb-d1-h3c-h6c-x'),(213,6,5,2,1,73,'y-4-dn-n3-f1-q1-hd-dn-h6c-h4c-x'),(214,6,6,3,1,73,'y-8-dn-f1-q1-hd-dn-h4c-o11a-c'),(215,7,0,5,1,73,'u'),(216,1,4,2,1,74,'q-3-s4-3r-h12d-o51a-x'),(217,6,7,1,1,74,'y-1-a2-t1-33-e1-st-d11-n3-f0-q2-oa-d11-h4c-h3b-z'),(218,7,0,5,1,75,'u'),(219,1,2,2,1,76,'q-12-s4-3r-h52d-o51d-x'),(220,3,1,1,1,76,'t-7-b3-lo-a1-1c-t2-q2-e2-d23-o4c-h2b-h11b-z'),(221,5,5,3,1,77,'w-4-s4-3r-f1-n3-h51d-h61b-c'),(222,4,1,3,1,77,'r-7-a7-t2-n3-3r-f1-22-t2-q1-d11-h61b-h4a-c'),(223,2,5,2,1,77,'e-4-a2-22-t2-q1-e2-d23-b3-c1-h4a-o2d-x'),(224,6,5,5,1,77,'y-4-a5-t2-33-e1-st-d11-n2-f-q3-oa-d1-h51b-h2d-b'),(225,4,1,3,1,77,'r-7-w5-t2-n2-f-33-e1-q3-d1-h2d-h61b-c'),(226,2,2,5,1,77,'e-12-a1-1c-t2-q3-e3-d33-b2-c2-h61b-o3c-b'),(227,1,6,3,1,78,'q-8-s4-3r-h52c-o61c-c'),(228,3,3,2,1,78,'t-11-b1-k-a5-nn-t2-q2-e1-d11-o3c-h3b-h3b-x'),(229,6,3,1,1,78,'y-11-a5-t2-nn-e1-st-d11-n3-f0-q2-hb-d11-h3b-h11a-z'),(230,5,5,3,1,79,'w-4-s4-3r-f0-n3-h51d-h61b-c'),(231,4,1,3,1,79,'r-7-a7-t2-n3-3r-f1-22-e2-q1-d11-h61b-h4a-c'),(232,2,5,3,1,79,'e-4-a2-22-t2-q1-e2-d23-b3-c1-h4a-o51a-c'),(233,6,2,4,1,79,'y-12-a6-t2-nn-e4-dn-n2-f-q3-oa-dn-h61c-h2c-v'),(234,2,3,1,1,79,'e-11-a1-22-t1-q3-e2-d22-b2-c2-h3a-h3b-z'),(235,5,7,5,1,80,'w-1-s4-3r-f-n2-h61b-h3b-b'),(236,2,1,4,1,80,'e-7-a5-nn-t2-q1-e4-dn-h3b-o3b-v'),(237,6,4,1,1,80,'y-3-a7-t4-nn-e4-dn-q3-dn-h2c-h2c-z'),(238,5,2,1,1,81,'w-12-s4-3r-f0-n3-h61a-h2b-z'),(239,5,2,3,1,82,'w-12-s4-3r-f1-n3-h61a-h3d-c'),(240,4,1,3,1,82,'r-7-a7-t2-n3-3r-f-t2-e2-q1-d11-h3d-h4a-c'),(241,2,5,3,1,82,'e-4-a2-22-t2-q1-e2-d23-b3-c1-h4a-o2d-c'),(242,3,1,3,1,82,'t-7-b3-li-a2-t3-t2-q3-e2-d22-o4c-h2a-h6a-c'),(243,6,7,2,1,82,'y-1-a2-t2-33-e2-lk-d22-n3-f0-q3-hb-h6a-h1b-x'),(244,6,8,3,1,82,'y-9-n3-f1-q3-hd-h1b-h61b-c'),(245,6,2,2,1,82,'y-12-n3-q3-hd-h61b-o1c-x'),(246,8,0,1,1,82,'i'),(247,5,2,1,1,83,'w-12-s4-3r-f0-n3-h61d-h4a-z'),(248,5,2,5,1,84,'w-12-s4-3r-f-n1-h61a-h3b-b'),(249,2,1,5,1,84,'e-7-a5-nn-t2-q1-e2-d22-b2-h3b-o3d-b'),(250,1,1,3,1,85,'q-7-s1-3r-h12a-o61d-c'),(251,6,1,1,1,85,'y-7-a1-t1-33-e3-cb-d33-n3-f0-oa-dn-o3d-h11c-z'),(252,5,2,2,1,86,'w-12-s4-3r-f0-n3-h51b-h1b-x'),(253,6,5,4,1,86,'y-4-n3-f-q1-hd-h1b-h61b-v'),(254,2,2,2,1,86,'e-12-a1-nn-t2-q1-e4-dn-c2-h61b-o61d-x'),(255,8,0,1,1,86,'i');
/*!40000 ALTER TABLE `rally_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rally_details_criteria`
--

DROP TABLE IF EXISTS `rally_details_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=3595 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rally_details_criteria`
--

LOCK TABLES `rally_details_criteria` WRITE;
/*!40000 ALTER TABLE `rally_details_criteria` DISABLE KEYS */;
INSERT INTO `rally_details_criteria` VALUES (1,82,'7-H12A-H3B-',1),(2,1,'JF',1),(3,6,'3',1),(4,2,'',1),(5,3,'C',1),(6,4,'1',1),(7,5,'3',1),(8,7,'U',1),(9,8,'0 : 0',1),(10,9,'RC',1),(11,10,'FR',1),(12,86,'12-H51B-H61B-',2),(13,54,'SF',2),(14,55,'3',2),(15,59,'Favourable',2),(16,60,'ANF',2),(17,56,'6',2),(18,57,'5',2),(19,58,'OH',2),(20,63,'0 : 1',2),(21,64,'RR',2),(22,85,'7-H61B-H2A-',3),(23,42,'R',3),(24,43,'MEDIUM',3),(25,44,'ANF',3),(26,45,'3',3),(27,46,'Favourable',3),(28,49,'3C',3),(29,50,'DBL',3),(30,51,'K1',3),(31,89,'1-2-3',3),(32,47,'6',3),(33,48,'2',3),(34,52,'U',3),(35,53,'0 : 1',3),(36,83,'4-H2A-O6D-',4),(37,11,'IN',4),(38,12,'3C',4),(39,14,'MEDIUM',4),(40,17,'K1',4),(41,19,'DBL',4),(42,20,'2-1-3',4),(43,26,'Stack or zone',4),(44,94,'C',4),(45,13,'',4),(46,15,'2',4),(47,16,'6',4),(48,18,'OH',4),(49,21,'6',4),(50,22,'MB',4),(51,23,'0 : 1',4),(52,24,'RR',4),(53,25,'C',4),(54,91,'',4),(55,92,'',4),(56,93,'',4),(57,82,'4-H62D-O6D-',5),(58,1,'JP',5),(59,6,'3',5),(60,2,'',5),(61,3,'C',5),(62,4,'6',5),(63,5,'6',5),(64,7,'MB',5),(65,8,'1 : 1',5),(66,9,'SC',5),(67,10,'RR',5),(68,86,'1-H61D-H3D-',7),(69,54,'JP',7),(70,55,'3',7),(71,59,'Semi Favourable',7),(72,60,'ON',7),(73,56,'3',7),(74,57,'6',7),(75,58,'L',7),(76,63,'1 : 2',7),(77,64,'RM',7),(78,85,'7-H3B-H4A-',8),(79,42,'R',8),(80,43,'MEDIUM',8),(81,44,'ON',8),(82,45,'3',8),(83,46,'Favourable',8),(84,49,'2C',8),(85,50,'DBL',8),(86,51,'TP',8),(87,89,'Non Organised',8),(88,47,'3',8),(89,48,'4',8),(90,52,'OH',8),(91,53,'1 : 2',8),(92,83,'12-H4A-O6D-',9),(93,11,'IN',9),(94,12,'2C',9),(95,14,'MEDIUM',9),(96,17,'TPS',9),(97,19,'DBL',9),(98,20,'2-0-4',9),(99,26,'Commit',9),(100,94,'C',9),(101,13,'',9),(102,15,'4',9),(103,16,'6',9),(104,18,'OH',9),(105,21,'6',9),(106,22,'S',9),(107,23,'1 : 2',9),(108,24,'RM',9),(109,25,'C',9),(110,91,'',9),(111,92,'',9),(112,93,'',9),(113,82,'11-H62A-O6D-',10),(114,1,'JF',10),(115,6,'3',10),(116,2,'',10),(117,3,'C',10),(118,4,'6',10),(119,5,'6',10),(120,7,'S',10),(121,8,'5 : 2',10),(122,9,'SC',10),(123,10,'RM',10),(124,87,'12-H4B-H3A-',11),(125,65,'R',11),(126,66,'HIGH',11),(127,67,'NC',11),(128,69,'DBL',11),(129,70,'LC',11),(130,71,'Non Organised',11),(131,75,'CN',11),(132,76,'Favourable',11),(133,77,'K2',11),(134,90,'OA',11),(135,95,'',11),(136,68,'4',11),(137,72,'4',11),(138,73,'3',11),(139,74,'U',11),(140,78,'RL',11),(141,80,'5 : 2',11),(142,81,'',11),(143,85,'7-H3A-H2A-',12),(144,42,'FP',12),(145,43,'MEDIUM',12),(146,44,'CN',12),(147,45,'3',12),(148,46,'Favourable',12),(149,49,'2C',12),(150,50,'DBL',12),(151,51,'K2',12),(152,89,'Non Organised',12),(153,47,'3',12),(154,48,'2',12),(155,52,'MB',12),(156,53,'2 : 2',12),(157,83,'3-H2A-O11C-',13),(158,11,'R',13),(159,12,'2C',13),(160,14,'MEDIUM',13),(161,17,'K2',13),(162,19,'DBL',13),(163,20,'2-1-3',13),(164,26,'Commit',13),(165,94,'C',13),(166,13,'',13),(167,15,'2',13),(168,16,'1',13),(169,18,'U',13),(170,21,'1',13),(171,22,'U',13),(172,23,'2 : 2',13),(173,24,'RL',13),(174,25,'C',13),(175,91,'',13),(176,92,'',13),(177,93,'',13),(178,82,'11-H62A-O5D-',15),(179,1,'JF',15),(180,6,'3',15),(181,2,'',15),(182,3,'C',15),(183,4,'6',15),(184,5,'5',15),(185,7,'MB',15),(186,8,'3 : 2',15),(187,9,'RC',15),(188,10,'RM',15),(189,84,'8-O51C-O51C-O51C-O4C-H3B-H5C-',16),(190,27,'RR',16),(191,28,'Soft',16),(192,29,'IN',16),(193,30,'3C',16),(194,31,'MEDIUM',16),(195,35,'TP',16),(196,37,'DBL',16),(197,38,'2-1-3',16),(198,32,'5',16),(199,33,'5',16),(200,34,'5',16),(201,36,'MB',16),(202,39,'Opp',16),(203,40,'3 : 2',16),(204,41,'RM',16),(205,88,'',16),(206,87,'11-',17),(207,65,'IN',17),(208,66,'MEDIUM',17),(209,67,'3C',17),(210,69,'DBL',17),(211,70,'C',17),(212,71,'2-1-3',17),(213,75,'',17),(214,76,'',17),(215,77,'TP',17),(216,90,'HB',17),(217,95,'',17),(218,68,'',17),(219,72,'',17),(220,73,'',17),(221,74,'',17),(222,78,'RL',17),(223,80,'3 : 2',17),(224,81,'',17),(225,86,'4-H6A-H61B-',18),(226,54,'SS',18),(227,55,'3',18),(228,59,'Favourable',18),(229,60,'AN',18),(230,56,'6',18),(231,57,'6',18),(232,58,'OH',18),(233,63,'3 : 3',18),(234,64,'RL',18),(235,85,'7-H61B-H4A-',19),(236,42,'R',19),(237,43,'MEDIUM',19),(238,44,'ANF',19),(239,45,'3',19),(240,46,'Semi Favourable',19),(241,49,'3C',19),(242,50,'DBL',19),(243,51,'K1',19),(244,89,'',19),(245,47,'6',19),(246,48,'4',19),(247,52,'MB',19),(248,53,'3 : 3',19),(249,83,'12-H4A-O2D-',20),(250,11,'D',20),(251,12,'3C',20),(252,14,'HIGH',20),(253,17,'K1',20),(254,19,'DBL',20),(255,20,'2-1-3',20),(256,26,'Commit',20),(257,94,'C',20),(258,13,'NSL',20),(259,15,'4',20),(260,16,'2',20),(261,18,'OH',20),(262,21,'2',20),(263,22,'U',20),(264,23,'3 : 3',20),(265,24,'RL',20),(266,25,'C',20),(267,91,'',20),(268,92,'',20),(269,93,'',20),(270,87,'4-H51A-H3D-',21),(271,65,'',21),(272,66,'MEDIUM',21),(273,67,'3C',21),(274,69,'DBL',21),(275,70,'',21),(276,71,'Non Organised',21),(277,75,'AN',21),(278,76,'Favourable',21),(279,77,'TP',21),(280,90,'HA',21),(281,95,'',21),(282,68,'5',21),(283,72,'5',21),(284,73,'3',21),(285,74,'OH',21),(286,78,'RL',21),(287,80,'3 : 3',21),(288,81,'',21),(289,85,'7-H3D-H3A-',22),(290,42,'RB',22),(291,43,'LOW',22),(292,44,'ANF',22),(293,45,'',22),(294,46,'Favourable',22),(295,49,'3C',22),(296,50,'SGL',22),(297,51,'TP',22),(298,89,'',22),(299,47,'3',22),(300,48,'3',22),(301,52,'OH',22),(302,53,'3 : 3',22),(303,83,'8-H3A-O51D-',23),(304,11,'OT',23),(305,12,'3C',23),(306,14,'LOW',23),(307,17,'TPS',23),(308,19,'SGL',23),(309,20,'1-2-3',23),(310,26,'Commit',23),(311,94,'D',23),(312,13,'',23),(313,15,'3',23),(314,16,'5',23),(315,18,'MB',23),(316,21,'5',23),(317,22,'S',23),(318,23,'3 : 3',23),(319,24,'RL',23),(320,25,'C',23),(321,91,'',23),(322,92,'',23),(323,93,'',23),(324,84,'12-O2D-O2D-O2D-H4B-O61D-',24),(325,27,'Stack or zone',24),(326,28,'Kill',24),(327,29,'IN',24),(328,30,'NC',24),(329,31,'MEDIUM',24),(330,35,'TP',24),(331,37,'SGL',24),(332,38,'Non Organised',24),(333,32,'2',24),(334,33,'2',24),(335,34,'2',24),(336,36,'U',24),(337,39,'Opp',24),(338,40,'3 : 3',24),(339,41,'RL',24),(340,88,'',24),(341,82,'3-H62C-O6D-',25),(342,1,'SF',25),(343,6,'3',25),(344,2,'',25),(345,3,'C',25),(346,4,'6',25),(347,5,'6',25),(348,7,'U',25),(349,8,'4 : 3',25),(350,9,'SC',25),(351,10,'RL',25),(352,87,'1-H3C-H2A-',26),(353,65,'D',26),(354,66,'LOW',26),(355,67,'3C',26),(356,69,'SGL',26),(357,70,'',26),(358,71,'1-3-2',26),(359,75,'CN',26),(360,76,'Favourable',26),(361,77,'K2',26),(362,90,'OA',26),(363,95,'',26),(364,68,'3',26),(365,72,'3',26),(366,73,'2',26),(367,74,'U',26),(368,78,'FL',26),(369,80,'4 : 3',26),(370,81,'',26),(371,85,'7-H2A-H4B-',27),(372,42,'R',27),(373,43,'MEDIUM',27),(374,44,'ANF',27),(375,45,'',27),(376,46,'Favourable',27),(377,49,'2C',27),(378,50,'DBL',27),(379,51,'K2',27),(380,89,'Non Organised',27),(381,47,'2',27),(382,48,'4',27),(383,52,'MB',27),(384,53,'4 : 3',27),(385,83,'12-H4B-O2D-',28),(386,11,'IN',28),(387,12,'2C',28),(388,14,'MEDIUM',28),(389,17,'K2',28),(390,19,'DBL',28),(391,20,'2-0-4',28),(392,26,'Stack or zone',28),(393,94,'S',28),(394,13,'NSL',28),(395,15,'4',28),(396,16,'2',28),(397,18,'OH',28),(398,21,'2',28),(399,22,'U',28),(400,23,'4 : 3',28),(401,24,'FL',28),(402,25,'C',28),(403,91,'',28),(404,92,'',28),(405,93,'',28),(406,82,'3-H12D-O5D-',29),(407,1,'SS',29),(408,6,'3',29),(409,2,'',29),(410,3,'C',29),(411,4,'1',29),(412,5,'5',29),(413,7,'S',29),(414,8,'5 : 3',29),(415,9,'RC',29),(416,10,'RL',29),(417,84,'12-O2D-O2D-O2D-H4B-HL2B-',30),(418,27,'Stack or zone',30),(419,28,'LO',30),(420,29,'OT',30),(421,30,'NC',30),(422,31,'MEDIUM',30),(423,35,'K2',30),(424,37,'DBL',30),(425,38,'2-0-4',30),(426,32,'2',30),(427,33,'2',30),(428,34,'2',30),(429,36,'U',30),(430,39,'Opp',30),(431,40,'5 : 3',30),(432,41,'RL',30),(433,88,'',30),(434,82,'12-H62D-O6C-',32),(435,1,'SF',32),(436,6,'3',32),(437,2,'',32),(438,3,'C',32),(439,4,'6',32),(440,5,'6',32),(441,7,'MB',32),(442,8,'6 : 4',32),(443,9,'SC',32),(444,10,'FL',32),(445,86,'12-H1B-',34),(446,54,'JP',34),(447,55,'3',34),(448,59,'',34),(449,60,'',34),(450,56,'',34),(451,57,'',34),(452,58,'OH',34),(453,63,'6 : 5',34),(454,64,'FM',34),(455,82,'8-H52D-O6C-',36),(456,1,'SS',36),(457,6,'3',36),(458,2,'',36),(459,3,'C',36),(460,4,'5',36),(461,5,'6',36),(462,7,'U',36),(463,8,'7 : 6',36),(464,9,'SC',36),(465,10,'FM',36),(466,87,'7-H2B-H11C-',37),(467,65,'D',37),(468,66,'MEDIUM',37),(469,67,'NC',37),(470,69,'SGL',37),(471,70,'C',37),(472,71,'1-2-3',37),(473,75,'AN',37),(474,76,'Semi Favourable',37),(475,77,'K2',37),(476,90,'OA',37),(477,95,'',37),(478,68,'2',37),(479,72,'2',37),(480,73,'1',37),(481,74,'OH',37),(482,78,'FR',37),(483,80,'7 : 6',37),(484,81,'',37),(485,87,'3-H11C-H2B-',38),(486,65,'',38),(487,66,'',38),(488,67,'',38),(489,69,'',38),(490,70,'',38),(491,71,'Non Organised',38),(492,75,'ON',38),(493,76,'Favourable',38),(494,77,'K2',38),(495,90,'HD',38),(496,95,'',38),(497,68,'1',38),(498,72,'1',38),(499,73,'2',38),(500,74,'MB',38),(501,78,'FR',38),(502,80,'7 : 6',38),(503,81,'',38),(504,83,'7-H2B-O5D-',39),(505,11,'D',39),(506,12,'NC',39),(507,14,'MEDIUM',39),(508,17,'K2',39),(509,19,'SGL',39),(510,20,'Non Organised',39),(511,26,'Commit',39),(512,94,'',39),(513,13,'',39),(514,15,'2',39),(515,16,'5',39),(516,18,'S',39),(517,21,'5',39),(518,22,'MB',39),(519,23,'7 : 6',39),(520,24,'FR',39),(521,25,'C',39),(522,91,'',39),(523,92,'',39),(524,93,'',39),(525,84,'11-7-O4B-O4B-O4B-O4B-O4B-O4B-O4B-O4B-O4B-O4B-O4B-O4B-O4B-H2A-H2A-H2A-H2A-H2A-',40),(526,27,'Stack or zone',40),(527,28,'LC',40),(528,29,'',40),(529,30,'NC',40),(530,31,'MEDIUM',40),(531,35,'TP',40),(532,37,'DBL',40),(533,38,'2-1-3',40),(534,32,'',40),(535,33,'',40),(536,34,'',40),(537,36,'',40),(538,39,'',40),(539,40,'7 : 6',40),(540,41,'FM',40),(541,88,'',40),(542,86,'12-H61C-H3B-',41),(543,54,'SF',41),(544,55,'3',41),(545,59,'Favourable',41),(546,60,'CN',41),(547,56,'3',41),(548,57,'6',41),(549,58,'OH',41),(550,63,'7 : 7',41),(551,64,'FR',41),(552,85,'7-H3B-H3A-',42),(553,42,'RB',42),(554,43,'LOW',42),(555,44,'CN',42),(556,45,'3',42),(557,46,'Favourable',42),(558,49,'2C',42),(559,50,'DBL',42),(560,51,'K1',42),(561,89,'',42),(562,47,'3',42),(563,48,'3',42),(564,52,'U',42),(565,53,'7 : 7',42),(566,83,'11-H3B-O2C-',43),(567,11,'IN',43),(568,12,'2C',43),(569,14,'LOW',43),(570,17,'K1',43),(571,19,'SGL',43),(572,20,'2-1-3',43),(573,26,'Stack or zone',43),(574,94,'C',43),(575,13,'BT',43),(576,15,'3',43),(577,16,'2',43),(578,18,'MB',43),(579,21,'2',43),(580,22,'S',43),(581,23,'7 : 7',43),(582,24,'FR',43),(583,25,'C',43),(584,91,'',43),(585,92,'',43),(586,93,'',43),(587,82,'7-H12A-O6D-',45),(588,1,'SF',45),(589,6,'3',45),(590,2,'',45),(591,3,'C',45),(592,4,'1',45),(593,5,'6',45),(594,7,'OH',45),(595,8,'8 : 7',45),(596,9,'SC',45),(597,10,'FR',45),(598,84,'11-O3C-H3A-H3A-H51D-',46),(599,27,'Stack or zone',46),(600,28,'Soft',46),(601,29,'OT',46),(602,30,'3C',46),(603,31,'LOW',46),(604,35,'K2',46),(605,37,'SGL',46),(606,38,'Non Organised',46),(607,32,'3',46),(608,33,'3',46),(609,34,'3',46),(610,36,'U',46),(611,39,'H',46),(612,40,'8 : 7',46),(613,41,'FR',46),(614,88,'',46),(615,86,'12-H5B-H3D-',47),(616,54,'SF',47),(617,55,'3',47),(618,59,'Semi Favourable',47),(619,60,'AN',47),(620,56,'3',47),(621,57,'5',47),(622,58,'OH',47),(623,63,'8 : 8',47),(624,64,'RR',47),(625,85,'7-H3D-H3A-',48),(626,42,'R',48),(627,43,'MEDIUM',48),(628,44,'AN',48),(629,45,'3',48),(630,46,'Semi Favourable',48),(631,49,'3C',48),(632,50,'SGL',48),(633,51,'K1',48),(634,89,'',48),(635,47,'3',48),(636,48,'3',48),(637,52,'MB',48),(638,53,'8 : 8',48),(639,83,'11-H3A-O3D-',49),(640,11,'BT',49),(641,12,'3C',49),(642,14,'MEDIUM',49),(643,17,'K1',49),(644,19,'SGL',49),(645,20,'1-2-3',49),(646,26,'Commit',49),(647,94,'D',49),(648,13,'BT',49),(649,15,'3',49),(650,16,'3',49),(651,18,'MB',49),(652,21,'3',49),(653,22,'MB',49),(654,23,'8 : 8',49),(655,24,'RR',49),(656,25,'C',49),(657,91,'',49),(658,92,'',49),(659,93,'',49),(660,87,'4-',50),(661,65,'',50),(662,66,'',50),(663,67,'',50),(664,69,'',50),(665,70,'',50),(666,71,'',50),(667,75,'',50),(668,76,'',50),(669,77,'',50),(670,90,'',50),(671,95,'',50),(672,68,'',50),(673,72,'',50),(674,73,'',50),(675,74,'',50),(676,78,'RR',50),(677,80,'8 : 8',50),(678,81,'',50),(679,85,'7-H3D-H2A-',51),(680,42,'R',51),(681,43,'MEDIUM',51),(682,44,'',51),(683,45,'',51),(684,46,'Favourable',51),(685,49,'2C',51),(686,50,'DBL',51),(687,51,'TP',51),(688,89,'',51),(689,47,'3',51),(690,48,'2',51),(691,52,'U',51),(692,53,'8 : 8',51),(693,83,'4-H2A-O3C-',52),(694,11,'IN',52),(695,12,'2C',52),(696,14,'MEDIUM',52),(697,17,'TPS',52),(698,19,'DBL',52),(699,20,'2-1-3',52),(700,26,'RR',52),(701,94,'S',52),(702,13,'BT',52),(703,15,'2',52),(704,16,'3',52),(705,18,'OH',52),(706,21,'3',52),(707,22,'MB',52),(708,23,'8 : 8',52),(709,24,'RR',52),(710,25,'C',52),(711,91,'',52),(712,92,'',52),(713,93,'',52),(714,86,'1-H6C-HR1C-',53),(715,54,'SF',53),(716,55,'3',53),(717,59,'Non Favourable',53),(718,60,'',53),(719,56,'R',53),(720,57,'6',53),(721,58,'L',53),(722,63,'8 : 9',53),(723,64,'RR',53),(724,86,'12-H5A-H3D-',54),(725,54,'SF',54),(726,55,'3',54),(727,59,'Semi Favourable',54),(728,60,'AN',54),(729,56,'3',54),(730,57,'5',54),(731,58,'OH',54),(732,63,'8 : 10',54),(733,64,'RR',54),(734,85,'7-H3D-H4B-',55),(735,42,'R',55),(736,43,'MEDIUM',55),(737,44,'AN',55),(738,45,'3',55),(739,46,'Favourable',55),(740,49,'2C',55),(741,50,'DBL',55),(742,51,'K1',55),(743,89,'',55),(744,47,'3',55),(745,48,'4',55),(746,52,'U',55),(747,53,'8 : 10',55),(748,83,'3-H4B-O2D-',56),(749,11,'R',56),(750,12,'2C',56),(751,14,'MEDIUM',56),(752,17,'K1',56),(753,19,'DBL',56),(754,20,'2-1-3',56),(755,26,'Stack or zone',56),(756,94,'S',56),(757,13,'NSL',56),(758,15,'4',56),(759,16,'2',56),(760,18,'U',56),(761,21,'2',56),(762,22,'U',56),(763,23,'8 : 10',56),(764,24,'RR',56),(765,25,'C',56),(766,91,'',56),(767,92,'',56),(768,93,'',56),(769,86,'4-H61B-H2A-',57),(770,54,'SF',57),(771,55,'3',57),(772,59,'Favourable',57),(773,60,'CN',57),(774,56,'2',57),(775,57,'6',57),(776,58,'OH',57),(777,63,'8 : 11',57),(778,64,'RR',57),(779,85,'7-H2A-H2C-',58),(780,42,'R',58),(781,43,'MEDIUM',58),(782,44,'CN',58),(783,45,'3',58),(784,46,'Favourable',58),(785,49,'2C',58),(786,50,'NB',58),(787,51,'K1',58),(788,89,'Non Organised',58),(789,47,'2',58),(790,48,'2',58),(791,52,'U',58),(792,53,'8 : 11',58),(793,83,'4-H2C-O61B-',59),(794,11,'IN',59),(795,12,'2C',59),(796,14,'MEDIUM',59),(797,17,'K1',59),(798,19,'NB',59),(799,20,'2-1-3',59),(800,26,'',59),(801,94,'S',59),(802,13,'',59),(803,15,'2',59),(804,16,'6',59),(805,18,'OH',59),(806,21,'6',59),(807,22,'MB',59),(808,23,'8 : 11',59),(809,24,'RR',59),(810,25,'C',59),(811,91,'',59),(812,92,'',59),(813,93,'',59),(814,84,'11-O4C-H3B-H3B-H61A-',60),(815,27,'RR',60),(816,28,'Kill',60),(817,29,'OT',60),(818,30,'2C',60),(819,31,'MEDIUM',60),(820,35,'K2',60),(821,37,'DBL',60),(822,38,'2-0-4',60),(823,32,'4',60),(824,33,'3',60),(825,34,'3',60),(826,36,'MB',60),(827,39,'H',60),(828,40,'8 : 11',60),(829,41,'RR',60),(830,88,'',60),(831,87,'12-H61A-H11C-',61),(832,65,'OT',61),(833,66,'MEDIUM',61),(834,67,'2C',61),(835,69,'DBL',61),(836,70,'C',61),(837,71,'2-0-4',61),(838,75,'AN',61),(839,76,'Semi Favourable',61),(840,77,'TP',61),(841,90,'HB',61),(842,95,'',61),(843,68,'6',61),(844,72,'6',61),(845,73,'1',61),(846,74,'S',61),(847,78,'RR',61),(848,80,'8 : 11',61),(849,81,'',61),(850,85,'7-H11C-H61B-',62),(851,42,'R',62),(852,43,'MEDIUM',62),(853,44,'AN',62),(854,45,'',62),(855,46,'Semi Favourable',62),(856,49,'nC',62),(857,50,'NB',62),(858,51,'TP',62),(859,89,'Non Organised',62),(860,47,'1',62),(861,48,'6',62),(862,52,'MB',62),(863,53,'8 : 11',62),(864,83,'12-H61B-O62A-',63),(865,11,'R',63),(866,12,'NC',63),(867,14,'MEDIUM',63),(868,17,'TPS',63),(869,19,'NB',63),(870,20,'Non Organised',63),(871,26,'',63),(872,94,'S',63),(873,13,'',63),(874,15,'6',63),(875,16,'BOC',63),(876,18,'OH',63),(877,21,'BOC',63),(878,22,'MB',63),(879,23,'8 : 11',63),(880,24,'RR',63),(881,25,'C',63),(882,91,'',63),(883,92,'',63),(884,93,'',63),(885,86,'1-H61D-H3B-',64),(886,54,'SF',64),(887,55,'3',64),(888,59,'Favourable',64),(889,60,'CN',64),(890,56,'3',64),(891,57,'6',64),(892,58,'L',64),(893,63,'8 : 12',64),(894,64,'RR',64),(895,85,'7-H3B-H3A-',65),(896,42,'RB',65),(897,43,'LOW',65),(898,44,'ON',65),(899,45,'3',65),(900,46,'Favourable',65),(901,49,'3C',65),(902,50,'DBL',65),(903,51,'K1',65),(904,89,'',65),(905,47,'3',65),(906,48,'3',65),(907,52,'MB',65),(908,53,'8 : 12',65),(909,83,'11-H3A-O11D-',66),(910,11,'IN',66),(911,12,'3C',66),(912,14,'LOW',66),(913,17,'K1',66),(914,19,'DBL',66),(915,20,'2-1-3',66),(916,26,'Commit',66),(917,94,'D',66),(918,13,'NSL',66),(919,15,'3',66),(920,16,'1',66),(921,18,'MB',66),(922,21,'1',66),(923,22,'S',66),(924,23,'8 : 12',66),(925,24,'RR',66),(926,25,'C',66),(927,91,'',66),(928,92,'',66),(929,93,'',66),(930,82,'7-H12A-O51A-',69),(931,1,'SF',69),(932,6,'3',69),(933,2,'',69),(934,3,'C',69),(935,4,'1',69),(936,5,'5',69),(937,7,'OH',69),(938,8,'1 : 0',69),(939,9,'RC',69),(940,10,'RR',69),(941,87,'7-O2B-H11D-',70),(942,65,'IN',70),(943,66,'MEDIUM',70),(944,67,'3C',70),(945,69,'DBL',70),(946,70,'C',70),(947,71,'1-2-3',70),(948,75,'',70),(949,76,'',70),(950,77,'K2',70),(951,90,'OA',70),(952,95,'',70),(953,68,'2',70),(954,72,'2',70),(955,73,'1',70),(956,74,'S',70),(957,78,'RR',70),(958,80,'1 : 0',70),(959,81,'',70),(960,86,'1-H1A-H1A-H1A-',71),(961,54,'JS',71),(962,55,'3',71),(963,59,'Favourable',71),(964,60,'ON',71),(965,56,'',71),(966,57,'',71),(967,58,'L',71),(968,63,'1 : 1',71),(969,64,'RR',71),(970,85,'7-H2A-H3A-',72),(971,42,'RB',72),(972,43,'LOW',72),(973,44,'ON',72),(974,45,'3',72),(975,46,'Favourable',72),(976,49,'3C',72),(977,50,'SGL',72),(978,51,'K1',72),(979,89,'Non Organised',72),(980,47,'2',72),(981,48,'3',72),(982,52,'U',72),(983,53,'1 : 1',72),(984,83,'8-H3A-O61D-',73),(985,11,'IN',73),(986,12,'3C',73),(987,14,'LOW',73),(988,17,'K1',73),(989,19,'SGL',73),(990,20,'2-1-3',73),(991,26,'RR',73),(992,94,'S',73),(993,13,'',73),(994,15,'3',73),(995,16,'6',73),(996,18,'MB',73),(997,21,'6',73),(998,22,'S',73),(999,23,'1 : 1',73),(1000,24,'RR',73),(1001,25,'C',73),(1002,91,'',73),(1003,92,'',73),(1004,93,'',73),(1005,82,'16-H62A-O61C-',74),(1006,1,'JS',74),(1007,6,'3',74),(1008,2,'',74),(1009,3,'C',74),(1010,4,'6',74),(1011,5,'6',74),(1012,7,'S',74),(1013,8,'2 : 1',74),(1014,9,'RC',74),(1015,10,'RM',74),(1016,84,'8-O4C-H2B-H2B-H6B-',75),(1017,27,'RR',75),(1018,28,'Soft',75),(1019,29,'OT',75),(1020,30,'2C',75),(1021,31,'MEDIUM',75),(1022,35,'K1',75),(1023,37,'DBL',75),(1024,38,'2-1-3',75),(1025,32,'4',75),(1026,33,'2',75),(1027,34,'2',75),(1028,36,'MB',75),(1029,39,'H',75),(1030,40,'2 : 1',75),(1031,41,'RM',75),(1032,88,'',75),(1033,87,'16-H6B-H6A-',76),(1034,65,'OT',76),(1035,66,'MEDIUM',76),(1036,67,'2C',76),(1037,69,'DBL',76),(1038,70,'C',76),(1039,71,'1-2-3',76),(1040,75,'',76),(1041,76,'',76),(1042,77,'K2',76),(1043,90,'HB',76),(1044,95,'',76),(1045,68,'6',76),(1046,72,'6',76),(1047,73,'6',76),(1048,74,'S',76),(1049,78,'RM',76),(1050,80,'2 : 1',76),(1051,81,'',76),(1052,86,'16-H11C-H61A-',77),(1053,54,'JF',77),(1054,55,'3',77),(1055,59,'Semi Favourable',77),(1056,60,'AN',77),(1057,56,'6',77),(1058,57,'1',77),(1059,58,'U',77),(1060,63,'2 : 2',77),(1061,64,'RM',77),(1062,85,'8-H61A-H4B-',78),(1063,42,'R',78),(1064,43,'MEDIUM',78),(1065,44,'',78),(1066,45,'3',78),(1067,46,'Semi Favourable',78),(1068,49,'nC',78),(1069,50,'DBL',78),(1070,51,'K1',78),(1071,89,'Non Organised',78),(1072,47,'6',78),(1073,48,'4',78),(1074,52,'MB',78),(1075,53,'2 : 2',78),(1076,83,'18-H4B-O51D-',79),(1077,11,'OT',79),(1078,12,'NC',79),(1079,14,'MEDIUM',79),(1080,17,'K1',79),(1081,19,'DBL',79),(1082,20,'2-1-3',79),(1083,26,'Stack or zone',79),(1084,94,'C',79),(1085,13,'',79),(1086,15,'4',79),(1087,16,'5',79),(1088,18,'OH',79),(1089,21,'5',79),(1090,22,'S',79),(1091,23,'2 : 2',79),(1092,24,'RM',79),(1093,25,'C',79),(1094,91,'',79),(1095,92,'',79),(1096,93,'',79),(1097,84,'18-O2D-H4B-H4B-H11B-',80),(1098,27,'RR',80),(1099,28,'Kill',80),(1100,29,'IN',80),(1101,30,'NC',80),(1102,31,'MEDIUM',80),(1103,35,'TP',80),(1104,37,'DBL',80),(1105,38,'2-1-3',80),(1106,32,'2',80),(1107,33,'4',80),(1108,34,'4',80),(1109,36,'MB',80),(1110,39,'H',80),(1111,40,'2 : 2',80),(1112,41,'RL',80),(1113,88,'',80),(1114,87,'7-H11B-H2A-',81),(1115,65,'IN',81),(1116,66,'MEDIUM',81),(1117,67,'NC',81),(1118,69,'DBL',81),(1119,70,'C',81),(1120,71,'2-1-3',81),(1121,75,'',81),(1122,76,'Favourable',81),(1123,77,'TP',81),(1124,90,'HB',81),(1125,95,'',81),(1126,68,'1',81),(1127,72,'1',81),(1128,73,'2',81),(1129,74,'U',81),(1130,78,'RM',81),(1131,80,'2 : 2',81),(1132,81,'',81),(1133,85,'6-H2A-H4D-',82),(1134,42,'R',82),(1135,43,'HIGH',82),(1136,44,'ON',82),(1137,45,'',82),(1138,46,'Favourable',82),(1139,49,'2C',82),(1140,50,'DBL',82),(1141,51,'TP',82),(1142,89,'1-3-2',82),(1143,47,'2',82),(1144,48,'4',82),(1145,52,'MB',82),(1146,53,'2 : 2',82),(1147,83,'18-H2A-O62D-',83),(1148,11,'OT',83),(1149,12,'2C',83),(1150,14,'HIGH',83),(1151,17,'TPS',83),(1152,19,'DBL',83),(1153,20,'2-1-3',83),(1154,26,'Stack or zone',83),(1155,94,'C',83),(1156,13,'',83),(1157,15,'2',83),(1158,16,'BOC',83),(1159,18,'OH',83),(1160,21,'BOC',83),(1161,22,'OH',83),(1162,23,'2 : 2',83),(1163,24,'RM',83),(1164,25,'C',83),(1165,91,'',83),(1166,92,'',83),(1167,93,'',83),(1168,86,'18-H51A-H3B-',84),(1169,54,'JF',84),(1170,55,'3',84),(1171,59,'Favourable',84),(1172,60,'ON',84),(1173,56,'3',84),(1174,57,'5',84),(1175,58,'OH',84),(1176,63,'2 : 3',84),(1177,64,'RM',84),(1178,85,'7-H3B-H4A-',85),(1179,42,'HP',85),(1180,43,'MEDIUM',85),(1181,44,'ON',85),(1182,45,'3',85),(1183,46,'Favourable',85),(1184,49,'3C',85),(1185,50,'DBL',85),(1186,51,'K1',85),(1187,89,'1-2-3',85),(1188,47,'3',85),(1189,48,'4',85),(1190,52,'MB',85),(1191,53,'2 : 3',85),(1192,83,'18-H4A-H3D-',86),(1193,11,'OT',86),(1194,12,'3C',86),(1195,14,'MEDIUM',86),(1196,17,'K1',86),(1197,19,'DBL',86),(1198,20,'2-1-3',86),(1199,26,'RR',86),(1200,94,'C',86),(1201,13,'',86),(1202,15,'4',86),(1203,16,'3',86),(1204,18,'OH',86),(1205,21,'3',86),(1206,22,'OH',86),(1207,23,'2 : 3',86),(1208,24,'RM',86),(1209,25,'C',86),(1210,91,'',86),(1211,92,'',86),(1212,93,'',86),(1213,82,'8-H52B-O6D-',87),(1214,1,'SF',87),(1215,6,'3',87),(1216,2,'',87),(1217,3,'C',87),(1218,4,'5',87),(1219,5,'6',87),(1220,7,'OH',87),(1221,8,'3 : 3',87),(1222,9,'RC',87),(1223,10,'RL',87),(1224,84,'6-O4C-H2B-H2B-O4B-',88),(1225,27,'Stack or zone',88),(1226,28,'Kill',88),(1227,29,'D',88),(1228,30,'3C',88),(1229,31,'HIGH',88),(1230,35,'K1',88),(1231,37,'DBL',88),(1232,38,'2-1-3',88),(1233,32,'4',88),(1234,33,'2',88),(1235,34,'2',88),(1236,36,'U',88),(1237,39,'H',88),(1238,40,'3 : 3',88),(1239,41,'RL',88),(1240,88,'',88),(1241,84,'5-O3C-H3B-H3B-H51B-',89),(1242,27,'Commit',89),(1243,28,'CB',89),(1244,29,'OT',89),(1245,30,'2C',89),(1246,31,'LOW',89),(1247,35,'TP',89),(1248,37,'SGL',89),(1249,38,'1-2-3',89),(1250,32,'3',89),(1251,33,'3',89),(1252,34,'3',89),(1253,36,'OH',89),(1254,39,'H',89),(1255,40,'3 : 3',89),(1256,41,'RL',89),(1257,88,'',89),(1258,87,'8-H51B-H3A-',90),(1259,65,'OT',90),(1260,66,'LOW',90),(1261,67,'2C',90),(1262,69,'SGL',90),(1263,70,'C',90),(1264,71,'Non Organised',90),(1265,75,'ON',90),(1266,76,'Favourable',90),(1267,77,'TP',90),(1268,90,'HB',90),(1269,95,'',90),(1270,68,'5',90),(1271,72,'5',90),(1272,73,'3',90),(1273,74,'OH',90),(1274,78,'RL',90),(1275,80,'3 : 3',90),(1276,81,'',90),(1277,85,'7-H3A-H2B-',91),(1278,42,'R',91),(1279,43,'MEDIUM',91),(1280,44,'ON',91),(1281,45,'',91),(1282,46,'Favourable',91),(1283,49,'2C',91),(1284,50,'SGL',91),(1285,51,'TP',91),(1286,89,'1-2-3',91),(1287,47,'3',91),(1288,48,'2',91),(1289,52,'U',91),(1290,53,'3 : 3',91),(1291,86,'16-H6B-H3C-',93),(1292,54,'SF',93),(1293,55,'3',93),(1294,59,'Favourable',93),(1295,60,'AN',93),(1296,56,'3',93),(1297,57,'6',93),(1298,58,'U',93),(1299,63,'3 : 4',93),(1300,64,'RL',93),(1301,85,'7-H3B-H3A-',94),(1302,42,'RB',94),(1303,43,'LOW',94),(1304,44,'ON',94),(1305,45,'3',94),(1306,46,'Favourable',94),(1307,49,'3C',94),(1308,50,'SGL',94),(1309,51,'K1',94),(1310,89,'1-3-2',94),(1311,47,'3',94),(1312,48,'3',94),(1313,52,'MB',94),(1314,53,'3 : 4',94),(1315,83,'5-H3A-O3B-',95),(1316,11,'D',95),(1317,12,'3C',95),(1318,14,'LOW',95),(1319,17,'K1',95),(1320,19,'SGL',95),(1321,20,'1-3-2',95),(1322,26,'Commit',95),(1323,94,'S',95),(1324,13,'',95),(1325,15,'3',95),(1326,16,'3',95),(1327,18,'MB',95),(1328,21,'3',95),(1329,22,'MB',95),(1330,23,'3 : 4',95),(1331,24,'RL',95),(1332,25,'C',95),(1333,91,'',95),(1334,92,'',95),(1335,93,'',95),(1336,84,'6-O4C-H2B-H2B-HR2B-',96),(1337,27,'Stack or zone',96),(1338,28,'LC',96),(1339,29,'IN',96),(1340,30,'2C',96),(1341,31,'HIGH',96),(1342,35,'K2',96),(1343,37,'DBL',96),(1344,38,'2-0-4',96),(1345,32,'4',96),(1346,33,'2',96),(1347,34,'2',96),(1348,36,'OH',96),(1349,39,'H',96),(1350,40,'3 : 4',96),(1351,41,'FL',96),(1352,88,'',96),(1353,86,'18-H51C-H51B-',97),(1354,54,'SF',97),(1355,55,'3',97),(1356,59,'Semi Favourable',97),(1357,60,'AN',97),(1358,56,'5',97),(1359,57,'5',97),(1360,58,'OH',97),(1361,63,'3 : 5',97),(1362,64,'RL',97),(1363,85,'7-H51B-H2B-',98),(1364,42,'R',98),(1365,43,'MEDIUM',98),(1366,44,'AN',98),(1367,45,'3',98),(1368,46,'Semi Favourable',98),(1369,49,'3C',98),(1370,50,'SGL',98),(1371,51,'K1',98),(1372,89,'Non Organised',98),(1373,47,'5',98),(1374,48,'2',98),(1375,52,'OH',98),(1376,53,'3 : 5',98),(1377,83,'6-H2B-O4B-',99),(1378,11,'OT',99),(1379,12,'3C',99),(1380,14,'MEDIUM',99),(1381,17,'K1',99),(1382,19,'SGL',99),(1383,20,'Non Organised',99),(1384,26,'Commit',99),(1385,94,'C',99),(1386,13,'DC',99),(1387,15,'2',99),(1388,16,'4',99),(1389,18,'U',99),(1390,21,'4',99),(1391,22,'S',99),(1392,23,'3 : 5',99),(1393,24,'RL',99),(1394,25,'C',99),(1395,91,'',99),(1396,92,'',99),(1397,93,'',99),(1398,82,'6-H52B-O5B-',100),(1399,1,'JP',100),(1400,6,'3',100),(1401,2,'DC',100),(1402,3,'C',100),(1403,4,'5',100),(1404,5,'5',100),(1405,7,'OH',100),(1406,8,'4 : 5',100),(1407,9,'RC',100),(1408,10,'FL',100),(1409,86,'16-H51B-H3B-',101),(1410,54,'SF',101),(1411,55,'3',101),(1412,59,'Favourable',101),(1413,60,'ON',101),(1414,56,'3',101),(1415,57,'5',101),(1416,58,'U',101),(1417,63,'4 : 6',101),(1418,64,'FL',101),(1419,85,'7-H3B-H2A-',102),(1420,42,'HP',102),(1421,43,'LOW',102),(1422,44,'ON',102),(1423,45,'3',102),(1424,46,'Favourable',102),(1425,49,'3C',102),(1426,50,'DBL',102),(1427,51,'K1',102),(1428,89,'Non Organised',102),(1429,47,'3',102),(1430,48,'2',102),(1431,52,'MB',102),(1432,53,'4 : 6',102),(1433,83,'5-H2A-O11B-',103),(1434,11,'BT',103),(1435,12,'3C',103),(1436,14,'LOW',103),(1437,17,'K1',103),(1438,19,'DBL',103),(1439,20,'',103),(1440,26,'RR',103),(1441,94,'D',103),(1442,13,'',103),(1443,15,'2',103),(1444,16,'1',103),(1445,18,'MB',103),(1446,21,'1',103),(1447,22,'OH',103),(1448,23,'4 : 6',103),(1449,24,'FL',103),(1450,25,'C',103),(1451,91,'',103),(1452,92,'',103),(1453,93,'',103),(1454,82,'18-H62B-O1A-',104),(1455,1,'JP',104),(1456,6,'3',104),(1457,2,'DC',104),(1458,3,'C',104),(1459,4,'6',104),(1460,5,'1',104),(1461,7,'OH',104),(1462,8,'5 : 6',104),(1463,9,'RC',104),(1464,10,'FM',104),(1465,82,'18-H62B-O2A-',105),(1466,1,'JP',105),(1467,6,'3',105),(1468,2,'DC',105),(1469,3,'C',105),(1470,4,'6',105),(1471,5,'2',105),(1472,7,'MB',105),(1473,8,'6 : 6',105),(1474,9,'SC',105),(1475,10,'FM',105),(1476,86,'1-H61D-H3A-',106),(1477,54,'SF',106),(1478,55,'3',106),(1479,59,'Favourable',106),(1480,60,'ON',106),(1481,56,'3',106),(1482,57,'6',106),(1483,58,'L',106),(1484,63,'6 : 7',106),(1485,64,'FM',106),(1486,83,'7-H3A-O2C-',107),(1487,11,'D',107),(1488,12,'3C',107),(1489,14,'MEDIUM',107),(1490,17,'K1',107),(1491,19,'NB',107),(1492,20,'Non Organised',107),(1493,26,'',107),(1494,94,'C',107),(1495,13,'BT',107),(1496,15,'3',107),(1497,16,'2',107),(1498,18,'S',107),(1499,21,'2',107),(1500,22,'S',107),(1501,23,'6 : 7',107),(1502,24,'FM',107),(1503,25,'C',107),(1504,91,'',107),(1505,92,'',107),(1506,93,'',107),(1507,87,'18-H51C-H61A-',108),(1508,65,'D',108),(1509,66,'MEDIUM',108),(1510,67,'NC',108),(1511,69,'SGL',108),(1512,70,'S',108),(1513,71,'Non Organised',108),(1514,75,'AN',108),(1515,76,'Semi Favourable',108),(1516,77,'TP',108),(1517,90,'OA',108),(1518,95,'',108),(1519,68,'5',108),(1520,72,'5',108),(1521,73,'6',108),(1522,74,'OH',108),(1523,78,'FM',108),(1524,80,'6 : 7',108),(1525,81,'',108),(1526,85,'7-H61A-H4A-',109),(1527,42,'R',109),(1528,43,'MEDIUM',109),(1529,44,'AN',109),(1530,45,'3',109),(1531,46,'Semi Favourable',109),(1532,49,'nC',109),(1533,50,'DBL',109),(1534,51,'TP',109),(1535,89,'1-3-2',109),(1536,47,'6',109),(1537,48,'4',109),(1538,52,'MB',109),(1539,53,'6 : 7',109),(1540,83,'16-H4A-O2C-',110),(1541,11,'OT',110),(1542,12,'3C',110),(1543,14,'MEDIUM',110),(1544,17,'TPS',110),(1545,19,'DBL',110),(1546,20,'2-1-3',110),(1547,26,'Stack or zone',110),(1548,94,'C',110),(1549,13,'BT',110),(1550,15,'4',110),(1551,16,'2',110),(1552,18,'U',110),(1553,21,'2',110),(1554,22,'S',110),(1555,23,'6 : 7',110),(1556,24,'FM',110),(1557,25,'C',110),(1558,91,'',110),(1559,92,'',110),(1560,93,'',110),(1561,82,'5-H52B-O61D-',112),(1562,1,'SF',112),(1563,6,'3',112),(1564,2,'',112),(1565,3,'C',112),(1566,4,'5',112),(1567,5,'6',112),(1568,7,'OH',112),(1569,8,'7 : 7',112),(1570,9,'RC',112),(1571,10,'FR',112),(1572,87,'7-H2A-H3A-',113),(1573,65,'IN',113),(1574,66,'MEDIUM',113),(1575,67,'2C',113),(1576,69,'DBL',113),(1577,70,'S',113),(1578,71,'1-3-2',113),(1579,75,'CN',113),(1580,76,'Favourable',113),(1581,77,'K2',113),(1582,90,'OA',113),(1583,95,'',113),(1584,68,'2',113),(1585,72,'2',113),(1586,73,'3',113),(1587,74,'OH',113),(1588,78,'FR',113),(1589,80,'7 : 7',113),(1590,81,'',113),(1591,85,'8-H3A-H61A-',114),(1592,42,'BC',114),(1593,43,'MEDIUM',114),(1594,44,'CN',114),(1595,45,'',114),(1596,46,'Favourable',114),(1597,49,'1C',114),(1598,50,'NB',114),(1599,51,'K2',114),(1600,89,'2-1-3',114),(1601,47,'3',114),(1602,48,'6',114),(1603,52,'OH',114),(1604,53,'7 : 7',114),(1605,83,'18-H61B-H3A-',115),(1606,11,'IN',115),(1607,12,'1C',115),(1608,14,'MEDIUM',115),(1609,17,'K2',115),(1610,19,'NB',115),(1611,20,'Non Organised',115),(1612,26,'',115),(1613,94,'S',115),(1614,13,'',115),(1615,15,'6',115),(1616,16,'3',115),(1617,18,'OH',115),(1618,21,'3',115),(1619,22,'OH',115),(1620,23,'7 : 7',115),(1621,24,'FR',115),(1622,25,'C',115),(1623,91,'',115),(1624,92,'',115),(1625,93,'',115),(1626,86,'18-H61C-H2D-',116),(1627,54,'JF',116),(1628,55,'3',116),(1629,59,'Favourable',116),(1630,60,'CN',116),(1631,56,'2',116),(1632,57,'6',116),(1633,58,'OH',116),(1634,63,'7 : 8',116),(1635,64,'FR',116),(1636,85,'7-H2D-H3A-',117),(1637,42,'HP',117),(1638,43,'LOW',117),(1639,44,'CN',117),(1640,45,'3',117),(1641,46,'Favourable',117),(1642,49,'3C',117),(1643,50,'SGL',117),(1644,51,'K1',117),(1645,89,'1-3-2',117),(1646,47,'2',117),(1647,48,'3',117),(1648,52,'MB',117),(1649,53,'7 : 8',117),(1650,83,'8-H3A-O2A-',118),(1651,11,'OT',118),(1652,12,'3C',118),(1653,14,'LOW',118),(1654,17,'K1',118),(1655,19,'SGL',118),(1656,20,'1-3-2',118),(1657,26,'Commit',118),(1658,94,'S',118),(1659,13,'DC',118),(1660,15,'3',118),(1661,16,'2',118),(1662,18,'MB',118),(1663,21,'2',118),(1664,22,'OH',118),(1665,23,'7 : 8',118),(1666,24,'FR',118),(1667,25,'C',118),(1668,91,'',118),(1669,92,'',118),(1670,93,'',118),(1671,82,'7-H12B-O51B-',119),(1672,1,'SF',119),(1673,6,'2',119),(1674,2,'',119),(1675,3,'C',119),(1676,4,'1',119),(1677,5,'5',119),(1678,7,'OH',119),(1679,8,'8 : 8',119),(1680,9,'RC',119),(1681,10,'RR',119),(1682,84,'8-O3C-H3B-H3B-H2A-',120),(1683,27,'Commit',120),(1684,28,'Kill',120),(1685,29,'BT',120),(1686,30,'3C',120),(1687,31,'LOW',120),(1688,35,'K2',120),(1689,37,'SGL',120),(1690,38,'1-3-2',120),(1691,32,'3',120),(1692,33,'3',120),(1693,34,'3',120),(1694,36,'MB',120),(1695,39,'H',120),(1696,40,'8 : 8',120),(1697,41,'RR',120),(1698,88,'',120),(1699,87,'6-H2A-O2B-',121),(1700,65,'BT',121),(1701,66,'LOW',121),(1702,67,'3C',121),(1703,69,'SGL',121),(1704,70,'C',121),(1705,71,'1-3-2',121),(1706,75,'',121),(1707,76,'',121),(1708,77,'TP',121),(1709,90,'HB',121),(1710,95,'',121),(1711,68,'2',121),(1712,72,'2',121),(1713,73,'2',121),(1714,74,'OH',121),(1715,78,'RR',121),(1716,80,'8 : 8',121),(1717,81,'',121),(1718,84,'8-O2C-H2B-H2B-O2C-',122),(1719,27,'RR',122),(1720,28,'Kill',122),(1721,29,'IN',122),(1722,30,'NC',122),(1723,31,'MEDIUM',122),(1724,35,'TP',122),(1725,37,'DBL',122),(1726,38,'2-1-3',122),(1727,32,'2',122),(1728,33,'2',122),(1729,34,'2',122),(1730,36,'OH',122),(1731,39,'H',122),(1732,40,'8 : 8',122),(1733,41,'RR',122),(1734,88,'',122),(1735,82,'7-H12C-O61D-',123),(1736,1,'SF',123),(1737,6,'3',123),(1738,2,'',123),(1739,3,'C',123),(1740,4,'1',123),(1741,5,'6',123),(1742,7,'MB',123),(1743,8,'9 : 8',123),(1744,9,'SC',123),(1745,10,'RR',123),(1746,82,'7-H12C-O51D-',124),(1747,1,'SF',124),(1748,6,'3',124),(1749,2,'',124),(1750,3,'C',124),(1751,4,'1',124),(1752,5,'5',124),(1753,7,'OH',124),(1754,8,'10 : 8',124),(1755,9,'SC',124),(1756,10,'RR',124),(1757,82,'7-H12C-O11C-',125),(1758,1,'SF',125),(1759,6,'3',125),(1760,2,'',125),(1761,3,'C',125),(1762,4,'1',125),(1763,5,'1',125),(1764,7,'S',125),(1765,8,'11 : 8',125),(1766,9,'SC',125),(1767,10,'RR',125),(1768,83,'16-H4A-O61A-',126),(1769,11,'OT',126),(1770,12,'2C',126),(1771,14,'HIGH',126),(1772,17,'K2',126),(1773,19,'DBL',126),(1774,20,'2-0-4',126),(1775,26,'Stack or zone',126),(1776,94,'C',126),(1777,13,'',126),(1778,15,'4',126),(1779,16,'6',126),(1780,18,'U',126),(1781,21,'6',126),(1782,22,'MB',126),(1783,23,'11 : 8',126),(1784,24,'RR',126),(1785,25,'C',126),(1786,91,'',126),(1787,92,'',126),(1788,93,'',126),(1789,87,'7-H61C-H3A-',41),(1790,65,'IN',41),(1791,66,'MEDIUM',41),(1792,67,'1C',41),(1793,69,'SGL',41),(1794,70,'S',41),(1795,71,'Non Organised',41),(1796,75,'ON',41),(1797,76,'Favourable',41),(1798,77,'K2',41),(1799,90,'OA',41),(1800,95,'',41),(1801,68,'6',41),(1802,72,'6',41),(1803,73,'3',41),(1804,74,'MB',41),(1805,78,'RR',41),(1806,80,'12 : 8',41),(1807,81,'',41),(1808,85,'6-H3A-H4A-',41),(1809,42,'R',41),(1810,43,'HIGH',41),(1811,44,'ON',41),(1812,45,'',41),(1813,46,'Favourable',41),(1814,49,'2C',41),(1815,50,'DBL',41),(1816,51,'K2',41),(1817,89,'2-0-4',41),(1818,47,'3',41),(1819,48,'4',41),(1820,52,'U',41),(1821,53,'12 : 8',41),(1822,82,'7-H12C-O61B-',130),(1823,1,'SF',130),(1824,6,'3',130),(1825,2,'',130),(1826,3,'C',130),(1827,4,'1',130),(1828,5,'6',130),(1829,7,'MB',130),(1830,8,'12 : 8',130),(1831,9,'SC',130),(1832,10,'RR',130),(1833,85,'6-H11B-H61B-',131),(1834,42,'R',131),(1835,43,'HIGH',131),(1836,44,'AN',131),(1837,45,'',131),(1838,46,'Non Favourable',131),(1839,49,'1C',131),(1840,50,'NB',131),(1841,51,'TP',131),(1842,89,'2-0-4',131),(1843,47,'1',131),(1844,48,'6',131),(1845,52,'MB',131),(1846,53,'12 : 8',131),(1847,87,'7-H11A-H11B-',132),(1848,65,'IN',132),(1849,66,'MEDIUM',132),(1850,67,'3C',132),(1851,69,'DBL',132),(1852,70,'S',132),(1853,71,'Non Organised',132),(1854,75,'AN',132),(1855,76,'Non Favourable',132),(1856,77,'K2',132),(1857,90,'OA',132),(1858,95,'',132),(1859,68,'1',132),(1860,72,'1',132),(1861,73,'1',132),(1862,74,'S',132),(1863,78,'RR',132),(1864,80,'12 : 8',132),(1865,81,'',132),(1866,83,'18-H61B-O6A-',133),(1867,11,'IN',133),(1868,12,'NC',133),(1869,14,'HIGH',133),(1870,17,'TPS',133),(1871,19,'NB',133),(1872,20,'Non Organised',133),(1873,26,'',133),(1874,94,'S',133),(1875,13,'NEL',133),(1876,15,'6',133),(1877,16,'6',133),(1878,18,'OH',133),(1879,21,'6',133),(1880,22,'MB',133),(1881,23,'12 : 8',133),(1882,24,'RR',133),(1883,25,'C',133),(1884,91,'',133),(1885,92,'',133),(1886,93,'',133),(1887,82,'7-H12C-O1C-',134),(1888,1,'SF',134),(1889,6,'3',134),(1890,2,'',134),(1891,3,'C',134),(1892,4,'1',134),(1893,5,'1',134),(1894,7,'S',134),(1895,8,'13 : 8',134),(1896,9,'SC',134),(1897,10,'RR',134),(1898,87,'7-H11B-H2C-',135),(1899,65,'D',135),(1900,66,'LOW',135),(1901,67,'3C',135),(1902,69,'NB',135),(1903,70,'',135),(1904,71,'Non Organised',135),(1905,75,'',135),(1906,76,'',135),(1907,77,'K2',135),(1908,90,'OA',135),(1909,95,'',135),(1910,68,'1',135),(1911,72,'1',135),(1912,73,'2',135),(1913,74,'OH',135),(1914,78,'RR',135),(1915,80,'13 : 8',135),(1916,81,'',135),(1917,86,'1-H61C-H3A-',136),(1918,54,'JP',136),(1919,55,'3',136),(1920,59,'Favourable',136),(1921,60,'ON',136),(1922,56,'3',136),(1923,57,'6',136),(1924,58,'L',136),(1925,63,'13 : 9',136),(1926,64,'RR',136),(1927,85,'7-H3A-H4A-',137),(1928,42,'JS',137),(1929,43,'MEDIUM',137),(1930,44,'ON',137),(1931,45,'3',137),(1932,46,'Favourable',137),(1933,49,'3C',137),(1934,50,'SGL',137),(1935,51,'K1',137),(1936,89,'Non Organised',137),(1937,47,'3',137),(1938,48,'4',137),(1939,52,'OH',137),(1940,53,'13 : 9',137),(1941,83,'6-H4A-H61C-',138),(1942,11,'OT',138),(1943,12,'NC',138),(1944,14,'MEDIUM',138),(1945,17,'K1',138),(1946,19,'SGL',138),(1947,20,'1-3-2',138),(1948,26,'RR',138),(1949,94,'C',138),(1950,13,'',138),(1951,15,'4',138),(1952,16,'6',138),(1953,18,'U',138),(1954,21,'6',138),(1955,22,'S',138),(1956,23,'13 : 9',138),(1957,24,'RR',138),(1958,25,'C',138),(1959,91,'',138),(1960,92,'',138),(1961,93,'',138),(1962,82,'16-H62B-O5B-',139),(1963,1,'JP',139),(1964,6,'3',139),(1965,2,'DC',139),(1966,3,'C',139),(1967,4,'6',139),(1968,5,'5',139),(1969,7,'MB',139),(1970,8,'14 : 9',139),(1971,9,'RC',139),(1972,10,'RM',139),(1973,82,'8-H52C-O61B-',141),(1974,1,'SF',141),(1975,6,'3',141),(1976,2,'',141),(1977,3,'C',141),(1978,4,'5',141),(1979,5,'6',141),(1980,7,'OH',141),(1981,8,'15 : 10',141),(1982,9,'RC',141),(1983,10,'RL',141),(1984,87,'7-H11C-H3C-',142),(1985,65,'BC',142),(1986,66,'MEDIUM',142),(1987,67,'1C',142),(1988,69,'NB',142),(1989,70,'',142),(1990,71,'Non Organised',142),(1991,75,'AN',142),(1992,76,'Semi Favourable',142),(1993,77,'K2',142),(1994,90,'OA',142),(1995,95,'',142),(1996,68,'1',142),(1997,72,'1',142),(1998,73,'3',142),(1999,74,'OH',142),(2000,78,'RL',142),(2001,80,'15 : 10',142),(2002,81,'',142),(2003,85,'6-H3A-H2A-',143),(2004,42,'R',143),(2005,43,'HIGH',143),(2006,44,'AN',143),(2007,45,'',143),(2008,46,'Semi Favourable',143),(2009,49,'1C',143),(2010,50,'DBL',143),(2011,51,'K1',143),(2012,89,'Non Organised',143),(2013,47,'3',143),(2014,48,'2',143),(2015,52,'U',143),(2016,53,'15 : 10',143),(2017,83,'18-H4A-H2B-',144),(2018,11,'OT',144),(2019,12,'1C',144),(2020,14,'HIGH',144),(2021,17,'K1',144),(2022,19,'DBL',144),(2023,20,'2-1-3',144),(2024,26,'RR',144),(2025,94,'C',144),(2026,13,'',144),(2027,15,'4',144),(2028,16,'2',144),(2029,18,'OH',144),(2030,21,'2',144),(2031,22,'U',144),(2032,23,'15 : 10',144),(2033,24,'RL',144),(2034,25,'C',144),(2035,91,'',144),(2036,92,'',144),(2037,93,'',144),(2038,86,'18-H51C-O4D-',145),(2039,54,'SF',145),(2040,55,'3',145),(2041,59,'Non Favourable',145),(2042,60,'CN',145),(2043,56,'4',145),(2044,57,'5',145),(2045,58,'OH',145),(2046,63,'15 : 11',145),(2047,64,'RL',145),(2048,86,'1-H11D-H2D-',146),(2049,54,'SF',146),(2050,55,'3',146),(2051,59,'Semi Favourable',146),(2052,60,'AN',146),(2053,56,'2',146),(2054,57,'1',146),(2055,58,'L',146),(2056,63,'15 : 12',146),(2057,64,'RL',146),(2058,85,'7-H2D-H3A-',147),(2059,42,'RB',147),(2060,43,'LOW',147),(2061,44,'AN',147),(2062,45,'3',147),(2063,46,'Semi Favourable',147),(2064,49,'3C',147),(2065,50,'SGL',147),(2066,51,'K1',147),(2067,89,'Non Organised',147),(2068,47,'2',147),(2069,48,'3',147),(2070,52,'MB',147),(2071,53,'15 : 12',147),(2072,86,'5-H51A-H4C-',149),(2073,54,'SF',149),(2074,55,'3',149),(2075,59,'',149),(2076,60,'',149),(2077,56,'4',149),(2078,57,'5',149),(2079,58,'MB',149),(2080,63,'15 : 13',149),(2081,64,'RL',149),(2082,86,'1-H61B-H3B-',150),(2083,54,'SF',150),(2084,55,'3',150),(2085,59,'Favourable',150),(2086,60,'ON',150),(2087,56,'3',150),(2088,57,'6',150),(2089,58,'L',150),(2090,63,'15 : 14',150),(2091,64,'RL',150),(2092,85,'7-H3B-H3B-',151),(2093,42,'R',151),(2094,43,'HIGH',151),(2095,44,'ON',151),(2096,45,'3',151),(2097,46,'Favourable',151),(2098,49,'3C',151),(2099,50,'DBL',151),(2100,51,'K1',151),(2101,89,'Non Organised',151),(2102,47,'3',151),(2103,48,'3',151),(2104,52,'MB',151),(2105,53,'15 : 14',151),(2106,83,'6-H2B-O3A-',152),(2107,11,'IN',152),(2108,12,'3C',152),(2109,14,'MEDIUM',152),(2110,17,'K1',152),(2111,19,'DBL',152),(2112,20,'2-1-3',152),(2113,26,'RR',152),(2114,94,'C',152),(2115,13,'',152),(2116,15,'2',152),(2117,16,'3',152),(2118,18,'U',152),(2119,21,'3',152),(2120,22,'MB',152),(2121,23,'15 : 14',152),(2122,24,'RL',152),(2123,25,'C',152),(2124,91,'',152),(2125,92,'',152),(2126,93,'',152),(2127,86,'16-H51C-H2A-',154),(2128,54,'SF',154),(2129,55,'3',154),(2130,59,'Favourable',154),(2131,60,'',154),(2132,56,'2',154),(2133,57,'5',154),(2134,58,'U',154),(2135,63,'15 : 15',154),(2136,64,'RL',154),(2137,85,'7-H2A-H3A-',155),(2138,42,'JS',155),(2139,43,'LOW',155),(2140,44,'ON',155),(2141,45,'3',155),(2142,46,'Favourable',155),(2143,49,'3C',155),(2144,50,'SGL',155),(2145,51,'K1',155),(2146,89,'Non Organised',155),(2147,47,'2',155),(2148,48,'3',155),(2149,52,'MB',155),(2150,53,'15 : 15',155),(2151,83,'5-H3A-O51D-',156),(2152,11,'OT',156),(2153,12,'3C',156),(2154,14,'LOW',156),(2155,17,'K1',156),(2156,19,'SGL',156),(2157,20,'1-3-2',156),(2158,26,'Commit',156),(2159,94,'D',156),(2160,13,'',156),(2161,15,'3',156),(2162,16,'5',156),(2163,18,'MB',156),(2164,21,'5',156),(2165,22,'OH',156),(2166,23,'15 : 15',156),(2167,24,'RL',156),(2168,25,'C',156),(2169,91,'',156),(2170,92,'',156),(2171,93,'',156),(2172,82,'6-H52B-O62C-',157),(2173,1,'JP',157),(2174,6,'3',157),(2175,2,'',157),(2176,3,'C',157),(2177,4,'5',157),(2178,5,'BOC',157),(2179,7,'MB',157),(2180,8,'16 : 15',157),(2181,9,'RC',157),(2182,10,'FL',157),(2183,86,'16-H51B-H3C-',158),(2184,54,'SF',158),(2185,55,'3',158),(2186,59,'Semi Favourable',158),(2187,60,'AN',158),(2188,56,'3',158),(2189,57,'5',158),(2190,58,'U',158),(2191,63,'16 : 16',158),(2192,64,'FL',158),(2193,85,'7-H3B-H2B-',159),(2194,42,'HP',159),(2195,43,'MEDIUM',159),(2196,44,'AN',159),(2197,45,'3',159),(2198,46,'Semi Favourable',159),(2199,49,'2C',159),(2200,50,'SGL',159),(2201,51,'K1',159),(2202,89,'Non Organised',159),(2203,47,'3',159),(2204,48,'2',159),(2205,52,'MB',159),(2206,53,'16 : 16',159),(2207,83,'10-H2B-O11D-',160),(2208,11,'IN',160),(2209,12,'2C',160),(2210,14,'MEDIUM',160),(2211,17,'K1',160),(2212,19,'SGL',160),(2213,20,'2-1-3',160),(2214,26,'Commit',160),(2215,94,'C',160),(2216,13,'NSL',160),(2217,15,'2',160),(2218,16,'1',160),(2219,18,'OH',160),(2220,21,'1',160),(2221,22,'OH',160),(2222,23,'16 : 16',160),(2223,24,'FL',160),(2224,25,'C',160),(2225,91,'',160),(2226,92,'',160),(2227,93,'',160),(2228,82,'10-H62B-O51A-',161),(2229,1,'JF',161),(2230,6,'3',161),(2231,2,'',161),(2232,3,'C',161),(2233,4,'6',161),(2234,5,'5',161),(2235,7,'MB',161),(2236,8,'17 : 16',161),(2237,9,'RC',161),(2238,10,'FM',161),(2239,87,'10-H6B-H2A-',162),(2240,65,'IN',162),(2241,66,'MEDIUM',162),(2242,67,'2C',162),(2243,69,'DBL',162),(2244,70,'C',162),(2245,71,'1-3-2',162),(2246,75,'CN',162),(2247,76,'Favourable',162),(2248,77,'K2',162),(2249,90,'OA',162),(2250,95,'',162),(2251,68,'6',162),(2252,72,'6',162),(2253,73,'2',162),(2254,74,'MB',162),(2255,78,'FM',162),(2256,80,'17 : 16',162),(2257,81,'',162),(2258,85,'7-H2A-H11B-',163),(2259,42,'BC',163),(2260,43,'MEDIUM',163),(2261,44,'CN',163),(2262,45,'3',163),(2263,46,'Semi Favourable',163),(2264,49,'3C',163),(2265,50,'NB',163),(2266,51,'K1',163),(2267,89,'Non Organised',163),(2268,47,'2',163),(2269,48,'1',163),(2270,52,'OH',163),(2271,53,'17 : 16',163),(2272,83,'6-H11B-O4A-',164),(2273,11,'BC',164),(2274,12,'2C',164),(2275,14,'MEDIUM',164),(2276,17,'K1',164),(2277,19,'NB',164),(2278,20,'Non Organised',164),(2279,26,'',164),(2280,94,'S',164),(2281,13,'DC',164),(2282,15,'1',164),(2283,16,'4',164),(2284,18,'U',164),(2285,21,'4',164),(2286,22,'OH',164),(2287,23,'17 : 16',164),(2288,24,'FM',164),(2289,25,'C',164),(2290,91,'',164),(2291,92,'',164),(2292,93,'',164),(2293,87,'10-H6B-',165),(2294,65,'BC',165),(2295,66,'MEDIUM',165),(2296,67,'1C',165),(2297,69,'TPL',165),(2298,70,'S',165),(2299,71,'Non Organised',165),(2300,75,'',165),(2301,76,'',165),(2302,77,'TP',165),(2303,90,'OA',165),(2304,95,'',165),(2305,68,'',165),(2306,72,'',165),(2307,73,'',165),(2308,74,'',165),(2309,78,'FM',165),(2310,80,'17 : 16',165),(2311,81,'',165),(2312,86,'1-H51B-H3A-',166),(2313,54,'JF',166),(2314,55,'3',166),(2315,59,'Semi Favourable',166),(2316,60,'ON',166),(2317,56,'3',166),(2318,57,'5',166),(2319,58,'L',166),(2320,63,'17 : 17',166),(2321,64,'FM',166),(2322,83,'7-H3A-H2B-',167),(2323,11,'D',167),(2324,12,'NC',167),(2325,14,'MEDIUM',167),(2326,17,'K1',167),(2327,19,'SGL',167),(2328,20,'1-3-2',167),(2329,26,'RR',167),(2330,94,'D',167),(2331,13,'',167),(2332,15,'3',167),(2333,16,'2',167),(2334,18,'S',167),(2335,21,'2',167),(2336,22,'S',167),(2337,23,'17 : 17',167),(2338,24,'FM',167),(2339,25,'C',167),(2340,91,'',167),(2341,92,'',167),(2342,93,'',167),(2343,82,'5-H52B-O51A-',168),(2344,1,'SF',168),(2345,6,'3',168),(2346,2,'',168),(2347,3,'C',168),(2348,4,'5',168),(2349,5,'5',168),(2350,7,'U',168),(2351,8,'18 : 17',168),(2352,9,'RC',168),(2353,10,'FR',168),(2354,84,'7-O2C-H2B-H2B-H51B-',169),(2355,27,'Stack or zone',169),(2356,28,'LI',169),(2357,29,'OT',169),(2358,30,'2C',169),(2359,31,'MEDIUM',169),(2360,35,'K2',169),(2361,37,'DBL',169),(2362,38,'2-0-4',169),(2363,32,'2',169),(2364,33,'2',169),(2365,34,'2',169),(2366,36,'S',169),(2367,39,'H',169),(2368,40,'18 : 17',169),(2369,41,'FR',169),(2370,88,'',169),(2371,87,'5-H51B-H2A-',170),(2372,65,'OT',170),(2373,66,'MEDIUM',170),(2374,67,'2C',170),(2375,69,'DBL',170),(2376,70,'LC',170),(2377,71,'1-3-2',170),(2378,75,'LT',170),(2379,76,'Semi Favourable',170),(2380,77,'K2',170),(2381,90,'HB',170),(2382,95,'',170),(2383,68,'5',170),(2384,72,'5',170),(2385,73,'2',170),(2386,74,'S',170),(2387,78,'FR',170),(2388,80,'18 : 17',170),(2389,81,'',170),(2390,85,'8-H2A-H61B-',171),(2391,42,'BC',171),(2392,43,'MEDIUM',171),(2393,44,'LT',171),(2394,45,'',171),(2395,46,'Semi Favourable',171),(2396,49,'1C',171),(2397,50,'',171),(2398,51,'K2',171),(2399,89,'2-0-4',171),(2400,47,'2',171),(2401,48,'6',171),(2402,52,'OH',171),(2403,53,'18 : 17',171),(2404,84,'8-O3B-H3A-H3A-O3A-',172),(2405,27,'Commit',172),(2406,28,'Kill',172),(2407,29,'IN',172),(2408,30,'3C',172),(2409,31,'LOW',172),(2410,35,'TP',172),(2411,37,'DBL',172),(2412,38,'2-1-3',172),(2413,32,'3',172),(2414,33,'3',172),(2415,34,'3',172),(2416,36,'OH',172),(2417,39,'H',172),(2418,40,'18 : 17',172),(2419,41,'FR',172),(2420,88,'',172),(2421,82,'5-H52C-O11B-',173),(2422,1,'SF',173),(2423,6,'3',173),(2424,2,'',173),(2425,3,'C',173),(2426,4,'5',173),(2427,5,'1',173),(2428,7,'MB',173),(2429,8,'19 : 17',173),(2430,9,'SC',173),(2431,10,'FR',173),(2432,87,'5-H4C-O2A-',174),(2433,65,'D',174),(2434,66,'MEDIUM',174),(2435,67,'NC',174),(2436,69,'NB',174),(2437,70,'',174),(2438,71,'Non Organised',174),(2439,75,'',174),(2440,76,'',174),(2441,77,'K2',174),(2442,90,'OA',174),(2443,95,'',174),(2444,68,'4',174),(2445,72,'4',174),(2446,73,'2',174),(2447,74,'S',174),(2448,78,'FR',174),(2449,80,'19 : 17',174),(2450,81,'',174),(2451,82,'5-H52C-H61D-',176),(2452,1,'SF',176),(2453,6,'3',176),(2454,2,'',176),(2455,3,'C',176),(2456,4,'5',176),(2457,5,'6',176),(2458,7,'OH',176),(2459,8,'20 : 17',176),(2460,9,'SC',176),(2461,10,'FR',176),(2462,82,'5-H52C-O61B-',177),(2463,1,'SF',177),(2464,6,'3',177),(2465,2,'',177),(2466,3,'C',177),(2467,4,'5',177),(2468,5,'6',177),(2469,7,'OH',177),(2470,8,'21 : 17',177),(2471,9,'SC',177),(2472,10,'FR',177),(2473,84,'7-O2C-H2B-H2B-H2B-',178),(2474,27,'Stack or zone',178),(2475,28,'CB',178),(2476,29,'OT',178),(2477,30,'2C',178),(2478,31,'MEDIUM',178),(2479,35,'K2',178),(2480,37,'DBL',178),(2481,38,'2-0-4',178),(2482,32,'2',178),(2483,33,'2',178),(2484,34,'2',178),(2485,36,'S',178),(2486,39,'H',178),(2487,40,'21 : 17',178),(2488,41,'FR',178),(2489,88,'',178),(2490,87,'7-H2B-H2A-',179),(2491,65,'OT',179),(2492,66,'MEDIUM',179),(2493,67,'2C',179),(2494,69,'DBL',179),(2495,70,'C',179),(2496,71,'Non Organised',179),(2497,75,'CN',179),(2498,76,'Favourable',179),(2499,77,'K2',179),(2500,90,'HD',179),(2501,95,'',179),(2502,68,'2',179),(2503,72,'2',179),(2504,73,'2',179),(2505,74,'S',179),(2506,78,'FR',179),(2507,80,'21 : 17',179),(2508,81,'',179),(2509,85,'8-H3A-H4A-',180),(2510,42,'R',180),(2511,43,'HIGH',180),(2512,44,'CN',180),(2513,45,'',180),(2514,46,'Favourable',180),(2515,49,'1C',180),(2516,50,'DBL',180),(2517,51,'K2',180),(2518,89,'Non Organised',180),(2519,47,'3',180),(2520,48,'4',180),(2521,52,'MB',180),(2522,53,'21 : 17',180),(2523,83,'16-H4A-O6A-',181),(2524,11,'OT',181),(2525,12,'1C',181),(2526,14,'HIGH',181),(2527,17,'K2',181),(2528,19,'DBL',181),(2529,20,'2-1-3',181),(2530,26,'Stack or zone',181),(2531,94,'C',181),(2532,13,'NEL',181),(2533,15,'4',181),(2534,16,'6',181),(2535,18,'U',181),(2536,21,'6',181),(2537,22,'OH',181),(2538,23,'21 : 17',181),(2539,24,'FR',181),(2540,25,'C',181),(2541,91,'',181),(2542,92,'',181),(2543,93,'',181),(2544,87,'6-H11D-H2B-',182),(2545,65,'OT',182),(2546,66,'MEDIUM',182),(2547,67,'2C',182),(2548,69,'NB',182),(2549,70,'',182),(2550,71,'Non Organised',182),(2551,75,'ON',182),(2552,76,'Favourable',182),(2553,77,'TP',182),(2554,90,'OA',182),(2555,95,'',182),(2556,68,'1',182),(2557,72,'1',182),(2558,73,'2',182),(2559,74,'S',182),(2560,78,'FR',182),(2561,80,'21 : 17',182),(2562,81,'',182),(2563,83,'7-H2A-O11C-',183),(2564,11,'IN',183),(2565,12,'2C',183),(2566,14,'MEDIUM',183),(2567,17,'TPS',183),(2568,19,'SGL',183),(2569,20,'2-0-4',183),(2570,26,'Stack or zone',183),(2571,94,'C',183),(2572,13,'',183),(2573,15,'2',183),(2574,16,'1',183),(2575,18,'S',183),(2576,21,'1',183),(2577,22,'MB',183),(2578,23,'21 : 17',183),(2579,24,'FR',183),(2580,25,'C',183),(2581,91,'',183),(2582,92,'',183),(2583,93,'',183),(2584,82,'5-H52C-O61A-',184),(2585,1,'SF',184),(2586,6,'3',184),(2587,2,'',184),(2588,3,'C',184),(2589,4,'5',184),(2590,5,'6',184),(2591,7,'OH',184),(2592,8,'22 : 17',184),(2593,9,'SC',184),(2594,10,'FR',184),(2595,82,'5-H52C-O61B-',185),(2596,1,'SF',185),(2597,6,'3',185),(2598,2,'',185),(2599,3,'C',185),(2600,4,'5',185),(2601,5,'6',185),(2602,7,'OH',185),(2603,8,'23 : 17',185),(2604,9,'SC',185),(2605,10,'FR',185),(2606,84,'16-O3D-H3A-H3A-H2B-',186),(2607,27,'RR',186),(2608,28,'LI',186),(2609,29,'D',186),(2610,30,'NC',186),(2611,31,'MEDIUM',186),(2612,35,'K2',186),(2613,37,'DBL',186),(2614,38,'2-0-4',186),(2615,32,'3',186),(2616,33,'3',186),(2617,34,'3',186),(2618,36,'OH',186),(2619,39,'H',186),(2620,40,'23 : 17',186),(2621,41,'FR',186),(2622,88,'',186),(2623,86,'10-H61C-H2A-',187),(2624,54,'JF',187),(2625,55,'3',187),(2626,59,'Semi Favourable',187),(2627,60,'CN',187),(2628,56,'2',187),(2629,57,'6',187),(2630,58,'OH',187),(2631,63,'23 : 18',187),(2632,64,'FR',187),(2633,85,'7-H2A-H3B-',188),(2634,42,'RB',188),(2635,43,'LOW',188),(2636,44,'CN',188),(2637,45,'3',188),(2638,46,'Semi Favourable',188),(2639,49,'3C',188),(2640,50,'DBL',188),(2641,51,'K1',188),(2642,89,'Non Organised',188),(2643,47,'2',188),(2644,48,'3',188),(2645,52,'MB',188),(2646,53,'23 : 18',188),(2647,83,'8-H3A-O11D-',189),(2648,11,'IN',189),(2649,12,'3C',189),(2650,14,'LOW',189),(2651,17,'K1',189),(2652,19,'DBL',189),(2653,20,'3-0-3',189),(2654,26,'RR',189),(2655,94,'D',189),(2656,13,'NSL',189),(2657,15,'3',189),(2658,16,'1',189),(2659,18,'MB',189),(2660,21,'1',189),(2661,22,'S',189),(2662,23,'23 : 18',189),(2663,24,'FR',189),(2664,25,'C',189),(2665,91,'',189),(2666,92,'',189),(2667,93,'',189),(2668,82,'7-H12A-O51D-',190),(2669,1,'SF',190),(2670,6,'3',190),(2671,2,'',190),(2672,3,'C',190),(2673,4,'1',190),(2674,5,'5',190),(2675,7,'OH',190),(2676,8,'24 : 18',190),(2677,9,'RC',190),(2678,10,'RR',190),(2679,87,'10-H61C-H3B-',191),(2680,65,'BC',191),(2681,66,'MEDIUM',191),(2682,67,'1C',191),(2683,69,'NB',191),(2684,70,'',191),(2685,71,'Non Organised',191),(2686,75,'ON',191),(2687,76,'Favourable',191),(2688,77,'K2',191),(2689,90,'OA',191),(2690,95,'',191),(2691,68,'6',191),(2692,72,'6',191),(2693,73,'3',191),(2694,74,'MB',191),(2695,78,'RR',191),(2696,80,'24 : 18',191),(2697,81,'',191),(2698,85,'7-H3B-H2B-',192),(2699,42,'JS',192),(2700,43,'MEDIUM',192),(2701,44,'ON',192),(2702,45,'3',192),(2703,46,'Favourable',192),(2704,49,'3C',192),(2705,50,'NB',192),(2706,51,'K2',192),(2707,89,'',192),(2708,47,'3',192),(2709,48,'2',192),(2710,52,'OH',192),(2711,53,'24 : 18',192),(2712,83,'6-H2B-O61D-',193),(2713,11,'IN',193),(2714,12,'3C',193),(2715,14,'MEDIUM',193),(2716,17,'K2',193),(2717,19,'NB',193),(2718,20,'Non Organised',193),(2719,26,'',193),(2720,94,'C',193),(2721,13,'',193),(2722,15,'2',193),(2723,16,'6',193),(2724,18,'U',193),(2725,21,'6',193),(2726,22,'MB',193),(2727,23,'24 : 18',193),(2728,24,'RR',193),(2729,25,'C',193),(2730,91,'',193),(2731,92,'',193),(2732,93,'',193),(2733,86,'1-H1A-H3A-',194),(2734,54,'SF',194),(2735,55,'3',194),(2736,59,'Favourable',194),(2737,60,'ON',194),(2738,56,'3',194),(2739,57,'1',194),(2740,58,'L',194),(2741,63,'8 : 13',194),(2742,64,'RR',194),(2743,85,'7-H3A-H4B-',195),(2744,42,'JS',195),(2745,43,'LOW',195),(2746,44,'ON',195),(2747,45,'3',195),(2748,46,'Favourable',195),(2749,49,'3C',195),(2750,50,'SGL',195),(2751,51,'K1',195),(2752,89,'1-3-2',195),(2753,47,'3',195),(2754,48,'4',195),(2755,52,'U',195),(2756,53,'8 : 13',195),(2757,83,'11-O4B-O11D-',196),(2758,11,'IN',196),(2759,12,'3C',196),(2760,14,'LOW',196),(2761,17,'K1',196),(2762,19,'SGL',196),(2763,20,'1-3-2',196),(2764,26,'Commit',196),(2765,94,'S',196),(2766,13,'NSL',196),(2767,15,'4',196),(2768,16,'1',196),(2769,18,'MB',196),(2770,21,'1',196),(2771,22,'S',196),(2772,23,'8 : 13',196),(2773,24,'RR',196),(2774,25,'C',196),(2775,91,'',196),(2776,92,'',196),(2777,93,'',196),(2778,82,'4-H62B-O6C-',197),(2779,1,'JP',197),(2780,6,'3',197),(2781,2,'',197),(2782,3,'C',197),(2783,4,'6',197),(2784,5,'6',197),(2785,7,'MB',197),(2786,8,'9 : 13',197),(2787,9,'SC',197),(2788,10,'RR',197),(2789,82,'11-H62A-H3A-',200),(2790,1,'JF',200),(2791,6,'3',200),(2792,2,'',200),(2793,3,'C',200),(2794,4,'6',200),(2795,5,'3',200),(2796,7,'U',200),(2797,8,'10 : 14',200),(2798,9,'SC',200),(2799,10,'RM',200),(2800,86,'4-H5B-H2B-',201),(2801,54,'SF',201),(2802,55,'3',201),(2803,59,'Non Favourable',201),(2804,60,'AN',201),(2805,56,'2',201),(2806,57,'5',201),(2807,58,'OH',201),(2808,63,'10 : 15',201),(2809,64,'RL',201),(2810,87,'12-H2B-H61A-',202),(2811,65,'',202),(2812,66,'',202),(2813,67,'',202),(2814,69,'',202),(2815,70,'',202),(2816,71,'',202),(2817,75,'AN',202),(2818,76,'Favourable',202),(2819,77,'K1',202),(2820,90,'HD',202),(2821,95,'',202),(2822,68,'2',202),(2823,72,'2',202),(2824,73,'6',202),(2825,74,'U',202),(2826,78,'RL',202),(2827,80,'10 : 15',202),(2828,81,'',202),(2829,83,'4-H61A-O11D-',203),(2830,11,'R',203),(2831,12,'NC',203),(2832,14,'HIGH',203),(2833,17,'K1',203),(2834,19,'NB',203),(2835,20,'Non Organised',203),(2836,26,'',203),(2837,94,'S',203),(2838,13,'NSL',203),(2839,15,'6',203),(2840,16,'1',203),(2841,18,'OH',203),(2842,21,'1',203),(2843,22,'MB',203),(2844,23,'10 : 15',203),(2845,24,'RL',203),(2846,25,'C',203),(2847,91,'',203),(2848,92,'',203),(2849,93,'',203),(2850,82,'3-H1A-O51D-',205),(2851,1,'SF',205),(2852,6,'3',205),(2853,2,'',205),(2854,3,'C',205),(2855,4,'1',205),(2856,5,'5',205),(2857,7,'S',205),(2858,8,'11 : 15',205),(2859,9,'SC',205),(2860,10,'RL',205),(2861,83,'12-H2B-O11C-',206),(2862,11,'R',206),(2863,12,'NC',206),(2864,14,'MEDIUM',206),(2865,17,'K2',206),(2866,19,'NB',206),(2867,20,'Non Organised',206),(2868,26,'',206),(2869,94,'S',206),(2870,13,'',206),(2871,15,'2',206),(2872,16,'1',206),(2873,18,'OH',206),(2874,21,'1',206),(2875,22,'MB',206),(2876,23,'11 : 15',206),(2877,24,'FL',206),(2878,25,'C',206),(2879,91,'',206),(2880,92,'',206),(2881,93,'',206),(2882,82,'3-H12A-O11B-',207),(2883,1,'SF',207),(2884,6,'3',207),(2885,2,'',207),(2886,3,'C',207),(2887,4,'1',207),(2888,5,'1',207),(2889,7,'MB',207),(2890,8,'12 : 15',207),(2891,9,'RC',207),(2892,10,'RL',207),(2893,84,'8-O3D-H3A-H3A-O61D-',208),(2894,27,'RR',208),(2895,28,'Kill',208),(2896,29,'OT',208),(2897,30,'3C',208),(2898,31,'LOW',208),(2899,35,'K2',208),(2900,37,'SGL',208),(2901,38,'1-3-2',208),(2902,32,'3',208),(2903,33,'3',208),(2904,34,'3',208),(2905,36,'OH',208),(2906,39,'H',208),(2907,40,'12 : 15',208),(2908,41,'RL',208),(2909,88,'',208),(2910,82,'3-H12D-O51C-',209),(2911,1,'SF',209),(2912,6,'3',209),(2913,2,'',209),(2914,3,'C',209),(2915,4,'1',209),(2916,5,'5',209),(2917,7,'S',209),(2918,8,'13 : 15',209),(2919,9,'RC',209),(2920,10,'RL',209),(2921,82,'3-H12D-O61D-',210),(2922,1,'SF',210),(2923,6,'3',210),(2924,2,'',210),(2925,3,'C',210),(2926,4,'1',210),(2927,5,'6',210),(2928,7,'U',210),(2929,8,'14 : 15',210),(2930,9,'RC',210),(2931,10,'RL',210),(2932,84,'12-O2D-H4A-H4A-H3C-',211),(2933,27,'Stack or zone',211),(2934,28,'LC',211),(2935,29,'IN',211),(2936,30,'3C',211),(2937,31,'MEDIUM',211),(2938,35,'K2',211),(2939,37,'DBL',211),(2940,38,'2-1-3',211),(2941,32,'2',211),(2942,33,'4',211),(2943,34,'4',211),(2944,36,'MB',211),(2945,39,'H',211),(2946,40,'14 : 15',211),(2947,41,'RL',211),(2948,88,'',211),(2949,87,'7-H3C-H6C-',212),(2950,65,'IN',212),(2951,66,'MEDIUM',212),(2952,67,'3C',212),(2953,69,'DBL',212),(2954,70,'LC',212),(2955,71,'1-2-3',212),(2956,75,'AN',212),(2957,76,'Non Favourable',212),(2958,77,'K2',212),(2959,90,'HB',212),(2960,95,'',212),(2961,68,'3',212),(2962,72,'3',212),(2963,73,'6',212),(2964,74,'U',212),(2965,78,'FL',212),(2966,80,'14 : 15',212),(2967,81,'',212),(2968,87,'4-H6C-H4C-',213),(2969,65,'',213),(2970,66,'',213),(2971,67,'',213),(2972,69,'',213),(2973,70,'',213),(2974,71,'Non Organised',213),(2975,75,'AN',213),(2976,76,'Semi Favourable',213),(2977,77,'K1',213),(2978,90,'HD',213),(2979,95,'',213),(2980,68,'6',213),(2981,72,'6',213),(2982,73,'4',213),(2983,74,'MB',213),(2984,78,'FL',213),(2985,80,'14 : 15',213),(2986,81,'',213),(2987,87,'8-H4C-O11A-',214),(2988,65,'',214),(2989,66,'',214),(2990,67,'',214),(2991,69,'',214),(2992,70,'',214),(2993,71,'Non Organised',214),(2994,75,'',214),(2995,76,'Semi Favourable',214),(2996,77,'K1',214),(2997,90,'HD',214),(2998,95,'',214),(2999,68,'4',214),(3000,72,'4',214),(3001,73,'1',214),(3002,74,'MB',214),(3003,78,'FL',214),(3004,80,'14 : 15',214),(3005,81,'',214),(3006,82,'3-H12D-O51A-',216),(3007,1,'SF',216),(3008,6,'3',216),(3009,2,'',216),(3010,3,'C',216),(3011,4,'1',216),(3012,5,'5',216),(3013,7,'S',216),(3014,8,'15 : 15',216),(3015,9,'RC',216),(3016,10,'RL',216),(3017,87,'1-H4C-H3B-',217),(3018,65,'OT',217),(3019,66,'LOW',217),(3020,67,'3C',217),(3021,69,'SGL',217),(3022,70,'S',217),(3023,71,'1-3-2',217),(3024,75,'AN',217),(3025,76,'Non Favourable',217),(3026,77,'K2',217),(3027,90,'OA',217),(3028,95,'',217),(3029,68,'4',217),(3030,72,'4',217),(3031,73,'3',217),(3032,74,'OH',217),(3033,78,'FL',217),(3034,80,'15 : 15',217),(3035,81,'',217),(3036,82,'12-H52D-O51D-',219),(3037,1,'SF',219),(3038,6,'3',219),(3039,2,'',219),(3040,3,'C',219),(3041,4,'5',219),(3042,5,'5',219),(3043,7,'U',219),(3044,8,'16 : 16',219),(3045,9,'SC',219),(3046,10,'FL',219),(3047,84,'7-O4C-H2B-H2B-H11B-',220),(3048,27,'Stack or zone',220),(3049,28,'LO',220),(3050,29,'IN',220),(3051,30,'1C',220),(3052,31,'MEDIUM',220),(3053,35,'K2',220),(3054,37,'DBL',220),(3055,38,'2-1-3',220),(3056,32,'4',220),(3057,33,'2',220),(3058,34,'2',220),(3059,36,'OH',220),(3060,39,'H',220),(3061,40,'16 : 16',220),(3062,41,'FL',220),(3063,88,'',220),(3064,86,'4-H51D-H61B-',221),(3065,54,'SF',221),(3066,55,'3',221),(3067,59,'Semi Favourable',221),(3068,60,'AN',221),(3069,56,'6',221),(3070,57,'5',221),(3071,58,'OH',221),(3072,63,'16 : 17',221),(3073,64,'FM',221),(3074,85,'7-H61B-H4A-',222),(3075,42,'R',222),(3076,43,'MEDIUM',222),(3077,44,'AN',222),(3078,45,'3',222),(3079,46,'Semi Favourable',222),(3080,49,'2C',222),(3081,50,'',222),(3082,51,'K1',222),(3083,89,'1-3-2',222),(3084,47,'6',222),(3085,48,'4',222),(3086,52,'U',222),(3087,53,'16 : 17',222),(3088,83,'4-H4A-O2D-',223),(3089,11,'OT',223),(3090,12,'2C',223),(3091,14,'MEDIUM',223),(3092,17,'K1',223),(3093,19,'DBL',223),(3094,20,'2-1-3',223),(3095,26,'Stack or zone',223),(3096,94,'C',223),(3097,13,'NSL',223),(3098,15,'4',223),(3099,16,'2',223),(3100,18,'OH',223),(3101,21,'2',223),(3102,22,'MB',223),(3103,23,'16 : 17',223),(3104,24,'FM',223),(3105,25,'C',223),(3106,91,'',223),(3107,92,'',223),(3108,93,'',223),(3109,87,'4-H51B-H2D-',224),(3110,65,'D',224),(3111,66,'MEDIUM',224),(3112,67,'3C',224),(3113,69,'SGL',224),(3114,70,'S',224),(3115,71,'1-2-3',224),(3116,75,'CN',224),(3117,76,'Favourable',224),(3118,77,'TP',224),(3119,90,'OA',224),(3120,95,'',224),(3121,68,'5',224),(3122,72,'5',224),(3123,73,'2',224),(3124,74,'MB',224),(3125,78,'FM',224),(3126,80,'16 : 17',224),(3127,81,'',224),(3128,85,'7-H2D-H61B-',225),(3129,42,'BC',225),(3130,43,'MEDIUM',225),(3131,44,'CN',225),(3132,45,'',225),(3133,46,'Favourable',225),(3134,49,'3C',225),(3135,50,'SGL',225),(3136,51,'TP',225),(3137,89,'1-2-3',225),(3138,47,'2',225),(3139,48,'6',225),(3140,52,'U',225),(3141,53,'16 : 17',225),(3142,83,'12-H61B-O3C-',226),(3143,11,'IN',226),(3144,12,'1C',226),(3145,14,'MEDIUM',226),(3146,17,'TPS',226),(3147,19,'TPL',226),(3148,20,'3-0-3',226),(3149,26,'RR',226),(3150,94,'S',226),(3151,13,'BT',226),(3152,15,'6',226),(3153,16,'3',226),(3154,18,'OH',226),(3155,21,'3',226),(3156,22,'S',226),(3157,23,'16 : 17',226),(3158,24,'FM',226),(3159,25,'C',226),(3160,91,'',226),(3161,92,'',226),(3162,93,'',226),(3163,82,'8-H52C-O61C-',227),(3164,1,'SF',227),(3165,6,'3',227),(3166,2,'',227),(3167,3,'C',227),(3168,4,'5',227),(3169,5,'6',227),(3170,7,'U',227),(3171,8,'17 : 17',227),(3172,9,'SC',227),(3173,10,'FM',227),(3174,84,'11-O3C-H3B-H3B-H3B-',228),(3175,27,'Commit',228),(3176,28,'Kill',228),(3177,29,'D',228),(3178,30,'NC',228),(3179,31,'MEDIUM',228),(3180,35,'K2',228),(3181,37,'SGL',228),(3182,38,'1-3-2',228),(3183,32,'3',228),(3184,33,'3',228),(3185,34,'3',228),(3186,36,'S',228),(3187,39,'H',228),(3188,40,'17 : 17',228),(3189,41,'FM',228),(3190,88,'',228),(3191,87,'11-H3B-H11A-',229),(3192,65,'D',229),(3193,66,'MEDIUM',229),(3194,67,'NC',229),(3195,69,'SGL',229),(3196,70,'S',229),(3197,71,'1-3-2',229),(3198,75,'AN',229),(3199,76,'Non Favourable',229),(3200,77,'K2',229),(3201,90,'HB',229),(3202,95,'',229),(3203,68,'3',229),(3204,72,'3',229),(3205,73,'1',229),(3206,74,'OH',229),(3207,78,'FR',229),(3208,80,'17 : 17',229),(3209,81,'',229),(3210,86,'4-H51D-H61B-',230),(3211,54,'SF',230),(3212,55,'3',230),(3213,59,'Non Favourable',230),(3214,60,'AN',230),(3215,56,'6',230),(3216,57,'5',230),(3217,58,'OH',230),(3218,63,'17 : 18',230),(3219,64,'FR',230),(3220,85,'7-H61B-H4A-',231),(3221,42,'R',231),(3222,43,'MEDIUM',231),(3223,44,'AN',231),(3224,45,'3',231),(3225,46,'Semi Favourable',231),(3226,49,'2C',231),(3227,50,'DBL',231),(3228,51,'K1',231),(3229,89,'1-3-2',231),(3230,47,'6',231),(3231,48,'4',231),(3232,52,'MB',231),(3233,53,'17 : 18',231),(3234,83,'4-H4A-O51A-',232),(3235,11,'OT',232),(3236,12,'2C',232),(3237,14,'MEDIUM',232),(3238,17,'K1',232),(3239,19,'DBL',232),(3240,20,'2-1-3',232),(3241,26,'Stack or zone',232),(3242,94,'C',232),(3243,13,'',232),(3244,15,'4',232),(3245,16,'5',232),(3246,18,'OH',232),(3247,21,'5',232),(3248,22,'U',232),(3249,23,'17 : 18',232),(3250,24,'FR',232),(3251,25,'C',232),(3252,91,'',232),(3253,92,'',232),(3254,93,'',232),(3255,87,'12-H61C-H2C-',233),(3256,65,'BC',233),(3257,66,'MEDIUM',233),(3258,67,'NC',233),(3259,69,'NB',233),(3260,70,'',233),(3261,71,'Non Organised',233),(3262,75,'CN',233),(3263,76,'Favourable',233),(3264,77,'TP',233),(3265,90,'OA',233),(3266,95,'',233),(3267,68,'6',233),(3268,72,'6',233),(3269,73,'2',233),(3270,74,'S',233),(3271,78,'FR',233),(3272,80,'17 : 18',233),(3273,81,'',233),(3274,83,'11-H3A-H3B-',234),(3275,11,'IN',234),(3276,12,'2C',234),(3277,14,'LOW',234),(3278,17,'TPS',234),(3279,19,'DBL',234),(3280,20,'2-0-4',234),(3281,26,'RR',234),(3282,94,'S',234),(3283,13,'',234),(3284,15,'3',234),(3285,16,'3',234),(3286,18,'MB',234),(3287,21,'3',234),(3288,22,'U',234),(3289,23,'17 : 18',234),(3290,24,'FR',234),(3291,25,'C',234),(3292,91,'',234),(3293,92,'',234),(3294,93,'',234),(3295,86,'1-H61B-H3B-',235),(3296,54,'SF',235),(3297,55,'3',235),(3298,59,'Favourable',235),(3299,60,'CN',235),(3300,56,'3',235),(3301,57,'6',235),(3302,58,'L',235),(3303,63,'17 : 19',235),(3304,64,'FR',235),(3305,83,'7-H3B-O3B-',236),(3306,11,'D',236),(3307,12,'NC',236),(3308,14,'MEDIUM',236),(3309,17,'K1',236),(3310,19,'NB',236),(3311,20,'Non Organised',236),(3312,26,'',236),(3313,94,'',236),(3314,13,'',236),(3315,15,'3',236),(3316,16,'3',236),(3317,18,'S',236),(3318,21,'3',236),(3319,22,'U',236),(3320,23,'17 : 19',236),(3321,24,'FR',236),(3322,25,'C',236),(3323,91,'',236),(3324,92,'',236),(3325,93,'',236),(3326,87,'3-H2C-H2C-',237),(3327,65,'R',237),(3328,66,'ODB',237),(3329,67,'NC',237),(3330,69,'NB',237),(3331,70,'',237),(3332,71,'Non Organised',237),(3333,75,'',237),(3334,76,'',237),(3335,77,'TP',237),(3336,90,'',237),(3337,95,'',237),(3338,68,'2',237),(3339,72,'2',237),(3340,73,'2',237),(3341,74,'S',237),(3342,78,'FR',237),(3343,80,'17 : 19',237),(3344,81,'',237),(3345,86,'12-H61A-H2B-',238),(3346,54,'SF',238),(3347,55,'3',238),(3348,59,'Non Favourable',238),(3349,60,'AN',238),(3350,56,'2',238),(3351,57,'6',238),(3352,58,'OH',238),(3353,63,'17 : 20',238),(3354,64,'FR',238),(3355,86,'12-H61A-H3D-',239),(3356,54,'SF',239),(3357,55,'3',239),(3358,59,'Semi Favourable',239),(3359,60,'AN',239),(3360,56,'3',239),(3361,57,'6',239),(3362,58,'OH',239),(3363,63,'17 : 21',239),(3364,64,'FR',239),(3365,85,'7-H3D-H4A-',240),(3366,42,'R',240),(3367,43,'MEDIUM',240),(3368,44,'AN',240),(3369,45,'3',240),(3370,46,'Favourable',240),(3371,49,'',240),(3372,50,'DBL',240),(3373,51,'K1',240),(3374,89,'1-3-2',240),(3375,47,'3',240),(3376,48,'4',240),(3377,52,'MB',240),(3378,53,'17 : 21',240),(3379,83,'4-H4A-O2D-',241),(3380,11,'OT',241),(3381,12,'2C',241),(3382,14,'MEDIUM',241),(3383,17,'K1',241),(3384,19,'DBL',241),(3385,20,'2-1-3',241),(3386,26,'Stack or zone',241),(3387,94,'C',241),(3388,13,'NSL',241),(3389,15,'4',241),(3390,16,'2',241),(3391,18,'OH',241),(3392,21,'2',241),(3393,22,'S',241),(3394,23,'17 : 21',241),(3395,24,'FR',241),(3396,25,'C',241),(3397,91,'',241),(3398,92,'',241),(3399,93,'',241),(3400,84,'7-O4C-H2A-H2A-H6A-',242),(3401,27,'Stack or zone',242),(3402,28,'LI',242),(3403,29,'OT',242),(3404,30,'',242),(3405,31,'MEDIUM',242),(3406,35,'TP',242),(3407,37,'DBL',242),(3408,38,'2-0-4',242),(3409,32,'4',242),(3410,33,'2',242),(3411,34,'2',242),(3412,36,'S',242),(3413,39,'H',242),(3414,40,'17 : 21',242),(3415,41,'FR',242),(3416,88,'',242),(3417,87,'1-H6A-H1B-',243),(3418,65,'OT',243),(3419,66,'MEDIUM',243),(3420,67,'3C',243),(3421,69,'DBL',243),(3422,70,'LC',243),(3423,71,'2-0-4',243),(3424,75,'AN',243),(3425,76,'Non Favourable',243),(3426,77,'TP',243),(3427,90,'HB',243),(3428,95,'',243),(3429,68,'6',243),(3430,72,'6',243),(3431,73,'1',243),(3432,74,'MB',243),(3433,78,'FR',243),(3434,80,'17 : 21',243),(3435,81,'',243),(3436,87,'9-H1B-H61B-',244),(3437,65,'',244),(3438,66,'',244),(3439,67,'',244),(3440,69,'',244),(3441,70,'',244),(3442,71,'',244),(3443,75,'AN',244),(3444,76,'Semi Favourable',244),(3445,77,'TP',244),(3446,90,'HD',244),(3447,95,'',244),(3448,68,'1',244),(3449,72,'1',244),(3450,73,'6',244),(3451,74,'OH',244),(3452,78,'FR',244),(3453,80,'17 : 21',244),(3454,81,'',244),(3455,87,'12-H61B-O1C-',245),(3456,65,'',245),(3457,66,'',245),(3458,67,'',245),(3459,69,'',245),(3460,70,'',245),(3461,71,'',245),(3462,75,'AN',245),(3463,76,'',245),(3464,77,'TP',245),(3465,90,'HD',245),(3466,95,'',245),(3467,68,'6',245),(3468,72,'6',245),(3469,73,'1',245),(3470,74,'MB',245),(3471,78,'FR',245),(3472,80,'17 : 21',245),(3473,81,'',245),(3474,86,'12-H61D-H4A-',247),(3475,54,'SF',247),(3476,55,'3',247),(3477,59,'Non Favourable',247),(3478,60,'AN',247),(3479,56,'4',247),(3480,57,'6',247),(3481,58,'OH',247),(3482,63,'17 : 22',247),(3483,64,'FR',247),(3484,86,'12-H61A-H3B-',248),(3485,54,'SF',248),(3486,55,'3',248),(3487,59,'Favourable',248),(3488,60,'ON',248),(3489,56,'3',248),(3490,57,'6',248),(3491,58,'OH',248),(3492,63,'17 : 23',248),(3493,64,'FR',248),(3494,83,'7-H3B-O3D-',249),(3495,11,'D',249),(3496,12,'NC',249),(3497,14,'MEDIUM',249),(3498,17,'K1',249),(3499,19,'DBL',249),(3500,20,'2-0-4',249),(3501,26,'RR',249),(3502,94,'',249),(3503,13,'BT',249),(3504,15,'3',249),(3505,16,'3',249),(3506,18,'S',249),(3507,21,'3',249),(3508,22,'U',249),(3509,23,'17 : 23',249),(3510,24,'FR',249),(3511,25,'C',249),(3512,91,'',249),(3513,92,'',249),(3514,93,'',249),(3515,82,'7-H12A-O61D-',250),(3516,1,'JF',250),(3517,6,'3',250),(3518,2,'',250),(3519,3,'C',250),(3520,4,'1',250),(3521,5,'6',250),(3522,7,'OH',250),(3523,8,'18 : 23',250),(3524,9,'SC',250),(3525,10,'FR',250),(3526,87,'7-O3D-H11C-',251),(3527,65,'IN',251),(3528,66,'LOW',251),(3529,67,'3C',251),(3530,69,'TPL',251),(3531,70,'C',251),(3532,71,'Non Organised',251),(3533,75,'AN',251),(3534,76,'Non Favourable',251),(3535,77,'',251),(3536,90,'OA',251),(3537,95,'',251),(3538,68,'3',251),(3539,72,'3',251),(3540,73,'1',251),(3541,74,'MB',251),(3542,78,'RR',251),(3543,80,'18 : 23',251),(3544,81,'',251),(3545,86,'12-H51B-H1B-',252),(3546,54,'SF',252),(3547,55,'3',252),(3548,59,'Non Favourable',252),(3549,60,'AN',252),(3550,56,'1',252),(3551,57,'5',252),(3552,58,'OH',252),(3553,63,'18 : 24',252),(3554,64,'RR',252),(3555,87,'4-H1B-H61B-',253),(3556,65,'',253),(3557,66,'',253),(3558,67,'',253),(3559,69,'',253),(3560,70,'',253),(3561,71,'',253),(3562,75,'AN',253),(3563,76,'Favourable',253),(3564,77,'K1',253),(3565,90,'HD',253),(3566,95,'',253),(3567,68,'1',253),(3568,72,'1',253),(3569,73,'6',253),(3570,74,'MB',253),(3571,78,'RR',253),(3572,80,'18 : 24',253),(3573,81,'',253),(3574,83,'12-H61B-O61D-',254),(3575,11,'IN',254),(3576,12,'NC',254),(3577,14,'MEDIUM',254),(3578,17,'K1',254),(3579,19,'NB',254),(3580,20,'Non Organised',254),(3581,26,'',254),(3582,94,'S',254),(3583,13,'',254),(3584,15,'6',254),(3585,16,'6',254),(3586,18,'OH',254),(3587,21,'6',254),(3588,22,'MB',254),(3589,23,'18 : 24',254),(3590,24,'RR',254),(3591,25,'C',254),(3592,91,'',254),(3593,92,'',254),(3594,93,'',254);
/*!40000 ALTER TABLE `rally_details_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rally_rotation_order`
--

DROP TABLE IF EXISTS `rally_rotation_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rally_rotation_order`
--

LOCK TABLES `rally_rotation_order` WRITE;
/*!40000 ALTER TABLE `rally_rotation_order` DISABLE KEYS */;
INSERT INTO `rally_rotation_order` VALUES (1,1,5,3,4,2,6,13,15,21,19,14,18,1),(2,1,5,3,4,2,6,15,21,19,14,18,13,2),(3,5,3,4,2,6,1,15,21,19,14,18,13,3),(4,5,3,4,2,7,1,21,19,14,18,13,15,4),(5,3,4,2,6,1,5,21,19,14,18,13,15,5),(6,3,4,2,6,1,5,21,19,14,18,13,15,6),(7,3,4,2,6,1,5,19,14,18,13,15,21,7),(8,4,2,6,1,5,7,19,14,18,13,15,21,8),(9,4,2,6,1,5,7,19,14,18,13,15,21,9),(10,4,2,6,1,5,7,14,18,13,15,21,19,10),(11,2,6,1,5,7,4,14,18,13,15,21,19,11),(12,2,6,1,5,7,4,18,13,15,21,19,14,12),(13,2,6,1,5,7,4,18,13,15,21,19,14,13),(14,6,1,5,3,4,2,18,13,15,21,19,14,14),(15,6,1,5,3,4,2,13,15,21,19,14,18,15),(16,1,5,3,4,2,7,13,15,21,19,14,18,16),(17,1,5,3,4,2,7,15,21,19,14,18,13,17),(18,1,5,3,4,2,7,15,21,19,14,18,13,18),(19,1,5,3,4,2,7,15,21,19,14,18,13,19),(20,1,5,3,4,2,7,15,21,19,14,18,13,20),(21,1,5,3,4,2,7,15,21,19,14,18,13,21),(22,13,15,21,19,14,18,1,5,3,4,2,6,22),(23,15,21,19,14,18,13,1,5,3,4,2,6,23),(24,15,21,19,14,18,13,5,3,4,2,6,1,24),(25,21,19,14,18,13,15,5,3,4,2,6,1,25),(26,21,19,14,18,13,15,3,4,2,6,1,5,26),(27,21,19,14,18,13,15,3,4,2,6,1,5,27),(28,19,14,18,13,15,21,3,4,2,6,1,5,28),(29,19,14,18,13,15,21,4,2,6,1,5,3,29),(30,19,14,18,13,15,21,4,2,6,1,5,3,30),(31,14,18,13,15,21,19,4,2,6,1,5,3,31),(32,14,18,13,15,21,19,2,6,1,5,3,4,32),(33,18,13,15,21,19,14,2,6,1,5,3,4,33),(34,18,13,15,21,19,14,2,6,1,5,3,4,34),(35,18,13,15,21,19,24,6,1,5,3,4,2,35),(36,13,15,21,19,24,18,6,1,5,3,4,2,36),(37,13,15,21,19,24,18,1,5,3,4,2,6,37),(38,15,21,19,14,18,13,1,5,3,4,2,6,38),(39,15,21,19,14,18,13,1,5,3,4,2,6,39),(40,15,21,19,14,18,13,1,5,3,4,2,6,40),(41,15,21,19,14,18,13,1,5,3,4,2,6,41),(42,15,21,19,14,18,13,1,5,3,4,2,6,42),(43,15,21,19,14,18,13,1,5,3,4,2,6,43),(44,15,21,19,14,18,24,5,3,4,2,6,1,44),(45,21,19,14,18,24,15,5,3,4,2,6,1,45),(46,21,19,14,18,24,15,3,4,2,6,1,5,46),(47,19,14,18,21,15,21,3,4,2,6,1,5,47),(48,19,14,18,21,15,21,4,2,6,1,5,3,48),(49,19,14,18,13,15,24,4,2,6,1,5,3,49),(50,19,14,16,13,15,24,4,2,6,1,5,3,50),(51,19,14,16,13,15,24,4,2,6,1,5,3,51),(52,19,14,16,13,15,21,4,2,6,1,5,3,52),(53,14,16,13,15,21,19,4,2,6,1,5,3,53),(54,14,16,13,15,21,19,2,6,1,5,3,4,54),(55,16,13,15,21,19,14,2,6,1,5,3,4,55),(56,16,13,15,21,19,24,6,1,5,3,4,2,56),(57,13,15,21,19,24,16,6,1,5,3,4,2,57),(58,13,15,21,19,24,16,6,1,5,3,4,2,58),(59,13,15,21,19,24,16,6,1,5,3,4,2,59),(60,13,15,21,19,14,16,6,1,5,3,4,2,60),(61,13,15,21,19,14,16,6,1,5,3,4,2,61),(62,13,15,21,19,14,16,6,1,5,3,4,2,62),(63,13,15,21,19,14,16,1,5,3,4,2,6,63),(64,15,21,19,14,16,13,1,5,3,4,2,6,64),(65,1,5,3,4,2,7,15,21,19,14,18,13,65),(66,5,3,4,2,7,1,15,21,19,14,18,13,66),(67,5,3,4,2,7,1,21,19,14,18,13,15,67),(68,3,4,2,6,1,5,21,19,14,18,13,15,68),(69,3,4,2,6,1,5,19,14,18,13,15,21,69),(70,4,2,6,1,5,3,19,14,18,13,15,21,70),(71,4,2,6,1,5,3,19,14,18,13,15,21,71),(72,4,2,6,1,5,3,19,14,18,13,15,21,72),(73,4,2,6,1,5,3,19,14,18,13,15,21,73),(74,4,2,6,1,5,7,19,14,18,13,15,21,74),(75,4,2,6,1,5,7,14,18,13,15,21,19,75),(76,2,6,1,5,7,4,14,18,13,15,21,19,76),(77,2,6,1,5,7,4,18,13,15,21,19,14,77),(78,6,1,5,3,4,2,18,13,15,21,19,14,78),(79,6,1,5,3,4,2,13,15,21,19,14,18,79),(80,6,1,5,3,4,7,13,15,21,19,14,18,80),(81,6,1,5,3,4,2,13,15,21,19,14,18,81),(82,7,1,5,3,8,2,13,15,21,19,14,18,82),(83,7,1,5,3,8,2,13,15,21,19,14,18,83),(84,7,1,5,3,8,2,13,15,21,19,14,18,84),(85,1,5,3,8,2,7,13,15,21,19,14,18,85),(86,1,5,3,8,2,7,15,21,19,14,18,13,86);
/*!40000 ALTER TABLE `rally_rotation_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_plus_minus`
--

DROP TABLE IF EXISTS `set_plus_minus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `set_plus_minus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opponent_error` varchar(50) DEFAULT NULL,
  `team_fault` varchar(50) DEFAULT NULL,
  `match_evaluation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_evaluation_id` (`match_evaluation_id`),
  CONSTRAINT `set_plus_minus_ibfk_1` FOREIGN KEY (`match_evaluation_id`) REFERENCES `match_evaluation_set` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_plus_minus`
--

LOCK TABLES `set_plus_minus` WRITE;
/*!40000 ALTER TABLE `set_plus_minus` DISABLE KEYS */;
INSERT INTO `set_plus_minus` VALUES (1,'8','6',1),(2,'5','3',2);
/*!40000 ALTER TABLE `set_plus_minus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_substitution`
--

DROP TABLE IF EXISTS `set_substitution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_substitution`
--

LOCK TABLES `set_substitution` WRITE;
/*!40000 ALTER TABLE `set_substitution` DISABLE KEYS */;
INSERT INTO `set_substitution` VALUES (1,1,1,NULL,NULL,NULL,NULL,NULL,1,1),(2,2,5,NULL,NULL,NULL,NULL,NULL,1,1),(3,3,3,NULL,NULL,NULL,NULL,NULL,1,1),(4,4,4,8,'17 : 21',NULL,81,NULL,1,1),(5,5,2,NULL,NULL,NULL,NULL,NULL,1,1),(6,6,6,NULL,NULL,NULL,NULL,NULL,1,1),(7,1,13,NULL,NULL,NULL,NULL,NULL,1,2),(8,2,15,NULL,NULL,NULL,NULL,NULL,1,2),(9,3,21,NULL,NULL,NULL,NULL,NULL,1,2),(10,4,19,NULL,NULL,NULL,NULL,NULL,1,2),(11,5,14,NULL,NULL,NULL,NULL,NULL,1,2),(12,6,18,NULL,NULL,NULL,NULL,NULL,1,2),(13,1,13,NULL,NULL,NULL,NULL,NULL,2,1),(14,2,15,NULL,NULL,NULL,NULL,NULL,2,1),(15,3,21,NULL,NULL,NULL,NULL,NULL,2,1),(16,4,19,NULL,NULL,NULL,NULL,NULL,2,1),(17,5,14,NULL,NULL,NULL,NULL,NULL,2,1),(18,6,18,16,'15 : 14',NULL,50,0,2,1),(19,1,1,NULL,NULL,NULL,NULL,NULL,2,2),(20,2,5,NULL,NULL,NULL,NULL,NULL,2,2),(21,3,3,NULL,NULL,NULL,NULL,NULL,2,2),(22,4,4,NULL,NULL,NULL,NULL,NULL,2,2),(23,5,2,NULL,NULL,NULL,NULL,NULL,2,2),(24,6,6,NULL,NULL,NULL,NULL,NULL,2,2);
/*!40000 ALTER TABLE `set_substitution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set_timeout`
--

DROP TABLE IF EXISTS `set_timeout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set_timeout`
--

LOCK TABLES `set_timeout` WRITE;
/*!40000 ALTER TABLE `set_timeout` DISABLE KEYS */;
/*!40000 ALTER TABLE `set_timeout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setrotationorder`
--

DROP TABLE IF EXISTS `setrotationorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setrotationorder`
--

LOCK TABLES `setrotationorder` WRITE;
/*!40000 ALTER TABLE `setrotationorder` DISABLE KEYS */;
INSERT INTO `setrotationorder` VALUES (1,1,1,1,1),(2,2,5,1,1),(3,3,3,1,1),(4,4,4,1,1),(5,5,2,1,1),(6,6,6,1,1),(7,7,7,1,1),(8,1,13,1,2),(9,2,15,1,2),(10,3,21,1,2),(11,4,19,1,2),(12,5,14,1,2),(13,6,18,1,2),(14,7,24,1,2),(15,1,13,2,1),(16,2,15,2,1),(17,3,21,2,1),(18,4,19,2,1),(19,5,14,2,1),(20,6,18,2,1),(21,7,24,2,1),(22,1,1,2,2),(23,2,5,2,2),(24,3,3,2,2),(25,4,4,2,2),(26,5,2,2,2),(27,6,6,2,2),(28,7,7,2,2);
/*!40000 ALTER TABLE `setrotationorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shortcut_settings`
--

DROP TABLE IF EXISTS `shortcut_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shortcut_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shortcutId` int(11) DEFAULT NULL,
  `headingId` int(11) DEFAULT NULL,
  `code` varchar(100) NOT NULL,
  `abbr` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shortcut_settings`
--

LOCK TABLES `shortcut_settings` WRITE;
/*!40000 ALTER TABLE `shortcut_settings` DISABLE KEYS */;
INSERT INTO `shortcut_settings` VALUES (1,1,1,'S','S'),(2,2,1,'A','OH'),(3,3,1,'M','MB'),(4,4,1,'U','UV'),(5,5,1,'LB','LB'),(6,6,2,'H','H'),(7,7,2,'O','Opp'),(8,8,3,'5R','5R'),(9,9,3,'4R','4R'),(10,10,3,'3R','3R'),(11,11,3,'2R','2R'),(12,12,4,'55','5C'),(13,13,4,'44','4C'),(14,14,4,'33','3C'),(15,15,4,'22','2C'),(16,71,4,'NN','NC'),(17,72,4,'1C','1C'),(18,16,5,'T1','Low'),(19,17,5,'T2','Medium'),(20,18,5,'T3','High'),(21,87,5,'T4','ODB'),(22,19,6,'Q1','K1'),(23,20,6,'Q2','K2'),(24,21,6,'Q3','TP'),(25,22,7,'AA','OOH'),(26,23,7,'MM','OMB'),(27,24,7,'UU','OU'),(28,25,7,'SS','OS'),(29,26,8,'E1','SGL'),(30,27,8,'E2','DBL'),(31,28,8,'E3','TPL'),(32,84,8,'E4','NB'),(33,29,9,'D1','ODF'),(34,30,9,'D11','ODF'),(35,31,9,'D2','ODF'),(36,32,9,'D22','ODF'),(37,33,9,'D3','ODF'),(38,34,9,'D33','ODF'),(39,35,9,'D333','ODF'),(40,75,9,'D23','ODF'),(41,76,9,'DN','ODF'),(42,36,10,'A1','IN'),(43,37,10,'A2','OT'),(44,38,10,'A3','BT'),(45,39,10,'A4','OL'),(46,40,10,'A5','D'),(47,41,10,'A6','BC'),(48,73,10,'A7','R'),(49,74,10,'A8','BTL'),(50,42,11,'B1','CB'),(51,43,11,'B2','RR'),(52,44,11,'B3','ZB'),(53,88,11,'B4','ZB'),(54,45,12,'F','F'),(55,46,12,'F1','SF'),(56,47,12,'F0','NF'),(57,48,13,'S1','JF'),(58,49,13,'S2','JS'),(59,50,13,'S3','JP'),(60,51,13,'S4','SF'),(61,52,13,'S5','SS'),(62,53,14,'W1','JS'),(63,54,14,'W2','RB'),(64,55,14,'W3','FP'),(65,56,14,'W4','HP'),(66,57,14,'W5','BC'),(67,68,14,'W6','BS'),(68,69,14,'W7','FS'),(69,58,15,'N1','ON'),(70,59,15,'N2','CN'),(71,60,15,'N3','AN'),(72,61,15,'N4','LT'),(73,70,15,'N5','ANF'),(74,62,16,'LO','LO'),(75,63,16,'LK','LC'),(76,64,17,'K','Kill'),(77,65,17,'D','SF'),(78,66,17,'LI','LI'),(79,67,17,'CB','CB'),(80,77,17,'ST','ST'),(81,78,18,'HA','HA'),(82,79,18,'HB','HB'),(83,80,18,'HD','HD'),(84,85,18,'OA','OA'),(85,86,18,'OB','OB'),(86,81,19,'C1','C'),(87,82,19,'C2','S'),(88,83,19,'C3','D');
/*!40000 ALTER TABLE `shortcut_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'Maharashtra','MH',1,'Madhuri','','','','',0),(2,'Kerala','KL',1,'Madhuri','','','','',0);
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainee`
--

DROP TABLE IF EXISTS `trainee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trainee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `batch_id` int(11) NOT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `trainee_ibfk_1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainee`
--

LOCK TABLES `trainee` WRITE;
/*!40000 ALTER TABLE `trainee` DISABLE KEYS */;
/*!40000 ALTER TABLE `trainee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','root','root@gmail.com','3B46-5733-08CA-9CBB-8CE7',1,'','2018-12-08 03:33:55');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vollycourtcoordinates`
--

DROP TABLE IF EXISTS `vollycourtcoordinates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vollycourtcoordinates`
--

LOCK TABLES `vollycourtcoordinates` WRITE;
/*!40000 ALTER TABLE `vollycourtcoordinates` DISABLE KEYS */;
INSERT INTO `vollycourtcoordinates` VALUES (1,'Service',1,1,90,331,294,263,410,224,611,155),(2,'Service',1,2,90,331,294,233,410,177,461,155),(3,'Service',1,3,90,331,294,282,410,257,461,246),(4,'Service',1,4,90,331,294,332,410,332,461,332),(5,'Service',1,5,90,331,294,332,410,332,611,332),(6,'Service',1,6,90,331,294,296,410,277,611,242),(7,'Service',5,1,90,155,294,155,410,155,611,155),(8,'Service',5,2,90,155,294,155,410,155,461,155),(9,'Service',5,3,90,155,294,204,410,232,461,245),(10,'Service',5,4,90,155,294,251,410,306,461,330),(11,'Service',5,5,90,155,294,222,410,261,611,330),(12,'Service',5,6,90,155,294,188,410,206,611,243),(13,'Service',6,1,90,242,294,208,410,190,611,155),(14,'Service',6,2,90,242,294,193,410,164,461,155),(15,'Service',6,3,90,242,294,242,410,242,461,242),(16,'Service',6,4,90,242,294,290,410,317,461,331),(17,'Service',6,5,90,242,294,276,410,296,611,331),(18,'Service',6,6,90,242,294,242,410,242,611,242),(19,'Attack',1,1,212,331,294,297,410,244,611,155),(20,'Attack',1,2,212,331,294,273,410,188,461,155),(21,'Attack',1,3,212,331,294,304,410,264,461,242),(22,'Attack',1,4,212,331,294,330,410,330,461,331),(23,'Attack',1,5,212,331,294,330,410,330,611,331),(24,'Attack',1,6,212,331,294,314,410,290,611,242),(25,'Attack',2,1,361,331,410,300,525,216,611,155),(26,'Attack',2,2,361,331,410,247,445,177,461,155),(27,'Attack',2,3,361,331,410,286,444,255,461,242),(28,'Attack',2,4,361,331,410,330,452,330,461,331),(29,'Attack',2,5,361,331,410,330,452,330,611,331),(30,'Attack',2,6,361,331,410,314,525,274,611,242),(31,'Attack',3,1,361,242,410,225,525,186,611,155),(32,'Attack',3,2,361,242,410,200,443,168,461,155),(33,'Attack',3,3,361,242,410,243,443,245,461,242),(34,'Attack',3,4,361,242,410,280,448,320,461,331),(35,'Attack',3,5,361,242,410,257,448,272,611,331),(36,'Attack',3,6,361,242,410,240,525,242,611,242),(37,'Attack',4,1,361,155,410,157,451,155,611,155),(38,'Attack',4,2,361,155,410,157,451,155,461,155),(39,'Attack',4,3,361,155,410,197,447,233,461,242),(40,'Attack',4,4,361,155,410,235,447,307,461,331),(41,'Attack',4,5,361,155,410,187,451,215,611,331),(42,'Attack',4,6,361,155,410,173,451,186,611,242),(43,'Attack',5,1,212,155,294,155,410,155,611,155),(44,'Attack',5,2,212,155,294,155,410,155,461,155),(45,'Attack',5,3,212,155,294,185,410,228,461,242),(46,'Attack',5,4,212,155,294,208,410,292,461,331),(47,'Attack',5,5,212,155,294,189,410,239,611,331),(48,'Attack',5,6,212,155,294,172,410,198,611,242),(49,'Attack',6,1,212,242,294,227,410,200,611,155),(50,'Attack',6,2,212,242,294,218,410,170,461,155),(51,'Attack',6,3,212,242,294,242,410,242,461,242),(52,'Attack',6,4,212,242,294,269,410,312,461,331),(53,'Attack',6,5,212,242,294,260,410,285,611,331),(54,'Attack',6,6,212,242,294,242,410,242,611,242),(55,'SetH',1,1,212,331,212,331,212,331,212,331),(56,'SetM',1,1,212,331,212,331,212,331,212,331),(57,'SetL',1,1,212,331,212,331,212,331,212,331),(58,'SetH',1,2,212,331,411,309,430,320,395,331),(59,'SetM',1,2,212,331,411,309,430,320,395,331),(60,'SetL',1,2,212,331,411,309,430,320,395,331),(61,'SetH',1,3,212,331,411,284,430,262,395,240),(62,'SetM',1,3,212,331,411,284,430,262,395,240),(63,'SetL',1,3,212,331,411,284,430,262,395,240),(64,'SetH',1,4,212,331,411,200,430,175,395,150),(65,'SetM',1,4,212,331,411,200,430,175,395,150),(66,'SetL',1,4,212,331,411,200,430,175,395,150),(67,'SetH',1,5,212,331,411,274,430,212,285,155),(68,'SetM',1,5,212,331,411,274,430,212,285,155),(69,'SetL',1,5,212,331,411,274,430,212,285,155),(70,'SetH',1,6,212,331,411,312,430,290,285,242),(71,'SetM',1,6,212,331,411,312,430,290,285,242),(72,'SetL',1,6,212,331,411,312,430,290,285,242),(73,'SetH',2,1,361,331,430,300,360,309,285,331),(74,'SetM',2,1,361,331,430,300,360,309,285,331),(75,'SetL',2,1,361,331,430,300,360,309,285,331),(76,'SetH',2,2,361,331,361,331,361,331,361,331),(77,'SetM',2,2,361,331,361,331,361,331,361,331),(78,'SetL',2,2,361,331,361,331,361,331,361,331),(79,'SetH',2,3,361,331,411,313,430,285,395,242),(80,'SetM',2,3,361,331,411,313,430,285,395,242),(81,'SetL',2,3,361,331,411,313,430,285,395,242),(82,'SetH',2,4,361,331,411,266,430,208,395,155),(83,'SetM',2,4,361,331,411,266,430,208,395,155),(84,'SetL',2,4,361,331,411,266,430,208,395,155),(85,'SetH',2,5,361,331,411,300,430,250,285,155),(86,'SetM',2,5,361,331,411,300,430,250,285,155),(87,'SetL',2,5,361,331,411,300,430,250,285,155),(88,'SetH',2,6,361,331,411,320,430,290,285,242),(89,'SetM',2,6,361,331,411,320,430,290,285,242),(90,'SetL',2,6,361,331,411,320,430,290,285,242),(91,'SetH',3,1,361,242,411,265,430,295,285,331),(92,'SetM',3,1,361,242,411,265,430,295,285,331),(93,'SetL',3,1,361,242,411,265,430,295,285,331),(94,'SetH',3,2,361,242,411,265,430,300,395,331),(95,'SetM',3,2,361,242,411,265,430,300,395,331),(96,'SetL',3,2,361,242,411,265,430,300,395,331),(97,'SetH',3,3,361,242,361,242,361,242,361,242),(98,'SetM',3,3,361,242,361,242,361,242,361,242),(99,'SetL',3,3,361,242,361,242,361,242,361,242),(100,'SetH',3,4,361,242,411,215,430,190,395,155),(101,'SetM',3,4,361,242,411,215,430,190,395,155),(102,'SetL',3,4,361,242,411,215,430,190,395,155),(103,'SetH',3,5,361,242,411,225,430,200,285,155),(104,'SetM',3,5,361,242,411,225,430,200,285,155),(105,'SetL',3,5,361,242,411,225,430,200,285,155),(106,'SetH',3,6,361,242,411,236,430,218,285,242),(107,'SetM',3,6,361,242,411,236,430,218,285,242),(108,'SetL',3,6,361,242,411,236,430,218,285,242),(109,'SetH',4,1,361,155,411,164,430,190,285,331),(110,'SetM',4,1,361,155,411,164,430,190,285,331),(111,'SetL',4,1,361,155,411,164,430,190,285,331),(112,'SetH',4,2,361,155,411,200,430,262,395,331),(113,'SetM',4,2,361,155,411,200,430,262,395,331),(114,'SetL',4,2,361,155,411,200,430,262,395,331),(115,'SetH',4,3,361,155,411,185,430,210,395,242),(116,'SetM',4,3,361,155,411,185,430,210,395,242),(117,'SetL',4,3,361,155,411,185,430,210,395,242),(118,'SetH',4,4,361,155,361,155,361,155,361,155),(119,'SetM',4,4,361,155,361,155,361,155,361,155),(120,'SetL',4,4,361,155,361,155,361,155,361,155),(121,'SetH',4,5,361,155,411,165,430,190,285,155),(122,'SetM',4,5,361,155,411,165,430,190,285,155),(123,'SetL',4,5,361,155,411,165,430,190,285,155),(124,'SetH',4,6,361,155,411,178,430,200,285,242),(125,'SetM',4,6,361,155,411,178,430,200,285,242),(126,'SetL',4,6,361,155,411,178,430,200,285,242),(127,'SetH',5,1,212,155,411,220,430,260,285,331),(128,'SetM',5,1,212,155,411,220,430,260,285,331),(129,'SetL',5,1,212,155,411,220,430,260,285,331),(130,'SetH',5,2,212,155,411,240,430,300,395,331),(131,'SetM',5,2,212,155,411,240,430,300,395,331),(132,'SetL',5,2,212,155,411,240,430,300,395,331),(133,'SetH',5,3,212,155,411,200,430,220,395,242),(134,'SetM',5,3,212,155,411,200,430,220,395,242),(135,'SetL',5,3,212,155,411,200,430,220,395,242),(136,'SetH',5,4,212,155,411,190,430,162,395,155),(137,'SetM',5,4,212,155,411,190,430,162,395,155),(138,'SetL',5,4,212,155,411,190,430,162,395,155),(139,'SetH',5,5,212,155,212,155,212,155,212,155),(140,'SetM',5,5,212,155,212,155,212,155,212,155),(141,'SetL',5,5,212,155,212,155,212,155,212,155),(142,'SetH',5,6,212,155,411,180,430,210,285,242),(143,'SetM',5,6,212,155,411,180,430,210,285,242),(144,'SetL',5,6,212,155,411,180,430,210,285,242),(145,'SetH',6,1,212,242,411,260,430,296,285,331),(146,'SetM',6,1,212,242,411,260,430,296,285,331),(147,'SetL',6,1,212,242,411,260,430,296,285,331),(148,'SetH',6,2,212,242,411,267,430,310,395,331),(149,'SetM',6,2,212,242,411,267,430,310,395,331),(150,'SetL',6,2,212,242,411,267,430,310,395,331),(151,'SetH',6,3,212,242,411,210,430,230,395,242),(152,'SetM',6,3,212,242,411,210,430,230,395,242),(153,'SetL',6,3,212,242,411,210,430,230,395,242),(154,'SetH',6,4,212,242,411,206,430,165,395,155),(155,'SetM',6,4,212,242,411,206,430,165,395,155),(156,'SetL',6,4,212,242,411,206,430,165,395,155),(157,'SetH',6,5,212,242,411,220,430,187,285,155),(158,'SetM',6,5,212,242,411,220,430,187,285,155),(159,'SetL',6,5,212,242,411,220,430,187,285,155),(160,'SetH',6,6,212,242,212,242,212,242,212,242),(161,'SetM',6,6,212,242,212,242,212,242,212,242),(162,'SetL',6,6,212,242,212,242,212,242,212,242),(163,'Reception',1,1,212,331,212,331,212,331,212,331),(164,'Reception',1,2,212,331,270,331,319,331,361,331),(165,'Reception',1,3,212,331,274,292,325,256,361,242),(166,'Reception',1,4,212,331,256,282,317,205,361,155),(167,'Reception',1,5,212,331,212,284,212,190,212,155),(168,'Reception',1,6,212,331,212,284,212,264,212,242),(169,'Reception',2,1,361,331,307,331,260,331,212,331),(170,'Reception',2,2,361,331,361,331,361,331,361,331),(171,'Reception',2,3,361,331,361,298,361,297,361,242),(172,'Reception',2,4,361,331,361,298,361,196,361,155),(173,'Reception',2,5,361,331,314,276,259,209,212,155),(174,'Reception',2,6,361,331,293,292,250,265,212,242),(175,'Reception',3,1,361,242,294,281,255,307,212,331),(176,'Reception',3,2,361,242,361,266,361,303,361,331),(177,'Reception',3,3,361,242,361,242,361,242,361,242),(178,'Reception',3,4,361,242,361,217,361,173,361,155),(179,'Reception',3,5,361,242,303,211,251,176,212,155),(180,'Reception',3,6,361,242,294,246,248,245,212,242),(181,'Reception',4,1,361,155,315,211,266,273,212,331),(182,'Reception',4,2,361,155,361,198,361,290,361,331),(183,'Reception',4,3,361,155,361,198,361,220,361,242),(184,'Reception',4,4,361,155,361,155,361,155,361,155),(185,'Reception',4,5,361,155,294,160,253,155,212,155),(186,'Reception',4,6,361,155,294,197,252,221,212,242),(187,'Reception',5,1,212,155,212,210,212,280,212,331),(188,'Reception',5,2,212,155,267,217,323,285,361,331),(189,'Reception',5,3,212,155,280,196,316,216,361,242),(190,'Reception',5,4,212,155,270,156,320,155,361,155),(191,'Reception',5,5,212,155,212,155,212,155,212,155),(192,'Reception',5,6,212,155,212,177,212,220,212,242),(193,'Reception',6,1,212,242,212,270,212,306,212,331),(194,'Reception',6,2,212,242,261,272,314,303,361,331),(195,'Reception',6,3,212,242,268,242,319,242,361,242),(196,'Reception',6,4,212,242,287,197,316,176,361,155),(197,'Reception',6,5,212,242,212,200,212,175,212,155),(198,'Reception',6,6,212,242,212,242,212,242,212,242),(199,'Defence',1,1,212,331,212,331,212,331,212,331),(200,'Defence',1,2,212,331,270,331,319,331,361,331),(201,'Defence',1,3,212,331,274,292,325,256,361,242),(202,'Defence',1,4,212,331,256,282,317,205,361,155),(203,'Defence',1,5,212,331,212,284,212,190,212,155),(204,'Defence',1,6,212,331,212,284,212,264,212,242),(205,'Defence',2,1,361,331,307,331,260,331,212,331),(206,'Defence',2,2,361,331,361,331,361,331,361,331),(207,'Defence',2,3,361,331,361,298,361,297,361,242),(208,'Defence',2,4,361,331,361,298,361,196,361,155),(209,'Defence',2,5,361,331,314,276,259,209,212,155),(210,'Defence',2,6,361,331,293,292,250,265,212,242),(211,'Defence',3,1,361,242,294,281,255,307,212,331),(212,'Defence',3,2,361,242,361,266,361,303,361,331),(213,'Defence',3,3,361,242,361,242,361,242,361,242),(214,'Defence',3,4,361,242,361,217,361,173,361,155),(215,'Defence',3,5,361,242,303,211,251,176,212,155),(216,'Defence',3,6,361,242,294,246,248,245,212,242),(217,'Defence',4,1,361,155,315,211,266,273,212,331),(218,'Defence',4,2,361,155,361,198,361,290,361,331),(219,'Defence',4,3,361,155,361,198,361,220,361,242),(220,'Defence',4,4,361,155,361,155,361,155,361,155),(221,'Defence',4,5,361,155,294,160,253,155,212,155),(222,'Defence',4,6,361,155,294,197,252,221,212,242),(223,'Defence',5,1,212,155,212,210,212,280,212,331),(224,'Defence',5,2,212,155,267,217,323,285,361,331),(225,'Defence',5,3,212,155,280,196,316,216,361,242),(226,'Defence',5,4,212,155,270,156,320,155,361,155),(227,'Defence',5,5,212,155,212,155,212,155,212,155),(228,'Defence',5,6,212,155,212,177,212,220,212,242),(229,'Defence',6,1,212,242,212,270,212,306,212,331),(230,'Defence',6,2,212,242,261,272,314,303,361,331),(231,'Defence',6,3,212,242,268,242,319,242,361,242),(232,'Defence',6,4,212,242,287,197,316,176,361,155),(233,'Defence',6,5,212,242,212,200,212,175,212,155),(234,'Defence',6,6,212,242,212,242,212,242,212,242),(235,'BlockAttack',1,2,611,155,611,155,361,331,361,331),(236,'BlockAttack',1,3,611,155,611,155,361,242,361,242),(237,'BlockAttack',1,4,611,155,611,155,361,155,361,155),(238,'BlockAttack',2,2,461,155,461,155,361,331,361,331),(239,'BlockAttack',2,3,461,155,461,155,361,242,361,242),(240,'BlockAttack',2,4,461,155,461,155,361,155,361,155),(241,'BlockAttack',3,2,461,242,461,242,361,331,361,331),(242,'BlockAttack',3,3,461,242,461,242,361,242,361,242),(243,'BlockAttack',3,4,461,242,461,242,361,155,361,155),(244,'BlockAttack',4,2,461,331,461,331,361,331,361,331),(245,'BlockAttack',4,3,461,331,461,331,361,242,361,242),(246,'BlockAttack',4,4,461,331,461,331,361,155,361,155),(247,'BlockAttack',5,2,611,331,611,331,361,331,361,331),(248,'BlockAttack',5,3,611,331,611,331,361,242,361,242),(249,'BlockAttack',5,4,611,331,611,331,361,155,361,155),(250,'BlockAttack',6,2,611,242,611,242,361,331,361,331),(251,'BlockAttack',6,3,611,242,611,242,361,242,361,242),(252,'BlockAttack',6,4,611,242,611,242,361,155,361,155),(253,'BlockRH',2,1,361,331,361,331,212,331,212,331),(254,'BlockRH',2,2,361,331,361,331,361,331,361,331),(255,'BlockRH',2,3,361,331,361,331,361,242,361,242),(256,'BlockRH',2,4,361,331,361,331,361,155,361,155),(257,'BlockRH',2,5,361,331,361,331,212,155,212,155),(258,'BlockRH',2,6,361,331,361,331,212,242,212,242),(259,'BlockRH',2,7,361,331,361,331,295,80,295,80),(260,'BlockRH',2,8,361,331,361,331,295,405,295,405),(261,'BlockRH',2,9,361,331,361,331,55,242,55,242),(262,'BlockRH',3,1,361,242,361,242,212,331,212,331),(263,'BlockRH',3,2,361,242,361,242,361,331,361,331),(264,'BlockRH',3,3,361,242,361,242,361,242,361,242),(265,'BlockRH',3,4,361,242,361,242,361,155,361,155),(266,'BlockRH',3,5,361,242,361,242,212,155,212,155),(267,'BlockRH',3,6,361,242,361,242,212,242,212,242),(268,'BlockRH',3,7,361,242,361,242,295,80,295,80),(269,'BlockRH',3,8,361,242,361,242,295,405,295,405),(270,'BlockRH',3,9,361,242,361,242,55,242,55,242),(271,'BlockRH',4,1,361,155,361,155,212,331,212,331),(272,'BlockRH',4,2,361,155,361,155,361,331,361,331),(273,'BlockRH',4,3,361,155,361,155,361,242,361,242),(274,'BlockRH',4,4,361,155,361,155,361,155,361,155),(275,'BlockRH',4,5,361,155,361,155,212,155,212,155),(276,'BlockRH',4,6,361,155,361,155,212,242,212,242),(277,'BlockRH',4,7,361,155,361,155,295,80,295,80),(278,'BlockRH',4,8,361,155,361,155,295,405,295,405),(279,'BlockRH',4,9,361,155,361,155,55,242,55,242),(280,'BlockRO',2,1,361,331,361,331,611,155,611,155),(281,'BlockRO',2,2,361,331,361,331,461,155,461,155),(282,'BlockRO',2,3,361,331,361,331,461,242,461,242),(283,'BlockRO',2,4,361,331,361,331,461,331,461,331),(284,'BlockRO',2,5,361,331,361,331,611,331,611,331),(285,'BlockRO',2,6,361,331,361,331,611,242,611,242),(286,'BlockRO',2,7,361,331,361,331,525,405,525,405),(287,'BlockRO',2,8,361,331,361,331,525,80,525,80),(288,'BlockRO',2,9,361,331,361,331,745,242,745,242),(289,'BlockRO',3,1,361,242,361,242,611,155,611,155),(290,'BlockRO',3,2,361,242,361,242,461,155,461,155),(291,'BlockRO',3,3,361,242,361,242,461,242,461,242),(292,'BlockRO',3,4,361,242,361,242,461,331,461,331),(293,'BlockRO',3,5,361,242,361,242,611,331,611,331),(294,'BlockRO',3,6,361,242,361,242,611,242,611,242),(295,'BlockRO',3,7,361,242,361,242,525,405,525,405),(296,'BlockRO',3,8,361,242,361,242,525,80,525,80),(297,'BlockRO',3,9,361,242,361,242,745,242,745,242),(298,'BlockRO',4,1,361,155,361,155,611,155,611,155),(299,'BlockRO',4,2,361,155,361,155,461,155,461,155),(300,'BlockRO',4,3,361,155,361,155,461,242,461,242),(301,'BlockRO',4,4,361,155,361,155,461,331,461,331),(302,'BlockRO',4,5,361,155,361,155,611,331,611,331),(303,'BlockRO',4,6,361,155,361,155,611,242,611,242),(304,'BlockRO',4,7,361,155,361,155,525,405,525,405),(305,'BlockRO',4,8,361,155,361,155,525,80,525,80),(306,'BlockRO',4,9,361,155,361,155,745,242,745,242);
/*!40000 ALTER TABLE `vollycourtcoordinates` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-24  7:48:09
