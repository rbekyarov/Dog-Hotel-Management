CREATE DATABASE  IF NOT EXISTS `dog_hotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dog_hotel`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: dog_hotel
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `behaviors`
--

DROP TABLE IF EXISTS `behaviors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `behaviors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_create` date DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ohhe425xdkrh8nqvq9jrd5u2i` (`name`),
  KEY `FK1tund1m3rx1uinfmgjwt3ymoa` (`user_id`),
  CONSTRAINT `FK1tund1m3rx1uinfmgjwt3ymoa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `behaviors`
--

LOCK TABLES `behaviors` WRITE;
/*!40000 ALTER TABLE `behaviors` DISABLE KEYS */;
INSERT INTO `behaviors` VALUES (1,'2023-01-01','Good with dogs',1),(2,'2023-01-01','Good with dogs and humans',2),(3,'2023-01-01','Good with humans',2),(4,'2023-01-01','Bad with dogs',1),(5,'2023-01-01','Bad with dogs and humans',1),(6,'2023-01-01','Bad with humans',2);
/*!40000 ALTER TABLE `behaviors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `breeds`
--

DROP TABLE IF EXISTS `breeds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `breeds` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `breed_name` varchar(255) NOT NULL,
  `date_create` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_161eru7tn153wn8ph33grett1` (`breed_name`),
  KEY `FK69ad3nje7fv2nk4sreu9j5g56` (`user_id`),
  CONSTRAINT `FK69ad3nje7fv2nk4sreu9j5g56` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breeds`
--

LOCK TABLES `breeds` WRITE;
/*!40000 ALTER TABLE `breeds` DISABLE KEYS */;
INSERT INTO `breeds` VALUES (1,'Boxer','2023-01-01',1),(2,'German Shepherd','2023-01-01',1),(3,'Rottweiler','2023-01-01',2),(4,'Bulgarian Shepherd - BOK','2023-01-01',1),(5,'Miniature Schnauzer','2023-01-01',2),(6,'Husky','2023-01-01',1),(7,'Yorkshire Terrier','2023-01-01',1),(8,'Dakel','2023-01-01',1),(9,'Pit Bull Terrier','2023-01-20',1),(10,'American Bulldog','2023-01-17',1),(11,'Jack Russell Terrier','2023-01-21',1),(12,'American Staffordshire Terrier','2023-01-20',1),(13,'Cane Corso','2023-01-20',1),(14,'Labrador Retriever','2023-01-21',2),(15,'Dobermann','2023-01-21',2),(16,'Golden Retriever','2023-01-27',1),(17,'Basset Hound','2023-01-27',1),(18,'French Bulldog','2023-01-29',1),(19,'Pug','2023-02-01',1),(20,'English Bulldog','2023-02-03',1),(21,'French Bolognese','2023-02-03',1),(22,'Cocker Spaniels ','2023-02-04',2),(23,'Beagle','2023-02-04',2),(24,'Dalmatian','2023-02-04',2);
/*!40000 ALTER TABLE `breeds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cells`
--

DROP TABLE IF EXISTS `cells`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cells` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cell_size` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `date_create` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jrtbir7hw6mqwmxg3p1qbckx4` (`code`),
  KEY `FKgbuqo2aooq8e7konitl43ib98` (`user_id`),
  CONSTRAINT `FKgbuqo2aooq8e7konitl43ib98` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cells`
--

LOCK TABLES `cells` WRITE;
/*!40000 ALTER TABLE `cells` DISABLE KEYS */;
INSERT INTO `cells` VALUES (1,'SMALL','S1','2023-03-15','empty',2),(2,'SMALL','S2','2023-03-09','empty',1),(3,'SMALL','S3','2023-03-15','empty',2),(4,'MEDIUM','M1','2023-03-15','empty',2),(5,'MEDIUM','M2','2023-02-13','empty',1),(6,'MEDIUM','M3','2023-02-01','empty',1),(7,'LARGE','L1','2023-02-19','empty',2),(8,'LARGE','L2','2023-02-13','empty',1),(9,'LARGE','L3','2023-03-03','empty',1),(10,'SMALL','S4','2023-01-27','under_repair',1),(11,'LARGE','L4','2023-02-19','empty',2),(12,'MEDIUM','M4','2023-03-12','empty',2),(13,'SMALL','S5','2023-03-15','empty',2);
/*!40000 ALTER TABLE `cells` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `date_create` date DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qww1g66rmhx352jxut53oqh3y` (`code`),
  KEY `FKkkg38v9k33cltq7h3406ydw79` (`user_id`),
  CONSTRAINT `FKkkg38v9k33cltq7h3406ydw79` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'1000','2023-01-01','Sofia',2),(2,'2000','2023-01-01','Plovdiv',1),(3,'6000','2023-01-01','Stara Zagora',1),(4,'9000','2023-01-01','Varna',2),(5,'7008','2023-01-01','Ruse',1),(6,'8800','2023-01-27','Sliven',1),(7,'8000','2023-01-29','Burgas',2);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_srv16ica2c1csub334bxjjb59` (`email`),
  KEY `FKtiuqdledq2lybrds2k3rfqrv4` (`user_id`),
  KEY `FKhvj87763ko5y5uukwc6wyf0v6` (`city_id`),
  CONSTRAINT `FKhvj87763ko5y5uukwc6wyf0v6` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  CONSTRAINT `FKtiuqdledq2lybrds2k3rfqrv4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Lulin 15','2023-02-14','ivanov@gmail.com','Ivan','Ivanov','0886335241',1,1,'VIP'),(2,'Zdravetzh 4','2023-02-14','spavlov@abv.bg','Stanimir','Pavlov','08842276361',1,2,'REGULAR'),(3,'Zagorka 16','2023-01-01','vgeorgiev@outlook.com','Vladimir','Georgiev','0887325579',1,3,'NEW'),(4,'Ludogorska 12B','2023-02-10','bdimitrov@hotmail.com','Boyan','Dimitrov','0881256377',1,4,'VIP'),(5,'Vasil Levski 22','2023-02-13','yatanasov@abv.bg','Yordan','Atanasov','0867258954',1,1,'VIP'),(6,'Hristo Botev 24','2023-02-13','penev23@abv.bg','Viktor','Penev','08865578956',1,2,'REGULAR'),(7,'Struma 26','2023-01-17','ra_mb@abv.bg','Dimitar','Stanev','0887322541',1,5,'NEW'),(9,'Vuzrajdane 33','2023-01-20','rdimov15@gmail.com','Rumen','Dimov','0885463197',1,4,'NEW'),(10,'Lulin 121','2023-02-13','ddikov@abv.bg','Dancho','Dikov','0884556778',1,1,'REGULAR'),(11,'Dunav 12','2023-01-21','jivko85@abv.bg','Jivko','Mihalev','0887663411',2,5,'NEW'),(12,'Parchevich 14','2023-01-21','anton66@gmail.com','Anton','Hristov','0883649785',2,3,'NEW'),(13,'Burgaska 7','2023-01-27','kk33@abv.bg','Kaloyan','Kostadinov','0886332447',1,6,'NEW'),(14,'Cherno More 66','2023-02-10','vspasov@outlook.com','Velizar','Spasov','0887654321',1,7,'NEW'),(15,'Hristo Botev 70','2023-01-29','sivanova44@gmail.com','Stela','Ivanova','0887531246',1,3,'NEW'),(16,'Vitosha 1','2023-02-03','dari66@gmail.com','Darina','Hristova','0887332000',1,1,'NEW'),(17,'Shabla 58','2023-02-10','drusenov@gmail.com','Dragomir','Rusenov','0884665522',1,4,'NEW');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logo_name` varchar(255) DEFAULT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `vat_number` varchar(255) DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKie62fn0rnmuenspjh789l8e9f` (`city_id`),
  CONSTRAINT `FKie62fn0rnmuenspjh789l8e9f` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Tzar Simeon Veliki 1',5115.69,'BG18RZBB91550123456789','Bulgaria Bank','Bulgaria','office@dhm.bg','logo.png','Ivan Petrov','DHM Ltd','BG030298796',3);
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costs`
--

DROP TABLE IF EXISTS `costs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `costs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `date_cost` date DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `vendor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9m7cqyahiqkwjb3ppk376rioj` (`user_id`),
  KEY `FKtjqj6ly1ccwr9qyfrfs2m5wev` (`vendor_id`),
  CONSTRAINT `FK9m7cqyahiqkwjb3ppk376rioj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtjqj6ly1ccwr9qyfrfs2m5wev` FOREIGN KEY (`vendor_id`) REFERENCES `vendors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costs`
--

LOCK TABLES `costs` WRITE;
/*!40000 ALTER TABLE `costs` DISABLE KEYS */;
INSERT INTO `costs` VALUES (1,44.26,'2023-01-01','2023-01-06','lumber 5 pies','365323',2,4),(2,55.50,'2023-01-02','2023-01-06','Water fee','546666662',1,2),(3,21.00,'2023-01-02','2023-01-06','Gsm fee','8787464',2,1),(4,191.99,'2023-01-03','2023-01-06','Dog food','84312',1,3),(5,94.60,'2023-01-04','2023-01-06','Cosmetics for dogs','116343',1,5),(6,200.01,'2023-01-06','2023-01-06','Construction materials','873123',2,4),(7,40.00,'2023-01-22','2023-01-22','Dog food ','67854336',1,5),(8,60.00,'2023-01-24','2023-01-25','Dog Toys','476465645',1,3),(9,21.00,'2023-01-31','2023-01-31','Gsm fee','534535656',1,1),(11,60.20,'2023-02-01','2023-02-04','Water fee','435454',1,2),(12,158.00,'2023-02-13','2023-02-13','Dog food','65454675',1,5),(13,180.80,'2023-03-03','2023-03-04','Cosmetics for dogs','5654466',1,3);
/*!40000 ALTER TABLE `costs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dogs`
--

DROP TABLE IF EXISTS `dogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dogs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `dog_size` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `last_deworming_date` date DEFAULT NULL,
  `microchip` varchar(255) DEFAULT NULL,
  `microchip_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `passport` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `years` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `behavior_id` bigint DEFAULT NULL,
  `breed_id` bigint DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsk4f0m5le3o4n8mjugyribthu` (`user_id`),
  KEY `FK4nl6gbypbidrtaqidkk8pwqvw` (`behavior_id`),
  KEY `FK16mwq5mge3ifihf8a76sj8jjw` (`breed_id`),
  KEY `FK4r9ett3btd0316prt9v86ft97` (`client_id`),
  CONSTRAINT `FK16mwq5mge3ifihf8a76sj8jjw` FOREIGN KEY (`breed_id`) REFERENCES `breeds` (`id`),
  CONSTRAINT `FK4nl6gbypbidrtaqidkk8pwqvw` FOREIGN KEY (`behavior_id`) REFERENCES `behaviors` (`id`),
  CONSTRAINT `FK4r9ett3btd0316prt9v86ft97` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKsk4f0m5le3o4n8mjugyribthu` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dogs`
