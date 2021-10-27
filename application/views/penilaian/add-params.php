<div class="card">
	<div class="card-header">
		<h4 class="card-title">Form Tambah Parameter</h4>
	</div>
	<div class="card-body">
		<form id="formParameter">
			<div class="form-group">
				<label>Untuk Jabatan</label>
				<select name="id_jabatan" class="form-control" required>
					<?php foreach ($jabatan as $j) { ?>
						<option value="<?= $j->id_jabatan ?>"><?= $j->nama_jabatan ?></option>
					<?php } ?>
				</select>
			</div>
			<div class="form-group">
				<label>Untuk Bidang</label>
				<select name="id_bidang" class="form-control" required>
					<?php foreach ($bidang as $b) { ?>
						<option value="<?= $b->id_bidang ?>"><?= $b->nama_bidang ?></option>
					<?php } ?>
				</select>
			</div>
			<div class="table-responsive">
				<label>Daftar Point Penilaian</label>
				<table class="table table-sm table-bordered" id="table-point">
					<thead>
						<tr>
							<th class="align-middle">Isi Point</th>
							<th class="align-middle">Bobot</th>
							<th class="align-middle">
								Subpoint
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="<?= rand() ?>" data-index="0">
							<td class="align-top">
								<textarea name="isi_point" class="form-control" id="point__0" placeholder="tulis point utama penilaian" required></textarea>
							</td>
							<td class="align-top">
								<input type="text" name="bobot" id="bobot__0" placeholder="Jika bobot 40% maka tulis 0.4" class="form-control" required>
							</td>
							<td class="align-top" id="subpoint-0">
								<a href="javascript:void(0)" onclick="addSubPoint('0')" class="btn btn-default btn-sm mb-1">
									<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
									Tambah
								</a>
							</td>

						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td class="align-top" colspan="3">
								<a href="javascript:void(0)" onclick="addPoint()" class="btn btn-sm btn-primary">
									<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
									Tambah Inputan
								</a>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="form-group">
				<input type="submit" value="Simpan Data" class="btn btn-sm btn-success">
			</div>
		</form>
		<a href="#" onclick="getInput()" class="btn btn-sm btn-primary">Get Input</a>
	</div>
</div>
<script>

	function getInput() {
		var formElements = new Array();
		// $("#point-0 :input").each(function(){
		// 	formElements.push($(this).val())
		// });
		// console.log(formElements);

		var formElement = new Array();
		$("#table-point tbody tr").each(function(i) {
			var ii = $(this).data("index");
			var d = {};
			var id = $(this).prop("id");
			var elem = new Array();
			var subpoint = new Array();
			var bobot = new Array();
			$(this).find("input").each(function(d) {
				if ($(this).prop("id") == ("subpoint__" + ii)) {
					subpoint.push($(this).val());
				}
				if ($(this).prop("id") == ("bobot__" + ii)) {
					bobot.push($(this).val());
				}
			});
			d[id] = {
				"subpoint": subpoint,
				"bobot": bobot,
				"isi": $(this).find("textarea").val()
			};
			formElement.push(d);
		});

		console.log(formElement);

		return {
			"bidang": $('[name="id_bidang"]').val(),
			"id_jabatan": $('[name="id_jabatan"]').val(),
			"point": formElement
		};
	}


	$("#formParameter").submit(function(e) {
		e.preventDefault();
		var unindexed_array = getInput();
		var urlParam = "<?= site_url('penilaian/action-add') ?>";
		$.post(urlParam, JSON.stringify(unindexed_array), function(res) {
				console.log(res);
			}, 'json')
			.fail(function(j, e, t) {

			});
	})

	var ind = 0;
	var subInd = 0;

	function addSubPoint(indx) {
		var appdSubpoint = `
		<div class="input-group mb-2" id="subpoint--${indx}">
			<input type="text" name="subpoint" class="form-control" id="subpoint__${indx}"  placeholder="Tulis subpoint"  required>
			<div class="input-group-append">
				<a class="btn btn-danger btn-sm text-white pt-2" onclick="removeAppend('${indx}')"><i class="fa fa-times"></i></a>
			</div>
		</div>
		`;
		$("#subpoint-" + indx).append(appdSubpoint);
	}

	function removeAppend(id) {
		$("#subpoint--" + id).remove();
	}

	function addPoint() {
		ind++;
		var appd = `<tr id="${Math.floor((Math.random()*1000000000)+1)}" data-index="${ind}">
							<td class="align-top">
								<textarea name="isi_point" class="form-control" id="point__${ind}" placeholder="tulis point utama penilaian" required></textarea>
							</td>
							<td class="align-top">
								<input type="text" name="bobot" id="bobot__${ind}" placeholder="Jika bobot 40% maka tulis 0.4" class="form-control" required>
							</td>
							<td class="align-top" id="subpoint-${ind}">
							<a href="javascript:void(0)" onclick="addSubPoint('${ind}')" class="btn btn-default btn-sm mb-1">
									<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;
									Tambah
								</a>	
							</td>
						</tr>`;
		$("#table-point tbody").append(appd);
	}

	(function($, undefined) {
		'$:nomunge'; // Used by YUI compressor.

		$.fn.serializeObject = function() {
			var obj = {};

			$.each(this.serializeArray(), function(i, o) {
				var n = o.name,
					v = o.value;

				obj[n] = obj[n] === undefined ? v :
					$.isArray(obj[n]) ? obj[n].concat(v) : [obj[n], v];
			});

			return obj;
		};

	})(jQuery);
</script>
