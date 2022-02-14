<?php


class Settings extends CI_Controller{
    function index(){
        $data['settings'] = $this->db->query("SELECT * FROM tb_settings")->row();
        $master['page'] = $this->load->view('settings/settings', $data, TRUE);
		$master['title'] = "Pengaturan";
		$this->load->view('template', $master);
	}
	
	function updateData(){
	    $end_point_presensi = $this->input->post('end_point_presensi');
	    $where = ['id' => 1];
	    $data = ['end_point_presensi' => $end_point_presensi];
	    
	    $ins = $this->crud->updateData('tb_settings', $data, $where);
	    if($ins){
	        redirect(site_url('settings'));
	    }else{
	        die('something wrong just happened');
	    }
	}
}
