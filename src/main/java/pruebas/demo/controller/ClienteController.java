package pruebas.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Cliente;
import pruebas.demo.service.ClienteService;

@RestController
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        return clienteService.obtenerClientes();
    }
}
