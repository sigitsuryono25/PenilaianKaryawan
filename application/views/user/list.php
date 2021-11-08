<div class="card">
	<div class="card-header">
		<div class="row">
			<div class="col-md-6 col-12">
				<h4 class="card-title">Daftar Karyawan</h4>
			</div>
			<div class="col-md-6 col-12 text-center text-md-right">
				<a href="<?= site_url('user/add-user') ?>" class="btn btn-sm btn-primary">
					<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
					Tambah User
				</a>
			</div>
		</div>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-sm table-bordered table-striped zero_config">
				<thead>
					<tr class="bg-success text-white ">
						<th class="font-weight-bold">#</th>
						<th class="font-weight-bold">User ID</th>
						<th class="font-weight-bold">Nama</th>
						<th class="font-weight-bold">Jabatan</th>
						<th class="font-weight-bold">Aksi</th>
					</tr>
				</thead>
				<tbody>
					<?php foreach ($user as $key => $s) { ?>
						<tr>
							<td class="align-middle"><?= $key + 1 ?></td>
							<td class="align-middle"><?= $s->userid ?></td>
							<td class="align-middle"><?= $s->nama ?></td>
							<td class="align-middle"><?= $s->nama_jabatan ?></td>
							<td class="align-middle">
								<a href="<?= site_url('user/form-edit?userid=' . $s->userid) ?>" class="btn btn-sm btn-warning btn-block">
									<i class="fas fa-pencil-alt "></i>
									Edit
								</a>
								<a href="<?= site_url('user/action-delete?userid=' . $s->userid) ?>" onclick="return confirm('Hapus data user ini?')" class="btn btn-sm btn-danger btn-block">
									<i class="fa fa-trash" aria-hidden="true"></i>
									Hapus
								</a>
							</td>
						</tr>
					<?php } ?>
				</tbody>
			</table>
		</div>
	</div>
</div>
