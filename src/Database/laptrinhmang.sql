-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2021 at 04:44 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.2.34

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
  `Password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`Id`, `Username`, `Password`) VALUES
('001', 'a@gmail.com', '123456789'),
('002', 'b@gmail.com', '123456789'),
('003', 'c@gmail.com', '123456789'),
('004', 'd@gmail.com', '123456789'),
('005', 'e@gmail.com', '12345679'),
('006', 'f@gmail.com', '123456789'),
('007', 'g@gmail.com', '123456789');

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
('001', 'Phạm Minh Mẫn', 'Male', '2000-12-28'),
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
  `IdGroup` varchar(10) NOT NULL,
  `NameGroup` varchar(100) NOT NULL,
  `Id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
