-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2019 at 04:55 AM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `userName` varchar(45) NOT NULL,
  `userPass` text NOT NULL,
  `userType` text NOT NULL,
  `fullName` text NOT NULL,
  `phone` text NOT NULL,
  `email` text NOT NULL,
  `status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`userName`, `userPass`, `userType`, `fullName`, `phone`, `email`, `status`) VALUES
('E001', '1234', 'Admin', 'Ayush Raj', '7777777777', 'robin@mail.com', 'Activated'),
('E002', '1234', 'Manager', 'Sparsh', '8888888888', 'qwerty@mail.com', 'Deactivated'),
('Q001', '123456', 'Admin', 'Rock', '5555555555', 'ask@mail.com', 'Activated');

-- --------------------------------------------------------

--
-- Table structure for table `prospect`
--

CREATE TABLE `prospect` (
  `prosID` int(11) NOT NULL,
  `prosName` varchar(45) NOT NULL,
  `prosPhone` varchar(45) NOT NULL,
  `prosAddres` varchar(45) NOT NULL,
  `interestedModel` varchar(45) NOT NULL,
  `interestedColor` varchar(45) NOT NULL,
  `dateOfVisit` varchar(20) NOT NULL,
  `hotness` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prospect`
--

INSERT INTO `prospect` (`prosID`, `prosName`, `prosPhone`, `prosAddres`, `interestedModel`, `interestedColor`, `dateOfVisit`, `hotness`) VALUES
(1, 'Ayush', '9999988888', 'H12 Building ', 'Ferrari', 'Red', '01/01/2017', 'hot'),
(2, 'Prakhar', '4444444444', 'IIT Kanpur', 'Spyder', 'Orange', '09/09/2018', 'hot'),
(3, 'Yajan', '7777777777', 'Anand Colony', 'Volkswagen', 'Black', '02/07/2017', 'Cold'),
(4, 'Sparsh', '5555555555', 'Lucknow', 'AUDI Q7', 'White', '01/17/1999', 'hot'),
(5, 'Sagar', '8888888888', 'Gagan Colony', 'Creta', 'Black', '27/07/2009', 'hot');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`userName`);

--
-- Indexes for table `prospect`
--
ALTER TABLE `prospect`
  ADD PRIMARY KEY (`prosID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `prospect`
--
ALTER TABLE `prospect`
  MODIFY `prosID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1235;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
