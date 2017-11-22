SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `author_id` int(10) unsigned NOT NULL COMMENT '著者ID',
  `name` varchar(128) NOT NULL COMMENT '著者名',
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='著者';

DROP TABLE IF EXISTS `publisher`;
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
  `author_id` int(10) unsigned NOT NULL COMMENT '著者ID',
  PRIMARY KEY (`book_id`),
  KEY `publisher_id` (`publisher_id`),
  KEY `book_fk_author` (`author_id`),
  CONSTRAINT `book_fk_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍';

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` int(10) unsigned NOT NULL COMMENT '住所ID',
  `city_name` varchar(128) NOT NULL COMMENT '都市名',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='住所';

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(10) unsigned NOT NULL COMMENT '会員ID',
  `name` varchar(128) NOT NULL COMMENT '氏名',
  `address_id` int(10) unsigned NOT NULL COMMENT '住所ID',
  PRIMARY KEY (`member_id`),
  KEY `member_fk_address` (`address_id`),
  CONSTRAINT `member_fk_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会員';

DROP TABLE IF EXISTS `hobby`;
CREATE TABLE `hobby` (
  `hobby_id` int(10) unsigned NOT NULL COMMENT '趣味ID',
  `name` varchar(128) NOT NULL COMMENT '趣味',
  PRIMARY KEY (`hobby_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='趣味';

DROP TABLE IF EXISTS `member_hobby`;
CREATE TABLE `member_hobby` (
  `member_id` int(10) unsigned NOT NULL COMMENT '会員ID',
  `hobby_id` int(10) unsigned NOT NULL COMMENT '趣味ID',
  PRIMARY KEY (`member_id`,`hobby_id`),
  KEY `member_hobby_fk_hobby` (`hobby_id`),
  CONSTRAINT `member_hobby_fk_hobby` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`hobby_id`),
  CONSTRAINT `member_hobby_fk_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会員趣味';

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `review_id` int(10) unsigned NOT NULL COMMENT 'レビューID',
  `book_id` int(10) unsigned NOT NULL COMMENT '書籍ID',
  `member_id` int(10) unsigned NOT NULL COMMENT '会員ID',
  `rank` char(1) NOT NULL COMMENT 'ランク',
  `comment` varchar(512) NOT NULL COMMENT 'コメント',
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_unique` (`book_id`,`member_id`),
  KEY `review_fk_member` (`member_id`),
  CONSTRAINT `review_fk_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `review_fk_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='レビュー';

SET FOREIGN_KEY_CHECKS = 1;