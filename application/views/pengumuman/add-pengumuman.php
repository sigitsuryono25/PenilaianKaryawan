<div class="card">
	<div class="card-header">
		<h4 class="card-title">
			Form Tambah Pengumuman
		</h4>
	</div>
	<div class="card-body">

		<form id="formPengumuman" class="dropzone">
			<div class="form-group">
				<label>Judul Pengumuman</label>
				<input type="text" name="judul" class="form-control">
			</div>
			<div class="form-group">
				<label>Keterangan</label>
				<textarea name="keterangan" class="form-control"></textarea>
			</div>
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
	var url = "<?= site_url('pengumuman/action-add')?>";

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
					location.assign('<?= site_url('page/galeri') ?>');
				}
			});
		},
	};
</script>
