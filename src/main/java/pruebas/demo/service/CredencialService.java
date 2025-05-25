package pruebas.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pruebas.demo.model.Credencial;
import pruebas.demo.repository.CredencialRepository;

@Service
public class CredencialService {
    private final CredencialRepository credencialRepository;

    public CredencialService(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    public List<Credencial> mostrarCredenciales() {
        return credencialRepository.findAll();
    }

}
