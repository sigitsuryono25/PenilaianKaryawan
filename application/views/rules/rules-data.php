<div class="card">
	<div class="card-header">
		<div class="row">
			<div class="col-md-6 col-12">
				<h4 class="card-title">Form Rules</h4>
			</div>
			<div class="col-md-6 col-12 text-center text-md-right">
				<a href="<?= site_url('rules/form-rules') ?>" class="btn btn-sm btn-primary">
					<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
					Tambah Rules
				</a>
			</div>
		</div>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-sm table-bordered table-striped zero_config">
				<thead>
					<tr class="bg-success text-white font-weight-bold">
						<th>#</th>
						<th>Jabatan</th>
						<th>Rules Penilaian</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<?php
					$no = 1;
					foreach ($rules as $rr) {
						$rulesJabatan = '';
						foreach ($rr['rules_jbtn'] as $rls) {
							$rulesJabatan .= "<li>" . $rls['nama_jabatan'] . "</li>";
						}
					?>
						<tr>
							<td><?= $no ?></td>
							<td><?= $rr['nama_jabatan'] ?></td>
							<td>
								<ol>
									<?= $rulesJabatan ?>
								</ol>
							</td>
							<td>
								<a href="<?= site_url('rules/form-edit?rules_id=' . $rr['id_rules']) ?>" class="btn btn-sm btn-warning">
									<i class="fa fa-trash" aria-hidden="true"></i>
									&nbsp;&nbsp;&nbsp;
									Edit
								</a>
								<a href="<?= site_url('rules/action-delete?rules_id=' . $rr['id_rules']) ?>" onclick="return confirm('Hapus rules penilaian ini?')" class="btn btn-sm btn-danger">
									<i class="fa fa-trash" aria-hidden="true"></i>
									&nbsp;&nbsp;&nbsp;
									Hapus
								</a>
							</td>
						</tr>
					<?php $no++;
					} ?>
				</tbody>
			</table>
		</div>
	</div>
</div>
