package cl.duoc.util;


import cl.duoc.ui.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->{
            new VentanaPrincipal();
        });
    }
}