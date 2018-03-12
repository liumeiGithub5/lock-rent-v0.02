
DROP DATABASE IF EXISTS `lock-rent-v0.02`;
CREATE DATABASE IF NOT EXISTS `lock-rent-v0.02` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `lock-rent-v0.02`;

SET FOREIGN_KEY_CHECKS=0;



DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'property','1','2017-06-22 06:45:03'),(2,'super','1','2017-06-22 06:45:03');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1,1),(2,1,2),(4,1,4),(5,1,9),(8,2,5),(9,2,6),(10,2,7),(11,2,8),(12,2,10),(14,2,12),(15,1,11);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','13612345678',1,'2016-11-11 03:11:11'),('9a1b10eafc6442c28565e1f6ac8e6d2a','yangquan','E10ADC3949BA59ABBE56E057F20F883E','17602831583',1,'2017-06-28 08:36:05'),('a9551b3b6a5548cbad0fed0930639a97','test','E10ADC3949BA59ABBE56E057F20F883E','12356789870',0,'2017-08-03 08:47:33'),('c02b769a80184193be0bc7315e6712e0','liumei','E10ADC3949BA59ABBE56E057F20F883E','18628107232',1,'2017-06-28 03:22:14'),('c0c1c30724be468d8927ae17b279fcf8','xyj','E10ADC3949BA59ABBE56E057F20F883E','13880345037',1,'2017-07-12 07:29:10');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (6,'c02b769a80184193be0bc7315e6712e0',2),(7,'9a1b10eafc6442c28565e1f6ac8e6d2a',1),(8,'c0c1c30724be468d8927ae17b279fcf8',1),(9,'a9551b3b6a5548cbad0fed0930639a97',1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_band_lock`
--

