<?php

/** 
 * extends ke class ini untuk menggunakan function yang sudah ada disini
 * 
 * extends controller lain (untuk laman web admin bukan API) ke class ini agar hanya melakukan
 * pengecekan user login hanya sekali saja
 * 
 * jangan extends controller untuk login ke class ini. 
 * nanti akan terjadi never ending looping (Too Many Redirect).
*/

class Basecontroller extends CI_Controller{
	
	function __construct()
	{
		parent::__construct();

		//check if user logged in or not
	}

	public function getDate($format = "Y-m-d H:i:s")
	{
		return date($format);
	}

	// put your code here
	public function setHeader($code, $message)
	{
		return header($_SERVER["SERVER_PROTOCOL"] . " $code $message");
	}

	function badRequest($message = "Bad Request")
	{
		$this->setHeader(400, $message);
		die(json_encode([
			'message' => $message,
			"code" => 400
		]));
	}

	function internalError($message = SERVER_ERROR)
	{
		$this->setHeader(500, "Internal Server Error");
		die(json_encode([
			'message' => $message,
			"code" => 500
		]));
	}

	function notFound($message = "Data tidak ditemukan")
	{
		$this->setHeader(404, NOT_FOUND);
		die(json_encode([
			'message' => $message,
			"code" => 404
		]));
	}

	function nothingChange($message = "Nothing Change")
	{
		$this->setHeader(200, "OK");
		die(json_encode([
			'message' => $message,
			"code" => 200
		]));
	}

	function success($message = "Tindakan berhasil dilakukan", $node = "data", $data = [])
	{
		$this->setHeader(200, "OK");
		echo json_encode([
			$node => $data,
			'message' => $message,
			"code" => 200
		]);
	}


	function contentType($type)
	{
		header("Content-Type: $type");
	}
}
