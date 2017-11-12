CREATE TABLE `author` (
  `author_id` int(10) unsigned NOT NULL COMMENT '著者ID',
  `name` varchar(128) NOT NULL COMMENT '著者名',
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='著者';

DELETE FROM book;
ALTER TABLE `book` DROP COLUMN `author`;
ALTER TABLE `book` ADD `author_id`  int(10) unsigned NOT NULL COMMENT '著者ID' AFTER `publisherid`;
ALTER TABLE `book` ADD CONSTRAINT `book_fk_author` FOREIGN KEY (`author_id`) REFERENCES author(`author_id`);