package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HitungHarga", urlPatterns = {"/HitungHarga"})

public class HitungHarga extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String namaBarang =
                request.getParameter("namaBarang");

        double harga =
                Double.parseDouble(
                request.getParameter("harga"));

        int jumlah =
                Integer.parseInt(
                request.getParameter("jumlah"));

        double total = harga * jumlah;

        double diskon = 0;

        if (jumlah >= 100 && total >= 1000000) {

            diskon = total * 0.05;
        }

        double totalBayar = total - diskon;

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Menghitung Harga</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h2>Hasil Penghitungan Harga</h2>");

        out.println("<br>");

        out.println("Nama Barang &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : "
                + namaBarang);

        out.println("<br>");

        out.println("Harga Satuan &nbsp;&nbsp;&nbsp;&nbsp; : "
                + (int) harga);

        out.println("<br>");

        out.println("Jumlah &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : "
                + jumlah);

        out.println("<br>");

        out.println("Diskon &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : "
                + (int) diskon);

        out.println("<br>");

        out.println("Total &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : "
                + (int) totalBayar);

        out.println("<br><br>");

        out.println("<form action='index.jsp'>");
        out.println("<input type='submit' value='Kembali'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}