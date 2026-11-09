package com.mycompany.modul.assessment.view;

import com.mycompany.modul.assessment.model.Barang;
import com.mycompany.modul.assessment.repository.CrudRepository;
import com.mycompany.modul.assessment.repository.BarangRepository;
import com.mycompany.modul.assessment.repository.BarangDatabaseRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormStok extends JFrame {
    
    private CrudRepository repository; 
    
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtId, txtNama, txtStok;

    public FormStok() {
        this.repository = new BarangDatabaseRepository();
        
        setTitle("Kelola Stok (SOLID Edition)");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("ID Barang:"));
        txtId = new JTextField();
        panelInput.add(txtId);

        panelInput.add(new JLabel("Nama Barang:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Stok:"));
        txtStok = new JTextField();
        panelInput.add(txtStok);

        JButton btnTambah = new JButton("Tambah");
        panelInput.add(new JLabel()); 
        panelInput.add(btnTambah);

        add(panelInput, BorderLayout.NORTH);

        String[] kolom = {"ID", "Nama", "Stok"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnHapus = new JButton("Hapus");
        panelBawah.add(btnHapus);
        add(panelBawah, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> prosesTambah());
        btnHapus.addActionListener(e -> prosesHapus());
    }

    private void prosesTambah() {
        try {
            String id = txtId.getText();
            String nama = txtNama.getText();
            int stok = Integer.parseInt(txtStok.getText());

            if (id.isEmpty() || nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
                return;
            }

            repository.simpan(new Barang(id, nama, stok));
            
            refreshTabel();
            
            txtId.setText("");
            txtNama.setText("");
            txtStok.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka!");
        }
    }

    private void prosesHapus() {
        int baris = table.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel dulu!");
            return;
        }
        
        repository.hapus(baris);
        refreshTabel();
    }

    private void refreshTabel() {
        tableModel.setRowCount(0); 
        List<Barang> list = repository.ambilSemua(); 
        for (Barang b : list) {
            tableModel.addRow(new Object[]{b.getId(), b.getNama(), b.getStok()});
        }
    }
}