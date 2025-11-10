/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Services;

import com.mycompany.appclinica.Models.Cita;
import com.mycompany.appclinica.Models.EnumEstadoCita;
import com.mycompany.appclinica.Persistence.CitaTxtDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar operaciones CRUD de Citas. Aplica el principio de
 * Responsabilidad Única (SRP): Solo se encarga de la lógica de negocio
 * relacionada con citas médicas.
 *
 * @author Juan Moscoso y Slleider Rojas
 */
public class CitaService {

    private final CitaTxtDAO dao;
    private final List<Cita> citas;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    /**
     * Constructor que inicializa el servicio de citas. Requiere los servicios
     * de pacientes y médicos para validaciones.
     *
     * @param pacienteService Servicio de pacientes
     * @param medicoService Servicio de médicos
     */
    public CitaService(PacienteService pacienteService, MedicoService medicoService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.dao = new CitaTxtDAO(pacienteService, medicoService);
        List<Cita> cargadas;
        try {
            cargadas = dao.cargar();
        } catch (IOException e) {
            cargadas = new ArrayList<>();
        }
        this.citas = cargadas;
    }

    public void persistir() {
        try {
            dao.guardar(citas);
        } catch (IOException e) {
            System.err.println("Error guardando citas: " + e.getMessage());
        }
    }

    /**
     * Agenda una nueva cita en el sistema. Valida que el paciente y el médico
     * existan, y que no haya conflictos de horario.
     *
     * @param cita Cita a agendar
     * @return true si se agendó exitosamente, false si hay errores
     */
    public boolean agendarCita(Cita cita) {
        if (cita == null) {
            System.err.println("Error: La cita es nula.");
            return false;
        }

        // Validar que el paciente exista
        if (cita.getPaciente() == null
                || !pacienteService.existePaciente(cita.getPaciente().getCedula())) {
            System.err.println("Error: El paciente no existe en el sistema.");
            return false;
        }

        // Validar que el médico exista
        if (cita.getMedico() == null
                || !medicoService.existeMedico(cita.getMedico().getCedula())) {
            System.err.println("Error: El médico no existe en el sistema.");
            return false;
        }

        // Validar que la fecha sea futura
        if (cita.getFecha().isBefore(LocalDateTime.now())) {
            System.err.println("Error: La fecha de la cita debe ser futura.");
            return false;
        }

        // Validar que no haya conflicto de horario para el médico
        if (existeConflictoHorario(cita.getMedico().getCedula(), cita.getFecha(), null)) {
            System.err.println("Error: El médico ya tiene una cita agendada en ese horario.");
            return false;
        }

        boolean res = citas.add(cita);
        if (res) {
            persistir();
        }
        return res;
    }

