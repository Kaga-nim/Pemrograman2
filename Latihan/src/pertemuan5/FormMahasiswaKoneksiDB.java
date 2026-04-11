package pertemuan5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FormMahasiswaKoneksiDB extends JFrame {

    JTextField txtNim, txtNama, txtJurusan, txtAlamat;
    JButton btnTambah;
    JTable table;
    DefaultTableModel model;

    Connection conn;

    // METHOD KONEK DATABASE
    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_mahasiswa";
            String user = "root";
            String pass = ""; // default XAMPP kosong

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil!");
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public FormMahasiswaKoneksiDB() {

        connect(); // panggil koneksi

        setTitle("Form Mahasiswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

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

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(100, 150, 100, 30);
        add(btnTambah);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 200, 440, 130);
        add(scrollPane);

        model.addColumn("NIM");
        model.addColumn("Nama");
        model.addColumn("Jurusan");
        model.addColumn("Alamat");

        // EVENT BUTTON
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

                    // tampil ke tabel
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
    }

    public static void main(String[] args) {
        new FormMahasiswaKoneksiDB().setVisible(true);
    }
}