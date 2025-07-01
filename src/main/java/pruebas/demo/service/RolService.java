package pruebas.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.demo.model.tipos.Rol;
import pruebas.demo.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> mostrarRoles() {
        return rolRepository.findAll();
    }

}
