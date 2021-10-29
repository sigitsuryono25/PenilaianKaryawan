<div class="card">
	<div class="card-header">
		<h4 class="card-title">Daftar Params</h4>
	</div>
	<div class="card-body">
		<form id="daftarParamsForm">
			<div class="form-group">
				<label>Untuk Jabatan</label>
				<select name="id_jabatan" class="form-control" required>
					<?php foreach ($jabatan as $j) { ?>
						<option value="<?= $j->id_jabatan ?>"><?= $j->nama_jabatan ?></option>
					<?php } ?>
				</select>
			</div>
		</form>
		<div class="table-responsive">
			<table class="table table-sm table-bordered">
				<thead>
					<tr class="bg-success text-white">
						<th rowspan="2" class="text-center align-middle font-weight-bold font-weight-bold">#</th>
						<th rowspan="2" class="text-center align-middle font-weight-bold">Bidang</th>
						<th colspan="2" class="text-center align-middle font-weight-bold">Point</th>
						<th rowspan="2" class="text-center align-middle font-weight-bold">Action</th>
					</tr>
					<tr class="bg-success text-white">
						<th class="text-center align-middle font-weight-bold">Isi</th>
						<th class="text-center align-middle font-weight-bold">Bobot</th>
					</tr>
				</thead>
				<tbody>
					<?php
					$no = 1;
					foreach ($bidang as $key => $bd) {
						$startCode = 97;
						$point = $this->db->query("SELECT * FROM tb_point WHERE id_bidang IN ('$bd->id_bidang') AND id_jabatan IN ('2') ORDER BY urutan ASC");
					?>
						<tr>
							<td rowspan="<?= $point->num_rows() + 1 ?>"><?= $no ?></td>
							<td rowspan="<?= $point->num_rows() + 1 ?>"><?= $bd->nama_bidang ?></td>
						</tr>
						<?php foreach ($point->result() as $kk => $p) {
							$subPoint = $this->db->query("SELECT * FROM tb_sub_point WHERE id_point IN ('$p->id_point')")->result();
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
								<td><?= $p->bobot ?></td>
								<?php if ($kk == 0) { ?>
									<td rowspan="<?= $point->num_rows() + 1 ?>">
										<a href="javascript:void(0)" class="btn btn-block btn-warning btn-sm">
											<i class="fa fa-pencil-alt" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
											Edit
										</a>
										<a href="javascript:void(0)" class="btn btn-block btn-danger btn-sm">
											<i class="fa fa-times" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
											Hapus
										</a>
									</td>
								<?php } ?>
							</tr>
						<?php $startCode++;
						} ?>
					<?php $no++;
					} ?>
				</tbody>
			</table>
		</div>
	</div>

</div>
