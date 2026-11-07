package com.mycompany.modul.assessment.repository;

import com.mycompany.modul.assessment.model.Barang;
import java.util.ArrayList;
import java.util.List;

public class BarangRepository implements CrudRepository {
    private List<Barang> daftarBarang = new ArrayList<>();

    @Override
    public void simpan(Barang barang) {
        daftarBarang.add(barang);
    }

    @Override
    public List<Barang> ambilSemua() {
        return daftarBarang;
    }

    @Override
    public void hapus(int index) {
        if (index >= 0 && index < daftarBarang.size()) {
            daftarBarang.remove(index);
        }
    }
}