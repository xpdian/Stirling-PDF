/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : any-pdf

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 22/06/2024 19:08:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('xpstart', 'addtest', 'test', '0/5 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('xpstart', 'xp', 'first', '0/10 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME` ASC, `INSTANCE_NAME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME` ASC, `INSTANCE_NAME` ASC, `REQUESTS_RECOVERY` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_FIRED_TRIGGERS` VALUES ('xpstart', 'NON_CLUSTERED1714649755898', 'xp', 'first', 'NON_CLUSTERED', 1714650041339, 1714650050000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME` ASC, `REQUESTS_RECOVERY` ASC) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('xpstart', 'addtest', 'test', NULL, 'com.monkeylessey.job.MyTestJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('xpstart', 'xp', 'top', NULL, 'com.monkeylessey.job.job.TestJob', '1', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('xpstart', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME` ASC, `CALENDAR_NAME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME` ASC, `TRIGGER_STATE` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('xpstart', 'addtest', 'test', 'addtest', 'test', NULL, 1701224485000, -1, 5, 'PAUSED', 'CRON', 1701224484000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('xpstart', 'xp', 'first', 'xp', 'top', NULL, 1714650050000, 1714650040000, 5, 'ACQUIRED', 'CRON', 1714649755000, 0, NULL, 0, '');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dict_sort` int NULL DEFAULT NULL COMMENT '排序',
  `dict_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典键值',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否是默认值(Y\\N)',
  `dict_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dict_type_id` bigint NULL DEFAULT NULL COMMENT '所属字典类型id',
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (5, NULL, 'A', 'hello', '0', '0', '2023-11-28 18:01:41', 'xp', '2023-11-28 18:01:41', NULL, 7, 0);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dict_sort` int NULL DEFAULT NULL COMMENT '字典排序',
  `dict_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典类别名',
  `dict_type_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典类别key',
  `dict_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典状态0可用，1不可用',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_update` datetime NULL DEFAULT NULL,
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (7, 1, 'DEMO', 'demo', '0', '2023-11-28 17:48:10', 'xp', '2023-11-28 17:48:10', NULL, '测试', 0);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `upload_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传类型',
  `append_position` bigint NULL DEFAULT NULL COMMENT '追加位置，追加上传才有',
  `file_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'oss文件名',
  `content_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `original_filename` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原始名',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, NULL, NULL, 'image/8774e4fe-cf5d-4f94-9b7e-98a747bcb91c', 'image/jpeg', 12091, NULL, '2022-04-26 21:29:14', NULL, 0);
INSERT INTO `sys_file` VALUES (2, NULL, NULL, 'image/70889ea4-d5cf-49f6-946a-c5a97a0c7f5e', 'image/jpeg', 12091, 'QQ截图20210528172950.jpg', '2022-04-26 21:34:53', NULL, 0);
INSERT INTO `sys_file` VALUES (3, NULL, NULL, 'image/20230408161807300', 'image/png', 9271, 'vue+element实现菜单动态渲染.png', '2023-04-08 16:18:08', NULL, 0);
INSERT INTO `sys_file` VALUES (4, NULL, NULL, 'image/20230408162501559', 'image/png', 9271, 'vue+element实现菜单动态渲染.png', '2023-04-08 16:25:02', NULL, 0);
INSERT INTO `sys_file` VALUES (5, NULL, NULL, 'image/20230408162711632', 'image/png', 9271, 'vue+element实现菜单动态渲染.png', '2023-04-08 16:27:12', NULL, 0);
INSERT INTO `sys_file` VALUES (6, NULL, NULL, 'image/20230408162922655', 'image/png', 9271, 'vue+element实现菜单动态渲染.png', '2023-04-08 16:29:22', NULL, 0);
INSERT INTO `sys_file` VALUES (7, NULL, NULL, 'image/20230408163041874', 'image/png', 9271, 'vue+element实现菜单动态渲染.png', '2023-04-08 16:30:41', NULL, 0);
INSERT INTO `sys_file` VALUES (8, NULL, NULL, 'image/20230408163115154', 'image/png', 9271, 'vue+element实现菜单动态渲染.png', '2023-04-08 16:31:16', NULL, 0);
INSERT INTO `sys_file` VALUES (9, NULL, NULL, 'image/20230408163632925', 'image/jpeg', 90167, '头像.jpeg', '2023-04-08 16:36:33', NULL, 0);
INSERT INTO `sys_file` VALUES (10, NULL, NULL, 'image/20230408163936571', 'image/png', 1071, 'cover1.png', '2023-04-08 16:39:37', NULL, 0);
INSERT INTO `sys_file` VALUES (11, NULL, NULL, 'image/20230408164102886', 'image/jpeg', 90167, '头像.jpeg', '2023-04-08 16:41:02', NULL, 0);
INSERT INTO `sys_file` VALUES (14, 'append', 67, 'text/demo.txt', NULL, NULL, 'abc.txt', '2023-07-15 20:07:07', '2023-07-15 20:07:36', 0);

-- ----------------------------
-- Table structure for sys_file_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_log`;
CREATE TABLE `sys_file_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `op_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作内容',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口地址',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件原始名',
  `file_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件key',
  `request_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求IP',
  `deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件处理日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_log
-- ----------------------------
INSERT INTO `sys_file_log` VALUES (2, 1, 'PDF转WORD', 45719, '2024-06-16 21:28:36', '/api/v1/convert/pdf/word', 'java-向培.pdf', 'E:\\tmp\\1230843524.doc', '127.0.0.1', 0);
INSERT INTO `sys_file_log` VALUES (3, 1, 'PDF转WORD', 147456, '2024-06-16 21:33:27', '/api/v1/convert/pdf/word', 'java-向培.pdf', 'E:\\tmp\\1\\7170898375.\\5927983289.do', '127.0.0.1', 0);
INSERT INTO `sys_file_log` VALUES (4, 1, 'PDF转WORD', 270848, '2024-06-16 21:36:18', '/api/v1/convert/pdf/word', 'java-向培.pdf', 'E:\\tmp\\7771751305.do', '127.0.0.1', 0);
INSERT INTO `sys_file_log` VALUES (5, 1, 'PDF转WORD', 45720, '2024-06-16 21:38:10', '/api/v1/convert/pdf/word', 'java-向培.pdf', 'E:\\tmp\\2484668093.doc', '127.0.0.1', 0);
INSERT INTO `sys_file_log` VALUES (6, 1, 'PDF转WORD', 561441, '2024-06-17 11:44:14', '/api/v1/convert/pdf/word', '阿里巴巴Java开发手册（详尽版）.pdf', 'E:\\tmp\\5954738416.doc', '127.0.0.1', 0);
INSERT INTO `sys_file_log` VALUES (7, 1, 'PDF转WORD', 710111, '2024-06-17 23:06:40', '/api/v1/convert/pdf/word', '阿里巴巴Java开发手册（详尽版）_convertedToPDF.pdf', 'E:\\tmp\\6556531259.doc', '[0:0:0:0:0:0:0:1]', 0);
INSERT INTO `sys_file_log` VALUES (8, 1, 'PDF转WORD', 561459, '2024-06-18 21:26:45', '/api/v1/convert/pdf/word', '阿里巴巴Java开发手册（详尽版）.pdf', 'E:\\tmp\\0013596793.doc', '[0:0:0:0:0:0:0:1]', 0);

-- ----------------------------
-- Table structure for sys_gen_set
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_set`;
CREATE TABLE `sys_gen_set`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '代码生成配置JSON',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_gen_set
-- ----------------------------
INSERT INTO `sys_gen_set` VALUES (1, 'sys_gen_set', '{\"tableName\":\"sys_gen_set\",\"whichModule\":\"dev-test\",\"voPath\":\"/templates/vo.java.vm\",\"formPath\":\"/templates/form.java.vm\",\"addGroupPath\":\"com.monkeylessey.group.AddGroup\",\"updateGroupPath\":\"com.monkeylessey.group.UpdateGroup\",\"responseClassName\":\"Result\",\"responseClassPath\":\"com.monkeylessey.response.Result\",\"pageClassName\":\"PageData\",\"pagePath\":\"com.monkeylessey.domain\",\"columns\":[{\"columnName\":\"id\",\"propertyName\":\"id\",\"columnType\":\"Integer\",\"orderNum\":1,\"required\":\"NO\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":false,\"editRequired\":false,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"table_name\",\"propertyName\":\"tableName\",\"columnType\":\"String\",\"orderNum\":2,\"required\":\"NO\",\"comment\":\"表名\",\"stringLength\":255,\"addRequired\":true,\"editRequired\":true,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"content\",\"propertyName\":\"content\",\"columnType\":\"String\",\"orderNum\":3,\"required\":\"NO\",\"comment\":\"代码生成配置JSON\",\"stringLength\":1500,\"addRequired\":true,\"editRequired\":true,\"search\":true,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null}]}');
INSERT INTO `sys_gen_set` VALUES (2, 'sys_role_menu', '{\"tableName\":\"sys_role_menu\",\"whichModule\":\"dev-test\",\"voPath\":\"/templates/vo.java.vm\",\"formPath\":\"/templates/form.java.vm\",\"addGroupPath\":\"com.monkeylessey.group.AddGroup\",\"updateGroupPath\":\"com.monkeylessey.group.UpdateGroup\",\"responseClassName\":\"Result\",\"responseClassPath\":\"com.monkeylessey.response.Result\",\"pageClassName\":\"PageData\",\"pagePath\":\"com.monkeylessey.domain\",\"columns\":[{\"columnName\":\"id\",\"propertyName\":\"id\",\"columnType\":\"Long\",\"orderNum\":1,\"required\":\"NO\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":false,\"editRequired\":true,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"role_id\",\"propertyName\":\"roleId\",\"columnType\":\"Long\",\"orderNum\":2,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"search\":true,\"searchType\":null,\"refTableName\":\"sys_role\",\"refTableVoName\":\"SysRole\",\"refTableProperty\":\"sysRole\",\"refColumn\":\"id\",\"refType\":null,\"logic\":null,\"returnColumns\":[\"id\",\"role_name\"],\"returnColumnList\":[{\"column\":\"id\",\"property\":\"id\"},{\"column\":\"role_name\",\"property\":\"roleName\"}]},{\"columnName\":\"menu_id\",\"propertyName\":\"menuId\",\"columnType\":\"String\",\"orderNum\":3,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":500,\"addRequired\":true,\"editRequired\":true,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null}]}');
INSERT INTO `sys_gen_set` VALUES (4, 'sys_role', '{\"tableName\":\"sys_role\",\"whichModule\":\"dev-test\",\"voPath\":\"/templates/vo.java.vm\",\"formPath\":\"/templates/form.java.vm\",\"addGroupPath\":\"com.monkeylessey.group.AddGroup\",\"updateGroupPath\":\"com.monkeylessey.group.UpdateGroup\",\"responseClassName\":\"Result\",\"responseClassPath\":\"com.monkeylessey.response.Result\",\"pageClassName\":\"PageData\",\"pagePath\":\"com.monkeylessey.domain\",\"columns\":[{\"columnName\":\"id\",\"propertyName\":\"id\",\"columnType\":\"Long\",\"orderNum\":1,\"required\":\"NO\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":false,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"role_name\",\"propertyName\":\"roleName\",\"columnType\":\"String\",\"orderNum\":2,\"required\":\"YES\",\"comment\":\"角色名\",\"stringLength\":20,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"role_key\",\"propertyName\":\"roleKey\",\"columnType\":\"String\",\"orderNum\":3,\"required\":\"YES\",\"comment\":\"角色key\",\"stringLength\":20,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"status\",\"propertyName\":\"status\",\"columnType\":\"String\",\"orderNum\":4,\"required\":\"YES\",\"comment\":\"0可用，1停用\",\"stringLength\":1,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"select\",\"dataSourceId\":7,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"order_num\",\"propertyName\":\"orderNum\",\"columnType\":\"Integer\",\"orderNum\":5,\"required\":\"YES\",\"comment\":\"排序\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"gmt_create\",\"propertyName\":\"gmtCreate\",\"columnType\":\"LocalDateTime\",\"orderNum\":6,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"create_by\",\"propertyName\":\"createBy\",\"columnType\":\"String\",\"orderNum\":7,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":30,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"gmt_update\",\"propertyName\":\"gmtUpdate\",\"columnType\":\"LocalDateTime\",\"orderNum\":8,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"update_by\",\"propertyName\":\"updateBy\",\"columnType\":\"String\",\"orderNum\":9,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":30,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null},{\"columnName\":\"remark\",\"propertyName\":\"remark\",\"columnType\":\"String\",\"orderNum\":11,\"required\":\"YES\",\"comment\":\"备注\",\"stringLength\":100,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":null,\"searchType\":null,\"refTableName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":null,\"returnColumnList\":null}]}');
INSERT INTO `sys_gen_set` VALUES (5, 'sys_dict_type', '{\"tableName\":\"sys_dict_type\",\"whichModule\":\"dev-test\",\"voPath\":\"/templates/vo.java.vm\",\"formPath\":\"/templates/form.java.vm\",\"addGroupPath\":\"com.monkeylessey.group.AddGroup\",\"updateGroupPath\":\"com.monkeylessey.group.UpdateGroup\",\"responseClassName\":\"Result\",\"responseClassPath\":\"com.monkeylessey.response.Result\",\"pageClassName\":\"PageData\",\"pagePath\":\"com.monkeylessey.domain\",\"columns\":[{\"columnName\":\"id\",\"propertyName\":\"id\",\"columnType\":\"Long\",\"orderNum\":1,\"required\":\"NO\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":false,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"dict_sort\",\"propertyName\":\"dictSort\",\"columnType\":\"Integer\",\"orderNum\":2,\"required\":\"YES\",\"comment\":\"字典排序\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":\"sys_job\",\"refTableSimpleName\":null,\"refTableVoName\":\"SysJob\",\"refTableProperty\":\"sysJob\",\"refColumn\":\"job_key\",\"refType\":\"OTO\",\"logic\":null,\"returnColumns\":[\"job_group\",\"job_key\",\"job_status\"],\"returnColumnList\":[{\"column\":\"job_group\",\"property\":\"jobGroup\"},{\"column\":\"job_key\",\"property\":\"jobKey\"},{\"column\":\"job_status\",\"property\":\"jobStatus\"}]},{\"columnName\":\"dict_type_name\",\"propertyName\":\"dictTypeName\",\"columnType\":\"String\",\"orderNum\":3,\"required\":\"YES\",\"comment\":\"字典类别名\",\"stringLength\":50,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"dict_type_key\",\"propertyName\":\"dictTypeKey\",\"columnType\":\"String\",\"orderNum\":4,\"required\":\"YES\",\"comment\":\"字典类别key\",\"stringLength\":50,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"dict_status\",\"propertyName\":\"dictStatus\",\"columnType\":\"String\",\"orderNum\":5,\"required\":\"YES\",\"comment\":\"字典状态0可用，1不可用\",\"stringLength\":1,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"gmt_create\",\"propertyName\":\"gmtCreate\",\"columnType\":\"LocalDateTime\",\"orderNum\":6,\"required\":\"YES\",\"comment\":\"创建时间\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"create_by\",\"propertyName\":\"createBy\",\"columnType\":\"String\",\"orderNum\":7,\"required\":\"YES\",\"comment\":\"创建人\",\"stringLength\":30,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"gmt_update\",\"propertyName\":\"gmtUpdate\",\"columnType\":\"LocalDateTime\",\"orderNum\":8,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":null,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"update_by\",\"propertyName\":\"updateBy\",\"columnType\":\"String\",\"orderNum\":9,\"required\":\"YES\",\"comment\":\"\",\"stringLength\":30,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null},{\"columnName\":\"remark\",\"propertyName\":\"remark\",\"columnType\":\"String\",\"orderNum\":10,\"required\":\"YES\",\"comment\":\"备注\",\"stringLength\":100,\"addRequired\":true,\"editRequired\":true,\"inputWay\":\"input\",\"dataSourceId\":null,\"search\":false,\"searchType\":null,\"refTableName\":null,\"refTableSimpleName\":null,\"refTableVoName\":null,\"refTableProperty\":null,\"refColumn\":null,\"refType\":null,\"logic\":null,\"returnColumns\":[],\"returnColumnList\":null}]}');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `job_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务key',
  `job_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `cron_express` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `gmt_create` datetime NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `job_class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'job类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (3, 'test', 'addtest', '1', '0/5 * * * * ?', '2022-06-09 21:54:01', 'xp', '2023-11-29 10:21:25', 'xp', 0, '测试', 'com.monkeylessey.job.MyTestJob');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单分类,0目录，1菜单，2按钮',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0可用，1停用',
  `menu_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `router_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由名称',
  `router_component` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由对应组件',
  `permission` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `parent_id` int NULL DEFAULT 0,
  `gmt_create` datetime NULL DEFAULT NULL,
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  `menu_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (24, '菜单管理', '1', '0', '/menu', 'Menu', 'MenuView', NULL, 1, 84, '2022-06-03 18:07:33', 'xp', '2024-03-02 14:13:35', 'xp', 0, 'el-icon-s-unfold');
INSERT INTO `sys_menu` VALUES (25, '用户管理', '1', '0', '/user', 'User', 'UserView', '', 2, 84, '2022-06-03 18:08:02', 'xp', '2024-03-02 14:14:05', 'xp', 0, 'el-icon-s-custom');
INSERT INTO `sys_menu` VALUES (26, '角色管理', '1', '0', '/role', 'Role', 'RoleView', NULL, 3, 84, '2022-06-03 18:08:30', 'xp', '2024-03-02 14:14:27', 'xp', 0, 'el-icon-info');
INSERT INTO `sys_menu` VALUES (27, '字典管理', '1', '0', '/dict/type', 'DictType', 'DictTypeView', NULL, 4, 84, '2022-06-03 18:09:02', 'xp', '2024-03-02 14:14:44', 'xp', 0, 'el-icon-notebook-1');
INSERT INTO `sys_menu` VALUES (28, '代码生成', '1', '0', '/gen', 'Gen', 'CodeGenView', NULL, 5, 84, '2022-06-03 18:09:34', 'xp', '2024-03-02 14:15:02', 'xp', 0, 'el-icon-folder-opened');
INSERT INTO `sys_menu` VALUES (29, '定时任务', '1', '0', '/quartz', 'Quartz', 'QuartzView', '', 6, 84, '2022-06-07 20:54:03', 'xp', '2024-03-02 14:15:19', 'xp', 0, 'el-icon-help');
INSERT INTO `sys_menu` VALUES (30, '字典数据', '1', '1', '/dict/data/:id', 'DictData', 'DictDataView', '', 7, 84, '2022-06-19 17:29:56', 'xp', '2024-03-02 14:15:34', 'xp', 0, 'el-icon-plus');
INSERT INTO `sys_menu` VALUES (31, '测试用户', '1', '0', '/testUser', 'testUser', 'SysTestUserView', '', NULL, 0, '2023-04-01 16:44:51', 'xp', '2023-04-03 11:49:06', 'xp', 1, 'el-icon-user-solid');
INSERT INTO `sys_menu` VALUES (34, '组织架构', '1', '0', '/org', 'SysOrg', 'SysOrgView', '', NULL, 84, '2023-11-25 18:38:20', 'xp', '2024-03-02 14:13:05', 'xp', 0, 'el-icon-user');
INSERT INTO `sys_menu` VALUES (38, '添加菜单', '2', '0', NULL, '', '', 'menu:add', NULL, 24, '2023-11-27 15:53:10', 'xp', '2023-11-29 16:27:17', 'xp', 0, '');
INSERT INTO `sys_menu` VALUES (39, '修改菜单', '2', '0', NULL, '', '', 'menu:edit', NULL, 24, '2023-11-27 17:01:16', 'xp', '2023-11-28 16:56:02', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (40, '删除菜单', '2', '0', NULL, '', '', 'menu:del', NULL, 24, '2023-11-27 17:12:05', 'xp', '2023-11-28 16:56:18', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (41, '批量删除菜单', '2', '0', NULL, '', '', 'menu:del:batch', NULL, 24, '2023-11-27 17:13:21', 'xp', '2023-11-28 16:56:33', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (42, '添加子级菜单', '2', '0', NULL, '', '', 'menu:add:child', NULL, 24, '2023-11-27 20:12:31', 'xp', '2023-11-28 16:56:49', 'xp', 0, '');
INSERT INTO `sys_menu` VALUES (43, '菜单分页', '2', '0', NULL, '', '', 'menu:page', NULL, 24, '2023-11-27 20:13:54', 'xp', '2023-11-28 16:57:22', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (44, '添加用户', '2', '0', '', NULL, NULL, 'user:add', NULL, 25, '2023-11-27 22:51:14', 'xp', '2023-11-28 15:45:09', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (45, '编辑用户', '2', '0', NULL, NULL, NULL, 'user:edit', NULL, 25, '2023-11-27 22:59:41', 'xp', '2023-11-28 15:45:19', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (46, '删除用户', '2', '0', NULL, NULL, NULL, 'user:del', NULL, 25, '2023-11-27 23:01:35', 'xp', '2023-11-28 15:45:27', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (47, '批量删除', '2', '0', NULL, NULL, NULL, 'user:del:batch', NULL, 25, '2023-11-28 10:43:42', 'xp', '2023-11-28 15:46:51', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (50, '分配角色', '2', '0', NULL, NULL, NULL, 'user:permission', NULL, 25, '2023-11-28 11:28:11', 'xp', '2023-11-28 15:46:14', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (51, '禁用', '2', '0', NULL, NULL, NULL, 'user:forbidden', NULL, 25, '2023-11-28 11:36:43', 'xp', '2023-11-28 15:46:26', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (52, '用户分页', '2', '0', NULL, NULL, NULL, 'user:page', NULL, 25, '2023-11-28 16:42:56', 'xp', '2023-11-28 16:42:56', NULL, 0, '');
INSERT INTO `sys_menu` VALUES (53, '添加角色', '2', '0', NULL, NULL, NULL, 'role:add', NULL, 26, '2023-11-28 17:10:05', 'xp', '2023-11-28 17:10:05', NULL, 0, '');
INSERT INTO `sys_menu` VALUES (54, '修改角色', '2', '0', NULL, NULL, NULL, 'role:edit', NULL, 26, '2023-11-28 17:10:37', 'xp', '2023-11-28 17:10:37', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (55, '删除角色', '2', '0', NULL, NULL, NULL, 'role:del', NULL, 26, '2023-11-28 17:11:04', 'xp', '2023-11-28 17:11:04', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (56, '批量删除', '2', '0', NULL, NULL, NULL, 'role:del:batch', NULL, 26, '2023-11-28 17:11:32', 'xp', '2023-11-28 17:11:32', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (57, '分配权限', '2', '0', NULL, NULL, NULL, 'role:permission', NULL, 26, '2023-11-28 17:12:03', 'xp', '2023-11-28 17:12:03', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (58, '分页', '2', '0', NULL, NULL, NULL, 'role:page', NULL, 26, '2023-11-28 17:12:51', 'xp', '2023-11-28 17:12:51', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (59, '添加字典', '2', '0', NULL, NULL, NULL, 'dict:add', NULL, 27, '2023-11-28 17:29:18', 'xp', '2023-11-28 17:29:18', NULL, 0, '');
INSERT INTO `sys_menu` VALUES (60, '修改字典', '2', '0', NULL, NULL, NULL, 'dict:edit', NULL, 27, '2023-11-28 17:29:44', 'xp', '2023-11-28 17:29:44', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (61, '删除字典', '2', '0', NULL, NULL, NULL, 'dict:del', NULL, 27, '2023-11-28 17:30:24', 'xp', '2023-11-28 17:30:24', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (62, '批量删除', '2', '0', NULL, NULL, NULL, 'dict:del:batch', NULL, 27, '2023-11-28 17:30:46', 'xp', '2023-11-28 17:30:46', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (63, '分页', '2', '0', NULL, NULL, NULL, 'dict:page', NULL, 27, '2023-11-28 17:31:11', 'xp', '2023-11-28 17:31:11', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (64, '数据管理', '2', '0', NULL, NULL, NULL, 'role:data:admin', NULL, 27, '2023-11-28 17:32:01', 'xp', '2023-11-28 17:41:42', 'xp', 0, NULL);
INSERT INTO `sys_menu` VALUES (65, '添加数据', '2', '0', NULL, NULL, NULL, 'dict:data:add', NULL, 30, '2023-11-28 17:50:53', 'xp', '2023-11-28 17:53:16', 'xp', 0, '');
INSERT INTO `sys_menu` VALUES (66, '修改数据', '2', '0', NULL, NULL, NULL, 'dict:data:edit', NULL, 30, '2023-11-28 17:51:16', 'xp', '2023-11-28 17:51:16', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (67, '删除数据', '2', '0', NULL, NULL, NULL, 'dict:data:del', NULL, 30, '2023-11-28 17:51:59', 'xp', '2023-11-28 17:51:59', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (68, '批量删除', '2', '0', NULL, NULL, NULL, 'dict:data:del:batch', NULL, 30, '2023-11-28 17:52:20', 'xp', '2023-11-28 17:52:20', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (69, '分页', '2', '0', NULL, NULL, NULL, 'dict:data:page', NULL, 30, '2023-11-28 17:52:53', 'xp', '2023-11-28 17:52:53', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (70, '添加定时任务', '2', '0', NULL, NULL, NULL, 'job:add', NULL, 29, '2023-11-28 18:16:29', 'xp', '2023-11-28 18:16:29', NULL, 0, '');
INSERT INTO `sys_menu` VALUES (71, '编辑定时任务', '2', '0', NULL, NULL, NULL, 'job:edit', NULL, 29, '2023-11-28 18:16:56', 'xp', '2023-11-28 18:16:56', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (72, '删除定时任务', '2', '0', NULL, NULL, NULL, 'job:del', NULL, 29, '2023-11-28 18:18:52', 'xp', '2023-11-28 18:18:52', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (73, '批量删除', '2', '0', NULL, NULL, NULL, 'job:del:batch', NULL, 29, '2023-11-28 18:19:24', 'xp', '2023-11-28 18:19:24', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (74, '执行一次', '2', '0', NULL, NULL, NULL, 'job:once', NULL, 29, '2023-11-28 18:20:32', 'xp', '2023-11-28 18:20:32', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (75, '暂停任务', '2', '0', NULL, NULL, NULL, 'job:pause', NULL, 29, '2023-11-28 18:22:49', 'xp', '2023-11-28 18:22:49', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (76, '启动任务', '2', '0', NULL, NULL, NULL, 'job:run', NULL, 29, '2023-11-28 18:23:18', 'xp', '2023-11-28 18:23:18', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (77, '分页', '2', '0', NULL, NULL, NULL, 'job:page', NULL, 29, '2023-11-29 09:37:20', 'xp', '2023-11-29 09:37:20', NULL, 0, '');
INSERT INTO `sys_menu` VALUES (78, '添加', '2', '0', NULL, NULL, NULL, 'org:add', NULL, 34, '2023-11-29 10:23:31', 'xp', '2023-11-29 10:23:31', NULL, 0, '');
INSERT INTO `sys_menu` VALUES (79, '修改', '2', '0', NULL, NULL, NULL, 'org:edit', NULL, 34, '2023-11-29 10:23:55', 'xp', '2023-11-29 10:23:55', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (80, '删除', '2', '0', NULL, NULL, NULL, 'org:del', NULL, 34, '2023-11-29 10:24:19', 'xp', '2023-11-29 10:24:19', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (81, '批量删除', '2', '0', NULL, NULL, NULL, 'org:del:batch', NULL, 34, '2023-11-29 10:25:05', 'xp', '2023-11-29 10:25:05', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (82, '分页', '2', '0', NULL, NULL, NULL, 'org:page', NULL, 34, '2023-11-29 10:25:29', 'xp', '2023-11-29 10:25:29', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (83, '添加子项', '2', '0', NULL, NULL, NULL, 'org:add:child', NULL, 34, '2023-11-29 10:26:01', 'xp', '2023-11-29 10:26:01', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (84, '系统管理', '0', '0', '/sys', 'SYS', 'View', '', 1, 0, '2024-03-02 14:12:29', 'xp', '2024-03-02 15:10:27', 'xp', 0, 'el-icon-s-tools');
INSERT INTO `sys_menu` VALUES (86, '首页', '1', '0', '/home', 'Home', 'HomeView', '', 0, 0, '2024-03-02 16:42:18', 'xp', '2024-03-03 16:52:30', 'xp', 0, 'el-icon-s-home');
INSERT INTO `sys_menu` VALUES (117, '字典列表', '2', '0', NULL, NULL, NULL, 'dict:list', NULL, 27, '2024-03-31 15:14:44', 'admin', '2024-03-31 15:21:45', 'admin', 0, '');
INSERT INTO `sys_menu` VALUES (118, '日志管理', '1', '0', '/log', 'Log', 'SysOpLogView', '', 8, 84, '2024-05-02 19:29:12', 'admin', '2024-05-02 19:29:12', NULL, 0, 'el-icon-s-order');
INSERT INTO `sys_menu` VALUES (119, '日志分页', '2', '0', NULL, NULL, NULL, 'sysOpLog:page', 1, 118, '2024-05-02 19:29:47', 'admin', '2024-05-02 19:29:47', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (120, '重置密码', '2', '0', NULL, NULL, NULL, 'user:reset', 8, 25, '2024-05-02 19:33:58', 'admin', '2024-05-02 19:33:58', NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_op_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_op_log`;
CREATE TABLE `sys_op_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `op_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '干了啥',
  `request_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `invoke_method` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行的方法名',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `request_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '谁发起的请求',
  `request_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求ip',
  `request_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `request_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求结果',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `gmt_update` datetime NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_op_log
-- ----------------------------
INSERT INTO `sys_op_log` VALUES (1, '测试', '/test', 'String com.monkeylessey.controller.TestController.test(String,HttpServletRequest)', 'GET', 'xp', '0:0:0:0:0:0:0:1', NULL, 'null', '2022-04-29 16:32:20', NULL, NULL, NULL);
INSERT INTO `sys_op_log` VALUES (2, '测试', '/test', 'String com.monkeylessey.controller.TestController.test(String,HttpServletRequest)', 'GET', 'xp', '0:0:0:0:0:0:0:1', NULL, 'null', '2022-04-29 16:39:03', NULL, NULL, NULL);
INSERT INTO `sys_op_log` VALUES (3, '我来啦', '/sys-job/execute/once', 'ResponseData com.monkeylessey.controller.SysJobController.executeJobOnce(SysJobForm)', 'POST', 'xp', '0:0:0:0:0:0:0:1', NULL, '{\"msg\":\"执行成功\",\"code\":200,\"data\":null}', '2023-03-08 09:17:35', '2023-03-08 09:17:35', 0, NULL);
INSERT INTO `sys_op_log` VALUES (4, '我来啦', '/sys-job/execute/once', 'ResponseData com.monkeylessey.controller.SysJobController.executeJobOnce(SysJobForm)', 'POST', 'xp', '0:0:0:0:0:0:0:1', NULL, '{\"msg\":\"执行成功\",\"code\":200,\"data\":null}', '2023-03-08 09:19:18', '2023-03-08 09:19:18', 0, NULL);
INSERT INTO `sys_op_log` VALUES (5, '类', '/sys-job/execute/once', 'ResponseData com.monkeylessey.controller.SysJobController.executeJobOnce(SysJobForm)', 'POST', 'xp', '0:0:0:0:0:0:0:1', NULL, '{\"msg\":\"执行成功\",\"code\":200,\"data\":null}', '2023-03-08 09:24:28', '2023-03-08 09:24:28', 0, NULL);
INSERT INTO `sys_op_log` VALUES (6, '类', '/sys-job/start/job', 'ResponseData com.monkeylessey.controller.SysJobController.startJob(SysJobForm)', 'POST', 'xp', '0:0:0:0:0:0:0:1', NULL, '{\"msg\":\"启动成功\",\"code\":200,\"data\":null}', '2023-03-08 09:25:30', '2023-03-08 09:25:30', 0, NULL);
INSERT INTO `sys_op_log` VALUES (7, '类', '/sys-job/pause/job', 'ResponseData com.monkeylessey.controller.SysJobController.pauseJob(SysJobForm)', 'POST', 'xp', '0:0:0:0:0:0:0:1', NULL, '{\"msg\":\"暂停成功\",\"code\":200,\"data\":null}', '2023-03-08 09:26:13', '2023-03-08 09:26:13', 0, NULL);
INSERT INTO `sys_op_log` VALUES (8, '类', '/sys-job/page', 'ResponseData com.monkeylessey.controller.SysJobController.getSysJobPage(SysJobForm)', 'GET', 'xp', '0:0:0:0:0:0:0:1', '{\"current\":[\"1\"],\"size\":[\"10\"]}', '{\"msg\":\"\",\"code\":200,\"data\":{\"total\":1,\"records\":[{\"id\":\"3\",\"gmtCreate\":1654782841000,\"createBy\":\"xp\",\"gmtUpdate\":1654919602000,\"updateBy\":\"xp\",\"isDeleted\":\"0\",\"jobGroup\":\"test\",\"jobKey\":\"addtest\",\"jobStatus\":\"1\",\"cronExpress\":\"0/10 * * * * ?\",\"remark\":\"测试\",\"jobClass\":\"com.monkeylessey.job.MyTestJob\"}]}}', '2023-03-08 09:26:13', '2023-03-08 09:26:13', 0, NULL);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `org_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级ID',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_update` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '删除标识',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `leader_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `leader_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人联系电话',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `org_type` int NULL DEFAULT NULL COMMENT '组织、部门',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, '测试组织', 0, '2023-11-25 19:54:22', '2023-11-25 19:54:22', 1, 1, 'xp', '13739464446', 0, 1, NULL, NULL);
INSERT INTO `sys_org` VALUES (2, 'whatF', 0, '2023-11-25 20:38:56', '2023-11-25 20:38:56', 0, 2, '', '', 0, 0, NULL, NULL);
INSERT INTO `sys_org` VALUES (3, '子级', 2, '2023-11-26 16:28:20', '2023-11-26 16:28:20', 0, NULL, '', '', 0, 0, NULL, NULL);
INSERT INTO `sys_org` VALUES (4, '我去啊', 0, '2023-11-26 16:36:43', '2023-11-26 16:36:43', 0, NULL, 'xp', NULL, 0, 1, NULL, NULL);
INSERT INTO `sys_org` VALUES (5, 'sdfghj', 0, '2023-11-26 16:42:58', '2023-11-26 16:42:58', 0, NULL, NULL, NULL, 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色key',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0可用，1停用',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime NULL DEFAULT NULL,
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', '0', 0, '2022-05-24 21:16:41', 'xp', '2024-05-02 19:38:41', 'admin', 0, '最高权限');
INSERT INTO `sys_role` VALUES (6, '测试12', 'testttt', '0', 2, '2022-05-28 18:52:26', 'xp', '2022-05-28 20:37:14', 'xp', 1, '测试');
INSERT INTO `sys_role` VALUES (7, '项目经理', 'PROJECT_MANAGER', '0', 4, '2024-03-10 18:16:46', 'xp', '2024-03-10 18:16:46', NULL, 1, '');
INSERT INTO `sys_role` VALUES (8, '人力资源经理', 'WORKER_MANAGER', '0', 5, '2024-03-10 18:17:12', 'xp', '2024-03-10 18:17:12', NULL, 1, NULL);
INSERT INTO `sys_role` VALUES (9, '普通员工', 'STAFF', '0', 4, '2024-03-24 15:09:59', 'xp', '2024-03-24 15:09:59', NULL, 1, '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `menu_id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (8, 6, '3,4,15,7,16,2', 0);
INSERT INTO `sys_role_menu` VALUES (9, 1, '86,84,24,38,39,40,41,42,43,25,44,45,46,47,50,51,52,120,26,53,54,55,56,57,58,27,59,60,61,62,63,64,117,28,29,70,71,72,73,74,75,76,77,30,65,66,67,68,69,34,78,79,80,81,82,83,118,119', 0);
INSERT INTO `sys_role_menu` VALUES (10, 7, '93,94,95,96,97,98,99,115,107,108,109,110,112,114,86', 0);
INSERT INTO `sys_role_menu` VALUES (11, 9, '114,86', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `belong_org` bigint NULL DEFAULT NULL COMMENT '所属组织',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0男，1女',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像路径',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0可用，1不可用',
  `login_date` datetime NULL DEFAULT NULL COMMENT '上一次登录日期',
  `tenant_id` int NULL DEFAULT NULL COMMENT '租户id',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', NULL, '管理员', '2944397754@qq.com', '13739464447', '1', '$2a$10$KGQsOj4V1rmU/ydyYF8.i.Cd/r8FP1SZRQaysM4TJO/cAZavnfgsa', 'https://xpstart-test.oss-cn-chengdu.aliyuncs.com/image/20230408164102886', '0', NULL, NULL, '2022-04-26 21:34:53', '2023-11-25 10:31:16', NULL, 'xp', 0);
INSERT INTO `sys_user` VALUES (6, 'aaa', NULL, 'xxx', NULL, '13739464446', '0', '$2a$10$PnvQ5hHcgAhFyv4v/imDBeNNGD3qUi.VD8xBnIm0rwNsX7FBWh0hK', NULL, '0', NULL, NULL, '2024-03-10 19:02:28', '2024-03-10 19:22:19', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (7, '张三', NULL, '张三', NULL, '13739464446', '0', '$2a$10$ZsHncimzbGNNhQie6gyr7.WmTw1m0Z.J6kw9Au.VSzPo2Ej1Zr9Re', NULL, '0', NULL, NULL, '2024-03-16 16:00:13', '2024-03-16 16:00:13', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (8, '李四', NULL, '李四', NULL, '13739464446', '0', '$2a$10$lxZHcDf7DLb0p5UHa0PX1uZAfwp2Jvqq.1QrQXxtx49fmCGV4MGdW', NULL, '0', NULL, NULL, '2024-03-16 16:00:37', '2024-03-16 16:00:37', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `role_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, '6,1', '2022-06-03 12:08:10', 0, 'xp');
INSERT INTO `sys_user_role` VALUES (2, 6, '6', '2023-04-07 18:26:42', 0, 'xp');
INSERT INTO `sys_user_role` VALUES (3, 6, '7', '2024-03-10 19:02:28', 0, NULL);
INSERT INTO `sys_user_role` VALUES (4, 7, '7', '2024-03-16 17:45:24', 0, 'xp');
INSERT INTO `sys_user_role` VALUES (5, 8, '9', '2024-03-24 19:14:59', 0, 'admin');

-- ----------------------------
-- Table structure for sys_vip
-- ----------------------------
DROP TABLE IF EXISTS `sys_vip`;
CREATE TABLE `sys_vip`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `end_time` datetime NOT NULL COMMENT '到期时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `vip_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `renew_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '续费价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_vip
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
