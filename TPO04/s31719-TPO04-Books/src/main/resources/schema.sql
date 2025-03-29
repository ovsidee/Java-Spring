CREATE TABLE IF NOT EXISTS Publisher (
    ID      INT AUTO_INCREMENT PRIMARY KEY,
    Name    VARCHAR(255) NOT NULL,
    Surname VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Book (
    ID           INT AUTO_INCREMENT PRIMARY KEY,
    Title        VARCHAR(255) NOT NULL,
    Publisher_id INT          NOT NULL,
    CONSTRAINT fk_book_publisher FOREIGN KEY (Publisher_id) REFERENCES Publisher (ID)
);

CREATE TABLE IF NOT EXISTS Author (
    ID   INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Book_Author (
    Book_id   INT NOT NULL,
    Author_id INT NOT NULL,
    PRIMARY KEY (Book_id, Author_id),
    CONSTRAINT fk_book_author_book FOREIGN KEY (Book_id) REFERENCES Book (ID) ON DELETE CASCADE,
    CONSTRAINT fk_book_author_author FOREIGN KEY (Author_id) REFERENCES Author (ID) ON DELETE CASCADE
);