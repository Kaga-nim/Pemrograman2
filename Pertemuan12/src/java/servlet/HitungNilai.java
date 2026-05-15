package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HitungNilai",
        urlPatterns = {"/HitungNilai"})

public class HitungNilai extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        int hadir =
                Integer.parseInt(
                request.getParameter("hadir"));

        int pertemuan =
                Integer.parseInt(
                request.getParameter("pertemuan"));

        double tugas =
                Double.parseDouble(
                request.getParameter("tugas"));

        double uts =
                Double.parseDouble(
                request.getParameter("uts"));

        double uas =
                Double.parseDouble(
                request.getParameter("uas"));

        double presensi =
                ((double) hadir / pertemuan) * 100;

        double nilaiAkhir =
                (presensi * 0.1) +
                (tugas * 0.2) +
                (uts * 0.3) +
                (uas * 0.4);

        String grade;
        String status;

        if (nilaiAkhir >= 80) {

            grade = "A";
            status = "Lulus";

        } else if (nilaiAkhir >= 70) {

            grade = "B";
            status = "Lulus";

        } else if (nilaiAkhir >= 60) {

            grade = "C";
            status = "Lulus";

        } else if (nilaiAkhir >= 50) {

            grade = "D";
            status = "Tidak Lulus";

        } else {

            grade = "E";
            status = "Tidak Lulus";
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Menghitung Nilai</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Menghitung Nilai</h1>");

        out.println("<form>");

        out.println("<table>");

        out.println("<tr>");
        out.println("<td>Jumlah hadir</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + hadir + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Jumlah pertemuan</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + pertemuan + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Nilai tugas</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + tugas + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Nilai UTS</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + uts + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Nilai UAS</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + uas + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Nilai Akhir</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + nilaiAkhir + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Grade</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + grade + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Status</td>");
        out.println("<td>");
        out.println("<input type='text' value='" + status + "'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td></td>");
        out.println("<td>");
        out.println("<input type='button' value='Kembali' onclick='history.back()'>");
        out.println("</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}