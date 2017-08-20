CREATE TABLE `publisher` (
  `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
  `name` varchar(40) NOT NULL COMMENT '出版社名',
  PRIMARY KEY (`publisherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社';

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
  `title` varchar(128) NOT NULL COMMENT 'タイトル',
  `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
  `author` varchar(40) NOT NULL COMMENT '著者',
  PRIMARY KEY (`isbn`),
  KEY `publisherid` (`publisherid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍';
