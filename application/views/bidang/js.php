<script>
	var tbBidang = $('#tbBidang').DataTable({
		'ajax': "<?= site_url('bidang/data-bidang-api') ?>",
		'columnDefs': [{
				"targets": 3,
				"orderable": false,
				"data": 'id_bidang',
				"render": function(data, type, row, meta) {
					return `<a class="btn btn-sm btn-warning text-white" onclick="editData('${data}')" id="edit">Edit Data</a>
				<a class="btn btn-sm btn-danger  text-white" onclick="hapusData('${data}')" id="hapus">Hapus Data</a>
				`
				}
			},
			{
				"targets": 2,
				"orderable": true,
				"data": 'bobot',
			},{
				"targets": 1,
				"orderable": true,
				"data": 'nama_bidang',
			},
			{
				"targets": 0,
				"orderable": true,
				"render": function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}

			}

		],

	});
	var urlAction = "<?= site_url('bidang/action-add') ?>";
	$("#formTambahBidang").on("submit", function(e) {
		e.preventDefault();
		var dTambah = JSON.stringify(getFormData($(this)));
		$.post(urlAction, dTambah, function(res) {
			if (res.code == 200) {
				alert(res.message);
				$("#input-id").remove();
				urlAction = "<?= site_url('bidang/action-add') ?>";
				$("#formTambahBidang").trigger("reset");
				tbBidang.ajax.reload();
			} else {
				alert(res.message);
			}
		}, 'json').fail(function(j, e, t) {
			alert(t);
			console.error(JSON.stringify(j));
		});
	})

	function hapusData(idBidang) {
		if (!confirm("Hapus data ini?")) {
			return;
		}

		var urlDeleteBidang = "<?= site_url('bidang/action-delete') ?>";
		var data = {
			'id_bidang': idBidang
		};

		$.post(urlDeleteBidang, JSON.stringify(data), function(res) {
			if (res.code == 200) {
				alert(res.message);
				tbBidang.ajax.reload();
			} else {
				alert(res.message);
			}
		}, 'json').fail(function(j, e, t) {
			alert(t);
			console.error(JSON.stringify(j));
		});
	}

	function editData(idBidang) {
		var isExist = $("#formTambahBidang").find("#input-id").length;
		if (isExist <= 0) {
			var id = `<input type="hidden" name="id" id="input-id"/>`;
			$("#formTambahBidang").append(id);
		}
		urlAction = "<?= site_url('bidang/action-update') ?>"
		var urlDetailBidang = "<?= site_url('bidang/get-detail') ?>";
		var data = {
			'id_bidang': idBidang
		};

		$.post(urlDetailBidang, JSON.stringify(data), function(res) {
			$('[name="nama_bidang"]').val(res.data.nama_bidang);
			$('[name="bobot"]').val(res.data.bobot);
			$('[name="id"]').val(res.data.id_bidang);

		}, 'json').fail(function(j, e, t) {
			alert(t);
			console.error(JSON.stringify(j));
		});
	}

	function getFormData($form) {
		var unindexed_array = $form.serializeArray();
		var indexed_array = {};

		$.map(unindexed_array, function(n, i) {
			indexed_array[n['name']] = n['value'];
		});

		return indexed_array;
	}
</script>
