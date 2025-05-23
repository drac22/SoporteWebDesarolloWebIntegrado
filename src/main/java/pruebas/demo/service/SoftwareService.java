package pruebas.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pruebas.demo.model.Software;
import pruebas.demo.repository.SoftwareRepository;

@Service
public class SoftwareService {

    private final SoftwareRepository softwareRepository;

    public SoftwareService(SoftwareRepository softwareRepository){
        this.softwareRepository = softwareRepository;
    }

    public List<Software> mostrarSoftwares(){
        return softwareRepository.findAll();
    }

    public Software agregarSoftware(Software software){
        return softwareRepository.save(software);
    }

    public void eliminarSoftware(Long id){
        softwareRepository.deleteById(id);
    }
}
