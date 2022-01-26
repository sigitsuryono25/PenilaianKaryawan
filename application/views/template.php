<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Tell the browser to be responsive to screen width -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- Favicon icon -->
	<link rel="icon" type="image/png" sizes="16x16" href="<?= base_url() ?>/assets/images/favicon.png">
	<title>Admin Panel</title>
	<link rel="stylesheet" type="text/css" href="<?= base_url() ?>/assets/extra-libs/multicheck/multicheck.css?<?= time() ?>">
	<link href="<?= base_url() ?>/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.css?<?= time() ?>" rel="stylesheet">
	<link href="<?= base_url() ?>/dist/css/style.min.css?<?= time() ?>" rel="stylesheet">

	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">

	<!-- JQUERY -->
	<script src="<?= base_url() ?>/assets/libs/jquery/dist/jquery.min.js"></script>
	<script src="<?= base_url() ?>/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script src="<?= base_url() ?>/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="<?= base_url() ?>/assets/extra-libs/DataTables/datatables.min.js"></script>

	<!-- DROP ZONE -->
	<link rel="stylesheet" href="https://cdn.rawgit.com/enyo/dropzone/master/dist/dropzone.css">
	<script src="https://cdn.rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script>

	<script src="https://cdn.jsdelivr.net/gh/linways/table-to-excel@v1.0.4/dist/tableToExcel.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@mdi/font@6.4.95/css/materialdesignicons.min.css">

	<style>
		.dropzone {
			border: 0px dashed #dedede;
			border-radius: 5px;
			padding: 0;
			background: #fff;
		}

		.dropzone .dz-message {
			margin-top: .2em;
			border: 2px dashed #dedede;
			color: rgba(0, 0, 0, .54);
		}
	</style>
</head>

<body>
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
			<h5 style="margin-top: 60px;">Loading...</h5>
		</div>
	</div>
	<div id="main-wrapper">
		<header class="topbar" data-navbarbg="skin5">
			<nav class="navbar top-navbar navbar-expand-md navbar-dark">
				<div class="navbar-header" data-logobg="skin5">
					<!-- This is for the sidebar toggle which is visible on mobile only -->
					<a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>

					<a class="navbar-brand" href="index.html">
						<!-- Logo icon -->
						<span class="logo-text">
							<!-- dark Logo text -->
							<h4 class="text-white">Admin KSPPS BMT BIMA</h4>

						</span>
					</a>
					<a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i class="ti-more"></i></a>
				</div>
				<div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
					<ul class="navbar-nav float-left mr-auto">
						<li class="nav-item d-none d-md-block"><a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)" data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a></li>
					</ul>
					<ul class="navbar-nav float-right">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="<?= base_url() ?>/assets/images/users/1.jpg" alt="user" class="rounded-circle" width="31"></a>
							<div class="dropdown-menu dropdown-menu-right user-dd animated">
								<a class="dropdown-item" href="javascript:void(0)" ><i class="fa fa-user-circle m-r-5 m-l-5" aria-hidden="true"></i>Signed as: <?= $this->session->userdata('nama')?></a>
								<a class="dropdown-item" href="#modalLogout" data-toggle="modal" data-target="#modalLogout"><i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
							</div>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<aside class="left-sidebar" data-sidebarbg="skin5">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav" class="p-t-30">
						<li class="sidebar-item">
							<a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false">
								<i class="mdi mdi-database-outline"></i><span class="hide-menu">Master Data </span>
							</a>
							<ul aria-expanded="false" class="collapse">
								<li class="sidebar-item"><a href="<?= site_url('user/list')?>" class="sidebar-link"><i class="mdi mdi-account-circle-outline"></i><span class="hide-menu"> User</span></a></li>
								<li class="sidebar-item"><a href="<?= site_url('bidang/form-add') ?>" class="sidebar-link"><i class="mdi mdi-magnify"></i><span class="hide-menu"> Bidang Penilaian </span></a></li>
								<li class="sidebar-item"><a href="<?= site_url('penilaian/list') ?>" class="sidebar-link"><i class="mdi mdi-format-list-bulleted"></i><span class="hide-menu"> Parameter Penilaian </span></a></li>
							</ul>
						</li>
						<li class="sidebar-item">
							<a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false">
								<i class="mdi mdi-order-bool-descending"></i><span class="hide-menu">Penilaian </span>
							</a>
							<ul aria-expanded="false" class="collapse">
								<li class="sidebar-item"><a href="<?= site_url('rules/list') ?>" class="sidebar-link"><i class="mdi mdi-calendar-check"></i><span class="hide-menu"> Rules Penilaian </span></a></li>
								<li class="sidebar-item"><a href="<?= site_url('penilaian/result') ?>" class="sidebar-link"><i class="mdi mdi-calendar-check"></i><span class="hide-menu"> Hasil </span></a></li>
							</ul>
						</li>
						<li class="sidebar-item">
							<a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false">
								<i class="mdi mdi-bullhorn-outline"></i><span class="hide-menu">Pengumuman </span>
							</a>
							<ul aria-expanded="false" class="collapse">
								<li class="sidebar-item"><a href="<?= site_url('pengumuman/list') ?>" class="sidebar-link"><i class="mdi mdi-access-point-check"></i><span class="hide-menu"> Daftar Pengumuman </span></a></li>
							</ul>
						</li>
					</ul>
				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-12 d-flex no-block align-items-center">
						<h4 class="page-title"><?= $title ?></h4>
					</div>
				</div>
			</div>
			<div class="container-fluid">
				<?= $page ?>
			</div>
		</div>
	</div>

	<!-- Modal logout -->
	<div class="modal fade" id="modalLogout" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Konfirmasi Logout</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="text-justify">
						Anda akan keluar dari Administrator Panel. Anda harus login kembali jika ingin menggunakan fitur yang telah disediakan.<br><br>
						Klik tombol <span class="font-weight-bold">Logout</span> dibawah ini untuk menlanjutkan proses logout.
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Batal</button>
					<a type="button" class="btn btn-danger text-white" href="<?= site_url('welcome/logout') ?>">Logout</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap tether Core JavaScript -->
	<!-- slimscrollbar scrollbar JavaScript -->
	<script src="<?= base_url() ?>/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
	<script src="<?= base_url() ?>/assets/extra-libs/sparkline/sparkline.js"></script>
	<!--Wave Effects -->
	<script src="<?= base_url() ?>/dist/js/waves.js"></script>
	<!--Menu sidebar -->
	<script src="<?= base_url() ?>/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="<?= base_url() ?>/dist/js/custom.min.js"></script>
	<!-- this page js -->
	<script src="<?= base_url() ?>/assets/extra-libs/multicheck/datatable-checkbox-init.js"></script>
	<script src="<?= base_url() ?>/assets/extra-libs/multicheck/jquery.multicheck.js"></script>

	<!-- SUMMER NOTE -->
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
	<script>
		/****************************************
		 *       Basic Table                   *
		 ****************************************/
		$('#zero_config').DataTable();
		$('.zero_config').DataTable();
		$(".summernote").summernote({
			height: 250,
			placeholder: 'Tulis sesuatu disini'
		});

		function showLoading() {
			$(".preloader").fadeIn();
		}

		function hideLoading() {
			$(".preloader").fadeOut();
		}
	</script>


</body>

</html>
