/*
SQLyog - Free MySQL GUI v5.19
Host - 5.0.15-nt : Database - nw_ids_log
*********************************************************************
Server version : 5.0.15-nt
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `nw_ids_log`;

USE `nw_ids_log`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `dos_layer_virus` */

DROP TABLE IF EXISTS `dos_layer_virus`;

CREATE TABLE `dos_layer_virus` (
  `sl_no` int(5) default NULL,
  `f1` varchar(20) default NULL,
  `f2` varchar(20) default NULL,
  `f4` varchar(20) default NULL,
  `f5` varchar(20) default NULL,
  `f23` varchar(20) default NULL,
  `f34` varchar(20) default NULL,
  `f38` varchar(20) default NULL,
  `f39` varchar(20) default NULL,
  `f40` varchar(20) default NULL,
  `f_v_name` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dos_layer_virus` */

insert into `dos_layer_virus` (`sl_no`,`f1`,`f2`,`f4`,`f5`,`f23`,`f34`,`f38`,`f39`,`f40`,`f_v_name`) values (1,'0','tcp','SF','54540','4','1','0','0','0.04','back');
insert into `dos_layer_virus` (`sl_no`,`f1`,`f2`,`f4`,`f5`,`f23`,`f34`,`f38`,`f39`,`f40`,`f_v_name`) values (2,'0','tcp','RSTR','50248','10','1','0','0','0.05','back');
insert into `dos_layer_virus` (`sl_no`,`f1`,`f2`,`f4`,`f5`,`f23`,`f34`,`f38`,`f39`,`f40`,`f_v_name`) values (3,'0','tcp','S0','0','1','1','1','1','0','land');

/*Table structure for table `m_block` */

DROP TABLE IF EXISTS `m_block`;

CREATE TABLE `m_block` (
  `s_no` int(11) NOT NULL auto_increment,
  `Block_IpAddress` varchar(100) default NULL,
  PRIMARY KEY  (`s_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_block` */

/*Table structure for table `m_client` */

DROP TABLE IF EXISTS `m_client`;

CREATE TABLE `m_client` (
  `c_code` int(11) NOT NULL auto_increment,
  `id` varchar(100) default NULL,
  `pwd` varchar(100) default NULL,
  `user_name` varchar(100) default NULL,
  `user_emailid` varchar(150) default NULL,
  `Ip_address` varchar(50) default NULL,
  `Mac_address` varchar(50) default NULL,
  PRIMARY KEY  (`c_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_client` */

insert into `m_client` (`c_code`,`id`,`pwd`,`user_name`,`user_emailid`,`Ip_address`,`Mac_address`) values (1,'eswar','eswar','eswar','kanaga@celestalv.com','192.168.1.8','00-00-21-FA-D8-BC');
insert into `m_client` (`c_code`,`id`,`pwd`,`user_name`,`user_emailid`,`Ip_address`,`Mac_address`) values (2,'xyz','xyz','xyz','kanaganagaraj@gmail.com','192.168.1.6','33334');
insert into `m_client` (`c_code`,`id`,`pwd`,`user_name`,`user_emailid`,`Ip_address`,`Mac_address`) values (3,'karthick','karthick','karthick','karthicknsr89@gmail.com','192.168.1.6',NULL);

/*Table structure for table `m_file_upload` */

DROP TABLE IF EXISTS `m_file_upload`;

CREATE TABLE `m_file_upload` (
  `file_no` int(11) NOT NULL auto_increment,
  `file_name` varchar(50) default NULL,
  `file_datetime` varchar(100) default NULL,
  `user_code` int(5) default NULL,
  PRIMARY KEY  (`file_no`),
  KEY `FK_m_file_upload` (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_file_upload` */

insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (1,'2.txt','2014-05-31 19:05:51',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (2,'2.txt','2014-05-31 19:05:59',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (3,'8.txt','2014-05-33 19:05:18',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (4,'8.txt','2014-05-33 19:05:33',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (5,'8.txt','2014-05-02 20:05:07',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (6,'6.txt','2014-05-02 20:05:29',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (7,'6.txt','2014-05-02 20:05:36',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (8,'6.txt','2014-05-02 20:05:39',1);
insert into `m_file_upload` (`file_no`,`file_name`,`file_datetime`,`user_code`) values (9,'2.txt','2014-05-13 20:05:15',1);

/*Table structure for table `m_log` */

DROP TABLE IF EXISTS `m_log`;

CREATE TABLE `m_log` (
  `T_no` int(11) NOT NULL auto_increment,
  `T_datetime` varchar(200) default NULL,
  `ClientIp` varchar(100) default NULL,
  `Filename` varchar(100) default NULL,
  `file_status` varchar(100) default NULL,
  `Layer` varchar(150) default NULL,
  `AttackName` varchar(150) default NULL,
  PRIMARY KEY  (`T_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_log` */

insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (1,'05/33/2014','192.168.1.8','8.txt','virus','R2LLayer','ftp_write');
insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (2,'05/33/2014','192.168.1.8','8.txt','virus','ProbeLayer','ftp_write');
insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (3,'05/02/2014','192.168.1.8','8.txt','virus','R2LLayer','ftp_write');
insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (4,'05/02/2014','192.168.1.8','6.txt','virus','ProbeLayer','ftp_write');
insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (5,'05/02/2014','192.168.1.8','6.txt','virus','ProbeLayer','ftp_write');
insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (6,'05/02/2014','192.168.1.8','6.txt','virus','ProbeLayer','ftp_write');
insert into `m_log` (`T_no`,`T_datetime`,`ClientIp`,`Filename`,`file_status`,`Layer`,`AttackName`) values (7,'05/13/2014','192.168.1.8','2.txt','virus','R2LLayer','ftp_write');

/*Table structure for table `m_server` */

DROP TABLE IF EXISTS `m_server`;

CREATE TABLE `m_server` (
  `id` varchar(150) default NULL,
  `pwd` varchar(100) default NULL,
  `server_port1` int(50) default NULL,
  `server_port2` int(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_server` */

insert into `m_server` (`id`,`pwd`,`server_port1`,`server_port2`) values ('rajan','rajan',7001,7002);

/*Table structure for table `m_transfer` */

DROP TABLE IF EXISTS `m_transfer`;

CREATE TABLE `m_transfer` (
  `t_no` int(45) NOT NULL auto_increment,
  `t_datetime` varchar(100) default NULL,
  `ipaddress` varchar(45) NOT NULL,
  `no_acess` int(100) NOT NULL,
  PRIMARY KEY  (`t_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_transfer` */

insert into `m_transfer` (`t_no`,`t_datetime`,`ipaddress`,`no_acess`) values (1,'05/33/2014','192.168.1.8',5);

/*Table structure for table `probe_layer_virus` */

DROP TABLE IF EXISTS `probe_layer_virus`;

CREATE TABLE `probe_layer_virus` (
  `sl_no` int(5) NOT NULL auto_increment,
  `f1` varchar(20) default NULL,
  `f2` varchar(20) default NULL,
  `f3` varchar(20) default NULL,
  `f4` varchar(20) default NULL,
  `f5` varchar(20) default NULL,
  `f_v_name` varchar(20) default NULL,
  PRIMARY KEY  (`sl_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `probe_layer_virus` */

insert into `probe_layer_virus` (`sl_no`,`f1`,`f2`,`f3`,`f4`,`f5`,`f_v_name`) values (1,'0','tcp','private','REJ','0','ipsweep');
insert into `probe_layer_virus` (`sl_no`,`f1`,`f2`,`f3`,`f4`,`f5`,`f_v_name`) values (2,'0','tcp','private','REJ','0','ipsweep');
insert into `probe_layer_virus` (`sl_no`,`f1`,`f2`,`f3`,`f4`,`f5`,`f_v_name`) values (3,'0','tcp','private','REJ','0','ipsweep');
insert into `probe_layer_virus` (`sl_no`,`f1`,`f2`,`f3`,`f4`,`f5`,`f_v_name`) values (4,'0','tcp','smtp','SF','0','ipsweep');

/*Table structure for table `r2l_layer_virus` */

DROP TABLE IF EXISTS `r2l_layer_virus`;

CREATE TABLE `r2l_layer_virus` (
  `sl_no` int(5) NOT NULL auto_increment,
  `f1` varchar(20) default NULL,
  `f2` varchar(20) default NULL,
  `f3` varchar(20) default NULL,
  `f4` varchar(20) default NULL,
  `f5` varchar(20) default NULL,
  `f10` varchar(20) default NULL,
  `f11` varchar(20) default NULL,
  `f12` varchar(20) default NULL,
  `f13` varchar(20) default NULL,
  `f17` varchar(20) default NULL,
  `f18` varchar(20) default NULL,
  `f19` varchar(20) default NULL,
  `f21` varchar(20) default NULL,
  `f22` varchar(20) default NULL,
  `f_v_name` varchar(20) default NULL,
  PRIMARY KEY  (`sl_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `r2l_layer_virus` */

insert into `r2l_layer_virus` (`sl_no`,`f1`,`f2`,`f3`,`f4`,`f5`,`f10`,`f11`,`f12`,`f13`,`f17`,`f18`,`f19`,`f21`,`f22`,`f_v_name`) values (1,'26','tcp','ftp','SF','116','2','0','1','0','1','0','1','0','1','ftp_write');

/*Table structure for table `u2r_layer_virus` */

DROP TABLE IF EXISTS `u2r_layer_virus`;

CREATE TABLE `u2r_layer_virus` (
  `sl_no` int(5) NOT NULL auto_increment,
  `f10` varchar(20) default NULL,
  `f13` varchar(20) default NULL,
  `f14` varchar(20) default NULL,
  `f16` varchar(20) default NULL,
  `f17` varchar(20) default NULL,
  `f18` varchar(20) default NULL,
  `f19` varchar(20) default NULL,
  `f21` varchar(20) default NULL,
  `f_v_name` varchar(20) default NULL,
  PRIMARY KEY  (`sl_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `u2r_layer_virus` */

insert into `u2r_layer_virus` (`sl_no`,`f10`,`f13`,`f14`,`f16`,`f17`,`f18`,`f19`,`f21`,`f_v_name`) values (1,'3','2','1','0','1','0','0','0','buffer_overflow');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
