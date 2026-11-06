package com.mycompany.modul.assessment;

import com.mycompany.modul.assessment.view.FormStok;

public class Modul8Assessment {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Gagal set Look and Feel: " + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormStok().setVisible(true);
            }
        });
    }
}