<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Menghitung Harga</title>
    </head>

    <body>

        <h2>Form Memasukkan Nilai</h2>

        <form method="post" action="HitungHarga">

            Nama Barang
            <input type="text"
                   name="namaBarang"
                   size="20"
                   value="Nama Barang">
            <br><br>

            Harga Satuan
            <input type="text"
                   name="harga"
                   size="20"
                   value="10000">
            <br><br>

            Jumlah
            <input type="text"
                   name="jumlah"
                   size="20"
                   value="100">
            <br><br>

            Diskon diberikan sebesar 5% jika jumlah >= 100
            <br>

            dan total harga sebelum diskon >= 1000000
            <br><br>

            <input type="submit" value="Hitung">

        </form>

    </body>
</html>