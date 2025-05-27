package pruebas.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Cliente;
import pruebas.demo.model.Credencial;
import pruebas.demo.model.Usuario;
import pruebas.demo.model.DTO.UsuarioDTO;
import pruebas.demo.model.tipos.TipoCredencial;
import pruebas.demo.repository.ClienteRepository;
import pruebas.demo.repository.TipoCredencialRepository;
import pruebas.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final TipoCredencialRepository tipoCredencialRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository,
            TipoCredencialRepository tipoCredencialRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.tipoCredencialRepository = tipoCredencialRepository;
    }

    public List<Usuario> mostrarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario agregarUsuarioDesdeDto(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setTelefonoUsuario(dto.getTelefonoUsuario());

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        usuario.setCliente(cliente);

        Credencial credencial = new Credencial();
        credencial.setCorreo(dto.getCorreo());
        credencial.setPassword(passwordEncoder.encode(dto.getPassword()));

        TipoCredencial tipoCredencial = tipoCredencialRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("ERROR"));

        credencial.setTipoCredencial(tipoCredencial);

        usuario.setCredencial(credencial);

        return usuarioRepository.save(usuario);
    }
}
