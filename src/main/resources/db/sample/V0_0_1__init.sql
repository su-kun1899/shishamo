CREATE TABLE `book` (
  `book_id` int(10) unsigned NOT NULL COMMENT '書籍ID',
  `isbn` bigint(19) UNIQUE NOT NULL COMMENT 'ISBN',
  `title` varchar(128) NOT NULL COMMENT 'タイトル',
  `publisher_id` int(10) unsigned NOT NULL COMMENT '出版社ID',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍';