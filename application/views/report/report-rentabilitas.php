<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="Description" content="Enter your description here" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>Report CAR</title>
</head>

<body>

    <div class="container p-3">
        <div class="table-reponsive">
            <h5 class="font-weight-bold text-center mb-4"><?= $kantor?>
            </h5>
            <h5 class="text-center">Laporan Rentabilitas<br><?= $tanggal ?></h5>
            <table class="table table-bordered table-sm">
                <thead>
                    <tr>
                        <th>Pendapatan Pembiayaan</th>
                        <th>Pembiayaan</th>
                        <th>Renta</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><?= $data['Pendapatan Pembiayaan'] ?></td>
                        <td><?= $data['Pembiayaan'] ?></td>
                        <td><?= $data['Renta'] ?></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
</body>

</html>