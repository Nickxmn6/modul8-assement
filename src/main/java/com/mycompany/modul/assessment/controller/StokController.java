package com.mycompany.modul.assessment.controller;

import com.mycompany.modul.assessment.model.Barang;
import java.util.ArrayList;
import java.util.List;

public class StokController {
    private List<Barang> listBarang = new ArrayList<>();

    public void tambahBarang(String id, String nama, int stok) {
        listBarang.add(new Barang(id, nama, stok));
    }

    public List<Barang> getAllBarang() {
        return listBarang;
    }

    public void hapusBarang(int index) {
        if (index >= 0 && index < listBarang.size()) {
            listBarang.remove(index);
        }
    }
}