/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Services;

import com.mycompany.appclinica.Models.Medico;
import com.mycompany.appclinica.Models.EnumEspecialidad;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar operaciones CRUD de Médicos. Aplica el principio de
 * Responsabilidad Única (SRP): Solo se encarga de la lógica de negocio
 * relacionada con médicos.
 *
 * @author Alejandro Toro
 */
public class MedicoService {

    private final List<Medico> medicos;

    /**
     * Constructor que inicializa la lista de médicos.
     */
    public MedicoService() {
        this.medicos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo médico al sistema. Valida que el médico sea válido y que
     * no exista otro con la misma cédula.
     *
     * @param medico Médico a agregar
     * @return true si se agregó exitosamente, false si ya existe o es inválido
     */
    public boolean agregarMedico(Medico medico) {
        if (medico == null || !medico.esValido()) {
            System.err.println("Error: El medico es nulo o no es valido.");
            return false;
        }

        // Validar que no exista un médico con la misma cédula
        if (buscarPorCedula(medico.getCedula()).isPresent()) {
            System.err.println("Error: Ya existe un medico con la cédula " + medico.getCedula());
            return false;
        }

        return medicos.add(medico);
    }

    /**
     * Busca un médico por su cédula.
     *
     * @param cedula Cédula del médico a buscar
     * @return Optional con el médico si se encuentra, Optional.empty() si no
     */
    public Optional<Medico> buscarPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return Optional.empty();
        }

        return medicos.stream()
                .filter(m -> m.getCedula().equals(cedula))
                .findFirst();
    }

    /**
     * Busca médicos por nombre o apellido (búsqueda parcial).
     *
     * @param texto Texto a buscar en nombre o apellido
     * @return Lista de médicos que coinciden con el criterio
     */
    public List<Medico> buscarPorNombre(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String textoBusqueda = texto.toLowerCase();
        return medicos.stream()
                .filter(m -> m.getNombre().toLowerCase().contains(textoBusqueda)
                || m.getApellido().toLowerCase().contains(textoBusqueda))
                .collect(Collectors.toList());
    }

    /**
     * Busca médicos por especialidad.
     *
     * @param especialidad Especialidad a buscar
     * @return Lista de médicos con esa especialidad
     */
    public List<Medico> buscarPorEspecialidad(EnumEspecialidad especialidad) {
        if (especialidad == null) {
            return new ArrayList<>();
        }

        return medicos.stream()
                .filter(m -> m.getEspecialidad() == especialidad)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los médicos registrados en el sistema.
     *
     * @return Lista con todos los médicos (copia defensiva)
     */
    public List<Medico> listarTodos() {
        return new ArrayList<>(medicos);
    }

    /**
     * Actualiza los datos de un médico existente. No permite cambiar la cédula.
     *
     * @param cedula Cédula del médico a actualizar
     * @param medicoActualizado Médico con los nuevos datos
     * @return true si se actualizó exitosamente, false si no se encontró o es
     * inválido
     */
    public boolean actualizarMedico(String cedula, Medico medicoActualizado) {
        if (medicoActualizado == null || !medicoActualizado.esValido()) {
            System.err.println("Error: Los datos del médico son inválidos.");
            return false;
        }

        Optional<Medico> medicoOpt = buscarPorCedula(cedula);
        if (medicoOpt.isPresent()) {
            Medico medico = medicoOpt.get();
            medico.setNombre(medicoActualizado.getNombre());
            medico.setApellido(medicoActualizado.getApellido());
            medico.setEspecialidad(medicoActualizado.getEspecialidad());
            medico.setTelefono(medicoActualizado.getTelefono());
            return true;
        }

        System.err.println("Error: No se encontró un médico con la cédula " + cedula);
        return false;
    }

    /**
     * Elimina un médico del sistema.
     *
     * @param cedula Cédula del médico a eliminar
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminarMedico(String cedula) {
        boolean eliminado = medicos.removeIf(m -> m.getCedula().equals(cedula));

        if (!eliminado) {
            System.err.println("Error: No se encontró un médico con la cédula " + cedula);
        }

        return eliminado;
    }

    /**
     * Obtiene la cantidad total de médicos registrados.
     *
     * @return Número de médicos
     */
    public int contarMedicos() {
        return medicos.size();
    }

    /**
     * Verifica si existe un médico con la cédula especificada.
     *
     * @param cedula Cédula a verificar
     * @return true si existe, false si no
     */
    public boolean existeMedico(String cedula) {
        return buscarPorCedula(cedula).isPresent();
    }

    /**
     * Obtiene la cantidad de médicos por especialidad.
     *
     * @param especialidad Especialidad a contar
     * @return Cantidad de médicos con esa especialidad
     */
    public long contarPorEspecialidad(EnumEspecialidad especialidad) {
        return medicos.stream()
                .filter(m -> m.getEspecialidad() == especialidad)
                .count();
    }
}
