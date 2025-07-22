package pruebas.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.service.SolicitudService;

@RestController
public class Dashboard {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping("/dashboard/solicitudes-resumen")
    public Map<String, Integer> getResumenSolicitudes() {
        return solicitudService.mostrarResumenSolicitudes();
    }
}
