package pruebas.demo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Cliente;
import pruebas.demo.model.Software;
import pruebas.demo.model.DTO.ClienteDTO;
import pruebas.demo.model.tipos.TipoCliente;
import pruebas.demo.repository.ClienteRepository;
import pruebas.demo.repository.SoftwareRepository;
import pruebas.demo.repository.TipoClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoClienteRepository tipoClienteRepository;
    private final SoftwareRepository softwareRepository;

    public ClienteService(ClienteRepository clienteRepository,
            TipoClienteRepository tipoClienteRepository,
            SoftwareRepository softwareRepository) {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.softwareRepository = softwareRepository;
    }

    @Transactional
    public Cliente agregarClienteDesdeDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(dto.getNombreCliente());

        TipoCliente tipo = tipoClienteRepository.findById(dto.getTipoCliente())
                .orElseThrow(() -> new RuntimeException("TipoCliente no encontrado"));
        cliente.setTipoCliente(tipo);

        Set<Software> softwares = dto.getSoftwares().stream()
                .map(id -> softwareRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Software no encontrado con ID: " + id)))
                .collect(Collectors.toSet());
        cliente.setSoftwares(softwares);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
