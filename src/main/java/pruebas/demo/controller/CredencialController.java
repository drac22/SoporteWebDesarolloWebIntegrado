package pruebas.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Credencial;
import pruebas.demo.service.CredencialService;

@RestController
@RequestMapping("/api")
public class CredencialController {
    private final CredencialService credencialService;

    public CredencialController(CredencialService credencialService) {
        this.credencialService = credencialService;
    }

    @GetMapping("/credenciales")
    public List<Credencial> mostrarCredenciales() {
        return credencialService.mostrarCredenciales();
    }

    @GetMapping("/credenciales/{id}")
    public Optional<Credencial> mostrarCredencialesById(@PathVariable Long id) {
        return credencialService.mostrarCredencialById(id);
    }
}
