<?php


class Baseapi extends CI_Controller
{
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
		return null;
	}
}
