<?php

/**
 * include file Basecontroller
 * so we can extends into it.
 *  
 * */
include_once(dirname(__FILE__) . "/Basecontroller.php");


class User extends Basecontroller
{
	//getAllUser($field = "*", $where = null, $order = null, $group = null, $limit = null, $showCompiled = false)


	function getAllUser()
	{
		$data['user'] = $this->user->getAllUser()->result();
		$master['page'] = $this->load->view('user/list', $data, true);
		$master['title'] = "Daftar User/Karyawan";
		$this->load->view('template', $master, FALSE);
	}

	public function formAdd()
	{
		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$master['page'] = $this->load->view('user/add', $data, TRUE);
		$master['title'] = "Tambah User/Karyawan";
		$this->load->view('template', $master);
	}

	public function insertData()
	{
		$re = file_get_contents("php://input");
		$json = json_decode($re, true);
		$json['password'] = md5($json['password']);

		$ins = $this->crud->addData("tb_user", $json);
		if ($ins) {
			$this->success();
		} else {
			$this->internalError();
		}
	}

	public function formEdit()
	{
		$userid = $this->input->get('userid');
		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$data['user'] = $this->user->getAllUser("*", ['userid' => $userid])->row();
		$master['page'] = $this->load->view('user/edit', $data, TRUE);
		$master['title'] = "Form Edit User";
		$this->load->view('template', $master);
	}

	public function hapusData()
	{
		$userid = $this->input->get('userid');
		$where = ['userid' => $userid];
		$delt = $this->crud->deleteData("tb_user", $where);
		if ($delt) {
			redirect(site_url('user/list'));
		} else {
			$this->internalError();
		}
	}

	public function editData()
	{
		$re = file_get_contents("php://input");
		$json = json_decode($re, true);
		if (!empty($json['password'])) {
			//update password kalo passwordnya nggak sama dengan kosong
			$json['password'] = md5($json['password']);
		} else {
			//hapus index password dari array
			unset($json['password']);
		}

		$where = ['userid' => $json['userid']];
		//unser userid supaya nggak ikut diupdate
		unset($json['userid']);

		$ins = $this->crud->updateData("tb_user", $json, $where);
		if ($ins) {
			$this->success();
		} else {
			$this->internalError();
		}
	}

	function getUserByJabatan()
	{
		$jabatan = $this->input->get('jabatan');

		$user = $this->user->getAllUser("nama, userid, jabatan", ['jabatan' => $jabatan])->result();
		if (!empty($user)) {
			$this->success("Data ditemukan", "data", $user);
		} else {
			$this->notFound();
		}
	}
}
