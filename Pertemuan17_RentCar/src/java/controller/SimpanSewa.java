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

@WebServlet("/SimpanSewa")
public class SimpanSewa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        try {

            Connection conn = new Koneksi().getKoneksi();

            String sql =
                "INSERT INTO penyewaan VALUES (?,?,?,?,?,?)";

            PreparedStatement ps =
                conn.prepareStatement(sql);

            ps.setString(1, request.getParameter("id_sewa"));
            ps.setString(2, request.getParameter("tanggal_sewa"));
            ps.setString(3, request.getParameter("id_customer"));
            ps.setString(4, request.getParameter("id_mobil"));

            ps.setInt(5,
                Integer.parseInt(request.getParameter("lama_sewa")));

            ps.setDouble(6,
                Double.parseDouble(request.getParameter("total_bayar")));

            ps.executeUpdate();

            response.sendRedirect("sewa.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}