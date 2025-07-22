package pruebas.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.demo.model.Notificacion;
import pruebas.demo.model.Usuario;
import pruebas.demo.repository.NotificacionRepository;
import pruebas.demo.repository.UsuarioRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Notificacion agregarNotificacion(String mensaje, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(mensaje);
        notificacion.setUsuario(usuario);
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeido(false);

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listarPorUsuario(Long idUsuario) {
        return notificacionRepository.findByUsuario_IdUsuario(idUsuario);
    }
}
