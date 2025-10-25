/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Models;

/**
 *
 * @author Alejandro Toro
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un médico en el sistema de gestión clínica. Contiene
 * información profesional y mantiene el registro de sus citas. Aplica el
 * principio de Responsabilidad Única (SRP). La cédula se utiliza como
 * identificador único.
 */
public class Medico {

    private String cedula; // Identificador único (anteriormente 'id')
    private String nombre;
    private String apellido;
    private EnumEspecialidad especialidad;
    private String telefono;
    private List<Cita> citas;

    /**
     * Constructor completo de Medico.
     *
     * @param cedula Cédula de identidad (identificador único)
     * @param nombre Nombre del médico
     * @param apellido Apellido del médico
     * @param especialidad Especialidad médica
     * @param telefono Teléfono de contacto
     */
    public Medico(String cedula, String nombre, String apellido, EnumEspecialidad especialidad, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.citas = new ArrayList<>();
    }

    /**
     * Obtiene el nombre completo del médico.
     *
     * @return Nombre y apellido concatenados con título
     */
    public String getNombreCompleto() {
        return "Dr. " + this.nombre + " " + this.apellido;
    }

    /**
     * Obtiene la lista de citas del médico.
     *
     * @return Lista de citas (copia defensiva)
     */
    public List<Cita> getCitas() {
        return new ArrayList<>(this.citas);
    }

    /**
     * Agrega una cita a la agenda del médico.
     *
     * @param cita Cita a agregar
     */
    public void agregarCita(Cita cita) {
        if (cita != null && !this.citas.contains(cita)) {
            this.citas.add(cita);
        }
    }

    /**
     * Elimina una cita de la agenda del médico.
     *
     * @param cita Cita a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarCita(Cita cita) {
        return this.citas.remove(cita);
    }

    /**
     * Valida que los datos del médico sean correctos.
     *
     * @return true si los datos son válidos, false en caso contrario
     */
    public boolean esValido() {
        return this.cedula != null && !this.cedula.trim().isEmpty()
                && this.nombre != null && !this.nombre.trim().isEmpty()
                && this.apellido != null && !this.apellido.trim().isEmpty()
                && this.especialidad != null
                && this.telefono != null && !this.telefono.trim().isEmpty();
    }

    /**
     * Representación en cadena del médico.
     *
     * @return String con la información del médico
     */
    @Override
    public String toString() {
        return getNombreCompleto()
                + " | Cédula: " + cedula
                + " | Especialidad: " + especialidad.toString()
                + " | Teléfono: " + telefono;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public EnumEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EnumEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para compatibilidad con código que usa getId()
     *
     * @return La cédula como identificador
     */
    public String getId() {
        return this.cedula;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Medico medico = (Medico) obj;
        return cedula != null && cedula.equals(medico.cedula);
    }

    @Override
    public int hashCode() {
        return cedula != null ? cedula.hashCode():0;
    }
}
    


