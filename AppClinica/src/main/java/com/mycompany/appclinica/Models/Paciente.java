/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Models;

/**
 *
 * @author Samuel Castaño, HéctorJulián Ospina
 */

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un paciente en el sistema de gestión clínica.
 * Contiene información personal y mantiene el registro de sus citas.
 * Aplica el principio de Responsabilidad Única (SRP).
 * La cédula se utiliza como identificador único.
 */
public class Paciente {
    private String cedula; // Identificador único (anteriormente 'id')
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDate fechaNacimiento;
    private final List<Cita> citas;

    /**
     * Constructor completo de Paciente.
     * @param cedula Cédula de identidad (identificador único)
     * @param nombre Nombre del paciente
     * @param apellido Apellido del paciente
     * @param telefono Teléfono de contacto
     * @param fechaNacimiento Fecha de nacimiento
     */
    public Paciente(String cedula, String nombre, String apellido, String telefono, LocalDate fechaNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.citas = new ArrayList<>();
    }

    /**
     * Obtiene el nombre completo del paciente.
     * @return Nombre y apellido concatenados
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Calcula la edad del paciente basándose en su fecha de nacimiento.
     * @return Edad en años
     */
    public int getEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Obtiene la lista de citas del paciente.
     * @return Lista de citas (copia defensiva)
     */
    public List<Cita> getCitas() {
        return new ArrayList<>(this.citas);
    }

    /**
     * Agrega una cita a la lista del paciente.
     * @param cita Cita a agregar
     */
    public void agregarCita(Cita cita) {
        if (cita != null && !this.citas.contains(cita)) {
            this.citas.add(cita);
        }
    }

    /**
     * Elimina una cita de la lista del paciente.
     * @param cita Cita a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarCita(Cita cita) {
        return this.citas.remove(cita);
    }

    /**
     * Valida que los datos del paciente sean correctos.
     * @return true si los datos son válidos, false en caso contrario
     */
    public boolean esValido() {
        return this.cedula != null && !this.cedula.trim().isEmpty() &&
               this.nombre != null && !this.nombre.trim().isEmpty() &&
               this.apellido != null && !this.apellido.trim().isEmpty() &&
               this.telefono != null && !this.telefono.trim().isEmpty() &&
               this.fechaNacimiento != null && 
               this.fechaNacimiento.isBefore(LocalDate.now());
    }

    /**
     * Representación en cadena del paciente.
     * @return String con la información del paciente
     */
    @Override
    public String toString() {
        return "Paciente: " + getNombreCompleto() + 
               " | Cédula: " + cedula + 
               " | Edad: " + getEdad() + " años" +
               " | Teléfono: " + telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Método para compatibilidad con código que usa getId()
     * @return La cédula como identificador
     */
    public String getId() {
        return this.cedula;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return cedula != null && cedula.equals(paciente.cedula);
    }

    @Override
    public int hashCode() {
        return cedula != null ? cedula.hashCode() : 0;
    }
}

