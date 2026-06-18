package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    Connection conn;

    public Connection getKoneksi() {

        try {

            String url = "jdbc:mysql://localhost/rentcar";
            String user = "root";
            String pass = "";

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url,user,pass);

        } catch(Exception e){
            System.out.println(e);
        }

        return conn;
    }
}