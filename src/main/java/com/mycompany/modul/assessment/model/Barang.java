package com.mycompany.modul.assessment.model;

public class Barang {
    private String id;
    private String nama;
    private int stok;

    public Barang(String id, String nama, int stok) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}