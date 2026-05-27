package service;

public class NilaiService {

    public double hitungRataRata(double tugas,
                                 double uts,
                                 double uas){

        return (tugas + uts + uas)/3;
    }

    public String statusKelulusan(double nilai){

        if(nilai >= 75){
            return "Lulus";
        }

        return "Tidak Lulus";
    }

}