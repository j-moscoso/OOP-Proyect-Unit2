/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Persistence;

import com.mycompany.appclinica.Models.EnumEspecialidad;
import com.mycompany.appclinica.Models.Medico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoTxtDAO {

    private final String path = "src/main/resources/data/medicos.txt";
    private List<String> lineasInvalidas = new ArrayList<>();

    public void guardar(List<Medico> medicos) throws IOException {
        File f = new File(path);
        File parent = f.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Medico medico : medicos) {
                String nombre = sanitize(medico.getNombre());
                String apellido = sanitize(medico.getApellido());
                String telefono = sanitize(medico.getTelefono());
                String linea = medico.getCedula() + "," + nombre + "," + apellido + "," + medico.getEspecialidad().name() + "," + telefono;
                writer.write(linea);
                writer.newLine();
            }
            for (String invalida : lineasInvalidas) {
                writer.write(invalida);
                writer.newLine();
            }
        }
    }

    public List<Medico> cargar() throws IOException {
        List<Medico> medicos = new ArrayList<>();
        lineasInvalidas.clear();
        File f = new File(path);
        if (!f.exists()) {
            return medicos;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            int lineaNum = 0;
            while ((linea = reader.readLine()) != null) {
                lineaNum++;
                String[] partes = linea.split(",");
                if (partes.length != 5) {
                    System.err.println("MEDICO: Linea " + lineaNum + " ignorada: formato incorrecto (" + linea + ")");
                    lineasInvalidas.add(linea);
                    continue;
                }
                try {
                    EnumEspecialidad especialidad = EnumEspecialidad.valueOf(partes[3]);
                    Medico medico = new Medico(partes[0], partes[1], partes[2], especialidad, partes[4]);
                    medicos.add(medico);
                } catch (IllegalArgumentException e) {
                    System.err.println("MEDICO: Error en linea " + lineaNum + " (" + linea + "): especialidad invalida");
                    lineasInvalidas.add(linea);
                } catch (Exception e) {
                    System.err.println("MEDICO: Error en linea " + lineaNum + " (" + linea + "): " +
                        e.getClass().getSimpleName() + " - " + e.getMessage());
                    lineasInvalidas.add(linea);
                }
            }
        }
        return medicos;
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
    
    private String sanitize(String input) {
        if (input == null) return "";
        return input.replace(",", "").replace("\n", "").replace("\r", "");
    }
}
