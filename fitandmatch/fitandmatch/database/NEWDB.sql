/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - fitandmatch
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fitandmatch` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `fitandmatch`;

/*Table structure for table `category_table` */

DROP TABLE IF EXISTS `category_table`;

CREATE TABLE `category_table` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `category_table` */

insert  into `category_table`(`cat_id`,`category`) values 
(1,'Necklace'),
(2,'Earring'),
(4,'T shirt');

/*Table structure for table `customers_table` */

DROP TABLE IF EXISTS `customers_table`;

CREATE TABLE `customers_table` (
  `cust_id` int(20) NOT NULL AUTO_INCREMENT,
  `log_id` int(20) DEFAULT NULL,
  `cust_fname` varchar(20) DEFAULT NULL,
  `cust_lname` varchar(20) DEFAULT NULL,
  `cust_gender` varchar(10) DEFAULT NULL,
  `cust_hname` varchar(20) DEFAULT NULL,
  `cust_city` varchar(20) DEFAULT NULL,
  `cust_pincode` int(10) DEFAULT NULL,
  `cust_email` varchar(100) DEFAULT NULL,
  `cust_contact` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `customers_table` */

insert  into `customers_table`(`cust_id`,`log_id`,`cust_fname`,`cust_lname`,`cust_gender`,`cust_hname`,`cust_city`,`cust_pincode`,`cust_email`,`cust_contact`) values 
(1,4,'ASNA','NAZAR','Female','PARACKAL HOUSE','IDUKKI',685553,'asnanazar786@gmail.com','9061986184'),
(2,5,'cust1','lname1','female','hname1','city1',685554,'cust1@gmail.com','0987654321'),
(3,8,'Miya','Jose','Female','Rosevilla','Kochi',685236,'miya@gmail.com','9856324152'),
(4,12,'Richard','V Joseph','Male','v','Wayanad',670646,'richardvjoseph127@gmail.com','9539665781');

/*Table structure for table `feedback_table` */

DROP TABLE IF EXISTS `feedback_table`;

CREATE TABLE `feedback_table` (
  `f_id` int(20) NOT NULL AUTO_INCREMENT,
  `cust_id` int(20) NOT NULL,
  `feedback` varchar(1000) NOT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `feedback_table` */

insert  into `feedback_table`(`f_id`,`cust_id`,`feedback`,`date`) values 
(1,3,'good','2020-01-04'),
(2,3,'good','2020-01-06');

/*Table structure for table `login_table` */

DROP TABLE IF EXISTS `login_table`;

CREATE TABLE `login_table` (
  `log_id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `login_table` */

insert  into `login_table`(`log_id`,`username`,`password`,`type`) values 
(1,'admin','admin','admin'),
(2,'ss','ss','salesteam'),
(3,'ll','ll','logistics'),
(4,'uu','uu','user'),
(5,'cu','cu','user'),
(8,'mi','mi','user'),
(9,'nice','nice','shop'),
(10,'nayarambalam','nayarambalam','shop'),
(11,'adhil','55555555','shop'),
(12,'Richard','1000','user');

/*Table structure for table `logistics_table` */

DROP TABLE IF EXISTS `logistics_table`;

