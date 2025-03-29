-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-03-29 16:30:33.625

-- tables
-- Table: Author
CREATE TABLE Author (
    ID integer  DEFAULT as NOT NULL,
    Name varchar2(255)  NOT NULL,
    CONSTRAINT Author_pk PRIMARY KEY (ID)
) ;

-- Table: Book
CREATE TABLE Book (
    ID integer  DEFAULT as NOT NULL,
    Title varchar2(255)  NOT NULL,
    Publisher_id integer  NOT NULL,
    CONSTRAINT Book_pk PRIMARY KEY (ID)
) ;

-- Table: Book_Author
CREATE TABLE Book_Author (
    Book_id integer  NOT NULL,
    Author_id integer  NOT NULL,
    CONSTRAINT Book_Author_pk PRIMARY KEY (Book_id,Author_id)
) ;

-- Table: Publisher
CREATE TABLE Publisher (
    ID integer  DEFAULT as NOT NULL,
    Name varchar2(255)  NOT NULL,
    Surname varchar2(255)  NOT NULL,
    CONSTRAINT Publisher_pk PRIMARY KEY (ID)
) ;

-- foreign keys
-- Reference: fk_book_author_author (table: Book_Author)
ALTER TABLE Book_Author ADD CONSTRAINT fk_book_author_author
    FOREIGN KEY (Author_id)
    REFERENCES Author (ID)
    ON DELETE CASCADE;

-- Reference: fk_book_author_book (table: Book_Author)
ALTER TABLE Book_Author ADD CONSTRAINT fk_book_author_book
    FOREIGN KEY (Book_id)
    REFERENCES Book (ID)
    ON DELETE CASCADE;

-- Reference: fk_book_publisher (table: Book)
ALTER TABLE Book ADD CONSTRAINT fk_book_publisher
    FOREIGN KEY (Publisher_id)
    REFERENCES Publisher (ID);

-- End of file.

