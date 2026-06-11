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

@WebServlet("/SimpanCustomer")
public class SimpanCustomer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        try {

            Connection conn = new Koneksi().getKoneksi();

            String sql =
                "INSERT INTO customer VALUES (?,?,?,?,?)";

            PreparedStatement ps =
                conn.prepareStatement(sql);

            ps.setString(1, request.getParameter("id_customer"));
            ps.setString(2, request.getParameter("nama_customer"));
            ps.setString(3, request.getParameter("alamat"));
            ps.setString(4, request.getParameter("no_telp"));
            ps.setString(5, request.getParameter("no_ktp"));

            ps.executeUpdate();

            response.sendRedirect("customer.jsp");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}