package pruebas.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pruebas.demo.model.Asignacion;
import pruebas.demo.model.Colaborador;
import pruebas.demo.model.Notificacion;
import pruebas.demo.model.Solicitud;
import pruebas.demo.model.DTO.AsignacionDTO;
import pruebas.demo.model.DTO.AsignacionDTOResponse;
import pruebas.demo.repository.AsignacionRepository;
import pruebas.demo.repository.ColaboradorRepository;
import pruebas.demo.repository.NotificacionRepository;
import pruebas.demo.repository.SolicitudRepository;

@Service
public class AsignacionService {

    private final AsignacionRepository asignacionRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final SolicitudRepository solicitudRepository;
    private final NotificacionRepository notificacionRepository;

    public AsignacionService(AsignacionRepository asignacionRepository, ColaboradorRepository colaboradorRepository,
            SolicitudRepository solicitudRepository, NotificacionRepository notificacionRepository) {
        this.asignacionRepository = asignacionRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.solicitudRepository = solicitudRepository;
        this.notificacionRepository = notificacionRepository;
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
            dto.setEstado(solicitud.getEstado().getEstadoSolicitud());

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

        Asignacion guardada = asignacionRepository.save(nuevaAsignacion);

        String mensaje = "Solicitud N° " + solicitud.getIdSolicitud() +
                " fue asignada — Asignación N° " + guardada.getIdAsignacion();

        Notificacion noti = new Notificacion();
        noti.setMensaje(mensaje);
        noti.setUsuario(solicitud.getUsuario());
        noti.setFecha(LocalDateTime.now());
        noti.setLeido(false);
        notificacionRepository.save(noti);

        return guardada;
    }

}
