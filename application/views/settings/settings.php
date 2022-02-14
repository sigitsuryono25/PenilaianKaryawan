<div class="card">
    <div class="card-header">
		<h4>Settings Aplikasi Android</h4>
	</div>
	<div class="card-body">
	    <form class="formSettings" action="<?= site_urL('settings/update')?>" method="POST">
	        <div class="form-group">
	           <label>End Point Halaman Presensi</label>
	           <input type="text" class="form-control" name='end_point_presensi' required value="<?= (!empty($settings)) ? $settings->end_point_presensi : ''?>" />
	        </div>
	        <div class="form-group">
	            <input type="submit" class="btn btn-sm btn-primary" value="Simpan"/>
	        </div>
	    </form>
	</div>
</div>
