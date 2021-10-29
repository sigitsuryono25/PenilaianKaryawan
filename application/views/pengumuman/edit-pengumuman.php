<div class="card">
	<div class="card-header">
		<h4 class="card-title">Form Edit Pengumuman</h4>
	</div>
	<div class="card-body">

		<form id="formPengumuman" class="dropzone">
			<div class="form-group">
				<input type="hidden" name="id" value="<?= $pengumuman->id ?>">
			</div>
			<div class="form-group">
				<label>Judul Pengumuman</label>
				<input type="text" name="judul" value="<?= $pengumuman->judul ?>" class="form-control">
			</div>
			<div class="form-group">
				<label>Keterangan</label>
				<textarea name="keterangan" class="form-control"><?= $pengumuman->keterangan ?></textarea>
			</div>
			<?php if (!empty($pengumuman)) { ?>
				<div class="form-group">
					<fieldset class="border p-3">
						<legend class="h6">Daftar File</legend>
						<ol list-type="1">
							<?php $file = json_decode($pengumuman->file);
							foreach ($file as $f) { ?>
								<li>
									<a target="_blank" href="<?= base_url('assets/public/pengumuman/' . $f) ?>"><?= $f ?></a>
								</li>
							<?php } ?>
						</ol>
					</fieldset>
				</div>
				<div class="form-group">
					<fieldset class="border p-3">
						<legend class="h6">Hapus Foto Sebelumnya ?</legend>
						<div class="form-check-inline">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" name="conf-del" value="Y">Ya
							</label>
						</div>
						<div class="form-check-inline">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" name="conf-del" value="N" checked>Tidak
							</label>
						</div>
					</fieldset>
				</div>
			<?php } ?>
			<div class="form-group ">
				<label>File Pengumuman<span class="small text-danger font-weight-bold">(maksimal 10 foto)</span></label>
				<div class="dz-message p-5 d-flex flex-column text-center">
					Tarik dan lepaskan file anda Disini atau klik bagian ini
				</div>
				<div class="fallback">
					<input type="file" multiple name="file">
				</div>
			</div>

		</form>
		<div class="form-group">
			<button type="submit" class="btn btn-sm btn-success" id="submit">
				<i class="fas fa-save"></i>&nbsp;&nbsp;
				Simpan Data
			</button>
		</div>
	</div>
</div>
<script>
	var url = "<?= site_url('pengumuman/action-update') ?>";

	Dropzone.options.formPengumuman = {
		url: url,
		paramName: 'file',
		clickable: true,
		autoProcessQueue: false,
		maxFilesize: 10,
		uploadMultiple: true,
		maxFiles: 10,
		parallelUploads: 10,
		addRemoveLinks: true,
		acceptedFiles: '.png,.jpg,.pdf',
		dictDefaultMessage: 'Upload your files here',
		init: function() {
			var dp = this;
			$("#submit").click(function(e) {
				e.preventDefault();
				dp.processQueue();
			});

			this.on('sending', function(file, xhr, formData) {
				var data = $("#formPengumuman").serializeArray();
				$.each(data, function(key, el) {
					formData.append(el.name, el.value);
				});
			});

			this.on('success', function(file, response) {

			});

			this.on('error', function(file, responses, xhr) {
				if (xhr) {
					var response = JSON.parse(xhr.responseText);
					$(file.previewElement).addClass("dz-error").find('.dz-error-message').text(response.message);
				}
			});
			this.on("complete", function(file) {
				if (this.getUploadingFiles().length === 0 && this.getQueuedFiles().length === 0) {
					setTimeout(() => {
						alert('Berkas berhasil diunggah');
						location.assign('<?= site_url('pengumuman/list') ?>');
					}, 1000);
				}

			});
		},
	};
</script>
