<?php

/**
 * include file Basecontroller
 * so we can extends into it.
 *  
 * */
include_once(dirname(__FILE__) . "/Basecontroller.php");


class Penilaian extends Basecontroller
{
	function formAddParam()
	{
		$data['bidang'] = $this->bidang->getAllBidang()->result();
		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$master['page'] = $this->load->view('penilaian/add-params', $data, TRUE);
		$master['title'] = "Tambah Parameter Penilaian";
		$this->load->view('template', $master);
	}

	public function daftarParams()
	{
		$idJabatan = $this->input->get('idjabatan');
		$data['id_jabatan'] = (!empty($idJabatan)) ? $idJabatan : 1;
		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$data['bidang'] = $this->bidang->getAllBidang()->result();
		$master['page'] = $this->load->view('penilaian/daftar-params', $data, TRUE);
		$master['title'] = "Daftar Parameter Penilaian";
		$this->load->view('template', $master);
	}


	function insertData()
	{
		$json = file_get_contents("php://input");
		$re = json_decode($json);
		$pointArray = [];
		$subpointArray = [];
		foreach ($re->point as $key => $r) {
			foreach ($r as $k => $value) {
				$point = [];
				$point['urutan'] = $key + 1;
				$point['id_point'] = $k;
				$point['id_bidang'] = $re->bidang;
				$point['isi_point'] = $value->isi;
				$point['bobot'] = $value->bobot[0];
				$point['id_jabatan'] = $re->id_jabatan;
				array_push($pointArray, $point);

				if (!empty($value->subpoint)) {
					foreach ($value->subpoint as $sp) {
						$sub = [];
						$sub['id_point'] = $k;
						$sub['isi_sub_point'] = $sp;

						array_push($subpointArray, $sub);
					}
				}
			}
		}

		$ins = $this->db->insert_batch('tb_point', $pointArray);
		$insSub = $this->db->insert_batch('tb_sub_point', $subpointArray);

		if ($ins && $insSub) {
			$this->success();
		}
	}

	public function hasilPenilaian()
	{
		$karyawan = $this->input->get('karyawan');
		$idJabatan = $this->input->get('id_jabatan');
		$bulan = $this->input->get('bulan');
		$tahun = $this->input->get('tahun');
		if (empty($karyawan)) {
			$data['bidang'] = [];
			$data['id_jabatan'] = '';
			$data['daftar_kar'] = '';
			$data['bulan'] = '';
			$data['tahun'] = '';
		} else {
			$data['bidang'] = $this->bidang->getAllBidang()->result();
			$data['karyawan'] = $karyawan;
			$data['id_jabatan'] = $idJabatan;
			$data['daftar_kar'] = $this->user->getAllUser("nama, userid, jabatan", ['jabatan' => $idJabatan])->result();
			$data['periode'] = $this->etc->indonesiaDate($tahun . "-" . $bulan . "-01", null, "", false, true);
			$data['bulan'] = $bulan;
			$data['tahun'] = $tahun;
		}

		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$master['page'] = $this->load->view('penilaian/result', $data, TRUE);
		$master['title'] = "Hasil Penilaian";
		$this->load->view('template', $master);
	}
}