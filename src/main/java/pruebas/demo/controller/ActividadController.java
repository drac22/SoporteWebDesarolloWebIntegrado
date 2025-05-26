package pruebas.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Actividad;
import pruebas.demo.model.DTO.ActividadDTO;
import pruebas.demo.model.DTO.ActividadDTOResponse;
import pruebas.demo.service.ActividadService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping("/actividades")
    public List<ActividadDTOResponse> mostrarActividades() {
        return actividadService.mostrarActividades();
    }

    @GetMapping("/actividadesByAsignacion/{id}")
    public List<Actividad> obtenerActividadesPorAsignacion(@PathVariable Long id) {
        return actividadService.obtenerActividadesPorAsignacion(id);
    }

    @PostMapping("/registrarActividad")
    public Actividad crearActividad(@RequestBody ActividadDTO actividadDTO) {
        return actividadService.registrarActividad(actividadDTO);
    }

}
