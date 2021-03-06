/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : fproto

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2016-05-04 15:55:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_info
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `version` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_info
-- ----------------------------
INSERT INTO `project_info` VALUES ('1', '龙行天下', '0');
INSERT INTO `project_info` VALUES ('2', '水浒风云', '0');
INSERT INTO `project_info` VALUES ('14', '这个页面只能用jsp了', '3');
INSERT INTO `project_info` VALUES ('15', '分页功能有了', '2');
INSERT INTO `project_info` VALUES ('16', '分页拉', '1');
INSERT INTO `project_info` VALUES ('17', '开启二级缓存了', '1');
INSERT INTO `project_info` VALUES ('19', '由于二级缓存可能存在的脏缓存，放弃了', '2');
INSERT INTO `project_info` VALUES ('20', '现在测试页面行不行', '1');
INSERT INTO `project_info` VALUES ('23', '3423', '1');
INSERT INTO `project_info` VALUES ('24', '斗地主', '1');

-- ----------------------------
-- Table structure for proto
-- ----------------------------
DROP TABLE IF EXISTS `proto`;
CREATE TABLE `proto` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` bigint(11) unsigned NOT NULL,
  `cmd` int(11) unsigned NOT NULL,
  `name` varchar(225) NOT NULL,
  `namespace` varchar(225) DEFAULT NULL,
  `describes` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proto
-- ----------------------------
INSERT INTO `proto` VALUES ('1', '1', '1', 'Pro_Login', 'UnityNetworkLib', '登录请求');
INSERT INTO `proto` VALUES ('2', '1', '2', 'SPro_Login', 'UnityNetworkLib', '登录响应');
INSERT INTO `proto` VALUES ('3', '1', '3', 'Pro_Requst', 'UnityNetworkLib', '通用请求');
INSERT INTO `proto` VALUES ('4', '1', '4', 'SPro_Response', 'UnityNetworkLib', '通用响应');
INSERT INTO `proto` VALUES ('5', '1', '5', 'SPro_RoleAttribute', 'UnityNetworkLib', '角色信息');
INSERT INTO `proto` VALUES ('6', '2', '1', 'Pro_Login', 'UnityNetworkLib', '登录请求');
INSERT INTO `proto` VALUES ('7', '2', '2', 'SPro_Login', 'UnityNetworkLib', '登录响应');
INSERT INTO `proto` VALUES ('8', '1', '6', 'Model_Position', 'UnityNetworkLib', '玩家位置信息');
INSERT INTO `proto` VALUES ('9', '1', '7', 'Model_Speed2', 'UnityNetworkLib', '速度');
INSERT INTO `proto` VALUES ('14', '2', '1', '56', '123', '123');
INSERT INTO `proto` VALUES ('15', '2', '1', '1', '1', '1');
INSERT INTO `proto` VALUES ('16', '24', '1', 'user', 'userspace', '2');

-- ----------------------------
-- Table structure for proto_field
-- ----------------------------
DROP TABLE IF EXISTS `proto_field`;
CREATE TABLE `proto_field` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `proto_id` bigint(20) unsigned NOT NULL,
  `name` text NOT NULL,
  `type` tinyint(4) NOT NULL,
  `extend` bigint(20) unsigned DEFAULT NULL COMMENT '扩展类型id',
  `is_array` tinyint(4) unsigned DEFAULT '0',
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proto_field
-- ----------------------------
INSERT INTO `proto_field` VALUES ('1', '1', 'username', '5', null, '0', '用户名');
INSERT INTO `proto_field` VALUES ('2', '1', 'password', '5', null, '0', '密码');
INSERT INTO `proto_field` VALUES ('3', '2', 'token', '5', null, '0', '连接标记');
INSERT INTO `proto_field` VALUES ('4', '2', 'result', '1', null, '0', '登录结果：0成功、1失败');
INSERT INTO `proto_field` VALUES ('5', '3', 'type', '2', null, '0', '请求类型');
INSERT INTO `proto_field` VALUES ('6', '3', 'intValue', '3', null, '0', '整形数据');
INSERT INTO `proto_field` VALUES ('7', '3', 'strValue', '5', null, '0', '字符串数据');
INSERT INTO `proto_field` VALUES ('8', '4', 'type', '2', null, '0', '请求类型');
INSERT INTO `proto_field` VALUES ('9', '4', 'intValue', '3', null, '0', '整形数据');
INSERT INTO `proto_field` VALUES ('10', '4', 'strValue', '5', null, '0', '字符串数据');
INSERT INTO `proto_field` VALUES ('11', '5', 'nickname', '5', null, '0', '玩家昵称');
INSERT INTO `proto_field` VALUES ('12', '5', 'level', '1', null, '0', '玩家等级');
INSERT INTO `proto_field` VALUES ('13', '5', 'job', '1', null, '0', '玩家职业：0射手、1剑士、2魔导师、3猎人、4刺客');
INSERT INTO `proto_field` VALUES ('14', '5', 'sex', '1', null, '0', '玩家性别：0男、1女');
INSERT INTO `proto_field` VALUES ('15', '5', 'position', '9', '8', '0', '玩家当前位置信息');
INSERT INTO `proto_field` VALUES ('16', '5', 'money', '3', null, '0', '玩家金币数量');
INSERT INTO `proto_field` VALUES ('17', '6', 'username', '5', null, '0', null);
INSERT INTO `proto_field` VALUES ('18', '6', 'password', '5', null, '0', null);
INSERT INTO `proto_field` VALUES ('19', '7', 'token', '5', null, '0', null);
INSERT INTO `proto_field` VALUES ('20', '7', 'result', '1', null, '0', '登录结果：0成功、1失败');
INSERT INTO `proto_field` VALUES ('21', '8', 'x', '6', null, '0', 'x坐标');
INSERT INTO `proto_field` VALUES ('22', '8', 'y', '6', null, '0', 'y坐标');
INSERT INTO `proto_field` VALUES ('23', '8', 'z', '6', null, '0', 'z坐标');
INSERT INTO `proto_field` VALUES ('24', '8', 'map_id', '1', null, '0', '地图id');
INSERT INTO `proto_field` VALUES ('25', '9', 'x', '6', null, '0', null);
INSERT INTO `proto_field` VALUES ('26', '9', 'y', '6', null, '0', null);
INSERT INTO `proto_field` VALUES ('27', '9', 'z', '6', null, '0', null);
INSERT INTO `proto_field` VALUES ('28', '14', '字段1', '12', null, null, '');
INSERT INTO `proto_field` VALUES ('29', '14', '字段2', '12', null, null, '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin');
INSERT INTO `user` VALUES ('2', 'admin_2', 'admin_2', 'admin');
INSERT INTO `user` VALUES ('3', 'admin_3', 'admin_3', 'admin');
INSERT INTO `user` VALUES ('4', '嘟嘟', 'test', 'test');

-- ----------------------------
-- Table structure for user_project
-- ----------------------------
DROP TABLE IF EXISTS `user_project`;
CREATE TABLE `user_project` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户名',
  `project_id` bigint(20) unsigned NOT NULL COMMENT '跟用户关联的项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_project
-- ----------------------------
INSERT INTO `user_project` VALUES ('1', '1', '1');
INSERT INTO `user_project` VALUES ('2', '1', '2');
INSERT INTO `user_project` VALUES ('3', '3', '1');
INSERT INTO `user_project` VALUES ('4', '3', '2');
INSERT INTO `user_project` VALUES ('6', '1', '14');
INSERT INTO `user_project` VALUES ('7', '1', '15');
INSERT INTO `user_project` VALUES ('8', '1', '16');
INSERT INTO `user_project` VALUES ('9', '1', '17');
INSERT INTO `user_project` VALUES ('11', '1', '19');
INSERT INTO `user_project` VALUES ('12', '1', '20');
INSERT INTO `user_project` VALUES ('15', '1', '23');
INSERT INTO `user_project` VALUES ('16', '4', '24');