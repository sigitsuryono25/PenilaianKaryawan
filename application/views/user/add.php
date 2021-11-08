<div class="card">
	<div class="card-header">
		<h4 class="card-title">Form Tambah User</h4>
	</div>
	<div class="card-body">
		<form id="frmUser">
			<div class="form-group">
				<label for="userid">User ID/NIP</label>
				<input type="text" name="userid" id="userid" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" name="password" id="password" class="form-control">
			</div>
			<div class="form-group">
				<label for="nama">Nama Lengkap</label>
				<input type="text" name="nama" id="nama" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="jabatan">Jabatan</label>
				<select name="jabatan" id="jabatan" class="form-control" required>
					<option value="">.: Silahkan Pilih Satu :.</option>
					<?php foreach ($jabatan as $j) { ?>
						<option value="<?= $j->id_jabatan ?>"><?= $j->nama_jabatan ?></option>
					<?php } ?>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-sm btn-primary">
					<i class="fas fa-save "></i>&nbsp;&nbsp;&nbsp;
					Simpan Data
				</button>
			</div>
		</form>
	</div>
</div>
<script>
	var url = "<?= site_url('user/action-add') ?>";
	$("#frmUser").submit(function(e) {
		e.preventDefault();

		showLoading();

		var data = {};
		$(this).serializeArray().map(function(name, value) {
			data[name.name] = name.value;
		});

		$.post(url, JSON.stringify(data), function(res) {
			hideLoading()
			if (res.code == 200) {
				alert(res.message);
				location.assign('<?= site_url('user/list')?>');
			} else {
				alert(res.message);
			}
		}, 'json').fail(function(j, t, e) {

		});
	});
</script>
