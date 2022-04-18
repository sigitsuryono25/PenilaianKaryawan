<?php


defined('BASEPATH') or exit('No direct script access allowed');

class Etc extends CI_Model
{

	public function indonesiaDate($tgl, $jam = null, $delimiter = " ", $isCut = false, $monthOnly = false)
	{
		$hari = substr($tgl, 8, 2);
		// if($hari < 10){
		// 	$hari = "0".$hari;
		// }
		$tahun = ($isCut) ? substr(substr($tgl, 0, 4), -2) : substr($tgl, 0, 4);
		$nama_bulan = $this->bulan($tgl, $isCut);
		if ($monthOnly) {
			$tgl_oke = $nama_bulan . ' ' . $tahun;
		} else {
			$tgl_oke = $hari . ' ' . $nama_bulan . ' ' . $tahun . "$delimiter " . $jam;
		}
		return $tgl_oke;
	}
	function bulan($tgl, $isCut)
	{
		$bulan = substr($tgl, 5, 2);
		switch ($bulan) {
			case 1:
				$bulan = ($isCut) ? "Jan" : "Januari";
				break;
			case 2:
				$bulan = ($isCut) ? "Feb" : "Februari";
				break;
			case 3:
				$bulan = ($isCut) ? "Mar" : "Maret";
				break;
			case 4:
				$bulan = ($isCut) ? "Apr" : "April";
				break;
			case 5:
				$bulan = ($isCut) ? "Mei" : "Mei";
				break;
			case 6:
				$bulan = ($isCut) ? "Jun" : "Juni";
				break;
			case 7:
				$bulan = ($isCut) ? "Jul" : "Juli";
				break;
			case 8:
				$bulan = ($isCut) ? "Agus" : "Agustus";
				break;
			case 9:
				$bulan = ($isCut) ? "Sep" : "September";
				break;
			case 10:
				$bulan = ($isCut) ? "Okt" : "Oktober";
				break;
			case 11:
				$bulan = ($isCut) ? "Nov" : "November";
				break;
			case 12:
				$bulan = ($isCut) ? "Des" : "Desember";
				break;
		}
		return $bulan;
	}

	public function rps($angka)
    {
        $rupiah = number_format($angka, 0, ',', '.');
        return $rupiah;
    }
}

/* End of file Etc.php */
