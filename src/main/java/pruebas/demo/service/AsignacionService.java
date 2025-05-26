package pruebas.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Asignacion;
import pruebas.demo.model.Colaborador;
import pruebas.demo.model.Solicitud;
import pruebas.demo.model.DTO.AsignacionDTO;
import pruebas.demo.repository.AsignacionRepository;
import pruebas.demo.repository.ColaboradorRepository;
import pruebas.demo.repository.SolicitudRepository;

@Service
public class AsignacionService {

    private final AsignacionRepository asignacionRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final SolicitudRepository solicitudRepository;

    public AsignacionService(AsignacionRepository asignacionRepository, ColaboradorRepository colaboradorRepository,
            SolicitudRepository solicitudRepository) {
        this.asignacionRepository = asignacionRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.solicitudRepository = solicitudRepository;
    }

    public List<Asignacion> mostrarColaboradoresAsignados() {
        return asignacionRepository.findAll();
    }

    @Transactional
    public Asignacion asignarColaborador(AsignacionDTO asignacionDTO) {

        // Obtener la solicitud de la BD
        Solicitud solicitud = solicitudRepository.findById(asignacionDTO.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // Obtener el colaborador de la BD
        Colaborador colaborador = colaboradorRepository.findById(asignacionDTO.getIdColaborador())
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        // Validar si se intenta asignar como coordinador
        if (asignacionDTO.isEsCoordinador()) {
            if (solicitud.getIdColaboradorQueEsCoordinador() != null) {
                throw new RuntimeException("Esta solicitud ya tiene un coordinador asignado");
            }
            // Asignar el colaborador como coordinador en la solicitud
            solicitud.setIdColaboradorQueEsCoordinador(colaborador);
            solicitudRepository.save(solicitud); // Guarda la actualización en la BD
        }

        // Crear la nueva asignación
        Asignacion nuevaAsignacion = new Asignacion();
        nuevaAsignacion.setSolicitud(solicitud);
        nuevaAsignacion.setColaborador(colaborador);
        // Aquí podrías guardar el flag esCoordinador en Asignacion si tienes esa
        // propiedad

        // Guardar la asignación en la BD
        return asignacionRepository.save(nuevaAsignacion);
    }
}
