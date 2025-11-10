/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Slleider y Juan Moscoso
 */
/**
 * Clase que representa una cita médica en el sistema. Relaciona a un paciente
 * con un médico en una fecha específica.
 */
public class Cita {

    private static int contadorCitas = 1; // Contador estático para generar IDs secuenciales

    private String id;
    private Paciente paciente;
    private Medico medico;
    private String motivo;
    private EnumEstadoCita estado;
    private LocalDateTime fecha;

    /**
     * Constructor completo de Cita con ID autogenerado.
     *
     * @param paciente Paciente que solicita la cita
     * @param medico Médico que atenderá la cita
     * @param motivo Motivo de la consulta
     * @param fecha Fecha y hora programada
     */
    public Cita(Paciente paciente, Medico medico, String motivo, LocalDateTime fecha) {
        this.paciente = paciente;
        this.medico = medico;
        this.motivo = motivo;
        this.estado = EnumEstadoCita.PENDIENTE; // Estado inicial por defecto
        this.fecha = fecha;

        // Agregar la cita a las listas de paciente y médico
        if (paciente != null) {
            paciente.agregarCita(this);
        }
        if (medico != null) {
            medico.agregarCita(this);
        }
    }

    /**
     * Constructor con ID específico (para recuperación de datos desde
     * persistencia).
     *
     * @param id ID de la cita
     * @param paciente Paciente que solicita la cita
     * @param medico Médico que atenderá la cita
     * @param motivo Motivo de la consulta
     * @param estado Estado de la cita
     * @param fecha Fecha y hora programada
     */
    public Cita(String id, Paciente paciente, Medico medico, String motivo, EnumEstadoCita estado, LocalDateTime fecha) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.motivo = motivo;
        this.estado = estado;
        this.fecha = fecha;

        // Actualizar el contador si el ID es numérico y mayor al actual
        try {
            int idNumerico = Integer.parseInt(id.replace("CITA-", ""));
            if (idNumerico >= contadorCitas) {
                contadorCitas = idNumerico + 1;
            }
        } catch (NumberFormatException e) {
            // ID no es del formato esperado, no actualizar contador
        }
    }

    /**
     * Método estático para reiniciar el contador (útil para testing o limpieza
     * de datos)
     */
    public static void reiniciarContador() {
        contadorCitas = 1;
    }

    /**
     * Método estático para establecer el contador en un valor específico
     *
     * @param valor Nuevo valor del contador
     */
    public static void setContador(int valor) {
        contadorCitas = valor;
    }

    /**
     * Obtiene la fecha programada en formato legible.
     *
     * @return String con la fecha formateada
     */
    public String getFechaProgramada() {
        if (this.fecha == null) {
            return "Fecha no definida";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.fecha.format(formatter);
    }

    /**
     * Verifica si la cita está disponible para ser agendada o modificada.
     *
     * @return true si la cita está en estado PENDIENTE, false en caso contrario
     */
    public boolean esDisponible() {
        return this.estado == EnumEstadoCita.PENDIENTE;
    }

    /**
     * Confirma la cita, cambiando su estado a CONFIRMADA.
     *
     * @return true si se pudo confirmar, false si ya estaba en otro estado
     */
    public boolean confirmar() {
        if (this.estado == EnumEstadoCita.PENDIENTE) {
            this.estado = EnumEstadoCita.CONFIRMADA;
            return true;
        }
        return false;
    }

    /**
     * Cancela la cita, cambiando su estado a CANCELADA.
     *
     * @return true si se pudo cancelar, false si ya estaba completada
     */
    public boolean cancelar() {
        if (this.estado != EnumEstadoCita.COMPLETADA) {
            this.estado = EnumEstadoCita.CANCELADA;
            return true;
        }
        return false;
    }

    /**
     * Marca la cita como completada.
     *
     * @return true si se pudo completar, false en caso contrario
     */
    public boolean completar() {
        if (this.estado == EnumEstadoCita.CONFIRMADA) {
            this.estado = EnumEstadoCita.COMPLETADA;
            return true;
        }
        return false;
    }

    /**
     * Marca que el paciente no asistió a la cita.
     */
    public void marcarNoAsistio() {
        if (this.estado == EnumEstadoCita.CONFIRMADA || this.estado == EnumEstadoCita.PENDIENTE) {
            this.estado = EnumEstadoCita.NO_ASISTIO;
        }
    }

    /**
     * Valida que la cita tenga todos los datos requeridos.
     *
     * @return true si la cita es válida, false en caso contrario
     */
    public boolean esValida() {
        return this.paciente != null
                && this.medico != null
                && this.motivo != null && !this.motivo.trim().isEmpty()
                && this.fecha != null;
    }

    /**
     * Representación en cadena de la cita.
     *
     * @return String con la información de la cita
     */
    @Override
    public String toString() {
        return "Cita ID: " + id + "\n"
                + "Paciente: " + (paciente != null ? paciente.getNombreCompleto() + " (CC: " + paciente.getCedula() + ")" : "No asignado") + "\n"
                + "Médico: " + (medico != null ? medico.getNombreCompleto() + " (CC: " + medico.getCedula() + ")" : "No asignado") + "\n"
                + "Especialidad: " + (medico != null ? medico.getEspecialidad() : "N/A") + "\n"
                + "Fecha: " + getFechaProgramada() + "\n"
                + "Motivo: " + motivo + "\n"
                + "Estado: " + estado.toString();
    }

    // Getters y Setters
    
    /**
     * Obtiene el ID de la cita.
     * @return ID actual
     */
    public String getId() {
        return id;
    }
    
    /**
    * Establece el ID de la cita.
    * @param id Nuevo ID a asignar
    */
    public void setId(String id) {
        this.id = id;
    }
       
     /**
     * Obtiene el paciente asociado a la cita.
     * @return paciente actual
     */
    public Paciente getPaciente() {
        return paciente;
    }
    
    /**
    * Establece el paciente asociado a la cita.
    * @param paciente Nuevo paciente a asignar
    */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    /**
     * Obtiene el médico asignado a la cita.
     * @return médico actual
     */
    public Medico getMedico() {
        return medico;  
    }
    /**
     * Establece el médico asignado a la cita.
     * @param medico Nuevo médico a asignar
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    /**
     * Obtiene el motivo de la consulta.
     * @return motivo actual
     */
    public String getMotivo() {
        return motivo;
    }
    /**
     * Establece el motivo de la consulta.
     * @param motivo Nuevo motivo a asignar
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    /**
     * Obtiene el estado actual de la cita.
     * @return estado actual
     */
    public EnumEstadoCita getEstado() {
        return estado;
    }
    /**
     * Establece el estado de la cita.
     * @param estado Nuevo estado a asignar
     */
    public void setEstado(EnumEstadoCita estado) {
        this.estado = estado;
    }
    /**
     * Obtiene la fecha y hora programada para la cita.
     * @return fecha programada
     */ 
    public LocalDateTime getFecha() {
        return fecha;
    }
    /**
     * Establece la fecha y hora programada para la cita.
     * @param fecha Nueva fecha a asignar
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    /**
     * Compara esta cita con otro objeto.
     * @param obj Objeto con el que se compara
     * @return true si ambos son instancias de Cita con el mismo ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cita cita = (Cita) obj;
        return id != null && id.equals(cita.id);
    }
    /**
     * Genera código hash basado en el ID de la cita.
     * @return código hash
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
