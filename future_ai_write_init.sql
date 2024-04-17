/*
 Navicat Premium Data Transfer

 Source Server         : aliyun_onegpt
 Source Server Type    : MySQL
 Source Server Version : 50742 (5.7.42-log)
 Source Host           : rm-f8zspwik1j05r7m56vo.mysql.rds.aliyuncs.com:3306
 Source Schema         : future_ai_write_init

 Target Server Type    : MySQL
 Target Server Version : 50742 (5.7.42-log)
 File Encoding         : 65001

 Date: 21/03/2024 17:45:22
*/
use mysql;
CREATE DATABASE future_ai_write;
use future_ai_write;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_system_dict
-- ----------------------------
DROP TABLE IF EXISTS `biz_system_dict`;
CREATE TABLE `biz_system_dict` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父主键',
  `code` varchar(255) DEFAULT NULL COMMENT '字典码',
  `dict_key` varchar(255) DEFAULT NULL COMMENT '字典值',
  `dict_value` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `dict_data` varchar(255) DEFAULT NULL COMMENT '字典额外数据',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统字典表';

-- ----------------------------
-- Records of biz_system_dict
-- ----------------------------
BEGIN;
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (1, NULL, 'aiwrite_degree', '学历', '-1', NULL, 1, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (2, 1, 'aiwrite_degree', '专科', '1', NULL, 1, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (3, 1, 'aiwrite_degree', '本科', '2', NULL, 2, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (4, 1, 'aiwrite_degree', '研究生', '3', NULL, 3, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (5, NULL, 'aiwrite_major', '专业', '-1', NULL, 2, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (6, 5, 'aiwrite_major', '哲学', '0101', NULL, 1, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (7, 6, 'aiwrite_major', '马克思主义哲学', '010101', NULL, 1, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (8, 6, 'aiwrite_major', '中国哲学', '010102', NULL, 2, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (9, 6, 'aiwrite_major', '外国哲学', '010103', NULL, 3, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (10, 6, 'aiwrite_major', '逻辑学', '010104', NULL, 4, 0);
INSERT INTO `biz_system_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `dict_data`, `sort`, `is_deleted`) VALUES (11, 5, 'aiwrite_major', '经济学', '02', NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for biz_write_ai_model
-- ----------------------------
DROP TABLE IF EXISTS `biz_write_ai_model`;
CREATE TABLE `biz_write_ai_model` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `model_name` varchar(50) DEFAULT NULL COMMENT '模型名称',
  `proxy_url` varchar(50) DEFAULT NULL COMMENT '请求代理地址',
  `api_key` varchar(128) DEFAULT NULL COMMENT '模型授权key',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='AI写作模型配置表';

-- ----------------------------
-- Records of biz_write_ai_model
-- ----------------------------
BEGIN;
INSERT INTO `biz_write_ai_model` (`id`, `model_name`, `proxy_url`, `api_key`, `enabled`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (1726495250526392322, 'futureai-outline-v1', 'http://oneapi.infra.thisonegpt.com', NULL, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_ai_model` (`id`, `model_name`, `proxy_url`, `api_key`, `enabled`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (1726495250526392323, 'futureai-paper-v1', 'http://oneapi.infra.thisonegpt.com', NULL, 1, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for biz_write_config
-- ----------------------------
DROP TABLE IF EXISTS `biz_write_config`;
CREATE TABLE `biz_write_config` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `config_name` varchar(50) DEFAULT NULL COMMENT '配置名',
  `config_key` varchar(255) DEFAULT NULL COMMENT '配置key',
  `config_icon` varchar(255) DEFAULT NULL COMMENT 'icon配置',
  `config_data` varchar(5000) DEFAULT NULL COMMENT '配置数据',
  `material_list` varchar(500) DEFAULT NULL COMMENT '材料清单',
  `is_default` tinyint(4) DEFAULT NULL COMMENT '是否默认',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='AI写作配置表';

-- ----------------------------
-- Records of biz_write_config
-- ----------------------------
BEGIN;
INSERT INTO `biz_write_config` (`id`, `config_name`, `config_key`, `config_icon`, `config_data`, `material_list`, `is_default`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (1, '毕业论文', 'bylw', 'https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_bylw.png', '', '[{\"key\":\"中英文摘要\",\"value\":\"https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_abstract.png\"},{\"key\":\"超长正文\",\"value\":\"https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_content.png\"},{\"key\":\"参考文献\",\"value\":\"https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_reference.png\"},{\"key\":\"致谢模板\",\"value\":\"https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_acknowledgment.png\"}]', 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config` (`id`, `config_name`, `config_key`, `config_icon`, `config_data`, `material_list`, `is_default`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (2, '期末论文', 'qmlw', 'https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_jmlw.png', '', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config` (`id`, `config_name`, `config_key`, `config_icon`, `config_data`, `material_list`, `is_default`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (3, '课程论文', 'kclw', 'https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_kclw.png', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config` (`id`, `config_name`, `config_key`, `config_icon`, `config_data`, `material_list`, `is_default`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (4, '开题报告', 'ktbg', 'https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_bylw.png', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config` (`id`, `config_name`, `config_key`, `config_icon`, `config_data`, `material_list`, `is_default`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (5, '实训报告', 'sxbg', 'https://future-ai-write.oss-cn-beijing.aliyuncs.com/icon/icon_bylw.png', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for biz_write_config_input
-- ----------------------------
DROP TABLE IF EXISTS `biz_write_config_input`;
CREATE TABLE `biz_write_config_input` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `config_id` bigint(20) NOT NULL COMMENT '对应选项id',
  `name` varchar(50) DEFAULT NULL COMMENT '配置名',
  `description` varchar(255) DEFAULT NULL COMMENT '配置描述',
  `form_type` varchar(255) DEFAULT NULL COMMENT '表单类型',
  `api_sign` varchar(255) DEFAULT NULL COMMENT '数据来源',
  `form_key` varchar(255) DEFAULT NULL COMMENT '表单主键',
  `tip` varchar(1000) DEFAULT NULL COMMENT '提示语',
  `span` varchar(1000) DEFAULT NULL COMMENT '响应式适配',
  `rule` varchar(500) DEFAULT NULL COMMENT '验证规则',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='表单配置表';

-- ----------------------------
-- Records of biz_write_config_input
-- ----------------------------
BEGIN;
INSERT INTO `biz_write_config_input` (`id`, `config_id`, `name`, `description`, `form_type`, `api_sign`, `form_key`, `tip`, `span`, `rule`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (1, 1, '选择学历', '', 'radio', 'aiwrite_degree', 'degree', NULL, '24', '{\"required\":true,\"message\":\"请选择学历\",\"trigger\":[\"input\",\"blur\"]}', NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config_input` (`id`, `config_id`, `name`, `description`, `form_type`, `api_sign`, `form_key`, `tip`, `span`, `rule`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (2, 1, '', '搜索或选择专业', 'cascader', 'aiwrite_major', 'major', NULL, '10 m:8 ', '{\"required\":true,\"message\":\"请选择专业\",\"trigger\":[\"input\",\"blur\"]}', NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config_input` (`id`, `config_id`, `name`, `description`, `form_type`, `api_sign`, `form_key`, `tip`, `span`, `rule`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (3, 1, NULL, '请输入完整的论文题目(5字及以上)', 'input', NULL, 'title', NULL, '24 m:16', '{\"required\":true,\"min\":5,\"max\":50,\"message\":\"论文标题长度必须在5-50个字之间\",\"trigger\":[\"input\",\"blur\"]}', NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for biz_write_config_structure
-- ----------------------------
DROP TABLE IF EXISTS `biz_write_config_structure`;
CREATE TABLE `biz_write_config_structure` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `config_id` bigint(20) DEFAULT NULL COMMENT 'AI写作配置id',
  `structure_name` varchar(50) DEFAULT NULL COMMENT '论文结构名称',
  `structure_model_id` bigint(20) DEFAULT NULL COMMENT '论文结构配置模型id',
  `structure_prompt` varchar(500) DEFAULT NULL COMMENT '论文结构提示词',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='AI写作论文结构配置表';

-- ----------------------------
-- Records of biz_write_config_structure
-- ----------------------------
BEGIN;
INSERT INTO `biz_write_config_structure` (`id`, `config_id`, `structure_name`, `structure_model_id`, `structure_prompt`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (1726495250526392322, 1, 'outline', 1726495250526392322, '${title}', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_write_config_structure` (`id`, `config_id`, `structure_name`, `structure_model_id`, `structure_prompt`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `is_deleted`) VALUES (1726495250526392323, 1, 'paper', 1726495250526392323, '{\"title\":\"${title}\",\"outlines\":${outlines}}', 1, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for biz_write_paper
-- ----------------------------
DROP TABLE IF EXISTS `biz_write_paper`;
CREATE TABLE `biz_write_paper` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `config_id` bigint(20) DEFAULT NULL COMMENT 'AI写作配置id',
  `title` varchar(200) DEFAULT NULL COMMENT '论文题目',
  `detail` varchar(500) DEFAULT NULL COMMENT '论文明细',
  `content` text COMMENT '论文内容',
  `generate_time` int(11) DEFAULT NULL COMMENT '生成时间',
  `oss_url` varchar(200) DEFAULT NULL COMMENT 'OSS下载链接',
  `generate_status` tinyint(2) DEFAULT NULL COMMENT '论文生成状态',
  `submit_time` datetime DEFAULT NULL COMMENT '提交时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='AI论文写作记录表';

-- ----------------------------
-- Records of biz_write_paper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for biz_write_paper_structure
-- ----------------------------
DROP TABLE IF EXISTS `biz_write_paper_structure`;
CREATE TABLE `biz_write_paper_structure` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `paper_id` bigint(20) DEFAULT NULL COMMENT '论文记录Id',
  `structure_id` bigint(20) DEFAULT NULL COMMENT '论文结构Id',
  `data` text COMMENT '论文数据',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `finish_time` datetime DEFAULT NULL COMMENT '结束时间',
  `execute_time` int(11) DEFAULT NULL COMMENT '执行时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='AI论文写作记录明细表';

-- ----------------------------
-- Records of biz_write_paper_structure
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
