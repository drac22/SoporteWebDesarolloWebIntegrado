package pruebas.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pruebas.demo.model.Persona;
import pruebas.demo.repository.PersonaRepository;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    public List<Persona> mostrarPersonas(){
        return personaRepository.findAll();
    }

    public Persona agregarPersona(Persona persona){
        return personaRepository.save(persona);
    }
}
