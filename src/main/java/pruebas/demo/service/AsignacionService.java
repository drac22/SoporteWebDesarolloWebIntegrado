package pruebas.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // public List<Asignacion> mostrarColaboradoresAsignados() {
    // return asignacionRepository.findAll();
    // }

    public List<Map<String, Object>> mostrarColaboradoresAsignados() {
        List<Asignacion> asignaciones = asignacionRepository.findAll();

        return asignaciones.stream().map(asignacion -> {
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("idSolicitud", asignacion.getSolicitud().getIdSolicitud());
            resultado.put("idColaborador", asignacion.getColaborador().getIdColaborador());
            return resultado;
        }).collect(Collectors.toList());
    }

    @Transactional
    public Asignacion asignarColaborador(AsignacionDTO asignacionDTO) {

        Solicitud solicitud = solicitudRepository.findById(asignacionDTO.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        Colaborador colaborador = colaboradorRepository.findById(asignacionDTO.getIdColaborador())
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        if (asignacionDTO.isEsCoordinador()) {
            if (solicitud.getIdColaboradorQueEsCoordinador() != null) {
                throw new RuntimeException("Esta solicitud ya tiene un coordinador asignado");
            }
            solicitud.setIdColaboradorQueEsCoordinador(colaborador);
            solicitudRepository.save(solicitud);
        }

        Asignacion nuevaAsignacion = new Asignacion();
        nuevaAsignacion.setSolicitud(solicitud);
        nuevaAsignacion.setColaborador(colaborador);
        return asignacionRepository.save(nuevaAsignacion);
    }
}
