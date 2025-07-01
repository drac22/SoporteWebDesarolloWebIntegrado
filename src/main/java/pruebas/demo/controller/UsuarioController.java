package pruebas.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Usuario;
import pruebas.demo.model.DTO.UsuarioDTO;
import pruebas.demo.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public List<Usuario> mostrarUsuarios(){
        return usuarioService.mostrarUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarUsuarioPorId(id);
    }

    @PostMapping("/agregarUsuario")
    public ResponseEntity<Usuario> registrar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevo = usuarioService.agregarUsuarioDesdeDto(usuarioDTO);
        return ResponseEntity.ok(nuevo);
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
    }
}
