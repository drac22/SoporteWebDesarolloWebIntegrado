package pruebas.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Software;
import pruebas.demo.service.SoftwareService;

@RestController
public class SoftwareController {

    private final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService){
        this.softwareService = softwareService;
    }

    @GetMapping("/softwares")
    public List<Software> mostrarSoftwares(){
        return softwareService.mostrarSoftwares();
    }
}
