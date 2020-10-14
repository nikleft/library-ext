
INSERT INTO roles VALUE (DEFAULT, "user", "Пользователь");
INSERT INTO roles VALUE (DEFAULT, "worker", "Библиотекарь");
INSERT INTO roles VALUE (DEFAULT, "admin", "Администратор");

INSERT INTO order_type VALUE (DEFAULT, "Week", "Неделя");
INSERT INTO order_type VALUE (DEFAULT, "Another date", "Другой срок");

INSERT INTO users VALUE (DEFAULT, "Ivan","Иван", "Ivanov", "Иванов", "nikleft12@gmail.com","202cb962ac59075b964b07152d234b70",2000,1,"Normal","Нормальное");
INSERT INTO users VALUE (DEFAULT, "Petr","Петр", "Petrov","Петров", "Petrov@gmail.com","202cb962ac59075b964b07152d234b70",0,1,"Blocked","Заблокирован");
INSERT INTO users VALUE (DEFAULT, "Sid","Сид", "Sidorov","Сидоров", "Sidorov@gmail.com","202cb962ac59075b964b07152d234b70",0,3,"Normal","Нормальное");
INSERT INTO users VALUE (DEFAULT, "Norman","Норман", "Edar","Эдар", "norm@gmail.com","202cb962ac59075b964b07152d234b70",0,2,"Normal","Нормальное");

INSERT INTO books VALUES (DEFAULT, "Moby Dick", "Моби Дик", "Melville", "Мэльвиль", "1950", "Breeze", "Бриз", 10,"WEB-INF/books/book.txt");
INSERT INTO books VALUES (DEFAULT, "Get Rich Really Fast","Очень быстро стать богатым", "Ima","Има","1970", "Breeze","Бриз",11,"WEB-INF/books/book.txt");
INSERT INTO books VALUES (DEFAULT, "Finding Inner Peace","Найти другое место", "Blissford","Блисфорд","1998", "Rossman-Press","Россман-Пресс",16,"WEB-INF/books/book.txt");
INSERT INTO books VALUES (DEFAULT, "Great Mystery Stories","Знаменитые истории заговора", "Whodunit","Худонит","1954", "Edicions Bromera","Бромера",190,"WEB-INF/books/book.txt");

INSERT INTO orders VALUES (DEFAULT, "nikleft12@gmail.com", DEFAULT, 1, DEFAULT, DEFAULT, 1, DEFAULT,"Не подтвержден","path");
INSERT INTO orders VALUES (DEFAULT, "nikleft12@gmail.com", "norm@gmail.com", 2, "2000-02-02", "2000-01-20", 2, "Confirmed","Подтвержден","WEB-INF/books/book.txt");
INSERT INTO orders VALUES (DEFAULT, "nikleft12@gmail.com", "norm@gmail.com", 2, "2000-02-02", "2000-01-20", 2, "Done","Выполнен","WEB-INF/books/book.txt");

SELECT * FROM users;
SELECT * FROM books;
SELECT * FROM orders;