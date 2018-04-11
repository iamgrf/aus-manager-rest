-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.1.197    Database: db_aus
-- ------------------------------------------------------
-- Server version	5.7.21-1ubuntu1

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
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '父id',
  `menu_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名',
  `menu_code` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单码',
  `create_date` datetime NOT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_code_UNIQUE` (`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (7,'-1','用户管理','/user','2018-03-26 19:06:39'),(8,'7','用户列表','/userList','2018-03-26 19:07:29'),(9,'-1','角色管理','/role','2018-03-26 19:07:51'),(10,'9','角色列表','/roleList','2018-03-26 19:08:03'),(11,'-1','菜单管理','/menu','2018-03-26 19:08:16'),(12,'11','菜单列表','/menuList','2018-03-26 19:08:27'),(14,'12','查询','/menu/cascadeMenu','2018-03-27 15:19:30'),(16,'12','删除','/menu/delMenu/.*','2018-03-27 15:20:15'),(17,'12','添加','/menu/addMenu','2018-03-27 15:21:17'),(18,'10','查询','/role/listRole','2018-03-27 15:21:41'),(19,'10','添加','/role/addRole','2018-03-27 15:22:02'),(20,'10','授权','/role/authorized','2018-03-27 15:22:33'),(21,'10','修改','/role/updateRole','2018-03-27 15:22:55'),(22,'10','删除','/role/delRole/.*','2018-03-27 15:23:12'),(23,'8','查询','/user/listUser','2018-03-27 15:23:40'),(24,'8','添加','/user/addUser','2018-03-27 15:24:11'),(25,'8','删除','/user/delUser/.*','2018-03-27 15:24:25'),(26,'8','修改','/user/updateUser','2018-03-27 15:24:35'),(27,'8','授权','/user/authorized','2018-03-27 15:24:45'),(28,'10','授权列表','/role/cascadeMenu/.*','2018-03-27 15:51:10');
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `code` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '唯一标识码',
  `authority_code` varchar(1000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '权限码',
  `create_date` datetime NOT NULL COMMENT 'account',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'管理员','admin','11111111111110101111110000000','2018-03-26 19:09:38'),(2,'测试','test','1111100000000000000110000000','2018-03-26 19:11:10');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录帐号',
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `real_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '真实姓名',
  `role_id` int(11) DEFAULT NULL COMMENT '角色',
  `disable` varchar(1) COLLATE utf8_unicode_ci NOT NULL COMMENT '1启用0禁用',
  `create_date` datetime NOT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (2,'admin','64cba3faf84ed1be5d213c2286f092c4355f85b7d05eb30854c0df4828bb08c1','admin',1,'1','2018-03-27 08:08:08'),(5,'test','2f2e63e47e747bafea4793d7ab0f537592a2d48f1c2ecaa57c40fe590c928394','test',2,'1','2018-03-28 14:06:09');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-11 18:34:38
