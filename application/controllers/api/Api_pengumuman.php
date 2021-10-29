<?php

include_once(dirname(__FILE__) . "/Baseapi.php");


class Api_pengumuman extends Baseapi
{
	public function getAllPengumuman()
	{
		$peng = $this->pengumuman->getAllPengumuman()->result();
		$data = [];
		if(!empty($peng)){
			foreach($peng as $p){
				$tmp = [];
				$tmp['judul'] = $p->judul;
				$tmp['keterangan'] = $p->keterangan;
				$tmp['added_on'] = date_format(date_create($p->added_on), "d/m/Y");
				$tmp['file'] = [];
				$file = json_decode($p->file);
				foreach($file as $f){
					$path = base_url('/assets/public/pengumuman/' . $f);
					array_push($tmp['file'], $path);
				}

				array_push($data, $tmp);
			}
			$this->success("data ditemukan", "data_pengumuman", $data);
		}else{
			$this->notFound();
		}
	}
}
