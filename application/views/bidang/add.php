<div class="card">
	<div class="card-header">
		<h4>Form Tambah Bidang Penilaian</h4>
	</div>
	<div class="card-body">
		<form id="formTambahBidang">
			<div class="form-group">
				<label >Nama Bidang</label>
				<input type="text" name="nama_bidang" class="form-control" required>
			</div>
			<div class="form-group">
				<label >Bobot Bidang</label>
				<input type="text" name="bobot" class="form-control" required>
				<small class="text-danger font-weight-bold">Masukkan angka desimal. Gunakan titik. Contoh 0.40</small>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-sm btn-primary">
					<i class="fas fa-save "></i>&nbsp;&nbsp;&nbsp;
					Simpan
				</button>
			</div>
		</form>
	</div>
</div>

<div class="card">
	<div class="card-header">
		<h4>Daftar Bidang Penilaian</h4>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-sm table-bordered" id="tbBidang">
				<thead>
					<tr>
						<th>#</th>
						<th>Nama Bidang</th>
						<th>Bobot Bidang</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>
<?php $this->load->view('bidang/js');?>
