-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2021 at 11:12 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kspps_bmt_bima`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_bidang`
--

CREATE TABLE `tb_bidang` (
  `id_bidang` bigint(20) NOT NULL,
  `nama_bidang` text NOT NULL,
  `bobot` varchar(100) NOT NULL,
  `added_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `added_by` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_bidang`
--

INSERT INTO `tb_bidang` (`id_bidang`, `nama_bidang`, `bobot`, `added_on`, `added_by`) VALUES
(1, 'Pengawasan Bidang Kedisiplinan', '0.15', '2021-10-27 08:45:57', 'sigitsuryono25'),
(2, 'Pengawasan Bidang inisiatif Kerja', '0.15', '2021-10-27 08:46:03', 'sigitsuryono25'),
(3, 'Pengawasan Bidang Kerjasama', '0.10', '2021-10-27 08:46:08', 'sigitsuryono25'),
(4, 'Pengawasan Bidang Pelaksanaan Tugas', '0.40', '2021-10-27 08:46:18', 'sigitsuryono25'),
(5, 'Pengawaan Bidang Kepribadian', '0.20', '2021-10-27 08:46:22', 'sigitsuryono25');

-- --------------------------------------------------------

--
-- Table structure for table `tb_jabatan`
--

CREATE TABLE `tb_jabatan` (
  `id_jabatan` bigint(20) NOT NULL,
  `nama_jabatan` text NOT NULL,
  `added_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `added_by` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_jabatan`
--

INSERT INTO `tb_jabatan` (`id_jabatan`, `nama_jabatan`, `added_on`, `added_by`) VALUES
(1, 'Teller', '2021-10-22 03:10:13', 'sigitsuryono25'),
(2, 'Kabag Operasional', '2021-10-22 03:10:20', 'sigitsuryono25'),
(3, 'Staff Pemasaran', '2021-10-22 03:10:29', 'sigitsuryono25'),
(4, 'Kabag Pemasaran', '2021-10-22 03:10:38', 'sigitsuryono25'),
(5, 'Kepala KCP', '2021-10-22 03:10:47', 'sigitsuryono25'),
(6, 'Kepala Cabang', '2021-10-22 03:10:55', 'sigitsuryono25');

-- --------------------------------------------------------

--
-- Table structure for table `tb_penilaian`
--

CREATE TABLE `tb_penilaian` (
  `id` int(11) NOT NULL,
  `dinilai_oleh` varchar(100) NOT NULL,
  `id_karyawan` varchar(100) NOT NULL,
  `data_penilaian` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`data_penilaian`)),
  `dinilai_pada` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_point`
--

CREATE TABLE `tb_point` (
  `id_point` bigint(20) NOT NULL,
  `id_bidang` bigint(20) NOT NULL,
  `isi_point` text NOT NULL,
  `bobot` double NOT NULL,
  `id_jabatan` bigint(20) NOT NULL,
  `urutan` int(11) NOT NULL,
  `added_by` varchar(200) NOT NULL,
  `added_on` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_point`
--

INSERT INTO `tb_point` (`id_point`, `id_bidang`, `isi_point`, `bobot`, `id_jabatan`, `urutan`, `added_by`, `added_on`) VALUES
(19166121, 5, 'Aplikasi akhlakul Karima (JAB)', 0.25, 1, 2, '', '2021-10-27 04:06:05'),
(69859042, 4, 'Kemampuan melaksanakan Tugas sesuai Job Dis', 0.3, 1, 1, '', '2021-10-27 04:02:42'),
(118060210, 3, 'Kemampuan mengalokasikan waktu bagi bag lain', 0.3, 1, 3, '', '2021-10-27 03:59:48'),
(270160394, 1, 'Jumlah Jam Kerja < 5 jam /hari', 0.2, 1, 2, '', '2021-10-27 03:54:13'),
(370892429, 2, 'Kemampuan menyerap dan menjabarkan Tugas', 0.3, 1, 2, '', '2021-10-27 03:57:06'),
(380715651, 1, 'Ketepatan dalam Laporan', 0.2, 1, 4, '', '2021-10-27 03:54:13'),
(424897284, 4, 'Kemampuan memenuhi Target Kerja', 0.7, 1, 2, '', '2021-10-27 04:02:42'),
(493332936, 1, 'Ketaatan terhadap aturan kerja', 0.2, 1, 3, '', '2021-10-27 03:54:13'),
(610526789, 3, 'Kesiapan menerima input dari pihak lain', 0.3, 1, 2, '', '2021-10-27 03:59:48'),
(761520485, 2, 'Kemampuan mengatasi kendala dalam tugas', 0.3, 1, 3, '', '2021-10-27 03:57:06'),
(836148514, 5, 'Loyalitas dalam kerja', 0.2, 1, 3, '', '2021-10-27 04:06:05'),
(862219698, 5, 'Tanggung jawab atas job yang diterima', 0.25, 1, 4, '', '2021-10-27 04:06:05'),
(1150584524, 3, ' Kemampuan kerjasama dalam Bidangnya', 0.4, 1, 1, '', '2021-10-27 03:59:48'),
(1177051116, 5, 'Pelaksanaan Ibadah', 0.3, 1, 1, '', '2021-10-27 04:06:05'),
(1280696529, 2, 'Konsep2 strategis yang disampaikan', 0.4, 1, 1, '', '2021-10-27 03:57:06'),
(1967890968, 1, 'Ketepatan Waktu / jam Hadir', 0.4, 1, 1, '', '2021-10-27 03:54:13');

-- --------------------------------------------------------

--
-- Table structure for table `tb_sub_point`
--

CREATE TABLE `tb_sub_point` (
  `id_sub` bigint(20) NOT NULL,
  `id_point` bigint(20) NOT NULL,
  `isi_sub_point` text NOT NULL,
  `added_by` varchar(200) NOT NULL,
  `added_on` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_sub_point`
--

INSERT INTO `tb_sub_point` (`id_sub`, `id_point`, `isi_sub_point`, `added_by`, `added_on`) VALUES
(37, 1967890968, 'Hadir Jam 08.00', '', '2021-10-27 03:54:13'),
(38, 1967890968, 'Pulang', '', '2021-10-27 03:54:13'),
(39, 493332936, 'Penggunaan Seragam ', '', '2021-10-27 03:54:13'),
(40, 493332936, 'Penggunaan Atribut (Cocard, Pin Dll) ', '', '2021-10-27 03:54:13'),
(41, 493332936, 'Tingkat kehadiran (SIA)', '', '2021-10-27 03:54:13'),
(42, 493332936, 'Taat mengikuti kegiatan lembaga', '', '2021-10-27 03:54:13'),
(43, 493332936, 'Kerapian tempat kerja', '', '2021-10-27 03:54:13'),
(44, 380715651, 'Laporan Harian Kas tepat waktu ', '', '2021-10-27 03:54:13'),
(45, 380715651, 'Ketelitian dalam input transaksi', '', '2021-10-27 03:54:13'),
(46, 1280696529, 'Usulan terkait dengan Job nya', '', '2021-10-27 03:57:06'),
(47, 1280696529, 'Usulan lintas bagian', '', '2021-10-27 03:57:06'),
(48, 370892429, 'Kemandirian dalam tugas', '', '2021-10-27 03:57:06'),
(49, 370892429, 'Dapat mengeksekusi tugas dalam batas kewenangannya', '', '2021-10-27 03:57:06'),
(50, 761520485, 'Tidak adanya komplain dari anggota', '', '2021-10-27 03:57:06'),
(51, 761520485, 'Dapat menyelesaikan masalah dalam batas kewenangannya', '', '2021-10-27 03:57:06'),
(52, 1150584524, 'Mampu kerjasama dengan Atasan', '', '2021-10-27 03:59:48'),
(53, 1150584524, 'Mampu kerjasama dengan sesama teller', '', '2021-10-27 03:59:48'),
(54, 610526789, 'Input dari atasan', '', '2021-10-27 03:59:48'),
(55, 610526789, 'Input dari rekan kerja', '', '2021-10-27 03:59:48'),
(56, 610526789, 'Input dari anggota', '', '2021-10-27 03:59:48'),
(57, 118060210, 'Kemauan membantu bagian lain dalam satu kantor', '', '2021-10-27 03:59:48'),
(58, 118060210, 'Kemauan membantu bagian lain di kantor lain', '', '2021-10-27 03:59:48'),
(59, 69859042, 'Menerima, membayarkan dan mencatat semua transaksi tunai', '', '2021-10-27 04:02:42'),
(60, 69859042, 'Menyusun bukti transaksi', '', '2021-10-27 04:02:42'),
(61, 69859042, 'Terselesaikannya Laporan Harian Kas', '', '2021-10-27 04:02:42'),
(62, 69859042, 'Menjaga keamanan ruang teller', '', '2021-10-27 04:02:42'),
(63, 69859042, 'Memastikan jumlah uang dan terhindar dari uang palsu', '', '2021-10-27 04:02:42'),
(64, 424897284, 'Target Simpanan (Simpanan lancar dan berjangka)', '', '2021-10-27 04:02:42'),
(65, 424897284, ' Target SMP, SIMPOK dan SIMWA', '', '2021-10-27 04:02:42'),
(66, 424897284, 'Target realisasi pembiayaan', '', '2021-10-27 04:02:42'),
(67, 424897284, 'Target angsuran pokok', '', '2021-10-27 04:02:42'),
(68, 424897284, 'Target angsuran margin', '', '2021-10-27 04:02:42'),
(69, 1177051116, 'Pelaksanaan sholat lima waktu', '', '2021-10-27 04:06:05'),
(70, 1177051116, 'Pelaksanaan sholat dhuha', '', '2021-10-27 04:06:05'),
(71, 1177051116, 'Pelaksanaan tadarus', '', '2021-10-27 04:06:05'),
(72, 1177051116, 'Pelaksanaan puasa Wajib', '', '2021-10-27 04:06:05'),
(73, 1177051116, 'Pelaksanaan puasa Sunnah', '', '2021-10-27 04:06:05'),
(74, 19166121, 'Jujur dalam bekerja', '', '2021-10-27 04:06:05'),
(75, 19166121, 'Tidak ada fraud', '', '2021-10-27 04:06:05'),
(76, 836148514, 'Loyal terhadap SOP', '', '2021-10-27 04:06:05'),
(77, 836148514, 'Loyal terhadap Lembaga', '', '2021-10-27 04:06:05'),
(78, 836148514, 'Loyal terhadap Pimpinan', '', '2021-10-27 04:06:05'),
(79, 862219698, 'Menyelesaikan tugas tepat waktu', '', '2021-10-27 04:06:05'),
(80, 862219698, 'Menyelesaikan tugas sesuai SOP', '', '2021-10-27 04:06:05');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `userid` varchar(100) NOT NULL,
  `nama` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `jabatan` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`userid`, `nama`, `password`, `jabatan`, `created_at`) VALUES
('1634785822462', ' Abdul Hadi Nashir', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2021-10-21 03:18:33'),
('1634785822463', ' Drs. Syamsudin ', '827ccb0eea8a706c4c34a16891f84e7b', 6, '2021-10-21 03:18:33'),
('1634785822464', ' Fathmy Azizah ', '827ccb0eea8a706c4c34a16891f84e7b', 4, '2021-10-21 03:18:33'),
('1634785822465', ' Pujiantho Alhadi ', '827ccb0eea8a706c4c34a16891f84e7b', 2, '2021-10-21 03:18:33'),
('1634785822466', ' Hesti Purwanti ', '827ccb0eea8a706c4c34a16891f84e7b', 0, '2021-10-21 03:18:33'),
('1634785822467', ' Agus Hamid Rosyidi ', '827ccb0eea8a706c4c34a16891f84e7b', 5, '2021-10-21 03:18:33'),
('1634785822468', ' Ismail ', '827ccb0eea8a706c4c34a16891f84e7b', 3, '2021-10-21 03:18:33'),
('1634785822469', ' Khusna Rofiqoh ', '827ccb0eea8a706c4c34a16891f84e7b', 2, '2021-10-21 03:18:33'),
('1634785822470', ' Slamet Widodo ', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2021-10-21 03:18:33'),
('1634785822471', ' Suristyo ', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2021-10-21 03:18:33'),
('1634785822472', ' Enny Setyowati ', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2021-10-21 03:18:33'),
('1634785822473', ' Supriyono ', '827ccb0eea8a706c4c34a16891f84e7b', 5, '2021-10-21 03:18:33'),
('1634785822474', ' Khotib Anshori', '827ccb0eea8a706c4c34a16891f84e7b', 2, '2021-10-21 03:18:33'),
('1634785822475', ' Mazid Efendi ', '827ccb0eea8a706c4c34a16891f84e7b', 3, '2021-10-21 03:18:33'),
('1634785822476', ' Arif Satya Himawan ', '827ccb0eea8a706c4c34a16891f84e7b', 4, '2021-10-21 03:18:33'),
('1634785822477', ' Zani Setiawan ', '827ccb0eea8a706c4c34a16891f84e7b', 5, '2021-10-21 03:18:33'),
('1634785822478', ' Tri Handayani ', '827ccb0eea8a706c4c34a16891f84e7b', 6, '2021-10-21 03:18:33'),
('1634785822479', ' Slamet Istanto', '827ccb0eea8a706c4c34a16891f84e7b', 5, '2021-10-21 03:18:33'),
('1634785822480', ' Tri Wibowo Sulaksono', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2021-10-21 03:18:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_bidang`
--
ALTER TABLE `tb_bidang`
  ADD PRIMARY KEY (`id_bidang`);

--
-- Indexes for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  ADD PRIMARY KEY (`id_jabatan`);

--
-- Indexes for table `tb_penilaian`
--
ALTER TABLE `tb_penilaian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_point`
--
ALTER TABLE `tb_point`
  ADD PRIMARY KEY (`id_point`);

--
-- Indexes for table `tb_sub_point`
--
ALTER TABLE `tb_sub_point`
  ADD PRIMARY KEY (`id_sub`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_bidang`
--
ALTER TABLE `tb_bidang`
  MODIFY `id_bidang` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  MODIFY `id_jabatan` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_penilaian`
--
ALTER TABLE `tb_penilaian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_point`
--
ALTER TABLE `tb_point`
  MODIFY `id_point` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2107803749;

--
-- AUTO_INCREMENT for table `tb_sub_point`
--
ALTER TABLE `tb_sub_point`
  MODIFY `id_sub` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
