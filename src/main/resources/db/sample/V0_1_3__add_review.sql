CREATE TABLE `review` (
  `review_id` INT(10) UNSIGNED NOT NULL
  COMMENT 'レビュー',
  `book_id`   INT(10) UNSIGNED NOT NULL
  COMMENT '書籍ID',
  `member_id` INT(10) UNSIGNED NOT NULL
  COMMENT '会員ID',
  `rank`      CHAR(1)          NOT NULL
  COMMENT 'ランク',
  `comment`   VARCHAR(512)     NOT NULL
  COMMENT 'コメント',
  PRIMARY KEY (`review_id`),
  CONSTRAINT `review_fk_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `review_fk_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  UNIQUE `review_unique` (`book_id`, `member_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='レビュー';
