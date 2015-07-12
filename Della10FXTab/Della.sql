-- MySQL dump 10.13  Distrib 5.6.21, for osx10.8 (x86_64)
--
-- Host: localhost    Database: Della
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `actionitems`
--

DROP TABLE IF EXISTS `actionitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actionitems` (
  `name` varchar(200) NOT NULL,
  `description` text,
  `resolution` text,
  `creation` date DEFAULT NULL,
  `due` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `member` varchar(100) DEFAULT NULL,
  `team` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `fk_members` (`member`),
  KEY `fk_teams` (`team`),
  CONSTRAINT `fk_members` FOREIGN KEY (`member`) REFERENCES `members` (`membername`),
  CONSTRAINT `fk_teams` FOREIGN KEY (`team`) REFERENCES `teams` (`teamname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actionitems`
--

LOCK TABLES `actionitems` WRITE;
/*!40000 ALTER TABLE `actionitems` DISABLE KEYS */;
INSERT INTO `actionitems` VALUES ('Heyo!','Howdy','Come on','2015-02-03','2015-02-28',1,'Ram',' Fire '),('Ram','Say Ram','Hello Ram','2015-02-03','2015-02-27',1,'Anand',' Wings '),('Win','I said win','I said win','2015-01-31','2015-02-04',1,'Ram',' Fire ');
/*!40000 ALTER TABLE `actionitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('ram','hello');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memberTeam`
--

DROP TABLE IF EXISTS `memberTeam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memberTeam` (
  `membername` varchar(100) DEFAULT NULL,
  `teamname` varchar(100) DEFAULT NULL,
  KEY `membername` (`membername`),
  KEY `teamname` (`teamname`),
  CONSTRAINT `memberteam_ibfk_1` FOREIGN KEY (`membername`) REFERENCES `members` (`membername`),
  CONSTRAINT `memberteam_ibfk_2` FOREIGN KEY (`teamname`) REFERENCES `teams` (`teamname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memberTeam`
--

LOCK TABLES `memberTeam` WRITE;
/*!40000 ALTER TABLE `memberTeam` DISABLE KEYS */;
INSERT INTO `memberTeam` VALUES ('Somu',' Wings '),('Ram',' Wings ');
/*!40000 ALTER TABLE `memberTeam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members` (
  `membername` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`membername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('Anand'),('Ram'),('Somu');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `teamname` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`teamname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (' Fire '),(' Wings ');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-03 15:14:44
