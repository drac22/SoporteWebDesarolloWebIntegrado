package pruebas.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Notificacion;
import pruebas.demo.model.Solicitud;
import pruebas.demo.model.Usuario;
import pruebas.demo.model.DTO.SolicitudDTO;
import pruebas.demo.model.DTO.SolicitudDTOResponse;
import pruebas.demo.model.tipos.EstadoSolicitud;
import pruebas.demo.model.tipos.TipoSolicitud;
import pruebas.demo.repository.EstadoSolicitudRepository;
import pruebas.demo.repository.NotificacionRepository;
import pruebas.demo.repository.SolicitudRepository;
import pruebas.demo.repository.TipoSolicitudRepository;
import pruebas.demo.repository.UsuarioRepository;

@Service
public class SolicitudService {
    private final UsuarioRepository usuarioRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;
    private final SolicitudRepository solicitudRepository;
    private final TipoSolicitudRepository tipoSolicitudRepository;
    private final NotificacionRepository notificacionRepository;

    public SolicitudService(UsuarioRepository usuarioRepository, EstadoSolicitudRepository estadoSolicitudRepository,
            SolicitudRepository solicitudRepository, TipoSolicitudRepository tipoSolicitudRepository,
            NotificacionRepository notificacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estadoSolicitudRepository = estadoSolicitudRepository;
        this.solicitudRepository = solicitudRepository;
        this.tipoSolicitudRepository = tipoSolicitudRepository;
        this.notificacionRepository = notificacionRepository;
    }

    public List<Solicitud> mostrarSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Transactional
    public Solicitud agregarSolicitud(SolicitudDTO solicitudDTO) {
        Usuario usuario = usuarioRepository.findById(solicitudDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findById(solicitudDTO.getIdTipoSolicitud())
                .orElseThrow(() -> new RuntimeException("Tipo de solicitud no encontrado"));

        EstadoSolicitud estado = estadoSolicitudRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Solicitud nuevaSolicitud = new Solicitud();
        nuevaSolicitud.setUsuario(usuario);
        nuevaSolicitud.setMotivo(solicitudDTO.getMotivo());
        nuevaSolicitud.setFechaRegistro(LocalDateTime.now());
        nuevaSolicitud.setFechaCulminacion(null);
        nuevaSolicitud.setTipoSolicitud(tipoSolicitud);
        nuevaSolicitud.setEstado(estado);

        
        Solicitud guardada = solicitudRepository.save(nuevaSolicitud);

        
        String mensaje = "Solicitud N° " + guardada.getIdSolicitud() + " registrada exitosamente";
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(mensaje);
        notificacion.setUsuario(usuario);
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeido(false);
        notificacionRepository.save(notificacion);

        return guardada;
    }

    public List<SolicitudDTOResponse> listarSolicitudesPorUsuarioDTO(Long idUsuario) {
        List<Solicitud> solicitudes = solicitudRepository.findByUsuarioIdUsuario(idUsuario);

        return solicitudes.stream().map(solicitud -> {
            SolicitudDTOResponse dto = new SolicitudDTOResponse();
            dto.setIdSolicitud(solicitud.getIdSolicitud());
            dto.setIdUsuario(solicitud.getUsuario().getIdUsuario());
            dto.setNombreUsuario(solicitud.getUsuario().getNombreUsuario());
            dto.setMotivo(solicitud.getMotivo());
            dto.setEstado(solicitud.getEstado().getEstadoSolicitud());
            dto.setFechaRegistro(solicitud.getFechaRegistro());
            dto.setFechaCulminacion(solicitud.getFechaCulminacion());
            dto.setTipoSolicitud(
                    solicitud.getTipoSolicitud() != null ? solicitud.getTipoSolicitud().getTipoSolicitud() : null);
            dto.setNombreCoordinador(solicitud.getIdColaboradorQueEsCoordinador() != null
                    ? solicitud.getIdColaboradorQueEsCoordinador().getNombreColaborador()
                    : null);
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public Solicitud actualizarSolicitud(Long id, Long idEstado, LocalDateTime fechaCulminacion) {
        Optional<Solicitud> solicitudOpt = solicitudRepository.findById(id);
        if (!solicitudOpt.isPresent()) {
            throw new RuntimeException("Solicitud no encontrada");
        }

        Optional<EstadoSolicitud> estadoOpt = estadoSolicitudRepository.findById(idEstado);
        if (!estadoOpt.isPresent()) {
            throw new RuntimeException("Estado no válido");
        }

        Solicitud solicitud = solicitudOpt.get();
        solicitud.setEstado(estadoOpt.get());
        solicitud.setFechaCulminacion(fechaCulminacion);

        Solicitud actualizada = solicitudRepository.save(solicitud);

        String mensaje = "Solicitud N° " + actualizada.getIdSolicitud() + " cerrada correctamente";

        Notificacion noti = new Notificacion();
        noti.setMensaje(mensaje);
        noti.setUsuario(actualizada.getUsuario());
        noti.setFecha(LocalDateTime.now());
        noti.setLeido(false);

        notificacionRepository.save(noti);

        return actualizada;
    }

    public Map<String, Integer> mostrarResumenSolicitudes() {
        int pendientes = solicitudRepository.countByEstadoIdEstadoSolicitud(1L);
        int solucionadas = solicitudRepository.countByEstadoIdEstadoSolicitud(2L);

        Map<String, Integer> resumen = new HashMap<>();
        resumen.put("pendientes", pendientes);
        resumen.put("solucionadas", solucionadas);
        return resumen;
    }

}
