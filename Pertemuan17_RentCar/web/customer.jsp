<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="config.Koneksi"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Data Customer</title>

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

    <h1 class="page-title">Data Customer</h1>

    <!-- FORM INPUT -->

    <div class="form-card">

        <h2>Tambah Data Customer</h2>

        <form action="SimpanCustomer" method="post">

            <label>ID Customer</label>
            <input type="text"
                   name="id_customer"
                   placeholder="Contoh : C001"
                   required>

            <label>Nama Customer</label>
            <input type="text"
                   name="nama_customer"
                   placeholder="Contoh : Budi Santoso"
                   required>

            <label>Alamat</label>
            <textarea name="alamat"
                      rows="4"
                      placeholder="Masukkan alamat customer"
                      required></textarea>

            <label>No Telepon</label>
            <input type="text"
                   name="no_telp"
                   placeholder="Contoh : 08123456789"
                   required>

            <label>No KTP</label>
            <input type="text"
                   name="no_ktp"
                   placeholder="Contoh : 317xxxxxxxx"
                   required>

            <button type="submit" class="btn">
                Simpan Data
            </button>

        </form>

    </div>

    <!-- TABEL CUSTOMER -->

    <div class="table-card">

        <h2>Daftar Customer</h2>

        <table>

            <tr>
                <th>ID Customer</th>
                <th>Nama Customer</th>
                <th>Alamat</th>
                <th>No Telepon</th>
                <th>No KTP</th>
            </tr>

            <%
            try{

                Connection conn =
                    new Koneksi().getKoneksi();

                Statement st =
                    conn.createStatement();

                ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM customer ORDER BY id_customer");

                while(rs.next()){
            %>

            <tr>

                <td>
                    <%= rs.getString("id_customer") %>
                </td>

                <td>
                    <%= rs.getString("nama_customer") %>
                </td>

                <td>
                    <%= rs.getString("alamat") %>
                </td>

                <td>
                    <%= rs.getString("no_telp") %>
                </td>

                <td>
                    <%= rs.getString("no_ktp") %>
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