/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.appclinica.Presentation;

import com.mycompany.appclinica.Services.CitaService;
import com.mycompany.appclinica.Services.MedicoService;
import com.mycompany.appclinica.Services.PacienteService;
import javax.swing.JOptionPane;

/**
 *
 * Ventana principal de la aplicación clínica.
 * Provee el entorno de trabajo con menú y escritorio para la gestión
 * de pacientes, médicos y citas.
 * Controla las acciones del usuario para abrir las distintas vistas y servicios.
 * 
 * @author Juan Moscoso y Slleider Rojas
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    // Servicios para gestionar pacientes, médicos y citas

    private PacienteService pacienteService = new PacienteService();
    private MedicoService medicoService = new MedicoService();
    private CitaService citaService = new CitaService(pacienteService, medicoService);
    /**
     * Crea una nueva instancia de VentanaPrincipal.
     * Inicializa los componentes gráficos y la configuración inicial.
     */
    public VentanaPrincipal() {
        initComponents();
    }

    /**
     * Método generado automáticamente para inicializar los componentes gráficos de la ventana.
     * Se recomienda no modificar este método manualmente.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        panelStatus = new javax.swing.JPanel();
        labelStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPrincipal = new javax.swing.JMenu();
        menuArchivo = new javax.swing.JMenu();
        menuItemSalir = new javax.swing.JMenuItem();
        menuItemAcercaDe = new javax.swing.JMenuItem();
        menuPaciente = new javax.swing.JMenu();
        menuItemGestionPaciente = new javax.swing.JMenuItem();
        menuMedico = new javax.swing.JMenu();
        menuItemGestionMedico = new javax.swing.JMenuItem();
        menuCita = new javax.swing.JMenu();
        menuItemGestionCita = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );

        panelStatus.setBackground(new java.awt.Color(30, 107, 117));

        labelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelStatus.setForeground(new java.awt.Color(255, 255, 255));
        labelStatus.setText("@2025");

        javax.swing.GroupLayout panelStatusLayout = new javax.swing.GroupLayout(panelStatus);
        panelStatus.setLayout(panelStatusLayout);
        panelStatusLayout.setHorizontalGroup(
            panelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStatusLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        panelStatusLayout.setVerticalGroup(
            panelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatusLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelStatus)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(30, 107, 117));

        menuPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        menuPrincipal.setText("Menu");

        menuArchivo.setText("Archivo");

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemSalir);

        menuItemAcercaDe.setText("Acerca de");
        menuItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAcercaDeActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemAcercaDe);

        menuPrincipal.add(menuArchivo);

        menuPaciente.setText("Paciente");

        menuItemGestionPaciente.setText("Gestión de Pacientes");
        menuItemGestionPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGestionPacienteActionPerformed(evt);
            }
        });
        menuPaciente.add(menuItemGestionPaciente);

        menuPrincipal.add(menuPaciente);

        menuMedico.setText("Médico");

        menuItemGestionMedico.setText("Gestión de Médicos");
        menuItemGestionMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGestionMedicoActionPerformed(evt);
            }
        });
        menuMedico.add(menuItemGestionMedico);

        menuPrincipal.add(menuMedico);

        menuCita.setText("Cita");

        menuItemGestionCita.setText("Gestión Cita");
        menuItemGestionCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGestionCitaActionPerformed(evt);
            }
        });
        menuCita.add(menuItemGestionCita);

        menuPrincipal.add(menuCita);

        jMenuBar1.add(menuPrincipal);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void abrirListaPaciente() {
        
}
    /**
    * Acción que se ejecuta cuando el usuario selecciona "Salir" en el menú.
    * Termina la aplicación cerrando la ventana principal.
    * 
    * @param evt evento de acción
    */
    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSalirActionPerformed
    /**
     * Acción que se ejecuta cuando el usuario selecciona "Acerca de" en el menú.
     * Muestra un diálogo con información del software.
     * 
     * @param evt evento de acción
     */
    private void menuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercaDeActionPerformed
        AcercaDeDialog dialog = new AcercaDeDialog(this, true); // "this" es el JFrame como owner y "true" es modalidad
        dialog.setLocationRelativeTo(this); // Centrar sobre la ventana principal
        dialog.setVisible(true);
    }//GEN-LAST:event_menuItemAcercaDeActionPerformed
    /**
     * Acción para abrir el formulario de gestión de pacientes dentro del escritorio.
     * Centra la ventana interna en el área del escritorio.
     * 
     * @param evt evento de acción
     */
    private ListaPaciente listaPacienteFrame = null;
    private void menuItemGestionPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGestionPacienteActionPerformed
        if (listaPacienteFrame != null && listaPacienteFrame.isDisplayable()) {
            JOptionPane.showMessageDialog(
                this,
                "Ya existe una gestión de pacientes abierta. Se traerá al frente.",
                "Gestión de Pacientes", 
                JOptionPane.INFORMATION_MESSAGE
            );
            try {
                listaPacienteFrame.toFront();
                listaPacienteFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {
                // Manejo opcional
            }
            return;
        }
        listaPacienteFrame = new ListaPaciente(pacienteService, medicoService, citaService);
        desktopPane.add(listaPacienteFrame);
        listaPacienteFrame.setVisible(true);

        int x = (desktopPane.getWidth() - listaPacienteFrame.getWidth()) / 2;
        int y = (desktopPane.getHeight() - listaPacienteFrame.getHeight()) / 2;
        listaPacienteFrame.setLocation(x, y);
    }//GEN-LAST:event_menuItemGestionPacienteActionPerformed
    
    private ListaMedico listaMedicoFrame = null;
    private void menuItemGestionMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGestionMedicoActionPerformed
        if (listaMedicoFrame != null && listaMedicoFrame.isDisplayable()) {
            JOptionPane.showMessageDialog(
                this,
                "Ya existe una gestión de médicos abierta. Se traerá al frente.",
                "Gestión de Médicos", 
                JOptionPane.INFORMATION_MESSAGE
            );
            try {
                listaMedicoFrame.toFront();
                listaMedicoFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {
                // Manejo opcional
            }
            return;
        }
        listaMedicoFrame = new ListaMedico(medicoService);
        desktopPane.add(listaMedicoFrame);
        listaMedicoFrame.setVisible(true);

        int x = (desktopPane.getWidth() - listaMedicoFrame.getWidth()) / 2;
        int y = (desktopPane.getHeight() - listaMedicoFrame.getHeight()) / 2;
        listaMedicoFrame.setLocation(x, y);
    }//GEN-LAST:event_menuItemGestionMedicoActionPerformed
    /**
     * Acción para abrir el formulario de gestión de citas dentro del escritorio.
     * Centra la ventana interna en el área del escritorio.
     * 
     * @param evt evento de acción
     */
    
    private ListaCitas listaCitasFrame = null;
    private void menuItemGestionCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGestionCitaActionPerformed
        if (listaCitasFrame != null && listaCitasFrame.isDisplayable()) {
            JOptionPane.showMessageDialog(
                this,
                "Ya existe una gestión de citas abierta. Se traerá al frente.",
                "Gestión de Citas", 
                JOptionPane.INFORMATION_MESSAGE
            );
            try {
                listaCitasFrame.toFront();
                listaCitasFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {
                // Manejo opcional
            }
            return;
        }
        listaCitasFrame = new ListaCitas(citaService, medicoService, pacienteService);
        desktopPane.add(listaCitasFrame);
        listaCitasFrame.setVisible(true);

        int x = (desktopPane.getWidth() - listaCitasFrame.getWidth()) / 2;
        int y = (desktopPane.getHeight() - listaCitasFrame.getHeight()) / 2;
        listaCitasFrame.setLocation(x, y);
    }//GEN-LAST:event_menuItemGestionCitaActionPerformed

    /**
     * Método principal que lanza la aplicación mostrando la ventana principal.
     * Configura el look and feel y muestra la ventana principal.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String args[]) {
        
        // Código auto generado para establecer look and feel Nimbus o defecto
        // e invocar la ventana principal.
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuCita;
    private javax.swing.JMenuItem menuItemAcercaDe;
    private javax.swing.JMenuItem menuItemGestionCita;
    private javax.swing.JMenuItem menuItemGestionMedico;
    private javax.swing.JMenuItem menuItemGestionPaciente;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuMedico;
    private javax.swing.JMenu menuPaciente;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JPanel panelStatus;
    // End of variables declaration//GEN-END:variables
}
