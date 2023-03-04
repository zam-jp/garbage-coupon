-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: kusatsu
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcements` (
  `id` int NOT NULL AUTO_INCREMENT,
  `posted_by` varchar(20) NOT NULL,
  `posted_on` timestamp NULL DEFAULT NULL,
  `text` text NOT NULL,
  `is_publish` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `posted_by` (`posted_by`),
  CONSTRAINT `announcements_ibfk_1` FOREIGN KEY (`posted_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `name` varchar(50) NOT NULL,
  `code` char(2) NOT NULL,
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES ('Andorra','AD'),('United Arab Emirates','AE'),('Afghanistan','AF'),('Antigua and Barbuda','AG'),('Anguilla','AI'),('Albania','AL'),('Armenia','AM'),('Angola','AO'),('Antarctica','AQ'),('Argentina','AR'),('American Samor','AS'),('Austria','AT'),('Australia','AU'),('Aruba','AW'),('&Aring;land Islands','AX'),('Azerbaijan','AZ'),('Bosnia and Herzegovina','BA'),('Barbados','BB'),('Bangladesh','BD'),('Belgium','BE'),('Burkina Faso','BF'),('Bulgaria','BG'),('Bahrain','BH'),('Burundi','BI'),('Benin','BJ'),('Saint Barth&eacute;lemy','BL'),('Bermuda','BM'),('Brunei Darussalam','BN'),('Bolivia, Plurinational State of','BO'),('Bonaire, Sint Eustatius and Saba','BQ'),('Brazil','BR'),('Bahamas','BS'),('Bhutan','BT'),('Bouvet Island','BV'),('Botswana','BW'),('Belarus','BY'),('Belize','BZ'),('Canada','CA'),('Cocos (Keeling) Islands','CC'),('Congo, The Democratic Republic of The','CD'),('Central African Republic','CF'),('Congo','CG'),('Switzerland','CH'),('Ivory Coast','CI'),('Cook Islands','CK'),('Chile','CL'),('Cameroon','CM'),('China','CN'),('Colombia','CO'),('Costa Rica','CR'),('Cuba','CU'),('Cape Verde','CV'),('Cura&Ccedil;ao','CW'),('Christmas Island','CX'),('Algeria','DZ'),('Western Sahara','EH'),('Spain','ES'),('United Kingdom','GB'),('South Georgia and The South Sandwich Islands','GS'),('Croatia','HR'),('British Indian Ocean Territory','IO'),('Cambodia','KH'),('Comoros','KM'),('Saint Kitts and Nevis','KN'),('Cayman Islands','KY'),('Saint Lucia','LC'),('Sri Lanka','LK'),('Saint Martin (French Part)','MF'),('Saint Pierre and Miquelon','PM'),('Serbia','RS'),('Saudi Arabia','SA'),('Solomon Islands','SB'),('Seychelles','SC'),('Sudan','SD'),('Sweden','SE'),('Singapore','SG'),('Slovania','SI'),('Svalbard and Jan Mayen','SJ'),('Slovakia','SK'),('Sierra Leone','SL'),('San Marino','SM'),('Senegal','SN'),('Somalia','SO'),('Suriname','SR'),('South Sudan','SS'),('Sao Tome and Principe','ST'),('Sint Maarten (Dutch Part)','SX'),('Syrian Arab Republic','SY'),('Swaziland','SZ'),('Turks and Caicos Islands','TC'),('Chad','TD'),('Togo','TG'),('Thailand','TH'),('Tajikistan','TJ'),('Tokelau','TK'),('Timor-Leste','TL'),('Turkmenistan','TM'),('Tunisia','TN'),('Tonga','TO'),('Turkey','TR'),('Trinidad and Tobago','TT'),('Tuvalu','TV'),('Taiwan, Province of China','TW'),('Tanzania, United Republic of','TZ'),('Ukraine','UA'),('Uganda','UG'),('United States Minor Outlying Islands','UM'),('United States','US'),('Uruguay','UY'),('Uzbekistan','UZ'),('Saint Vincent and The Grenadines','VC'),('Venezuela','VE'),('Virgin Islands, British','VG'),('Virgin Islands, U.S.','VI'),('Viet Nam','VN'),('Vanuatu','VU'),('Wallis and Futuna','WF'),('Samoa','WS'),('Yemen','YE'),('South Africa','ZA'),('Zambia','ZM'),('Zimbabwe','ZW');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enquiries`
--

DROP TABLE IF EXISTS `enquiries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enquiries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `posted_by` varchar(20) NOT NULL,
  `posted_on` timestamp NULL DEFAULT NULL,
  `responded_by` varchar(20) NOT NULL,
  `responded_on` timestamp NULL DEFAULT NULL,
  `question` text NOT NULL,
  `respond` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `posted_by` (`posted_by`),
  KEY `responded_by` (`responded_by`),
  CONSTRAINT `enquiries_ibfk_1` FOREIGN KEY (`posted_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `enquiries_ibfk_2` FOREIGN KEY (`responded_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enquiries`
--

LOCK TABLES `enquiries` WRITE;
/*!40000 ALTER TABLE `enquiries` DISABLE KEYS */;
/*!40000 ALTER TABLE `enquiries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garbage_bag_quota`
--

DROP TABLE IF EXISTS `garbage_bag_quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garbage_bag_quota` (
  `amount1` int NOT NULL,
  `amount2` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garbage_bag_quota`
--

LOCK TABLES `garbage_bag_quota` WRITE;
/*!40000 ALTER TABLE `garbage_bag_quota` DISABLE KEYS */;
/*!40000 ALTER TABLE `garbage_bag_quota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garbage_bag_requests`
--

DROP TABLE IF EXISTS `garbage_bag_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garbage_bag_requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `requested_by` varchar(20) NOT NULL,
  `requested_on` timestamp NULL DEFAULT NULL,
  `processed_by` varchar(20) NOT NULL,
  `processed_on` timestamp NULL DEFAULT NULL,
  `has_processed` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `requested_by` (`requested_by`),
  CONSTRAINT `garbage_bag_requests_ibfk_1` FOREIGN KEY (`requested_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garbage_bag_requests`
--

LOCK TABLES `garbage_bag_requests` WRITE;
/*!40000 ALTER TABLE `garbage_bag_requests` DISABLE KEYS */;
INSERT INTO `garbage_bag_requests` VALUES (20,'arel','2020-07-11 07:10:26','cityhall','2020-07-11 07:10:59',1),(21,'stan','2020-07-11 07:30:03','cityhall','2020-07-11 07:30:46',1),(22,'stan','2020-07-11 07:30:18','cityhall','2020-07-11 07:30:49',1),(23,'stan','2020-07-11 07:40:51','cityhall','2020-07-11 07:41:13',1),(24,'wang','2020-07-11 16:12:34','cityhall','2020-07-11 16:13:48',1),(25,'jiho','2020-07-11 16:13:05','cityhall','2020-07-11 16:13:44',1),(26,'nicholas','2020-07-12 07:55:53','cityhall','2020-07-12 08:04:06',1),(27,'nicholas','2020-07-12 07:56:57','cityhall','2020-07-12 08:03:45',1),(28,'nene','2020-07-12 07:57:32','cityhall','2020-07-12 08:03:51',1),(29,'nana','2020-07-12 07:58:18','cityhall','2020-07-12 08:03:55',1),(30,'lee','2020-07-12 07:58:56','cityhall','2020-07-12 08:03:59',1),(31,'john','2020-07-12 07:59:57','cityhall','2020-07-12 08:05:14',1),(32,'alto','2020-07-12 08:00:51','cityhall','2020-07-12 08:05:04',1),(33,'blanc','2020-07-12 08:01:26','cityhall','2020-07-12 08:04:53',1),(34,'luca','2020-07-12 08:02:19','cityhall','2020-07-12 08:04:37',1),(35,'nome','2020-07-12 08:03:07','cityhall','2020-07-12 08:04:12',1),(36,'nene','2020-07-12 08:08:34','cityhall','2020-07-12 08:50:29',1),(38,'zam','2020-07-12 08:39:23','cityhall','2020-07-12 08:42:11',1),(39,'arel','2020-07-12 08:51:14','',NULL,0),(40,'wang','2020-07-12 08:51:46','',NULL,0),(42,'zam','2020-07-12 09:05:57','cityhall','2020-07-12 09:09:08',1),(43,'zam','2020-07-25 13:00:10','cityhall','2020-07-25 13:04:02',1);
/*!40000 ALTER TABLE `garbage_bag_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garbage_bag_tradings`
--

DROP TABLE IF EXISTS `garbage_bag_tradings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garbage_bag_tradings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `offered_by` varchar(20) NOT NULL,
  `offered_on` timestamp NULL DEFAULT NULL,
  `offered_to` varchar(20) NOT NULL,
  `amount` int NOT NULL DEFAULT '0',
  `accepted_on` timestamp NULL DEFAULT NULL,
  `has_accepted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `offered_by` (`offered_by`),
  KEY `offered_to` (`offered_to`),
  CONSTRAINT `garbage_bag_tradings_ibfk_1` FOREIGN KEY (`offered_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `garbage_bag_tradings_ibfk_2` FOREIGN KEY (`offered_to`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garbage_bag_tradings`
--

LOCK TABLES `garbage_bag_tradings` WRITE;
/*!40000 ALTER TABLE `garbage_bag_tradings` DISABLE KEYS */;
INSERT INTO `garbage_bag_tradings` VALUES (6,'arel','2020-07-11 07:38:04','stan',10,NULL,0),(7,'zam','2020-07-12 08:40:24','arel',5,'2020-07-12 08:52:23',1),(9,'zam','2020-07-12 09:07:07','stan',5,'2020-07-12 09:08:06',1),(10,'zam','2020-07-25 13:07:51','arel',10,NULL,0);
/*!40000 ALTER TABLE `garbage_bag_tradings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garbage_bag_types`
--

DROP TABLE IF EXISTS `garbage_bag_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garbage_bag_types` (
  `bag_type` varchar(20) NOT NULL,
  `hex_color_code` varchar(10) NOT NULL,
  PRIMARY KEY (`bag_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garbage_bag_types`
--

LOCK TABLES `garbage_bag_types` WRITE;
/*!40000 ALTER TABLE `garbage_bag_types` DISABLE KEYS */;
INSERT INTO `garbage_bag_types` VALUES ('Burnable','#33afff'),('PET Bottles','#8dff33'),('Plastics','#ff4f33');
/*!40000 ALTER TABLE `garbage_bag_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postal_data`
--

DROP TABLE IF EXISTS `postal_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postal_data` (
  `postal_code` varchar(7) NOT NULL,
  `town` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`postal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postal_data`
--

LOCK TABLES `postal_data` WRITE;
/*!40000 ALTER TABLE `postal_data` DISABLE KEYS */;
INSERT INTO `postal_data` VALUES ('5250001','Oroshimo-cho'),('5250002','Ashiura-cho'),('5250003','Natsuka-cho'),('5250004','Kamidera-cho'),('5250005','Shina-cho'),('5250006','Shinanaka-cho'),('5250007','Shimodera-cho'),('5250011','Kataoka-cho'),('5250012','Anamura-cho'),('5250013','Shindo-cho'),('5250014','Komaizawa-cho'),('5250015','Atsumari-cho'),('5250016','Kitaogaya-cho'),('5250021','Kawara'),('5250022','Kawara-cho'),('5250023','Hirai'),('5250024','Hirai-cho'),('5250025','Nishishibukawa'),('5250026','Shibukawa'),('5250027','No-mura'),('5250028','Kamigasa'),('5250029','Shimogasa-cho'),('5250031','Wakatake-cho'),('5250032','Oji'),('5250033','Higashikusatsu'),('5250034','Kusatsu'),('5250035','Nishikusatsu'),('5250036','Kusatsu-cho'),('5250037','Nishioji-cho'),('5250041','Aoji-cho'),('5250042','Yamadera-cho'),('5250043','Bamba-cho'),('5250044','Okamoto-cho'),('5250045','Wakakusa'),('5250047','Oiwake'),('5250048','Oiwakeminami'),('5250050','Minamikusatsu'),('5250051','Kinokawa-cho'),('5250052','Nishiyagura'),('5250053','Yagura'),('5250054','Higashiyagura'),('5250055','Noji-cho'),('5250056','Minamigasa-cho'),('5250057','Sakuragaoka'),('5250058','Nojihigashi'),('5250059','Noji'),('5250061','Kitayamada-cho'),('5250062','Yamada-cho'),('5250063','Minamiyamada-cho'),('5250064','Mikura-cho'),('5250065','Hashioka-cho'),('5250066','Yabase-cho'),('5250067','Shinhama-cho'),('5250071','Minamigasahigashi'),('5250072','Kasayama');
/*!40000 ALTER TABLE `postal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_items`
--

DROP TABLE IF EXISTS `request_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_items` (
  `request_id` int NOT NULL,
  `bag_type` varchar(20) NOT NULL,
  `amount` int NOT NULL DEFAULT '0',
  KEY `request_id` (`request_id`),
  KEY `bag_type` (`bag_type`),
  CONSTRAINT `request_items_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `garbage_bag_requests` (`id`) ON DELETE CASCADE,
  CONSTRAINT `request_items_ibfk_2` FOREIGN KEY (`bag_type`) REFERENCES `garbage_bag_types` (`bag_type`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_items`
--

LOCK TABLES `request_items` WRITE;
/*!40000 ALTER TABLE `request_items` DISABLE KEYS */;
INSERT INTO `request_items` VALUES (20,'Burnable',10),(20,'Plastics',5),(21,'Burnable',5),(21,'PET Bottles',5),(21,'Plastics',5),(22,'Plastics',10),(23,'Burnable',15),(24,'Burnable',10),(24,'PET Bottles',5),(25,'Burnable',5),(25,'PET Bottles',5),(25,'Plastics',5),(26,'PET Bottles',5),(26,'Plastics',8),(27,'Burnable',15),(28,'Burnable',3),(28,'PET Bottles',3),(28,'Plastics',3),(29,'Burnable',6),(29,'PET Bottles',3),(29,'Plastics',6),(30,'Burnable',2),(30,'PET Bottles',1),(30,'Plastics',5),(31,'Burnable',8),(31,'Plastics',5),(32,'Burnable',3),(32,'Plastics',9),(33,'Burnable',3),(33,'PET Bottles',7),(34,'Burnable',10),(34,'Plastics',5),(35,'Burnable',4),(35,'PET Bottles',4),(35,'Plastics',4),(36,'Plastics',5),(38,'Burnable',7),(38,'PET Bottles',5),(38,'Plastics',3),(39,'Burnable',7),(40,'Plastics',5),(42,'Burnable',5),(42,'PET Bottles',5),(42,'Plastics',5),(43,'Burnable',3);
/*!40000 ALTER TABLE `request_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('Administrator'),('City Hall'),('Resident');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(20) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_name`),
  KEY `role_name` (`role_name`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_name`) REFERENCES `roles` (`role_name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('admin','Administrator'),('cityhall','City Hall'),('admin','Resident'),('alto','Resident'),('arel','Resident'),('blanc','Resident'),('jiho','Resident'),('john','Resident'),('lee','Resident'),('luca','Resident'),('nana','Resident'),('nene','Resident'),('nicholas','Resident'),('nome','Resident'),('ozma','Resident'),('stan','Resident'),('wang','Resident'),('zam','Resident');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `postcode` varchar(8) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(100) NOT NULL,
  `prefecture` varchar(100) NOT NULL,
  `household_size` int NOT NULL DEFAULT '1',
  `bags_remaining` int NOT NULL DEFAULT '0',
  `registered_on` timestamp NULL DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `prev_login` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Administrator','','','','','',1,0,'2020-06-27 11:10:55',NULL,NULL),('alto','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Alto Saotome','','525-0050','Minami Kusatsu 3 chome 12-1','Kusatsu-shi','Shiga-ken',1,78,'2020-07-12 07:32:35',NULL,NULL),('arel','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Test User 1','','525-0058','Minami Kusatsu Noji Higashi 6 Chome 1-18  Felicia Usagi #1004','Kusatsu City','Shiga-ken',1,63,'2020-07-11 07:08:20',NULL,NULL),('blanc','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Michael Blanc','','525-0034','Kusatsu 4 Chome 1-26','Kusatsu-shi','Shiga-ken',1,80,'2020-07-12 07:40:45',NULL,NULL),('cityhall','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','City Hall 1','','','','','',1,90,'2020-07-04 06:05:38',NULL,NULL),('jiho','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Test User 2','','5250058','6-chÅme-5-14 Nojihigashi','Kusatsu','Shiga',1,75,'2020-07-11 16:11:37',NULL,NULL),('john','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','John Doe','','525-0034','Kusatsu 4 Chome 1-26','Kusatsu-shi','Shiga-ken',1,77,'2020-07-12 07:21:05',NULL,NULL),('lee','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Ranka Lee','','525-0058','6-chÅme-5-14 Nojihigashi','Kusatsu-shi','Shiga-ken',1,82,'2020-07-12 07:33:41',NULL,NULL),('luca','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Luca Angelloni','','525-0058','Minami Kusatsu Noji Higashi 6 Chome 1-18','Kusatsu-shi','Shiga-ken',1,75,'2020-07-12 07:42:10',NULL,NULL),('nana','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Nanase Matsuura','','525-0050','Minami Kusatsu 3 chome 12-1','Kusatsu-shi','Shiga-ken',1,75,'2020-07-12 07:43:32',NULL,NULL),('nene','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Nene Rora','','5250072','Kasayama','Kusatsu-shi','Shiga-ken',1,76,'2020-07-12 07:51:29',NULL,NULL),('nicholas','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Test User 3','','525-0071','Minami Gasa Higashi 1-12-24, Villa Copo Shimizu #116','Kusatsu-shi','Shiga-ken',1,62,'2020-07-12 07:14:41',NULL,NULL),('nome','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Sheryl Nome','','525-0071','Minami Gasa Higashi 1-12-24','Kusatsu-shi','Shiga-ken',1,78,'2020-07-12 07:35:52',NULL,NULL),('ozma','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Ozma Lee','','525-0071','Minami Gasa Higashi 1-12-24','Kusatsu-shi','Shiga-ken',1,90,'2020-07-12 07:44:54',NULL,NULL),('stan','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Test User 4','','525-0034','Kusatsu 4 Chome 1-26 Haitsu Miya no Mori 1315','Kusatsu-Shi','Shiga-ken',1,55,'2020-07-11 07:29:19',NULL,NULL),('wang','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Test User 5','','5250050','Minami Kusatsu 3 chome 12-1 Glory Minami Kusatsu 102','Kusatsu shi','Shiga ken',1,70,'2020-07-11 16:10:00',NULL,NULL),('zam','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','Shaiful Nizam','','525-0071','Minami Gasa Higashi 1-12-24, Villa Copo Shimizu #116','Kusatsu-shi','Shiga-ken',1,42,'2020-07-12 07:10:03',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-04 12:41:15
