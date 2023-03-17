# 表结构

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mock_config
-- ----------------------------
DROP TABLE IF EXISTS `mock_config`;
CREATE TABLE `mock_config` (
`id` int NOT NULL AUTO_INCREMENT,
`function_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名称',
`interface_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类名',
`dubbo_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组',
`version` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1.0.0' COMMENT '版本',
`response` json NOT NULL COMMENT '返回值',
`parameter_types` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传参',
PRIMARY KEY (`id`),
UNIQUE KEY `function interface parameter unique` (`function_name`,`interface_name`,`parameter_types`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='dubbo接口参数';

-- ----------------------------
-- Table structure for request_history
-- ----------------------------
DROP TABLE IF EXISTS `request_history`;
CREATE TABLE `request_history` (
`id` int NOT NULL AUTO_INCREMENT,
`request_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求id',
`param_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求类型',
`param_value` varchar(1000) DEFAULT NULL,
`group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'dubbo组',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=324 DEFAULT CHARSET=utf8 COMMENT='请求历史';

SET FOREIGN_KEY_CHECKS = 1;

