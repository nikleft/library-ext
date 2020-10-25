insert into books (name, author, year, edition, count, description, genre) values ('Moby Dick', 'Melville', to_date('01-01-18', 'DD-MM-RR'), 'Breeze',10
,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
 Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
 Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
 Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'FORMAL');
insert into books (name, author, year, edition, count, genre) values ('Get Rich Really Fast', 'Ima', to_date('01-01-18', 'DD-MM-RR'), 'Breeze',11, 'DETECTIVE');
insert into books (name, author, year, edition, count, genre) values ('Finding Inner Peace', 'Blissford',to_date('01-01-18', 'DD-MM-RR'), 'Rossman-Press',16, 'THRILLER');
insert into books (name, author, year, edition, count, genre) values ('Great Mystery Stories', 'Whodunit',to_date('01-01-18', 'DD-MM-RR'), 'Edicions Bromera',190, 'DOCUMENTARY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');

insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');
insert into books (name, author, year, edition, count, genre) values ('Software Wizardry', 'Abugov',to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',15, 'HOBBY');

insert into users (name, surname, login, password, type, state) values ('Иван', 'Иванов', 'Ivanov', '123', 'DEFAULT', 'NORMAL');
insert into users (name, surname, login, password, type, state) values ('Петр', 'Петров', 'Petrov', '123', 'DEFAULT', 'NORMAL');
insert into users (name, surname, login, password, type, state) values ('Сид', 'Сидоров', 'Sidorov', '123', 'DEFAULT', 'NORMAL');


insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 9.2, 'true', 1, 1, '2018-11-12');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 2.5, 'false', 2, 1, '2018-11-13');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 7.8, 'true', 3, 1, '2018-11-14');