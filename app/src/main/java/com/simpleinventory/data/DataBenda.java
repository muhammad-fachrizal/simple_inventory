package com.simpleinventory.data;

import android.content.SharedPreferences;

import com.simpleinventory.activity.AlgoritmeRSA;
import com.simpleinventory.activity.MainActivity;


public class DataBenda {
    MainActivity MA = new MainActivity();
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private String id_benda, nama_barang, stok, tahun;

    public DataBenda() {
    }


    public String getId_Benda() {
        return id_benda;
    }

    public void setId_Benda(String id_benda) {
        this.id_benda = id_benda;
    }

    public String getNama_Barang() {
        //nama_barang = AlgoritmeRSA.Dekripsi(nama_barang)
        return nama_barang;
    }

    public void setNama_Barang(String nama_barang) {
        //String strD = sharedPreferences.getString("stringD", "");
        //String strN = sharedPreferences.getString("stringN", "");
        //AlgoritmeRSA.Dekripsi(nama_barang, strD, strN)

        this.nama_barang = AlgoritmeRSA.Dekripsi(nama_barang, MainActivity.publicStrD, MainActivity.publicStrN);
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = AlgoritmeRSA.Dekripsi(stok, MainActivity.publicStrD, MainActivity.publicStrN);
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = AlgoritmeRSA.Dekripsi(tahun, MainActivity.publicStrD, MainActivity.publicStrN);
    }



}