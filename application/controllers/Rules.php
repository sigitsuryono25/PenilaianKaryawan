<?php
include_once(dirname(__FILE__) . "/Basecontroller.php");

class Rules extends Basecontroller{
	public function rulesData()
	{
		$rules = $this->db->query("SELECT * FROM tb_rules 
		INNER JOIN tb_jabatan 
		ON tb_rules.jabatan=tb_jabatan.id_jabatan")->result_array();
		$data['rules'] = [];
		foreach($rules as $r){
			$rules = json_decode($r['rules']);
			$r['rules'] = $rules;
			$r['rules_jbtn'] = $this->db->select("*")->from("tb_jabatan")->where_in('id_jabatan', $rules)->get()->result_array();
			array_push($data['rules'], $r);
		}

		$master['page'] = $this->load->view('rules/rules-data', $data, TRUE);
		$master['title'] = "Data Rules";
		$this->load->view('template', $master);		
	}

	public function addRules()
	{
		$jabatan = $this->input->post("jabatan");
		$rules = $this->input->post('rules');

		$check = $this->db->query("SELECT * FROM tb_rules WHERE jabatan IN ('$jabatan')")->row();
		if(!empty($check)){
			echo '<script>
				alert(`Penilaian untuk level/jabatan ini sudah di atur. Silahkan gunakan menu edit`);
				history.back();
			</script>';
			return;
		}

		if(empty($rules)){
			echo '<script>
				alert(`Anda belum memilih jabatan yang dapat dinilai`);
				history.back();
			</script>';
			return;
		}

		$data = [
			'jabatan' => $jabatan,
			'rules' => json_encode($rules)
		];
		
		$ins = $this->crud->addData('tb_rules', $data);
		if($ins){
			redirect(site_url('rules/list'));
		}else{
			echo '<script>
				alert(`rules gagal disimpan`);
			</script>';
		}
	}

	public function formRules()
	{
		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$master['page'] = $this->load->view('rules/form-rules', $data, TRUE);
		$master['title'] = "Form Rules";
		$this->load->view('template', $master);
	}

	public function deleteRules()
	{
		$idRules = $this->input->get('rules_id');
		$where = ['id_rules' => $idRules];
		$del = $this->crud->deleteData("tb_rules", $where);
		if($del){
			redirect(site_url('rules/list'));
		}else{
			echo '<script>
				alert(`rules gagal disimpan`);
			</script>';
		}
		
	}
	
	public function formEdit()
	{
		$idRules = $this->input->get('rules_id');
		$data['rules'] = $this->db->query("SELECT * FROM tb_rules 
		WHERE id_rules IN ('$idRules')")->row();
		$data['jabatan'] = $this->jabatan->getAllJabatan()->result();
		$master['page'] = $this->load->view('rules/form-rules-edit', $data, TRUE);
		$master['title'] = "Form Edit Rules";
		$this->load->view('template', $master);
	}

	public function updateRules()
	{
		$rulesId = $this->input->post("rules_id");
		$jabatan = $this->input->post("jabatan");
		$rules = $this->input->post('rules');

		if(empty($rules)){
			echo '<script>
				alert(`Anda belum memilih jabatan yang dapat dinilai`);
				history.back();
			</script>';
			return;
		}
		$where = ['id_rules' => $rulesId];

		$data = [
			'jabatan' => $jabatan,
			'rules' => json_encode($rules)
		];
		
		$ins = $this->crud->updateData('tb_rules', $data, $where);
		if($ins){
			redirect(site_url('rules/list'));
		}else{
			echo '<script>
				alert(`rules gagal disimpan`);
			</script>';
		}
	}
}
