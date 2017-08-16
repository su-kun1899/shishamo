CREATE TABLE book (
  isbn bigint NOT NULL,
  title varchar(128) NOT NULL,
  publisherid int NOT NULL,
  PRIMARY KEY (isbn)
);

COMMENT ON TABLE book IS '書籍';
COMMENT ON COLUMN book.isbn IS 'ISBN';
COMMENT ON COLUMN book.title IS 'タイトル';
COMMENT ON COLUMN book.publisherid IS '書籍';
