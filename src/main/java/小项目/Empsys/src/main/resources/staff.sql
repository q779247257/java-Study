/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : java_study

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 17/03/2020 15:43:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名字',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '选', 19, '男', '15713912141');
INSERT INTO `staff` VALUES (2, '选', 18, '男', '1123123123');
INSERT INTO `staff` VALUES (3, '轩轩', 15, '女', '1231231123');
INSERT INTO `staff` VALUES (6, '雷雷s傻冒', 133, '女', '14213');
INSERT INTO `staff` VALUES (7, '轩轩11', 16, '女', '145');
INSERT INTO `staff` VALUES (8, '轩轩1', 13, '女', '131313');
INSERT INTO `staff` VALUES (9, '轩轩2', 22, '男', '22');
INSERT INTO `staff` VALUES (16, '轩轩666', 666, '男', '666');

SET FOREIGN_KEY_CHECKS = 1;
