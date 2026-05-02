package Pertemuan9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pertemuan9 extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Pertemuan9().setVisible(true));
    }

    // ── Tema Warna Pink ────────────────────────────────────
    static final Color PINK_DEEP  = new Color(194, 90, 130);
    static final Color PINK_MID   = new Color(230, 140, 170);
    static final Color PINK_LITE  = new Color(253, 230, 240);
    static final Color PINK_PALE  = new Color(255, 245, 250);
    static final Color WHITE      = Color.WHITE;
    static final Color TEXT_DARK  = new Color(80, 40, 60);
    static final Color GRAY_LINE  = new Color(240, 200, 220);

    static final Font FONT_LBL    = new Font("Segoe UI", Font.PLAIN, 12);
    static final Font FONT_BOLD   = new Font("Segoe UI", Font.BOLD, 12);
    static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 14);
    static final Font FONT_HEAD   = new Font("Segoe UI", Font.BOLD, 18);
    static final Font FONT_SMALL  = new Font("Segoe UI", Font.PLAIN, 10);

    public static Connection getConnection() {
//        try { Class.forName("org.mysql.jdbc.Driver"); }
//        catch (ClassNotFoundException e) { throw new SQLException("Driver tidak ditemukan"); }
//        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/toko_della";
            String user = "root";
            String pass = "";

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }

    // ── Helper: field dengan label di atas ────────────────
    static JTextField addFieldVertical(JPanel p, String label, int x, int y, int w) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setForeground(PINK_DEEP);
        lbl.setBounds(x, y, w, 16);
        p.add(lbl);
        JTextField tf = new JTextField();
        tf.setBounds(x, y + 18, w, 30);
        tf.setFont(FONT_LBL);
        tf.setBackground(WHITE);
        tf.setForeground(TEXT_DARK);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, PINK_MID),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)));
        p.add(tf);
        return tf;
    }

    // ── Helper: tombol solid ───────────────────────────────
    static JButton makeBtn(String text) {
        JButton b = new JButton(text);
        b.setBackground(PINK_DEEP);
        b.setForeground(WHITE);
        b.setFont(FONT_BOLD);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(new Color(160, 60, 100)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { b.setBackground(PINK_DEEP); }
        });
        return b;
    }

    static JButton makeOutlineBtn(String text) {
        JButton b = new JButton(text);
        b.setBackground(PINK_PALE);
        b.setForeground(PINK_DEEP);
        b.setFont(FONT_BOLD);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createLineBorder(PINK_MID, 1));
        return b;
    }

    static DefaultTableModel makeModel(String[] cols) {
        return new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
    }

    static JTable makeStyledTable(DefaultTableModel model) {
        JTable t = new JTable(model);
        t.setSelectionBackground(PINK_LITE);
        t.setSelectionForeground(PINK_DEEP);
        t.getTableHeader().setBackground(PINK_DEEP);
        t.getTableHeader().setForeground(WHITE);
        t.getTableHeader().setFont(FONT_BOLD);
        t.getTableHeader().setPreferredSize(new Dimension(0, 32));
        t.setFont(FONT_LBL);
        t.setRowHeight(26);
        t.setGridColor(GRAY_LINE);
        t.setBackground(WHITE);
        t.setForeground(TEXT_DARK);
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(false);
        t.setIntercellSpacing(new Dimension(0, 1));
        return t;
    }

    static JScrollPane wrapTable(JTable t) {
        JScrollPane sp = new JScrollPane(t);
        sp.setBorder(BorderFactory.createLineBorder(PINK_MID, 1));
        sp.getViewport().setBackground(WHITE);
        return sp;
    }

    // ── Section title ─────────────────────────────────────
    static JLabel sectionTitle(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setForeground(PINK_DEEP);
        return l;
    }

    // ══════════════════════════════════════════════════════
    // MAIN FRAME
    // ══════════════════════════════════════════════════════
    public Pertemuan9() {
        setTitle("Aplikasi Penjualan - Seftia Della Fiisyatir Rodhiah (231011401012)");
        setSize(900, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ── Header ────────────────────────────────────────
        JPanel header = new JPanel(null);
        header.setBackground(PINK_DEEP);
        header.setPreferredSize(new Dimension(900, 58));

        JLabel lblApp = new JLabel("Aplikasi Penjualan Barang");
        lblApp.setFont(FONT_HEAD);
        lblApp.setForeground(WHITE);
        lblApp.setBounds(20, 7, 450, 26);
        header.add(lblApp);

        JLabel lblSub = new JLabel("Seftia Della Fiisyatir Rodhiah   |   NIM : 231011401012");
        lblSub.setFont(FONT_SMALL);
        lblSub.setForeground(new Color(255, 210, 230));
        lblSub.setBounds(20, 34, 500, 16);
        header.add(lblSub);

        JPanel stripe = new JPanel();
        stripe.setBackground(new Color(220, 110, 150));
        stripe.setBounds(0, 54, 900, 4);
        header.add(stripe);

        add(header, BorderLayout.NORTH);

        // ── Tabs ──────────────────────────────────────────
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT);
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabs.setBackground(PINK_PALE);
        tabs.setForeground(TEXT_DARK);

        tabs.addTab("Dashboard",  new PanelDashboard());
        tabs.addTab("Barang",     new PanelBarang());
        tabs.addTab("Customer",   new PanelCustomer());
        tabs.addTab("Supplier",   new PanelSupplier());
        tabs.addTab("Transaksi",  new PanelTransaksi());
        tabs.addTab("Inventory",  new PanelInventory());
        tabs.addTab("Laporan",    new PanelLaporan());

        add(tabs, BorderLayout.CENTER);
    }

    // ══════════════════════════════════════════════════════
    // DASHBOARD  — card vertikal di kiri, info di kanan
    // ══════════════════════════════════════════════════════
    static class PanelDashboard extends JPanel {
        JLabel valBarang, valCustomer, valSupplier, valTransaksi;

        PanelDashboard() {
            setLayout(null);
            setBackground(PINK_PALE);

            // Judul
            JLabel title = new JLabel("Dashboard");
            title.setFont(new Font("Segoe UI", Font.BOLD, 20));
            title.setForeground(PINK_DEEP);
            title.setBounds(20, 18, 300, 30);
            add(title);

            JLabel sub = new JLabel("Ringkasan data aplikasi penjualan");
            sub.setFont(FONT_SMALL);
            sub.setForeground(PINK_MID);
            sub.setBounds(20, 48, 300, 16);
            add(sub);

            // Card vertikal di kiri
            valBarang    = addVerticalCard("Total Barang",    "0", 20, 80,  new Color(255, 182, 207));
            valCustomer  = addVerticalCard("Total Customer",  "0", 20, 175, new Color(255, 160, 190));
            valSupplier  = addVerticalCard("Total Supplier",  "0", 20, 270, new Color(230, 130, 170));
            valTransaksi = addVerticalCard("Total Transaksi", "0", 20, 365, PINK_DEEP);

            // Panel info kanan
            JPanel info = new JPanel(null);
            info.setBounds(230, 80, 580, 430);
            info.setBackground(WHITE);
            info.setBorder(BorderFactory.createLineBorder(GRAY_LINE, 1));

            JPanel infoHeader = new JPanel(null);
            infoHeader.setBackground(PINK_DEEP);
            infoHeader.setBounds(0, 0, 580, 40);
            JLabel infoTitle = new JLabel("Informasi Aplikasi");
            infoTitle.setFont(FONT_BOLD);
            infoTitle.setForeground(WHITE);
            infoTitle.setBounds(16, 10, 300, 20);
            infoHeader.add(infoTitle);
            info.add(infoHeader);

            String[][] rows = {
                {"Nama Aplikasi",  "Aplikasi Penjualan Barang"},
                {"Dibuat oleh",    "Seftia Della Fiisyatir Rodhiah"},
                {"NIM",            "231011401012"},
                {"Database",       "MySQL - toko_della"},
                {"Fitur",          "Barang, Customer, Supplier, Transaksi, Inventory, Laporan"},
                {"Framework",      "Java Swing + JasperReports"},
            };

            int ry = 55;
            for (String[] row : rows) {
                JLabel k = new JLabel(row[0]);
                k.setFont(FONT_BOLD); k.setForeground(PINK_DEEP);
                k.setBounds(20, ry, 160, 20); info.add(k);
                JLabel v = new JLabel(row[1]);
                v.setFont(FONT_LBL); v.setForeground(TEXT_DARK);
                v.setBounds(190, ry, 370, 20); info.add(v);
                JSeparator s = new JSeparator();
                s.setForeground(GRAY_LINE);
                s.setBounds(20, ry + 24, 540, 1); info.add(s);
                ry += 40;
            }

            JButton btnRefresh = makeBtn("Refresh Data");
            btnRefresh.setBounds(20, 390, 140, 32);
            btnRefresh.addActionListener(e -> refresh());
            info.add(btnRefresh);

            add(info);
            refresh();
        }

        JLabel addVerticalCard(String title, String val, int x, int y, Color accent) {
            JPanel card = new JPanel(null);
            card.setBounds(x, y, 185, 80);
            card.setBackground(WHITE);
            card.setBorder(BorderFactory.createLineBorder(GRAY_LINE, 1));

            JPanel bar = new JPanel();
            bar.setBackground(accent);
            bar.setBounds(0, 0, 5, 80);
            card.add(bar);

            JLabel lTitle = new JLabel(title);
            lTitle.setFont(new Font("Segoe UI", Font.BOLD, 10));
            lTitle.setForeground(new Color(140, 80, 110));
            lTitle.setBounds(16, 12, 160, 16);
            card.add(lTitle);

            JLabel lVal = new JLabel(val);
            lVal.setFont(new Font("Segoe UI", Font.BOLD, 28));
            lVal.setForeground(accent.darker());
            lVal.setBounds(16, 30, 160, 38);
            card.add(lVal);

            add(card);
            return lVal;
        }

        void refresh() {
            String[] queries = {
                "SELECT COUNT(*) FROM barang;",
                "SELECT COUNT(*) FROM customer;",
                "SELECT COUNT(*) FROM supplier;",
                "SELECT COUNT(*) FROM transaksi;"
            };
            JLabel[] labels = {valBarang, valCustomer, valSupplier, valTransaksi};
            try (Connection k = getConnection()) {
                for (int i = 0; i < queries.length; i++) {
                    try (
                            Statement st = k.createStatement();
                            ResultSet rs = st.executeQuery(queries[i])
                        ) {
                        if (rs.next()) labels[i].setText(rs.getString(1));
                    } catch (SQLException ignored) { labels[i].setText("?"); }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal koneksi DB:\n" + e.getMessage());
            }
        }
    }

    // ══════════════════════════════════════════════════════
    // PANEL BARANG — split: form kiri, tabel kanan
    // ══════════════════════════════════════════════════════
    static class PanelBarang extends JPanel {
        JTextField txtKode, txtNama, txtHargaBeli, txtHargaJual, txtStok, txtIdSupplier;
        DefaultTableModel model;
        JTable table;

        PanelBarang() {
            setLayout(new BorderLayout());
            setBackground(PINK_PALE);

            // ── Sidebar kiri: form ────────────────────────
            JPanel sidebar = new JPanel(null);
            sidebar.setPreferredSize(new Dimension(240, 0));
            sidebar.setBackground(PINK_LITE);
            sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, GRAY_LINE));

            JLabel title = sectionTitle("Data Barang");
            title.setBounds(16, 16, 200, 22);
            sidebar.add(title);

            txtKode       = addFieldVertical(sidebar, "Kode Barang",  16, 50,  205);
            txtNama       = addFieldVertical(sidebar, "Nama Barang",  16, 105, 205);
            txtHargaBeli  = addFieldVertical(sidebar, "Harga Beli",   16, 160, 205);
            txtHargaJual  = addFieldVertical(sidebar, "Harga Jual",   16, 215, 205);
            txtStok       = addFieldVertical(sidebar, "Stok Awal",    16, 270, 205);
            txtIdSupplier = addFieldVertical(sidebar, "ID Supplier",  16, 325, 205);

            JButton btnSimpan = makeBtn("Simpan");
            JButton btnUpdate = makeBtn("Update");
            JButton btnHapus  = makeOutlineBtn("Hapus");
            JButton btnBersih = makeOutlineBtn("Bersihkan");

            btnSimpan.setBounds(16, 380, 97, 30);
            btnUpdate.setBounds(120, 380, 100, 30);
            btnHapus.setBounds(16, 418, 97, 30);
            btnBersih.setBounds(120, 418, 100, 30);

            btnSimpan.addActionListener(e -> simpan());
            btnUpdate.addActionListener(e -> update());
            btnHapus.addActionListener(e -> hapus());
            btnBersih.addActionListener(e -> bersih());

            sidebar.add(btnSimpan); sidebar.add(btnUpdate);
            sidebar.add(btnHapus); sidebar.add(btnBersih);

            add(sidebar, BorderLayout.WEST);

            // ── Panel kanan: tabel ────────────────────────
            JPanel right = new JPanel(new BorderLayout(0, 8));
            right.setBackground(PINK_PALE);
            right.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

            JLabel tblTitle = sectionTitle("Daftar Barang");
            right.add(tblTitle, BorderLayout.NORTH);

            model = makeModel(new String[]{"Kode", "Nama Barang", "Harga Beli", "Harga Jual", "Stok", "Supplier"});
            table = makeStyledTable(model);
            right.add(wrapTable(table), BorderLayout.CENTER);

            add(right, BorderLayout.CENTER);

            table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                    int r = table.getSelectedRow();
                    txtKode.setText(model.getValueAt(r, 0).toString());
                    txtNama.setText(model.getValueAt(r, 1).toString());
                    txtHargaBeli.setText(model.getValueAt(r, 2).toString());
                    txtHargaJual.setText(model.getValueAt(r, 3).toString());
                    txtStok.setText(model.getValueAt(r, 4).toString());
                    txtIdSupplier.setText(model.getValueAt(r, 5).toString());
                }
            });
            tampil();
        }

        void simpan() {
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement(
                     "INSERT INTO barang(kode_barang,nama_barang,harga_beli,harga_jual,stok,id_supplier) VALUES(?,?,?,?,?,?)")) {
                ps.setString(1, txtKode.getText().trim());
                ps.setString(2, txtNama.getText().trim());
                ps.setDouble(3, Double.parseDouble(txtHargaBeli.getText().trim()));
                ps.setDouble(4, Double.parseDouble(txtHargaJual.getText().trim()));
                ps.setInt(5,    Integer.parseInt(txtStok.getText().trim()));
                ps.setString(6, txtIdSupplier.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data barang berhasil disimpan.");
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void update() {
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement(
                     "UPDATE barang SET nama_barang=?,harga_beli=?,harga_jual=?,stok=?,id_supplier=? WHERE kode_barang=?")) {
                ps.setString(1, txtNama.getText().trim());
                ps.setDouble(2, Double.parseDouble(txtHargaBeli.getText().trim()));
                ps.setDouble(3, Double.parseDouble(txtHargaJual.getText().trim()));
                ps.setInt(4,    Integer.parseInt(txtStok.getText().trim()));
                ps.setString(5, txtIdSupplier.getText().trim());
                ps.setString(6, txtKode.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data barang berhasil diupdate.");
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void hapus() {
            if (txtKode.getText().isBlank()) return;
            if (JOptionPane.showConfirmDialog(this, "Yakin hapus barang ini?") != JOptionPane.YES_OPTION) return;
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement("DELETE FROM barang WHERE kode_barang=?")) {
                ps.setString(1, txtKode.getText().trim()); ps.executeUpdate();
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void tampil() {
            model.setRowCount(0);
            try (Connection k = getConnection();
                 Statement st = k.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM barang")) {
                while (rs.next()) model.addRow(new Object[]{
                    rs.getString("kode_barang"), rs.getString("nama_barang"),
                    rs.getDouble("harga_beli"),  rs.getDouble("harga_jual"),
                    rs.getInt("stok"),           rs.getString("id_supplier")});
            } catch (Exception ignored) {}
        }

        void bersih() {
            txtKode.setText(""); txtNama.setText(""); txtHargaBeli.setText("");
            txtHargaJual.setText(""); txtStok.setText(""); txtIdSupplier.setText("");
            table.clearSelection();
        }
    }

    // ══════════════════════════════════════════════════════
    // PANEL CUSTOMER
    // ══════════════════════════════════════════════════════
    static class PanelCustomer extends JPanel {
        JTextField txtId, txtNama, txtAlamat, txtTelp;
        DefaultTableModel model;
        JTable table;

        PanelCustomer() {
            setLayout(new BorderLayout());
            setBackground(PINK_PALE);

            JPanel sidebar = new JPanel(null);
            sidebar.setPreferredSize(new Dimension(240, 0));
            sidebar.setBackground(PINK_LITE);
            sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, GRAY_LINE));

            JLabel title = sectionTitle("Data Customer");
            title.setBounds(16, 16, 200, 22);
            sidebar.add(title);

            txtId     = addFieldVertical(sidebar, "ID Customer", 16, 50,  205);
            txtNama   = addFieldVertical(sidebar, "Nama",        16, 105, 205);
            txtAlamat = addFieldVertical(sidebar, "Alamat",      16, 160, 205);
            txtTelp   = addFieldVertical(sidebar, "Telepon",     16, 215, 205);

            JButton btnSimpan = makeBtn("Simpan");
            JButton btnUpdate = makeBtn("Update");
            JButton btnHapus  = makeOutlineBtn("Hapus");
            JButton btnBersih = makeOutlineBtn("Bersihkan");

            btnSimpan.setBounds(16, 275, 97, 30);
            btnUpdate.setBounds(120, 275, 100, 30);
            btnHapus.setBounds(16, 313, 97, 30);
            btnBersih.setBounds(120, 313, 100, 30);

            btnSimpan.addActionListener(e -> simpan());
            btnUpdate.addActionListener(e -> update());
            btnHapus.addActionListener(e -> hapus());
            btnBersih.addActionListener(e -> bersih());

            sidebar.add(btnSimpan); sidebar.add(btnUpdate);
            sidebar.add(btnHapus); sidebar.add(btnBersih);

            add(sidebar, BorderLayout.WEST);

            JPanel right = new JPanel(new BorderLayout(0, 8));
            right.setBackground(PINK_PALE);
            right.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
            right.add(sectionTitle("Daftar Customer"), BorderLayout.NORTH);

            model = makeModel(new String[]{"ID", "Nama", "Alamat", "Telepon"});
            table = makeStyledTable(model);
            right.add(wrapTable(table), BorderLayout.CENTER);

            add(right, BorderLayout.CENTER);

            table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                    int r = table.getSelectedRow();
                    txtId.setText(model.getValueAt(r, 0).toString());
                    txtNama.setText(model.getValueAt(r, 1).toString());
                    txtAlamat.setText(model.getValueAt(r, 2).toString());
                    txtTelp.setText(model.getValueAt(r, 3).toString());
                }
            });
            tampil();
        }

        void simpan() {
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement(
                     "INSERT INTO customer(id_customer,nama_customer,alamat,telepon) VALUES(?,?,?,?)")) {
                ps.setString(1, txtId.getText().trim()); ps.setString(2, txtNama.getText().trim());
                ps.setString(3, txtAlamat.getText().trim()); ps.setString(4, txtTelp.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Customer berhasil disimpan.");
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void update() {
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement(
                     "UPDATE customer SET nama_customer=?,alamat=?,telepon=? WHERE id_customer=?")) {
                ps.setString(1, txtNama.getText().trim()); ps.setString(2, txtAlamat.getText().trim());
                ps.setString(3, txtTelp.getText().trim()); ps.setString(4, txtId.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Customer berhasil diupdate.");
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void hapus() {
            if (txtId.getText().isBlank()) return;
            if (JOptionPane.showConfirmDialog(this, "Yakin hapus customer ini?") != JOptionPane.YES_OPTION) return;
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement("DELETE FROM customer WHERE id_customer=?")) {
                ps.setString(1, txtId.getText().trim()); ps.executeUpdate();
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void tampil() {
            model.setRowCount(0);
            try (Connection k = getConnection();
                 Statement st = k.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM customer")) {
                while (rs.next()) model.addRow(new Object[]{
                    rs.getString("id_customer"), rs.getString("nama_customer"),
                    rs.getString("alamat"), rs.getString("telepon")});
            } catch (Exception ignored) {}
        }

        void bersih() {
            txtId.setText(""); txtNama.setText(""); txtAlamat.setText(""); txtTelp.setText("");
            table.clearSelection();
        }
    }

    // ══════════════════════════════════════════════════════
    // PANEL SUPPLIER
    // ══════════════════════════════════════════════════════
    static class PanelSupplier extends JPanel {
        JTextField txtId, txtNama, txtAlamat, txtTelp;
        DefaultTableModel model;
        JTable table;

        PanelSupplier() {
            setLayout(new BorderLayout());
            setBackground(PINK_PALE);

            JPanel sidebar = new JPanel(null);
            sidebar.setPreferredSize(new Dimension(240, 0));
            sidebar.setBackground(PINK_LITE);
            sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, GRAY_LINE));

            JLabel title = sectionTitle("Data Supplier");
            title.setBounds(16, 16, 200, 22);
            sidebar.add(title);

            txtId     = addFieldVertical(sidebar, "ID Supplier", 16, 50,  205);
            txtNama   = addFieldVertical(sidebar, "Nama",        16, 105, 205);
            txtAlamat = addFieldVertical(sidebar, "Alamat",      16, 160, 205);
            txtTelp   = addFieldVertical(sidebar, "Telepon",     16, 215, 205);

            JButton btnSimpan = makeBtn("Simpan");
            JButton btnUpdate = makeBtn("Update");
            JButton btnHapus  = makeOutlineBtn("Hapus");
            JButton btnBersih = makeOutlineBtn("Bersihkan");

            btnSimpan.setBounds(16, 275, 97, 30);
            btnUpdate.setBounds(120, 275, 100, 30);
            btnHapus.setBounds(16, 313, 97, 30);
            btnBersih.setBounds(120, 313, 100, 30);

            btnSimpan.addActionListener(e -> simpan());
            btnUpdate.addActionListener(e -> update());
            btnHapus.addActionListener(e -> hapus());
            btnBersih.addActionListener(e -> bersih());

            sidebar.add(btnSimpan); sidebar.add(btnUpdate);
            sidebar.add(btnHapus); sidebar.add(btnBersih);

            add(sidebar, BorderLayout.WEST);

            JPanel right = new JPanel(new BorderLayout(0, 8));
            right.setBackground(PINK_PALE);
            right.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
            right.add(sectionTitle("Daftar Supplier"), BorderLayout.NORTH);

            model = makeModel(new String[]{"ID", "Nama", "Alamat", "Telepon"});
            table = makeStyledTable(model);
            right.add(wrapTable(table), BorderLayout.CENTER);

            add(right, BorderLayout.CENTER);

            table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                    int r = table.getSelectedRow();
                    txtId.setText(model.getValueAt(r, 0).toString());
                    txtNama.setText(model.getValueAt(r, 1).toString());
                    txtAlamat.setText(model.getValueAt(r, 2).toString());
                    txtTelp.setText(model.getValueAt(r, 3).toString());
                }
            });
            tampil();
        }

        void simpan() {
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement(
                     "INSERT INTO supplier(id_supplier,nama_supplier,alamat,telepon) VALUES(?,?,?,?)")) {
                ps.setString(1, txtId.getText().trim()); ps.setString(2, txtNama.getText().trim());
                ps.setString(3, txtAlamat.getText().trim()); ps.setString(4, txtTelp.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Supplier berhasil disimpan.");
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void update() {
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement(
                     "UPDATE supplier SET nama_supplier=?,alamat=?,telepon=? WHERE id_supplier=?")) {
                ps.setString(1, txtNama.getText().trim()); ps.setString(2, txtAlamat.getText().trim());
                ps.setString(3, txtTelp.getText().trim()); ps.setString(4, txtId.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Supplier berhasil diupdate.");
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void hapus() {
            if (txtId.getText().isBlank()) return;
            if (JOptionPane.showConfirmDialog(this, "Yakin hapus supplier ini?") != JOptionPane.YES_OPTION) return;
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement("DELETE FROM supplier WHERE id_supplier=?")) {
                ps.setString(1, txtId.getText().trim()); ps.executeUpdate();
                bersih(); tampil();
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void tampil() {
            model.setRowCount(0);
            try (Connection k = getConnection();
                 Statement st = k.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM supplier")) {
                while (rs.next()) model.addRow(new Object[]{
                    rs.getString("id_supplier"), rs.getString("nama_supplier"),
                    rs.getString("alamat"), rs.getString("telepon")});
            } catch (Exception ignored) {}
        }

        void bersih() {
            txtId.setText(""); txtNama.setText(""); txtAlamat.setText(""); txtTelp.setText("");
            table.clearSelection();
        }
    }

    // ══════════════════════════════════════════════════════
    // PANEL TRANSAKSI — form atas full width, tabel bawah
    // ══════════════════════════════════════════════════════
    static class PanelTransaksi extends JPanel {
        JTextField txtNo, txtTanggal, txtIdCustomer, txtKodeBarang, txtJumlah;
        JLabel lblNamaBarang, lblHarga, lblTotal;
        DefaultTableModel model;
        JTable table;
        double hargaJual = 0;

        PanelTransaksi() {
            setLayout(new BorderLayout());
            setBackground(PINK_PALE);

            // ── Top: form input ───────────────────────────
            JPanel formArea = new JPanel(null);
            formArea.setPreferredSize(new Dimension(0, 195));
            formArea.setBackground(PINK_LITE);
            formArea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GRAY_LINE));

            JLabel title = sectionTitle("Transaksi Penjualan");
            title.setBounds(16, 10, 300, 22);
            formArea.add(title);

            // Kolom 1
            txtNo         = addFieldVertical(formArea, "No. Transaksi", 16,  38, 150);
            txtTanggal    = addFieldVertical(formArea, "Tanggal",       16,  93, 150);
            txtIdCustomer = addFieldVertical(formArea, "ID Customer",   16, 148, 150);
            txtTanggal.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            // Kolom 2
            txtKodeBarang = addFieldVertical(formArea, "Kode Barang", 196, 38, 130);
            txtJumlah     = addFieldVertical(formArea, "Jumlah",      196, 93, 130);

            JButton btnCek = makeBtn("Cek Barang");
            btnCek.setBounds(340, 53, 110, 30);
            formArea.add(btnCek);

            // Info barang
            lblNamaBarang = new JLabel("Nama barang akan tampil di sini");
            lblNamaBarang.setFont(new Font("Segoe UI", Font.ITALIC, 11));
            lblNamaBarang.setForeground(PINK_MID);
            lblNamaBarang.setBounds(196, 148, 280, 18);
            formArea.add(lblNamaBarang);

            lblHarga = new JLabel("Harga : Rp 0");
            lblHarga.setFont(FONT_LBL);
            lblHarga.setForeground(TEXT_DARK);
            lblHarga.setBounds(196, 168, 200, 18);
            formArea.add(lblHarga);

            // Total — pojok kanan
            JPanel totalBox = new JPanel(null);
            totalBox.setBounds(490, 38, 340, 80);
            totalBox.setBackground(WHITE);
            totalBox.setBorder(BorderFactory.createLineBorder(GRAY_LINE, 1));

            JLabel lblTotalText = new JLabel("Total Pembayaran");
            lblTotalText.setFont(new Font("Segoe UI", Font.BOLD, 10));
            lblTotalText.setForeground(PINK_MID);
            lblTotalText.setBounds(14, 10, 200, 16);
            totalBox.add(lblTotalText);

            lblTotal = new JLabel("Rp 0");
            lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 22));
            lblTotal.setForeground(PINK_DEEP);
            lblTotal.setBounds(14, 30, 310, 34);
            totalBox.add(lblTotal);
            formArea.add(totalBox);

            // Tombol
            JButton btnSimpan = makeBtn("Simpan Transaksi");
            JButton btnBersih = makeOutlineBtn("Bersihkan");
            btnSimpan.setBounds(490, 130, 160, 32);
            btnBersih.setBounds(660, 130, 110, 32);
            formArea.add(btnSimpan); formArea.add(btnBersih);

            btnCek.addActionListener(e -> cekBarang());
            txtJumlah.addActionListener(e -> hitungTotal());
            btnSimpan.addActionListener(e -> simpan());
            btnBersih.addActionListener(e -> bersih());

            add(formArea, BorderLayout.NORTH);

            // ── Bottom: tabel ─────────────────────────────
            JPanel bottom = new JPanel(new BorderLayout(0, 6));
            bottom.setBackground(PINK_PALE);
            bottom.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
            bottom.add(sectionTitle("Riwayat Transaksi"), BorderLayout.NORTH);

            model = makeModel(new String[]{"No. Transaksi", "Tanggal", "ID Customer", "Kode Barang", "Jumlah", "Total Harga"});
            table = makeStyledTable(model);
            bottom.add(wrapTable(table), BorderLayout.CENTER);

            add(bottom, BorderLayout.CENTER);
            tampil();
        }

        void cekBarang() {
            String kode = txtKodeBarang.getText().trim();
            if (kode.isBlank()) return;
            try (Connection k = getConnection();
                 PreparedStatement ps = k.prepareStatement("SELECT * FROM barang WHERE kode_barang=?")) {
                ps.setString(1, kode);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    lblNamaBarang.setText(rs.getString("nama_barang"));
                    hargaJual = rs.getDouble("harga_jual");
                    lblHarga.setText("Harga : Rp " + String.format("%,.0f", hargaJual));
                    hitungTotal();
                } else {
                    lblNamaBarang.setText("Barang tidak ditemukan!");
                    hargaJual = 0;
                }
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void hitungTotal() {
            try {
                int qty = Integer.parseInt(txtJumlah.getText().trim());
                lblTotal.setText("Rp " + String.format("%,.0f", qty * hargaJual));
            } catch (NumberFormatException ignored) {}
        }

        void simpan() {
            try {
                int qty = Integer.parseInt(txtJumlah.getText().trim());
                double total = qty * hargaJual;
                try (Connection k = getConnection()) {
                    PreparedStatement cek = k.prepareStatement("SELECT stok FROM barang WHERE kode_barang=?");
                    cek.setString(1, txtKodeBarang.getText().trim());
                    ResultSet rs = cek.executeQuery();
                    if (rs.next() && rs.getInt("stok") < qty) {
                        JOptionPane.showMessageDialog(this, "Stok tidak cukup! Tersedia: " + rs.getInt("stok"));
                        return;
                    }
                    PreparedStatement ps = k.prepareStatement(
                        "INSERT INTO transaksi(no_transaksi,tanggal,id_customer,kode_barang,jumlah,total_harga) VALUES(?,?,?,?,?,?)");
                    ps.setString(1, txtNo.getText().trim()); ps.setString(2, txtTanggal.getText().trim());
                    ps.setString(3, txtIdCustomer.getText().trim()); ps.setString(4, txtKodeBarang.getText().trim());
                    ps.setInt(5, qty); ps.setDouble(6, total);
                    ps.executeUpdate();
                    PreparedStatement upd = k.prepareStatement("UPDATE barang SET stok=stok-? WHERE kode_barang=?");
                    upd.setInt(1, qty); upd.setString(2, txtKodeBarang.getText().trim());
                    upd.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan.");
                    bersih(); tampil();
                }
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
        }

        void tampil() {
            model.setRowCount(0);
            try (Connection k = getConnection();
                 Statement st = k.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM transaksi ORDER BY tanggal DESC")) {
                while (rs.next()) model.addRow(new Object[]{
                    rs.getString("no_transaksi"), rs.getString("tanggal"),
                    rs.getString("id_customer"),  rs.getString("kode_barang"),
                    rs.getInt("jumlah"), "Rp " + String.format("%,.0f", rs.getDouble("total_harga"))});
            } catch (Exception ignored) {}
        }

        void bersih() {
            txtNo.setText(""); txtIdCustomer.setText(""); txtKodeBarang.setText(""); txtJumlah.setText("");
            txtTanggal.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            lblNamaBarang.setText("Nama barang akan tampil di sini");
            lblHarga.setText("Harga : Rp 0");
            lblTotal.setText("Rp 0");
            hargaJual = 0; table.clearSelection();
        }
    }

    // ══════════════════════════════════════════════════════
    // PANEL INVENTORY — tabel penuh dengan color coding
    // ══════════════════════════════════════════════════════
    static class PanelInventory extends JPanel {
        DefaultTableModel model;

        PanelInventory() {
            setLayout(new BorderLayout(0, 0));
            setBackground(PINK_PALE);

            // Top bar
            JPanel topBar = new JPanel(null);
            topBar.setPreferredSize(new Dimension(0, 52));
            topBar.setBackground(PINK_PALE);
            topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GRAY_LINE));

            JLabel title = sectionTitle("Status Inventory Barang");
            title.setBounds(14, 14, 300, 22);
            topBar.add(title);

            JButton btnRefresh = makeBtn("Refresh");
            btnRefresh.setBounds(700, 12, 100, 28);
            btnRefresh.addActionListener(e -> tampil());
            topBar.add(btnRefresh);

            // Badge keterangan
            addBadge(topBar, "Normal",  new Color(200, 240, 210), new Color(60, 140, 80),  430);
            addBadge(topBar, "Menipis", new Color(255, 240, 190), new Color(180, 120, 0),  515);
            addBadge(topBar, "Kritis",  new Color(255, 200, 210), new Color(180, 50, 70),  595);

            add(topBar, BorderLayout.NORTH);

            // Tabel
            model = makeModel(new String[]{"Kode", "Nama Barang", "Harga Beli", "Harga Jual", "Stok", "Status"});
            JTable table = makeStyledTable(model);

            table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable t, Object val,
                        boolean sel, boolean foc, int row, int col) {
                    super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                    if (!sel) {
                        try {
                            int s = Integer.parseInt(t.getModel().getValueAt(row, 4).toString());
                            if (s <= 5)       { setBackground(new Color(255, 200, 210)); setForeground(new Color(140, 30, 50)); }
                            else if (s <= 10) { setBackground(new Color(255, 240, 190)); setForeground(new Color(140, 90, 0)); }
                            else              { setBackground(WHITE); setForeground(TEXT_DARK); }
                        } catch (Exception ex) { setBackground(WHITE); setForeground(TEXT_DARK); }
                    }
                    return this;
                }
            });

            JPanel tableWrap = new JPanel(new BorderLayout());
            tableWrap.setBackground(PINK_PALE);
            tableWrap.setBorder(BorderFactory.createEmptyBorder(10, 14, 14, 14));
            tableWrap.add(wrapTable(table), BorderLayout.CENTER);
            add(tableWrap, BorderLayout.CENTER);

            tampil();
        }

        void addBadge(JPanel p, String text, Color bg, Color fg, int x) {
            JLabel b = new JLabel(text, SwingConstants.CENTER);
            b.setFont(new Font("Segoe UI", Font.BOLD, 10));
            b.setForeground(fg);
            b.setBackground(bg);
            b.setOpaque(true);
            b.setBounds(x, 16, 72, 20);
            b.setBorder(BorderFactory.createLineBorder(fg, 1));
            p.add(b);
        }

        void tampil() {
            model.setRowCount(0);
            try (Connection k = getConnection();
                 Statement st = k.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM barang ORDER BY stok ASC")) {
                while (rs.next()) {
                    int stok = rs.getInt("stok");
                    String status = stok <= 5 ? "Kritis" : stok <= 10 ? "Menipis" : "Normal";
                    model.addRow(new Object[]{
                        rs.getString("kode_barang"), rs.getString("nama_barang"),
                        "Rp " + String.format("%,.0f", rs.getDouble("harga_beli")),
                        "Rp " + String.format("%,.0f", rs.getDouble("harga_jual")),
                        stok, status});
                }
            } catch (Exception ignored) {}
        }
    }

    // ══════════════════════════════════════════════════════
    // PANEL LAPORAN — card horizontal dengan deskripsi
    // ══════════════════════════════════════════════════════
    static class PanelLaporan extends JPanel {
        PanelLaporan() {
            setLayout(null);
            setBackground(PINK_PALE);

            JLabel title = sectionTitle("Cetak Laporan");
            title.setBounds(20, 20, 300, 24);
            add(title);

            JLabel sub = new JLabel("Pilih laporan yang ingin dicetak");
            sub.setFont(FONT_SMALL);
            sub.setForeground(PINK_MID);
            sub.setBounds(20, 46, 300, 16);
            add(sub);

            addCard("Laporan Transaksi",
                    "Menampilkan seluruh riwayat transaksi penjualan,",
                    "termasuk data customer, barang, jumlah, dan total.",
                    "laporan/LaporanTransaksiDella.jrxml", "Laporan Transaksi", 20, 80);

            addCard("Laporan Inventory",
                    "Menampilkan status stok seluruh barang beserta",
                    "informasi harga beli, harga jual, dan supplier.",
                    "laporan/LaporanInventoryDella.jrxml", "Laporan Inventory", 20, 240);

            JLabel note = new JLabel("File .jrxml harus ada di folder src/laporan/ dalam project NetBeans.");
            note.setFont(FONT_SMALL);
            note.setForeground(PINK_MID);
            note.setBounds(20, 400, 600, 16);
            add(note);
        }

        void addCard(String judul, String desc1, String desc2, String jrxmlPath, String param, int x, int y) {
            JPanel card = new JPanel(null);
            card.setBounds(x, y, 700, 140);
            card.setBackground(WHITE);
            card.setBorder(BorderFactory.createLineBorder(GRAY_LINE, 1));

            JPanel leftBar = new JPanel();
            leftBar.setBackground(PINK_DEEP);
            leftBar.setBounds(0, 0, 5, 140);
            card.add(leftBar);

            JLabel lJudul = new JLabel(judul);
            lJudul.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lJudul.setForeground(PINK_DEEP);
            lJudul.setBounds(20, 18, 400, 22);
            card.add(lJudul);

            JLabel lDesc1 = new JLabel(desc1);
            lDesc1.setFont(FONT_LBL);
            lDesc1.setForeground(new Color(140, 100, 120));
            lDesc1.setBounds(20, 45, 500, 18);
            card.add(lDesc1);

            JLabel lDesc2 = new JLabel(desc2);
            lDesc2.setFont(FONT_LBL);
            lDesc2.setForeground(new Color(140, 100, 120));
            lDesc2.setBounds(20, 63, 500, 18);
            card.add(lDesc2);

            JButton btn = makeBtn("Cetak Laporan");
            btn.setBounds(565, 50, 120, 36);
            btn.addActionListener(e -> cetak(jrxmlPath, param));
            card.add(btn);

            add(card);
        }

        void cetak(String jrxmlRelPath, String judul) {
            try {
                String path = new java.io.File(".").getAbsolutePath()
                        + java.io.File.separator + "src"
                        + java.io.File.separator + jrxmlRelPath.replace("/", java.io.File.separator);
                net.sf.jasperreports.engine.JasperReport jr =
                    net.sf.jasperreports.engine.JasperCompileManager.compileReport(path);
                java.util.Map<String, Object> params = new java.util.HashMap<>();
                params.put("judul", judul);
                try (Connection k = getConnection()) {
                    net.sf.jasperreports.engine.JasperPrint jp =
                        net.sf.jasperreports.engine.JasperFillManager.fillReport(jr, params, k);
                    net.sf.jasperreports.view.JasperViewer.viewReport(jp, false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal cetak laporan:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
