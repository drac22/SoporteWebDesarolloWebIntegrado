package pruebas.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Asignacion;
import pruebas.demo.model.Colaborador;
import pruebas.demo.model.Solicitud;
import pruebas.demo.model.DTO.AsignacionDTO;
import pruebas.demo.model.DTO.AsignacionDTOResponse;
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

    public List<Asignacion> mostrarAsignaciones() {
        List<Asignacion> asignaciones = asignacionRepository.findAll();
        return asignaciones;
    }

    @Transactional
    public List<AsignacionDTOResponse> mostrarAsignacionesByIdColaborador(Long idColaborador) {
        List<Asignacion> asignaciones = asignacionRepository.findAll().stream()
                .filter(a -> a.getColaborador().getIdColaborador().equals(idColaborador))
                .collect(Collectors.toList());

        return asignaciones.stream().map(asignacion -> {
            Solicitud solicitud = asignacion.getSolicitud();

            AsignacionDTOResponse dto = new AsignacionDTOResponse();
            dto.setIdAsignacion(asignacion.getIdAsignacion());
            dto.setIdSolicitud(solicitud.getIdSolicitud());
            dto.setMotivo(solicitud.getMotivo());
            dto.setNombreUsuario(solicitud.getUsuario().getNombreUsuario());
            dto.setFechaRegistro(solicitud.getFechaRegistro());
            dto.setNombreTipoSolicitud(solicitud.getTipoSolicitud().getTipoSolicitud());

            return dto;
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
