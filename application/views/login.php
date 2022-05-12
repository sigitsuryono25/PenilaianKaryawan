<!DOCTYPE html>
<html dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<?php echo base_url()?>/assets/images/favicon.png">
    <title>BMT BIMA Login Portal Admin</title>
    <!-- Custom CSS -->
    <link href="<?php echo base_url()?>/dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <div class="main-wrapper">
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Login box.scss -->
        <!-- ============================================================== -->
        <div class="auth-wrapper d-flex no-block justify-content-center align-items-center bg-dark">
            <div class="auth-box bg-dark border-top border-secondary">
                <div id="loginform">
                    <div class="text-center p-t-20 p-b-20">
                        <span class="db h3 text-white font-weight-bold">
							BMT BIMA Login Portal
						</span>
                    </div>
                    <!-- Form -->
                    <form class="form-horizontal m-t-20" id="formLogin">
                        <div class="row p-b-30">
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-white" id="basic-addon1"><i class="ti-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control form-control-lg" name="username" value="<?php echo set_value('username')?>" placeholder="User ID" aria-label="User ID" aria-describedby="basic-addon1" required="">
									<span class="text-danger"><?php echo form_error('username')?></span>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-warning text-white" id="basic-addon2"><i class="ti-pencil"></i></span>
                                    </div>
                                    <input type="password" class="form-control form-control-lg" name="password" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" required="">
									<div class="input-group-append">
										<a href="javascript:void(0)" class="btn btn-danger btn-lg" id="toggle-password">
											<i class="fa fa-eye"></i>
										</a>
									</div>
									<span class="text-danger"><?php echo form_error('password')?></span>
                                </div>
                            </div>
                        </div>
						<div class="error-message"></div>
                        <div class="row border-top border-secondary">
                            <div class="col-12">
                                <div class="form-group">
                                    <div class="p-t-20 text-center">
                                        <button class="btn btn-success" type="submit">Login</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Login box.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper scss in scafholding.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper scss in scafholding.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Right Sidebar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Right Sidebar -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- All Required js -->
    <!-- ============================================================== -->
    <script src="<?php echo base_url()?>/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="<?php echo base_url()?>/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="<?php echo base_url()?>/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugin js -->
    <!-- ============================================================== -->
    <script>

    $('[data-toggle="tooltip"]').tooltip();
    $(".preloader").fadeOut();
    // ============================================================== 
    // Login and Recover Password 
    // ============================================================== 
    $('#to-recover').on("click", function() {
        $("#loginform").slideUp();
        $("#recoverform").fadeIn();
    });
    $('#to-login').click(function(){
        $("#recoverform").hide();
        $("#loginform").fadeIn();
    });
    </script>

	<script>
		$('#toggle-password').click(function(){
			// ini ambil value dari input type dengan name element password
			var attrPassword = $('[name="password"]').attr('type');
			if(attrPassword == "password"){
				$('[name="password"]').prop('type', 'text');
			}else{
				$('[name="password"]').prop('type', 'password');
			}
		});


		$("#formLogin").submit(function(event){
			//mencegah form untuk submit serta berpindah halaman
			event.preventDefault();

			//kumpulkan data
			//serialize meng-encode setiap data yang telah di inputkan
			var data = $("#formLogin").serialize();

			//url action
			var urlLogin = "<?php echo site_url('Welcome/loginProcess')?>";

			//kirim data menggunakan post
			//post ada 4 params, 1. urlAction, 2. data yang dikirmkan, 3. callback success, 4. type response
			$.post(urlLogin, data, function(response){
				if(response.code == 200){
					//ini berhasil
					
					//pindah ke halaman lain
					location.assign('<?php echo site_url('bidang/form-add')?>');
				}else{
					//ini gagal
					$(".error-message").html(response.message)
				}
			}, 'json');
		});

	</script>

</body>

</html>
