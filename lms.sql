-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 11, 2021 at 03:02 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `AdminID` varchar(50) NOT NULL,
  `AdminName` varchar(50) NOT NULL,
  `AdminPassword` varchar(50) NOT NULL,
  `AdminPhoneNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `AdminID`, `AdminName`, `AdminPassword`, `AdminPhoneNo`) VALUES
(1, 'A0001', 'Administrator', 'abc123', 1234567890);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `Title` varchar(50) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Genres` varchar(50) NOT NULL,
  `CountOfbooks` bigint(21) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `CallNumber` varchar(50) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Genres` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`CallNumber`, `Title`, `ISBN`, `Author`, `Genres`) VALUES
('C0001', 'Chemistry Term1', 2001001, 'Kong', 'Chemistry'),
('C0002', 'Chemistry Term1', 2001001, 'Kong', 'Chemistry'),
('CS001', 'Computer Science', 3001001, 'Voon', 'Computer'),
('J0001', 'JAVA', 4001001, 'James', 'computer language'),
('M0001', 'Algorithm', 1001001, 'Voon', 'Mathematics'),
('M0002', 'Algorithm', 1001001, 'Voon', 'Mathematics');

-- --------------------------------------------------------

--
-- Table structure for table `bookstatus`
--

CREATE TABLE `bookstatus` (
  `Title` varchar(50) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Genres` varchar(50) NOT NULL,
  `bookcnt` bigint(21) NOT NULL DEFAULT 0,
  `Availability` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bookstatus`
--

INSERT INTO `bookstatus` (`Title`, `ISBN`, `Author`, `Genres`, `bookcnt`, `Availability`) VALUES
('Algorithm', 1001001, 'Voon', 'Mathematics', 2, 'Available'),
('Chemistry Term1', 2001001, 'Kong', 'Chemistry', 2, 'Unavailable'),
('Computer Science', 3001001, 'Voon', 'Computer', 1, 'Unavailable');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `CallNumber` varchar(50) NOT NULL,
  `Borrower` varchar(50) NOT NULL,
  `DateOut` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`id`, `CallNumber`, `Borrower`, `DateOut`) VALUES
(1, 'C0001', 'BCS19090001', '2021-06-11 07:45:11'),
(2, 'C0002', 'BCS19090001', '2021-06-11 07:48:43'),
(4, 'M0001', 'BCS19090001', '2021-06-11 12:36:32');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Student_id` varchar(50) NOT NULL,
  `Student_name` varchar(50) NOT NULL,
  `Student_password` varchar(50) NOT NULL,
  `Phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`Student_id`, `Student_name`, `Student_password`, `Phone`) VALUES
('BCS19090001', 'Voon', 'password', 1234567890),
('BCS19090002', 'NewStudent2', 'password', 23987654),
('BCS19090003', 'student3', 'password', 121895437);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`CallNumber`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `CallNumber` (`CallNumber`),
  ADD KEY `Borrower` (`Borrower`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Student_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`Borrower`) REFERENCES `student` (`Student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`CallNumber`) REFERENCES `books` (`CallNumber`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
