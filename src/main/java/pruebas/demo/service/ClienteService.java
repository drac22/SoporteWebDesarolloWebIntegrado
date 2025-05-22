package pruebas.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pruebas.demo.model.Cliente;
import pruebas.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerClientes(){
        return clienteRepository.findAll();
    }
}
