-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2021 at 08:43 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laptrinhmang`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(140) NOT NULL,
  `Block` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`Id`, `Username`, `Password`, `Block`) VALUES
(1, 'a@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
(2, 'b@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
(3, 'c@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
(4, 'd@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
(5, 'e@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
(6, 'f@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
(7, 'g@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False');

-- --------------------------------------------------------

--
-- Table structure for table `accountinfor`
--

CREATE TABLE IF NOT EXISTS `accountinfor` (
  `Id` int(11) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `Gender` varchar(7) NOT NULL,
  `DateOfBirth` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accountinfor`
--

INSERT INTO `accountinfor` (`Id`, `FullName`, `Gender`, `DateOfBirth`) VALUES
(1, 'Phạm Minh Mẫn', 'Female', '2000-12-28'),
(2, 'Vũ Anh Phúc', 'Male', '2000-11-17'),
(3, 'Phạm Nhật Khánh', 'Male', '2000-01-03'),
(4, 'Phạm Minh Minh', 'Female', '2000-11-10'),
(5, 'Vũ Phúc Minh', 'Female', '2000-07-09'),
(6, 'Phạm Nhật Nhật', 'Male', '2000-01-01'),
(7, 'Nguyễn Thị Thị', 'Female', '2011-11-18');

-- --------------------------------------------------------

--
-- Table structure for table `blockuser`
--

CREATE TABLE IF NOT EXISTS `blockuser` (
  `Id` int(11) NOT NULL,
  `IdUserBlock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `blockuser`
--

INSERT INTO `blockuser` (`Id`, `IdUserBlock`) VALUES
(3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NameGroup` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `group`
--

INSERT INTO `group` (`Id`, `NameGroup`) VALUES
(1, 'Bạn Bè'),
(2, 'Bạn tôi'),
(3, 'Chào'),
(5, 'Bạn Hỡi'),
(7, 'SayYeah'),
(8, 'Ôiiii'),
(9, 'Ôiii dồiiiii');

-- --------------------------------------------------------

--
-- Table structure for table `groupmember`
--

CREATE TABLE IF NOT EXISTS `groupmember` (
  `IdGroup` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  `Block` varchar(15) NOT NULL,
  PRIMARY KEY (`IdGroup`,`IdUser`),
  KEY `IdUser` (`IdUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `groupmember`
--

INSERT INTO `groupmember` (`IdGroup`, `IdUser`, `Block`) VALUES
(1, 2, 'False'),
(1, 5, 'False'),
(2, 1, 'False'),
(2, 3, 'False'),
(2, 4, 'False'),
(3, 2, 'False'),
(3, 3, 'False'),
(3, 5, 'False'),
(5, 1, 'False'),
(5, 3, 'False'),
(5, 6, 'False'),
(7, 1, 'False'),
(7, 2, 'False'),
(7, 4, 'False'),
(8, 1, 'False'),
(8, 2, 'False'),
(8, 3, 'False'),
(8, 5, 'False'),
(9, 1, 'False'),
(9, 2, 'False'),
(9, 6, 'False'),
(9, 7, 'False');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accountinfor`
--
ALTER TABLE `accountinfor`
  ADD CONSTRAINT `accountinfor_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `account` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `groupmember`
--
ALTER TABLE `groupmember`
  ADD CONSTRAINT `groupmember_ibfk_1` FOREIGN KEY (`IdGroup`) REFERENCES `group` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `groupmember_ibfk_2` FOREIGN KEY (`IdUser`) REFERENCES `accountinfor` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
