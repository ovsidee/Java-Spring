--publisher:
INSERT INTO Publisher (Name, Surname, Address)
VALUES
    ('Arsen', 'Kolopav', 'Khreshchatyk St, Kyiv'),
    ('Artem', 'Gatusta', 'Deribasivska St, Odesa'),
    ('Illya', 'Nesmachniy', 'Svobody Ave, Lviv'),
    ('Vitalii', 'Korytnyi', 'Shevchenka St, Ivano-Frankivsk'),
    ('Maksym', 'Lototskyi', 'Soborna St, Vinnytsia'),
    ('Viktoria', 'Kharchenko', 'Peremohy Ave, Kharkiv'),
    ('Diana', 'Targoni', 'Dniprovska Naberezhna, Kyiv'),
    ('Djiana', 'Korichnevaya', 'Pushkinska St, Kherson'),
    ('Ivan', 'Svin', 'Myru Ave, Chernihiv'),
    ('Slava', 'Larin', 'Zamkova St, Lutsk'),
    ('Katya', 'Vinokurova', 'Osvoboditeley St, Zaporizhzhia'),
    ('Marina', 'Korytna', 'Gorkoho St, Poltava'),
    ('Kevin', 'Kolopav', 'Kyrylivska St, Kyiv'),
    ('Sofia', 'Gillmavnt', 'Ivana Franka St, Lviv'),
    ('Kira', 'Martynenko', 'Gagarina Ave, Dnipro'),
    ('Ihor', 'Bykova', 'Haharina St, Uzhhorod'),
    ('Nasar', 'Chaika', 'Nezalezhnosti St, Ternopil'),
    ('Kamil', 'Mamedov', 'Yevropeiska St, Kropyvnytskyi'),
    ('Egor', 'Misin', 'Bohdana Khmelnytskoho St, Rivne'),
    ('Danya', 'Kiktenko', 'Mykhailivska St, Zhytomyr');

-- book
INSERT INTO Book (Title, Price, Publisher_id)
VALUES ('Spring Boot In Action 5', 50, 1),
       ('Spring Boot In Action 3', 45, 2),
       ('C++ For Kids', 30, 3),
       ('Fight Club', 25, 4),
       ('Interstellar. Science behind.', 40, 5),
       ('Shuttered Island', 35, 6),
       ('Looking for Alaska', 28, 7),
       ('The Bluest Eye', 22, 8),
       ('Mathematical Analysis', 60, 9),
       ('Linear Algebra', 55, 10),
       ('Linear Geometry', 48, 11),
       ('Animal Farm', 20, 12),
       ('Lord of the Flies', 25, 13),
       ('To Kill a Mockingbird', 30, 14),
       ('The Catcher in the Rye', 27, 15),
       ('Artificial Intelligence In Action', 70, 16),
       ('PPJ Lecture', 15, 17),
       ('Business Partner English C1', 45, 18),
       ('The Handmaid', 33, 19),
       ('Beloved', 30, 20);


-- author
INSERT INTO Author (Name)
VALUES ('Hanna Bitsayeva'),
       ('Sviatoslav Vasylenko'),
       ('Kateryna Vynokurova'),
       ('Oleksandr Voroshylo'),
       ('Marianna Herlanets'),
       ('Rostyslav Horiukhin'),
       ('Danii Drobnyi'),
       ('Mariia Katolyk'),
       ('Maksym Kirpikin'),
       ('Natalia Kitaieva'),
       ('Yuliia Klymenko'),
       ('Vitalii Korytnyi'),
       ('Oleksandr Kulish'),
       ('Anastasiia Lohinova'),
       ('Daryna Lutsenko'),
       ('Vasylina Mykhneva'),
       ('Yehor Mashkovskyi'),
       ('Ihor Panchenko'),
       ('Denys Perepechai'),
       ('Artem Ponomarenko'),
       ('Maksym Popov'),
       ('Dmytro Prudko'),
       ('Diana Rychko'),
       ('Yevdokiia Rozdobudko');

-- Book_Author
INSERT INTO Book_Author (Book_id, Author_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6),
       (4, 7),
       (4, 8),
       (5, 9),
       (5, 10),
       (6, 11),
       (6, 12),
       (7, 13),
       (7, 14),
       (8, 15),
       (8, 16),
       (9, 17),
       (9, 18),
       (10, 19),
       (10, 20),
       (11, 1),
       (11, 3),
       (12, 5),
       (12, 7),
       (13, 9),
       (13, 11),
       (14, 13),
       (14, 15),
       (15, 17),
       (15, 19),
       (16, 2),
       (16, 4),
       (17, 6),
       (17, 8),
       (18, 10),
       (18, 12),
       (19, 14),
       (19, 16),
       (20, 18),
       (20, 20);