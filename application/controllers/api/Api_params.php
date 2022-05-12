<?php

include_once(dirname(__FILE__) . "/Baseapi.php");


class Api_params extends Baseapi
{
	public function getParams()
	{
		$idJabatan = $this->input->get('id_jabatan');

		$bidang = $this->db->query("SELECT * FROM tb_bidang")->result();
		$finalData = [];
		foreach ($bidang as $b) {
			$bd = [];

			$bd['nama'] = $b->nama_bidang;
			$bd['id_bidang'] = $b->id_bidang;
			$bd['bobot_bidang'] = $b->bobot;
			$bd['point'] = [];

			$point = $this->db->query("SELECT * FROM tb_point 
			WHERE id_jabatan IN ('$idJabatan') AND id_bidang IN ('$b->id_bidang') ORDER BY `tb_point`.`urutan` ASC")->result();
			foreach ($point as $p) {
				$tmp = [];
				$tmp['id_point'] =  $p->id_point;
				$tmp['isi_point'] =  $p->isi_point;
				$tmp['bobot'] =  $p->bobot;

				$tmp['subpoint'] = $this->db->query("SELECT * FROM tb_sub_point 
				WHERE id_point IN ('$p->id_point')")->result();

				array_push($bd['point'], $tmp);
			}

			array_push($finalData, $bd);
		}

		$this->success("data ditemukan", "data_param", $finalData);
	}


	public function receivedPenilaian()
	{
		$json = file_get_contents("php://input");
		$re = json_decode($json);

		$dinilaiOleh = $re->dinilaiOleh;
		$idKaryawan = $re->userid;
		$dataPenilaian = json_encode($re->penilaian);

		$ins = $this->db->insert("tb_penilaian", [
			'dinilai_oleh' => $dinilaiOleh,
			'id_karyawan' => $idKaryawan,
			'data_penilaian' => $dataPenilaian,
		]);

		if ($ins) {
			$this->success();
		} else {
			$this->internalError();
		}
	}

	public function getPenilaian()
	{
		$dinilaiOleh = $this->input->get('dinilai_oleh');
		$penilaian = $this->db->query("SELECT user1.userid as id_penilai, user1.nama as nama_penilai, 
		user2.userid as id_kar, user2.nama as nama_kar, user2.jabatan as jabatan_kar, tb_penilaian.* FROM tb_penilaian
		INNER JOIN tb_user as user1 ON tb_penilaian.dinilai_oleh=user1.userid
		INNER JOIN tb_user as user2 ON tb_penilaian.id_karyawan=user2.userid
		 WHERE dinilai_oleh IN ('$dinilaiOleh')")->result();
		$data = [];
		foreach ($penilaian as $p) {
			$tmp = [];
			$tmp['id_penilai'] = $p->id_penilai;
			$tmp['nama_penilai'] = $p->nama_penilai;
			$tmp['jabatan_kar'] = $p->jabatan_kar;
			$tmp['id_kar'] = $p->id_kar;
			$tmp['nama_kar'] = $p->nama_kar;
			$tmp['dinilai_pada'] = date_format(date_create($p->dinilai_pada), "d/m/Y");
			$tmp['data_penilaian'] = json_decode($p->data_penilaian);

			array_push($data, $tmp);
		}
		if (!empty($data)) {
			$this->success("data ditemukan", "data_penilaian", $data);
		} else {
			$this->notFound();
		}
	}

	public function getPenilaianByUser()
	{
		$dinilaiOleh = $this->input->get('id_karyawan');
		$penilaian = $this->db->query("SELECT user1.userid as id_penilai, user1.nama as nama_penilai, 
		user2.userid as id_kar, user2.nama as nama_kar, user2.jabatan as jabatan_kar, tb_penilaian.* FROM tb_penilaian
		INNER JOIN tb_user as user1 ON tb_penilaian.dinilai_oleh=user1.userid
		INNER JOIN tb_user as user2 ON tb_penilaian.id_karyawan=user2.userid
		 WHERE id_karyawan IN ('$dinilaiOleh')")->result();
		$data = [];
		foreach ($penilaian as $p) {
			$tmp = [];
			$tmp['id_penilai'] = $p->id_penilai;
			$tmp['nama_penilai'] = $p->nama_penilai;
			$tmp['jabatan_kar'] = $p->jabatan_kar;
			$tmp['id_kar'] = $p->id_kar;
			$tmp['nama_kar'] = $p->nama_kar;
			$tmp['dinilai_pada'] = date_format(date_create($p->dinilai_pada), "d/m/Y");
			$tmp['data_penilaian'] = json_decode($p->data_penilaian);

			array_push($data, $tmp);
		}
		if (!empty($data)) {
			$this->success("data ditemukan", "data_penilaian", $data);
		} else {
			$this->notFound();
		}
	}
}
