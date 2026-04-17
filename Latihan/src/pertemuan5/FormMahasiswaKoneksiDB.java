package pertemuan7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// IMPORT JASPER
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class FormMahasiswaReport extends JFrame {

    JTextField txtNim, txtNama, txtJurusan, txtAlamat;
    JButton btnTambah, btnCetak;
    JTable table;
    DefaultTableModel model;

    Connection conn;

    // KONEKSI DATABASE
    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_mahasiswa";
            String user = "root";
            String pass = "";

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil!");
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public FormMahasiswaReport() {

        connect(); // koneksi jalan

        setTitle("Form Mahasiswa + Report");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // LABEL
        JLabel lblNim = new JLabel("NIM");
        lblNim.setBounds(20, 20, 80, 25);
        add(lblNim);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(20, 50, 80, 25);
        add(lblNama);

        JLabel lblJurusan = new JLabel("Jurusan");
        lblJurusan.setBounds(20, 80, 80, 25);
        add(lblJurusan);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(20, 110, 80, 25);
        add(lblAlamat);

        // TEXTFIELD
        txtNim = new JTextField();
        txtNim.setBounds(100, 20, 150, 25);
        add(txtNim);

        txtNama = new JTextField();
        txtNama.setBounds(100, 50, 150, 25);
        add(txtNama);

        txtJurusan = new JTextField();
        txtJurusan.setBounds(100, 80, 150, 25);
        add(txtJurusan);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(100, 110, 150, 25);
        add(txtAlamat);

        // BUTTON TAMBAH
        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(100, 150, 100, 30);
        add(btnTambah);

        // BUTTON CETAK
        btnCetak = new JButton("Cetak Laporan");
        btnCetak.setBounds(220, 150, 150, 30);
        add(btnCetak);

        // TABEL
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 200, 500, 150);
        add(scrollPane);

        model.addColumn("NIM");
        model.addColumn("Nama");
        model.addColumn("Jurusan");
        model.addColumn("Alamat");

        // EVENT TAMBAH DATA
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "INSERT INTO mahasiswa VALUES (?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setString(1, txtNim.getText());
                    pst.setString(2, txtNama.getText());
                    pst.setString(3, txtJurusan.getText());
                    pst.setString(4, txtAlamat.getText());

                    pst.executeUpdate();

                    Object[] data = {
                        txtNim.getText(),
                        txtNama.getText(),
                        txtJurusan.getText(),
                        txtAlamat.getText()
                    };
                    model.addRow(data);

                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");

                    txtNim.setText("");
                    txtNama.setText("");
                    txtJurusan.setText("");
                    txtAlamat.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Gagal: " + ex.getMessage());
                }
            }
        });

        // EVENT CETAK LAPORAN 🔥
        btnCetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String path = "src/laporan/laporan1.jasper";

                    JasperPrint print = JasperFillManager.fillReport(path, null, conn);
                    JasperViewer.viewReport(print, false);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Gagal cetak: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        new FormMahasiswaReport().setVisible(true);
    }
}