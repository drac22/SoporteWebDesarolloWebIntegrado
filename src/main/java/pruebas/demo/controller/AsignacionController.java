package pruebas.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import pruebas.demo.model.Asignacion;
import pruebas.demo.model.DTO.AsignacionDTO;
import pruebas.demo.model.DTO.AsignacionDTOResponse;
import pruebas.demo.service.AsignacionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AsignacionController {

    private final AsignacionService asignacionService;

    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @GetMapping("/mostrarAsignaciones")
    public List<Asignacion> mostrarColaboradoresAsignados() {
        return asignacionService.mostrarAsignaciones();
    }

    @GetMapping("/mostrarAsignacionesById/{idColaborador}")
    public List<AsignacionDTOResponse> mostrarAsignacionesByIdColaborador(@PathVariable Long idColaborador) {
        return asignacionService.mostrarAsignacionesByIdColaborador(idColaborador);
    }

    @PostMapping("/asignarColaboradores")
    public ResponseEntity<Asignacion> registrar(@RequestBody AsignacionDTO asignacionDTO) {
        Asignacion nuevo = asignacionService.asignarColaborador(asignacionDTO);
        return ResponseEntity.ok(nuevo);
    }

}
