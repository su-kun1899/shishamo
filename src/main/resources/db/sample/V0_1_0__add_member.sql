CREATE TABLE `member` (
  `member_id` int(10) unsigned NOT NULL COMMENT '会員ID',
  `name` varchar(128) NOT NULL COMMENT '氏名',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会員';