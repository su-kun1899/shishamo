INSERT INTO `address` (address_id, city_name) VALUES (1, 'Tokyo');
INSERT INTO `address` (address_id, city_name) VALUES (2, 'Osaka');

INSERT INTO author (author_id, name) VALUES (1, "Takashi Kadokura");
INSERT INTO author (author_id, name) VALUES (2, "Yusuke Aiba");
INSERT INTO author (author_id, name) VALUES (3, "Yoshiaki Kaneyama");
INSERT INTO author (author_id, name) VALUES (4, "Grant Si Burton");
INSERT INTO author (author_id, name) VALUES (5, "Oscar V. Hasan");

INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (1, 123, 'Momotaro', 1, 1);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (2, 456, 'Kintaro', 1, 2);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (3, 789, 'Urashimataro', 1, 3);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (4, 234, 'Sarukanigassenn', 2, 4);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (5, 567, 'Kaguyahime', 2, 5);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (6, 890, 'Kobutorijisan', 3, 1);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (7, 345, 'Omusubikororin', 3, 2);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (8, 678, 'Turunoongaeshi', 3, 3);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (9, 901, 'Kasajizo', 3, 4);
INSERT INTO book (book_id, isbn, title, publisher_id, author_id) VALUES (10, 112, 'Issunboshi', 3, 5);

INSERT INTO hobby (hobby_id, name) VALUES (1, "football");
INSERT INTO hobby (hobby_id, name) VALUES (2, "running");
INSERT INTO hobby (hobby_id, name) VALUES (3, "video game");
INSERT INTO hobby (hobby_id, name) VALUES (4, "programing");

INSERT INTO member (member_id, name, address_id) VALUES (1, 'Taro Yamada', 1);
INSERT INTO member (member_id, name, address_id) VALUES (2, 'Jiro Sato', 1);
INSERT INTO member (member_id, name, address_id) VALUES (3, 'Hanako Tanaka', 2);

INSERT INTO member_hobby (member_id, hobby_id) VALUES (1, 1);
INSERT INTO member_hobby (member_id, hobby_id) VALUES (1, 2);
INSERT INTO member_hobby (member_id, hobby_id) VALUES (2, 2);
INSERT INTO member_hobby (member_id, hobby_id) VALUES (2, 3);
INSERT INTO member_hobby (member_id, hobby_id) VALUES (3, 3);
INSERT INTO member_hobby (member_id, hobby_id) VALUES (3, 4);

INSERT review (review_id, book_id, member_id, rank, comment) VALUES (1, 1, 1, '5', '史上最高傑作。');
