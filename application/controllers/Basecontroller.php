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

class Basecontroller extends CI_Controller
{

	function __construct()
	{
		parent::__construct();

		//check if user logged in or not
		if (!$this->session->has_userdata('username')) {
			redirect(site_url());
		}
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

	public function base64ToFile($stringBase64, $path)
	{
		if (!is_dir($path)) {
			mkdir($path, 0775, TRUE);
		}

		$image = base64_decode(preg_replace('#^data:image/\w+;base64,#i', '', $stringBase64));
		$name = md5(time()) . ".jpg";
		if (file_put_contents($path . $name, $image) !== FALSE) {
			return $name;
		}
	}

	public function pushNotification($message)
	{
		$url = "https://firebasepush.server4111.com/push.php";
		$ch = curl_init($url);
		$payload = json_encode($message);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
		curl_setopt($ch, CURLOPT_HTTPHEADER, array("Content-Type:application/json"));
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

		$result = curl_exec($ch);
		curl_close($ch);

		return $result;
	}


	function contentType($type)
	{
		header("Content-Type: $type");
	}
}
