package pruebas.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Software;
import pruebas.demo.service.SoftwareService;

@RestController
@RequestMapping("/api")
public class SoftwareController {

    private final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService){
        this.softwareService = softwareService;
    }

    @GetMapping("/softwares")
    public List<Software> mostrarSoftwares(){
        return softwareService.mostrarSoftwares();
    }

    @PostMapping("/agregarSoftware")
    public Software agregarSoftware(@RequestBody Software software){
        Software nuevoSoftware = softwareService.agregarSoftware(software);
        return nuevoSoftware;
    }

    @DeleteMapping("/eliminarSoftware/{id}")
    public void eliminarSoftware(@PathVariable Long id){
        softwareService.eliminarSoftware(id);
    }
}
