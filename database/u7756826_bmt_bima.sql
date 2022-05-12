-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 14, 2022 at 02:18 PM
-- Server version: 10.3.32-MariaDB-cll-lve
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u7756826_bmt_bima`
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
(1, 'Pengawasan Bidang Kedisiplinan', '0.15', '2021-10-29 02:08:32', 'sigitsuryono25'),
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
(6, 'Kepala Cabang', '2021-10-22 03:10:55', 'sigitsuryono25'),
(7, 'Karyawan', '2021-11-08 02:06:42', 'sigitsuryono25');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pengumuman`
--

CREATE TABLE `tb_pengumuman` (
  `id` bigint(20) NOT NULL,
  `judul` varchar(200) NOT NULL,
  `file` varchar(200) DEFAULT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `keterangan` text NOT NULL,
  `added_by` varchar(100) NOT NULL,
  `added_on` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_pengumuman`
--

INSERT INTO `tb_pengumuman` (`id`, `judul`, `file`, `cover`, `keterangan`, `added_by`, `added_on`) VALUES
(6, 'Pengumuman Libur Akhir Tahun BMT BIMA', '[\"c169c6f93bd14bea247ac681a1575ad6.pdf\",\"41719958f3535da5422995c26fa39a7c.pdf\",\"260eee00a123472c536169bc78ceac51.pdf\"]', 'e854f6111013908a68ab95b516529f08.jpg', 'Pengumuman Libur Akhir Tahun BMT BIMA', '', '2021-10-29 07:57:38'),
(18, 'Prosedur Kerja Selama Pandemi', NULL, '', '<p>Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;<span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><span style=\"font-size: 0.875rem;\">Prosedur Kerja Selama Pandemi&nbsp;Prosedur Kerja Selama Pandemi&nbsp;</span><br></p>', '', '2021-11-01 07:00:58'),
(19, 'Pengumuman Kenaikan Jabatan', NULL, '', '<p>Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;<span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><span style=\"font-size: 0.875rem;\">Pengumuman Kenaikan Jabatan&nbsp;Pengumuman Kenaikan Jabatan&nbsp;</span><br></p>', '', '2021-11-01 07:02:44'),
(36, 'DIKLAT Staf Pusat Manajemen Proyek PT. PLN (Persero)', '[\"f39f60644877d76392d1a7b64e824929.jpg\"]', '9bc443b75aef8fcfb53c30445c172fa4.jpg', '<p>DIKLAT Staf Pusat Manajemen Proyek PT. PLN (Persero)<br></p>', '', '2021-11-08 03:20:31');

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

--
-- Dumping data for table `tb_penilaian`
--

INSERT INTO `tb_penilaian` (`id`, `dinilai_oleh`, `id_karyawan`, `data_penilaian`, `dinilai_pada`) VALUES
(56, '1634785822463', '1634785822425', '[{\"bobotPoint\":\"0.15\",\"idBidang\":\"1\",\"nilaiAkhirPerPoint\":\"1.05\",\"nilaiDanPoint\":[{\"giveScore\":7,\"idPoint\":\"1747915758\",\"nilai\":2.8000000000000003},{\"giveScore\":7,\"idPoint\":\"1747915764\",\"nilai\":1.4000000000000001},{\"giveScore\":7,\"idPoint\":\"1747915765\",\"nilai\":1.4000000000000001},{\"giveScore\":7,\"idPoint\":\"1747915769\",\"nilai\":1.4000000000000001}]},{\"bobotPoint\":\"0.15\",\"idBidang\":\"2\",\"nilaiAkhirPerPoint\":\"1.05\",\"nilaiDanPoint\":[{\"giveScore\":7,\"idPoint\":\"1747915756\",\"nilai\":2.8000000000000003},{\"giveScore\":7,\"idPoint\":\"1747915762\",\"nilai\":2.1},{\"giveScore\":7,\"idPoint\":\"1747915767\",\"nilai\":2.1}]},{\"bobotPoint\":\"0.10\",\"idBidang\":\"3\",\"nilaiAkhirPerPoint\":\"0.70\",\"nilaiDanPoint\":[{\"giveScore\":7,\"idPoint\":\"1747915757\",\"nilai\":2.8000000000000003},{\"giveScore\":7,\"idPoint\":\"1747915763\",\"nilai\":2.1},{\"giveScore\":7,\"idPoint\":\"1747915766\",\"nilai\":2.1}]},{\"bobotPoint\":\"0.40\",\"idBidang\":\"4\",\"nilaiAkhirPerPoint\":\"2.80\",\"nilaiDanPoint\":[{\"giveScore\":7,\"idPoint\":\"1747915755\",\"nilai\":2.1},{\"giveScore\":7,\"idPoint\":\"1747915761\",\"nilai\":4.8999999999999995}]},{\"bobotPoint\":\"0.20\",\"idBidang\":\"5\",\"nilaiAkhirPerPoint\":\"1.40\",\"nilaiDanPoint\":[{\"giveScore\":7,\"idPoint\":\"1747915759\",\"nilai\":2.1},{\"giveScore\":7,\"idPoint\":\"1747915760\",\"nilai\":1.75},{\"giveScore\":7,\"idPoint\":\"1747915768\",\"nilai\":1.4000000000000001},{\"giveScore\":7,\"idPoint\":\"1747915770\",\"nilai\":1.75}]}]', '2022-01-20 02:58:05');

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
(52248055, 5, 'Pelaksanaan Ibadah', 0.3, 1, 1, '', '2021-11-08 04:07:21'),
(178850901, 4, 'Kemampuan memenuhi Target Kerja', 0.7, 1, 2, '', '2021-11-08 04:04:24'),
(209247853, 3, 'Kemampuan kerjasama dalam Bidangnya\n', 0.4, 1, 1, '', '2021-11-08 04:02:13'),
(276229882, 5, 'Tanggung jawab atas job yang diterima', 0.25, 1, 4, '', '2021-11-08 04:07:21'),
(287084673, 3, 'Kesiapan menerima input dari pihak lain\n', 0.3, 1, 2, '', '2021-11-08 04:02:13'),
(326651394, 4, 'Kemampuan melaksanakan Tugas sesuai Job Dis', 0.3, 1, 1, '', '2021-11-08 04:04:24'),
(334017688, 1, ' Jumlah Jam Kerja < 5 jam /hari', 0.2, 1, 2, '', '2021-11-08 03:59:32'),
(355720043, 2, 'Konsep2 strategis yang disampaikan', 0.4, 1, 1, '', '2021-11-08 04:01:00'),
(403470649, 3, 'Kemampuan mengalokasikan waktu bagi bag lain\n', 0.3, 1, 3, '', '2021-11-08 04:02:13'),
(455728167, 1, 'Ketepatan dalam Laporan', 0.2, 1, 4, '', '2021-11-08 03:59:32'),
(683805166, 5, 'Loyalitas dalam kerja', 0.2, 1, 3, '', '2021-11-08 04:07:21'),
(729843806, 2, 'Kemampuan menyerap dan menjabarkan Tugas', 0.3, 1, 2, '', '2021-11-08 04:01:00'),
(733550472, 2, 'Kemampuan mengatasi kendala dalam tugas', 0.3, 1, 3, '', '2021-11-08 04:01:00'),
(817731099, 1, 'Ketaatan terhadap aturan kerja', 0.2, 1, 3, '', '2021-11-08 03:59:32'),
(847128388, 5, 'Aplikasi akhlakul Karima (JAB)', 0.25, 1, 2, '', '2021-11-08 04:07:21'),
(1747915754, 1, 'Ketepatan Waktu / jam Hadir', 0.4, 1, 1, '', '2021-11-08 03:59:32'),
(1747915755, 4, 'Kemampuan melaksanakan Tugas sesuai Job Dis', 0.3, 7, 1, '', '2021-11-08 04:04:24'),
(1747915756, 2, 'Konsep2 strategis yang disampaikan', 0.4, 7, 1, '', '2021-11-08 04:01:00'),
(1747915757, 3, 'Kemampuan kerjasama dalam Bidangnya\r\n', 0.4, 7, 1, '', '2021-11-08 04:02:13'),
(1747915758, 1, 'Ketepatan Waktu / jam Hadir', 0.4, 7, 1, '', '2021-11-08 03:59:32'),
(1747915759, 5, 'Pelaksanaan Ibadah', 0.3, 7, 1, '', '2021-11-08 04:07:21'),
(1747915760, 5, 'Aplikasi akhlakul Karima (JAB)', 0.25, 7, 2, '', '2021-11-08 04:07:21'),
(1747915761, 4, 'Kemampuan memenuhi Target Kerja', 0.7, 7, 2, '', '2021-11-08 04:04:24'),
(1747915762, 2, 'Kemampuan menyerap dan menjabarkan Tugas', 0.3, 7, 2, '', '2021-11-08 04:01:00'),
(1747915763, 3, 'Kesiapan menerima input dari pihak lain\r\n', 0.3, 7, 2, '', '2021-11-08 04:02:13'),
(1747915764, 1, 'Jumlah Jam Kerja < 5 jam /hari', 0.2, 7, 2, '', '2021-11-08 03:59:32'),
(1747915765, 1, 'Ketaatan terhadap aturan kerja', 0.2, 7, 3, '', '2021-11-08 03:59:32'),
(1747915766, 3, 'Kemampuan mengalokasikan waktu bagi bag lain\r\n', 0.3, 7, 3, '', '2021-11-08 04:02:13'),
(1747915767, 2, 'Kemampuan mengatasi kendala dalam tugas', 0.3, 7, 3, '', '2021-11-08 04:01:00'),
(1747915768, 5, 'Loyalitas dalam kerja', 0.2, 7, 3, '', '2021-11-08 04:07:21'),
(1747915769, 1, 'Ketepatan dalam Laporan', 0.2, 7, 4, '', '2021-11-08 03:59:32'),
(1747915770, 5, 'Tanggung jawab atas job yang diterima', 0.25, 7, 4, '', '2021-11-08 04:07:21');

-- --------------------------------------------------------

--
-- Table structure for table `tb_rules`
--

CREATE TABLE `tb_rules` (
  `id_rules` bigint(20) NOT NULL,
  `jabatan` int(11) NOT NULL,
  `rules` longtext NOT NULL,
  `added_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `added_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_rules`
--

INSERT INTO `tb_rules` (`id_rules`, `jabatan`, `rules`, `added_on`, `added_by`) VALUES
(1, 2, '[\"4\",\"5\",\"6\"]', '2022-01-26 03:54:11', ''),
(2, 6, '[\"2\",\"4\",\"5\",\"7\"]', '2022-01-26 06:27:53', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_settings`
--

CREATE TABLE `tb_settings` (
  `id` int(11) NOT NULL,
  `end_point_presensi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_settings`
--

INSERT INTO `tb_settings` (`id`, `end_point_presensi`) VALUES
(1, 'http://103.247.14.100:8081/iclock/accounts/login/?next=/iclock/data/iclock/');

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
(1, 1747915754, 'Hadir Jam 08.00', '', '2021-11-08 03:59:32'),
(2, 1747915754, 'Pulang', '', '2021-11-08 03:59:32'),
(3, 817731099, 'Penggunaan Seragam ', '', '2021-11-08 03:59:32'),
(4, 817731099, 'Penggunaan Atribut (Cocard, Pin Dll)', '', '2021-11-08 03:59:32'),
(5, 817731099, 'Tingkat kehadiran (SIA)', '', '2021-11-08 03:59:32'),
(6, 817731099, 'Taat mengikuti kegiatan lembaga', '', '2021-11-08 03:59:32'),
(7, 817731099, 'Kerapian tempat kerja', '', '2021-11-08 03:59:32'),
(8, 455728167, 'Laporan Harian Kas tepat waktu ', '', '2021-11-08 03:59:32'),
(9, 455728167, ' Ketelitian dalam input transaksi', '', '2021-11-08 03:59:32'),
(10, 355720043, 'Usulan terkait dengan Job nya', '', '2021-11-08 04:01:00'),
(11, 355720043, 'Usulan lintas bagian', '', '2021-11-08 04:01:00'),
(12, 729843806, 'Kemandirian dalam tugas', '', '2021-11-08 04:01:00'),
(13, 729843806, 'Dapat mengeksekusi tugas dalam batas kewenangannya', '', '2021-11-08 04:01:00'),
(14, 733550472, 'Tidak adanya komplain dari anggota', '', '2021-11-08 04:01:00'),
(15, 733550472, 'Dapat menyelesaikan masalah dalam batas kewenangannya', '', '2021-11-08 04:01:00'),
(16, 209247853, 'Mampu kerjasama dengan Atasan', '', '2021-11-08 04:02:13'),
(17, 209247853, 'Mampu kerjasama dengan sesama teller', '', '2021-11-08 04:02:13'),
(18, 287084673, 'Input dari atasan', '', '2021-11-08 04:02:13'),
(19, 287084673, 'Input dari rekan kerja', '', '2021-11-08 04:02:13'),
(20, 287084673, 'Input dari anggota', '', '2021-11-08 04:02:13'),
(21, 403470649, 'Kemauan membantu bagian lain dalam satu kantor', '', '2021-11-08 04:02:13'),
(22, 403470649, 'Kemauan membantu bagian lain di kantor lain', '', '2021-11-08 04:02:13'),
(23, 326651394, 'Menerima, membayarkan dan mencatat semua transaksi tunai', '', '2021-11-08 04:04:24'),
(24, 326651394, 'Menyusun bukti transaksi', '', '2021-11-08 04:04:24'),
(25, 326651394, 'Terselesaikannya Laporan Harian Kas', '', '2021-11-08 04:04:24'),
(26, 326651394, 'Menjaga keamanan ruang teller', '', '2021-11-08 04:04:24'),
(27, 326651394, 'Memastikan jumlah uang dan terhindar dari uang palsu', '', '2021-11-08 04:04:24'),
(28, 178850901, 'Target Simpanan (Simpanan lancar dan berjangka)', '', '2021-11-08 04:04:24'),
(29, 178850901, 'Target SMP, SIMPOK dan SIMWA', '', '2021-11-08 04:04:24'),
(30, 178850901, 'Target realisasi pembiayaan', '', '2021-11-08 04:04:24'),
(31, 178850901, 'Target angsuran pokok', '', '2021-11-08 04:04:24'),
(32, 178850901, 'Target angsuran margin', '', '2021-11-08 04:04:24'),
(33, 52248055, 'Pelaksanaan sholat lima waktu', '', '2021-11-08 04:07:21'),
(34, 52248055, 'Pelaksanaan sholat dhuha', '', '2021-11-08 04:07:21'),
(35, 52248055, 'Pelaksanaan tadarus', '', '2021-11-08 04:07:21'),
(36, 52248055, 'Pelaksanaan puasa Wajib', '', '2021-11-08 04:07:21'),
(37, 52248055, 'Pelaksanaan puasa Sunnah', '', '2021-11-08 04:07:21'),
(38, 847128388, ' Jujur dalam bekerja', '', '2021-11-08 04:07:21'),
(39, 847128388, 'Tidak ada fraud', '', '2021-11-08 04:07:21'),
(40, 683805166, 'Loyal terhadap SOP', '', '2021-11-08 04:07:21'),
(41, 683805166, 'Loyal terhadap Lembaga', '', '2021-11-08 04:07:21'),
(42, 683805166, 'Loyal terhadap Pimpinan', '', '2021-11-08 04:07:21'),
(43, 276229882, 'Menyelesaikan tugas tepat waktu', '', '2021-11-08 04:07:21'),
(44, 276229882, 'Menyelesaikan tugas sesuai SOP', '', '2021-11-08 04:07:21'),
(45, 1747915758, 'Hadir Jam 08.00', '', '2021-11-08 03:59:32'),
(46, 1747915758, 'Pulang', '', '2021-11-08 03:59:32'),
(47, 1747915765, 'Penggunaan Seragam ', '', '2021-11-08 03:59:32'),
(48, 1747915765, 'Penggunaan Atribut (Cocard, Pin Dll)', '', '2021-11-08 03:59:32'),
(49, 1747915765, 'Tingkat kehadiran (SIA)', '', '2021-11-08 03:59:32'),
(50, 1747915765, 'Taat mengikuti kegiatan lembaga', '', '2021-11-08 03:59:32'),
(51, 1747915765, 'Kerapian tempat kerja', '', '2021-11-08 03:59:32'),
(52, 1747915769, 'Laporan Harian Kas tepat waktu ', '', '2021-11-08 03:59:32'),
(53, 1747915769, ' Ketelitian dalam input transaksi', '', '2021-11-08 03:59:32'),
(54, 1747915756, 'Usulan terkait dengan Job nya', '', '2021-11-08 04:01:00'),
(55, 1747915756, 'Usulan lintas bagian', '', '2021-11-08 04:01:00'),
(56, 1747915762, 'Kemandirian dalam tugas', '', '2021-11-08 04:01:00'),
(57, 1747915762, 'Dapat mengeksekusi tugas dalam batas kewenangannya', '', '2021-11-08 04:01:00'),
(58, 1747915767, 'Tidak adanya komplain dari anggota', '', '2021-11-08 04:01:00'),
(59, 1747915767, 'Dapat menyelesaikan masalah dalam batas kewenangannya', '', '2021-11-08 04:01:00'),
(60, 1747915757, 'Mampu kerjasama dengan Atasan', '', '2021-11-08 04:02:13'),
(61, 1747915757, 'Mampu kerjasama dengan sesama teller', '', '2021-11-08 04:02:13'),
(62, 1747915763, 'Input dari atasan', '', '2021-11-08 04:02:13'),
(63, 1747915763, 'Input dari rekan kerja', '', '2021-11-08 04:02:13'),
(64, 1747915763, 'Input dari anggota', '', '2021-11-08 04:02:13'),
(65, 1747915766, 'Kemauan membantu bagian lain dalam satu kantor', '', '2021-11-08 04:02:13'),
(66, 1747915766, 'Kemauan membantu bagian lain di kantor lain', '', '2021-11-08 04:02:13'),
(67, 1747915755, 'Menerima, membayarkan dan mencatat semua transaksi tunai', '', '2021-11-08 04:04:24'),
(68, 1747915755, 'Menyusun bukti transaksi', '', '2021-11-08 04:04:24'),
(69, 1747915755, 'Terselesaikannya Laporan Harian Kas', '', '2021-11-08 04:04:24'),
(70, 1747915755, 'Menjaga keamanan ruang teller', '', '2021-11-08 04:04:24'),
(71, 1747915755, 'Memastikan jumlah uang dan terhindar dari uang palsu', '', '2021-11-08 04:04:24'),
(72, 1747915761, 'Target Simpanan (Simpanan lancar dan berjangka)', '', '2021-11-08 04:04:24'),
(73, 1747915761, 'Target SMP, SIMPOK dan SIMWA', '', '2021-11-08 04:04:24'),
(74, 1747915761, 'Target realisasi pembiayaan', '', '2021-11-08 04:04:24'),
(75, 1747915761, 'Target angsuran pokok', '', '2021-11-08 04:04:24'),
(76, 1747915761, 'Target angsuran margin', '', '2021-11-08 04:04:24'),
(77, 1747915759, 'Pelaksanaan sholat lima waktu', '', '2021-11-08 04:07:21'),
(78, 1747915759, 'Pelaksanaan sholat dhuha', '', '2021-11-08 04:07:21'),
(79, 1747915759, 'Pelaksanaan tadarus', '', '2021-11-08 04:07:21'),
(80, 1747915759, 'Pelaksanaan puasa Wajib', '', '2021-11-08 04:07:21'),
(81, 1747915759, 'Pelaksanaan puasa Sunnah', '', '2021-11-08 04:07:21'),
(82, 1747915760, ' Jujur dalam bekerja', '', '2021-11-08 04:07:21'),
(83, 1747915760, 'Tidak ada fraud', '', '2021-11-08 04:07:21'),
(84, 1747915768, 'Loyal terhadap SOP', '', '2021-11-08 04:07:21'),
(85, 1747915768, 'Loyal terhadap Lembaga', '', '2021-11-08 04:07:21'),
(86, 1747915768, 'Loyal terhadap Pimpinan', '', '2021-11-08 04:07:21'),
(87, 1747915770, 'Menyelesaikan tugas tepat waktu', '', '2021-11-08 04:07:21');

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
('1634785822425', 'Sigit Suryono', '827ccb0eea8a706c4c34a16891f84e7b', 7, '2021-11-08 03:22:27'),
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
-- Indexes for table `tb_pengumuman`
--
ALTER TABLE `tb_pengumuman`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `tb_rules`
--
ALTER TABLE `tb_rules`
  ADD PRIMARY KEY (`id_rules`);

--
-- Indexes for table `tb_settings`
--
ALTER TABLE `tb_settings`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id_bidang` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  MODIFY `id_jabatan` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_pengumuman`
--
ALTER TABLE `tb_pengumuman`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `tb_penilaian`
--
ALTER TABLE `tb_penilaian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `tb_point`
--
ALTER TABLE `tb_point`
  MODIFY `id_point` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1747915771;

--
-- AUTO_INCREMENT for table `tb_rules`
--
ALTER TABLE `tb_rules`
  MODIFY `id_rules` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_settings`
--
ALTER TABLE `tb_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_sub_point`
--
ALTER TABLE `tb_sub_point`
  MODIFY `id_sub` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
