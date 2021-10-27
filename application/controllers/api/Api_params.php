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
}