    /**
     * Verifica si existe un conflicto de horario para un médico en una fecha
     * específica. Se considera conflicto si hay otra cita en la misma hora (con
     * margen de 30 minutos).
     *
     * @param cedulaMedico Cédula del médico
     * @param fecha Fecha y hora a verificar
     * @param idCitaExcluir ID de cita a excluir de la validación (para
     * actualizaciones)
     * @return true si hay conflicto, false si está disponible
     */
    private boolean existeConflictoHorario(String cedulaMedico, LocalDateTime fecha, String idCitaExcluir) {
        // Verificar en citas válidas cargadas en memoria
        boolean conflictoValido = citas.stream()
            .filter(c -> !c.getId().equals(idCitaExcluir))
            .filter(c -> c.getMedico().getCedula().equals(cedulaMedico))
            .filter(c -> c.getEstado() != EnumEstadoCita.CANCELADA)
            .anyMatch(c -> {
                LocalDateTime inicioCita = c.getFecha();
                LocalDateTime finCita = inicioCita.plusMinutes(30);
                return !fecha.isBefore(inicioCita) && fecha.isBefore(finCita);
            });

        if (conflictoValido) return true;

        // Verificar en las líneas inválidas cargadas del archivo
        for (String linea : dao.lineasInvalidas) {
            String[] partes = linea.split(",");
            if (partes.length < 4) continue;
            String cedMedicoInv = partes[2].trim();
            String fechaTexto = partes[3].trim();

            if (cedMedicoInv.equals(cedulaMedico)) {
                try {
                    LocalDateTime fechaInv = LocalDateTime.parse(fechaTexto);
                    LocalDateTime finInv = fechaInv.plusMinutes(30);
                    if (!fecha.isBefore(fechaInv) && fecha.isBefore(finInv)) {
                        return true;
                    }
                } catch (Exception e) {
                    // Si la fecha no se puede analizar, es seguro asumir conflicto para evitar duplicados erróneos
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Busca una cita por su ID.
     *
     * @param id ID de la cita (formato: "CITA-XXXX")
     * @return Optional con la cita si se encuentra, Optional.empty() si no
     */
    public Optional<Cita> buscarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Optional.empty();
        }

        return citas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    /**
     * Obtiene todas las citas de un paciente específico.
     *
     * @param cedulaPaciente Cédula del paciente
     * @return Lista de citas del paciente
     */
    public List<Cita> buscarPorPaciente(String cedulaPaciente) {
        if (cedulaPaciente == null || cedulaPaciente.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return citas.stream()
                .filter(c -> c.getPaciente().getCedula().equals(cedulaPaciente))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las citas de un médico específico.
     *
     * @param cedulaMedico Cédula del médico
     * @return Lista de citas del médico
     */
    public List<Cita> buscarPorMedico(String cedulaMedico) {
        if (cedulaMedico == null || cedulaMedico.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return citas.stream()
                .filter(c -> c.getMedico().getCedula().equals(cedulaMedico))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las citas en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de citas en ese rango
     */
    public List<Cita> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return new ArrayList<>();
        }

        return citas.stream()
                .filter(c -> !c.getFecha().isBefore(fechaInicio)
                && !c.getFecha().isAfter(fechaFin))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las citas con un estado específico.
     *
     * @param estado Estado de la cita
     * @return Lista de citas con ese estado
     */
    public List<Cita> buscarPorEstado(EnumEstadoCita estado) {
        if (estado == null) {
            return new ArrayList<>();
        }

        return citas.stream()
                .filter(c -> c.getEstado() == estado)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return Lista con todas las citas (copia defensiva)
     */
    public List<Cita> listarTodas() {
        return new ArrayList<>(citas);
    }

    /**
     * Confirma una cita cambiando su estado a CONFIRMADA.
     *
     * @param id ID de la cita a confirmar (formato: "CITA-XXXX")
     * @return true si se confirmó exitosamente, false si no se pudo
     */
    public boolean confirmarCita(String id) {
        Optional<Cita> citaOpt = buscarPorId(id);
        if (citaOpt.isPresent()) {
            return citaOpt.get().confirmar();
        }

        System.err.println("Error: No se encontró una cita con el ID " + id);
        return false;
    }

    /**
     * Cancela una cita cambiando su estado a CANCELADA.
     *
     * @param id ID de la cita a cancelar (formato: "CITA-XXXX")
     * @return true si se canceló exitosamente, false si no se pudo
     */
    public boolean cancelarCita(String id) {
        Optional<Cita> citaOpt = buscarPorId(id);
        if (citaOpt.isPresent()) {
            boolean ok = citaOpt.get().cancelar();
            if (ok) {
                persistir();
            }
            return ok;
        }
        System.err.println("Error: No se encontró una cita con el ID " + id);
        return false;
    }

    /**
     * Completa una cita cambiando su estado a COMPLETADA.
     *
     * @param id ID de la cita a completar (formato: "CITA-XXXX")
     * @return true si se completó exitosamente, false si no se pudo
     */
    public boolean completarCita(String id) {
        Optional<Cita> citaOpt = buscarPorId(id);
        if (citaOpt.isPresent()) {
            boolean ok = citaOpt.get().completar();
            if (ok) {
                persistir();
            }
            return ok;
        }
        System.err.println("Error: No se encontró una cita con el ID " + id);
        return false;
    }

    /**
     * Marca que el paciente no asistió a la cita.
     *
     * @param id ID de la cita (formato: "CITA-XXXX")
     * @return true si se marcó exitosamente, false si no se encontró
     */
    public boolean marcarNoAsistio(String id) {
        Optional<Cita> citaOpt = buscarPorId(id);
        if (citaOpt.isPresent()) {
            citaOpt.get().marcarNoAsistio();
            persistir();
            return true;
        }

        System.err.println("Error: No se encontró una cita con el ID " + id);
        return false;
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param id ID de la cita a actualizar (formato: "CITA-XXXX")
     * @param citaActualizada Cita con los nuevos datos
     * @return true si se actualizó exitosamente, false si no se encontró
     */
    public boolean actualizarCita(String id, Cita citaActualizada) {
        if (citaActualizada == null) {
            System.err.println("Error: Los datos de la cita son nulos.");
            return false;
        }

        Optional<Cita> citaOpt = buscarPorId(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();

            // Validar cambio de fecha si hay conflicto
            if (!cita.getFecha().equals(citaActualizada.getFecha())) {
                if (existeConflictoHorario(cita.getMedico().getCedula(),
                        citaActualizada.getFecha(),
                        id)) { // Excluir la cita actual de la validación
                    System.err.println("Error: Conflicto de horario al actualizar la cita.");
                    return false;
                }
                cita.setFecha(citaActualizada.getFecha());
            }

            cita.setMotivo(citaActualizada.getMotivo());
            cita.setEstado(citaActualizada.getEstado());
            persistir();
            return true;
        }

        System.err.println("Error: No se encontró una cita con el ID " + id);
        return false;
    }

    /**
     * Elimina una cita del sistema.
     *
     * @param id ID de la cita a eliminar (formato: "CITA-XXXX")
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminarCita(String id) {
        boolean eliminado = citas.removeIf(c -> c.getId().equals(id));

        if (eliminado) {
            persistir();
        } else {
            System.err.println("Error: No se encontró una cita con el ID " + id);
        }

        return eliminado;
    }

    /**
     * Obtiene la cantidad total de citas registradas.
     *
     * @return Número de citas
     */
    public int contarCitas() {
        return citas.size();
    }

    /**
     * Obtiene las citas próximas (dentro de las próximas 24 horas).
     *
     * @return Lista de citas próximas
     */
    public List<Cita> obtenerCitasProximas() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.plusHours(24);

        return citas.stream()
                .filter(c -> c.getEstado() == EnumEstadoCita.CONFIRMADA
                || c.getEstado() == EnumEstadoCita.PENDIENTE)
                .filter(c -> !c.getFecha().isBefore(ahora)
                && !c.getFecha().isAfter(limite))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las citas del día actual.
     *
     * @return Lista de citas de hoy
     */
    public List<Cita> obtenerCitasDelDia() {
        LocalDateTime inicioDia = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime finDia = inicioDia.plusDays(1).minusSeconds(1);

        return buscarPorRangoFechas(inicioDia, finDia);
    }

    /**
     * Verifica si un paciente tiene citas pendientes o confirmadas.
     *
     * @param cedulaPaciente Cédula del paciente
     * @return true si tiene citas activas, false si no
     */
    public boolean pacienteTieneCitasActivas(String cedulaPaciente) {
        return citas.stream()
                .filter(c -> c.getPaciente().getCedula().equals(cedulaPaciente))
                .anyMatch(c -> c.getEstado() == EnumEstadoCita.PENDIENTE
                || c.getEstado() == EnumEstadoCita.CONFIRMADA);
    }

    /**
     * Verifica si un médico tiene citas pendientes o confirmadas.
     *
     * @param cedulaMedico Cédula del médico
     * @return true si tiene citas activas, false si no
     */
    public boolean medicoTieneCitasActivas(String cedulaMedico) {
        return citas.stream()
                .filter(c -> c.getMedico().getCedula().equals(cedulaMedico))
                .anyMatch(c -> c.getEstado() == EnumEstadoCita.PENDIENTE
                || c.getEstado() == EnumEstadoCita.CONFIRMADA);
    }
}
