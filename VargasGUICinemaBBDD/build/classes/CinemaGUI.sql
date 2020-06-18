-- Create user for local access.
CREATE USER 'cinemausr'@'localhost' IDENTIFIED BY 'cinemapwd';

-- Create database.
CREATE DATABASE cinemagui
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

-- Grant permissions.
GRANT SELECT, INSERT, UPDATE, DELETE ON cinemagui.* TO 'cinemausr'@'localhost';

-- Use database.
USE cinemagui;

-- Create tables
CREATE TABLE `room` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`totalRows` BIGINT NOT NULL,
`totalCols` BIGINT NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `users` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`userName` VARCHAR(50) NOT NULL UNIQUE,
`userPass` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB;

CREATE TABLE `booking` (
`idUser` BIGINT NOT NULL,
`idRoom` BIGINT NOT NULL,
`session` BIGINT NOT NULL,
`row` BIGINT NOT NULL,
`col` BIGINT NOT NULL,
PRIMARY KEY (`idRoom`, `session`, `row`, `col`),
FOREIGN KEY(idUser) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(idRoom) REFERENCES room(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- create data test
INSERT INTO room (`id`, `totalRows`,`totalCols`) VALUES (1,5,5);
INSERT INTO room (`id`, `totalRows`,`totalCols`) VALUES (2,7,5);
INSERT INTO room (`id`, `totalRows`,`totalCols`) VALUES (3,10,6);

INSERT INTO users (`userName`, `userPass`) VALUES ("Anna", "0123");
INSERT INTO users (`userName`, `userPass`) VALUES ("Bruce", "Batpass");
INSERT INTO users (`userName`, `userPass`) VALUES ("Helen", "cazadora");

INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (1, 1,2, 3, 3);
INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (1, 1,2, 3, 4);

INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (2, 2,1, 4, 2);
INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (2, 2,1, 4, 3);

INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (3, 3,3, 10, 2);
INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (3, 3,3, 10, 3);