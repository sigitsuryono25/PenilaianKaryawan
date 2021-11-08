<?php

include_once(dirname(__FILE__) . "/Baseapi.php");


class Api_pengumuman extends Baseapi
{
	public function getAllPengumuman()
	{
		$peng = $this->pengumuman->getAllPengumuman()->result();
		$data = [];
		if (!empty($peng)) {
			foreach ($peng as $p) {
				$tmp = [];
				$tmp['judul'] = $p->judul;
				$tmp['keterangan'] = strip_tags($p->keterangan);
				$tmp['cover'] = (!empty($p->cover)) ? base_url('assets/cover/'. $p->cover) : "";
				$tmp['added_on'] = date_format(date_create($p->added_on), "d/m/Y");
				$tmp['file'] = [];
				if (!empty($p->file)) {
					$file = json_decode($p->file);
					foreach ($file as $f) {
						$path = base_url('/assets/public/pengumuman/' . $f);
						array_push($tmp['file'], $path);
					}
				}

				array_push($data, $tmp);
			}
			$this->success("data ditemukan", "data_pengumuman", $data);
		} else {
			$this->notFound();
		}
	}

	function detailPengumuman()
	{
		$idPeng = $this->input->get('id_pengumuman');

		$peng = $this->pengumuman->getAllPengumuman("*", ['id' => $idPeng])->row_array();
		$peng['file'] = json_decode($peng['file']);
		if (!empty($peng)) {
			$this->success("data ditemukan", "data_pengumuman", $peng);
		} else {
			$this->notFound();
		}
	}
}
