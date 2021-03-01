-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 02, 2020 at 07:00 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `simple_inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id_admin` int(2) NOT NULL,
  `nama_admin` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `id_barang` int(3) NOT NULL,
  `nama_barang` varchar(255) DEFAULT NULL,
  `stok` varchar(255) DEFAULT '0',
  `tahun` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_detail_barang_keluar`
--

CREATE TABLE `tbl_detail_barang_keluar` (
  `id_keluar` varchar(255) DEFAULT NULL,
  `id_barang` int(3) DEFAULT NULL,
  `jumlah_keluar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_detail_barang_masuk`
--

CREATE TABLE `tbl_detail_barang_masuk` (
  `id_masuk` varchar(255) DEFAULT NULL,
  `id_barang` int(3) DEFAULT NULL,
  `jumlah_masuk` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_total_barang_keluar`
--

CREATE TABLE `tbl_total_barang_keluar` (
  `id_keluar` varchar(255) NOT NULL DEFAULT '1000',
  `nama_peminjam` varchar(255) DEFAULT NULL,
  `total_jumlah_keluar` varchar(255) DEFAULT '0',
  `status` varchar(255) DEFAULT NULL,
  `tanggal_keluar` varchar(255) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_total_barang_masuk`
--

CREATE TABLE `tbl_total_barang_masuk` (
  `id_masuk` varchar(255) NOT NULL DEFAULT '1001',
  `nama_pengembali` varchar(255) DEFAULT NULL,
  `total_jumlah_masuk` varchar(255) DEFAULT '0',
  `tanggal_masuk` varchar(255) DEFAULT NULL,
  `id_admin` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `tbl_detail_barang_keluar`
--
ALTER TABLE `tbl_detail_barang_keluar`
  ADD KEY `Foreign Key` (`id_keluar`);

--
-- Indexes for table `tbl_detail_barang_masuk`
--
ALTER TABLE `tbl_detail_barang_masuk`
  ADD KEY `Foreign Key` (`id_masuk`,`id_barang`);

--
-- Indexes for table `tbl_total_barang_keluar`
--
ALTER TABLE `tbl_total_barang_keluar`
  ADD PRIMARY KEY (`id_keluar`),
  ADD KEY `Foreign Key` (`id_admin`);

--
-- Indexes for table `tbl_total_barang_masuk`
--
ALTER TABLE `tbl_total_barang_masuk`
  ADD PRIMARY KEY (`id_masuk`),
  ADD KEY `Foreign Key` (`id_admin`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id_admin` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  MODIFY `id_barang` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
