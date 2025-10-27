/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.appclinica;
import com.mycompany.appclinica.Presentation.VentanaPrincipal;

/**
 *
 * @author Juan
 */
public class AppClinica {

    public static void main(String[] args) {
        // Lanza la ventana principal del proyecto
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
        });
    }
}
    

