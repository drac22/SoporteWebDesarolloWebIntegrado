package pruebas.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Cliente;
import pruebas.demo.model.DTO.ClienteDTO;
import pruebas.demo.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @PostMapping("/agregarCliente")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente nuevo = clienteService.agregarClienteDesdeDTO(clienteDTO);
        return ResponseEntity.ok(nuevo);
    }

    @DeleteMapping("/eliminarCliente/{id}")
    public void eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
    }

}