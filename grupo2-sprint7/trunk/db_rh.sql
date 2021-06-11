-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21-Jan-2021 às 18:24
-- Versão do servidor: 10.4.17-MariaDB
-- versão do PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_rh`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `locality` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `address`
--

INSERT INTO `address` (`id`, `cep`, `complement`, `district`, `locality`, `street`, `uf`) VALUES
(1, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(2, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(3, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(4, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(5, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(6, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(7, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(8, '89121-000', 'house', 'divineia', 'RDC', 'pedro II', 'SC'),
(9, '89121-000', 'house', 'divineia', 'RDC', 'pedro II', 'SC'),
(10, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC'),
(11, '89121-000', 'house', 'divineia', 'RDC', 'pedro II', 'SC'),
(22, '89120-000', 'house 2', 'imigrantes', 'TBO', 'Portugal', 'SC');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cep`
--

CREATE TABLE `cep` (
  `cep` varchar(255) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `localidade` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `contact`
--

INSERT INTO `contact` (`id`, `email`, `phone`) VALUES
(1, 'friotechmaster@gmail.com', '(47) 99752-0622'),
(2, 'liptalkidiomas@gmail.com', '(47) 99304-0360'),
(3, 'prowayinfo@gmail.com', '(47) 3322-3344'),
(4, 'jardinagemflor@gmail.com', '(47) 90248-5493'),
(5, 'cleanmaster@gmail.com', '(47) 90952-2835'),
(6, 'justChill@gmail.com', '(47) 99245-5548'),
(7, 'justChill@gmail.com', '(47) 99245-5548'),
(8, 'justChill@gmail.com', '(47) 99245-5548'),
(9, 'justChill@gmail.com', '(47) 99245-5548'),
(10, 'justChill@gmail.com', '(47) 99245-5548'),
(11, 'justChill@gmail.com', '(47) 99245-5548'),
(12, 'justChill@gmail.com', '(47) 99245-5548'),
(13, 'ficandoMaas@gmail.com', '(47) 99245-5548'),
(14, 'ficandoMaas@gmail.com', '(47) 99245-5548'),
(15, 'justChill@gmail.com', '(47) 99245-5548'),
(16, 'ficandoMaas@gmail.com', '(47) 99245-5548'),
(27, 'justChill@gmail.com', '(47) 99245-5548');

-- --------------------------------------------------------

--
-- Estrutura da tabela `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `department`
--

INSERT INTO `department` (`id`, `name`, `manager_id`) VALUES
(1, 'Suporte Técnico', NULL),
(3, 'Suporte Técnico', NULL),
(6, 'Suporte Técnico', NULL),
(8, 'Suporte Técnico', NULL),
(10, 'Suporte Técnico', NULL),
(12, 'Suporte Técnico', NULL),
(14, 'Suporte Técnico', NULL),
(16, 'Suporte Técnico', NULL),
(17, 'Suporte Técnico', NULL),
(20, 'Suporte Técnico', NULL),
(22, 'Suporte Técnico', NULL),
(24, 'Suporte Técnico', NULL),
(26, 'Suporte Técnico', NULL),
(39, 'Suporte Técnico', NULL),
(41, 'Suporte Técnico', NULL),
(43, 'Suporte Técnico', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `discount`
--

CREATE TABLE `discount` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(45);

-- --------------------------------------------------------

--
-- Estrutura da tabela `payroll`
--

CREATE TABLE `payroll` (
  `id` int(11) NOT NULL,
  `begin` date DEFAULT NULL,
  `finalSalary` double DEFAULT NULL,
  `finish` date DEFAULT NULL,
  `payrollDiscounts` varchar(255) DEFAULT NULL,
  `timeLog` date DEFAULT NULL,
  `collaborator_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `person`
--

CREATE TABLE `person` (
  `DTYPE` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cnpj` varchar(255) DEFAULT NULL,
  `socialReason` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `pis` varchar(255) DEFAULT NULL,
  `admissionDate` varchar(255) DEFAULT NULL,
  `canVacation` bit(1) DEFAULT NULL,
  `extraSalary` double DEFAULT NULL,
  `grossSalary` double DEFAULT NULL,
  `inVacation` bit(1) DEFAULT NULL,
  `lastVacation` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `vacationSize` varchar(255) DEFAULT NULL,
  `workHours` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `person`
--

INSERT INTO `person` (`DTYPE`, `id`, `name`, `cnpj`, `socialReason`, `description`, `price`, `cpf`, `pis`, `admissionDate`, `canVacation`, `extraSalary`, `grossSalary`, `inVacation`, `lastVacation`, `password`, `user`, `vacationSize`, `workHours`, `address_id`, `contact_id`, `role_id`) VALUES
('ServiceProvider', 1, 'Friotech', '00.578.165/0001-45', 'Friotech Master Ltda', NULL, 500, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
('ServiceProvider', 2, 'LipTalk Idiomas', NULL, 'LipTalk Idiomas', NULL, 500, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
('ServiceProvider', 3, 'Proway', NULL, 'Proway Info', NULL, 500, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL),
('ServiceProvider', 4, 'Jardinagem Flor do Caribe', '59.740.604/0001-17', 'Jardinagem Flor do Caribe', NULL, 500, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL),
('ServiceProvider', 5, 'Clean Master', '05.740.261/0001-61', 'Clean Master Ltda', NULL, 500, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL),
('Collaborator', 29, 'Eh noes irmao', NULL, NULL, NULL, NULL, '122.923.519-19', '830.20183.25-1', '12/01/2021', b'0', 550, 0, b'0', '2004-02-04', '$2a$10$63stVqGJgQy/0YY9/kT17OjoM0Uzod2r47lkyTBCkhUxaHcbJEs6y', NULL, '0', 8, 3, 8, NULL),
('Collaborator', 30, 'Eh noes irmao', NULL, NULL, NULL, NULL, '122.923.519-19', '830.20183.25-1', '12/01/2021', b'0', 550, 0, b'0', '2004-02-04', '$2a$10$nJtnS.1Tx55kVCf/9aQ3HOAxcsB3M0Nth1d0/67lu1wEDm7avtZUm', NULL, '0', 8, 3, 8, NULL),
('Collaborator', 31, 'Eh noes irmao', NULL, NULL, NULL, NULL, '122.923.519-19', '830.20183.25-1', '12/01/2021', b'0', 550, 0, b'0', '2004-02-04', '$2a$10$JfO2TZJhnr6T3gdq2HO7K.Xr3J.xr5AqoK1E8kfPrIj.40ULS8HGu', NULL, '0', 8, 3, 8, NULL),
('Collaborator', 32, 'Eh noes irmao', NULL, NULL, NULL, NULL, '122.923.519-19', '830.20183.25-1', '12/01/2021', b'0', 550, 0, b'0', '2004-02-04', '$2a$10$vqTJr5V4zd8ZcOqcxGn0newWIQfEt205nALCn1z9NyhcdJtVGqkm6', NULL, '0', 8, 3, 8, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `person_discount`
--

CREATE TABLE `person_discount` (
  `Collaborator_id` int(11) NOT NULL,
  `discounts_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `point`
--

CREATE TABLE `point` (
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `dayMinute` int(11) NOT NULL,
  `collaborator_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `accessLevel` int(11) DEFAULT NULL,
  `nameRole` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`id`, `accessLevel`, `nameRole`, `salary`, `department_id`) VALUES
(2, NULL, 'analista de suporte n2', 0, 1),
(4, NULL, 'analista de suporte n2', 0, 3),
(5, NULL, 'analista de suporte n2', 0, 6),
(7, NULL, 'analista de suporte n2', 0, 8),
(9, NULL, 'analista de suporte n2', 0, 10),
(11, NULL, 'analista de suporte n2', 0, 12),
(13, NULL, 'analista de suporte n2', 0, 14),
(15, NULL, 'analista de suporte n2', 0, 16),
(18, NULL, 'analista de suporte n4', 0, 17),
(19, NULL, 'analista de suporte n4', 0, 20),
(21, NULL, 'analista de suporte n4', 0, 22),
(23, NULL, 'analista de suporte n2', 0, 24),
(25, NULL, 'analista de suporte n2', 0, 26),
(40, NULL, 'analista de suporte n4', 0, 39),
(42, NULL, 'analista de suporte n4', 0, 41),
(44, NULL, 'analista de suporte n2', 0, 43);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cep`
--
ALTER TABLE `cep`
  ADD PRIMARY KEY (`cep`);

--
-- Índices para tabela `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4bwdkfxh80ex5nlystwtm5non` (`manager_id`);

--
-- Índices para tabela `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `payroll`
--
ALTER TABLE `payroll`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKluvhx4g1tk15y89wcqoa2jajc` (`collaborator_id`);

--
-- Índices para tabela `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6i7nduc8blbwp1dbfwavvnvvx` (`address_id`),
  ADD KEY `FKbi2pspahr0wcmi26je6si7xve` (`contact_id`),
  ADD KEY `FKtjksra5jea9apa6hsks456die` (`role_id`);

--
-- Índices para tabela `person_discount`
--
ALTER TABLE `person_discount`
  ADD UNIQUE KEY `UK_qa30jxby5cuvc1rkyxpl2ppcu` (`discounts_id`),
  ADD KEY `FKe9lbib00juah3fo5i1l2b3j7r` (`Collaborator_id`);

--
-- Índices para tabela `point`
--
ALTER TABLE `point`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1tr3w1kd479b4ahd3osdreuwl` (`collaborator_id`);

--
-- Índices para tabela `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6wf8e6adcissy1s2mwimik9we` (`department_id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de tabela `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de tabela `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de tabela `point`
--
ALTER TABLE `point`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `FK4bwdkfxh80ex5nlystwtm5non` FOREIGN KEY (`manager_id`) REFERENCES `person` (`id`);

--
-- Limitadores para a tabela `payroll`
--
ALTER TABLE `payroll`
  ADD CONSTRAINT `FKluvhx4g1tk15y89wcqoa2jajc` FOREIGN KEY (`collaborator_id`) REFERENCES `person` (`id`);

--
-- Limitadores para a tabela `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FK6i7nduc8blbwp1dbfwavvnvvx` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FKbi2pspahr0wcmi26je6si7xve` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`),
  ADD CONSTRAINT `FKtjksra5jea9apa6hsks456die` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Limitadores para a tabela `person_discount`
--
ALTER TABLE `person_discount`
  ADD CONSTRAINT `FKe9lbib00juah3fo5i1l2b3j7r` FOREIGN KEY (`Collaborator_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKon4qve37lxhvwugexs1biipcd` FOREIGN KEY (`discounts_id`) REFERENCES `discount` (`id`);

--
-- Limitadores para a tabela `point`
--
ALTER TABLE `point`
  ADD CONSTRAINT `FK1tr3w1kd479b4ahd3osdreuwl` FOREIGN KEY (`collaborator_id`) REFERENCES `person` (`id`);

--
-- Limitadores para a tabela `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK6wf8e6adcissy1s2mwimik9we` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
