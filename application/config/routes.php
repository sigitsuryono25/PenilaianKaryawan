<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*
| -------------------------------------------------------------------------
| URI ROUTING
| -------------------------------------------------------------------------
| This file lets you re-map URI requests to specific controller functions.
|
| Typically there is a one-to-one relationship between a URL string
| and its corresponding controller class/method. The segments in a
| URL normally follow this pattern:
|
|	example.com/class/method/id/
|
| In some instances, however, you may want to remap this relationship
| so that a different class/function is called than the one
| corresponding to the URL.
|
| Please see the user guide for complete details:
|
|	https://codeigniter.com/user_guide/general/routing.html
|
| -------------------------------------------------------------------------
| RESERVED ROUTES
| -------------------------------------------------------------------------
|
| There are three reserved routes:
|
|	$route['default_controller'] = 'welcome';
|
| This route indicates which controller class should be loaded if the
| URI contains no data. In the above example, the "welcome" class
| would be loaded.
|
|	$route['404_override'] = 'errors/page_missing';
|
| This route will tell the Router which controller/method to use if those
| provided in the URL cannot be matched to a valid route.
|
|	$route['translate_uri_dashes'] = FALSE;
|
| This is not exactly a route, but allows you to automatically route
| controller and method names that contain dashes. '-' isn't a valid
| class or method name character, so it requires translation.
| When you set this option to TRUE, it will replace ALL dashes in the
| controller and method URI segments.
|
| Examples:	my-controller/index	-> my_controller/index
|		my-controller/my-method	-> my_controller/my_method
*/
$route['default_controller'] = 'welcome';
$route['404_override'] = '';
$route['translate_uri_dashes'] = FALSE;


//api
$route['api/user/auth'] = "api/Api_user/index";
$route['api/user/list/(:any)'] = "api/Api_user/getAllUser/$1";
$route['api/user/list'] = "api/Api_user/getAllUser";
$route['api/user/jabatan'] = "api/Api_user/getJabatan";
$route['api/penilaian/param'] = "api/Api_params/getParams";
$route['api/penilaian/received'] = "api/Api_params/receivedPenilaian";
$route['api/penilaian/get-penilaian'] = "api/Api_params/getPenilaian";
$route['api/penilaian/get-penilaian-by-user'] = "api/Api_params/getPenilaianByUser";
$route['api/pengumuman/list'] = "api/Api_pengumuman/getAllPengumuman";
$route['api/pengumuman/detail'] = "api/Api_pengumuman/detailPengumuman";

//bidang
$route['bidang/form-add'] = "Bidang/formAdd";
$route['bidang/data-bidang-api'] = "Bidang/dataBidang";
$route['bidang/get-detail'] = "Bidang/getDetailById";
$route['bidang/action-add'] = "Bidang/insertData";
$route['bidang/action-delete'] = "Bidang/deleteData";
$route['bidang/action-update'] = "Bidang/updateData";


//penilaian
$route['penilaian/add-parameter'] = "Penilaian/formAddParam";
$route['penilaian/list'] = "Penilaian/daftarParams";
$route['penilaian/action-add'] = "Penilaian/insertData";
$route['penilaian/result'] = "Penilaian/hasilPenilaian";

//pengumuman
$route['pengumuman/add-pengumuman'] = "Pengumuman/formAdd";
$route['pengumuman/form-edit'] = "Pengumuman/formEditData";
$route['pengumuman/action-add'] = "Pengumuman/insertData";
$route['pengumuman/action-update'] = "Pengumuman/updateData";
$route['pengumuman/list'] = "Pengumuman/daftarPengumuman";
$route['pengumuman/action-delete'] = "Pengumuman/deleteData";


//user
$route['user/get-by-jabatan'] = "User/getUserByJabatan";
$route['user/list'] = "User/getAllUser";
$route['user/add-user'] = "User/formAdd";
$route['user/action-add'] = "User/insertData";
$route['user/form-edit'] = "User/formEdit";
$route['user/action-update'] = "User/editData";
$route['user/action-delete'] = "User/hapusData";


//rules
$route['rules/form-rules'] = "Rules/formRules";
$route['rules/add-rules'] = "Rules/addRules";
$route['rules/list'] = "Rules/rulesData";
$route['rules/action-delete'] = "Rules/deleteRules";
$route['rules/action-update'] = "Rules/updateRules";
$route['rules/form-edit'] = "Rules/formEdit";
