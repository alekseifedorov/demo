CREATE SCHEMA IF NOT EXISTS core;
SET search_path TO core;

CREATE TABLE AUTHOR
  (
     id UUID NOT NULL,
     name VARCHAR(255) NOT NULL,
     CONSTRAINT "PK_author.id" PRIMARY KEY (id)
  );

CREATE TABLE BOOK
  (
     id        UUID NOT NULL,
     title     VARCHAR(255) NOT NULL,
     author_id UUID NOT NULL,
     CONSTRAINT "PK_book.id" PRIMARY KEY (id),
     CONSTRAINT "FK_author.author_id" FOREIGN KEY (author_id) REFERENCES author(id)
  );