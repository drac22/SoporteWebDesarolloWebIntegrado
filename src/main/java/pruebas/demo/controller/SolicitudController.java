package pruebas.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import pruebas.demo.model.Solicitud;
import pruebas.demo.model.DTO.SolicitudDTO;
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

    @PostMapping("/agregarSolicitud")
    public ResponseEntity<Solicitud> agregarSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        Solicitud solicitud = solicitudService.agregarSolicitud(solicitudDTO);
        return ResponseEntity.ok(solicitud);
    }

}
