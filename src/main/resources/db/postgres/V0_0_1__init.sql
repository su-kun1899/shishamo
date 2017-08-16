CREATE TABLE `book` (
  `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
  `title` varchar(128) NOT NULL COMMENT 'タイトル',
  `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
  PRIMARY KEY (`isbn`)
) COMMENT='書籍';