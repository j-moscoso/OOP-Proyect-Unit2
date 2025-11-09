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

    public CitaTxtDAO(PacienteService pacienteService, MedicoService medicoService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    public void guardar(List<Cita> citas) throws IOException {
        File f = new File(path);
        if (f.getParentFile() != null) f.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Cita cita : citas) {
                String linea = cita.getId() + "," +
                    cita.getPaciente().getCedula() + "," +
                    cita.getMedico().getCedula() + "," +
                    cita.getFecha().toString() + "," +
                    cita.getMotivo() .replace(","," ")+ "," +
                    cita.getEstado().name();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public List<Cita> cargar() throws IOException {
        List<Cita> citas = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return citas;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 6) continue; // línea malformada

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
                    cita.setId(id); // asumiendo setter para id si es necesario
                    cita.setEstado(estado);
                    citas.add(cita);
                }
            }
        }
        return citas;
    }
}