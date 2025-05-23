package pruebas.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pruebas.demo.model.Persona;
import pruebas.demo.service.PersonaService;

@RestController
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController (PersonaService personaService){
        this.personaService = personaService;
    }

    @GetMapping("/personas")
    public List<Persona> mostrarPersonas(){
        return personaService.mostrarPersonas();
    }

    @PostMapping("/agregandoPersona")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.agregarPersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
    }
}
