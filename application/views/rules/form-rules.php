<div class="card">
	<div class="card-header">
		<h4 class="card-title">Form Rules</h4>
	</div>
	<div class="card-body">
		<form action="<?= site_url('rules/add-rules')?>" method="POST">
			<div class="form-group">
				<label for="">Jabatan</label>
				<select name="jabatan" class="form-control" required>
					<option value="">--Pilih Satu--</option>
					<?php foreach ($jabatan as $j) { ?>
						<option value="<?= $j->id_jabatan ?>"><?= $j->nama_jabatan ?></option>
					<?php } ?>
				</select>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-md-12">
						<h5>Jabatan terpilih diatas, dapat memilih:<br><small>pilih minimal satu</small></h5>
					</div>
					<?php foreach ($jabatan as $j) { ?>
						<div class="col-md-2">
							<div class="form-check">
								<label class="form-check-label">
									<input type="checkbox" class="form-check-input chk-jbtn" id="jbtn-<?= $j->id_jabatan ?>" name="rules[]" value="<?= $j->id_jabatan ?>">
									<?= $j->nama_jabatan ?>
								</label>
							</div>
						</div>
					<?php } ?>
				</div>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-sm btn-primary">
					<i class="fas fa-save"></i>
					&nbsp;&nbsp;&nbsp;
					Simpan Rules
				</button>
			</div>
		</form>
	</div>
</div>
<script>
	$(".chk-jbtn").each(function() {
		$(this).attr('disabled', true);
	});
	$('[name="jabatan"]').val("");
	$('[name="jabatan"]').change(function() {
		var nilai = $(this).val();
		if (nilai == "" || nilai == null) {
			$(".chk-jbtn").each(function() {
				$(this).attr('disabled', true);
			});
			return
		}
		$(".chk-jbtn").each(function() {
			$(this).attr('disabled', false);
		});

		$(`#jbtn-${nilai}`).attr('disabled', true);
	});
</script>
