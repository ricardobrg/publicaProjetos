-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28-Jan-2021 às 05:57
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `address`
--

INSERT INTO `address` (`id`, `cep`, `complement`, `district`, `locality`, `street`, `uf`) VALUES
(1, '89120-000', '', '', 'Timbó', '', 'SC');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `contact`
--

INSERT INTO `contact` (`id`, `email`, `phone`) VALUES
(1, 'entaoquerdizer@gmail.com', '(11) 98181-0220');

-- --------------------------------------------------------

--
-- Estrutura da tabela `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(1, 'ADM'),
(11, 'TI'),
(13, 'Contabilidade');

-- --------------------------------------------------------

--
-- Estrutura da tabela `discount`
--

CREATE TABLE `discount` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `discount`
--

INSERT INTO `discount` (`id`, `date`, `name`, `value`) VALUES
(3, '2021-01-27', 'INSS', 574.59);

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(24);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `manageDepartment_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `person`
--

INSERT INTO `person` (`DTYPE`, `id`, `name`, `cnpj`, `socialReason`, `description`, `price`, `cpf`, `pis`, `admissionDate`, `canVacation`, `extraSalary`, `grossSalary`, `inVacation`, `lastVacation`, `password`, `user`, `vacationSize`, `workHours`, `address_id`, `contact_id`, `manageDepartment_id`, `role_id`) VALUES
('Collaborator', 1, 'Manager', NULL, NULL, NULL, NULL, '999.999.999-99', NULL, NULL, NULL, 0, 3500, b'0', NULL, '$2a$10$f3na6gGJPl.13UVIhVed.OD7CgsccGBGMPx26luHj9os93FR5aXKq', 'admin', '0', 0, 1, 1, NULL, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `person_discount`
--

CREATE TABLE `person_discount` (
  `Collaborator_id` int(11) NOT NULL,
  `discounts_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `person_discount`
--

INSERT INTO `person_discount` (`Collaborator_id`, `discounts_id`) VALUES
(1, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `point`
--

CREATE TABLE `point` (
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `dayMinute` int(11) NOT NULL,
  `collaborator_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`id`, `accessLevel`, `nameRole`, `salary`, `department_id`) VALUES
(2, 2, 'Streammer', 3500, 11),
(15, 2, 'Novo Cargo', 12345, 1),
(17, 0, 'Teste', 12, NULL),
(19, 1, 'asdas', 123, 1),
(21, 2, 'analista de suporte n2', 100, 11),
(22, 2, 'analista de suporte n2', 100, 1),
(23, 2, 'Novo Cargo teste', 10, 11);

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
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `FKtgl1k9ymkqq7mikrkfym7f4bi` (`manageDepartment_id`),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `point`
--
ALTER TABLE `point`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

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
  ADD CONSTRAINT `FKtgl1k9ymkqq7mikrkfym7f4bi` FOREIGN KEY (`manageDepartment_id`) REFERENCES `department` (`id`),
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
