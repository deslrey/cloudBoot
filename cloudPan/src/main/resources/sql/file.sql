/*
 Navicat Premium Data Transfer

 Source Server         : gcy
 Source Server Type    : MySQL
 Source Server Version : 50720 (5.7.20-log)
 Source Host           : localhost:3306
 Source Schema         : file

 Target Server Type    : MySQL
 Target Server Version : 50720 (5.7.20-log)
 File Encoding         : 65001

 Date: 28/09/2024 15:03:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for email_code
-- ----------------------------
DROP TABLE IF EXISTS `email_code`;
CREATE TABLE `email_code`  (
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '验证码',
  `creat_time` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '0:未使用 1:已使用',
  PRIMARY KEY (`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '邮箱验证码表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for entities
-- ----------------------------
DROP TABLE IF EXISTS `entities`;
CREATE TABLE `entities`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `exist` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `file_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件Id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户Id',
  `file_md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件MD5值',
  `file_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父级Id',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件名',
  `file_cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件封面',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件路径',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `folder_type` int(11) NULL DEFAULT NULL COMMENT '0:文件 1:目录',
  `file_category` int(11) NULL DEFAULT NULL COMMENT '文件分类 1:视频 2:音频 3:图片 4:文档 5:其他',
  `file_type` int(11) NULL DEFAULT NULL COMMENT '1:视频 2:音频 3:图片 4:pdf 5:doc 6:excel 7:txt 8:code 9:zip 10:其他',
  `status` int(11) NULL DEFAULT NULL COMMENT '0:转码中 1:转码失败 2:转码成功',
  `recovery_time` datetime NULL DEFAULT NULL COMMENT '进入回收站时间',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '0:删除 1:回收站 2:正常',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_members
-- ----------------------------
DROP TABLE IF EXISTS `group_members`;
CREATE TABLE `group_members`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `node_id` int(11) NOT NULL,
  `node_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `exist` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `exist` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for manage_arrows
-- ----------------------------
DROP TABLE IF EXISTS `manage_arrows`;
CREATE TABLE `manage_arrows`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `arrow_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '箭头数据',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建用户',
  `exist` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for node_types
-- ----------------------------
DROP TABLE IF EXISTS `node_types`;
CREATE TABLE `node_types`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `exist` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for persons
-- ----------------------------
DROP TABLE IF EXISTS `persons`;
CREATE TABLE `persons`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `birthplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `exist` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for relationships
-- ----------------------------
DROP TABLE IF EXISTS `relationships`;
CREATE TABLE `relationships`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_id` int(11) NOT NULL,
  `start_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `end_id` int(11) NOT NULL,
  `end_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` int(11) NOT NULL,
  `information` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `exist` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户ID',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `join_time` datetime NOT NULL COMMENT '注册时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int(11) NOT NULL COMMENT '用户状态(0禁用)',
  `use_space` bigint(20) NOT NULL COMMENT '使用空间',
  `total_space` bigint(20) NOT NULL COMMENT '总空间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
