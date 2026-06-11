package controller;

import config.Koneksi;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpanKembali")
public class SimpanKembali extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        try {

            Connection conn = new Koneksi().getKoneksi();

            String sql =
                "INSERT INTO pengembalian VALUES (?,?,?,?,?)";

            PreparedStatement ps =
                conn.prepareStatement(sql);

            ps.setString(1, request.getParameter("id_kembali"));
            ps.setString(2, request.getParameter("id_sewa"));
            ps.setString(3, request.getParameter("tanggal_kembali"));

            ps.setDouble(4,
                Double.parseDouble(request.getParameter("denda")));

            ps.setDouble(5,
                Double.parseDouble(request.getParameter("total_bayar")));

            ps.executeUpdate();

            response.sendRedirect("kembali.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}