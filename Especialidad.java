/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagestionmedica.modelo;

/**
 *
 * Enumeración que representa las especialidades médicas disponibles.
 * 
 */
public enum Especialidad {
    
    MEDICINA_GENERAL("Medicina General"),
    PEDIATRIA("Pediatría"),
    CARDIOLOGIA("Cardiología"),
    DERMATOLOGIA("Dermatología"),
    GINECOLOGIA("Ginecología");
    
    private final String nombre;
    
    /**
    * Constructor del enum 
    */
        
    Especialidad(String nombre) {
        this.nombre = nombre;
    }
    
    /**
    * Retorna el nombre de la especialidad
    */
    
    @Override
    public String toString() {
        return nombre;          
    }
}  
