/*
Navicat MySQL Data Transfer

Source Server         : lubingliang
Source Server Version : 50510
Source Host           : localhost:3306
Source Database       : db_base_project

Target Server Type    : MYSQL
Target Server Version : 50510
File Encoding         : 65001

Date: 2020-03-24 21:23:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(11) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `url` varchar(128) DEFAULT NULL,
  `icon` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '0', '系统设置', '', 'icon-advancedsettings');
INSERT INTO `menu` VALUES ('4', '2', '菜单管理', '../admin/menu/list', 'icon-chart-organisation');
INSERT INTO `menu` VALUES ('6', '0', 'afja', '', 'icon-book-next');
INSERT INTO `menu` VALUES ('7', '2', '角色管理', '../admin/role/list', 'icon-user-group');
INSERT INTO `menu` VALUES ('8', '2', 'dsfasdf', 'dsfa', 'icon-briefcase');
INSERT INTO `menu` VALUES ('9', '2', 'werwer', '', 'icon-bin');
INSERT INTO `menu` VALUES ('10', '0', 'werwer', '', 'icon-border-all');
INSERT INTO `menu` VALUES ('11', '0', 'sdfasfdas', '', 'icon-border-inner');
INSERT INTO `menu` VALUES ('12', '0', 'dfsaf', '', 'icon-bullet-database-yellow');
INSERT INTO `menu` VALUES ('13', '0', 'sdafasf', '', 'icon-bug-magnify');
INSERT INTO `menu` VALUES ('14', '6', 'dafa', '', 'icon-application-osx-delete');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('16', '超级管理员', '超级管理员拥有一切权限');
INSERT INTO `role` VALUES ('17', '普通用户', '普通用户，自由授权');
INSERT INTO `role` VALUES ('19', 'adfasdf', '');
INSERT INTO `role` VALUES ('20', 'safdaf', '');
INSERT INTO `role` VALUES ('21', 'sdfasf', '');
INSERT INTO `role` VALUES ('22', 'dfasdf', '');
INSERT INTO `role` VALUES ('23', 'asdfasf', '');
INSERT INTO `role` VALUES ('24', 'asdfasf', '');
INSERT INTO `role` VALUES ('25', 'sdfasf', '');
INSERT INTO `role` VALUES ('26', 'sdfasf', '');
INSERT INTO `role` VALUES ('27', 'sdfas', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `photo` varchar(128) DEFAULT NULL,
  `sex` int(1) NOT NULL DEFAULT '0',
  `age` int(3) DEFAULT '0',
  `address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', null, '0', '0', null);
