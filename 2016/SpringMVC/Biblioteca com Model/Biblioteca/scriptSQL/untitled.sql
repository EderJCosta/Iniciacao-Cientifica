CREATE TABLE "user" (
  id       SERIAL NOT NULL, 
  name     varchar(255) NOT NULL, 
  password varchar(255) NOT NULL, 
  CONSTRAINT name 
    PRIMARY KEY (id));
CREATE TABLE book (
  id          SERIAL NOT NULL, 
  userid      int4 NOT NULL, 
  name        varchar(255) NOT NULL, 
  author      varchar(255) NOT NULL, 
  description varchar(255) NOT NULL, 
  PRIMARY KEY (id));
ALTER TABLE book ADD CONSTRAINT FKbook398344 FOREIGN KEY (userid) REFERENCES "user" (id);

INSERT INTO public."user"(name, password) VALUES ( 'admim', 'admin');

INSERT INTO public.book(userid, name, author, description) VALUES (1, 'Livro 1', 'Autor 1', 'Descrição 1');
INSERT INTO public.book(userid, name, author, description) VALUES (1, 'Livro 2', 'Autor 2', 'Descrição 2');
INSERT INTO public.book(userid, name, author, description) VALUES (1, 'Livro 3', 'Autor 3', 'Descrição 3');

