package pruebas.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.tipos.TipoCliente;
import pruebas.demo.service.TipoClienteService;

@RestController
@RequestMapping("/api")
public class TipoClienteController {
    @Autowired
    private TipoClienteService tipoClienteService;

    @GetMapping("/tipoClientes")
    public List<TipoCliente> mostrarTipoClientes(){
        return tipoClienteService.mostrarTipoClientes();
    }
}
