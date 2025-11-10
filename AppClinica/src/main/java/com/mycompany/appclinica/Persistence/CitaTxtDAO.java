/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Persistence;

import com.mycompany.appclinica.Models.Cita;
import com.mycompany.appclinica.Models.Paciente;
import com.mycompany.appclinica.Models.Medico;
import com.mycompany.appclinica.Models.EnumEstadoCita;
import com.mycompany.appclinica.Services.MedicoService;
import com.mycompany.appclinica.Services.PacienteService;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaTxtDAO {
    private final String path = "src/main/resources/data/citas.txt";
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    public List<String> lineasInvalidas = new ArrayList<>();

    public CitaTxtDAO(PacienteService pacienteService, MedicoService medicoService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    public void guardar(List<Cita> citas) throws IOException {
        File f = new File(path);
        if (f.getParentFile() != null) f.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Cita cita : citas) {
                String motivo = sanitize(cita.getMotivo());
                String linea = cita.getId() + "," +
                    cita.getPaciente().getCedula() + "," +
                    cita.getMedico().getCedula() + "," +
                    cita.getFecha().toString() + "," +
                    motivo + "," +
                    cita.getEstado().name();
                writer.write(linea);
                writer.newLine();
            }
            for (String invalida : lineasInvalidas) {
                writer.write(invalida);
                writer.newLine();
            }
        }
    }

    public List<Cita> cargar() throws IOException {
        List<Cita> citas = new ArrayList<>();
        lineasInvalidas.clear();
        File f = new File(path);
        if (!f.exists()) return citas;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            int lineaNum = 0;
            while ((linea = reader.readLine()) != null) {
                lineaNum++;
                String[] partes = linea.split(",");
                if (partes.length < 6) {
                    System.err.println("CITA: Linea " + lineaNum + " ignorada: formato incorrecto (" + linea + ")");
                    lineasInvalidas.add(linea);
                    continue;
                }
                try {
                    String id = partes[0];
                    String cedulaPaciente = partes[1];
                    String cedulaMedico = partes[2];
                    LocalDateTime fecha = LocalDateTime.parse(partes[3]);
                    String motivo = partes[4];
                    EnumEstadoCita estado = EnumEstadoCita.valueOf(partes[5]);
                    Paciente paciente = pacienteService.buscarPorCedula(cedulaPaciente).orElse(null);
                    Medico medico = medicoService.buscarPorCedula(cedulaMedico).orElse(null);

                    if (paciente != null && medico != null) {
                        Cita cita = new Cita(paciente, medico, motivo, fecha);
                        cita.setId(id);
                        cita.setEstado(estado);
                        citas.add(cita);
                    } else {
                        System.err.println("CITA: Linea " + lineaNum + " (" + linea + ") ignorada: paciente o medico no encontrado");
                        lineasInvalidas.add(linea);
                    }
                } catch (Exception e) {
                    System.err.println("CITA: Error en linea " + lineaNum + " (" + linea + "): " +
                        e.getClass().getSimpleName() + " - " + e.getMessage());
                    lineasInvalidas.add(linea);
                }
            }
        }
        return citas;
    }
    
    private String sanitize(String input) {
        if (input == null) return "";
        return input.replace(",", "").replace("\n", "").replace("\r", "");
    }
}