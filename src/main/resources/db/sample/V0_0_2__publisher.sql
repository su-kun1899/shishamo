CREATE TABLE `publisher` (
  `publisher_id` int(10) unsigned NOT NULL COMMENT '出版社ID',
  `name` varchar(40) NOT NULL COMMENT '出版社名',
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社';

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(10) unsigned NOT NULL COMMENT '書籍ID',
  `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
  `title` varchar(128) NOT NULL COMMENT 'タイトル',
  `publisher_id` int(10) unsigned NOT NULL COMMENT '出版社ID',
  `author` varchar(40) NOT NULL COMMENT '著者',
  PRIMARY KEY (`book_id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍';
