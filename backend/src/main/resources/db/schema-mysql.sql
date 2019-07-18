DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`
(
  `id`           varchar(36) NOT NULL,
  `pid`          varchar(36)  default NULL COMMENT '父级节点',
  `name`         varchar(100) default NULL COMMENT '类型名称',
  `code`         varchar(100) default NULL COMMENT '类型编码',
  `create_by`    varchar(50)  default NULL COMMENT '创建人',
  `create_time`  datetime     default NULL COMMENT '创建日期',
  `update_by`    varchar(50)  default NULL COMMENT '更新人',
  `update_time`  datetime     default NULL COMMENT '更新日期',
  `sys_org_code` varchar(64)  default NULL COMMENT '所属部门',
  `has_child`    varchar(3)   default NULL COMMENT '是否有子节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统分类表';

DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart`
(
  `id`               varchar(32)  NOT NULL COMMENT 'ID',
  `parent_id`        varchar(32)  default NULL COMMENT '父机构ID',
  `depart_name`      varchar(100) NOT NULL COMMENT '机构/部门名称',
  `depart_name_en`   varchar(500) default NULL COMMENT '英文名',
  `depart_name_abbr` varchar(500) default NULL COMMENT '缩写',
  `depart_order`     int(11) default '0' COMMENT '排序',
  `description`      text COMMENT '描述',
  `org_type`         varchar(10)  default NULL COMMENT '机构类型 1一级部门 2子部门',
  `org_code`         varchar(64)  NOT NULL COMMENT '机构编码',
  `mobile`           varchar(32)  default NULL COMMENT '手机号',
  `fax`              varchar(32)  default NULL COMMENT '传真',
  `address`          varchar(100) default NULL COMMENT '地址',
  `memo`             varchar(500) default NULL COMMENT '备注',
  `status`           varchar(1)   default NULL COMMENT '状态（1启用，0不启用）',
  `create_by`        varchar(32)  default NULL COMMENT '创建人',
  `create_time`      datetime     default NULL COMMENT '创建日期',
  `update_by`        varchar(32)  default NULL COMMENT '更新人',
  `update_time`      datetime     default NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY                `index_depart_parent_id` USING BTREE (`parent_id`),
  KEY                `index_depart_depart_order` USING BTREE (`depart_order`),
  KEY                `index_depart_org_code` USING BTREE (`org_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';


DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
  `id`          varchar(32) NOT NULL,
  `dict_name`   varchar(100) default NULL COMMENT '字典名称',
  `dict_code`   varchar(100) default NULL COMMENT '字典编码',
  `description` varchar(255) default NULL COMMENT '描述',
  `create_by`   varchar(32)  default NULL COMMENT '创建人',
  `create_time` datetime     default NULL COMMENT '创建时间',
  `update_by`   varchar(32)  default NULL COMMENT '更新人',
  `update_time` datetime     default NULL COMMENT '更新时间',
  `type`        int(1) unsigned zerofill default '0' COMMENT '字典类型0为string,1为number',
  PRIMARY KEY (`id`),
  UNIQUE KEY `indextable_dict_code` USING BTREE (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`
(
  `id`          varchar(32) NOT NULL,
  `dict_id`     varchar(32)  default NULL COMMENT '字典id',
  `item_text`   varchar(100) default NULL COMMENT '字典项文本',
  `item_value`  varchar(100) default NULL COMMENT '字典项值',
  `description` varchar(255) default NULL COMMENT '描述',
  `sort_order`  int(10) default NULL COMMENT '排序',
  `status`      int(11) default NULL COMMENT '状态（1启用 0不启用）',
  `create_by`   varchar(32)  default NULL,
  `create_time` datetime     default NULL,
  `update_by`   varchar(32)  default NULL,
  `update_time` datetime     default NULL,
  PRIMARY KEY (`id`),
  KEY           `index_table_dict_id` USING BTREE (`dict_id`),
  KEY           `index_table_sort_order` USING BTREE (`sort_order`),
  KEY           `index_table_dict_status` USING BTREE (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典选项表';

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
  `id`            varchar(32) NOT NULL,
  `log_type`      int(2) default NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content`   varchar(1000) default NULL COMMENT '日志内容',
  `operate_type`  int(2) default NULL COMMENT '操作类型',
  `user_id`       varchar(32)   default NULL COMMENT '操作用户账号',
  `username`      varchar(100)  default NULL COMMENT '操作用户名称',
  `ip`            varchar(100)  default NULL COMMENT 'IP',
  `method`        varchar(500)  default NULL COMMENT '请求java方法',
  `request_url`   varchar(255)  default NULL COMMENT '请求路径',
  `request_param` longtext COMMENT '请求参数',
  `request_type`  varchar(10)   default NULL COMMENT '请求类型',
  `cost_time`     bigint(20) default NULL COMMENT '耗时',
  `create_by`     varchar(32)   default NULL COMMENT '创建人',
  `create_time`   datetime      default NULL COMMENT '创建时间',
  `update_by`     varchar(32)   default NULL COMMENT '更新人',
  `update_time`   datetime      default NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY             `index_table_userid` USING BTREE (`userid`),
  KEY             `index_logt_ype` USING BTREE (`log_type`),
  KEY             `index_operate_type` USING BTREE (`operate_type`),
  KEY             `index_log_type` USING BTREE (`log_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统日志表';

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
  `id`             varchar(32) NOT NULL COMMENT '主键id',
  `parent_id`      varchar(32)  default NULL COMMENT '父id',
  `name`           varchar(100) default NULL COMMENT '菜单标题',
  `url`            varchar(255) default NULL COMMENT '路径',
  `component`      varchar(255) default NULL COMMENT '组件',
  `component_name` varchar(100) default NULL COMMENT '组件名字',
  `redirect`       varchar(255) default NULL COMMENT '一级菜单跳转地址',
  `menu_type`      int(11) default NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms`          varchar(255) default NULL COMMENT '菜单权限编码',
  `perms_type`     varchar(10)  default '0' COMMENT '权限策略1显示2禁用',
  `sort_no`        int(10) default NULL COMMENT '菜单排序',
  `always_show`    tinyint(1) default NULL COMMENT '聚合子路由: 1是0否',
  `icon`           varchar(100) default NULL COMMENT '菜单图标',
  `is_route`       tinyint(1) default '1' COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf`        tinyint(1) default NULL COMMENT '是否叶子节点:    1:是   0:不是',
  `keep_alive`     tinyint(1) default NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `hidden`         int(2) default '0' COMMENT '是否隐藏路由: 0否,1是',
  `description`    varchar(255) default NULL COMMENT '描述',
  `create_by`      varchar(32)  default NULL COMMENT '创建人',
  `create_time`    datetime     default NULL COMMENT '创建时间',
  `update_by`      varchar(32)  default NULL COMMENT '更新人',
  `update_time`    datetime     default NULL COMMENT '更新时间',
  `rule_flag`      int(3) default '0' COMMENT '是否添加数据权限1是0否',
  `status`         varchar(2)   default NULL COMMENT '按钮权限状态(0无效1有效)',
  PRIMARY KEY (`id`),
  KEY              `index_prem_pid` USING BTREE (`parent_id`),
  KEY              `index_prem_is_route` USING BTREE (`is_route`),
  KEY              `index_prem_is_leaf` USING BTREE (`is_leaf`),
  KEY              `index_prem_sort_no` USING BTREE (`sort_no`),
  KEY              `index_prem_del_flag` USING BTREE (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
  `id`          varchar(32)  NOT NULL COMMENT '主键id',
  `role_name`   varchar(200) default NULL COMMENT '角色名称',
  `role_code`   varchar(100) NOT NULL COMMENT '角色编码',
  `description` varchar(255) default NULL COMMENT '描述',
  `create_by`   varchar(32)  default NULL COMMENT '创建人',
  `create_time` datetime     default NULL COMMENT '创建时间',
  `update_by`   varchar(32)  default NULL COMMENT '更新人',
  `update_time` datetime     default NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_role_code` USING BTREE (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
  `id`            varchar(32) NOT NULL,
  `role_id`       varchar(32)   default NULL COMMENT '角色id',
  `permission_id` varchar(32)   default NULL COMMENT '权限id',
  `data_rule_ids` varchar(1000) default NULL,
  PRIMARY KEY (`id`),
  KEY             `index_group_role_per_id` USING BTREE (`role_id`,`permission_id`),
  KEY             `index_group_role_id` USING BTREE (`role_id`),
  KEY             `index_group_per_id` USING BTREE (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
  `id`          varchar(32) NOT NULL COMMENT '主键id',
  `username`    varchar(100) default NULL COMMENT '登录账号',
  `real_name`   varchar(100) default NULL COMMENT '真实姓名',
  `password`    varchar(255) default NULL COMMENT '密码',
  `salt`        varchar(45)  default NULL COMMENT 'md5密码盐',
  `avatar`      varchar(255) default NULL COMMENT '头像',
  `birthday`    datetime     default NULL COMMENT '生日',
  `sex`         int(11) default NULL COMMENT '性别(1 男 2 女)',
  `email`       varchar(45)  default NULL COMMENT '电子邮件',
  `phone`       varchar(45)  default NULL COMMENT '电话',
  `org_code`    varchar(100) default NULL COMMENT '部门code',
  `status`      int(2) default NULL COMMENT '状态(1 正常  2 冻结)',
  `create_by`   varchar(32)  default NULL COMMENT '创建人',
  `create_time` datetime     default NULL COMMENT '创建时间',
  `update_by`   varchar(32)  default NULL COMMENT '更新人',
  `update_time` datetime     default NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_user_name` USING BTREE (`username`),
  KEY           `index_user_status` USING BTREE (`status`),
  KEY           `index_user_del_flag` USING BTREE (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `sys_user_depart`;
CREATE TABLE `sys_user_depart`
(
  `ID`      varchar(32) NOT NULL COMMENT 'id',
  `user_id` varchar(32) default NULL COMMENT '用户id',
  `dep_id`  varchar(32) default NULL COMMENT '部门id',
  PRIMARY KEY (`ID`),
  KEY       `index_depart_groupk_userid` USING BTREE (`user_id`),
  KEY       `index_depart_groupkorgid` USING BTREE (`dep_id`),
  KEY       `index_depart_groupk_uidanddid` USING BTREE (`user_id`,`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门表';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
  `id`      varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) default NULL COMMENT '用户id',
  `role_id` varchar(32) default NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY       `index2_groupuu_user_id` USING BTREE (`user_id`),
  KEY       `index2_groupuu_ole_id` USING BTREE (`role_id`),
  KEY       `index2_groupuu_useridandroleid` USING BTREE (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

