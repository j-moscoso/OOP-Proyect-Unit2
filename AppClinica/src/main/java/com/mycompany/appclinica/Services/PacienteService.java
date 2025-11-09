/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appclinica.Services;

import com.mycompany.appclinica.Models.Paciente;
import com.mycompany.appclinica.Persistence.PacienteTxtDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar operaciones CRUD de Pacientes. Aplica el principio de
 * Responsabilidad Única (SRP): Solo se encarga de la lógica de negocio
 * relacionada con pacientes.
 *
 * @author Samuel Castaño, Héctor Julián Ospina
 */
public class PacienteService {

    private final PacienteTxtDAO dao = new PacienteTxtDAO();
    private final List<Paciente> pacientes;

    /**
     * Constructor que inicializa la lista de pacientes.
     */
    public PacienteService() {

        List<Paciente> cargados;
        try {
            cargados = dao.cargar();
        } catch (IOException e) {
            cargados = new ArrayList<>();
        }
        this.pacientes = cargados;
    }

    public void persistir() {
        try {
            dao.guardar(pacientes);
        } catch (IOException e) {
            // Muestra el error si ocurre
            System.err.println("Error guardando pacientes: " + e.getMessage());
        }
    }

    /**
     * Agrega un nuevo paciente al sistema. Valida que el paciente sea válido y
     * que no exista otro con la misma cédula.
     *
     * @param paciente Paciente a agregar
     * @return true si se agregó exitosamente, false si ya existe o es inválido
     */
    public boolean agregarPaciente(Paciente paciente) {
        if (paciente == null || !paciente.esValido()) {
            System.err.println("Error: El paciente es nulo o no es válido.");
            return false;
        }

        // Validar que no exista un paciente con la misma cédula
        if (buscarPorCedula(paciente.getCedula()).isPresent()) {
            System.err.println("Error: Ya existe un paciente con la cédula " + paciente.getCedula());
            return false;
        }

        boolean res = pacientes.add(paciente);
        persistir();
        return res;
    }

    /**
     * Busca un paciente por su cédula.
     *
     * @param cedula Cédula del paciente a buscar
     * @return Optional con el paciente si se encuentra, Optional.empty() si no
     */
    public Optional<Paciente> buscarPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return Optional.empty();
        }

        return pacientes.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst();
    }

    /**
     * Busca pacientes por nombre o apellido (búsqueda parcial).
     *
     * @param texto Texto a buscar en nombre o apellido
     * @return Lista de pacientes que coinciden con el criterio
     */
    public List<Paciente> buscarPorNombre(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String textoBusqueda = texto.toLowerCase();
        return pacientes.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(textoBusqueda)
                || p.getApellido().toLowerCase().contains(textoBusqueda))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los pacientes registrados en el sistema.
     *
     * @return Lista con todos los pacientes (copia defensiva)
     */
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }

    /**
     * Actualiza los datos de un paciente existente. No permite cambiar la
     * cédula.
     *
     * @param cedula Cédula del paciente a actualizar
     * @param pacienteActualizado Paciente con los nuevos datos
     * @return true si se actualizó exitosamente, false si no se encontró o es
     * inválido
     */
    public boolean actualizarPaciente(String cedula, Paciente pacienteActualizado) {
        if (pacienteActualizado == null || !pacienteActualizado.esValido()) {
            System.err.println("Error: Los datos del paciente son inválidos.");
            return false;
        }

        Optional<Paciente> pacienteOpt = buscarPorCedula(cedula);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setApellido(pacienteActualizado.getApellido());
            paciente.setTelefono(pacienteActualizado.getTelefono());
            paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
            persistir();
            return true;
        }

        System.err.println("Error: No se encontró un paciente con la cédula " + cedula);
        return false;
    }

    /**
     * Elimina un paciente del sistema.
     *
     * @param cedula Cédula del paciente a eliminar
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminarPaciente(String cedula) {
        boolean eliminado = pacientes.removeIf(p -> p.getCedula().equals(cedula));

        if (eliminado) {
            persistir();
        }
        return eliminado;
    }
        /**
         * Obtiene la cantidad total de pacientes registrados.
         *
         * @return Número de pacientes
         */
    public int contarPacientes() {
        return pacientes.size();
    }

    /**
     * Verifica si existe un paciente con la cédula especificada.
     *
     * @param cedula Cédula a verificar
     * @return true si existe, false si no
     */
    public boolean existePaciente(String cedula) {
        return buscarPorCedula(cedula).isPresent();
    }
}
