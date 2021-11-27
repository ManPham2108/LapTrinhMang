create database laptrinhmang;
use laptrinhmang;

CREATE TABLE IF NOT EXISTS `account` (
  `Id` int NOT NULL auto_increment primary key,
  `Username` varchar(100) NOT NULL unique,
  `Password` varchar(140) NOT NULL,
  `Block` varchar(15) NOT NULL
);

INSERT INTO `account` (`Username`, `Password`, `Block`) VALUES
('a@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('b@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('c@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('d@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('e@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('f@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('g@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False');

CREATE TABLE IF NOT EXISTS `accountinfor` (
  `Id` int NOT NULL primary key,
  `FullName` varchar(100) NOT NULL,
  `Gender` varchar(7) NOT NULL,
  `DateOfBirth` date NOT NULL,
  foreign key(`Id`) references `account`(`Id`) on update cascade on delete cascade
);

INSERT INTO `accountinfor` (`Id`, `FullName`, `Gender`, `DateOfBirth`) VALUES
(1, 'Phạm Minh Mẫn', 'Female', '2000-12-28'),
(2, 'Vũ Anh Phúc', 'Male', '2000-11-17'),
(3, 'Phạm Nhật Khánh', 'Male', '2000-01-03'),
(4, 'Phạm Minh Minh', 'Female', '2000-11-10'),
(5, 'Vũ Phúc Minh', 'Female', '2000-07-09'),
(6, 'Phạm Nhật Nhật', 'Male', '2000-01-01'),
(7, 'Nguyễn Thị Thị', 'Female', '2011-11-18');

CREATE TABLE IF NOT EXISTS `group` (
  `Id` int not null auto_increment primary key,
  `NameGroup` varchar(100) NOT NULL
);

INSERT INTO `group` (`NameGroup`) VALUES
('Bạn Bè'),
('Bạn tôi'),
('Chào');

CREATE TABLE IF NOT EXISTS `groupMember` (
  `IdGroup` int not null,
  `IdUser` int not null,
  primary key(`IdGroup`, `IdUser`),
  foreign key(`IdGroup`) references `group`(`Id`) on update cascade on delete cascade,
  foreign key(`IdUser`) references `accountinfor`(`Id`) on update cascade on delete cascade
);

insert into `groupMember` values
(1, 1),
(1, 2),
(1, 5),
(2, 1),
(2, 3),
(2, 4),
(3, 2),
(3, 5),
(3, 3);


