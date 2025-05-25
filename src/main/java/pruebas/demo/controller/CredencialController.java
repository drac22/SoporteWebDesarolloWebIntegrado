package pruebas.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Credencial;
import pruebas.demo.service.CredencialService;

@RestController
@RequestMapping("/credenciales")
public class CredencialController {
    private final CredencialService credencialService;

    public CredencialController(CredencialService credencialService) {
        this.credencialService = credencialService;
    }

    @GetMapping
    public List<Credencial> mostrarCredenciales() {
        return credencialService.mostrarCredenciales();
    }
}
