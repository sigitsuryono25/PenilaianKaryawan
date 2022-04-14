<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="Description" content="Enter your description here" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <title>Laporan Neraca</title>
</head>

<body>
    <div class="container">
        <div class="table-reponsive">
            <h5 class="font-weight-bold text-center mb-4">KANTOR PUSAT<br>
                <small>Jl. Pemuda Barat No. 02 Pucungrejo</small>
            </h5>
            <h5 class="text-center">Laporan Neraca<br>per <?= $tanggal ?></h5>
            <div class="table-responsive">
                <table class="table table-sm table-bordered" id="tables">
                    <thead>
                        <tr>
                            <th>NO-SBB</th>
                            <th>KETERANGAN</th>
                            <th>SALDO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($response as $rs) { ?>
                            <tr>
                                <td><?= $rs->nosbb ?></td>
                                <td><?= $rs->nmsbb ?></td>
                                <td>Rp. <?= $rs->saldo ?></td>
                            </tr>
                        <?php } ?>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script>
        $("#tables").DataTable({
            dom: 'ftpr'
        });
    </script>
</body>

</html>