DROP TABLE IF EXISTS `tb_band_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_band_lock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL,
  `lock_id` bigint(20) NOT NULL,
  `mac` varchar(50) NOT NULL,
  `alias` varchar(50) DEFAULT NULL,
  `addr` varchar(100) NOT NULL,
  `img` varchar(1000) DEFAULT NULL COMMENT '上传照片URL  非必须',
  `bluetooth` varchar(50) DEFAULT NULL COMMENT '蓝牙信息',
  `lbs` tinyint(2) NOT NULL DEFAULT '0' COMMENT '锁维修状态  0:已绑定（正常）1：维护中',
  `ls` tinyint(2) DEFAULT '0' COMMENT '锁状态  0:在线 1：掉线,临时密码功能不可用  默认0 ',
  `les` tinyint(2) DEFAULT '0' COMMENT '锁电量状态 0：正常 1：电量低 ； 默认正常0',
  `cales` tinyint(2) DEFAULT '0' COMMENT '卡取电设备电量状态 0：正常 1：电量低 ； 默认正常0',
  `eprice` double(10,2) NOT NULL COMMENT '电费单价',
  `pow` double NOT NULL COMMENT '对应家电功率',
  `savetotal` double NOT NULL DEFAULT '0',
  `savetoday` double NOT NULL DEFAULT '0',
  `bandtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_band_lock`
--

LOCK TABLES `tb_band_lock` WRITE;
/*!40000 ALTER TABLE `tb_band_lock` DISABLE KEYS */;
INSERT INTO `tb_band_lock` VALUES (1,'18628107238',1,'F9QJZUVW','1号房子','0.8','https://localhost:8080/rent-v0.02/fileStore/imgDirectory/mobd33cbdfea03a4628857b24bd2dbe9aef.jpg','bluetooth',0,0,0,0,0.80,1.8,187.35305555555556,140.6925,'2017-11-14 08:07:49'),(2,'18628107238',2,'SpF6Cq46','2号房子','0','','bluetooth',0,0,0,0,0.80,2.9,0,0,'2017-11-14 08:07:49'),(6,'18328425206',5,'H3nUobf9','白宫','杨权house','','bluetooth',0,0,0,0,0.82,1310,0,0,'2017-11-16 02:49:17'),(8,'18328425206',7,'Zs54HjkV','中南海','天府大道中段辅路','','bluetooth',1,0,0,0,0.52,1310,0,0,'2017-11-16 03:02:05'),(10,'18628107238',3,'8zYQJjYa','3号房子','软件园B7','','蓝牙信息',0,0,0,0,1.00,100,0,0,'2018-03-06 06:51:58');
/*!40000 ALTER TABLE `tb_band_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ic_band`
--

DROP TABLE IF EXISTS `tb_ic_band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ic_band` (
  `ic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lock_id` bigint(20) NOT NULL,
  `mac` varchar(50) NOT NULL,
  `inNo` varchar(20) DEFAULT NULL COMMENT '由硬件传给服务器',
  `outNo` varchar(20) DEFAULT NULL COMMENT '由客服在web端添加 随机  每个不一样',
  `ic_name` varchar(10) NOT NULL,
  `ic_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'ic状态 默认为0   0：正常 1：待补',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ic_band`
--

LOCK TABLES `tb_ic_band` WRITE;
/*!40000 ALTER TABLE `tb_ic_band` DISABLE KEYS */;
INSERT INTO `tb_ic_band` VALUES (1,1,'F9QJZUVW','D6D7D8A5','1','房东卡',0,'2017-11-15 10:58:21'),(2,1,'F9QJZUVW','4622CEA5','1001','用户卡1',0,'2017-11-15 10:58:21'),(3,1,'F9QJZUVW','967467A2','1002','用户卡2',0,'2017-11-15 10:58:21'),(4,2,'SpF6Cq46','E61728A1','2','房东卡',0,'2017-11-15 10:58:21'),(5,2,'SpF6Cq46','36C678A5','1003','用户卡1',0,'2017-11-15 10:58:21'),(6,2,'SpF6Cq46','F6E5C5A1','1004','用户卡2',0,'2017-11-15 10:58:21'),(7,3,'8zYQJjYa','86FB626B','3','房东卡',0,'2017-11-15 10:58:21'),(8,3,'8zYQJjYa','A647516B','1005','用户卡1',0,'2017-11-15 10:58:21'),(9,3,'8zYQJjYa','A62328A1','1006','用户卡2',0,'2017-11-15 10:58:21'),(10,4,'QAMhqXbV','303F9915','4','房东卡',0,'2017-11-15 10:58:21'),(11,4,'QAMhqXbV','A6C024A1','1007','用户卡1',0,'2017-11-15 10:58:21'),(12,4,'QAMhqXbV','666053A1','1008','用户卡2',0,'2017-11-15 10:58:21'),(13,5,'H3nUobf9','E6FEB7A1','5','房东卡',0,'2017-11-15 10:58:21'),(14,5,'H3nUobf9','96C32AA2','1009','用户卡1',0,'2017-11-15 10:58:21'),(15,5,'H3nUobf9','96FD70A5','1010','用户卡2',0,'2017-11-15 10:58:21'),(16,6,'mWYldTGQ','B647596B','6','房东卡',0,'2017-11-15 10:58:21'),(17,6,'mWYldTGQ','C6E9626B','1011','用户卡1',0,'2017-11-15 10:58:21'),(18,6,'mWYldTGQ','561CE8A1','1012','用户卡2',0,'2017-11-15 10:58:21'),(19,7,'Zs54HjkV','3699A46B','7','房东卡',0,'2017-11-15 10:58:21'),(20,7,'Zs54HjkV','E63FEEA1','1013','用户卡1',0,'2017-11-15 10:58:21'),(21,7,'Zs54HjkV','66FAB7A1','1014','用户卡2',0,'2017-11-15 10:58:21'),(22,8,'8LkVsJkl','56A745A2','8','房东卡',0,'2017-11-15 10:58:21'),(23,8,'8LkVsJkl','067E4CA2','1015','用户卡1',0,'2017-11-15 10:58:21'),(24,8,'8LkVsJkl','864876A2','1016','用户卡2',0,'2017-11-15 10:58:21'),(25,9,'kSJBNSFO','76A5B36B','9','房东卡',0,'2017-11-15 10:58:21'),(26,9,'kSJBNSFO','26CE616B','1017','用户卡1',0,'2017-11-15 10:58:21'),(27,9,'kSJBNSFO','A655DCA1','1018','用户卡2',0,'2017-11-15 10:58:21'),(28,10,'HvtVH8Rk','56C227A2','10','房东卡',0,'2017-11-15 10:58:21'),(29,10,'HvtVH8Rk','86E4DAA1','1019','用户卡1',0,'2017-11-15 10:58:21'),(30,10,'HvtVH8Rk','7600B26B','1020','用户卡2',0,'2017-11-15 10:58:21'),(31,11,'5atVjhmm','A6865F6B','11','房东卡',0,'2017-11-15 10:58:21'),(32,11,'5atVjhmm','96A5C2A1','1021','用户卡1',0,'2017-11-15 10:58:21'),(33,11,'5atVjhmm','D610B36B','1022','用户卡2',0,'2017-11-15 10:58:21'),(34,12,'XFNQJZiS','C6BB2EA1','12','房东卡',0,'2017-11-15 10:58:21'),(35,12,'XFNQJZiS','86B329A2','1023','用户卡1',0,'2017-11-15 10:58:21'),(36,12,'XFNQJZiS','E69E29A2','1024','用户卡2',0,'2017-11-15 10:58:21'),(37,13,'QrTzGup4','5602566B','13','房东卡',0,'2017-11-15 10:58:21'),(38,13,'QrTzGup4','960E536B','1025','用户卡1',0,'2017-11-15 10:58:21'),(39,13,'QrTzGup4','B6C5FFA5','1026','用户卡2',0,'2017-11-15 10:58:21'),(40,14,'7J7zPfKX','7669956B','14','房东卡',0,'2017-11-15 10:58:21'),(41,14,'7J7zPfKX','D6D2B5A5','1027','用户卡1',0,'2017-11-15 10:58:21'),(42,14,'7J7zPfKX','D67C20A2','1028','用户卡2',0,'2017-11-15 10:58:21'),(43,15,'HfMBICoO','0625B9A1','15','房东卡',0,'2017-11-15 10:58:21'),(44,15,'HfMBICoO','2602C3A1','1029','用户卡1',0,'2017-11-15 10:58:21'),(45,15,'HfMBICoO','36A96DA2','1030','用户卡2',0,'2017-11-15 10:58:21'),(46,16,'HumNHN18','D6F5ADA1','16','房东卡',0,'2017-11-15 10:58:21'),(47,16,'HumNHN18','E68EBDA5','1031','用户卡1',0,'2017-11-15 10:58:21'),(48,16,'HumNHN18','B6B762A2','1032','用户卡2',0,'2017-11-15 10:58:21'),(49,17,'0FZWiFhO','D62E28A1','17','房东卡',0,'2017-11-15 10:58:21'),(50,17,'0FZWiFhO','B6B129A2','1033','用户卡1',0,'2017-11-15 10:58:21'),(51,17,'0FZWiFhO','56BA27A2','1034','用户卡2',0,'2017-11-15 10:58:21'),(52,18,'MqdDqiPP','D67060A2','18','房东卡',0,'2017-11-15 10:58:21'),(53,18,'MqdDqiPP','96FE4DA2','1035','用户卡1',0,'2017-11-15 10:58:21'),(54,18,'MqdDqiPP','E683A7A1','1036','用户卡2',0,'2017-11-15 10:58:21'),(55,19,'NsbJa7d9','76EAC5A1','19','房东卡',0,'2017-11-15 10:58:21'),(56,19,'NsbJa7d9','B68867A2','1037','用户卡1',0,'2017-11-15 10:58:21'),(57,19,'NsbJa7d9','26CFC2A1','1038','用户卡2',0,'2017-11-15 10:58:21'),(58,20,'EQihc0jk','96EB29A2','20','房东卡',0,'2017-11-15 10:58:21'),(59,20,'EQihc0jk','D6019E6B','1039','用户卡1',0,'2017-11-15 10:58:21'),(60,20,'EQihc0jk','E6CE03A6','1040','用户卡2',0,'2017-11-15 10:58:21'),(61,21,'FD4KRrvV','F6A2536B','21','房东卡',0,'2017-11-15 10:58:21'),(62,21,'FD4KRrvV','F6DA72A5','1041','用户卡1',0,'2017-11-15 10:58:21'),(63,21,'FD4KRrvV','F68E6FA5','1042','用户卡2',0,'2017-11-15 10:58:21'),(64,22,'cfwQ2Dx6','56749EA1','22','房东卡',0,'2017-11-15 10:58:21'),(65,22,'cfwQ2Dx6','C6DBB3A5','1043','用户卡1',0,'2017-11-15 10:58:21'),(66,22,'cfwQ2Dx6','2653BCA1','1044','用户卡2',0,'2017-11-15 10:58:21'),(67,23,'jEAAw8X3','C65E6DA5','23','房东卡',0,'2017-11-15 10:58:21'),(68,23,'jEAAw8X3','D67068A5','1045','用户卡1',0,'2017-11-15 10:58:21'),(69,23,'jEAAw8X3','66D7C3A5','1046','用户卡2',0,'2017-11-15 10:58:21'),(70,24,'M5PvRYZv','168251A2','24','房东卡',0,'2017-11-15 10:58:21'),(71,24,'M5PvRYZv','A6AF7FA5','1047','用户卡1',0,'2017-11-15 10:58:21'),(72,24,'M5PvRYZv','26F6DEA5','1048','用户卡2',0,'2017-11-15 10:58:21'),(73,25,'PeJuIk0w','76BDBEA1','25','房东卡',0,'2017-11-15 10:58:21'),(74,25,'PeJuIk0w','6629BCA5','1049','用户卡1',0,'2017-11-15 10:58:21'),(75,25,'PeJuIk0w','4699CDA5','1050','用户卡2',0,'2017-11-15 10:58:21'),(76,26,'8kbEmvvg','B63D9D6B','26','房东卡',0,'2017-11-15 10:58:21'),(77,26,'8kbEmvvg','765B6BA5','1051','用户卡1',0,'2017-11-15 10:58:21'),(78,26,'8kbEmvvg','D6F8CEA5','1052','用户卡2',0,'2017-11-15 10:58:21'),(79,27,'bWqs47Jo','E6DCC2A1','27','房东卡',0,'2017-11-15 10:58:21'),(80,27,'bWqs47Jo','06C97BA5','1053','用户卡1',0,'2017-11-15 10:58:21'),(81,27,'bWqs47Jo','7695CAA5','1054','用户卡2',0,'2017-11-15 10:58:21'),(82,28,'jveDVLXZ','160C80A2','28','房东卡',0,'2017-11-15 10:58:21'),(83,28,'jveDVLXZ','468152A2','1055','用户卡1',0,'2017-11-15 10:58:21'),(84,28,'jveDVLXZ','B68827A2','1056','用户卡2',0,'2017-11-15 10:58:21'),(85,29,'txoYqNY2','461BC5A1','29','房东卡',0,'2017-11-15 10:58:21'),(86,29,'txoYqNY2','3618576B','1057','用户卡1',0,'2017-11-15 10:58:21'),(87,29,'txoYqNY2','56A7576B','1058','用户卡2',0,'2017-11-15 10:58:21'),(88,30,'w6P5dNq9','36792AA2','30','房东卡',0,'2017-11-15 10:58:21'),(89,30,'w6P5dNq9','A65D47A2','1059','用户卡1',0,'2017-11-15 10:58:21'),(90,30,'w6P5dNq9','368FE1A1','1060','用户卡2',0,'2017-11-15 10:58:21'),(91,31,'VkbLlrAS','A67F67A2','31','房东卡',0,'2017-11-15 10:58:21'),(92,31,'VkbLlrAS','06F129A2','1061','用户卡1',0,'2017-11-15 10:58:21'),(93,31,'VkbLlrAS','A60484A2','1062','用户卡2',0,'2017-11-15 10:58:21'),(94,32,'94E48ZXt','16CE84A5','32','房东卡',0,'2017-11-15 10:58:21'),(95,32,'94E48ZXt','36721EA1','1063','用户卡1',0,'2017-11-15 10:58:21'),(96,32,'94E48ZXt','062728A1','1064','用户卡2',0,'2017-11-15 10:58:21'),(97,33,'SZ0mXtdW','468D6B6B','33','房东卡',0,'2017-11-15 10:58:21'),(98,33,'SZ0mXtdW','36D2656B','1065','用户卡1',0,'2017-11-15 10:58:21'),(99,33,'SZ0mXtdW','C63DBCA1','1066','用户卡2',0,'2017-11-15 10:58:21'),(100,34,'c1Gec7do','46E8D5A5','34','房东卡',0,'2017-11-15 10:58:21'),(101,34,'c1Gec7do','E6D954A2','1067','用户卡1',0,'2017-11-15 10:58:21'),(102,34,'c1Gec7do','86E3BC6B','1068','用户卡2',0,'2017-11-15 10:58:21'),(103,35,'u9VCVOsP','C659BCA1','35','房东卡',0,'2017-11-15 10:58:21'),(104,35,'u9VCVOsP','66DABBA1','1069','用户卡1',0,'2017-11-15 10:58:21'),(105,35,'u9VCVOsP','F6E92C6A','1070','用户卡2',0,'2017-11-15 10:58:21'),(106,36,'SOGaS8VT','96737CA2','36','房东卡',0,'2017-11-15 10:58:21'),(107,36,'SOGaS8VT','36C7B36B','1071','用户卡1',0,'2017-11-15 10:58:21'),(108,36,'SOGaS8VT','6602956B','1072','用户卡2',0,'2017-11-15 10:58:21'),(109,37,'khUu8DMm','1652B9A1','37','房东卡',0,'2017-11-15 10:58:21'),(110,37,'khUu8DMm','362CB9A1','1073','用户卡1',0,'2017-11-15 10:58:21'),(111,37,'khUu8DMm','7637B0A5','1074','用户卡2',0,'2017-11-15 10:58:21'),(112,38,'fIGEtyVV','066F6D6B','38','房东卡',0,'2017-11-15 10:58:21'),(113,38,'fIGEtyVV','363EA8A1','1075','用户卡1',0,'2017-11-15 10:58:21'),(114,38,'fIGEtyVV','F6A1B36B','1076','用户卡2',0,'2017-11-15 10:58:21'),(115,39,'ve5jXemE','26419D6B','39','房东卡',0,'2017-11-15 10:58:21'),(116,39,'ve5jXemE','163727A2','1077','用户卡1',0,'2017-11-15 10:58:21'),(117,39,'ve5jXemE','F6FC4DA2','1078','用户卡2',0,'2017-11-15 10:58:21'),(118,40,'sNIDaLML','4689AB6B','40','房东卡',0,'2017-11-15 10:58:21'),(119,40,'sNIDaLML','56AA5EA2','1079','用户卡1',0,'2017-11-15 10:58:21'),(120,40,'sNIDaLML','369284A5','1080','用户卡2',0,'2017-11-15 10:58:21'),(121,41,'rfkISkha','C6416D6B','41','房东卡',0,'2017-11-15 10:58:21'),(122,41,'rfkISkha','3611C3A1','1081','用户卡1',0,'2017-11-15 10:58:21'),(123,41,'rfkISkha','C67E4CA2','1082','用户卡2',0,'2017-11-15 10:58:21'),(124,42,'hJI4k9nP','B6706CA5','42','房东卡',0,'2017-11-15 10:58:21'),(125,42,'hJI4k9nP','B66D54A2','1083','用户卡1',0,'2017-11-15 10:58:21'),(126,42,'hJI4k9nP','1656B9A1','1084','用户卡2',0,'2017-11-15 10:58:21'),(127,43,'Dj4mAGta','E63F946B','43','房东卡',0,'2017-11-15 10:58:21'),(128,43,'Dj4mAGta','C60279A5','1085','用户卡1',0,'2017-11-15 10:58:21'),(129,43,'Dj4mAGta','76235DA2','1086','用户卡2',0,'2017-11-15 10:58:21'),(130,44,'D8gB3ILU','76E6CAA5','44','房东卡',0,'2017-11-15 10:58:21'),(131,44,'D8gB3ILU','561721A2','1087','用户卡1',0,'2017-11-15 10:58:21'),(132,44,'D8gB3ILU','E63973A5','1088','用户卡2',0,'2017-11-15 10:58:21'),(133,45,'CTmtdopb','4673CDA5','45','房东卡',0,'2017-11-15 10:58:21'),(134,45,'CTmtdopb','B6D2B7A1','1089','用户卡1',0,'2017-11-15 10:58:21'),(135,45,'CTmtdopb','46E72AA2','1090','用户卡2',0,'2017-11-15 10:58:21'),(136,46,'TtjO7t2k','C6390CA6','46','房东卡',0,'2017-11-15 10:58:21'),(137,46,'TtjO7t2k','568D6C6B','1091','用户卡1',0,'2017-11-15 10:58:21'),(138,46,'TtjO7t2k','B6007FA5','1092','用户卡2',0,'2017-11-15 10:58:21'),(139,47,'W3f1rxYI','C631A26B','47','房东卡',0,'2017-11-15 10:58:21'),(140,47,'W3f1rxYI','367372A5','1093','用户卡1',0,'2017-11-15 10:58:21'),(141,47,'W3f1rxYI','F68E78A5','1094','用户卡2',0,'2017-11-15 10:58:21'),(142,48,'6sIt0ZQ5','868E646B','48','房东卡',0,'2017-11-15 10:58:21'),(143,48,'6sIt0ZQ5','364D79A5','1095','用户卡1',0,'2017-11-15 10:58:21'),(144,48,'6sIt0ZQ5','C6D412A6','1096','用户卡2',0,'2017-11-15 10:58:21'),(145,49,'hIN6jfeN','66ACB36B','49','房东卡',0,'2017-11-15 10:58:21'),(146,49,'hIN6jfeN','368B0BA6','1097','用户卡1',0,'2017-11-15 10:58:21'),(147,49,'hIN6jfeN','8609B16B','1098','用户卡2',0,'2017-11-15 10:58:21'),(148,50,'01FegcT0','C67B5A6B','50','房东卡',0,'2017-11-15 10:58:21'),(149,50,'01FegcT0','56416D6B','1099','用户卡1',0,'2017-11-15 10:58:21'),(150,50,'01FegcT0','86AB14A6','1100','用户卡2',0,'2017-11-15 10:58:21'),(151,51,'sjUzfiWS','E60CEDA5','51','房东卡',0,'2017-11-15 10:58:21'),(152,51,'sjUzfiWS','964049A1','1101','用户卡1',0,'2017-11-15 10:58:21'),(153,51,'sjUzfiWS','16356BA5','1102','用户卡2',0,'2017-11-15 10:58:21'),(154,52,'XlGODZJo','46E3D0A5','52','房东卡',0,'2017-11-15 10:58:21'),(155,52,'XlGODZJo','560DD1A5','1103','用户卡1',0,'2017-11-15 10:58:21'),(156,52,'XlGODZJo','8695D0A5','1104','用户卡2',0,'2017-11-15 10:58:21'),(157,53,'gNyqzh2R','263FEEA1','53','房东卡',0,'2017-11-15 10:58:21'),(158,53,'gNyqzh2R','E629D0A5','1105','用户卡1',0,'2017-11-15 10:58:21'),(159,53,'gNyqzh2R','269AB36B','1106','用户卡2',0,'2017-11-15 10:58:21'),(160,54,'llIOyivT','E65E39A1','54','房东卡',0,'2017-11-15 10:58:21'),(161,54,'llIOyivT','F692506B','1107','用户卡1',0,'2017-11-15 10:58:21'),(162,54,'llIOyivT','968CE1A1','1108','用户卡2',0,'2017-11-15 10:58:21'),(163,55,'PP3R5r30','F6BA20A2','55','房东卡',0,'2017-11-15 10:58:21'),(164,55,'PP3R5r30','06B22EA1','1109','用户卡1',0,'2017-11-15 10:58:21'),(165,55,'PP3R5r30','B637946B','1110','用户卡2',0,'2017-11-15 10:58:21'),(166,56,'g9zZJJ4X','06B821A1','56','房东卡',0,'2017-11-15 10:58:21'),(167,56,'g9zZJJ4X','96AB9F6B','1111','用户卡1',0,'2017-11-15 10:58:21'),(168,56,'g9zZJJ4X','26B44FA1','1112','用户卡2',0,'2017-11-15 10:58:21'),(169,57,'67Zo5UbI','F6C72AA2','57','房东卡',0,'2017-11-15 10:58:21'),(170,57,'67Zo5UbI','46965F6B','1113','用户卡1',0,'2017-11-15 10:58:21'),(171,57,'67Zo5UbI','8655DCA1','1114','用户卡2',0,'2017-11-15 10:58:21'),(172,58,'YwmVEPoY','E6DAB26B','58','房东卡',0,'2017-11-15 10:58:21'),(173,58,'YwmVEPoY','D6A4546B','1115','用户卡1',0,'2017-11-15 10:58:21'),(174,58,'YwmVEPoY','E65DEFA5','1116','用户卡2',0,'2017-11-15 10:58:21'),(175,59,'HYFYMHSv','A6B4BEA1','59','房东卡',0,'2017-11-15 10:58:21'),(176,59,'HYFYMHSv','068320A2','1117','用户卡1',0,'2017-11-15 10:58:21'),(177,59,'HYFYMHSv','E6EA20A2','1118','用户卡2',0,'2017-11-15 10:58:21'),(178,60,'eaM1i0Sg','969C8AA5','60','房东卡',0,'2017-11-15 10:58:21'),(179,60,'eaM1i0Sg','D65D2AA2','1119','用户卡1',0,'2017-11-15 10:58:21'),(180,60,'eaM1i0Sg','C69A6E6B','1120','用户卡2',0,'2017-11-15 10:58:21'),(181,61,'YWz54WCu','26A9B36B','61','房东卡',0,'2017-11-15 10:58:21'),(182,61,'YWz54WCu','461DA86B','1121','用户卡1',0,'2017-11-15 10:58:21'),(183,61,'YWz54WCu','B68D6C6B','1122','用户卡2',0,'2017-11-15 10:58:21'),(184,62,'L7RurdaW','6683946B','62','房东卡',0,'2017-11-15 10:58:21'),(185,62,'L7RurdaW','06F59A6B','1123','用户卡1',0,'2017-11-15 10:58:21'),(186,62,'L7RurdaW','F6C1B3A5','1124','用户卡2',0,'2017-11-15 10:58:21'),(187,63,'I3J5QFkv','26C229A2','63','房东卡',0,'2017-11-15 10:58:21'),(188,63,'I3J5QFkv','5667A06B','1125','用户卡1',0,'2017-11-15 10:58:21'),(189,63,'I3J5QFkv','C6A172A5','1126','用户卡2',0,'2017-11-15 10:58:21'),(190,64,'cjafhNyH','560B80A2','64','房东卡',0,'2017-11-15 10:58:21'),(191,64,'cjafhNyH','465DCDA5','1127','用户卡1',0,'2017-11-15 10:58:21'),(192,64,'cjafhNyH','1659B3A5','1128','用户卡2',0,'2017-11-15 10:58:21'),(193,65,'fCBVqVru','364121A2','65','房东卡',0,'2017-11-15 10:58:21'),(194,65,'fCBVqVru','16086C6B','1129','用户卡1',0,'2017-11-15 10:58:21'),(195,65,'fCBVqVru','86F1BC6B','1130','用户卡2',0,'2017-11-15 10:58:21'),(196,66,'PnV7I1bZ','269669A2','66','房东卡',0,'2017-11-15 10:58:21'),(197,66,'PnV7I1bZ','A62C9A6B','1131','用户卡1',0,'2017-11-15 10:58:21'),(198,66,'PnV7I1bZ','26F6B36B','1132','用户卡2',0,'2017-11-15 10:58:21'),(199,67,'rHyYotDY','66202AA2','67','房东卡',0,'2017-11-15 10:58:21'),(200,67,'rHyYotDY','C693B6A5','1133','用户卡1',0,'2017-11-15 10:58:21'),(201,67,'rHyYotDY','D62BCEA5','1134','用户卡2',0,'2017-11-15 10:58:21'),(202,68,'F9hTHaXM','561A556B','68','房东卡',0,'2017-11-15 10:58:21'),(203,68,'F9hTHaXM','26BEBEA1','1135','用户卡1',0,'2017-11-15 10:58:21'),(204,68,'F9hTHaXM','7637E9A1','1136','用户卡2',0,'2017-11-15 10:58:22'),(205,69,'gaVoQFXV','B6B7B26B','69','房东卡',0,'2017-11-15 10:58:22'),(206,69,'gaVoQFXV','66A2A7A1','1137','用户卡1',0,'2017-11-15 10:58:22'),(207,69,'gaVoQFXV','569469A2','1138','用户卡2',0,'2017-11-15 10:58:22'),(208,70,'1dq1iGX1','A616C6A1','70','房东卡',0,'2017-11-15 10:58:22'),(209,70,'1dq1iGX1','96D654A2','1139','用户卡1',0,'2017-11-15 10:58:22'),(210,70,'1dq1iGX1','A699506B','1140','用户卡2',0,'2017-11-15 10:58:22'),(211,71,'bfXcYSm7','76EC8BA5','71','房东卡',0,'2017-11-15 10:58:22'),(212,71,'bfXcYSm7','365981A2','1141','用户卡1',0,'2017-11-15 10:58:22'),(213,71,'bfXcYSm7','66C284A6','1142','用户卡2',0,'2017-11-15 10:58:22'),(214,72,'rP9ohEkJ','269E526B','72','房东卡',0,'2017-11-15 10:58:22'),(215,72,'rP9ohEkJ','7617B46B','1143','用户卡1',0,'2017-11-15 10:58:22'),(216,72,'rP9ohEkJ','B672956B','1144','用户卡2',0,'2017-11-15 10:58:22'),(217,73,'71Tt3Cwu','F6B884A2','73','房东卡',0,'2017-11-15 10:58:22'),(218,73,'71Tt3Cwu','06551EA1','1145','用户卡1',0,'2017-11-15 10:58:22'),(219,73,'71Tt3Cwu','A6FDB36B','1146','用户卡2',0,'2017-11-15 10:58:22'),(220,74,'qYUbah5L','D621A86B','74','房东卡',0,'2017-11-15 10:58:22'),(221,74,'qYUbah5L','56555B6B','1147','用户卡1',0,'2017-11-15 10:58:22'),(222,74,'qYUbah5L','D6FE4DA2','1148','用户卡2',0,'2017-11-15 10:58:22'),(223,75,'KOQmMq8f','9638B8A1','75','房东卡',0,'2017-11-15 10:58:22'),(224,75,'KOQmMq8f','069F5CA2','1149','用户卡1',0,'2017-11-15 10:58:22'),(225,75,'KOQmMq8f','E680AF6B','1150','用户卡2',0,'2017-11-15 10:58:22'),(226,76,'0upUyZuS','A6F652A2','76','房东卡',0,'2017-11-15 10:58:22'),(227,76,'0upUyZuS','86CEA96B','1151','用户卡1',0,'2017-11-15 10:58:22'),(228,76,'0upUyZuS','2646946B','1152','用户卡2',0,'2017-11-15 10:58:22'),(229,77,'bSoU060p','269E69AC','77','房东卡',0,'2017-11-15 10:58:22'),(230,77,'bSoU060p','C6C097A5','1153','用户卡1',0,'2017-11-15 10:58:22'),(231,77,'bSoU060p','C64B75A5','1154','用户卡2',0,'2017-11-15 10:58:22'),(232,78,'rz6c2doo','8624B9A1','78','房东卡',0,'2017-11-15 10:58:22'),(233,78,'rz6c2doo','F60782A2','1155','用户卡1',0,'2017-11-15 10:58:22'),(234,78,'rz6c2doo','466881A2','1156','用户卡2',0,'2017-11-15 10:58:22'),(235,79,'LDFn464Y','E6572AA2','79','房东卡',0,'2017-11-15 10:58:22'),(236,79,'LDFn464Y','661653A2','1157','用户卡1',0,'2017-11-15 10:58:22'),(237,79,'LDFn464Y','D6F383A2','1158','用户卡2',0,'2017-11-15 10:58:22'),(238,80,'yp2zy5I2','D6D6FDA5','80','房东卡',0,'2017-11-15 10:58:22'),(239,80,'yp2zy5I2','4624536B','1159','用户卡1',0,'2017-11-15 10:58:22'),(240,80,'yp2zy5I2','C6502BA1','1160','用户卡2',0,'2017-11-15 10:58:22'),(241,81,'rkb5deAS','66147DA2','81','房东卡',0,'2017-11-15 10:58:22'),(242,81,'rkb5deAS','161D59A2','1161','用户卡1',0,'2017-11-15 10:58:22'),(243,81,'rkb5deAS','760370A5','1162','用户卡2',0,'2017-11-15 10:58:22'),(244,82,'pzhh4xs7','E681CDA5','82','房东卡',0,'2017-11-15 10:58:22'),(245,82,'pzhh4xs7','B6E3D0A1','1163','用户卡1',0,'2017-11-15 10:58:22'),(246,82,'pzhh4xs7','561279A5','1164','用户卡2',0,'2017-11-15 10:58:22'),(247,83,'M37ABF4K','76DD29A2','83','房东卡',0,'2017-11-15 10:58:22'),(248,83,'M37ABF4K','06A06DA2','1165','用户卡1',0,'2017-11-15 10:58:22'),(249,83,'M37ABF4K','F624F5A5','1166','用户卡2',0,'2017-11-15 10:58:22'),(250,84,'GWHQLwoW','06B378A5','84','房东卡',0,'2017-11-15 10:58:22'),(251,84,'GWHQLwoW','A64DCEA5','1167','用户卡1',0,'2017-11-15 10:58:22'),(252,84,'GWHQLwoW','8657CDA5','1168','用户卡2',0,'2017-11-15 10:58:22'),(253,85,'uEyyVBbB','96ED72A5','85','房东卡',0,'2017-11-15 10:58:22'),(254,85,'uEyyVBbB','A60753A2','1169','用户卡1',0,'2017-11-15 10:58:22'),(255,85,'uEyyVBbB','2696A7A1','1170','用户卡2',0,'2017-11-15 10:58:22'),(256,86,'OzF72nEV','56FCCEA5','86','房东卡',0,'2017-11-15 10:58:22'),(257,86,'OzF72nEV','26E042A1','1171','用户卡1',0,'2017-11-15 10:58:22'),(258,86,'OzF72nEV','463459A2','1172','用户卡2',0,'2017-11-15 10:58:22'),(259,87,'lFHRG0e3','A68E4CA2','87','房东卡',0,'2017-11-15 10:58:22'),(260,87,'lFHRG0e3','36EE47A2','1173','用户卡1',0,'2017-11-15 10:58:22'),(261,87,'lFHRG0e3','A6E74DA2','1174','用户卡2',0,'2017-11-15 10:58:22'),(262,88,'YTSZlCdl','96D0BEA1','88','房东卡',0,'2017-11-15 10:58:22'),(263,88,'YTSZlCdl','162DFCA5','1175','用户卡1',0,'2017-11-15 10:58:22'),(264,88,'YTSZlCdl','26BBB1A5','1176','用户卡2',0,'2017-11-15 10:58:22'),(265,89,'4g8VDLzo','668F21A1','89','房东卡',0,'2017-11-15 10:58:22'),(266,89,'4g8VDLzo','E6AD6CA5','1177','用户卡1',0,'2017-11-15 10:58:22'),(267,89,'4g8VDLzo','76A9BFA1','1178','用户卡2',0,'2017-11-15 10:58:22'),(268,90,'xsM36J4k','56C626A2','90','房东卡',0,'2017-11-15 10:58:22'),(269,90,'xsM36J4k','86BCCEA5','1179','用户卡1',0,'2017-11-15 10:58:22'),(270,90,'xsM36J4k','360679A5','1180','用户卡2',0,'2017-11-15 10:58:22'),(271,91,'MAoe8VUT','860D2AA2','91','房东卡',0,'2017-11-15 10:58:22'),(272,91,'MAoe8VUT','23BC962A','1181','用户卡1',0,'2017-11-15 10:58:22'),(273,91,'MAoe8VUT','561C4CA2','1182','用户卡2',0,'2017-11-15 10:58:22'),(274,92,'rOtOvfqj','768C21A1','92','房东卡',0,'2017-11-15 10:58:22'),(275,92,'rOtOvfqj','66B9AFA1','1183','用户卡1',0,'2017-11-15 10:58:22'),(276,92,'rOtOvfqj','660F21A2','1184','用户卡2',0,'2017-11-15 10:58:22'),(277,93,'NJbHD5OV','16B375A5','93','房东卡',0,'2017-11-15 10:58:22'),(278,93,'NJbHD5OV','769729A2','1185','用户卡1',0,'2017-11-15 10:58:22'),(279,93,'NJbHD5OV','260C53A2','1186','用户卡2',0,'2017-11-15 10:58:22'),(280,94,'vIBXrk8D','16697CA5','94','房东卡',0,'2017-11-15 10:58:22'),(281,94,'vIBXrk8D','768AE5A5','1187','用户卡1',0,'2017-11-15 10:58:22'),(282,94,'vIBXrk8D','56717FA5','1188','用户卡2',0,'2017-11-15 10:58:22'),(283,95,'Uf6ovvXb','C6B972A5','95','房东卡',0,'2017-11-15 10:58:22'),(284,95,'Uf6ovvXb','E60834A2','1189','用户卡1',0,'2017-11-15 10:58:22'),(285,95,'Uf6ovvXb','265B7FA5','1190','用户卡2',0,'2017-11-15 10:58:22'),(286,96,'OId2ePl8','B60E53A2','96','房东卡',0,'2017-11-15 10:58:22'),(287,96,'OId2ePl8','867E6DA2','1191','用户卡1',0,'2017-11-15 10:58:22'),(288,96,'OId2ePl8','0646CEA5','1192','用户卡2',0,'2017-11-15 10:58:22'),(289,97,'Ek1LcDHK','568BCDA5','97','房东卡',0,'2017-11-15 10:58:22'),(290,97,'Ek1LcDHK','96FFCFA5','1193','用户卡1',0,'2017-11-15 10:58:22'),(291,97,'Ek1LcDHK','269A0AA6','1194','用户卡2',0,'2017-11-15 10:58:22'),(292,98,'0FX42Kon','7690CEA5','98','房东卡',0,'2017-11-15 10:58:22'),(293,98,'0FX42Kon','C67EB9A1','1195','用户卡1',0,'2017-11-15 10:58:22'),(294,98,'0FX42Kon','56325F6B','1196','用户卡2',0,'2017-11-15 10:58:22'),(295,99,'QGe8xHC9','467B5816','99','房东卡',0,'2017-11-15 10:58:22'),(296,99,'QGe8xHC9','9665BFA1','1197','用户卡1',0,'2017-11-15 10:58:22'),(297,99,'QGe8xHC9','56C524A1','1198','用户卡2',0,'2017-11-15 10:58:22'),(298,100,'4EAbNGX6','F673BCA1','100','房东卡',0,'2017-11-15 10:58:22'),(299,100,'4EAbNGX6','B617D0A5','1199','用户卡1',0,'2017-11-15 10:58:22'),(300,100,'4EAbNGX6','66F7A6A1','1200','用户卡2',0,'2017-11-15 10:58:22');
/*!40000 ALTER TABLE `tb_ic_band` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_id_mac_band`
--

