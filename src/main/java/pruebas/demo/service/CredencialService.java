package pruebas.demo.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Credencial> mostrarCredencialById(Long id){
        return credencialRepository.findById(id);
    }

}
