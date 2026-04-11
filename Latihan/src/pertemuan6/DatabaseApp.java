package pertemuan6;

import java.sql.*;

public class DatabaseApp {

    // 🔌 METHOD KONEKSI DATABASE
    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/db_mahasiswa";
            String user = "root";
            String pass = "";

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }

    // 🔍 SELECT (Cari Data)
    public static void cariData(Connection conn, String namaCari) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT * FROM mahasiswa WHERE nama LIKE '%" + namaCari + "%'"
            );

            if (rs.next()) {
                System.out.println("=== DATA DITEMUKAN ===");
                System.out.println("NIM     : " + rs.getString("nim"));
                System.out.println("Nama    : " + rs.getString("nama"));
                System.out.println("Jurusan : " + rs.getString("jurusan"));
                System.out.println("Alamat  : " + rs.getString("alamat"));
            } else {
                System.out.println("Data tidak ditemukan");
            }

        } catch (SQLException e) {
            System.out.println("Error SELECT: " + e.getMessage());
        }
    }

    // ✏️ UPDATE DATA
    public static void updateData(Connection conn, String nim, String namaBaru) {
        try {
            String sql = "UPDATE mahasiswa SET nama=? WHERE nim=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, namaBaru);
            ps.setString(2, nim);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                System.out.println("Data berhasil diupdate");
            } else {
                System.out.println("Data tidak ditemukan");
            }

        } catch (SQLException e) {
            System.out.println("Error UPDATE: " + e.getMessage());
        }
    }

    // 🗑️ DELETE DATA
    public static void hapusData(Connection conn, String nim) {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nim);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                System.out.println("Data berhasil dihapus");
            } else {
                System.out.println("Data tidak ditemukan");
            }

        } catch (SQLException e) {
            System.out.println("Error DELETE: " + e.getMessage());
        }
    }

    // 🚀 MAIN PROGRAM
    public static void main(String[] args) {
        Connection conn = getKoneksi();

        if (conn != null) {
            // 🔽 TESTING

            // 1. Cari Data
            System.out.println("\n-- CARI DATA --");
            cariData(conn, "Budi");

            // 2. Update Data
            System.out.println("\n-- UPDATE DATA --");
            updateData(conn, "123", "Budi Update");

            // 3. Hapus Data
            System.out.println("\n-- DELETE DATA --");
            hapusData(conn, "123");
        }
    }
}