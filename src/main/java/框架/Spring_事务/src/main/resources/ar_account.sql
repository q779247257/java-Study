/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-05-20 23:29:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ar_account
-- ----------------------------
DROP TABLE IF EXISTS `ar_account`;
CREATE TABLE `ar_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ar_account
-- ----------------------------
INSERT INTO `ar_account` VALUES ('1', 'Helen', '1000.00');
INSERT INTO `ar_account` VALUES ('2', 'Tom', '1000.00');
INSERT INTO `ar_account` VALUES ('3', 'ccc', '1000.00');
