CREATE TABLE `address` (
  `address_id` int(10) unsigned NOT NULL COMMENT '住所ID',
  `city_name` varchar(128) NOT NULL COMMENT '都市名',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='住所';

ALTER TABLE `member` ADD `address_id`  int(10) unsigned NOT NULL COMMENT '住所ID';
ALTER TABLE `member` ADD CONSTRAINT `member_fk_address` FOREIGN KEY (`address_id`) REFERENCES address(`address_id`);