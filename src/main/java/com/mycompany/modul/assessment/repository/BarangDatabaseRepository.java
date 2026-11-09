package com.mycompany.modul.assessment.repository;

import com.mycompany.modul.assessment.model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDatabaseRepository implements CrudRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/db_stok";
    private static final String USER = "root";
    private static final String PASSWORD = "nickxmn666"; 

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void simpan(Barang barang) {
        String sql = "INSERT INTO barang (id, nama, stok) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, barang.getId());
            pstmt.setString(2, barang.getNama());
            pstmt.setInt(3, barang.getStok());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Barang> ambilSemua() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Barang b = new Barang(
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getInt("stok")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void hapus(int index) {
        List<Barang> list = ambilSemua();
        if (index >= 0 && index < list.size()) {
            String idYangMauDihapus = list.get(index).getId();
            
            String sql = "DELETE FROM barang WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, idYangMauDihapus);
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}