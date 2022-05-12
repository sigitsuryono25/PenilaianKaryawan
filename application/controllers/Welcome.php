<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Welcome extends CI_Controller {

	public function index()
	{
		$this->load->view('login');
	}

	function loginProcess()
	{
		$username = $this->input->post('username');
		$password = $this->input->post('password');

		//validasi dulu
		$this->form_validation->set_rules('username', 'Username', 'required');
		$this->form_validation->set_rules('password', 'Password', 'required');

		if ($this->form_validation->run() == FALSE) {
			echo json_encode(['message' => validation_errors(), 'code' => 500]);
		} else {
			//validasi berhasil
			$where = ['userid' => $username, 'password' => md5($password)];
			// $field = "*", $where = null, $order = null, $group = null, $limit = null, $showCompiled = false
			$user = $this->user->getAllUser("*", $where)->row();

			if (!empty($user)) {
				//user ditemukan
				//set session
				//siapkan data untuk session
				$sess = ['username' => $user->userid, 'nama' => $user->nama, 'level' => $user->jabatan];

				//set sessionnya
				$this->session->set_userdata($sess);

				//kasih response kalo data ditemukan dan berhasil
				echo json_encode(['message' => 'data ditemukan', 'code' => 200]);
			} else {
				//user not found
				echo json_encode(['message' => 'data tidak ada', 'code' => 500]);
			}
		}
	}

	function logout()
	{
		//destroy session
		$this->session->sess_destroy();

		//redirect to login page
		redirect(site_url());
	}
}
