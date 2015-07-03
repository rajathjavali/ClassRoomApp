-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2015 at 12:10 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `proj`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `USN` varchar(10) NOT NULL DEFAULT '',
  `addr` varchar(100) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `pincode` varchar(10) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`USN`, `addr`, `city`, `state`, `pincode`) VALUES
('1rv12is053', 'Bmp no38,brindavan layout,padmanabhanahanagar', 'Bangalore', 'Karnataka', '560061');

-- --------------------------------------------------------

--
-- Table structure for table `attends`
--

CREATE TABLE IF NOT EXISTS `attends` (
  `susn` varchar(10) NOT NULL DEFAULT '',
  `cdte` varchar(12) NOT NULL,
  `ctme` varchar(5) NOT NULL,
  `ccode` varchar(10) NOT NULL DEFAULT '',
  `acy` year(4) NOT NULL,
  `sem` int(11) NOT NULL DEFAULT '0',
  `status` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attends`
--

INSERT INTO `attends` (`susn`, `cdte`, `ctme`, `ccode`, `acy`, `sem`, `status`) VALUES
('1rv12is004', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'P'),
('1rv12is004', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'A'),
('1rv12is004', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'P'),
('1rv12is004', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'A'),
('1rv12is004', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'A'),
('1rv12is009', '07-Apr-201', '21:11', '12IS63', 2014, 6, 'P'),
('1rv12is023', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'P'),
('1rv12is023', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'P'),
('1rv12is023', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'A'),
('1rv12is023', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'A'),
('1rv12is023', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'P'),
('1rv12is023', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'A'),
('1rv12is023', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'P'),
('1rv12is023', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'A'),
('1rv12is023', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'P'),
('1rv12is023', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'P'),
('1rv12is023', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'P'),
('1rv12is041', '06-Apr-201', '23:05', '12IS63', 2014, 6, 'A'),
('1rv12is041', '07-Apr-201', '21:11', '12IS63', 2014, 6, 'P'),
('1rv12is041', '08-Apr-201', '04:43', '12IS63', 2014, 6, 'A'),
('1rv12is041', '08-Apr-201', '04:44', '12IS63', 2014, 6, 'A'),
('1rv12is041', '08-Apr-201', '04:45', '12IS63', 2014, 6, 'A'),
('1rv12is041', '10-Apr-2015', '14:47', '12IS63', 2014, 6, 'A'),
('1rv12is041', '10-Apr-2015', '14:48', '12IS63', 2014, 6, 'P'),
('1rv12is041', '10-Apr-2015', '14:51', '12IS63', 2014, 6, 'P'),
('1rv12is041', '10-Apr-2015', '14:52', '12IS63', 2014, 6, 'A'),
('1rv12is041', '10-Apr-2015', '23:55', '12IS63', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'P'),
('1rv12is041', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'P'),
('1rv12is041', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'P'),
('1rv12is041', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'A'),
('1rv12is041', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'P'),
('1rv12is041', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'P'),
('1rv12is045', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'P'),
('1rv12is045', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'A'),
('1rv12is045', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'A'),
('1rv12is045', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'P'),
('1rv12is051', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'P'),
('1rv12is051', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'A'),
('1rv12is051', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'P'),
('1rv12is051', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'A'),
('1rv12is051', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'P'),
('1rv12is051', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'A'),
('1rv12is051', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'A'),
('1rv12is051', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'P'),
('1rv12is051', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'P'),
('1rv12is051', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'A'),
('1rv12is051', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'A'),
('1rv12is053', '10-Apr-2015', '14:47', '12IS63', 2014, 6, 'A'),
('1rv12is053', '10-Apr-2015', '14:48', '12IS63', 2014, 6, 'P'),
('1rv12is053', '10-Apr-2015', '14:51', '12IS63', 2014, 6, 'A'),
('1rv12is053', '10-Apr-2015', '14:52', '12IS63', 2014, 6, 'P'),
('1rv12is053', '10-Apr-2015', '23:55', '12IS63', 2014, 6, 'A'),
('1rv12is053', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'A'),
('1rv12is053', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'P'),
('1rv12is053', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'A'),
('1rv12is053', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'P'),
('1rv12is053', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'A'),
('1rv12is053', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'P'),
('1rv12is053', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'P'),
('1rv12is053', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'P'),
('1rv12is053', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'P'),
('1rv12is053', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'P'),
('1rv12is053', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'P'),
('1rv12is053', '2015-04-01', '12:30', '12HSM61', 2014, 6, 'P'),
('1rv12is053', '2015-04-02', '11:30', '12HSM61', 2014, 6, 'P'),
('1rv12is053', '2015-04-03', '02:15', '12HSM61', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '14:18', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '15:20', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '15:21', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '15:27', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '15:58', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '16:01', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '16:03', '12IS63', 2014, 6, 'P'),
('1rv12is054', '17-Apr-2015', '16:12', '12IS62', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '16:24', '12IS63', 2014, 6, 'A'),
('1rv12is054', '17-Apr-2015', '16:37', '12IS63', 2014, 6, 'A'),
('1rv12is054', '20-Apr-2015', '11:50', '12IS63', 2014, 6, 'P');

-- --------------------------------------------------------

--
-- Table structure for table `checks`
--

CREATE TABLE IF NOT EXISTS `checks` (
  `USN` varchar(10) NOT NULL DEFAULT '',
  `nsno` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `ccode` varchar(7) NOT NULL DEFAULT '',
  `dte` varchar(12) NOT NULL,
  `tme` varchar(8) NOT NULL,
  `roomno` varchar(6) DEFAULT NULL,
  `acy` year(4) NOT NULL,
  `sem` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`ccode`, `dte`, `tme`, `roomno`, `acy`, `sem`) VALUES
('12HSM61', '10-APR-2015', '09:00', '110', 2014, 6),
('12IS63', '10-APR-2015', '10:00', '110', 2014, 6);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `name` varchar(50) DEFAULT NULL,
  `ccode` varchar(7) NOT NULL DEFAULT '',
  `credits` int(11) DEFAULT NULL,
  `acy` year(4) NOT NULL,
  `sem` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`name`, `ccode`, `credits`, `acy`, `sem`) VALUES
('Management and Organizational Behavior', '12HSM61', 3, 2014, 6),
('Software Engineering', '12IS62', 4, 2014, 6),
('Computer Networks and Security', '12IS63', 5, 2014, 6),
('Database Management Systems', '12IS64', 5, 2014, 6),
('Emerging technologies', '12IS65', 2, 2014, 6),
('Information Security', '12IS6C1', 4, 2014, 6),
('High Performance Computing', '12IS6C3', 4, 2014, 6),
('Image Processing and Computer Vision', '12IS6C4', 4, 2014, 6),
('Pattern Recognition', '12IS6D3', 4, 2014, 6),
('Mobile Application Development', '12IS6D4', 4, 2014, 6);

-- --------------------------------------------------------

--
-- Table structure for table `handles`
--

CREATE TABLE IF NOT EXISTS `handles` (
  `lid` varchar(10) NOT NULL DEFAULT '',
  `ccode` varchar(7) NOT NULL DEFAULT '',
  `acy` year(4) NOT NULL DEFAULT '0000',
  `sem` int(11) NOT NULL DEFAULT '0',
  `section` varchar(2) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `handles`
--

INSERT INTO `handles` (`lid`, `ccode`, `acy`, `sem`, `section`) VALUES
('001', '12HSM61', 2014, 6, 'A'),
('003', '12IS62', 2014, 6, 'A'),
('004', '12IS62', 2014, 6, 'B'),
('123', '12IS62', 2014, 6, 'A'),
('123', '12IS63', 2014, 6, 'A'),
('123', '12IS65', 2014, 6, 'B'),
('123', '12IS6C1', 2014, 6, 'B'),
('234', '12IS6D4', 2014, 6, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE IF NOT EXISTS `marks` (
  `lab` int(2) NOT NULL DEFAULT '0',
  `t1` int(3) NOT NULL DEFAULT '0',
  `q1` int(2) NOT NULL DEFAULT '0',
  `t2` int(3) NOT NULL DEFAULT '0',
  `q2` int(2) NOT NULL DEFAULT '0',
  `t3` int(3) NOT NULL DEFAULT '0',
  `q3` int(2) NOT NULL DEFAULT '0',
  `assign` int(2) NOT NULL DEFAULT '0',
  `ccode` varchar(7) NOT NULL DEFAULT '',
  `susn` varchar(10) NOT NULL DEFAULT '',
  `acy` year(4) NOT NULL,
  `sem` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`lab`, `t1`, `q1`, `t2`, `q2`, `t3`, `q3`, `assign`, `ccode`, `susn`, `acy`, `sem`) VALUES
(0, 0, 0, 0, 0, 0, 0, 0, '12IS62', '1rv12is004', 2014, 6),
(0, 20, 4, 0, 0, 0, 0, 0, '12IS63', '1rv12is004', 2014, 6),
(0, 45, 12, 0, 0, 0, 0, 0, '12IS6C1', '1rv12is009', 2014, 6),
(0, 45, 12, 45, 12, 0, 0, 0, '12IS63', '1rv12is009', 2014, 6),
(0, 0, 0, 0, 0, 0, 0, 0, '12IS62', '1rv12is023', 2014, 6),
(0, 45, 7, 0, 0, 0, 0, 0, '12IS63', '1rv12is023', 2014, 6),
(0, 0, 0, 0, 0, 0, 0, 0, '12IS62', '1rv12is041', 2014, 6),
(0, 15, 15, 0, 0, 0, 0, 0, '12IS63', '1rv12is041', 2014, 6),
(0, 40, 10, 0, 0, 0, 0, 0, '12IS6C1', '1rv12is041', 2014, 6),
(0, 0, 0, 0, 0, 0, 0, 0, '12IS62', '1rv12is045', 2014, 6),
(0, 7, 5, 0, 0, 0, 0, 0, '12IS63', '1rv12is045', 2014, 6),
(0, 0, 0, 0, 0, 0, 0, 0, '12IS62', '1rv12is051', 2014, 6),
(0, 20, 4, 0, 0, 0, 0, 0, '12IS63', '1rv12is051', 2014, 6),
(0, 48, 15, 49, 13, 0, 0, 0, '12IS62', '1rv12is053', 2014, 6),
(40, 15, 4, 25, 15, 20, 14, 0, '12IS63', '1rv12is053', 2014, 6),
(0, 0, 0, 0, 0, 0, 0, 0, '12IS62', '1rv12is054', 2014, 6),
(0, 0, 0, 0, 0, 0, 0, 0, '12IS63', '1rv12is054', 2014, 6);

-- --------------------------------------------------------

--
-- Table structure for table `notices`
--

CREATE TABLE IF NOT EXISTS `notices` (
`serialno` int(11) NOT NULL,
  `info` varchar(1000) DEFAULT NULL,
  `dte` varchar(12) DEFAULT NULL,
  `tme` varchar(5) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `id` varchar(10) NOT NULL DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notices`
--

INSERT INTO `notices` (`serialno`, `info`, `dte`, `tme`, `type`, `id`) VALUES
(1, 'extra class on 21st april 2015', '19-Apr-2015', '12:45', NULL, '123'),
(2, 'hello everyone\n', '19-Apr-2015', '12:50', NULL, '123'),
(3, 'lab internals cn on Monday - 20-Apr-2015', '19-Apr-2015', '12:50', NULL, '123'),
(5, 'lab internals dbms on Monday - 22-Apr-2015', '19-Apr-2015', '12:51', NULL, '123'),
(6, 'lab internals portions include all programs except kerberos\n', '19-Apr-2015', '12:52', NULL, '123'),
(7, 'bsjxnxn do CFI cup dry coo cry so do CCI do so chu cut sy cl by srry CV Hi do cook j try do no dry srry er guy coo hi srry coo hi dry coo chu guy chu boo no cry srry hi no cut sy book CCI go chu CCU srry cut CCI vu chu CCU srry coo chu CCU CCU CCU CCU CCU CCU srry do CFI so cool HVO srry do CCI do CCI vu set chu CCU sy cl so do CCI vu chu at food do so so so quejrnsksbcndjd hdbdjdbdjdbdys xhd c xje jxkemdje f fbfjrbruvznnsjslndjfkdkdkdjfjdkdkd djdndndndjd djdnfmfmkfncgjbfb.   hnnsjdmckdbdjs jdndndndncndkwnzvdjd jx', '19-Apr-2015', '12:56', NULL, '123'),
(8, 'hello student\n', '21-Apr-2015', '02:18', NULL, '123');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `ID` varchar(10) NOT NULL,
  `FName` varchar(20) DEFAULT NULL,
  `Mname` varchar(20) DEFAULT NULL,
  `Lname` varchar(20) DEFAULT NULL,
  `Phno` bigint(10) DEFAULT NULL,
  `emailid` varchar(40) DEFAULT NULL,
  `Shortname` varchar(10) DEFAULT NULL,
  `pass` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`ID`, `FName`, `Mname`, `Lname`, `Phno`, `emailid`, `Shortname`, `pass`) VALUES
