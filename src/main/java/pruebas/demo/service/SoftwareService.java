package pruebas.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pruebas.demo.model.Software;
import pruebas.demo.repository.SoftwareRepositoy;

@Service
public class SoftwareService {

    private final SoftwareRepositoy softwareRepositoy;

    public SoftwareService(SoftwareRepositoy softwareRepositoy){
        this.softwareRepositoy = softwareRepositoy;
    }

    public List<Software> mostrarSoftwares(){
        return softwareRepositoy.findAll();
    }
}
