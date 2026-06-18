<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="config.Koneksi"%>

<%
int totalMobil = 0;
int totalCustomer = 0;
int totalSewa = 0;
int totalKembali = 0;

try{

    Connection conn =
        new Koneksi().getKoneksi();

    Statement st =
        conn.createStatement();

    ResultSet rs;

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM mobil");

    if(rs.next())
        totalMobil = rs.getInt(1);

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM customer");

    if(rs.next())
        totalCustomer = rs.getInt(1);

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM penyewaan");

    if(rs.next())
        totalSewa = rs.getInt(1);

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM pengembalian");

    if(rs.next())
        totalKembali = rs.getInt(1);

}catch(Exception e){
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Laporan Rent Car</title>

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
        Laporan Rent Car
    </h1>

    <!-- RINGKASAN -->

    <div class="info">

        <div class="box">
            <h2>Total Mobil</h2>
            <h1><%= totalMobil %></h1>
        </div>

        <div class="box">
            <h2>Total Customer</h2>
            <h1><%= totalCustomer %></h1>
        </div>

        <div class="box">
            <h2>Total Penyewaan</h2>
            <h1><%= totalSewa %></h1>
        </div>

        <div class="box">
            <h2>Total Pengembalian</h2>
            <h1><%= totalKembali %></h1>
        </div>

    </div>

    <br>

    <!-- LAPORAN PENYEWAAN -->

    <div class="table-card">

        <h2>Laporan Penyewaan</h2>

        <table>

            <tr>
                <th>ID Sewa</th>
                <th>Tanggal</th>
                <th>Customer</th>
                <th>Mobil</th>
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
                    "SELECT * FROM penyewaan");

                while(rs.next()){
            %>

            <tr>

                <td><%= rs.getString("id_sewa") %></td>

                <td><%= rs.getDate("tanggal_sewa") %></td>

                <td><%= rs.getString("id_customer") %></td>

                <td><%= rs.getString("id_mobil") %></td>

                <td><%= rs.getInt("lama_sewa") %> Hari</td>

                <td>
                    Rp
                    <%= String.format("%,.0f",
                    rs.getDouble("total_bayar")) %>
                </td>

            </tr>

            <%
                }

            }catch(Exception e){
                out.println(e);
            }
            %>

        </table>

    </div>

    <!-- LAPORAN PENGEMBALIAN -->

    <div class="table-card">

        <h2>Laporan Pengembalian</h2>

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
                    "SELECT * FROM pengembalian");

                while(rs.next()){
            %>

            <tr>

                <td><%= rs.getString("id_kembali") %></td>

                <td><%= rs.getString("id_sewa") %></td>

                <td><%= rs.getDate("tanggal_kembali") %></td>

                <td>
                    Rp
                    <%= String.format("%,.0f",
                    rs.getDouble("denda")) %>
                </td>

                <td>
                    Rp
                    <%= String.format("%,.0f",
                    rs.getDouble("total_bayar")) %>
                </td>

            </tr>

            <%
                }

            }catch(Exception e){
                out.println(e);
            }
            %>

        </table>

    </div>

</div>

</body>
</html>