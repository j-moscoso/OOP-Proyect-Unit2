/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Persistence;

import com.mycompany.appclinica.Models.Paciente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PacienteTxtDAO {

    private final String path = "src/main/resources/data/pacientes.txt";
    private List<String> lineasInvalidas = new ArrayList<>();

    public void guardar(List<Paciente> pacientes) throws IOException {
        File f = new File(path);
        File parent = f.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Paciente paciente : pacientes) {
                // Sanitizar campos para evitar comas y caracteres especiales problemáticos
                String nombre = sanitize(paciente.getNombre());
                String apellido = sanitize(paciente.getApellido());
                String telefono = sanitize(paciente.getTelefono());
                String linea = paciente.getCedula() + "," + nombre + "," + apellido + "," + telefono + "," + paciente.getFechaNacimiento().toString();
                writer.write(linea);
                writer.newLine();
            }
            for (String invalida : lineasInvalidas) {
                writer.write(invalida);
                writer.newLine();
            }
        }
    }

    public List<Paciente> cargar() throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        lineasInvalidas.clear();
        File f = new File(path);
        if (!f.exists()) {
            return pacientes;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            int lineaNum = 0;
            while ((linea = reader.readLine()) != null) {
                lineaNum++;
                String[] partes = linea.split(",");
                if (partes.length != 5) {
                    System.err.println("PACIENTE: Linea " + lineaNum + " ignorada: formato incorrecto (" + linea + ")");
                    lineasInvalidas.add(linea);
                    continue; // Ignorar línea inválida
                }
                try {
                    // Validar y parsear fecha
                    LocalDate fechaNacimiento = LocalDate.parse(partes[4]);
                    Paciente paciente = new Paciente(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            fechaNacimiento
                    );
                    pacientes.add(paciente);
                } catch (Exception e) {
                    System.err.println("PACIENTE: Error en linea " + lineaNum + " (" + linea + "): " +
                        e.getClass().getSimpleName() + " - " + e.getMessage());
                    lineasInvalidas.add(linea);
                    // Continuar con siguiente línea
                }
            }
        }
        return pacientes;
    }
    
    public List<String> obtenerTodasLasCedulasIncluidoInvalidos() throws IOException {
        List<String> cedulas = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return cedulas;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length > 0 && !partes[0].trim().isEmpty()) {
                    cedulas.add(partes[0].trim());
                }
            }
        }
        return cedulas;
    }
    
    // Método para limpiar caracteres problemáticos
    private String sanitize(String input) {
           if (input == null) return "";
           // Elimina comas y saltos de línea 
           return input.replace(",", "").replace("\n", "").replace("\r", "");
       }
   }