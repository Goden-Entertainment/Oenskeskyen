DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS WISHLIST;
DROP TABLE IF EXISTS WISHES;

CREATE TABLE USERS ( id INT AUTO_INCREMENT PRIMARY KEY,
                     username VARCHAR(255),
                     password VARCHAR(255),
                     email VARCHAR(255));
CREATE TABLE WISHLIST (id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       userKey VARCHAR(255));
CREATE TABLE WISHES (id INT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     price DOUBLE,
                     link VARCHAR(255),
                     description VARCHAR(255),
                     wishlistKey INT);
INSERT INTO USERS ( username, password, email) VALUES ('Yadi','kode', 'Halla@gmail.com');
INSERT INTO USERS ( username, password, email) VALUES('Rune', 'missekat', 'missekatElsker@gmail.com');


INSERT INTO WISHLIST (id, name, userKey)  VALUES (1, 'The goats ønskeliste', 'Marco');
INSERT INTO WISHLIST (id, name, userKey)  VALUES (2, 'Juleønsker', 'Rune');

INSERT INTO WISHES(id, name, price, link, description, wishlistKey) VALUES(1,'helicoptor', 2500,'www.br.dk','stor helicoptor',1);
INSERT INTO WISHES(id, name, price, link, description, wishlistKey) VALUES(2, 'kat', 250,'www.dba.dk', 'sød misser', 1);
