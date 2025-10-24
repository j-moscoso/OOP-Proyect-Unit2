/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Models;

/**
 *
 * @author Juan Moscoso y Slleider Rojas
 */
public enum EnumEspecialidad {
    
    MEDICINA_GENERAL("Medicina General"),
    PEDIATRIA("Pediatría"),
    GINECOLOGIA("Ginecología"),
    CIRUGIA_PLASTICA("Cirugía Plástica"),
    CARDIOLOGIA("Cardiología");

    private final String descripcion;

    /**
     * Constructor del enum Especialidad.
     * @param descripcion Nombre legible de la especialidad
     */
    
    EnumEspecialidad(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna una representación en cadena de la especialidad.
     * @return Descripción de la especialidad
     */
    @Override
    public String toString() {
        return this.descripcion;
    }

    /**
     * Obtiene la descripción de la especialidad.
     * @return Descripción de la especialidad
     */
    public String getDescripcion() {
        return descripcion;
    }
}
