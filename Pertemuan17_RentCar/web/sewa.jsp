<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="config.Koneksi"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Penyewaan Mobil</title>

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

    <h1 class="page-title">Transaksi Penyewaan Mobil</h1>

    <!-- FORM -->

    <div class="form-card">

        <h2>Tambah Data Penyewaan</h2>

        <form action="SimpanSewa" method="post">

            <label>ID Sewa</label>
            <input type="text"
                   name="id_sewa"
                   placeholder="Contoh : S001"
                   required>

            <label>Tanggal Sewa</label>
            <input type="date"
                   name="tanggal_sewa"
                   required>

            <label>ID Customer</label>
            <input type="text"
                   name="id_customer"
                   placeholder="Contoh : C001"
                   required>

            <label>ID Mobil</label>
            <input type="text"
                   name="id_mobil"
                   placeholder="Contoh : M001"
                   required>

            <label>Lama Sewa (Hari)</label>
            <input type="number"
                   name="lama_sewa"
                   placeholder="Contoh : 3"
                   required>

            <label>Total Bayar</label>
            <input type="number"
                   name="total_bayar"
                   placeholder="Contoh : 1050000"
                   required>

            <button type="submit" class="btn">
                Simpan Penyewaan
            </button>

        </form>

    </div>

    <!-- TABEL -->

    <div class="table-card">

        <h2>Riwayat Penyewaan</h2>

        <table>

            <tr>
                <th>ID Sewa</th>
                <th>Tanggal Sewa</th>
                <th>ID Customer</th>
                <th>ID Mobil</th>
                <th>Lama Sewa</th>
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
                    "SELECT * FROM penyewaan ORDER BY id_sewa");

                while(rs.next()){
            %>

            <tr>

                <td>
                    <%= rs.getString("id_sewa") %>
                </td>

                <td>
                    <%= rs.getDate("tanggal_sewa") %>
                </td>

                <td>
                    <%= rs.getString("id_customer") %>
                </td>

                <td>
                    <%= rs.getString("id_mobil") %>
                </td>

                <td>
                    <%= rs.getInt("lama_sewa") %> Hari
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
                    "<tr><td colspan='6'>"
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