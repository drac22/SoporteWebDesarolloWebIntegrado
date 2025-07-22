package pruebas.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Notificacion;
import pruebas.demo.model.Usuario;
import pruebas.demo.model.DTO.NotificacionDTO;
import pruebas.demo.repository.NotificacionRepository;
import pruebas.demo.service.NotificacionService;


@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/agregar")
    public ResponseEntity<Notificacion> agregar(@RequestBody NotificacionDTO dto) {
        Notificacion nueva = notificacionService.agregarNotificacion(dto.getMensaje(), dto.getIdUsuario());
        return ResponseEntity.ok(nueva);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Notificacion>> listar(@PathVariable Long idUsuario) {
        List<Notificacion> notificaciones = notificacionService.listarPorUsuario(idUsuario);
        return ResponseEntity.ok(notificaciones);
    }
}
