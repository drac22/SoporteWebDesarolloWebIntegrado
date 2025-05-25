package pruebas.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Colaborador;
import pruebas.demo.model.DTO.ColaboradorDTO;
import pruebas.demo.service.ColaboradorService;

@RestController
@RequestMapping("/api")
public class ColaboradorController {
    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/colaboradores")
    public List<Colaborador> mostrarColaboradores(){
        return colaboradorService.mostrarColaboradores();
    }

    @PostMapping("/agregarColaborador")
    public ResponseEntity<Colaborador> registrarColaborador(@RequestBody ColaboradorDTO colaboradorDTO) {
        Colaborador nuevo = colaboradorService.agregarColaboradorDesdeDto(colaboradorDTO);
        return ResponseEntity.ok(nuevo);
    }
}

