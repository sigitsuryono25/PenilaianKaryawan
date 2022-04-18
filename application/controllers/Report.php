<?php


defined('BASEPATH') or exit('No direct script access allowed');

class Report extends CI_Controller
{
    private $baseURL = "http://202.43.164.234:12310/kirim/keuangan/get";

    public function index()
    {
        // $input = file_get_contents("php://input");
        // $json = json_decode($input, true);
        // $dd = $this->input->post('q');
        $dd = '{
            "request": "lapkeu2",
            "userid": "system",
            "signature": "d3nm@sDevtern",
            "data01": {
                "tgl": "20210201",
                "golac": "nrc",
                "kdloc": "01"
            }
        }
        ';

        // die($dd);


        $curl = curl_init();

        curl_setopt_array($curl, array(
            CURLOPT_URL => $this->baseURL,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 0,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'POST',
            CURLOPT_POSTFIELDS => $dd,
            CURLOPT_HTTPHEADER => array(
                'Device-Terminal: d3nMas'
            ),
        ));

        $response = curl_exec($curl);

        curl_close($curl);
        $data['kiri'] = json_decode($response)->kiri;
        $data['kanan'] = json_decode($response)->kanan;
        $data['jumlah_kiri'] = json_decode($response)->jumlah->tot_kiri;
        $data['jumlah_kanan'] = json_decode($response)->jumlah->tot_kanan;
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $this->load->view('report/report-neraca-2', $data);
    }

    public function npf()
    {
        // $input = file_get_contents("php://input");
        // $json = json_decode($input, true);
        $dd = $this->input->post('q');
        // $dd = '{
        //     "request": "npf",
        //     "userid": "system",
        //     "signature": "d3nm@sDevtern",
        //     "data01": {
        //         "tgl": "20210201",
        //         "kode": "ao"
        //     }
        // }
        
        // ';


        $curl = curl_init();

        curl_setopt_array($curl, array(
            CURLOPT_URL => $this->baseURL,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 0,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'POST',
            CURLOPT_POSTFIELDS => $dd,
            CURLOPT_HTTPHEADER => array(
                'Device-Terminal: d3nMas'
            ),
        ));

        $response = curl_exec($curl);

        curl_close($curl);
        $data['response'] = json_decode($response)->data01;
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $this->load->view('report/report-npf', $data);
    }

    public function rugiLaba()
    {
        // $input = file_get_contents("php://input");
        // $json = json_decode($input, true);
        // $dd = $this->input->post('q');
        // $ddDecode = json_decode($dd, true);
        // $ddDecode['request'] = 'lapkeu2';
        $dd = '{
            "request": "lapkeu2",
            "userid": "system",
            "signature": "d3nm@sDevtern",
            "data01": {
                "tgl": "20220401",
                "golac": "lr",
                "kdloc": "01"
            }
        }
        ';

        // $dd = json_encode($ddDecode);


        $curl = curl_init();

        curl_setopt_array($curl, array(
            CURLOPT_URL => $this->baseURL,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 0,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'POST',
            CURLOPT_POSTFIELDS => $dd,
            CURLOPT_HTTPHEADER => array(
                'Device-Terminal: d3nMas'
            ),
        ));

        $response = curl_exec($curl);

        $data['kiri'] = json_decode($response)->kiri;
        $data['kanan'] = json_decode($response)->kanan;
        $data['jumlah_kiri'] = json_decode($response)->jumlah->tot_kiri;
        $data['jumlah_kanan'] = json_decode($response)->jumlah->tot_kanan;
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $this->load->view('report/report-rugi-laba-2', $data);
    }
}

/* End of file Report.php */