--

LOCK TABLES `dogs` WRITE;
/*!40000 ALTER TABLE `dogs` DISABLE KEYS */;
INSERT INTO `dogs` VALUES (1,'2018-07-15','2023-02-01','LARGE','Boxer-dog.jpg','2023-01-20','NO','','Jerry','YES','M',30,'yrs.4 mos.8',1,1,1,1),(2,'2022-03-15','2023-01-01','SMALL','tuti.jpg','2023-04-01','YES','85858585864','Tuti','YES','M',3,'yrs.1 mos.0',1,2,7,5),(3,'2021-06-11','2023-01-22','SMALL','dachshund-6934445_960_720.jpg','2022-12-06','YES','4155261523','Dido','YES','M',9,'yrs.1 mos.9',1,4,8,6),(4,'2022-02-22','2023-01-01','MEDIUM','sara.jpg','2023-01-29','NO','','Sara','YES','F',18,'yrs.1 mos.0',2,6,9,5),(5,'2019-03-26','2023-01-29','LARGE','578-325-rotvajler.jpg','2023-03-02','YES','85866864858','Berta','YES','F',33,'yrs.3 mos.12',1,5,3,1),(6,'2017-06-10','2023-01-29','LARGE','Image_5873126_811_0.jpg','2023-02-12','NO','','Boby','NO','M',40,'yrs.5 mos.9',1,2,4,1),(7,'2020-04-16','2023-01-29','LARGE','nemska-ovcharka1.jpg','2023-01-08','YES','17475567666772','Cezar','YES','M',35,'yrs.2 mos.11',1,4,2,3),(8,'2022-09-16','2023-01-29','MEDIUM','Boxer-puppy-sitting-in-the-grass.null_.jpg','2023-02-12','NO','','Zara','YES','F',15,'mos.6',1,1,1,2),(9,'2020-05-28','2023-01-29','SMALL','Untitled-design-2021-10-22T222329.601.jpg','2023-02-27','YES','7447364142','Benji','YES','M',10,'yrs.2 mos.9',1,1,5,2),(10,'2016-02-07','2023-01-01','LARGE','Lora.jpg','2023-03-02','NO','','Lora','YES','F',25,'yrs.7 mos.1',1,5,6,4),(11,'2020-06-09','2023-01-19','MEDIUM','American-Bulldog.jpg','2023-02-27','YES','564567657758','Rusty','YES','M',16,'yrs.2 mos.9',1,3,10,7),(13,'2020-07-15','2023-01-20','SMALL','image-55.jpg','2023-01-01','YES','5554577963','Zarko','YES','M',8,'yrs.2 mos.8',1,2,11,9),(14,'2019-09-22','2023-01-20','MEDIUM','ancestry-American-Staffordshire-Terrier-bulldogs-Staffie-mastiffs.jpg','2023-02-26','NO','','Jaro','NO','M',19,'yrs.3 mos.6',1,4,12,10),(15,'2018-06-12','2023-01-20','LARGE','250px-CaneCorso_(23).jpg','2023-03-26','NO','','Max','NO','M',40,'yrs.4 mos.9',1,4,13,10),(16,'2020-08-28','2023-01-27','LARGE','golden-retriever.webp','2023-02-18','YES','31886733518','Klara','YES','F',28,'yrs.2 mos.6',1,2,16,11),(17,'2019-10-15','2023-01-22','LARGE','Doberman-10.jpg','2023-02-27','YES','3458846443254','Goliat','YES','M',34,'yrs.3 mos.5',8,6,15,12),(18,'2020-05-12','2023-01-27','LARGE','labrador_Toby.jpg','2023-02-18','YES','545454511','Toby','YES','M',33,'yrs.2 mos.10',1,2,14,11),(19,'2019-04-10','2023-01-27','SMALL','Basset-Hound.jpg','2023-03-02','NO','','Tino','YES','M',9,'yrs.3 mos.11',1,1,17,13),(20,'2019-09-12','2023-01-29','LARGE','GermanShepherd-1000x600.jpg','2023-02-13','YES','7378393837','Roki','YES','M',35,'yrs.3 mos.6',2,4,2,14),(21,'2022-07-12','2023-01-29','SMALL','puppy-french-bulldog.jpg','2023-02-21','YES','73738383763','Boo','YES','F',6,'mos.8',1,2,18,15),(22,'2019-09-12','2023-02-01','SMALL','mops-kuche-poroda.jpg','2023-02-18','YES','74738833636','Bella','YES','F',8,'yrs.3 mos.6',1,4,19,15),(23,'2020-10-15','2023-02-03','MEDIUM','buldog_angielski_-_rasy_psw.jpg.webp','2023-03-02','NO','','Bily','YES','M',13,'yrs.2 mos.5',1,2,20,13),(24,'2020-02-18','2023-02-03','SMALL','vse-o-bolonkah-27.jpg','2023-02-17','YES','99383737383','Kari','YES','F',7,'yrs.3 mos.1',1,4,21,16),(25,'2022-07-06','2023-02-04','SMALL','Cocker-Spaniel_FeaturedImage-1024x615.webp','2023-02-18','YES','3454881515','Dara','YES','F',10,'mos.8',2,2,22,16),(26,'2020-10-13','2023-02-04','SMALL','Beagle_Featured-Image-1024x615.webp','2023-02-17','NO','','Marti','NO','M',10,'yrs.2 mos.5',2,1,23,17);
/*!40000 ALTER TABLE `dogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoces`
--

DROP TABLE IF EXISTS `invoces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoces` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author_name` varchar(255) DEFAULT NULL,
  `bathing` varchar(255) DEFAULT NULL,
  `bathing_price` decimal(19,2) DEFAULT NULL,
  `cancellation_invoice` varchar(255) DEFAULT NULL,
  `cancelled_date_invoice` date DEFAULT NULL,
  `cell_code` varchar(255) DEFAULT NULL,
  `client_address` varchar(255) DEFAULT NULL,
  `client_city_name` varchar(255) DEFAULT NULL,
  `client_email` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_phone` varchar(255) DEFAULT NULL,
  `combing` varchar(255) DEFAULT NULL,
  `combing_price` decimal(19,2) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `company_bank_account` varchar(255) DEFAULT NULL,
  `company_bank_name` varchar(255) DEFAULT NULL,
  `company_city_name` varchar(255) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `company_manager_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_vat_number` varchar(255) DEFAULT NULL,
  `count_stay` int DEFAULT NULL,
  `count_stay_price` decimal(19,2) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `deworming` varchar(255) DEFAULT NULL,
  `deworming_price` decimal(19,2) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `dog_name` varchar(255) DEFAULT NULL,
  `ears` varchar(255) DEFAULT NULL,
  `ears_price` decimal(19,2) DEFAULT NULL,
  `food` varchar(255) DEFAULT NULL,
  `food_price` decimal(19,2) DEFAULT NULL,
  `nails` varchar(255) DEFAULT NULL,
  `nails_price` decimal(19,2) DEFAULT NULL,
  `paws` varchar(255) DEFAULT NULL,
  `paws_price` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `reservation_id` bigint DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `training` varchar(255) DEFAULT NULL,
  `training_price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoces`
--

LOCK TABLES `invoces` WRITE;
/*!40000 ALTER TABLE `invoces` DISABLE KEYS */;
INSERT INTO `invoces` VALUES (1,'admin','NO',7.00,'NO','2023-01-07','L1','Lulin 15','Sofia','ivanov@gmail.com','Ivan Ivanov','0886335241','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',2,20.00,'2023-01-06','NO',10.00,5,'Boby','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,60.00,1,57.00,'NO',8.00),(2,'admin','NO',7.00,'NO','2023-01-07','B2','Zagorka 16','Stara Zagora','vgeorgiev@outlook.com','Vladimir Georgiev','0887325579','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,20.00,'2023-01-06','NO',10.00,0,'Cezar','NO',5.00,'NO',10.00,'NO',5.00,'NO',5.00,128.00,3,128.00,'YES',8.00),(3,'admin','NO',7.00,'YES','2023-01-07','C1','Zdravetzh 4','Plovdiv','spavlov@abv.bg','Stanimir Pavlov','08842276361','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',13,20.00,'2023-01-09','NO',10.00,20,'Zara','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,398.00,5,318.40,'NO',8.00),(4,'owner','NO',7.00,'NO',NULL,'L1','Ludogorska 12B','Varna','bdimitrov@hotmail.com','Boyan Dimitrov','0881256377','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',1,20.00,'2023-01-16','NO',10.00,20,'Cezar','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,38.00,6,30.40,'YES',8.00),(5,'owner','YES',7.00,'NO',NULL,'M3','Ludogorska 12B','Varna','bdimitrov@hotmail.com','Boyan Dimitrov','0881256377','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',1,15.00,'2023-01-16','NO',10.00,8,'Cezar','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,67.00,4,61.64,'YES',8.00),(6,'owner','YES',7.00,'NO',NULL,'L2','Ludogorska 12B','Varna','bdimitrov@hotmail.com','Boyan Dimitrov','0881256377','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',10,20.00,'2023-01-17','YES',10.00,8,'Lora','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,347.00,9,319.24,'YES',8.00),(7,'owner2','NO',7.00,'YES',NULL,'L2','Client1Client1Client1','Sofia','Client1@gmail.com','Client1 Client1','6767676767676','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',1,20.00,'2023-01-19','YES',10.00,6,'Dog for Client 1','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,48.00,13,45.12,'YES',8.00),(8,'owner','NO',7.00,'NO',NULL,'M1','Zdravetzh 4','Plovdiv','spavlov@abv.bg','Stanimir Pavlov','08842276361','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',2,15.00,'2023-01-20','NO',10.00,0,'Zara','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,58.00,10,58.00,'YES',8.00),(9,'owner','NO',7.00,'NO',NULL,'S2','Hristo Botev 24','Plovdiv','penev23@abv.bg','Viktor Penev','08865578956','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',11,12.00,'2023-01-21','NO',10.00,5,'Dido','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,250.00,8,225.00,'YES',8.00),(10,'owner','YES',7.00,'NO',NULL,'L1','Zdravetzh 4','Plovdiv','spavlov@abv.bg','Stanimir Pavlov','08842276361','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,20.00,'2023-01-21','NO',10.00,10,'Boby','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,217.00,2,195.30,'YES',8.00),(11,'owner','YES',7.00,'NO',NULL,'L3','Lulin 121','Sofia','ddikov@abv.bg','Dancho Dikov','0884556778','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',3,20.00,'2023-01-26','YES',10.00,3,'Max','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,137.00,19,132.89,'YES',8.00),(12,'owner','YES',7.00,'NO',NULL,'S2','Hristo Botev 24','Plovdiv','penev23@abv.bg','Viktor Penev','08865578956','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',3,12.00,'2023-01-26','NO',10.00,0,'Dido','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,81.00,20,81.00,'YES',8.00),(13,'owner','NO',7.00,'NO',NULL,'M1','Lulin 121','Sofia','ddikov@abv.bg','Dancho Dikov','0884556778','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',2,15.00,'2023-01-26','NO',10.00,0,'Jaro','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,58.00,15,58.00,'YES',8.00),(14,'owner','YES',7.00,'NO',NULL,'L3','Parchevich 14','Stara Zagora','anton66@gmail.com','Anton Hristov','0883649785','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',2,20.00,'2023-01-26','YES',10.00,0,'Goliat','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,85.00,17,85.00,'YES',8.00),(15,'owner','YES',7.00,'NO',NULL,'L2','Lulin 15','Sofia','ivanov@gmail.com','Ivan Ivanov','0886335241','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',5,20.00,'2023-01-26','YES',10.00,0,'Jerry','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,197.00,11,197.00,'YES',8.00),(16,'owner','YES',7.00,'NO',NULL,'S3','Vasil Levski 22','Sofia','yatanasov@abv.bg','Yordan Atanasov','0867258954','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',15,12.00,'2023-01-27','NO',10.00,5,'Tuti','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,367.00,7,348.65,'YES',8.00),(17,'owner','YES',7.00,'NO',NULL,'M3','Struma 26','Ruse','ra_mb@abv.bg','Dimitar Stanev','0887322541','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',4,15.00,'2023-01-29','YES',10.00,6,'Rusty','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,147.00,12,138.18,'YES',8.00),(18,'owner','YES',7.00,'NO',NULL,'M2','Vasil Levski 22','Sofia','yatanasov@abv.bg','Yordan Atanasov','0867258954','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',13,15.00,'2023-02-12','YES',10.00,10,'Sara','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,372.00,22,334.80,'YES',8.00),(19,'owner','NO',7.00,'NO',NULL,'S3','Vuzrajdane 33','Varna','rdimov15@gmail.com','Rumen Dimov','0885463197','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,12.00,'2023-02-12','NO',10.00,5,'Zarko','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,132.00,16,125.40,'NO',8.00),(20,'owner','NO',7.00,'NO',NULL,'L3','Zagorka 16','Stara Zagora','vgeorgiev@outlook.com','Vladimir Georgiev','0887325579','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',10,20.00,'2023-02-12','NO',10.00,0,'Cezar','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,308.00,14,308.00,'YES',8.00),(21,'owner','NO',7.00,'NO',NULL,'L1','Lulin 15','Sofia','ivanov@gmail.com','Ivan Ivanov','0886335241','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',13,20.00,'2023-02-12','NO',10.00,20,'Berta','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,299.00,5,239.20,'NO',8.00),(22,'owner','YES',7.00,'NO',NULL,'S1','Zdravetzh 4','Plovdiv','spavlov@abv.bg','Stanimir Pavlov','08842276361','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',14,12.00,'2023-02-16','YES',10.00,10,'Benji','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,355.00,25,319.50,'YES',8.00),(23,'owner','YES',7.00,'NO',NULL,'S2','Burgaska 7','Sliven','kk33@abv.bg','Kaloyan Kostadinov','0886332447','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',14,12.00,'2023-02-16','YES',10.00,15,'Tino','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,355.00,28,301.75,'YES',8.00),(24,'employee','NO',7.00,'NO',NULL,'L4','Dunav 12','Ruse','jivko85@abv.bg','Jivko Mihalev','0887663411','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,20.00,'2023-02-19','YES',10.00,0,'Toby','NO',5.00,'NO',10.00,'NO',5.00,'NO',5.00,138.00,26,138.00,'YES',8.00),(25,'employee','NO',7.00,'NO',NULL,'L1','Lulin 15','Sofia','ivanov@gmail.com','Ivan Ivanov','0886335241','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,20.00,'2023-02-19','YES',10.00,5,'Boby','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,198.00,24,188.10,'YES',8.00),(26,'employee','NO',7.00,'NO',NULL,'L3','Dunav 12','Ruse','jivko85@abv.bg','Jivko Mihalev','0887663411','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,20.00,'2023-02-20','YES',10.00,0,'Klara','NO',5.00,'NO',10.00,'NO',5.00,'NO',5.00,138.00,18,138.00,'YES',8.00),(27,'owner','NO',7.00,'NO',NULL,'M1','Zdravetzh 4','Plovdiv','spavlov@abv.bg','Stanimir Pavlov','08842276361','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,15.00,'2023-02-26','YES',10.00,5,'Zara','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,168.00,27,159.60,'YES',8.00),(28,'owner','NO',7.00,'NO',NULL,'L3','Lulin 15','Sofia','ivanov@gmail.com','Ivan Ivanov','0886335241','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,20.00,'2023-02-26','NO',10.00,5,'Jerry','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,188.00,23,178.60,'YES',8.00),(29,'owner','NO',7.00,'NO',NULL,'L2','Cherno More 66','Burgas','vspasov@outlook.com','Velizar Spasov','0887654321','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',13,20.00,'2023-03-02','YES',10.00,5,'Roki','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,408.00,29,387.60,'YES',8.00),(30,'owner','YES',7.00,'NO',NULL,'S2','Hristo Botev 70','Stara Zagora','sivanova44@gmail.com','Stela Ivanova','0887531246','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,12.00,'2023-03-04','YES',10.00,0,'Boo','YES',5.00,'YES',10.00,'NO',5.00,'NO',5.00,162.00,30,162.00,'YES',8.00),(31,'owner','NO',7.00,'NO',NULL,'M1','Burgaska 7','Sliven','kk33@abv.bg','Kaloyan Kostadinov','0886332447','NO',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,15.00,'2023-03-06','YES',10.00,0,'Bily','NO',5.00,'YES',10.00,'NO',5.00,'NO',5.00,168.00,32,168.00,'YES',8.00),(32,'employee','YES',7.00,'NO',NULL,'M4','Lulin 121','Sofia','ddikov@abv.bg','Dancho Dikov','0884556778','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',13,15.00,'2023-03-12','YES',10.00,5,'Jaro','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,372.00,36,353.40,'YES',8.00),(33,'employee','YES',7.00,'NO',NULL,'S3','Vitosha 1','Sofia','dari66@gmail.com','Darina Hristova','0887332000','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',9,12.00,'2023-03-12','YES',10.00,5,'Kari','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,245.00,33,232.75,'YES',8.00),(34,'employee','YES',7.00,'NO',NULL,'S1','Hristo Botev 70','Stara Zagora','sivanova44@gmail.com','Stela Ivanova','0887531246','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',8,12.00,'2023-03-12','YES',10.00,0,'Bella','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,223.00,31,223.00,'YES',8.00),(35,'owner','YES',7.00,'NO',NULL,'S2','Vitosha 1','Sofia','dari66@gmail.com','Darina Hristova','0887332000','YES',7.00,'Tzar Simeon Veliki 1','BG18RZBB91550123456789','Bulgaria Bank','Stara Zagora','office@dhm.bg','Ivan Petrov','DHM Ltd','BG030298796',6,12.00,'2023-03-19','YES',10.00,5,'Dara','YES',5.00,'YES',10.00,'YES',5.00,'YES',5.00,179.00,34,170.05,'YES',8.00);
/*!40000 ALTER TABLE `invoces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_create` date DEFAULT NULL,
  `price_bathing` decimal(19,2) DEFAULT NULL,
  `price_combing` decimal(19,2) DEFAULT NULL,
  `price_deworming` decimal(19,2) DEFAULT NULL,
  `price_ears` decimal(19,2) DEFAULT NULL,
  `price_food` decimal(19,2) DEFAULT NULL,
  `price_nails` decimal(19,2) DEFAULT NULL,
  `price_paws` decimal(19,2) DEFAULT NULL,
  `price_stayl` decimal(19,2) DEFAULT NULL,
  `price_staym` decimal(19,2) DEFAULT NULL,
  `price_stays` decimal(19,2) DEFAULT NULL,
  `price_training` decimal(19,2) DEFAULT NULL,
  `discount_client_regular` double DEFAULT NULL,
  `discount_client_vip` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (1,'2023-01-01',5.00,5.00,10.00,5.00,9.00,5.00,5.00,15.00,13.00,10.00,5.00,5,15),(2,'2023-02-11',7.00,7.00,10.00,5.00,10.00,5.00,5.00,20.00,15.00,12.00,8.00,5,15);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bathing` varchar(255) DEFAULT NULL,
  `combing` varchar(255) DEFAULT NULL,
  `count_overnight_stay` int DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `deworming` varchar(255) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `ears` varchar(255) DEFAULT NULL,
  `end_date` date NOT NULL,
  `food` varchar(255) DEFAULT NULL,
  `invoiced` varchar(255) DEFAULT NULL,
  `nails` varchar(255) DEFAULT NULL,
  `paws` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `start_date` date NOT NULL,
  `status_reservation` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `training` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `cell_id` bigint DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `dog_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb5g9io5h54iwl2inkno50ppln` (`user_id`),
  KEY `FK2cgvlwwe5iwocjflmel8vbb9p` (`cell_id`),
  KEY `FK6lekctbt4u88agg0b7cjsj6lf` (`client_id`),
  KEY `FKid8g0jk8m7yc9j8yxcvrp9lwr` (`company_id`),
  KEY `FK90kaalhgeanqt4bboww0b6iio` (`dog_id`),
  CONSTRAINT `FK2cgvlwwe5iwocjflmel8vbb9p` FOREIGN KEY (`cell_id`) REFERENCES `cells` (`id`),
  CONSTRAINT `FK6lekctbt4u88agg0b7cjsj6lf` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK90kaalhgeanqt4bboww0b6iio` FOREIGN KEY (`dog_id`) REFERENCES `dogs` (`id`),
  CONSTRAINT `FKb5g9io5h54iwl2inkno50ppln` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKid8g0jk8m7yc9j8yxcvrp9lwr` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'NO','NO',2,'2023-01-06','NO',5,'NO','2023-01-03','YES','YES','NO','NO',60.00,'2023-01-01','completed',57.00,'NO',1,7,1,1,3),(2,'YES','YES',6,'2023-01-06','NO',10,'YES','2023-01-21','YES','YES','YES','YES',217.00,'2023-01-15','completed',195.30,'YES',2,7,2,1,6),(3,'NO','NO',6,'2023-01-06','NO',0,'NO','2023-01-21','NO','YES','NO','NO',128.00,'2023-01-17','completed',128.00,'YES',2,5,3,1,4),(4,'YES','YES',1,'2023-01-06','NO',8,'YES','2023-01-07','YES','YES','YES','YES',67.00,'2023-01-06','completed',61.64,'YES',1,6,4,1,7),(5,'NO','NO',13,'2023-02-12','NO',20,'NO','2023-02-11','YES','YES','NO','NO',299.00,'2023-01-29','completed',239.20,'NO',1,7,1,1,5),(6,'NO','NO',1,'2023-01-07','NO',20,'NO','2023-01-07','YES','YES','NO','NO',38.00,'2023-01-06','completed',30.40,'YES',1,7,4,1,7),(7,'YES','YES',15,'2023-01-20','NO',5,'YES','2023-01-26','YES','YES','YES','YES',367.00,'2023-01-11','completed',348.65,'YES',1,3,5,1,2),(8,'NO','NO',11,'2023-01-09','NO',5,'NO','2023-01-20','YES','YES','NO','NO',250.00,'2023-01-09','completed',225.00,'YES',2,2,6,1,3),(9,'YES','YES',10,'2023-01-17','YES',8,'YES','2023-02-11','YES','YES','YES','YES',347.00,'2023-02-01','completed',319.24,'YES',1,8,4,1,10),(10,'NO','NO',2,'2023-01-17','NO',0,'NO','2023-01-19','YES','YES','NO','NO',58.00,'2023-01-17','completed',58.00,'YES',1,4,2,1,8),(11,'YES','YES',5,'2023-01-17','YES',0,'YES','2023-01-25','YES','YES','YES','YES',197.00,'2023-01-20','completed',197.00,'YES',1,8,1,1,1),(12,'YES','YES',4,'2023-01-19','YES',6,'YES','2023-01-28','YES','YES','YES','YES',147.00,'2023-01-24','completed',138.18,'YES',1,6,7,1,11),(14,'NO','NO',10,'2023-01-20','NO',0,'NO','2023-02-11','YES','YES','NO','NO',308.00,'2023-02-01','completed',308.00,'YES',1,9,3,1,7),(15,'NO','NO',2,'2023-01-20','NO',0,'NO','2023-01-25','YES','YES','NO','NO',58.00,'2023-01-23','completed',58.00,'YES',1,4,10,1,14),(16,'NO','NO',6,'2023-01-30','NO',5,'NO','2023-02-11','YES','YES','NO','NO',132.00,'2023-02-05','completed',125.40,'NO',1,3,9,1,13),(17,'YES','NO',2,'2023-01-23','YES',0,'NO','2023-01-28','YES','YES','NO','NO',85.00,'2023-01-26','completed',85.00,'YES',1,9,12,1,17),(18,'NO','NO',6,'2023-02-18','YES',0,'NO','2023-02-18','NO','YES','NO','NO',138.00,'2023-02-12','completed',138.00,'YES',2,9,11,1,16),(19,'YES','YES',3,'2023-01-21','YES',3,'YES','2023-01-25','YES','YES','YES','YES',137.00,'2023-01-22','completed',132.89,'YES',1,9,10,1,15),(20,'YES','NO',3,'2023-01-22','NO',0,'NO','2023-01-25','YES','YES','NO','NO',81.00,'2023-01-22','completed',81.00,'YES',1,2,6,1,3),(22,'YES','YES',13,'2023-01-25','YES',10,'YES','2023-02-11','YES','YES','YES','YES',372.00,'2023-01-29','completed',334.80,'YES',1,5,5,1,4),(23,'NO','NO',6,'2023-02-01','NO',5,'NO','2023-02-25','YES','YES','NO','NO',188.00,'2023-02-19','completed',178.60,'YES',1,9,1,1,1),(24,'NO','NO',6,'2023-01-27','YES',5,'NO','2023-02-18','YES','YES','NO','NO',198.00,'2023-02-12','completed',188.10,'YES',1,7,1,1,6),(25,'YES','YES',14,'2023-01-27','YES',10,'YES','2023-02-15','YES','YES','YES','YES',355.00,'2023-02-01','completed',319.50,'YES',1,1,2,1,9),(26,'NO','NO',6,'2023-02-18','YES',0,'NO','2023-02-18','NO','YES','NO','NO',138.00,'2023-02-12','completed',138.00,'YES',2,11,11,1,18),(27,'NO','NO',6,'2023-02-12','YES',5,'NO','2023-02-25','YES','YES','NO','NO',168.00,'2023-02-19','completed',159.60,'YES',1,4,2,1,8),(28,'YES','YES',14,'2023-02-12','YES',15,'YES','2023-02-15','YES','YES','YES','YES',355.00,'2023-02-01','completed',301.75,'YES',1,2,13,1,19),(29,'NO','NO',13,'2023-02-13','YES',5,'NO','2023-02-28','YES','YES','NO','NO',408.00,'2023-02-15','completed',387.60,'YES',1,8,14,1,20),(30,'YES','NO',6,'2023-02-21','YES',0,'YES','2023-03-03','YES','YES','NO','NO',162.00,'2023-02-25','completed',162.00,'YES',1,2,15,1,21),(31,'YES','YES',8,'2023-02-18','YES',0,'YES','2023-03-11','YES','YES','YES','YES',223.00,'2023-03-03','completed',223.00,'YES',2,1,15,1,22),(32,'NO','NO',6,'2023-02-12','YES',0,'NO','2023-03-04','YES','YES','NO','NO',168.00,'2023-02-26','completed',168.00,'YES',1,4,13,1,23),(33,'YES','YES',9,'2023-02-17','YES',5,'YES','2023-03-10','YES','YES','YES','YES',245.00,'2023-03-01','completed',232.75,'YES',2,3,16,1,24),(34,'YES','YES',6,'2023-02-18','YES',5,'YES','2023-03-18','YES','YES','YES','YES',179.00,'2023-03-12','completed',170.05,'YES',2,2,16,1,25),(35,'NO','NO',6,'2023-02-17','YES',0,'NO','2023-03-25','YES','NO','NO','NO',150.00,'2023-03-19','active',150.00,'YES',2,13,17,1,26),(36,'YES','YES',13,'2023-02-20','YES',5,'YES','2023-03-11','YES','YES','YES','YES',372.00,'2023-02-26','completed',353.40,'YES',2,12,10,1,14),(37,'YES','NO',6,'2023-02-27','YES',0,'NO','2023-03-25','YES','NO','YES','YES',215.00,'2023-03-19','active',215.00,'YES',2,7,12,1,17),(38,'YES','NO',6,'2023-02-27','YES',5,'YES','2023-04-01','YES','NO','NO','NO',210.00,'2023-03-26','upcoming',199.50,'YES',1,11,10,1,15),(39,'YES','YES',6,'2023-02-27','YES',5,'YES','2023-04-01','YES','NO','YES','YES',179.00,'2023-03-26','upcoming',170.05,'YES',2,3,2,1,9),(40,'YES','YES',14,'2023-02-27','YES',15,'YES','2023-04-15','YES','NO','YES','YES',355.00,'2023-04-01','upcoming',301.75,'YES',1,13,5,1,2),(41,'YES','YES',14,'2023-02-27','YES',3,'YES','2023-04-15','YES','NO','YES','YES',397.00,'2023-04-01','upcoming',385.09,'YES',2,6,7,1,11),(42,'YES','YES',7,'2023-03-02','YES',15,'YES','2023-04-11','YES','NO','YES','YES',257.00,'2023-04-04','upcoming',218.45,'YES',2,7,4,1,10),(43,'NO','NO',3,'2023-03-02','YES',4,'NO','2023-04-11','YES','NO','NO','NO',93.00,'2023-04-08','upcoming',89.28,'YES',1,5,13,1,23),(44,'YES','YES',3,'2023-03-02','YES',0,'YES','2023-04-08','YES','NO','YES','YES',113.00,'2023-04-05','upcoming',113.00,'YES',2,1,13,1,19),(45,'NO','NO',5,'2023-03-02','YES',15,'NO','2023-04-15','YES','NO','NO','NO',168.00,'2023-04-10','upcoming',142.80,'YES',2,8,1,1,5);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'939b5d9e8214ca6f2780373f7326268cea0899d520f2319411f9025804afec328b289329cb9f51c1','ADMIN','owner'),(2,'cd61498bd99aff16ccccb8d2a3929c64f9b61b808fd5bc6200efb8cd694843d7ad4aa47c8a6cf60d','USER','employee'),(3,'6b05c4a7f45b4810006f9a3f73b71ad647864dc958fc31ec79bb87a8842e084c5574792e3e4e81ac','USER','rest'),(7,'dcb4523dcd02cb289bca09dacddbd23b055106c5ada12276604dd62d4aaa18345d53d418007ff148','USER','Galin_P'),(8,'30b1cda959a74b9c966c04870a3123b5c4a817b9c58203a85c67cfa4ad09a3bf69415ecb2e7790a5','USER','pesho'),(9,'da6b54e494a18df9f75553d15de2e5c4e699263723b2b50681531cacc36f6486c979b11aa6f6a491','USER','bonkova'),(10,'64796b148c700254ac38dde0c65b277e893d96bb60d4b8f9831dcdac34711406d9820c6995ad7ac3','USER','rbekyarov'),(11,'5ae9ce08f6789ce95c6cf5333f8f78dc708cf8d9851c49fc4770d493c387cbfd80011d5af0d809ba','USER','Test'),(12,'ba3ec6472c06674d4a867bea7835a48147b7eaeb8bcb24e2d313de1ff5ed9b3a510524972173af64','USER','registered '),(13,'c924e45ee439dfea05438961155c1c92ebfe62af80948c2187fd27bb0bd5a0a2b637005aec6c065b','USER','koko90'),(14,'81d57e31b9cee4eb8ca2d12a0953fc63ea724d9b1c7010f4d93b447bf63490e83a7d1002192bbf96','USER','peter'),(15,'e8b225c7da97acd762493e4914298ff4aeeee5cc97f3b28aa6723e2b5e401c4f90b6b2a2e20e6ef9','USER','gosho'),(16,'e3c717e4ce8021bbb1d9e1ac2193c985290ee88b8ad4f6f191840664a4679f322eb73b6fc69801c6','USER','Username');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `vat_number` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiuqso7j7nivq7sb3v3v4j7ein` (`user_id`),
  KEY `FKc1l4hauvw3nfalvr0ma3b8gsa` (`city_id`),
  CONSTRAINT `FKc1l4hauvw3nfalvr0ma3b8gsa` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  CONSTRAINT `FKiuqso7j7nivq7sb3v3v4j7ein` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (1,'Blv Ivan Shishman 3','Bulgaria','2023-01-01','btk@btk.com','BTK Ltd','BG230234656',2,1),(2,'Ivan Vazov 8','Bulgaria','2023-01-01','vik-stz@vik.com','VIK Ltd','BG230364657',1,3),(3,'Hristo Botev 1','Bulgaria','2023-01-01','kaufland-bg@kaufland.com','Kaufland','BG537268593',2,3),(4,'Stoletov 31','Bulgaria','2023-01-01','bagira@bagira.bg','Bagira OOD','BG650765652',1,3),(5,'General Gurko 9','Bulgaria','2023-01-01','metro-stz@metro.com','METRO','BG486464798',2,3);
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-23  9:57:29
