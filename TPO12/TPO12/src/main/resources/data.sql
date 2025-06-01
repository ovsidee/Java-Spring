--author
INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES
('Fyodor',  'Dostoevsky'),
('Margaret','Mitchell'),
('Franz',   'Kafka'),
('Louisa',  'May Alcott'),
('Leo',     'Tolstoy'),
('J.R.R.',  'Tolkien'),
('E.M.',    'Forster'),
('Ray',     'Bradbury'),
('Oscar',   'Wilde'),
('Terry',   'Goodkind'),
('Margaret','Atwood'),
('Albert',  'Camus'),
('Yann',    'Martel'),
('John',    'Green'),
('Kurt',    'Vonnegut'),
('Ernest',  'Hemingway'),
('Aldous',  'Huxley'),
('David',   'Foster Wallace'),
('Gabriel', 'García Márquez'),
('Miguel de','Cervantes');

--publisher
INSERT INTO PUBLISHER (PUBLISHER_NAME, ADDRESS, COUNTRY) VALUES
('Penguin Classics',            '80 Strand, London',                         'England'),
('Grand Central Publishing',    '1290 Avenue of the Americas, New York, NY', 'USA'),
('Puffin Books',                '345 Hudson Street, New York, NY',           'USA'),
('Mariner Books',               '222 Berkeley Street, Boston, MA',           'USA'),
('William Morrow Paperbacks',   '195 Broadway, New York, NY',                'USA'),
('Dover Publications',          '31 East 2nd Street, Mineola, NY',           'USA'),
('Tor Books',                   '175 Fifth Avenue, New York, NY',            'USA'),
('Anchor Books',                '1745 Broadway, New York, NY',               'USA'),
('Vintage International',       '1745 Broadway, New York, NY',               'USA'),
('Speak',                       '1745 Broadway, New York, NY',               'USA'),
('Dial Press Trade Paperback',  '1745 Broadway, New York, NY',               'USA'),
('Scribner',                    '1230 Avenue of the Americas, New York, NY', 'USA'),
('Harper Perennial',            '195 Broadway, New York, NY',                'USA'),
('Back Bay Books',              '1745 Broadway, New York, NY',               'USA'),
('Vintage',                     '1745 Broadway, New York, NY',               'USA'),
('Little, Brown and Company',   '1290 Avenue of the Americas, New York, NY', 'USA'),
('Penguin Books',               '80 Strand, London',                         'England'),
('Bantam Books',                '1745 Broadway, New York, NY',               'USA'),
('Riverhead Books',             '1745 Broadway, New York, NY',               'USA'),
('Del Rey',                     '1745 Broadway, New York, NY',               'USA');

--genre
INSERT INTO GENRE (GENRE_NAME) VALUES
('Classic'), ('Historical Fiction'), ('Fantasy'), ('Philosophical Fiction'),
('Literary Fiction'), ('Young Adult'), ('Science Fiction'), ('Utopian'),
('Postmodern'), ('Short Stories'), ('Nonfiction'), ('Magical Realism'),
('Satire'), ('Crime'), ('Humor'), ('Dystopian'),
('Gothic'), ('Philosophy'), ('Modernist'), ('Existentialism');

--book
INSERT INTO BOOK (TITLE, ISBN, PUBLICATION_YEAR, PUBLISHER_ID) VALUES
('The Brothers Karamazov',          '9780140449242', 1880, 1),
('Gone with the Wind',              '9780446675536', 1936, 2),
('The Metamorphosis',               '9780141188127', 1915, 1),
('Little Women',                    '9780147514011', 1868, 3),
('Anna Karenina',                   '9780143035008', 1877, 1),
('The Fellowship of the Ring',      '9780547928210', 1954, 4),
('A Passage to India',              '9780156711425', 1924, 4),
('Dandelion Wine',                  '9780380977260', 1957, 5),
('The Importance of Being Earnest', '9780486264783', 1895, 6),
('Wizard''s First Rule',            '9780812548051', 1994, 7),
('Alias Grace',                     '9780385490443', 1996, 8),
('The Plague',                      '9780679720218', 1947, 9),
('Life of Pi',                      '9780156027328', 2001, 4),
('Looking for Alaska',              '9780142402511', 2005,10),
('Slaughterhouse-Five',             '9780385333849', 1969,11),
('A Farewell to Arms',              '9780684801469', 1929,12),
('Island',                          '9780061561795', 1962,13),
('Infinite Jest',                   '9780316066525', 1996,14),
('Chronicle of a Death Foretold',   '9781400034710', 1981,15),
('Don Quixote',                     '9780142437230', 1605, 1);

--book_author
INSERT INTO BOOK_AUTHOR (BOOK_ID, AUTHOR_ID) VALUES
(1 ,  5),
(2 ,  3),
(3 , 11),
(4 , 17),
(5 ,  2),
(6 , 18),
(7 , 12),
(8 , 16),
(9 , 13),
(10,  6),
(11, 19),
(12, 15),
(13, 14),
(14,  8),
(15,  1),
(16,  9),
(17,  4),
(18,  7),
(19, 20),
(20, 10);

--book_genre
INSERT INTO BOOK_GENRE (BOOK_ID, GENRE_ID) VALUES
(1 , 12),
(1, 2),
(2 ,  1),
(2 ,  12),
(3 , 10),
(4 ,  5),
(5 ,  4),
(6 ,  7),
(7 ,  2),
(8 , 11),
(9 , 15),
(10, 16),
(11, 13),
(12, 20),
(13,  6),
(14,  9),
(15,  3),
(16, 14),
(17, 17),
(18,  8),
(19, 18),
(20,  2);

--user_role
INSERT INTO USER_ROLE (NAME, DESCRIPTION) VALUES
('ADMIN',       'Administrator'),
('LIBRARIAN',   'Can manage and books'),
('PUBLISHER',   'Publisher'),
('READER',      'Regular user'),
('GUEST',       'Anonymous');

--app_user
INSERT INTO APP_USER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES
('System', 'Admin', 'admin@gg',
'{bcrypt}$2a$10$icLH7eGEQcpzlPQc7A8Zru5yCEqp7f.ptRA/3zKcw48onKyB8cQQK'
);
-- password - admin

--app_user_roles
INSERT INTO APP_USER_ROLES (APP_USER_ID, USER_ROLE_ID)
VALUES(
    (SELECT APP_USER_ID FROM APP_USER WHERE EMAIL = 'admin@gg'),
    (SELECT USER_ROLE_ID FROM USER_ROLE WHERE NAME = 'ADMIN')
);