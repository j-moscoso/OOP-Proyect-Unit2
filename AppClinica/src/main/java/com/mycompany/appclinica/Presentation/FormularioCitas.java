/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.appclinica.Presentation;

import com.mycompany.appclinica.Models.Cita;
import com.mycompany.appclinica.Models.EnumEspecialidad;
import com.mycompany.appclinica.Models.EnumEstadoCita;
import com.mycompany.appclinica.Models.Medico;
import com.mycompany.appclinica.Models.Paciente;
import com.mycompany.appclinica.Services.CitaService;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import com.mycompany.appclinica.Services.MedicoService;
import com.mycompany.appclinica.Services.PacienteService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 * Formulario para crear o editar citas médicas dentro de la aplicación. Permite
 * seleccionar paciente mediante cédula, médico según especialidad, fecha, hora,
 * motivo y estado de la cita. Usa servicios para gestión de pacientes, médicos
 * y citas para validar y guardar datos.
 *
 * @author Juan Moscoso y Slleider Rojas
 */

public class FormularioCitas extends javax.swing.JInternalFrame {

    private MedicoService medicoService;
    private CitaService citaService;
    private PacienteService pacienteService;
    private Cita cita;

    /**
     * Constructor que inicializa el formulario con los servicios y la cita (si
     * es edición). Configura combos de especialidades y estados, y si hay cita
     * rellena los campos para edición.
     *
     * @param pacienteService Servicio para gestión de pacientes
     * @param medicoService Servicio para gestión de médicos
     * @param citaService Servicio para gestión de citas
     * @param cita Cita para editar o null para crear nueva
     */
    public FormularioCitas(PacienteService pacienteService, MedicoService medicoService, CitaService citaService, Cita cita) {
        this.citaService = citaService;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.cita = cita;
        initComponents();
        cargarEstados();
        cargarEspecialidades();
        if (this.cita != null) {
            llenarCamposEditar();
        }
    }

    /**
     * Llena los campos del formulario con los datos de la cita para edición.
     * Establece editable en falso el campo de cédula para evitar modificación.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelPaciente = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        labelMedico = new javax.swing.JLabel();
        comboBoxMedico = new javax.swing.JComboBox<>();
        labelEspecialidad = new javax.swing.JLabel();
        comboBoxEspecialidades = new javax.swing.JComboBox<>();
        labelFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        labelHora = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        comboBoxEstado = new javax.swing.JComboBox<>();
        labelMotivo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        txtCedula = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        labelPaciente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPaciente.setForeground(new java.awt.Color(46, 71, 86));
        labelPaciente.setText("Paciente:");

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(206, 212, 218));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(220, 53, 69));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        labelMedico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMedico.setForeground(new java.awt.Color(46, 71, 86));
        labelMedico.setText("Medico:");

        comboBoxMedico.setModel(new javax.swing.DefaultComboBoxModel<Medico>());
        EnumEspecialidad especialidadSeleccionada = (EnumEspecialidad) comboBoxEspecialidades.getSelectedItem();
        List<Medico> medicosFiltrados = medicoService.buscarPorEspecialidad(especialidadSeleccionada);
        for (Medico m : medicosFiltrados) {
            comboBoxMedico.addItem(m);
        }

        labelEspecialidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEspecialidad.setForeground(new java.awt.Color(46, 71, 86));
        labelEspecialidad.setText("Especialidad:");

        comboBoxEspecialidades.setModel(new DefaultComboBoxModel<>(EnumEspecialidad.values()));
        comboBoxEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEspecialidadesActionPerformed(evt);
            }
        });

        labelFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(46, 71, 86));
        labelFecha.setText("Fecha:");

        labelHora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelHora.setForeground(new java.awt.Color(46, 71, 86));
        labelHora.setText("Hora:");

        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });

        labelEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstado.setForeground(new java.awt.Color(46, 71, 86));
        labelEstado.setText("Estado:");

        comboBoxEstado.setModel(new DefaultComboBoxModel<>(EnumEstadoCita.values()));

        labelMotivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMotivo.setForeground(new java.awt.Color(46, 71, 86));
        labelMotivo.setText("Motivo:");

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane1.setViewportView(txtMotivo);

        jPanel1.setBackground(new java.awt.Color(30, 107, 117));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NUEVO / EDITAR CITA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setBackground(new java.awt.Color(46, 71, 86));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(46, 71, 86));
        jLabel2.setText("Año-Mes-Día");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(46, 71, 86));
        jLabel3.setText("HH:mm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(46, 71, 86));
        jLabel4.setText("Ingrese la cédula:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelFecha)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btnGuardar)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboBoxEspecialidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnLimpiar)
                                    .addGap(44, 44, 44)
                                    .addComponent(btnCancelar))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(60, 60, 60)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(105, 105, 105)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEstado)
                                    .addComponent(labelMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEspecialidad)
                    .addComponent(comboBoxEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstado)
                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)
                            .addComponent(btnLimpiar)))
                    .addComponent(labelMotivo))
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void llenarCamposEditar() {
        if (cita != null) {
            txtCedula.setEditable(false);
            txtCedula.setText(cita.getPaciente().getCedula());
            txtFecha.setText(cita.getFecha().toLocalDate().toString());
            txtHora.setText(cita.getFecha().toLocalTime().toString());
            txtMotivo.setText(cita.getMotivo());
            comboBoxMedico.setSelectedItem(cita.getMedico().getNombre());
            comboBoxEspecialidades.setSelectedItem(cita.getMedico().getEspecialidad());
            comboBoxEstado.setSelectedItem(cita.getEstado());
        }
    }

    /**
     * Carga las especialidades médicas en el comboBoxEspecialidades desde la
     * enumeración.
     */
    private void cargarMedicosPorEspecialidad(EnumEspecialidad especialidad) {
        comboBoxMedico.removeAllItems();
        List<Medico> medicosFiltrados = medicoService.buscarPorEspecialidad(especialidad);
        for (Medico m : medicosFiltrados) {
            comboBoxMedico.addItem(m); // Si usas `toString()`, muestra nombre completo
        }
    }

