package com.mycompany.modul.assessment;

import com.mycompany.modul.assessment.view.FormStok;

public class Modul8Assessment {

    public static void main(String[] args) {
        // Biar tampilan GUI-nya ngikutin gaya Windows (opsional tapi bagus)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Gagal set Look and Feel: " + e.getMessage());
        }

        // Panggil FormStok
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormStok().setVisible(true);
            }
        });
    }
}