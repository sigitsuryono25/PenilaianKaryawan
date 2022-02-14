<?php

include_once(dirname(__FILE__) . "/Baseapi.php");


class Api_settings extends Baseapi
{
    function getSettings(){
        $conf = $this->db->query("SELECT * FROM tb_settings")->row();
        if(!empty($conf)){
            $this->success('data ditemukan','settings', $conf);
        }else{
            $this->notFound();
        }
    }   
}
