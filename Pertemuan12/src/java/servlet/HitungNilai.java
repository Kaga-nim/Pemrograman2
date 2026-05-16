package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HitungNilai", urlPatterns = {"/HitungNilai"})
public class HitungNilai extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nama  = request.getParameter("nama");
        double tugas = Double.parseDouble(request.getParameter("tugas"));
        double uts   = Double.parseDouble(request.getParameter("uts"));
        double uas   = Double.parseDouble(request.getParameter("uas"));

        double nilaiAkhir = (tugas * 0.30) + (uts * 0.35) + (uas * 0.35);

        String grade;
        if      (nilaiAkhir >= 85) grade = "A";
        else if (nilaiAkhir >= 70) grade = "B";
        else if (nilaiAkhir >= 60) grade = "C";
        else if (nilaiAkhir >= 50) grade = "D";
        else                       grade = "E";

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html>");
            out.println("<head><title>Hasil Nilai</title></head>");
            out.println("<body>");
            out.println("<h2>Hasil Perhitungan Nilai</h2>");
            out.println("<table border='1'>");
            out.println("<tr><td>Nama Mahasiswa</td><td>" + nama + "</td></tr>");
            out.println("<tr><td>Nilai Tugas (30%)</td><td>" + tugas + "</td></tr>");
            out.println("<tr><td>Nilai UTS (35%)</td><td>" + uts + "</td></tr>");
            out.println("<tr><td>Nilai UAS (35%)</td><td>" + uas + "</td></tr>");
            out.println("<tr><td><b>Nilai Akhir</b></td><td><b>" + String.format("%.2f", nilaiAkhir) + "</b></td></tr>");
            out.println("<tr><td><b>Grade</b></td><td><b>" + grade + "</b></td></tr>");
            out.println("</table>");
            out.println("<br/><a href='index.html'>Kembali</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }
}
