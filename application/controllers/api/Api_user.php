<?php

include_once(dirname(__FILE__) . "/Baseapi.php");


class Api_user extends Baseapi
{
	public function index()
	{
		$json = file_get_contents("php://input");
		$re = json_decode($json);

		$where = ['userid' => $re->userid, 'password' => md5($re->password)];
		$user = $this->user->getAllUser("*", $where)->row();

		if (!empty($user)) {
			$this->success("Data ditemukan", 'data_user', $user);
		} else {
			$this->notFound();
		}
	}

	function getAllUser()
	{
		$p = $this->input->get('p');
		$idJabatan = $this->input->get('id_jabatan');
		
		$page = (!empty($p)) ? $p : 0;
		$limit = 20;
		$nextLimit = $page * $limit;
		// $field = "*", $where = null, $order = null, $group = null, $limit = null, $showCompiled = false
		$user = $this->user->getAllUser("*", ['jabatan' => $idJabatan], null, null, [$nextLimit, $limit], false)->result();
		if (!empty($user)) {
			$this->success("data ditemukan", "data_karyawan", $user);
		} else {
			$this->notFound();
		}
	}

	public function getJabatan()
	{
		$jabatan = $this->db->query("SELECT * FROM tb_jabatan")->result();
		if(!empty($jabatan)){
			$this->success("data ditemukan", "data_jabatan", $jabatan);
		}else{
			$this->notFound();
		}
	}
}
