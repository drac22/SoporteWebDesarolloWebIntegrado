package pruebas.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Solicitud;
import pruebas.demo.model.Usuario;
import pruebas.demo.model.DTO.SolicitudDTO;
import pruebas.demo.model.tipos.EstadoSolicitud;
import pruebas.demo.model.tipos.TipoSolicitud;
import pruebas.demo.repository.EstadoSolicitudRepository;
import pruebas.demo.repository.SolicitudRepository;
import pruebas.demo.repository.TipoSolicitudRepository;
import pruebas.demo.repository.UsuarioRepository;

@Service
public class SolicitudService {
    private final UsuarioRepository usuarioRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;
    private final SolicitudRepository solicitudRepository;
    private final TipoSolicitudRepository tipoSolicitudRepository;

    public SolicitudService(UsuarioRepository usuarioRepository, EstadoSolicitudRepository estadoSolicitudRepository,
            SolicitudRepository solicitudRepository, TipoSolicitudRepository tipoSolicitudRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estadoSolicitudRepository = estadoSolicitudRepository;
        this.solicitudRepository = solicitudRepository;
        this.tipoSolicitudRepository = tipoSolicitudRepository;
    }

    public List<Solicitud> mostrarSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Transactional
    public Solicitud agregarSolicitud(SolicitudDTO solicitudDTO) {
        Solicitud nuevaSolicitud = new Solicitud();
        Usuario usuario = usuarioRepository.findById(solicitudDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        nuevaSolicitud.setUsuario(usuario);
        nuevaSolicitud.setMotivo(solicitudDTO.getMotivo());
        EstadoSolicitud estado = estadoSolicitudRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        nuevaSolicitud.setFechaRegistro(LocalDateTime.now());
        nuevaSolicitud.setFechaCulminacion(null);
        nuevaSolicitud.setEstado(estado);
        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findById(solicitudDTO.getIdTipoSolicitud())
                .orElseThrow(() -> new RuntimeException("Tipo de solicitud no encontrado"));

        nuevaSolicitud.setTipoSolicitud(tipoSolicitud);

        return solicitudRepository.save(nuevaSolicitud);
    }
}
