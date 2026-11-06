package com.mycompany.modul.assessment.view;

import com.mycompany.modul.assessment.controller.StokController;
import com.mycompany.modul.assessment.model.Barang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormStok extends JFrame {
    
    private StokController controller = new StokController();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtId, txtNama, txtStok;
    private JButton btnTambah, btnHapus;

    public FormStok() {
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

        btnTambah = new JButton("Tambah Barang");
        panelInput.add(new JLabel());
        panelInput.add(btnTambah);

        add(panelInput, BorderLayout.NORTH);

        String[] kolom = {"ID", "Nama Barang", "Stok"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnHapus = new JButton("Hapus Barang Terpilih");
        panelBawah.add(btnHapus);
        add(panelBawah, BorderLayout.SOUTH);

        
        btnTambah.addActionListener(e -> {
            try {
                String id = txtId.getText();
                String nama = txtNama.getText();
                int stok = Integer.parseInt(txtStok.getText());

                if (id.isEmpty() || nama.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "ID dan Nama tidak boleh kosong!");
                    return;
                }

                controller.tambahBarang(id, nama, stok);
                
                refreshTabel();
                
                txtId.setText("");
                txtNama.setText("");
                txtStok.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stok harus berupa angka!");
            }
        });

        btnHapus.addActionListener(e -> {
            int barisTerpilih = table.getSelectedRow();
            if (barisTerpilih == -1) {
                JOptionPane.showMessageDialog(this, "Pilih dulu barang yang mau dihapus di tabel!");
                return;
            }
            
            controller.hapusBarang(barisTerpilih);
            
            refreshTabel();
        });
    }

    private void refreshTabel() {
        tableModel.setRowCount(0); 
        List<Barang> list = controller.getAllBarang();
        for (Barang b : list) {
            Object[] row = {b.getId(), b.getNama(), b.getStok()};
            tableModel.addRow(row);
        }
    }
}