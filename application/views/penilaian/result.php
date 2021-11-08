<div class="card">
	<div class="card-header">
		<h4 class="card-title">Pilih Jabatan dan Karyawan</h4>
	</div>
	<div class="card-body">
		<form id="pilihKaryawan" action="<?= site_url('penilaian/result') ?>" method="GET">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Periode Bulan</label>
						<select name="bulan" class="form-control" required>
							<option value="01" <?= ("01" == $bulan) ? "selected" : "" ?>>Januari</option>
							<option value="02" <?= ("02" == $bulan) ? "selected" : "" ?>>Februari</option>
							<option value="03" <?= ("03" == $bulan) ? "selected" : "" ?>>Maret</option>
							<option value="04" <?= ("04" == $bulan) ? "selected" : "" ?>>April</option>
							<option value="05" <?= ("05" == $bulan) ? "selected" : "" ?>>Mei</option>
							<option value="06" <?= ("06" == $bulan) ? "selected" : "" ?>>Juni</option>
							<option value="07" <?= ("07" == $bulan) ? "selected" : "" ?>>Juli</option>
							<option value="08" <?= ("08" == $bulan) ? "selected" : "" ?>>Agustus</option>
							<option value="09" <?= ("09" == $bulan) ? "selected" : "" ?>>September</option>
							<option value="10" <?= ("10" == $bulan) ? "selected" : "" ?>>Oktober</option>
							<option value="11" <?= ("11" == $bulan) ? "selected" : "" ?>>November</option>
							<option value="12" <?= ("12" == $bulan) ? "selected" : "" ?>>Desember</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Periode Tahun</label>
						<select name="tahun" class="form-control" required>
							<?php
							for ($i = date('Y'); $i >= 2018; $i--) { ?>
								<option value="<?= $i ?>" <?= ($i == $tahun) ? "selected" : "" ?>><?= $i ?></option>
							<?php }
							?>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>Jabatan</label>
				<select name="id_jabatan" class="form-control" required>
					<option value="">--Pilih Satu--</option>
					<?php foreach ($jabatan as $j) {
						if ($j->id_jabatan == $id_jabatan) {
							$jbtn = $j->nama_jabatan;
						} ?>
						<option value="<?= $j->id_jabatan ?>" <?= ($j->id_jabatan == $id_jabatan) ? "selected" : "" ?>><?= $j->nama_jabatan ?></option>
					<?php } ?>
				</select>
			</div>
			<div class="form-group">
				<label>Karyawan</label>
				<select name="karyawan" class="form-control" required>
					<?php if (!empty($daftar_kar)) {
						foreach ($daftar_kar as $d) {
							if ($d->userid == $karyawan) {
								$nama = $d->nama;
							}
					?>
							<option value="<?= $d->userid ?>" <?= ($d->userid == $karyawan) ? "selected" : "" ?>><?= $d->nama ?></option>
						<?php } ?>
					<? } else { ?>
					<?php } ?>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-sm btn-primary">
					<i class="fa fa-search" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
					Tampilkan Data
				</button>
			</div>
		</form>
	</div>
</div>
<script>
	$('[name="id_jabatan"]').on('change', function() {
		$.get("<?= site_url('user/get-by-jabatan') ?>", {
				jabatan: $(this).val()
			}, function(res) {
				if (res.code == 200) {
					var d = res.data;
					$('[name="karyawan"]').html("");
					for (var i = 0; i < d.length; i++) {
						var appd = `<option value="${d[i].userid}">${d[i].nama}</option>`;
						$('[name="karyawan"]').append(appd);
					}
				}
			}, 'json')
			.fail(function(j, t, e) {
				console.error(JSON.stringify(j));
				$('[name="karyawan"]').html("");
			})
	});
