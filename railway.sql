-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2020 at 03:28 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `railway`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `clientid` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `client_first_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `client_last_name` varchar(255) DEFAULT NULL,
  `locked` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `additional_address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `credit_card_cvv` varchar(255) DEFAULT NULL,
  `credit_card_expiration_month` varchar(255) DEFAULT NULL,
  `credit_card_expiration_year` varchar(255) DEFAULT NULL,
  `credit_card_name` varchar(255) DEFAULT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `discount_type_id` bigint(20) NOT NULL,
  `document_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientid`, `address`, `client_first_name`, `email`, `enabled`, `client_last_name`, `locked`, `password`, `phone_number`, `user_role`, `additional_address`, `city`, `credit_card_cvv`, `credit_card_expiration_month`, `credit_card_expiration_year`, `credit_card_name`, `credit_card_number`, `zip`, `discount_type_id`, `document_number`) VALUES
(8, 'улица Сезам', 'Testov', 'testchanges@example.com', b'1', 'Tester', b'0', '$2a$10$AtNZLTyqoeY2vaZrZA6Aruaz4i3B9KU4F67ruvLoPrLmUlVO7DwE.', '+359895123456', 1, 'блок Магия', 'Варна', 'Q2OBKkiA4u3m9DSLpLhSxw==', '11', '22', 'Test Test', '5512345553444334', '9000', 1, ''),
(9, '', 'Testov', 'test@example.com', b'0', 'Tester', b'0', '$2a$10$rjxt3lWmOA47tCosZwvq9e2tIrczplbzEBW/wGfGHGTT7N7JaEmOG', '+359 895456123', NULL, '', '', '', '', '', '', '', '', 1, ''),
(14, 'улица Хан Аспарух', 'Иван', 'ivan.dimitrov@abv.bg', b'1', 'Димитров', b'0', '$2a$10$eP34vONK2kbDuZLQ88xjLeAFByF.pHyuFRZkBxCsLZgJrjecD6jEq', '+359 895456123', 2, 'блок 25, вход Б, апартамент 15', 'Бургас', 'oigTfvww1J/B6+CcESYquQ==', '12', '23', 'Ivan Dimitrov', '5513323443444353', '8000', 3, '563458375'),
(15, '', 'Тодор', 'todor_georgiev@yahoo.com', b'1', 'Георгиев', b'0', '$2a$10$rCmScn2HxPMq8RVj232O.OfwveZNzLlaienYklfY9Pq1TZdzqL7iG', '+359 897234523', 1, '', '', 'ReR3bWKYySOlQiFWNXPALg==', '12', '24', 'Todor Georgiev', '5512345544345534', '', 1, ''),
(16, '', 'Петър', 'petar_petrov@abv.bg', b'1', 'Петров', b'0', '$2a$10$hlyxJwowdHU90feSRvtCm.LtzZLj/5PEjBZ0TYc0j.iAGJldPKdQW', '+359 886332434', 1, '', '', '', '', '', '', '', '', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `confirmation_token`
--

CREATE TABLE `confirmation_token` (
  `id` bigint(20) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `clientid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `confirmation_token`
--

INSERT INTO `confirmation_token` (`id`, `confirmation_token`, `created_date`, `clientid`) VALUES
(1, '9e3a525f-6a5e-4aa2-b068-40c592bd3432', '2020-06-12', 9);

-- --------------------------------------------------------

--
-- Table structure for table `discount_type`
--

CREATE TABLE `discount_type` (
  `id` bigint(20) NOT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_typebg` varchar(255) DEFAULT NULL,
  `discount_value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `discount_type`
--

INSERT INTO `discount_type` (`id`, `document_type`, `document_typebg`, `discount_value`) VALUES
(1, 'No discount', 'Без карта за отстъпка', 0),
(2, 'Discount card for elderly people', 'Карта за отстъпка - Възрастен', 60),
(3, 'Discount card ISIC Student', 'Карта за отстъпка ISIC студент', 50),
(4, 'Discount card for student', 'Карта за отстъпка за учащ/студент', 50),
(5, 'Discount card for disabled people', 'Карта за отстъпка - хора с увреждания', 65),
(6, 'Discount card for working people', 'Карта за отстъпка - работещи', 50),
(7, 'Discount card for family', 'Карта за отстъпка - Семейство', 50);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `routeid` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `arrival_time` datetime DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `arrival_stationid` bigint(20) NOT NULL,
  `departure_stationid` bigint(20) NOT NULL,
  `trainid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`routeid`, `price`, `arrival_time`, `departure_time`, `arrival_stationid`, `departure_stationid`, `trainid`) VALUES
(1, 1, '2020-06-08 06:37:00', '2020-06-08 06:30:00', 17, 1, 8601),
(2, 1.8, '2020-06-08 06:46:00', '2020-06-08 06:38:00', 10, 17, 8601),
(3, 6, '2020-06-09 08:19:00', '2020-06-09 06:46:00', 11, 10, 8601),
(4, 2.4, '2020-06-09 08:30:00', '2020-06-09 08:20:00', 12, 11, 8601),
(5, 3.65, '2020-06-09 08:55:00', '2020-06-09 08:31:00', 2, 12, 8601),
(6, 1, '2020-06-09 09:08:00', '2020-06-09 09:00:00', 13, 2, 8601),
(7, 6, '2020-06-09 10:34:00', '2020-06-09 09:09:00', 3, 13, 8601),
(8, 3.65, '2020-06-09 10:55:00', '2020-06-09 10:36:00', 14, 3, 8601),
(9, 4.15, '2020-06-09 11:22:00', '2020-06-09 10:56:00', 4, 14, 8601),
(10, 2.4, '2020-06-09 11:34:00', '2020-06-09 11:23:00', 15, 4, 8601),
(11, 3.65, '2020-06-09 11:53:00', '2020-06-09 11:35:00', 16, 15, 8601),
(12, 4.95, '2020-06-09 12:30:00', '2020-06-09 11:54:00', 6, 16, 8601),
(13, 1, '2020-06-14 12:32:00', '2020-06-14 12:25:00', 17, 1, 1621),
(14, 1.8, '2020-06-14 12:41:00', '2020-06-14 12:34:00', 10, 17, 1621),
(15, 1, '2020-06-14 12:43:00', '2020-06-14 12:41:00', 18, 10, 1621),
(16, 2.4, '2020-06-14 13:01:00', '2020-06-14 12:44:00', 19, 18, 1621),
(17, 1.8, '2020-06-14 13:16:00', '2020-06-14 13:02:00', 20, 19, 1621),
(18, 1.8, '2020-06-14 13:34:00', '2020-06-14 13:18:00', 21, 20, 1621),
(19, 2.4, '2020-06-14 13:56:00', '2020-06-14 13:36:00', 22, 21, 1621),
(20, 1.8, '2020-06-14 14:16:00', '2020-06-14 13:57:00', 23, 22, 1621),
(21, 1.8, '2020-06-14 14:26:00', '2020-06-14 14:17:00', 11, 23, 1621),
(22, 1.8, '2020-06-14 14:39:00', '2020-06-14 14:29:00', 12, 11, 1621),
(23, 1.8, '2020-06-14 14:51:00', '2020-06-14 14:40:00', 24, 12, 1621),
(24, 1.8, '2020-06-14 15:04:00', '2020-06-14 14:52:00', 2, 24, 1621);

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE `seat` (
  `seatid` bigint(20) NOT NULL,
  `trainid` bigint(20) NOT NULL,
  `compartment_type` varchar(255) DEFAULT NULL,
  `seat_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`seatid`, `trainid`, `compartment_type`, `seat_name`) VALUES
(1, 8601, 'first', '1'),
(2, 8601, 'first', '2'),
(3, 8601, 'first', '3'),
(4, 8601, 'first', '4'),
(5, 8601, 'first', '5'),
(6, 8601, 'first', '6'),
(7, 8601, 'first', '7'),
(8, 8601, 'first', '8'),
(9, 8601, 'first', '9'),
(10, 8601, 'first', '10'),
(11, 8601, 'first', '11'),
(12, 8601, 'first', '12'),
(13, 8601, 'first', '13'),
(14, 8601, 'first', '14'),
(15, 8601, 'first', '15'),
(16, 8601, 'first', '16'),
(17, 8601, 'first', '17'),
(18, 8601, 'first', '18'),
(19, 8601, 'first', '19'),
(20, 8601, 'first', '20'),
(21, 8601, 'first', '21'),
(22, 8601, 'first', '22'),
(23, 8601, 'first', '23'),
(24, 8601, 'first', '24'),
(25, 8601, 'first', '25'),
(26, 8601, 'first', '26'),
(27, 8601, 'first', '27'),
(28, 8601, 'first', '28'),
(29, 8601, 'first', '29'),
(30, 8601, 'first', '30'),
(31, 8601, 'first', '31'),
(32, 8601, 'first', '32'),
(33, 8601, 'first', '33'),
(34, 8601, 'first', '34'),
(35, 8601, 'first', '35'),
(36, 8601, 'first', '36'),
(37, 8601, 'first', '37'),
(38, 8601, 'first', '38'),
(39, 8601, 'first', '39'),
(40, 8601, 'first', '40'),
(41, 8601, 'first', '41'),
(42, 8601, 'first', '42'),
(43, 8601, 'first', '43'),
(44, 8601, 'first', '44'),
(45, 8601, 'first', '45'),
(46, 8601, 'first', '46'),
(47, 8601, 'first', '47'),
(48, 8601, 'first', '48'),
(49, 8601, 'first', '49'),
(50, 8601, 'first', '50'),
(51, 8601, 'first', '51'),
(52, 8601, 'first', '52'),
(53, 8601, 'first', '53'),
(54, 8601, 'first', '54'),
(55, 8601, 'first', '55'),
(56, 8601, 'first', '56'),
(57, 8601, 'first', '57'),
(58, 8601, 'first', '58'),
(59, 8601, 'first', '59'),
(60, 8601, 'first', '60'),
(61, 8601, 'first', '61'),
(62, 8601, 'first', '62'),
(63, 8601, 'first', '63'),
(64, 8601, 'first', '64'),
(65, 8601, 'first', '65'),
(66, 8601, 'first', '66'),
(67, 8601, 'first', '67'),
(68, 8601, 'first', '68'),
(69, 8601, 'first', '69'),
(70, 8601, 'first', '70'),
(71, 8601, 'first', '71'),
(72, 8601, 'first', '72'),
(73, 8601, 'first', '73'),
(74, 8601, 'first', '74'),
(75, 8601, 'first', '75'),
(76, 8601, 'first', '76'),
(77, 8601, 'first', '77'),
(78, 8601, 'first', '78'),
(79, 8601, 'first', '79'),
(80, 8601, 'first', '80'),
(81, 8601, 'first', '81'),
(82, 8601, 'first', '82'),
(83, 8601, 'first', '83'),
(84, 8601, 'first', '84'),
(85, 8601, 'first', '85'),
(86, 8601, 'first', '86'),
(87, 8601, 'first', '87'),
(88, 8601, 'first', '88'),
(89, 8601, 'first', '89'),
(90, 8601, 'first', '90'),
(91, 8601, 'first', '91'),
(92, 8601, 'first', '92'),
(93, 8601, 'first', '93'),
(94, 8601, 'first', '94'),
(95, 8601, 'first', '95'),
(96, 8601, 'first', '96'),
(97, 8601, 'first', '97'),
(98, 8601, 'first', '98'),
(99, 8601, 'first', '99'),
(100, 8601, 'first', '100'),
(101, 8601, 'economy', '1'),
(102, 8601, 'economy', '2'),
(103, 8601, 'economy', '3'),
(104, 8601, 'economy', '4'),
(105, 8601, 'economy', '5'),
(106, 8601, 'economy', '6'),
(107, 8601, 'economy', '7'),
(108, 8601, 'economy', '8'),
(109, 8601, 'economy', '9'),
(110, 8601, 'economy', '10'),
(111, 8601, 'economy', '11'),
(112, 8601, 'economy', '12'),
(113, 8601, 'economy', '13'),
(114, 8601, 'economy', '14'),
(115, 8601, 'economy', '15'),
(116, 8601, 'economy', '16'),
(117, 8601, 'economy', '17'),
(118, 8601, 'economy', '18'),
(119, 8601, 'economy', '19'),
(120, 8601, 'economy', '20'),
(121, 8601, 'economy', '21'),
(122, 8601, 'economy', '22'),
(123, 8601, 'economy', '23'),
(124, 8601, 'economy', '24'),
(125, 8601, 'economy', '25'),
(126, 8601, 'economy', '26'),
(127, 8601, 'economy', '27'),
(128, 8601, 'economy', '28'),
(129, 8601, 'economy', '29'),
(130, 8601, 'economy', '30'),
(131, 8601, 'economy', '31'),
(132, 8601, 'economy', '32'),
(133, 8601, 'economy', '33'),
(134, 8601, 'economy', '34'),
(135, 8601, 'economy', '35'),
(136, 8601, 'economy', '36'),
(137, 8601, 'economy', '37'),
(138, 8601, 'economy', '38'),
(139, 8601, 'economy', '39'),
(140, 8601, 'economy', '40'),
(141, 8601, 'economy', '41'),
(142, 8601, 'economy', '42'),
(143, 8601, 'economy', '43'),
(144, 8601, 'economy', '44'),
(145, 8601, 'economy', '45'),
(146, 8601, 'economy', '46'),
(147, 8601, 'economy', '47'),
(148, 8601, 'economy', '48'),
(149, 8601, 'economy', '49'),
(150, 8601, 'economy', '50'),
(151, 8601, 'economy', '51'),
(152, 8601, 'economy', '52'),
(153, 8601, 'economy', '53'),
(154, 8601, 'economy', '54'),
(155, 8601, 'economy', '55'),
(156, 8601, 'economy', '56'),
(157, 8601, 'economy', '57'),
(158, 8601, 'economy', '58'),
(159, 8601, 'economy', '59'),
(160, 8601, 'economy', '60'),
(161, 8601, 'economy', '61'),
(162, 8601, 'economy', '62'),
(163, 8601, 'economy', '63'),
(164, 8601, 'economy', '64'),
(165, 8601, 'economy', '65'),
(166, 8601, 'economy', '66'),
(167, 8601, 'economy', '67'),
(168, 8601, 'economy', '68'),
(169, 8601, 'economy', '69'),
(170, 8601, 'economy', '70'),
(171, 8601, 'economy', '71'),
(172, 8601, 'economy', '72'),
(173, 8601, 'economy', '73'),
(174, 8601, 'economy', '74'),
(175, 8601, 'economy', '75'),
(176, 8601, 'economy', '76'),
(177, 8601, 'economy', '77'),
(178, 8601, 'economy', '78'),
(179, 8601, 'economy', '79'),
(180, 8601, 'economy', '80'),
(181, 8601, 'economy', '81'),
(182, 8601, 'economy', '82'),
(183, 8601, 'economy', '83'),
(184, 8601, 'economy', '84'),
(185, 8601, 'economy', '85'),
(186, 8601, 'economy', '86'),
(187, 8601, 'economy', '87'),
(188, 8601, 'economy', '88'),
(189, 8601, 'economy', '89'),
(190, 8601, 'economy', '90'),
(191, 8601, 'economy', '91'),
(192, 8601, 'economy', '92'),
(193, 8601, 'economy', '93'),
(194, 8601, 'economy', '94'),
(195, 8601, 'economy', '95'),
(196, 8601, 'economy', '96'),
(197, 8601, 'economy', '97'),
(198, 8601, 'economy', '98'),
(199, 8601, 'economy', '99'),
(200, 8601, 'economy', '100'),
(201, 1621, 'first', '1'),
(202, 1621, 'first', '2'),
(203, 1621, 'first', '3'),
(204, 1621, 'first', '4'),
(205, 1621, 'first', '5'),
(206, 1621, 'first', '6'),
(207, 1621, 'first', '7'),
(208, 1621, 'first', '8'),
(209, 1621, 'first', '9'),
(210, 1621, 'first', '10'),
(211, 1621, 'first', '11'),
(212, 1621, 'first', '12'),
(213, 1621, 'first', '13'),
(214, 1621, 'first', '14'),
(215, 1621, 'first', '15'),
(216, 1621, 'first', '16'),
(217, 1621, 'first', '17'),
(218, 1621, 'first', '18'),
(219, 1621, 'first', '19'),
(220, 1621, 'first', '20'),
(221, 1621, 'first', '21'),
(222, 1621, 'first', '22'),
(223, 1621, 'first', '23'),
(224, 1621, 'first', '24'),
(225, 1621, 'first', '25'),
(226, 1621, 'first', '26'),
(227, 1621, 'first', '27'),
(228, 1621, 'first', '28'),
(229, 1621, 'first', '29'),
(230, 1621, 'first', '30'),
(231, 1621, 'first', '31'),
(232, 1621, 'first', '32'),
(233, 1621, 'first', '33'),
(234, 1621, 'first', '34'),
(235, 1621, 'first', '35'),
(236, 1621, 'first', '36'),
(237, 1621, 'first', '37'),
(238, 1621, 'first', '38'),
(239, 1621, 'first', '39'),
(240, 1621, 'first', '40'),
(241, 1621, 'first', '41'),
(242, 1621, 'first', '42'),
(243, 1621, 'first', '43'),
(244, 1621, 'first', '44'),
(245, 1621, 'first', '45'),
(246, 1621, 'first', '46'),
(247, 1621, 'first', '47'),
(248, 1621, 'first', '48'),
(249, 1621, 'first', '49'),
(250, 1621, 'first', '50'),
(251, 1621, 'first', '51'),
(252, 1621, 'first', '52'),
(253, 1621, 'first', '53'),
(254, 1621, 'first', '54'),
(255, 1621, 'first', '55'),
(256, 1621, 'first', '56'),
(257, 1621, 'first', '57'),
(258, 1621, 'first', '58'),
(259, 1621, 'first', '59'),
(260, 1621, 'first', '60'),
(261, 1621, 'first', '61'),
(262, 1621, 'first', '62'),
(263, 1621, 'first', '63'),
(264, 1621, 'first', '64'),
(265, 1621, 'first', '65'),
(266, 1621, 'first', '66'),
(267, 1621, 'first', '67'),
(268, 1621, 'first', '68'),
(269, 1621, 'first', '69'),
(270, 1621, 'first', '70'),
(271, 1621, 'first', '71'),
(272, 1621, 'first', '72'),
(273, 1621, 'first', '73'),
(274, 1621, 'first', '74'),
(275, 1621, 'first', '75'),
(276, 1621, 'first', '76'),
(277, 1621, 'first', '77'),
(278, 1621, 'first', '78'),
(279, 1621, 'first', '79'),
(280, 1621, 'first', '80'),
(281, 1621, 'first', '81'),
(282, 1621, 'first', '82'),
(283, 1621, 'first', '83'),
(284, 1621, 'first', '84'),
(285, 1621, 'first', '85'),
(286, 1621, 'first', '86'),
(287, 1621, 'first', '87'),
(288, 1621, 'first', '88'),
(289, 1621, 'first', '89'),
(290, 1621, 'first', '90'),
(291, 1621, 'first', '91'),
(292, 1621, 'first', '92'),
(293, 1621, 'first', '93'),
(294, 1621, 'first', '94'),
(295, 1621, 'first', '95'),
(296, 1621, 'first', '96'),
(297, 1621, 'first', '97'),
(298, 1621, 'first', '98'),
(299, 1621, 'first', '99'),
(300, 1621, 'first', '100'),
(301, 1621, 'economy', '1'),
(302, 1621, 'economy', '2'),
(303, 1621, 'economy', '3'),
(304, 1621, 'economy', '4'),
(305, 1621, 'economy', '5'),
(306, 1621, 'economy', '6'),
(307, 1621, 'economy', '7'),
(308, 1621, 'economy', '8'),
(309, 1621, 'economy', '9'),
(310, 1621, 'economy', '10'),
(311, 1621, 'economy', '11'),
(312, 1621, 'economy', '12'),
(313, 1621, 'economy', '13'),
(314, 1621, 'economy', '14'),
(315, 1621, 'economy', '15'),
(316, 1621, 'economy', '16'),
(317, 1621, 'economy', '17'),
(318, 1621, 'economy', '18'),
(319, 1621, 'economy', '19'),
(320, 1621, 'economy', '20'),
(321, 1621, 'economy', '21'),
(322, 1621, 'economy', '22'),
(323, 1621, 'economy', '23'),
(324, 1621, 'economy', '24'),
(325, 1621, 'economy', '25'),
(326, 1621, 'economy', '26'),
(327, 1621, 'economy', '27'),
(328, 1621, 'economy', '28'),
(329, 1621, 'economy', '29'),
(330, 1621, 'economy', '30'),
(331, 1621, 'economy', '31'),
(332, 1621, 'economy', '32'),
(333, 1621, 'economy', '33'),
(334, 1621, 'economy', '34'),
(335, 1621, 'economy', '35'),
(336, 1621, 'economy', '36'),
(337, 1621, 'economy', '37'),
(338, 1621, 'economy', '38'),
(339, 1621, 'economy', '39'),
(340, 1621, 'economy', '40'),
(341, 1621, 'economy', '41'),
(342, 1621, 'economy', '42'),
(343, 1621, 'economy', '43'),
(344, 1621, 'economy', '44'),
(345, 1621, 'economy', '45'),
(346, 1621, 'economy', '46'),
(347, 1621, 'economy', '47'),
(348, 1621, 'economy', '48'),
(349, 1621, 'economy', '49'),
(350, 1621, 'economy', '50'),
(351, 1621, 'economy', '51'),
(352, 1621, 'economy', '52'),
(353, 1621, 'economy', '53'),
(354, 1621, 'economy', '54'),
(355, 1621, 'economy', '55'),
(356, 1621, 'economy', '56'),
(357, 1621, 'economy', '57'),
(358, 1621, 'economy', '58'),
(359, 1621, 'economy', '59'),
(360, 1621, 'economy', '60'),
(361, 1621, 'economy', '61'),
(362, 1621, 'economy', '62'),
(363, 1621, 'economy', '63'),
(364, 1621, 'economy', '64'),
(365, 1621, 'economy', '65'),
(366, 1621, 'economy', '66'),
(367, 1621, 'economy', '67'),
(368, 1621, 'economy', '68'),
(369, 1621, 'economy', '69'),
(370, 1621, 'economy', '70'),
(371, 1621, 'economy', '71'),
(372, 1621, 'economy', '72'),
(373, 1621, 'economy', '73'),
(374, 1621, 'economy', '74'),
(375, 1621, 'economy', '75'),
(376, 1621, 'economy', '76'),
(377, 1621, 'economy', '77'),
(378, 1621, 'economy', '78'),
(379, 1621, 'economy', '79'),
(380, 1621, 'economy', '80'),
(381, 1621, 'economy', '81'),
(382, 1621, 'economy', '82'),
(383, 1621, 'economy', '83'),
(384, 1621, 'economy', '84'),
(385, 1621, 'economy', '85'),
(386, 1621, 'economy', '86'),
(387, 1621, 'economy', '87'),
(388, 1621, 'economy', '88'),
(389, 1621, 'economy', '89'),
(390, 1621, 'economy', '90'),
(391, 1621, 'economy', '91'),
(392, 1621, 'economy', '92'),
(393, 1621, 'economy', '93'),
(394, 1621, 'economy', '94'),
(395, 1621, 'economy', '95'),
(396, 1621, 'economy', '96'),
(397, 1621, 'economy', '97'),
(398, 1621, 'economy', '98'),
(399, 1621, 'economy', '99'),
(400, 1621, 'economy', '100');

-- --------------------------------------------------------

--
-- Table structure for table `station`
--

CREATE TABLE `station` (
  `stationid` bigint(20) NOT NULL,
  `latin_station_name` varchar(255) DEFAULT NULL,
  `station_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `station`
--

INSERT INTO `station` (`stationid`, `latin_station_name`, `station_name`) VALUES
(1, 'Sofia', 'София'),
(2, 'Plovdiv', 'Пловдив'),
(3, 'Stara Zagora', 'Стара Загора'),
(4, 'Yambol', 'Ямбол'),
(5, 'Varna', 'Варна'),
(6, 'Burgas', 'Бургас'),
(7, 'Sliven', 'Сливен'),
(8, 'Kazanlak', 'Казанлък'),
(9, 'Karlovo', 'Карлово'),
(10, 'Iskarsko shose', 'Искърско шосе'),
(11, 'Septemvri', 'Септември'),
(12, 'Pazardjik', 'Пазарджик'),
(13, 'Trakiya', 'Тракия'),
(14, 'Nova zagora', 'Нова загора'),
(15, 'Zimnitsa', 'Зимница'),
(16, 'Karnobat', 'Карнобат'),
(17, 'Poduyane Patnicheska', 'Подуяне пътническа'),
(18, 'Iskar', 'Искър'),
(19, 'Elin Pelin', 'Елин Пелин'),
(20, 'Vakarel', 'Вакарел'),
(21, 'Ihtiman', 'Ихтиман'),
(22, 'Kostenets', 'Костенец'),
(23, 'Belovo', 'Белово'),
(24, 'Stamboliiski', 'Стамболийски');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `ticketid` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `arrival_time` datetime DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `arrival_stationid` bigint(20) NOT NULL,
  `clientid` bigint(20) NOT NULL,
  `departure_stationid` bigint(20) NOT NULL,
  `seatid` bigint(20) NOT NULL,
  `trainid` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `departure_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`ticketid`, `price`, `arrival_time`, `purchase_date`, `arrival_stationid`, `clientid`, `departure_stationid`, `seatid`, `trainid`, `status_id`, `departure_time`) VALUES
(1, 20.33, '2020-06-11 12:30:00', '2020-06-20', 6, 8, 1, 1, 8601, 1, '2020-06-11 06:30:00'),
(2, 20.33, '2020-06-11 12:30:00', '2020-06-20', 6, 8, 1, 2, 8601, 1, '2020-06-11 06:30:00'),
(4, 25.4, '2020-06-09 12:30:00', '2020-06-26', 6, 8, 1, 1, 8601, 1, '2020-06-09 06:30:00'),
(5, 9.625, '2020-06-14 14:16:00', '2020-06-27', 23, 9, 1, 202, 1621, 1, '2020-06-14 12:25:00'),
(6, 9.625, '2020-06-14 14:16:00', '2020-06-27', 23, 9, 1, 206, 1621, 1, '2020-06-14 12:25:00'),
(16, 10.75, '2020-06-09 12:30:00', '2020-07-04', 6, 15, 15, 6, 8601, 2, '2020-06-09 11:35:00'),
(17, 17.86, '2020-06-09 12:30:00', '2020-07-09', 6, 15, 3, 6, 8601, 1, '2020-06-09 10:36:00'),
(20, 17.86, '2020-06-09 12:30:00', '2020-07-03', 6, 15, 3, 15, 8601, 1, '2020-06-09 10:36:00'),
(21, 25.41, '2020-06-09 12:30:00', '2020-07-09', 6, 15, 1, 10, 8601, 1, '2020-06-08 06:30:00'),
(22, 25.41, '2020-06-09 12:30:00', '2020-07-04', 6, 15, 1, 9, 8601, 1, '2020-06-08 06:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `ticket_status`
--

CREATE TABLE `ticket_status` (
  `id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ticket_status`
--

INSERT INTO `ticket_status` (`id`, `status`) VALUES
(1, 'unverified'),
(2, 'validated'),
(3, 'expired'),
(4, 'cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `train`
--

CREATE TABLE `train` (
  `trainid` bigint(20) NOT NULL,
  `departure_time` datetime DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `short_latin_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `train`
--

INSERT INTO `train` (`trainid`, `departure_time`, `seats`, `type`, `short_latin_name`) VALUES
(1621, '2020-06-08 12:25:00', 200, 'Бърз', 'Fast'),
(8601, '2020-06-08 06:30:00', 200, 'Бърз', 'Fast');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientid`),
  ADD KEY `FK62kgnd13p6dejaaf8745ebv3x` (`discount_type_id`);

--
-- Indexes for table `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5f2sa2o1vq6hvutgan097f00n` (`clientid`);

--
-- Indexes for table `discount_type`
--
ALTER TABLE `discount_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`routeid`),
  ADD KEY `FKfpqhy430t8xq2dqxjw235e7hv` (`arrival_stationid`),
  ADD KEY `FKbr1rnvf1s0e457hoy8dhq5dhh` (`departure_stationid`),
  ADD KEY `FKqgf71wkr0xrcjky7d3f7ljhen` (`trainid`);

--
-- Indexes for table `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`seatid`),
  ADD KEY `FKalwhftuvqd5hj9levk5it0tpb` (`trainid`);

--
-- Indexes for table `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`stationid`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticketid`),
  ADD KEY `FKkncqbmo7vu8dom86ro7okpfv3` (`arrival_stationid`),
  ADD KEY `FKmf30kksey9303em3k8768h40a` (`clientid`),
  ADD KEY `FK1jyxewrlabdkoj2okxevqc924` (`departure_stationid`),
  ADD KEY `FKsd3pwhyh8pk8ump8y4tcqje9e` (`seatid`),
  ADD KEY `FKsohqvk8f7vd1nfamkrm5dv9v7` (`trainid`),
  ADD KEY `FKf5v1wmegho829dbtu7t2pwm1q` (`status_id`);

--
-- Indexes for table `ticket_status`
--
ALTER TABLE `ticket_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `train`
--
ALTER TABLE `train`
  ADD PRIMARY KEY (`trainid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `confirmation_token`
--
ALTER TABLE `confirmation_token`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `discount_type`
--
ALTER TABLE `discount_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `route`
--
ALTER TABLE `route`
  MODIFY `routeid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `seat`
--
ALTER TABLE `seat`
  MODIFY `seatid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=401;

--
-- AUTO_INCREMENT for table `station`
--
ALTER TABLE `station`
  MODIFY `stationid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticketid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `ticket_status`
--
ALTER TABLE `ticket_status`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK62kgnd13p6dejaaf8745ebv3x` FOREIGN KEY (`discount_type_id`) REFERENCES `discount_type` (`id`);

--
-- Constraints for table `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD CONSTRAINT `FK5f2sa2o1vq6hvutgan097f00n` FOREIGN KEY (`clientid`) REFERENCES `client` (`clientid`);

--
-- Constraints for table `route`
--
ALTER TABLE `route`
  ADD CONSTRAINT `FKbr1rnvf1s0e457hoy8dhq5dhh` FOREIGN KEY (`departure_stationid`) REFERENCES `station` (`stationid`),
  ADD CONSTRAINT `FKfpqhy430t8xq2dqxjw235e7hv` FOREIGN KEY (`arrival_stationid`) REFERENCES `station` (`stationid`),
  ADD CONSTRAINT `FKqgf71wkr0xrcjky7d3f7ljhen` FOREIGN KEY (`trainid`) REFERENCES `train` (`trainid`);

--
-- Constraints for table `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `FKalwhftuvqd5hj9levk5it0tpb` FOREIGN KEY (`trainid`) REFERENCES `train` (`trainid`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK1jyxewrlabdkoj2okxevqc924` FOREIGN KEY (`departure_stationid`) REFERENCES `station` (`stationid`),
  ADD CONSTRAINT `FKf5v1wmegho829dbtu7t2pwm1q` FOREIGN KEY (`status_id`) REFERENCES `ticket_status` (`id`),
  ADD CONSTRAINT `FKkncqbmo7vu8dom86ro7okpfv3` FOREIGN KEY (`arrival_stationid`) REFERENCES `station` (`stationid`),
  ADD CONSTRAINT `FKmf30kksey9303em3k8768h40a` FOREIGN KEY (`clientid`) REFERENCES `client` (`clientid`),
  ADD CONSTRAINT `FKsd3pwhyh8pk8ump8y4tcqje9e` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`),
  ADD CONSTRAINT `FKsohqvk8f7vd1nfamkrm5dv9v7` FOREIGN KEY (`trainid`) REFERENCES `train` (`trainid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
