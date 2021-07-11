package com.example.hotel.database;

import java.io.Serializable;

public class Pesanan implements Serializable {
    //line 7-12 mendeklarasi variabel
    String kode;
    String nama;
    String nomor_telepon;
    String lama_menginap;
    String Jenis_Kamar;
    String Harga;
    //konstruktor kosong
    public Pesanan() {
    }
    //konstruktor dengan semua parameter
    public Pesanan(String nama, String nomor_telepon, String lama_menginap, String jenis_Kamar, String harga) {
        this.nama = nama;
        this.nomor_telepon = nomor_telepon;
        this.lama_menginap = lama_menginap;
        Jenis_Kamar = jenis_Kamar;
        Harga = harga;
    }
    //line 25-69 berisi getter dan setter dari variabel-variabel yang sudah dibuat
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public String getLama_menginap() {
        return lama_menginap;
    }

    public void setLama_menginap(String lama_menginap) {
        this.lama_menginap = lama_menginap;
    }

    public String getJenis_Kamar() {
        return Jenis_Kamar;
    }

    public void setJenis_Kamar(String jenis_Kamar) {
        Jenis_Kamar = jenis_Kamar;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    @Override
    //tostring dari variabel yang telah dibuat
    public String toString() {
        return "Pesanan{" +
                "nama='" + nama + '\'' +
                ", nomor_telepon='" + nomor_telepon + '\'' +
                ", lama_menginap='" + lama_menginap + '\'' +
                ", Jenis_Kamar='" + Jenis_Kamar + '\'' +
                ", Harga='" + Harga + '\'' +
                '}';
    }
}
