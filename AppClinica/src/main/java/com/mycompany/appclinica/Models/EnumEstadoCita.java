/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Models;

/**
 *
 * @author Juan Moscoso y Slleider
 */
public enum EnumEstadoCita {
    
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    COMPLETADA("Completada"),
    NO_ASISTIO("No Asistió");

    private final String descripcion;

    /**
     * Constructor del enum EstadoCita.
     * @param descripcion Nombre legible del estado
     */
    EnumEstadoCita(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna una representación en cadena del estado.
     * @return Descripción del estado de la cita
     */
    @Override
    public String toString() {
        return this.descripcion;
    }

    /**
     * Obtiene la descripción del estado.
     * @return Descripción del estado
     */
    public String getDescripcion() {
        return descripcion;
    }
}