    private void cargarEspecialidades() {
        comboBoxEspecialidades.removeAllItems();
        for (EnumEspecialidad especialidad : EnumEspecialidad.values()) {
            comboBoxEspecialidades.addItem(especialidad);
        }
        if (comboBoxEspecialidades.getItemCount() > 0) {
            cargarMedicosPorEspecialidad((EnumEspecialidad) comboBoxEspecialidades.getItemAt(0));
        }
    }

    /**
     * Carga los estados posibles de una cita en el comboBoxEstado desde la
     * enumeración.
     */
    private void cargarEstados() {
        comboBoxEstado.removeAllItems();
        if (this.cita == null) {
            // SOLO opción pendiente en modo CREAR
            comboBoxEstado.addItem(EnumEstadoCita.PENDIENTE);
        } else {
            // TODAS las opciones en modo EDITAR
            for (EnumEstadoCita estado : EnumEstadoCita.values()) {
                comboBoxEstado.addItem(estado);
            }
        }
    }

    /**
     * Acción del botón cancelar para cerrar el formulario sin guardar cambios.
     *
     * @param evt evento de acción
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraActionPerformed
    /**
     * Acción para actualizar la lista de médicos mostrada al cambiar la
     * especialidad seleccionada. Consulta al servicio de médicos y actualiza
     * comboBoxMedico.
     *
     * @param evt evento generado al seleccionar especialidad
     */
    private void comboBoxEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEspecialidadesActionPerformed
        EnumEspecialidad especialidadSeleccionada = (EnumEspecialidad) comboBoxEspecialidades.getSelectedItem();
        if (especialidadSeleccionada != null) {
            cargarMedicosPorEspecialidad(especialidadSeleccionada);
        }
    }//GEN-LAST:event_comboBoxEspecialidadesActionPerformed
    /**
     * Acción para guardar los datos ingresados, validando los campos antes. Si
     * es nueva cita, crea y agenda; si es edición, actualiza la cita existente.
     * Muestra mensajes de éxito o error según corresponda.
     *
     * @param evt evento de acción
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cedula = txtCedula.getText().trim();
        Medico medicoSeleccionado = (Medico) comboBoxMedico.getSelectedItem();
        String fecha = txtFecha.getText().trim();
        String hora = txtHora.getText().trim();
        EnumEstadoCita estado = (EnumEstadoCita) comboBoxEstado.getSelectedItem();
        String motivo = txtMotivo.getText().trim();

        // Validaciones básicas
        if (cedula.isEmpty() || medicoSeleccionado == null || fecha.isEmpty() || hora.isEmpty() || estado == null || motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar paciente
        Optional<Paciente> pacienteOpt = pacienteService.buscarPorCedula(cedula);
        if (!pacienteOpt.isPresent()) {
            JOptionPane.showMessageDialog(this, "No existe un paciente con esa cédula.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fechaHoraStr = fecha + " " + hora;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

        Paciente paciente = pacienteOpt.get();

        if (this.cita == null) {
            // Modo crear
            Cita nuevaCita = new Cita(paciente, medicoSeleccionado, motivo, fechaHora);
            nuevaCita.setEstado(estado);
            boolean exito = citaService.agendarCita(nuevaCita);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Cita registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar la cita. Verifica que no esté duplicada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Modo editar
            cita.setPaciente(paciente);
            cita.setMedico(medicoSeleccionado);
            cita.setMotivo(motivo);
            cita.setFecha(fechaHora);
            cita.setEstado(estado);
            boolean exito = citaService.actualizarCita(cita.getId(), cita);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Cita editada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo editar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    /**
     * Acción del botón limpiar para borrar los campos del formulario y resetear
     * selecciones. En modo edición, mantiene bloqueado el campo cédula.
     *
     * @param evt evento de acción
     */
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (cita == null) {
            txtCedula.setText("");
            txtCedula.setEditable(true);
        }
        txtFecha.setText("");
        txtHora.setText("");
        txtMotivo.setText("");

        if (comboBoxEspecialidades.getItemCount() > 0) {
            comboBoxEspecialidades.setSelectedIndex(0);
        }
        if (comboBoxMedico.getItemCount() > 0) {
            comboBoxMedico.setSelectedIndex(0);
        }
        if (comboBoxEstado.getItemCount() > 0) {
            comboBoxEstado.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<EnumEspecialidad> comboBoxEspecialidades;
    private javax.swing.JComboBox<EnumEstadoCita> comboBoxEstado;
    private javax.swing.JComboBox<Medico> comboBoxMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelEspecialidad;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelMedico;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JLabel labelPaciente;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextArea txtMotivo;
    // End of variables declaration//GEN-END:variables
}