DROP TABLE IF EXISTS `tb_id_mac_band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_id_mac_band` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lock_id` bigint(20) NOT NULL,
  `mac` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_id_mac_band_mac_uindex` (`mac`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_id_mac_band`
--

LOCK TABLES `tb_id_mac_band` WRITE;
/*!40000 ALTER TABLE `tb_id_mac_band` DISABLE KEYS */;
INSERT INTO `tb_id_mac_band` VALUES (1,1,'F9QJZUVW'),(2,2,'SpF6Cq46'),(3,3,'8zYQJjYa'),(4,4,'QAMhqXbV'),(5,5,'H3nUobf9'),(6,6,'mWYldTGQ'),(7,7,'Zs54HjkV'),(8,8,'8LkVsJkl'),(9,9,'kSJBNSFO'),(10,10,'HvtVH8Rk'),(11,11,'5atVjhmm'),(12,12,'XFNQJZiS'),(13,13,'QrTzGup4'),(14,14,'7J7zPfKX'),(15,15,'HfMBICoO'),(16,16,'HumNHN18'),(17,17,'0FZWiFhO'),(18,18,'MqdDqiPP'),(19,19,'NsbJa7d9'),(20,20,'EQihc0jk'),(21,21,'FD4KRrvV'),(22,22,'cfwQ2Dx6'),(23,23,'jEAAw8X3'),(24,24,'M5PvRYZv'),(25,25,'PeJuIk0w'),(26,26,'8kbEmvvg'),(27,27,'bWqs47Jo'),(28,28,'jveDVLXZ'),(29,29,'txoYqNY2'),(30,30,'w6P5dNq9'),(31,31,'VkbLlrAS'),(32,32,'94E48ZXt'),(33,33,'SZ0mXtdW'),(34,34,'c1Gec7do'),(35,35,'u9VCVOsP'),(36,36,'SOGaS8VT'),(37,37,'khUu8DMm'),(38,38,'fIGEtyVV'),(39,39,'ve5jXemE'),(40,40,'sNIDaLML'),(41,41,'rfkISkha'),(42,42,'hJI4k9nP'),(43,43,'Dj4mAGta'),(44,44,'D8gB3ILU'),(45,45,'CTmtdopb'),(46,46,'TtjO7t2k'),(47,47,'W3f1rxYI'),(48,48,'6sIt0ZQ5'),(49,49,'hIN6jfeN'),(50,50,'01FegcT0'),(51,51,'sjUzfiWS'),(52,52,'XlGODZJo'),(53,53,'gNyqzh2R'),(54,54,'llIOyivT'),(55,55,'PP3R5r30'),(56,56,'g9zZJJ4X'),(57,57,'67Zo5UbI'),(58,58,'YwmVEPoY'),(59,59,'HYFYMHSv'),(60,60,'eaM1i0Sg'),(61,61,'YWz54WCu'),(62,62,'L7RurdaW'),(63,63,'I3J5QFkv'),(64,64,'cjafhNyH'),(65,65,'fCBVqVru'),(66,66,'PnV7I1bZ'),(67,67,'rHyYotDY'),(68,68,'F9hTHaXM'),(69,69,'gaVoQFXV'),(70,70,'1dq1iGX1'),(71,71,'bfXcYSm7'),(72,72,'rP9ohEkJ'),(73,73,'71Tt3Cwu'),(74,74,'qYUbah5L'),(75,75,'KOQmMq8f'),(76,76,'0upUyZuS'),(77,77,'bSoU060p'),(78,78,'rz6c2doo'),(79,79,'LDFn464Y'),(80,80,'yp2zy5I2'),(81,81,'rkb5deAS'),(82,82,'pzhh4xs7'),(83,83,'M37ABF4K'),(84,84,'GWHQLwoW'),(85,85,'uEyyVBbB'),(86,86,'OzF72nEV'),(87,87,'lFHRG0e3'),(88,88,'YTSZlCdl'),(89,89,'4g8VDLzo'),(90,90,'xsM36J4k'),(91,91,'MAoe8VUT'),(92,92,'rOtOvfqj'),(93,93,'NJbHD5OV'),(94,94,'vIBXrk8D'),(95,95,'Uf6ovvXb'),(96,96,'OId2ePl8'),(97,97,'Ek1LcDHK'),(98,98,'0FX42Kon'),(99,99,'QGe8xHC9'),(100,100,'4EAbNGX6'),(101,101,'Z16cJPYH'),(102,102,'QOKzCKsk'),(103,103,'xQJxqFsZ'),(104,104,'JmSYgzI6'),(105,105,'BZ1H6HtH'),(106,106,'idI2YXhS'),(107,107,'x39HE0p1'),(108,108,'pRkiOtKR'),(109,109,'Qko4Un7i'),(110,110,'7XuXMm7E'),(111,111,'ataUsvGU'),(112,112,'7pslowgO'),(113,113,'RZKB2gJT'),(114,114,'HKMZ0hfa'),(115,115,'GERhdU6J'),(116,116,'Rjz20VQQ'),(117,117,'6fijsUAI'),(118,118,'WFa8v62L'),(119,119,'qF6FLveJ'),(120,120,'wX6tBJc1'),(121,121,'41otGr5l'),(122,122,'QqID8UMz'),(123,123,'ynoMsCEy'),(124,124,'4ip39MGd'),(125,125,'FPKX4fg1'),(126,126,'OmLMmNAg'),(127,127,'vHwqWLtp'),(128,128,'9e5krVEW'),(129,129,'6DIFqujO'),(130,130,'TfF14H6E'),(131,131,'tTmcvKUI'),(132,132,'o2QOVBKd'),(133,133,'zdPbcoxM'),(134,134,'KCpwOLGL'),(135,135,'L89Es1UA'),(136,136,'BswJ7cAF'),(137,137,'SKtMYuCI'),(138,138,'NVerlmDf'),(139,139,'t6Nntsyn'),(140,140,'91qACTlu'),(141,141,'o5BT9Gi4'),(142,142,'oRgFfJcm'),(143,143,'chk0RJpN'),(144,144,'b2kraZ9W'),(145,145,'jcI99WNT'),(146,146,'kPwJUIB6'),(147,147,'Cv6D0YSc'),(148,148,'RkOQj9JC'),(149,149,'zCtIl9lk'),(150,150,'DqcwVmLd'),(151,151,'Rg4RReTU'),(152,152,'jomrrGwk'),(153,153,'gI8aar0l'),(154,154,'IUadnOfK'),(155,155,'RcSUsLyC'),(156,156,'ufHocJHm'),(157,157,'K28Z5Ab3'),(158,158,'tUwBPHtg'),(159,159,'UpajzrJs'),(160,160,'JzssEbnF'),(161,161,'7sxMlBsA'),(162,162,'NDfMVZ3R'),(163,163,'BwiJEbPj'),(164,164,'6ng6bB7b'),(165,165,'I9rj748w'),(166,166,'ijZQs49M'),(167,167,'EGQI4s9b'),(168,168,'9tqFuUGa'),(169,169,'7dJL4876'),(170,170,'qsh2FgVa'),(171,171,'M3JhQ21x'),(172,172,'U716wde3'),(173,173,'O15ZEWsz'),(174,174,'KrImxiJe'),(175,175,'o99cDDYK'),(176,176,'xIPeSnHP'),(177,177,'bRhSEbaa'),(178,178,'7vQgi5Ne'),(179,179,'C4dFOFWp'),(180,180,'pIDl9gxX'),(181,181,'X2f95n71'),(182,182,'JV3UkzQO'),(183,183,'40TFHwH2'),(184,184,'yjDMsaRr'),(185,185,'mu7Z5Xml'),(186,186,'D7ChrLYO'),(187,187,'U6U76Lg9'),(188,188,'tm9JI8dn'),(189,189,'lpNB4QbN'),(190,190,'2epCPRzm'),(191,191,'N6kZTCY3'),(192,192,'62BTXG4m'),(193,193,'EgpIoUl4'),(194,194,'V7GI5ZsA'),(195,195,'hmNwXYqU'),(196,196,'f2x3THE1'),(197,197,'JmnxEaB1'),(198,198,'1YkCU2L4'),(199,199,'cem37AMi'),(200,200,'byCaWTMT');
/*!40000 ALTER TABLE `tb_id_mac_band` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_keypass_band`
--

DROP TABLE IF EXISTS `tb_keypass_band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_keypass_band` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `keypass` varchar(6) NOT NULL DEFAULT '888888' COMMENT '默认初始密码888888',
  `tempKey` varchar(4) DEFAULT NULL,
  `tempstatus` tinyint(2) DEFAULT '1' COMMENT '0：未使用 1：已使用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '固定密码更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_keypass_band`
--

LOCK TABLES `tb_keypass_band` WRITE;
/*!40000 ALTER TABLE `tb_keypass_band` DISABLE KEYS */;
INSERT INTO `tb_keypass_band` VALUES (1,'F9QJZUVW','888888','3939',0,'2018-03-06 03:54:40'),(2,'8zYQJjYa','888888',NULL,1,'2018-03-06 06:51:58');
/*!40000 ALTER TABLE `tb_keypass_band` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_monthsdetail`
--

DROP TABLE IF EXISTS `tb_monthsdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_monthsdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `years` varchar(8) NOT NULL,
  `months` varchar(2) NOT NULL,
  `savetimes` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_monthsdetail`
--

LOCK TABLES `tb_monthsdetail` WRITE;
/*!40000 ALTER TABLE `tb_monthsdetail` DISABLE KEYS */;
INSERT INTO `tb_monthsdetail` VALUES (1,'F9QJZUVW','2017','11',187.13416666666666);
/*!40000 ALTER TABLE `tb_monthsdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_open`
--

DROP TABLE IF EXISTS `tb_open`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_open` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `result` int(2) NOT NULL COMMENT '0:成功1：失败',
  `typ` varchar(30) NOT NULL COMMENT '0：临时密码开门  1：固定密码开门 卡号：ic卡开门（卡号）',
  `open_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_open`
--

LOCK TABLES `tb_open` WRITE;
/*!40000 ALTER TABLE `tb_open` DISABLE KEYS */;
INSERT INTO `tb_open` VALUES (1,'F9QJZUVW',1,'临时密码','2017-10-12 07:40:33'),(2,'F9QJZUVW',1,'临时密码','2017-10-12 07:56:49'),(3,'F9QJZUVW',1,'临时密码','2017-10-12 07:58:07'),(4,'F9QJZUVW',0,'ic卡:01003891','2017-10-12 08:03:10'),(5,'F9QJZUVW',0,'ic卡:01003891','2017-10-12 08:04:34'),(6,'F9QJZUVW',1,'临时密码','2017-10-12 08:16:46'),(7,'F9QJZUVW',1,'固定密码','2017-10-12 08:16:53'),(8,'F9QJZUVW',1,'ic卡:01003893','2017-10-12 08:17:01'),(9,'F9QJZUVW',1,'临时密码','2017-10-12 08:17:15'),(10,'F9QJZUVW',1,'临时密码','2017-10-12 08:17:39'),(11,'F9QJZUVW',1,'临时密码','2017-10-12 09:43:41'),(12,'F9QJZUVW',1,'固定密码','2017-10-12 09:43:54'),(13,'F9QJZUVW',1,'ic卡:01003893','2017-10-12 09:44:03'),(14,'F9QJZUVW',1,'临时密码','2017-10-12 09:44:12'),(15,'F9QJZUVW',1,'临时密码','2017-10-12 09:44:23'),(16,'F9QJZUVW',1,'临时密码','2017-10-12 09:48:16'),(17,'F9QJZUVW',1,'临时密码','2017-10-12 09:49:17'),(18,'F9QJZUVW',1,'临时密码','2017-10-12 09:49:23'),(19,'F9QJZUVW',1,'临时密码','2017-10-12 09:49:26'),(20,'F9QJZUVW',1,'临时密码','2017-10-12 09:49:33'),(21,'F9QJZUVW',1,'临时密码','2017-10-12 09:55:57'),(22,'F9QJZUVW',1,'临时密码','2017-10-12 09:56:01'),(23,'F9QJZUVW',1,'临时密码','2017-10-12 09:56:03'),(24,'F9QJZUVW',1,'临时密码','2017-10-12 09:56:05'),(25,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 07:46:19'),(26,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 07:46:48'),(27,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 07:49:22'),(28,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 07:52:45'),(29,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 07:55:22'),(30,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:01:22'),(31,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:04:02'),(32,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:08:12'),(33,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:08:51'),(34,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:09:24'),(35,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:09:42'),(36,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:34:58'),(37,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:35:17'),(38,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:35:49'),(39,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:36:35'),(40,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:38:25'),(41,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-24 08:42:21'),(42,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-25 02:59:26'),(43,'F9QJZUVW',0,'临时密码','2017-10-25 03:00:55'),(44,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-25 03:10:56'),(45,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-25 03:11:15'),(46,'F9QJZUVW',0,'ic卡:F1BA5694','2017-10-25 03:11:34'),(47,'F9QJZUVW',1,'ic卡:01003893','2017-11-15 08:41:55'),(48,'F9QJZUVW',1,'ic卡:01003893','2017-11-15 08:42:16'),(49,'F9QJZUVW',1,'临时密码','2017-11-15 08:53:52'),(50,'F9QJZUVW',1,'临时密码','2017-11-15 08:54:07'),(51,'F9QJZUVW',1,'临时密码','2017-11-15 08:54:42'),(52,'F9QJZUVW',1,'临时密码','2017-11-15 08:57:02'),(53,'F9QJZUVW',1,'临时密码','2017-11-23 06:42:31'),(54,'F9QJZUVW',1,'临时密码','2017-11-23 06:44:40'),(55,'F9QJZUVW',1,'临时密码','2017-11-29 08:18:31'),(56,'F9QJZUVW',1,'临时密码','2017-11-29 08:31:56'),(57,'F9QJZUVW',1,'临时密码','2017-11-29 08:32:06'),(58,'mWYldTGQ',1,'临时密码','2017-11-29 08:33:58'),(59,'mWYldTGQ',1,'临时密码','2017-11-29 09:29:10'),(60,'F9QJZUVW',1,'临时密码','2017-11-29 10:42:09'),(61,'F9QJZUVW',1,'临时密码','2017-11-30 02:19:41'),(62,'F9QJZUVW',1,'临时密码','2017-11-30 02:33:27'),(63,'F9QJZUVW',1,'临时密码','2017-11-30 03:18:16'),(64,'F9QJZUVW',1,'临时密码','2017-11-30 03:18:52');
/*!40000 ALTER TABLE `tb_open` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_power`
--

DROP TABLE IF EXISTS `tb_power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_power` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `typ` int(2) NOT NULL COMMENT '0:来电 1：断电',
  `power_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_power`
--

LOCK TABLES `tb_power` WRITE;
/*!40000 ALTER TABLE `tb_power` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_quartersdetail`
--

DROP TABLE IF EXISTS `tb_quartersdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_quartersdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `years` varchar(8) NOT NULL,
  `months` varchar(4) NOT NULL,
  `quarters` varchar(1) NOT NULL,
  `savetimes` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_quartersdetail`
--

LOCK TABLES `tb_quartersdetail` WRITE;
/*!40000 ALTER TABLE `tb_quartersdetail` DISABLE KEYS */;
INSERT INTO `tb_quartersdetail` VALUES (1,'F9QJZUVW','2017','10','4',140.69472222222223);
/*!40000 ALTER TABLE `tb_quartersdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_remark`
--

DROP TABLE IF EXISTS `tb_remark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_remark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL,
  `remark` varchar(500) NOT NULL,
  `rm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '留言状态 0：待处理 1：已处理',
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_remark`
--

LOCK TABLES `tb_remark` WRITE;
/*!40000 ALTER TABLE `tb_remark` DISABLE KEYS */;
INSERT INTO `tb_remark` VALUES (1,'18628107238','测试留言测试留言测试留言测试留言测试留言测试留言测试留言测试留言测试留言测试留言测试留言',1,'2017-11-16 14:13:04'),(2,'18328425206','hhhh',0,'2017-11-17 14:26:11'),(3,'18628107238','空军建军节',0,'2017-11-29 17:50:43'),(4,'18628107238','卡里路',0,'2017-11-29 17:52:45'),(5,'18628107238','测试 升级版上传留言',0,'2018-03-06 14:44:32');
/*!40000 ALTER TABLE `tb_remark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_savee_time`
--

DROP TABLE IF EXISTS `tb_savee_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_savee_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `saveETime` double NOT NULL COMMENT '节约用电时间 单位小时',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入该记录的时间（当前来电时间为结束时间，即当前时间）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_savee_time`
--

LOCK TABLES `tb_savee_time` WRITE;
/*!40000 ALTER TABLE `tb_savee_time` DISABLE KEYS */;
INSERT INTO `tb_savee_time` VALUES (8,'F9QJZUVW',0.01638888888888889,'2017-11-15 09:59:47'),(9,'F9QJZUVW',0.11638888888888889,'2017-11-15 10:16:47'),(10,'F9QJZUVW',46.42305555555556,'2017-11-17 08:42:26'),(11,'F9QJZUVW',0.014166666666666666,'2017-11-17 08:43:26'),(12,'F9QJZUVW',0.0030555555555555557,'2017-11-17 09:42:37'),(13,'F9QJZUVW',0.0022222222222222222,'2017-11-17 09:50:29'),(14,'F9QJZUVW',0.0022222222222222222,'2017-11-17 09:53:46'),(15,'F9QJZUVW',0.0022222222222222222,'2017-11-17 09:58:33'),(16,'F9QJZUVW',140.6925,'2017-11-23 06:40:17');
/*!40000 ALTER TABLE `tb_savee_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_token`
--

DROP TABLE IF EXISTS `tb_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_token` (
  `mobile` varchar(11) NOT NULL,
  `token` varchar(50) NOT NULL,
  `expire_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_token`
--

LOCK TABLES `tb_token` WRITE;
/*!40000 ALTER TABLE `tb_token` DISABLE KEYS */;
INSERT INTO `tb_token` VALUES ('18328425206','482b5f99-8749-484f-92a8-01e13fe11b05','2018-11-29 16:31:08','2017-11-29 16:31:08'),('18628107232','b408f9a2-39aa-4d0f-bb67-cca4e576f0ae','2019-03-06 14:43:45','2018-03-06 14:43:45'),('18628107238','acf276c9-64a9-465f-8a7d-88a3bf93d8b3','2018-11-30 10:22:21','2017-11-30 10:22:21');
/*!40000 ALTER TABLE `tb_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_upload_history`
--

DROP TABLE IF EXISTS `tb_upload_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_upload_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(50) NOT NULL COMMENT '操作者 账号',
  `count` int(11) NOT NULL COMMENT '当前批次上传锁条数',
  `numrange` varchar(100) NOT NULL COMMENT '截止锁编号 如 101-201',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uptype` tinyint(4) NOT NULL COMMENT '上传类型 0：锁id_mac   1:锁Id_mac_ic ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_upload_history`
--

LOCK TABLES `tb_upload_history` WRITE;
/*!40000 ALTER TABLE `tb_upload_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_upload_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `mobile` varchar(11) NOT NULL,
  `sex` int(2) DEFAULT NULL COMMENT '0:女 1：男',
  `appType` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,NULL,'18328425206',NULL,'android'),(2,NULL,'18628107238',NULL,'ios'),(3,NULL,'18628107232',NULL,'ios');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_weeksdetail`
--

DROP TABLE IF EXISTS `tb_weeksdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_weeksdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `years` varchar(8) NOT NULL,
  `weeks` varchar(4) NOT NULL,
  `savetimes` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_weeksdetail`
--

LOCK TABLES `tb_weeksdetail` WRITE;
/*!40000 ALTER TABLE `tb_weeksdetail` DISABLE KEYS */;
INSERT INTO `tb_weeksdetail` VALUES (1,'F9QJZUVW','2017','46',46.44166666666667),(2,'F9QJZUVW','2017','47',140.6925);
/*!40000 ALTER TABLE `tb_weeksdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_xinge_token`
--

DROP TABLE IF EXISTS `tb_xinge_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_xinge_token` (
  `mobile` varchar(11) NOT NULL,
  `xinge_token` varchar(100) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_xinge_token`
--

LOCK TABLES `tb_xinge_token` WRITE;
/*!40000 ALTER TABLE `tb_xinge_token` DISABLE KEYS */;
INSERT INTO `tb_xinge_token` VALUES ('18328425206','c9efc874b49e492549fd90c8958caebba63dac6a','2017-10-17 16:25:35'),('18628107232','c9efc874b49e492549fd90c8958caebba63dac6a','2017-11-17 15:41:32');
/*!40000 ALTER TABLE `tb_xinge_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_yearsdetail`
--

DROP TABLE IF EXISTS `tb_yearsdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_yearsdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mac` varchar(50) NOT NULL,
  `years` varchar(8) NOT NULL,
  `savetimes` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_yearsdetail`
--

LOCK TABLES `tb_yearsdetail` WRITE;
/*!40000 ALTER TABLE `tb_yearsdetail` DISABLE KEYS */;
INSERT INTO `tb_yearsdetail` VALUES (1,'F9QJZUVW','2017',187.13194444444446),(2,'F9QJZUVW','2018',37);
/*!40000 ALTER TABLE `tb_yearsdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-06 15:29:32
