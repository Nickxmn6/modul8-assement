package com.mycompany.modul.assessment.repository;

import com.mycompany.modul.assessment.model.Barang;
import java.util.List;

public interface CrudRepository {
    void simpan(Barang barang);
    List<Barang> ambilSemua();
    void hapus(int index);
}
