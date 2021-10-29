<div class="card">
	<div class="card-header">
		<h4 class="card-title">Daftar Pengumuman</h4>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-sm table-bordered" id="zero_config">
				<thead>
					<tr class="bg-success text-white">
						<th class="font-weight-bold">#</th>
						<th class="font-weight-bold">Judul Pengumuman</th>
						<th class="font-weight-bold">Keterangan</th>
						<th class="font-weight-bold">Daftar File</th>
						<th class="font-weight-bold">Action</th>
					</tr>
				</thead>
				<tbody>
					<?php foreach ($pengumuman as $key => $p) { ?>
						<tr>
							<td><?= $key + 1 ?></td>
							<td><?= $p->judul ?></td>
							<td><?= $p->keterangan ?></td>
							<td>
								<ol list-type="1">
									<?php $file = json_decode($p->file);
									foreach ($file as $f) { ?>
										<li>
											<a target="_blank" href="<?= base_url('assets/public/pengumuman/' . $f)?>"><?= $f?></a>
										</li>
									<?php } ?>
								</ol>
							</td>
							<td>
								<a href="<?= site_url('pengumuman/form-edit?id=' . $p->id)?>" class="btn btn-warning btn-sm btn-block">
									<i class="fas fa-pencil-alt"></i>&nbsp;&nbsp;&nbsp;
									Edit
								</a>
								<a href="<?= site_url('pengumuman/action-delete?id=' . $p->id)?>"
								onclick="return confirm('Hapus pengumuman ini?')"
								class="btn btn-danger btn-sm btn-block">
									<i class="fas fa-times"></i>&nbsp;&nbsp;&nbsp;
									Edit
								</a>
							</td>
						</tr>
					<?php } ?>
				</tbody>
			</table>
		</div>
	</div>
</div>
