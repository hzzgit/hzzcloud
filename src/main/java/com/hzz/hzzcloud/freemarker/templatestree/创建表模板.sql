CREATE TABLE `tablename` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '调用的转发接口',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `isuse` bit(1) NOT NULL COMMENT '是否启用,0禁用,1启用',
  `deleted` int(11) DEFAULT NULL COMMENT '删除标志,1代表删除,0代表正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='配置表';

CREATE TABLE `tablenamebydep` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mainid` int(11) DEFAULT NULL COMMENT '配置表id',
  `depId` int(11) DEFAULT NULL COMMENT '绑定机构的id',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` int(11) DEFAULT NULL COMMENT '删除标志,0代表未删除,1代表删除',
  PRIMARY KEY (`id`),
  KEY `index_depId` (`depId`)
) ENGINE=InnoDB COMMENT='配置规则机构权限表';


CREATE TABLE `tablenamebyvehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mainid` int(11) DEFAULT NULL COMMENT '配置表id',
  `vehicleId` int(11) DEFAULT NULL COMMENT '车辆主键',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` int(11) DEFAULT NULL COMMENT '删除标志,0代表未删除,1代表删除',
  PRIMARY KEY (`id`),
  KEY `index_vehicleId` (`vehicleId`)
) ENGINE=InnoDB COMMENT='配置规则车辆权限表';

CREATE TABLE `tablenameuserlim` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mainid` int(11) DEFAULT NULL COMMENT '配置表id',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='配置规则授权用户权限表';