('001', 'arjun', NULL, 'singar', 9632359395, 'as@rvce.edu', 'AS', 'pass1'),
('003', 'G', 'Srinivas', 'S', 24864, 'gns@rvce.edu', 'GNS', 'pass3'),
('004', 'Cavery', 'K', 'N', 3754, 'ckn@rvce.edu', 'CKN', 'pass3'),
('005', 'Smitha', 'A', 'B', 4586, 'abs@rvce.edu', 'ABS', 'pass'),
('100', 'Mamatha', 'M', NULL, 34756, 'mm@rvce.edu.in', 'MM', 'pass1'),
('123', 'Padmashree', '', '', 9632359395, 'pad@gmail.com', 'PT', 'rtyu'),
('234', 'Anisha', '', '', 943187947, 'anisha@gmail.com', 'ASB', 'rtyu');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE IF NOT EXISTS `state` (
  `stateno` int(11) NOT NULL,
  `state` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`stateno`, `state`) VALUES
(1, 'Andhra Pradesh'),
(2, ' Arunachal Pradesh'),
(3, 'Assam'),
(4, 'Bihar'),
(5, 'Chhattisgarh'),
(6, 'Goa'),
(7, 'Gujarat'),
(8, 'Haryana'),
(9, 'Himachal Pradesh'),
(10, 'Jammu & Kashmir'),
(11, 'Jharkhand'),
(12, 'Karnataka'),
(13, 'Kerala'),
(14, 'Madhya Pradesh'),
(15, 'Maharashtra'),
(16, 'Manipur'),
(17, 'Meghalaya'),
(18, 'Mizoram'),
(19, 'Nagaland'),
(20, 'Odisha'),
(21, 'Punjab'),
(22, 'Rajasthan'),
(23, 'Sikkim'),
(24, 'Tamil Nadu'),
(25, 'Telangana'),
(26, 'Tripura'),
(27, 'Uttar Pradesh'),
(28, 'Uttarakhand'),
(29, 'West Bengal'),
(30, 'Andaman and Nicobar '),
(31, 'Chandigarh'),
(32, 'Dadra and Nagar Have'),
(33, 'Daman and Diu'),
(34, 'Lakshadweep'),
(35, 'Delhi'),
(36, 'Puducherry');

