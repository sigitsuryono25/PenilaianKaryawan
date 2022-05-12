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
    <link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/4.0.2/css/fixedColumns.dataTables.min.css">
    <title>Laporan NPF</title>
    <style>
        th,
        td {
            white-space: nowrap;
        }

        div.dataTables_wrapper {
            width: 800px;
            margin: 0 auto;
        }
    </style>
</head>

<body>
    <div class="container-fluid">
        <h5 class="font-weight-bold text-center mb-4"><?= $kantor ?>
        </h5>
        <h5 class="text-center">Laporan NPF<br>per <?= $tanggal ?></h5>
        <div class="table-responsive">
            <table class="table table-sm table-bordered" id="tables">
                <thead>
                    <tr>
                        <th>KODE</th>
                        <th>KETERANGAN</th>
                        <th>Cust</th>
                        <th>Col 1</th>
                        <th>Cust</th>
                        <th>Col 2</th>
                        <th>Cust</th>
                        <th>Col 3</th>
                        <th>Cust</th>
                        <th>Col 4</th>
                        <th>Cust</th>
                        <th>Col 4</th>
                        <th>T-Cust</th>
                        <th>Total</th>
                        <th>NPF</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($response as $rs) { ?>
                        <tr>
                            <td><?= $rs->kode ?></td>
                            <td><?= $rs->ket ?></td>
                            <td> <?= $rs->qty1 ?></td>
                            <td>Rp. <?= $rs->coll1 ?></td>
                            <td> <?= $rs->qty2 ?></td>
                            <td>Rp. <?= $rs->coll2 ?></td>
                            <td> <?= $rs->qty3 ?></td>
                            <td>Rp. <?= $rs->coll3 ?></td>
                            <td> <?= $rs->qty4 ?></td>
                            <td>Rp. <?= $rs->coll4 ?></td>
                            <td> <?= $rs->qty5 ?></td>
                            <td>Rp. <?= $rs->coll5 ?></td>
                            <td> <?= $rs->qty1 + $rs->qty2 + $rs->qty3 + $rs->qty4 + $rs->qty5 ?></td>
                            <td>Rp. <?= $rs->jumlah ?></td>
                            <td>Rp. <?= $rs->npf ?></td>
                        </tr>
                    <?php } ?>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/fixedcolumns/4.0.2/js/dataTables.fixedColumns.min.js"></script>
    <script>
        $("#tables").DataTable({
            dom: 'ftpr',
            // scrollX: true,
            // scrollCollapse: true,
            // fixedColumns: {
            //     left: 2,
            //     right: 1
            // }

        });
    </script>
</body>

</html>