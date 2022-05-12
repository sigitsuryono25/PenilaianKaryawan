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
    <title>Laporan Rata-Rata Saldo Tabungan & Deposito</title>
</head>

<body style="font-size: 11px;">
    <div class="container p-3">
        <div class="table-reponsive">
            <h5 class="font-weight-bold text-center mb-4"><?= $kantor?>
            </h5>
            <h6 class="text-center">Laporan Rata Rata Saldo Tabungan & Deposito <br><?= $tanggal ?> - <?= $tanggal2 ?></h6>
            <div class="table-responsive">
                <table class="table table-sm table-bordered" id="tables">
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>KODE</th>
                            <th>KETERANGAN</th>
                            <th>RATA-RATA TAB</th>
                            <th>QTY DEP</th>
                            <th>TOT DEP</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        $total = 0;
                        foreach ($data as $rs) {
                        ?>
                            <tr>
                                <td><?= $rs->no ?></td>
                                <td><?= $rs->kode ?></td>
                                <td><?= $rs->ket ?></td>
                                <td><?= $rs->rata_tab ?></td>
                                <td><?= $rs->qty_dep ?></td>
                                <td><?= $rs->tot_dep ?></td>
                            </tr>
                        <?php
                        } ?>
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