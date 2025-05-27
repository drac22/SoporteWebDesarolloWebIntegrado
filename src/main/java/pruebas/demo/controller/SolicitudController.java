package pruebas.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pruebas.demo.model.Solicitud;
import pruebas.demo.model.DTO.SolicitudDTO;
import pruebas.demo.model.DTO.SolicitudDTOResponse;
import pruebas.demo.service.SolicitudService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SolicitudController {

    private SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping("/solicitudes")
    public List<Solicitud> mostrarSolicitud() {
        return solicitudService.mostrarSolicitudes();
    }

    @GetMapping("/mostrarSolicitudesByIdUsuario/{idUsuario}")
    public ResponseEntity<List<SolicitudDTOResponse>> listarPorUsuario(@PathVariable Long idUsuario) {
    List<SolicitudDTOResponse> solicitudesDTO = solicitudService.listarSolicitudesPorUsuarioDTO(idUsuario);
    return ResponseEntity.ok(solicitudesDTO);
}

    @PostMapping("/agregarSolicitud")
    public ResponseEntity<Solicitud> agregarSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        Solicitud solicitud = solicitudService.agregarSolicitud(solicitudDTO);
        return ResponseEntity.ok(solicitud);
    }

}
