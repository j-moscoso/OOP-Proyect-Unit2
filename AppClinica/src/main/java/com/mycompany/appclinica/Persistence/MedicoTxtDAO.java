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

    public void guardar(List<Medico> medicos) throws IOException {
        File f = new File(path);
        File parent = f.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Medico medico : medicos) {
                String linea = medico.getCedula() + "," + medico.getNombre() + "," + medico.getApellido() + "," + medico.getEspecialidad().name() + "," + medico.getTelefono();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public List<Medico> cargar() throws IOException {
        List<Medico> medicos = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            return medicos;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                        Medico medico = new Medico(partes[0], partes[1], partes[2], EnumEspecialidad.valueOf(partes[3]), partes[4]);
                    medicos.add(medico);
                }
            }
        }
        return medicos;

    }
}
