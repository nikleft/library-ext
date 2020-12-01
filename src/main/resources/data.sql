insert into books (name, author, year, edition,  description, genre) values ('Moby Dick', 'Melville', to_date('01-01-18', 'DD-MM-RR'), 'Breeze'
,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
 Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
 Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
 Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'FORMAL');
insert into books (name, author, year, edition,  genre) values ('Get Rich Really Fast', 'Ima', to_date('01-01-18', 'DD-MM-RR'), 'Breeze', 'DETECTIVE');
insert into books (name, author, year, edition,  genre) values ('Finding Inner Peace', 'Blissford', to_date('01-01-18', 'DD-MM-RR'), 'Rossman-Press', 'THRILLER');
insert into books (name, author, year, edition,  genre) values ('Great Mystery Stories', 'Whodunit', to_date('01-01-18', 'DD-MM-RR'), 'Edicions Bromera', 'DOCUMENTARY');
insert into books (name, author, year, edition,  genre) values ('Software Wizardry', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');

INSERT INTO roles(id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_EDITOR');

insert into books (name, author, year, edition, genre) values ('Book 1', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 2', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 3', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 4', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 5', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 6', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 7', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 8', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 9', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 10', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book 11', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');
insert into books (name, author, year, edition,  genre) values ('Book Super Special', 'Abugov', to_date('01-01-18', 'DD-MM-RR'), 'J.B. Lippincott',  'HOBBY');

insert into users (name, surname, username, password, email, registration_date, address, phone)
    values ('Иван', 'Иванов', 'ivanov', '$2b$10$F72hCMCBXekhI7Q6T7LTlutF7r9QFemkgdHnVmGWw695lt3FA7Nh2', 'email@gmail.com', to_date('01-01-20', 'DD-MM-RR'), 'Piccadilly London W1J 7ВХ', '+44 7911 123456'),
           ('Петр', 'Петров', 'petrov', '$2b$10$F72hCMCBXekhI7Q6T7LTlutF7r9QFemkgdHnVmGWw695lt3FA7Nh2', 'email@gmail.com', to_date('01-01-20', 'DD-MM-RR'), 'Piccadilly London W1J 7ВХ', '+44 7911 123456'),
           ('Сид', 'Сидоров', 'sidorov', '$2b$10$F72hCMCBXekhI7Q6T7LTlutF7r9QFemkgdHnVmGWw695lt3FA7Nh2', 'email@gmail.com', to_date('01-01-20', 'DD-MM-RR'), 'Piccadilly London W1J 7ВХ', '+44 7911 123456'),
           ('Админ', 'Админ', 'admin', '$2b$10$F72hCMCBXekhI7Q6T7LTlutF7r9QFemkgdHnVmGWw695lt3FA7Nh2', 'email@gmail.com', to_date('01-01-20', 'DD-MM-RR'), 'Piccadilly London W1J 7ВХ', '+44 7911 123456');


insert into books_users (users_id, books_id)
    values (1, 1),
           (1, 2),
           (1, 3),
           (1, 4),
           (1, 5);

insert into users_roles (users_id, roles_id)
    values (1, 1),
           (2, 1),
           (3, 1),
           (4, 2);

insert into users_friends (user_model_id, friends_id)
    values (1, 2),
           (2, 1),
           (2, 3),
           (3, 1),
           (3, 2);

insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 9.2, 'true', 1, 1, '2018-01-12');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 2.2, 'true', 1, 2, '2019-11-12');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 4.7, 'true', 1, 3, '2018-12-11');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 5.2, 'true', 1, 4, '2018-01-01');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 8.0, 'true', 1, 5, '2018-06-06');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 9.5, 'true', 1, 6, '2020-05-09');




insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 2.5, 'false', 2, 1, '2018-11-13');
insert into reviews(title, description, rating, is_recommended, reviewer_id, book_id, review_date)
    values ('My honest review', 'Description text here', 7.8, 'true', 3, 1, '2018-11-14');

