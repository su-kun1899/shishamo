CREATE TABLE `hobby` (
  `hobby_id` INT(10) UNSIGNED NOT NULL
  COMMENT '趣味ID',
  `name`     VARCHAR(128)     NOT NULL
  COMMENT '趣味',
  PRIMARY KEY (`hobby_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='趣味';

CREATE TABLE `member_hobby` (
  `member_id` INT(10) UNSIGNED NOT NULL
  COMMENT '会員ID',
  `hobby_id`  INT(10) UNSIGNED NOT NULL
  COMMENT '趣味ID',
  PRIMARY KEY (`member_id`, `hobby_id`),
  CONSTRAINT `member_hobby_fk_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `member_hobby_fk_hobby` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`hobby_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='会員趣味';