CREATE TABLE `logistics_table` (
  `logi_id` int(20) NOT NULL AUTO_INCREMENT,
  `log_id` int(20) NOT NULL,
  `logi_name` varchar(30) NOT NULL,
  `logi_email` varchar(40) NOT NULL,
  `logi_contact` varchar(20) NOT NULL,
  PRIMARY KEY (`logi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `logistics_table` */

insert  into `logistics_table`(`logi_id`,`log_id`,`logi_name`,`logi_email`,`logi_contact`) values 
(1,3,'Logistic Team1','logisticteam1@gmail.com','9868755544');

/*Table structure for table `order_table` */

DROP TABLE IF EXISTS `order_table`;

CREATE TABLE `order_table` (
  `order_id` int(20) NOT NULL AUTO_INCREMENT,
  `cust_id` int(20) NOT NULL,
  `pro_id` int(20) NOT NULL,
  `tot_price` int(20) NOT NULL,
  `order_status` varchar(30) NOT NULL,
  `qty` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `order_table` */

insert  into `order_table`(`order_id`,`cust_id`,`pro_id`,`tot_price`,`order_status`,`qty`) values 
(1,3,1,50000,'confirmed',2),
(2,3,3,8000,'confirmed',1),
(3,3,4,18000,'confirmed',2),
(4,3,2,30000,'confirmed',1),
(5,3,5,9000,'confirmed',1),
(6,2,5,16000,'confirmed',2),
(7,1,11,666,'confirmed',1);

/*Table structure for table `orderstatus_table` */

DROP TABLE IF EXISTS `orderstatus_table`;

CREATE TABLE `orderstatus_table` (
  `orderstatus_id` int(20) NOT NULL AUTO_INCREMENT,
  `order_id` int(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`orderstatus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `orderstatus_table` */

insert  into `orderstatus_table`(`orderstatus_id`,`order_id`,`status`) values 
(1,1,'Shipped'),
(2,2,'Shipped'),
(3,3,'Shipped'),
(4,4,'Pending'),
(5,5,'Pending'),
(6,6,'Pending'),
(7,7,'On the way');

/*Table structure for table `payment_table` */

DROP TABLE IF EXISTS `payment_table`;

CREATE TABLE `payment_table` (
  `pay_id` int(20) NOT NULL AUTO_INCREMENT,
  `cust_id` int(20) NOT NULL,
  `order_id` int(20) NOT NULL,
  `pro_id` int(20) NOT NULL,
  `pro_price` varchar(20) NOT NULL,
  `pay_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `payment_table` */

insert  into `payment_table`(`pay_id`,`cust_id`,`order_id`,`pro_id`,`pro_price`,`pay_status`) values 
(1,3,1,1,'50000','confirmed'),
(2,3,2,3,'8000','confirmed'),
(3,3,3,4,'18000','confirmed'),
(4,3,4,2,'30000','confirmed'),
(5,3,5,4,'9000','pending'),
(6,2,6,3,'16000','pending'),
(7,1,7,11,'666','confirmed');

/*Table structure for table `products_table` */

DROP TABLE IF EXISTS `products_table`;

CREATE TABLE `products_table` (
  `pro_id` int(20) NOT NULL AUTO_INCREMENT,
  `cat_id` int(20) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `pro_img` varchar(500) NOT NULL,
  `pro_name` varchar(20) NOT NULL,
  `pro_desc` varchar(1000) NOT NULL,
  `pro_price` varchar(20) NOT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `products_table` */

insert  into `products_table`(`pro_id`,`cat_id`,`shop_id`,`pro_img`,`pro_name`,`pro_desc`,`pro_price`) values 
(1,1,NULL,'static/uploads/c0022ef4-2d30-11ea-b081-08edb9687060Jewellery-Necklace-PNG-Image.png','Necklace','Necklace with red stone,916','25000'),
(2,1,NULL,'static/uploads/f9eb5e1e-2d30-11ea-ae53-08edb9687060ne6.png','Round Necklace','Traditional Necklace','30000'),
(3,2,NULL,'static/uploads/026f96c8-2d32-11ea-9a70-08edb9687060eri13.png','Earring','Long earring','8000'),
(4,2,NULL,'static/uploads/27f430e2-2d32-11ea-91dd-08edb9687060ear5.png','Peacock Earring','Peacock Earring with white stone','9000'),
(5,1,1,'static/uploads/da502862-b113-11ec-8cf5-c01885c89be2b1.jpg','as','qwer','333'),
(7,1,4,'static/uploads/cd073fee-b19d-11ec-beae-489ebd74326btshirt_PNG5451.png','Round neck t shirt','qwerty','4444'),
(8,1,4,'static/uploads/e4d53036-b19d-11ec-b93c-489ebd74326bf4.jpg','Round neck t shirt','wedrfgth','2345'),
(9,1,4,'static/uploads/42abec00-b19e-11ec-824d-489ebd74326bf4.jpg','Round neck t shirt','wedrfgth','2345'),
(10,4,1,'static/uploads/7032ebd4-b19e-11ec-9383-489ebd74326bf4.jpg','Round neck t shirt','wedrfgth','2345'),
(11,4,1,'static/uploads/8297bcba-b19e-11ec-8321-489ebd74326btshirt_PNG5451.png','Round neck t shirt','wertyui','666');

/*Table structure for table `sales_table` */

DROP TABLE IF EXISTS `sales_table`;

CREATE TABLE `sales_table` (
  `s_id` int(20) NOT NULL AUTO_INCREMENT,
  `sales_id` int(20) NOT NULL,
  `order_id` int(20) NOT NULL,
  `s_date` varchar(20) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `sales_table` */

insert  into `sales_table`(`s_id`,`sales_id`,`order_id`,`s_date`,`status`) values 
(1,1,1,'2020-01-04','paid'),
(2,1,2,'2020-01-04','paid'),
(3,1,3,'2020-01-06','paid'),
(4,1,7,'2022-04-01','paid');

/*Table structure for table `salesteam_table` */

DROP TABLE IF EXISTS `salesteam_table`;

CREATE TABLE `salesteam_table` (
  `sales_id` int(20) NOT NULL AUTO_INCREMENT,
  `log_id` int(20) NOT NULL,
  `sales_name` varchar(30) NOT NULL,
  `sales_city` varchar(30) NOT NULL,
  `sales_pincode` varchar(10) NOT NULL,
  `sales_email` varchar(50) NOT NULL,
  `sales_contact` varchar(10) NOT NULL,
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `salesteam_table` */

insert  into `salesteam_table`(`sales_id`,`log_id`,`sales_name`,`sales_city`,`sales_pincode`,`sales_email`,`sales_contact`) values 
(1,2,'Sales Team1','Ernakulam','678566','salesteam1@gmail.com','8957647362');

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_id` int(11) DEFAULT NULL,
  `shop` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `shop` */

insert  into `shop`(`shop_id`,`log_id`,`shop`,`address`,`place`,`phone`,`email`) values 
(1,9,'nice','aasds','nayarambalam','9123456789','s@gamil.com'),
(2,10,'DD','eted555','nayarambalam','9123456789','s@gamil.com'),
(3,11,'fit','cfghb','nghjb','9255656565','adhil@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
