
CREATE TABLE `tbl_audit_log` (
  `id` varchar(20) NOT NULL ,
  `operator` varchar(64) NULL ,
  `login_ip` varchar(64) NULL ,
  `api_module` varchar(64) NULL ,
  `operation` varchar(64)  NULL ,
  `params` varchar(500) NULL ,
  `operation_time` datetime(3) default now(3) COMMENT '时间(毫秒级别)',
  `log_type` int(10) NULL
);

