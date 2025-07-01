package pruebas.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.tipos.Rol;
import pruebas.demo.service.RolService;

@RestController
@RequestMapping("/api")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping("/rols")
    public List<Rol> mostrarRoles(){
        return rolService.mostrarRoles();
    }

}
