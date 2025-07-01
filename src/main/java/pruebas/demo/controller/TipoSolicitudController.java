package pruebas.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.tipos.TipoSolicitud;
import pruebas.demo.service.TipoSolicitudService;

@RestController
@RequestMapping("/api")
public class TipoSolicitudController {
    @Autowired
    private TipoSolicitudService tipoSolicitudService;

    @GetMapping("/tipoSolicitudes")
    public List<TipoSolicitud> mostrarTipoSolicitudes() {
        return tipoSolicitudService.obtenerTipoSolicitudes();
    }
}
