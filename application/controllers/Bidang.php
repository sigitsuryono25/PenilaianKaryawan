<?php

/**
 * include file Basecontroller
 * so we can extends into it.
 *  
 * */
include_once(dirname(__FILE__) . "/Basecontroller.php");


class Bidang extends Basecontroller
{
	public function formAdd()
	{
		$master['page'] = $this->load->view('bidang/add', null, TRUE);
		$master['title'] = "Bidang Penilaian";
		$this->load->view('template', $master);
	}

	public function dataBidang()
	{
		$bidang = $this->bidang->getAllBidang()->result_array();

		echo json_encode(['data' => $bidang]);
	}

	public function insertData()
	{
		$json = file_get_contents("php://input");
		$data = json_decode($json, true);

		$ins = $this->crud->addData('tb_bidang', $data);
		if($ins > 0){
			$this->success();
		}else{
			$this->internalError();
		}
	}

	public function getDetailById()
	{
		$json = file_get_contents("php://input");
		$data = json_decode($json);
		$where = ['id_bidang' => $data->id_bidang];
		
		$bidang = $this->bidang->getAllBidang("*", $where)->row();
		if(!empty($bidang)){
			$this->success('data ditemukan', 'data', $bidang);
		}else{
			$this->internalError();
		}
	}

	public function deleteData()
	{
		$json = file_get_contents("php://input");
		$data = json_decode($json);
		$where = ['id_bidang' => $data->id_bidang];
		$ins = $this->crud->deleteData('tb_bidang', $where);
		if($ins > 0){
			$this->success();
		}else{
			$this->internalError();
		}
	}
}
