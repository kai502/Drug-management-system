/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : drugadministrationdb

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 09/12/2023 16:26:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `did` int(0) NOT NULL AUTO_INCREMENT COMMENT '药品id',
  `sid` int(0) NOT NULL COMMENT '供应商id',
  `dname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '药品名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '药品类型',
  `size` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '药品规格',
  `number` int(0) NOT NULL COMMENT '药品数量',
  `price` int(0) NOT NULL COMMENT '药品价格',
  `valid` datetime(0) NULL DEFAULT NULL COMMENT '头条发布时间',
  `details` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '药品描述',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` int(0) NULL DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (1, 13, '阿莫西林', '处方', '每盒4片', 25, 30, '2024-09-26 00:00:00', '新华视点”记者近日走进一些高校发现，部分学生身体素质堪忧。很多男生做引体向上非常吃力，立定跳远跳不远，不少学生短跑爆发力不足，存在“长跑恐惧”。', 1, 0);
INSERT INTO `drug` VALUES (2, 2, '阿莫西林2', '处方', '每盒3片', 15, 130, '2024-02-02 00:00:00', '新华视点”记者近日走进一些高校发现，部分学生身体素质堪忧。很多男生做引体向上非常吃力，立定跳远跳不远，不少学生短跑爆发力不足，存在“长跑恐惧”。', 1, 0);
INSERT INTO `drug` VALUES (3, 3, '阿莫西林3', '非处方', '每盒2片', 25, 230, '2024-03-03 00:00:00', '新华视点”记者近日走进一些高校发现，部分学生身体素质堪忧。很多男生做引体向上非常吃力，立定跳远跳不远，不少学生短跑爆发力不足，存在“长跑恐惧”。', 1, 0);
INSERT INTO `drug` VALUES (4, 1, '阿司匹林', '处方', '一盒8片', 10, 30, '2024-08-08 00:00:00', '地球online提醒您：请勿在NPC身上浪费太多时间，避免影响玩家自身情绪，祝各位玩家顺利完成自己的主线，游戏愉快。', 1, 0);
INSERT INTO `drug` VALUES (5, 1, '阿司匹林2', '处方', '一盒8片', 10, 30, '2024-08-08 00:00:00', '地球online提醒您：请勿在NPC身上浪费太多时间，避免影响玩家自身情绪，祝各位玩家顺利完成自己的主线，游戏愉快。', 1, 0);
INSERT INTO `drug` VALUES (6, 1, '阿司匹林3', '处方', '一盒8片', 10, 30, '2024-08-08 00:00:00', '地球online提醒您：请勿在NPC身上浪费太多时间，避免影响玩家自身情绪，祝各位玩家顺利完成自己的主线，游戏愉快。', 1, 0);
INSERT INTO `drug` VALUES (7, 2, '阿司匹林4', '处方', '一盒8片', 10, 30, '2024-08-08 00:00:00', '地球online提醒您：请勿在NPC身上浪费太多时间，避免影响玩家自身情绪，祝各位玩家顺利完成自己的主线，游戏愉快。', 1, 0);
INSERT INTO `drug` VALUES (12, 1, '3', '3', '23', 3, 3, '2023-11-26 00:00:00', '3', 1, 0);
INSERT INTO `drug` VALUES (13, 3, '4', '4', '4', 4, 4, '2023-11-26 00:00:00', '4', 1, 0);
INSERT INTO `drug` VALUES (14, 3, '5', '5', '5', 5, 5, '2023-11-26 00:00:00', '5', 1, 0);
INSERT INTO `drug` VALUES (15, 3, '6', '6', '6', 6, 6, '2023-11-26 00:00:00', '6', 1, 0);
INSERT INTO `drug` VALUES (16, 1, '8', '8', '8', 8, 8, '2023-11-26 00:00:00', '8', 1, 1);
INSERT INTO `drug` VALUES (17, 3, '9', '9', '9', 9, 9, '2023-11-26 00:00:00', '9', 1, 0);
INSERT INTO `drug` VALUES (23, 1, '药品2', '处方', '一盒', 20, 222, '2023-11-30 00:00:00', '123123阿萨大大飒飒的', 1, 0);
INSERT INTO `drug` VALUES (24, 2, '药品3', '非处方', '一片', 33, 33, '2023-11-27 00:00:00', '啊阿斯顿打撒阿迪斯', 1, 0);
INSERT INTO `drug` VALUES (25, 3, '药品4', '非处方', '一大片', 44, 44, '2023-11-30 00:00:00', '1撒旦倒萨大大飒飒的', 1, 0);
INSERT INTO `drug` VALUES (26, 1, '阿司匹林5', '处方', '一盒8片', 10, 30, '2024-05-05 00:00:00', '地球online提醒您：请勿在NPC身上浪费太多时间，避免影响玩家自身情绪，祝各位玩家顺利完成自己的主线，游戏愉快。', 1, 0);
INSERT INTO `drug` VALUES (27, 1, 'ce1', '处方', '123', 1, 1, '2023-11-29 00:00:00', '123123', 1, 0);
INSERT INTO `drug` VALUES (28, 1, 'ce2', '处方', '123', 2, 3, '2023-11-29 00:00:00', 'adadsad', 1, 0);
INSERT INTO `drug` VALUES (29, 1, 'ce3', '处方', '123', 0, 0, '2023-11-30 00:00:00', '123123', 1, 0);
INSERT INTO `drug` VALUES (30, 2, '999感冒灵', '非处方', '一盒10袋', 5, 10, '2023-11-28 00:00:00', '治疗感冒', 1, 0);
INSERT INTO `drug` VALUES (31, 1, '123123', '处方', '123123', 123, 1123, '2023-11-28 00:00:00', '123123', 1, 1);
INSERT INTO `drug` VALUES (32, 4, '测试供应商', '非处方', '一盒', 22, 22, '2023-11-30 00:00:00', '啊实打实大苏打', 1, 0);
INSERT INTO `drug` VALUES (33, 4, '测试供应商2', '非处方', '一哥', 33, 33, '2023-12-01 00:00:00', '123123阿达打撒打撒', 1, 0);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `pid` int(0) NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `did` int(0) NOT NULL COMMENT '药品id',
  `article` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '药品问题描述',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '药品问题时间',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` int(0) NULL DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (1, 1, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (3, 1, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 1);
INSERT INTO `problem` VALUES (4, 2, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (5, 2, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (6, 3, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (7, 3, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (8, 4, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (9, 4, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (10, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (11, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (12, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (13, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (14, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (15, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (16, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (17, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (18, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 1);
INSERT INTO `problem` VALUES (19, 5, '该药品存在较大的副作用', '2024-08-08 00:00:00', 1, 1);
INSERT INTO `problem` VALUES (20, 1, '', '2023-11-01 00:00:00', 1, 1);
INSERT INTO `problem` VALUES (21, 1, '已经过期了', '2023-11-01 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (22, 2, '有很大问题', '2023-11-01 00:00:00', 1, 0);
INSERT INTO `problem` VALUES (23, 7, '有很多的问题', '2023-12-01 00:00:00', 1, 0);

-- ----------------------------
-- Table structure for receipt
-- ----------------------------
DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt`  (
  `rid` int(0) NOT NULL AUTO_INCREMENT COMMENT '单据id',
  `did` int(0) NOT NULL,
  `type` int(0) NOT NULL COMMENT '单据类型(出库/入库)',
  `number` int(0) NOT NULL COMMENT '交易数量',
  `administrator` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '药品问题描述',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '交易时间',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` int(0) NULL DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receipt
-- ----------------------------
INSERT INTO `receipt` VALUES (1, 1, 1, 5, '用户1', '2023-08-08 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (2, 1, 1, 5, '用户1', '2023-08-08 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (3, 1, 1, 5, '用户1', '2023-08-08 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (4, 1, 0, 5, '用户1', '2023-08-08 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (5, 1, 1, 5, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (6, 1, 1, 5, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (7, 1, 0, 5, '用户1', '2023-08-08 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (8, 1, 0, 5, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (9, 1, 1, 20, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (10, 1, 0, 5, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (11, 23, 0, 2, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (12, 1, 0, 5, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (13, 1, 1, 5, '用户1', '2023-12-02 00:00:00', 1, 0);
INSERT INTO `receipt` VALUES (14, 1, 1, 5, '用户1', '2023-12-02 00:00:00', 1, 0);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `sid` int(0) NOT NULL AUTO_INCREMENT COMMENT '供应商id',
  `sname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '供应商名字',
  `phone_number` bigint(0) NOT NULL COMMENT '供应商电话',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` int(0) NULL DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '华为', 11111112312, 1, 0);
INSERT INTO `supplier` VALUES (2, '华为2', 12312312312, 1, 0);
INSERT INTO `supplier` VALUES (3, '华为3', 12312312312, 1, 0);
INSERT INTO `supplier` VALUES (4, '华为4', 12312312312, 1, 0);
INSERT INTO `supplier` VALUES (12, '小米', 12312312323, 1, 0);
INSERT INTO `supplier` VALUES (13, '小米2', 12312312323, 1, 0);
INSERT INTO `supplier` VALUES (14, '小米3', 12312312323, 1, 0);
INSERT INTO `supplier` VALUES (16, '小米4', 123123123123, 1, 0);
INSERT INTO `supplier` VALUES (17, '苹果1', 123123123, 1, 0);
INSERT INTO `supplier` VALUES (18, '苹果4', 123123123532, 1, 0);
INSERT INTO `supplier` VALUES (19, 'oppo1', 112233, 1, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户登录名',
  `user_pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户登录密码密文',
  `full_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` int(0) NULL DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '2123123123@qq.com', '12312312312', 1, 0);
INSERT INTO `user` VALUES (2, 'user', 'e10adc3949ba59abbe56e057f20f883e', '用户1', '2123123123@qq.com', '11111111111', 1, 0);
INSERT INTO `user` VALUES (3, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员1', '2123123123@qq.com', '12312312312', 1, 0);
INSERT INTO `user` VALUES (10, 'TestUser2', 'e10adc3949ba59abbe56e057f20f883e', '测试用户2', '1@qq.com', '11111111111', 1, 0);
INSERT INTO `user` VALUES (11, 'TestUpdate1', 'e10adc3949ba59abbe56e057f20f883e', '测试用户1', '2022@qq.com', '12356431212', 1, 0);
INSERT INTO `user` VALUES (12, 'TestUser3', 'e10adc3949ba59abbe56e057f20f883e', 'TestUser3', '123@qq.com', '12312312312', 1, 0);
INSERT INTO `user` VALUES (13, 'TestUser1', 'c4ec286b86c877e565cb3dd1dcd91b88', '陈真', '2025989163@qq.com', '13536857706', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
