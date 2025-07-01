package pruebas.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.demo.model.tipos.TipoCliente;
import pruebas.demo.repository.TipoClienteRepository;

@Service
public class TipoClienteService {
    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    public List<TipoCliente> mostrarTipoClientes() {
        return tipoClienteRepository.findAll();
    }

}
