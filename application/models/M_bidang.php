<?php

class M_bidang extends CI_Model
{
	public function getAllBidang($field = "*", $where = null, $order = null, $group = null, $limit = null, $showCompiled = false)
	{
		$this->db->select($field)
			->from("tb_bidang");

		if (!empty($where)) {
			$this->db->where($where);
		}
		if (!empty($order)) {
			$this->db->order_by($where);
		}
		if (!empty($group)) {
			$this->db->group_by($where);
		}
		if (!empty($limit)) {
			$this->db->limit($limit[1], $limit[0]);
		}

		if (!$showCompiled) {
			return $this->db->get();
		} else {
			return $this->db->get_compiled_select();
		}
	}
}
