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

@WebServlet("/SimpanMobil")
public class SimpanMobil extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
                          throws ServletException, IOException {

        try {

            Connection con = new Koneksi().getKoneksi();

            String sql =
            "INSERT INTO mobil VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setString(1, req.getParameter("id_mobil"));
            ps.setString(2, req.getParameter("merk"));
            ps.setString(3, req.getParameter("tipe"));

            ps.setInt(4,
                Integer.parseInt(req.getParameter("tahun")));

            ps.setString(5, req.getParameter("warna"));
            ps.setString(6, req.getParameter("nopol"));

            ps.setDouble(7,
                Double.parseDouble(req.getParameter("harga")));

            ps.setString(8, "Tersedia");

            ps.executeUpdate();

            res.sendRedirect("mobil.jsp");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}