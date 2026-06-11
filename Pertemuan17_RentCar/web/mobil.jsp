<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="config.Koneksi"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Data Mobil</title>

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

    <h1 class="page-title">Data Mobil</h1>

    <!-- FORM INPUT -->

    <div class="form-card">

        <h2>Tambah Data Mobil</h2>

        <form action="SimpanMobil" method="post">

            <label>ID Mobil</label>
            <input type="text"
                   name="id_mobil"
                   placeholder="Contoh : M001"
                   required>

            <label>Merk Mobil</label>
            <input type="text"
                   name="merk"
                   placeholder="Contoh : Toyota"
                   required>

            <label>Tipe Mobil</label>
            <input type="text"
                   name="tipe"
                   placeholder="Contoh : Avanza"
                   required>

            <label>Tahun</label>
            <input type="number"
                   name="tahun"
                   placeholder="Contoh : 2024"
                   required>

            <label>Warna</label>
            <input type="text"
                   name="warna"
                   placeholder="Contoh : Hitam"
                   required>

            <label>No Polisi</label>
            <input type="text"
                   name="nopol"
                   placeholder="Contoh : B1234ABC"
                   required>

            <label>Harga Sewa / Hari</label>
            <input type="number"
                   name="harga"
                   placeholder="Contoh : 350000"
                   required>

            <button type="submit" class="btn">
                Simpan Data
            </button>

        </form>

    </div>

    <!-- TABEL DATA -->

    <div class="table-card">

        <h2>Daftar Mobil</h2>

        <table>

            <tr>
                <th>ID Mobil</th>
                <th>Merk</th>
                <th>Tipe</th>
                <th>Tahun</th>
                <th>Warna</th>
                <th>No Polisi</th>
                <th>Harga Sewa</th>
                <th>Status</th>
            </tr>

            <%
            try{

                Connection conn =
                    new Koneksi().getKoneksi();

                Statement st =
                    conn.createStatement();

                ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM mobil ORDER BY id_mobil");

                while(rs.next()){
            %>

            <tr>

                <td>
                    <%= rs.getString("id_mobil") %>
                </td>

                <td>
                    <%= rs.getString("merk") %>
                </td>

                <td>
                    <%= rs.getString("tipe") %>
                </td>

                <td>
                    <%= rs.getInt("tahun") %>
                </td>

                <td>
                    <%= rs.getString("warna") %>
                </td>

                <td>
                    <%= rs.getString("no_polisi") %>
                </td>

                <td>
                    Rp <%= String.format("%,.0f",
                        rs.getDouble("harga_sewa")) %>
                </td>

                <td>
                    <%= rs.getString("status") %>
                </td>

            </tr>

            <%
                }

            }catch(Exception e){

                out.println(
                    "<tr><td colspan='8'>"
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