<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="config.Koneksi"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Pengembalian Mobil</title>

<link rel="stylesheet" href="dashboard.css">

</head>

<body>

<!-- SIDEBAR -->

<div class="sidebar">

    <div class="logo">
        DELLA RENT CAR
    </div>

    <a href="index.jsp">Dashboard</a>
    <a href="mobil.jsp">Data Mobil</a>
    <a href="customer.jsp">Customer</a>
    <a href="sewa.jsp">Penyewaan</a>
    <a href="kembali.jsp">Pengembalian</a>
    <a href="laporan.jsp">Laporan</a>

</div>

<!-- CONTENT -->

<div class="main">

    <h1 class="page-title">
        Transaksi Pengembalian Mobil
    </h1>

    <!-- FORM -->

    <div class="form-card">

        <h2>Tambah Data Pengembalian</h2>

        <form action="SimpanKembali" method="post">

            <label>ID Kembali</label>
            <input type="text"
                   name="id_kembali"
                   placeholder="Contoh : K001"
                   required>

            <label>ID Sewa</label>
            <input type="text"
                   name="id_sewa"
                   placeholder="Contoh : S001"
                   required>

            <label>Tanggal Kembali</label>
            <input type="date"
                   name="tanggal_kembali"
                   required>

            <label>Denda</label>
            <input type="number"
                   name="denda"
                   placeholder="Contoh : 50000"
                   required>

            <label>Total Bayar</label>
            <input type="number"
                   name="total_bayar"
                   placeholder="Contoh : 1100000"
                   required>

            <button type="submit" class="btn">
                Simpan Pengembalian
            </button>

        </form>

    </div>

    <!-- TABEL -->

    <div class="table-card">

        <h2>Riwayat Pengembalian</h2>

        <table>

            <tr>
                <th>ID Kembali</th>
                <th>ID Sewa</th>
                <th>Tanggal Kembali</th>
                <th>Denda</th>
                <th>Total Bayar</th>
            </tr>

            <%
            try{

                Connection conn =
                    new Koneksi().getKoneksi();

                Statement st =
                    conn.createStatement();

                ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM pengembalian ORDER BY id_kembali");

                while(rs.next()){
            %>

            <tr>

                <td>
                    <%= rs.getString("id_kembali") %>
                </td>

                <td>
                    <%= rs.getString("id_sewa") %>
                </td>

                <td>
                    <%= rs.getDate("tanggal_kembali") %>
                </td>

                <td>
                    Rp <%= String.format("%,.0f",
                        rs.getDouble("denda")) %>
                </td>

                <td>
                    Rp <%= String.format("%,.0f",
                        rs.getDouble("total_bayar")) %>
                </td>

            </tr>

            <%
                }

            }catch(Exception e){

                out.println(
                    "<tr><td colspan='5'>"
                    + e.getMessage()
                    + "</td></tr>"
                );

            }
            %>

        </table>

    </div>

</div>

</body>
</html>