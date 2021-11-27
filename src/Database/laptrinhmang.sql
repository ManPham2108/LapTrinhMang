-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2021 at 03:17 PM
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
  `Id` varchar(5) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(140) NOT NULL,
  `Block` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`Id`, `Username`, `Password`, `Block`) VALUES
('001', 'a@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('002', 'b@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('003', 'c@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('004', 'd@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('005', 'e@gmail.com', '12345679', 'False'),
('006', 'f@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False'),
('007', 'g@gmail.com', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 'False');

-- --------------------------------------------------------

--
-- Table structure for table `accountinfor`
--

CREATE TABLE IF NOT EXISTS `accountinfor` (
  `Id` varchar(5) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `Gender` varchar(7) NOT NULL,
  `DateOfBirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accountinfor`
--

INSERT INTO `accountinfor` (`Id`, `FullName`, `Gender`, `DateOfBirth`) VALUES
('001', 'Phạm Minh Mẫn', 'Female', '2000-12-28'),
('002', 'Vũ Anh Phúc', 'Male', '2000-11-17'),
('003', 'Phạm Nhật Khánh', 'Male', '2000-01-03'),
('004', 'Phạm Minh Minh', 'Female', '2000-11-10'),
('005', 'Vũ Phúc Minh', 'Female', '2000-07-09'),
('006', 'Phạm Nhật Nhật', 'Male', '2000-01-01'),
('007', 'Nguyễn Thị Thị', 'Female', '2011-11-18');

-- --------------------------------------------------------

--
-- Table structure for table `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `IdGroup` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `NameGroup` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `IdUser` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `group`
--

INSERT INTO `group` (`IdGroup`, `NameGroup`, `IdUser`) VALUES
('G100', 'Bạn Bè', '001'),
('G101', 'Bạn tôi', '001'),
('G100', 'Bạn Bè', '002'),
('G100', 'Bạn Bè', '003'),
('G102', 'Chào', '001'),
('G102', 'Chào', '005'),
('G102', 'Chào', '007');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
