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

 Date: 07/04/2020 23:45:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shiro_power
-- ----------------------------
DROP TABLE IF EXISTS `shiro_power`;
CREATE TABLE `shiro_power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `power_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `power_desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `prower_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源连接',
  `prow_type` int(255) NULL DEFAULT NULL COMMENT '权限类型',
  `parner_id` int(11) NULL DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_power
-- ----------------------------
INSERT INTO `shiro_power` VALUES (1, '系统管理', '', NULL, 1, NULL);
INSERT INTO `shiro_power` VALUES (2, '用户管理', 'user:list', NULL, 1, 1);
INSERT INTO `shiro_power` VALUES (3, '角色管理', 'role:list', NULL, 1, 1);
INSERT INTO `shiro_power` VALUES (4, '资源管理', 'res:list', NULL, 1, 1);
INSERT INTO `shiro_power` VALUES (5, '数据管理', NULL, NULL, 1, NULL);
INSERT INTO `shiro_power` VALUES (6, '客户数据管理', 'customer:list', NULL, NULL, 5);

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES (1, '管理员');
INSERT INTO `shiro_role` VALUES (2, '部门经理');
INSERT INTO `shiro_role` VALUES (3, '普通用户');

-- ----------------------------
-- Table structure for shiro_role_power
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_power`;
CREATE TABLE `shiro_role_power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `power_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_role_power
-- ----------------------------
INSERT INTO `shiro_role_power` VALUES (1, 1, 1);
INSERT INTO `shiro_role_power` VALUES (2, 1, 2);
INSERT INTO `shiro_role_power` VALUES (3, 1, 3);
INSERT INTO `shiro_role_power` VALUES (4, 2, 1);
INSERT INTO `shiro_role_power` VALUES (5, 2, 4);

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES (1, 'zhangsan', '123');
INSERT INTO `shiro_user` VALUES (2, 'lisi', '123');

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES (1, 1, 1);
INSERT INTO `shiro_user_role` VALUES (2, 1, 2);
INSERT INTO `shiro_user_role` VALUES (3, 2, 2);

-- ----------------------------
-- Table structure for shiro_user2
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user2`;
CREATE TABLE `shiro_user2`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_user2
-- ----------------------------
INSERT INTO `shiro_user2` VALUES (1, 'zhangsan', '123', 1);
INSERT INTO `shiro_user2` VALUES (2, 'lisi', '123', 2);
INSERT INTO `shiro_user2` VALUES (3, 'wangwu', '123', 1);

-- ----------------------------
-- Table structure for ssm_user
-- ----------------------------
DROP TABLE IF EXISTS `ssm_user`;
CREATE TABLE `ssm_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户称呼',
  `user_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户状态  ture 1 正常 false 2 异常',
  `user_pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的头像图片url',
  `create_time` datetime NOT NULL COMMENT '注册的时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近的更新时间',
  `user_level` int(11) NULL DEFAULT NULL COMMENT '用户的等级\\水平',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
