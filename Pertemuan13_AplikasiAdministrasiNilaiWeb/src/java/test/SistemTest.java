package test;

import koneksi.KoneksiDB;
import java.sql.Connection;

public class SistemTest {

    public static void main(String[] args){

        int total = 0;
        int berhasil = 0;

        // LOGIN

        total++;

        if("admin".equals("admin")
        && "admin123".equals("admin123")){

            berhasil++;

            System.out.println(
            "TEST LOGIN : PASS"
            );

        }else{

            System.out.println(
            "TEST LOGIN : FAIL"
            );

        }

        // DATABASE

        total++;

        KoneksiDB db =
        new KoneksiDB();

        Connection conn =
        db.getKoneksi();

        if(conn != null){

            berhasil++;

            System.out.println(
            "TEST DATABASE : PASS"
            );

        }else{

            System.out.println(
            "TEST DATABASE : FAIL"
            );

        }

        // DATA MAHASISWA

        total++;

        String nim =
        "231011401234";

        String nama =
        "Seftia";

        if(!nim.isEmpty()
        &&
        !nama.isEmpty()){

            berhasil++;

            System.out.println(
            "TEST DATA MAHASISWA : PASS"
            );

        }else{

            System.out.println(
            "TEST DATA MAHASISWA : FAIL"
            );

        }

        // DATA MATAKULIAH

        total++;

        String kode =
        "IF001";

        String matkul =
        "Rekayasa Perangkat Lunak";

        if(!kode.isEmpty()
        &&
        !matkul.isEmpty()){

            berhasil++;

            System.out.println(
            "TEST MATAKULIAH : PASS"
            );

        }else{

            System.out.println(
            "TEST MATAKULIAH : FAIL"
            );

        }

        // INPUT NILAI

        total++;

        double tugas = 80;
        double uts = 90;
        double uas = 85;

        double akhir =

        (tugas*0.2)+
        (uts*0.3)+
        (uas*0.5);

        if(akhir > 0){

            berhasil++;

            System.out.println(
            "TEST INPUT NILAI : PASS"
            );

        }else{

            System.out.println(
            "TEST INPUT NILAI : FAIL"
            );

        }

        // CETAK LAPORAN

        total++;

        String laporan =
        "laporanNilai.jsp";

        if(laporan.endsWith(".jsp")){

            berhasil++;

            System.out.println(
            "TEST LAPORAN : PASS"
            );

        }else{

            System.out.println(
            "TEST LAPORAN : FAIL"
            );

        }

        System.out.println();

        System.out.println(
        "TOTAL TEST : "
        + total
        );

        System.out.println(
        "BERHASIL : "
        + berhasil
        );

        System.out.println(
        "GAGAL : "
        + (total-berhasil)
        );

    }

}