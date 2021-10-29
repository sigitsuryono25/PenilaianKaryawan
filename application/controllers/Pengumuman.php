<?php

/**
 * include file Basecontroller
 * so we can extends into it.
 *  
 * */
include_once(dirname(__FILE__) . "/Basecontroller.php");


class Pengumuman extends Basecontroller
{
	public function formAdd()
	{
		$master['page'] = $this->load->view('pengumuman/add-pengumuman', null, TRUE);
		$master['title'] = "Tambah Pengumuman";
		$this->load->view('template', $master);
	}

	public function daftarPengumuman()
	{
		$data['pengumuman'] = $this->pengumuman->getAllPengumuman()->result();
		$master['page'] = $this->load->view('pengumuman/daftar-pengumuman', $data, TRUE);
		$master['title'] = "Tambah Pengumuman";
		$this->load->view('template', $master);
	}

	public function insertData()
	{
		$path = "./assets/public/pengumuman/";
		if (!is_dir($path)) {
			mkdir($path, 0775, TRUE);
		}

		$judul = $this->input->post('judul');
		$keterangan = $this->input->post('keterangan');

		$tmp = $_FILES['file']['tmp_name'];
		$name = $_FILES['file']['name'];
		// $files = $_FILES['foto'];
		$foto = [];
		foreach ($tmp as $key => $g) {
			$n = explode(".", $name[$key]);
			$ns = md5(round(microtime(true) * 1000)) . "." . $n[count($n) - 1];
			move_uploaded_file($g, $path . $ns);
			array_push($foto, $ns);
		}

		$data = [
			'keterangan' => $keterangan,
			'judul' => $judul,
			'file' => json_encode($foto),
		];

		$ins = $this->crud->addData('tb_pengumuman', $data);
		if ($ins > 0) {
			$this->success();
		} else {
			foreach ($foto as $f) {
				unlink($path . $f);
			}

			$this->internalError();
		}
	}

	public function formEditData()
	{
		$id = $this->input->get('id');
		$where = ['id' => $id];
		$data['pengumuman'] = $this->pengumuman->getAllPengumuman("*", $where)->row();
		$master['page'] = $this->load->view('pengumuman/edit-pengumuman', $data, TRUE);
		$master['title'] = "Edit Pengumuman";
		$this->load->view('template', $master);
	}

	public function updateData()
	{
		$path = "./assets/public/pengumuman/";

		$id = $this->input->post('id');
		$judul = $this->input->post('judul');
		$keterangan = $this->input->post('keterangan');
		$confDel = $this->input->post('conf-del');

		$where = ['id' => $id];
		$det = $this->pengumuman->getAllPengumuman("*", $where)->row();
		$file = json_decode($det->file);
		if ($confDel == "Y") {
			foreach ($file as $f) {
				if (file_exists($path . $f)) {
					unlink($path . $f);
				}
			}
			$file = [];
		}

		$newFt = [];
		$tmp = $_FILES['file']['tmp_name'];
		$name = $_FILES['file']['name'];
		// $files = $_FILES['foto'];
		foreach ($tmp as $key => $g) {
			$n = explode(".", $name[$key]);
			$ns = md5(round(microtime(true) * 1000)) . "." . $n[count($n) - 1];
			move_uploaded_file($g, $path . $ns);
			array_push($file, $ns);
			array_push($newFt, $ns);
		}

		$data = [
			'judul' => $judul,
			'keterangan' => $keterangan,
			'file' => json_encode($file),
		];

		$ins = $this->crud->updateData('tb_pengumuman', $data, $where);
		if ($ins > 0) {
			$this->success();
		} else {
			foreach ($newFt as $f) {
				if (file_exists($path . $f)) {
					unlink($path . $f);
				}
			}
			$this->internalError();
		}
	}

	public function deleteData()
	{
		$id = $this->input->get('id');
		$peng = $this->pengumuman->getAllPengumuman("*", ['id' => $id])->row();
		$file = json_decode($peng->file);
		foreach ($file as $p) {
			$path = "./assets/public/pengumuman/" . $p;
			if (file_exists($path)) {
				unlink($path);
			}
		}

		$this->crud->deleteData("tb_pengumuman", ['id' => $id]);
		redirect(site_url('pengumuman/list'));
	}
}
