CREATE TABLE book
  (
     id        RAW(16) NOT NULL,
     title     VARCHAR2(255) NOT NULL,
     author_id RAW(16) NOT NULL,
     CONSTRAINT "PK_book.id" PRIMARY KEY (id),
     CONSTRAINT "FK_author.author_id" FOREIGN KEY (author_id) REFERENCES author(id)
  );

CREATE TABLE author
  (
     id RAW(16) NOT NULL,
     CONSTRAINT "PK_author.id" PRIMARY KEY (id)
  );