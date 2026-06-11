<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="config.Koneksi"%>

<%
int totalMobil = 0;
int totalCustomer = 0;
int totalSewa = 0;
int totalKembali = 0;

try{

    Connection conn = new Koneksi().getKoneksi();

    Statement st = conn.createStatement();

    ResultSet rs;

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM mobil");

    if(rs.next()){
        totalMobil = rs.getInt(1);
    }

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM customer");

    if(rs.next()){
        totalCustomer = rs.getInt(1);
    }

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM penyewaan");

    if(rs.next()){
        totalSewa = rs.getInt(1);
    }

    rs = st.executeQuery(
        "SELECT COUNT(*) FROM pengembalian");

    if(rs.next()){
        totalKembali = rs.getInt(1);
    }

}catch(Exception e){
    out.println(e);
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Dashboard Della Rent Car</title>

<link rel="stylesheet" href="dashboard.css">

</head>

<body>

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

<div class="main">

    <h1 class="page-title">
        Dashboard Della Rent Car
    </h1>

    <div class="card">

        <h2>Selamat Datang</h2>

        <p>
            Sistem Penyewaan Mobil Pertemuan17_RentCar
        </p>

    </div>

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

</div>

</body>
</html>