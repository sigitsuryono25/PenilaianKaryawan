<?php


defined('BASEPATH') or exit('No direct script access allowed');

class Report extends CI_Controller
{
    private $baseURL = "http://202.43.164.234:12310/kirim/keuangan/get";

    public function getReport()
    {
        $type = $this->input->post('type');
        $dd = $this->input->post('q');
        $namaKantor = $this->input->post('kantor');

        switch ($type) {
            case "neraca":
                $this->neraca($dd, $namaKantor);
                break;
            case "rugi-laba":
                $this->rugiLaba($dd, $namaKantor);
                break;
            case "npf":
                $this->npf($dd, $namaKantor);
                break;
            case "noa":
                $this->noa($dd, $namaKantor);
                break;
            case "angsuran":
                $this->angsuran($dd, $namaKantor);
                break;
            case "saldo-tabungan":
                $this->rataRataSaldoTabunganDeposito($dd, $namaKantor);
                break;
            case "car":
                $this->car($dd, $namaKantor);
                break;
            case "fdr":
                $this->fdr($dd, $namaKantor);
                break;
            case "roa":
                $this->roa($dd, $namaKantor);
                break;
            case "roe":
                $this->roe($dd, $namaKantor);
                break;
            case "rentabilitas":
                $this->rentabilitas($dd, $namaKantor);
                break;
        }
    }

    private function neraca($dd, $namaKantor)
    {
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
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-neraca-2', $data);
    }

    private function npf($dd, $namaKantor)
    {
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
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-npf', $data);
    }

    private function rugiLaba($dd, $namaKantor)
    {
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
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-rugi-laba-2', $data);
    }

    private function noa($dd, $namaKantor)
    {
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
        $data['data'] = json_decode($response)->data01;
        $params = json_decode($dd);
        $date = $params->data01->tgl1;
        $date2 = $params->data01->tgl2;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['tanggal2'] = $this->etc->indonesiaDate(substr($date2, 0, 4) . "-" . substr($date2, 4, 2) . "-" . substr($date2, 6, 2));
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-noa', $data);
    }

    private function angsuran($dd, $namaKantor)
    {
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
        $data['data'] = json_decode($response)->data01;
        $params = json_decode($dd);
        $date = $params->data01->tgl1;
        $date2 = $params->data01->tgl2;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['tanggal2'] = $this->etc->indonesiaDate(substr($date2, 0, 4) . "-" . substr($date2, 4, 2) . "-" . substr($date2, 6, 2));
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-angsuran', $data);
    }

    private function rataRataSaldoTabunganDeposito($dd, $namaKantor)
    {
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
        $data['data'] = json_decode($response)->data01->data01;
        $params = json_decode($dd);
        $date = $params->data01->tgl1;
        $date2 = $params->data01->tgl2;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['tanggal2'] = $this->etc->indonesiaDate(substr($date2, 0, 4) . "-" . substr($date2, 4, 2) . "-" . substr($date2, 6, 2));
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-rata2-tabungan-deposito', $data);
    }

    private function car($dd, $namaKantor)
    {
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
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['data'] = json_decode($response);
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-car', $data);
    }
    private function fdr($dd, $namaKantor)
    {
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
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['data'] = json_decode($response, true);
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-fdr', $data);
    }

    private function roa($dd, $namaKantor)
    {

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
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['data'] = json_decode($response, true);
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-roa', $data);
    }

    private function roe($dd, $namaKantor)
    {
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
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['data'] = json_decode($response, true);
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-roe', $data);
    }
    public function rentabilitas($dd, $namaKantor)
    {
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
        $params = json_decode($dd);
        $date = $params->data01->tgl;
        $data['tanggal'] = $this->etc->indonesiaDate(substr($date, 0, 4) . "-" . substr($date, 4, 2) . "-" . substr($date, 6, 2));
        $data['data'] = json_decode($response, true);
        $data['kantor'] = $namaKantor;
        $this->load->view('report/report-rentabilitas', $data);
    }
}

/* End of file Report.php */