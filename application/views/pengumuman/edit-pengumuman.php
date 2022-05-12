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
				<input type="text" name="judul" value="<?= $pengumuman->judul ?>" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="">Cover Pengumuman</label>
				<input type="file" id="cover" accept="image/*" onchange="Main();" class="form-control-file">
				<input type="hidden" name="cover">
			</div>
			<div class="form-group">
				<label>Preview Cover</label><br>
				<img src="<?= base_url('./assets/cover/' . $pengumuman->cover) ?>" class="img-fluid " width="100" />
			</div>
			<div class="form-group">
				<label>Keterangan</label>
				<textarea name="keterangan" class="form-control summernote" required><?= $pengumuman->judul ?></textarea>
			</div>
			<?php if (!empty($pengumuman)) { ?>
				<?php $file = json_decode($pengumuman->file);
				if (!empty($file)) { ?>
					<div class="form-group">
						<fieldset class="border p-3">
							<legend class="h6">Daftar File</legend>
							<ol list-type="1">
								<?php foreach ($file as $f) { ?>
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
			<?php } ?>
			<div class="form-group ">
				<label>File Pengumuman<span class="small text-danger font-weight-bold"></span></label>
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
	var url = "<?= site_url('pengumuman/action-update?id='. $this->input->get('id')) ?>";
	Dropzone.autoDiscover = false;
	var myDropzone = new Dropzone("form#formPengumuman", {
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
				var res = JSON.parse(response);
				if (res.code == 200) {
					alert(res.message);
					// location.assign('<//?= site_url('pengumuman/list') ?>');
				} else {
					console.error(res.message);
				}
			});

			this.on('error', function(file, responses, xhr) {
				if (xhr) {
					var response = JSON.parse(xhr.responseText);
					$(file.previewElement).addClass("dz-error").find('.dz-error-message').text(response.message);
				}
			});
			this.on("complete", function(file, response) {
				console.log(file);
				console.log(response);
			});
		},
	});

	$("#submit").click(function() {
		if ($('[name="judul"]').val() == "") {
			alert('Masukkan judul pengumuman terlebih dahulu');
			return;
		}

		if ($('.summernote').summernote('code') == "<p><br></p>") {
			alert('Masukkan keterangan pengumuman terlebih dahulu');
			return;
		}
		if (myDropzone.files.length == 0) {
			processWithoutFiles();
		}
	});


	function processWithoutFiles() {
		var data = $("#formPengumuman").serialize();
		$.post(url, data, function(res) {
			if (res.code == 200) {
				alert(res.message);
				//location.assign('<?= site_url('pengumuman/list') ?>');
			} else {
				alert(res.message);
			}
		}, 'json').fail(function(j, t, e) {

		});
	}

	const toBase64 = file => new Promise((resolve, reject) => {
		const reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = () => resolve(reader.result);
		reader.onerror = error => reject(error);
	});
	async function Main() {
		const file = document.querySelector('#cover').files[0];
		$('[name="cover"]').val(await toBase64(file));
	}
</script>