</script>
<?php if (empty($bidang)) {
	return;
} ?>
<div class="card">
	<div class="card-header">
		<h4 class="card-title">Hasil Penilaian</h4>
	</div>
	<div class="card-body">
		<a class="btn btn-success btn-sm text-white" onclick="fnExcelReport()">
			<i class="fas fa-file-excel "></i>&nbsp;&nbsp;&nbsp;
			Export to Excel
		</a>
		<div class="table-responsive">
			<table class="table table-sm table-bordered" id="report">
				<thead>
					<tr>
						<td colspan="9" class="border-0">
							<h5 class="text-center  font-weight-bold">
								FORM PENGAWASAN DAN PENILAIAN PEGAWAI<br>
								KSPPS BMT BIMA
							</h5>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="border-0 font-weight-bold">
							NAMA: <span class="text-uppercase"><?= $nama ?></span>
						</td>
						<td colspan="9" class="border-0 text-right  font-weight-bold">
							Bulan/Pelaksanaan <?= $periode ?>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="border-0 font-weight-bold">
							JABATAN: <span class="text-uppercase"><?= $jbtn ?></span>
						</td>
					</tr>
					<tr class="bg-primary text-white font-weight-bold">
						<th class="font-weight-bold">No</th>
						<th class="font-weight-bold">BIDANG/BAGIAN YANG DINILAI</th>
						<th class="font-weight-bold">POINT</th>
						<th class="font-weight-bold">Score</th>
						<th class="font-weight-bold">Bobot</th>
						<th class="font-weight-bold">Rincian</th>
						<th class="font-weight-bold">Bobot</th>
						<th class="font-weight-bold">Nilai</th>
						<th class="font-weight-bold">Keterangan</th>
					</tr>
				</thead>
				<tbody>
					<?php
					$penilaian = $this->db->query("SELECT * FROM `tb_penilaian` WHERE id_karyawan IN ('$karyawan') AND MONTH(dinilai_pada) IN ('$bulan') AND YEAR(dinilai_pada) IN ('$tahun')")->row();
					if (empty($penilaian)) {
						echo ("<tr><td colspan='9' class='text-center'>Tidak ada data</td></tr>");
						return;
					}
					$no = 1;
					$index = 0;
					$totalAkhir = 0;
					foreach ($bidang as $key => $bd) {
						$startCode = 97;
						$point = $this->db->query("SELECT * FROM tb_point WHERE id_bidang IN ('$bd->id_bidang') AND id_jabatan IN ('$id_jabatan') ORDER BY urutan ASC");
						$penilaian = $this->db->query("SELECT * FROM `tb_penilaian` WHERE id_karyawan IN ('$karyawan') AND MONTH(dinilai_pada) IN ('$bulan') AND YEAR(dinilai_pada) IN ('$tahun')")->row()->data_penilaian;
						$arrayPenilaian = json_decode($penilaian, true);
						$penilaian = $arrayPenilaian[$index];
						$bobot = $bd->bobot;
						$total = 0;

					?>
						<tr>
							<td rowspan="<?= $point->num_rows() + 1 ?>"><?= $no ?></td>
							<td rowspan="<?= $point->num_rows() + 1 ?>"><?= $bd->nama_bidang ?></td>
						</tr>
						<?php foreach ($point->result() as $kk => $p) {
							$subPoint = $this->db->query("SELECT * FROM tb_sub_point WHERE id_point IN ('$p->id_point')")->result();
							$givenScore = $penilaian['pairNilaiXidPoint'][$kk]['giveScore'];
							$nilai = $penilaian['pairNilaiXidPoint'][$kk]['nilai'];
							$total += $nilai;
						?>
							<tr>
								<td>
									<?= chr($startCode) . ". " . $p->isi_point ?>
									<ul style="list-style: none;">
										<?php foreach ($subPoint as $key => $sbp) { ?>
											<li><?= chr($startCode) . "." . ($key + 1) . ". " . $sbp->isi_sub_point ?></li>
										<?php } ?>
									</ul>
								</td>
								<td>
									<?= $givenScore ?>
								</td>
								<td><?= ($p->bobot * 100) ?>%</td>
								<td>
									<?= $nilai ?>
								</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						<?php $startCode++;
						} ?>

						<tr class="bg-success font-weight-bold text-white">
							<td></td>
							<td>Jumlah Nilai</td>
							<td></td>
							<td></td>
							<td></td>
							<td><?= $total ?></td>
							<td><?= ($bobot * 100) ?> %</td>
							<td><?php $totalAkhir += $bobot * $total;
								echo $bobot * $total; ?></td>
							<td></td>
						</tr>
					<?php $no++;
						$index++;
					} ?>
				</tbody>
				<tfoot>
					<tr class="bg-success font-weight-bold text-white">
						<td colspan="2">Total Nilai Yang Diperoleh</td>
						<td colspan="6" class="text-right"><?php echo $totalAkhir; ?></td>
						<td></td>
					</tr>
					<!-- <tr>
						<td colspan="2" class="pt-4"></td>
						<td colspan="8" class="pt-4"></td>
					</tr> -->
				</tfoot>
			</table>
		</div>
	</div>
</div>
<iframe id="txtArea1" style="display:none"></iframe>
<script>
	function exportReportToExcel() {
		let table = document.getElementsByTagName("table"); // you can use document.getElementById('tableId') as well by providing id to the table tag
		TableToExcel.convert(table[0], { // html code may contain multiple tables so here we are refering to 1st table tag
			name: `export.xlsx`, // fileName you could use any name
			sheet: {
				name: 'Sheet 1' // sheetName
			}
		});
	}

	function fnExcelReport() {
		var name = "<?= $nama ?>";
		var fileName = `FORM PENGAWASAN DAN PENILAIAN PEGAWAI KSPPS BMT BIMA__${name}`;

		var tab_text = "<table border='2px'><tr >";
		var textRange;
		var j = 0;
		tab = document.getElementById('report'); // id of table

		for (j = 0; j < tab.rows.length; j++) {
			tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
			//tab_text=tab_text+"</tr>";
		}

		tab_text = tab_text + "</table>";
		tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, ""); //remove if u want links in your table
		tab_text = tab_text.replace(/<img[^>]*>/gi, ""); // remove if u want images in your table
		tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

		var ua = window.navigator.userAgent;
		var msie = ua.indexOf("MSIE");

		if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) // If Internet Explorer
		{
			txtArea1.document.open("txt/html", "replace");
			txtArea1.document.write(tab_text);
			txtArea1.document.close();
			txtArea1.focus();
			sa = txtArea1.document.execCommand("SaveAs", true, fileName);
		} else { //other browser not tested on IE 11
			sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));
			sa.document.title = fileName;
		}
		return (sa);
	}
</script>