-- --------------------------------------------------------

--
-- Table structure for table `studcourse`
--

CREATE TABLE IF NOT EXISTS `studcourse` (
  `USN` varchar(10) NOT NULL DEFAULT '',
  `ccode` varchar(7) NOT NULL DEFAULT '',
  `acy` year(4) NOT NULL DEFAULT '0000',
  `sem` varchar(2) NOT NULL DEFAULT '0',
  `section` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studcourse`
--

INSERT INTO `studcourse` (`USN`, `ccode`, `acy`, `sem`, `section`) VALUES
('1rv12is004', '12IS61', 2014, '6', 'A'),
('1rv12is004', '12IS62', 2014, '6', 'A'),
('1rv12is004', '12IS63', 2014, '6', 'A'),
('1rv12is004', '12IS64', 2014, '6', 'A'),
('1rv12is004', '12IS65', 2014, '6', 'A'),
('1rv12is004', '12IS6C1', 2014, '6', 'A'),
('1rv12is004', '12IS6D4', 2014, '6', 'A'),
('1RV12IS009', '12HSM61', 2014, '6', 'B'),
('1rv12is009', '12IS62', 2014, '6', 'B'),
('1rv12is009', '12IS63', 2014, '6', 'B'),
('1rv12is009', '12IS64', 2014, '6', 'B'),
('1rv12is009', '12IS65', 2014, '6', 'B'),
('1rv12is009', '12IS6C1', 2014, '6', 'C'),
('1rv12is009', '12IS6D4', 2014, '6', 'D'),
('1RV12IS022', '12HSM61', 2014, '6', 'B'),
('1rv12is023', '12IS61', 2014, '6', 'A'),
('1rv12is023', '12IS61', 2014, '6', 'B'),
('1rv12is023', '12IS62', 2014, '6', 'A'),
('1rv12is023', '12IS62', 2014, '6', 'B'),
('1rv12is023', '12IS63', 2014, '6', 'A'),
('1rv12is023', '12IS63', 2014, '6', 'B'),
('1rv12is023', '12IS64', 2014, '6', 'A'),
('1rv12is023', '12IS64', 2014, '6', 'B'),
('1rv12is023', '12IS65', 2014, '6', 'A'),
('1rv12is023', '12IS6C1', 2014, '6', 'A'),
('1rv12is023', '12IS6C3', 2014, '6', 'E'),
('1rv12is023', '12IS6D3', 2014, '6', 'F'),
('1rv12is023', '12IS6D4', 2014, '6', 'A'),
('1RV12IS041', '12HSM61', 2014, '6', 'A'),
('1RV12IS041', '12IS62', 2014, '6', 'A'),
('1RV12IS041', '12IS63', 2014, '6', 'A'),
('1RV12IS041', '12IS64', 2014, '6', 'A'),
('1rv12is041', '12IS65', 2014, '6', 'A'),
('1rv12is041', '12IS6C1', 2014, '6', 'A'),
('1rv12is041', '12IS6D4', 2014, '6', 'A'),
('1rv12is045', '12IS61', 2014, '6', 'A'),
('1rv12is045', '12IS62', 2014, '6', 'A'),
('1rv12is045', '12IS63', 2014, '6', 'A'),
('1rv12is045', '12IS64', 2014, '6', 'A'),
('1rv12is045', '12IS65', 2014, '6', 'A'),
('1rv12is045', '12IS6C1', 2014, '6', 'A'),
('1rv12is045', '12IS6D4', 2014, '6', 'A'),
('1rv12is051', '12IS61', 2014, '6', 'A'),
('1rv12is051', '12IS62', 2014, '6', 'A'),
('1rv12is051', '12IS63', 2014, '6', 'A'),
('1rv12is051', '12IS64', 2014, '6', 'A'),
('1rv12is051', '12IS65', 2014, '6', 'A'),
('1rv12is051', '12IS6C1', 2014, '6', 'A'),
('1rv12is051', '12IS6D4', 2014, '6', 'A'),
('1rv12is052', '12IS65', 2014, '6', 'A'),
('1RV12IS053', '12HSM61', 2014, '6', 'A'),
('1rv12is053', '12IS62', 2014, '6', 'A'),
('1rv12is053', '12IS63', 2014, '6', 'A'),
('1rv12is053', '12IS64', 2014, '6', 'A'),
('1rv12is053', '12IS65', 2014, '6', 'A'),
('1rv12is053', '12IS6C1', 2014, '6', 'A'),
('1rv12is053', '12IS6D4', 2014, '6', 'A'),
('1rv12is054', '12IS61', 2014, '6', 'A'),
('1rv12is054', '12IS62', 2014, '6', 'A'),
('1rv12is054', '12IS63', 2014, '6', 'A'),
('1rv12is054', '12IS64', 2014, '6', 'A'),
('1rv12is054', '12IS65', 2014, '6', 'A'),
('1rv12is054', '12IS6C1', 2014, '6', 'A'),
('1rv12is054', '12IS6D4', 2014, '6', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `USN` varchar(10) NOT NULL,
  `FName` varchar(20) DEFAULT NULL,
  `Mname` varchar(20) DEFAULT NULL,
  `Lname` varchar(20) DEFAULT NULL,
  `Phno` bigint(10) DEFAULT NULL,
  `emailid` varchar(40) DEFAULT NULL,
  `pass` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`USN`, `FName`, `Mname`, `Lname`, `Phno`, `emailid`, `pass`) VALUES
('1RV12IS004', 'ANIRUDH', 'V', 'C', 9739030486, 'anirudh2694@gmail.com', 'qwerty'),
('1RV12IS009', 'APOORVA', 'N', '', 9611939150, 'apoorvanagaraj95@gmail.com', 'qwerty'),
('1RV12IS022', 'LALITHA', 'R', 'RAKSHITHA', 9686687303, 'lalitharr@gmail.com', 'qwerty'),
('1RV12IS023', 'MEGHASHYAM', 'K', '', 9535701567, 'shyam2801951@gmail.com', 'qwerty'),
('1rv12is041', 'Rajath', 'P', 'Javali', 8867662232, 'rajathjavali@gmail.com', 'qwerty'),
('1RV12IS045', 'ROHITH', 'M', '', 8951205938, 'rohith.m94@gmail.com', 'qwerty'),
('1RV12IS051', 'SHYAM', 'KUMAR', 'S', 7760025234, 'shyamks111@gmail.com', 'qwerty'),
('1RV12IS052', 'SOURABH', '', '', 8861943745, 'sourabhshirgur@gmail.com', 'qwerty'),
('1rv12is053', 'Spoorthi', '', 'Pujari', 9632359395, 'spoorthi.pujari@gmail.com', 'qwerty'),
('1RV12IS054', 'SUHAS', 'BHATTA', 'A', 7259368949, 'suhasbhatta.a@gmail.com', 'qwerty');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
 ADD PRIMARY KEY (`USN`,`pincode`);

--
-- Indexes for table `attends`
--
ALTER TABLE `attends`
 ADD PRIMARY KEY (`susn`,`cdte`,`ctme`);

--
-- Indexes for table `checks`
--
ALTER TABLE `checks`
 ADD PRIMARY KEY (`USN`,`nsno`), ADD KEY `nsno` (`nsno`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
 ADD PRIMARY KEY (`ccode`,`dte`,`tme`,`acy`,`sem`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
 ADD PRIMARY KEY (`ccode`,`acy`,`sem`);

--
-- Indexes for table `handles`
--
ALTER TABLE `handles`
 ADD PRIMARY KEY (`lid`,`ccode`,`acy`,`sem`,`section`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
 ADD PRIMARY KEY (`lab`,`t1`,`q1`,`t2`,`q2`,`t3`,`q3`,`assign`,`ccode`,`susn`,`acy`,`sem`), ADD KEY `marks_ibfk_1` (`susn`);

--
-- Indexes for table `notices`
--
ALTER TABLE `notices`
 ADD PRIMARY KEY (`serialno`,`id`), ADD KEY `id` (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
 ADD PRIMARY KEY (`stateno`);

--
-- Indexes for table `studcourse`
--
ALTER TABLE `studcourse`
 ADD PRIMARY KEY (`USN`,`ccode`,`acy`,`sem`,`section`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
 ADD PRIMARY KEY (`USN`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notices`
--
ALTER TABLE `notices`
MODIFY `serialno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`USN`) REFERENCES `student` (`USN`) ON DELETE CASCADE;

--
-- Constraints for table `attends`
--
ALTER TABLE `attends`
ADD CONSTRAINT `attends_ibfk_1` FOREIGN KEY (`susn`) REFERENCES `student` (`USN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `handles`
--
ALTER TABLE `handles`
ADD CONSTRAINT `handles_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `staff` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `marks`
--
ALTER TABLE `marks`
ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`susn`) REFERENCES `student` (`USN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notices`
--
ALTER TABLE `notices`
ADD CONSTRAINT `notices_ibfk_1` FOREIGN KEY (`id`) REFERENCES `staff` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `studcourse`
--
ALTER TABLE `studcourse`
ADD CONSTRAINT `studcourse_ibfk_1` FOREIGN KEY (`USN`) REFERENCES `student` (`USN`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
