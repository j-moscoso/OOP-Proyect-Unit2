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

    public void guardar(List<Paciente> pacientes) throws IOException {
        File f = new File(path);
        File parent = f.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Paciente paciente : pacientes) {
                String linea = paciente.getCedula() + "," + paciente.getNombre() + "," + paciente.getApellido() + "," + paciente.getTelefono() + "," + paciente.getFechaNacimiento().toString();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public List<Paciente> cargar() throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            return pacientes;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    Paciente paciente = new Paciente(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            LocalDate.parse(partes[4])
                    );
                    pacientes.add(paciente);
                }
            }
        }
        return pacientes;
    }
}
