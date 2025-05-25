package pruebas.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pruebas.demo.model.Colaborador;
import pruebas.demo.model.Credencial;
import pruebas.demo.model.DTO.ColaboradorDTO;
import pruebas.demo.model.tipos.Rol;
import pruebas.demo.model.tipos.TipoCredencial;
import pruebas.demo.repository.ColaboradorRepository;
import pruebas.demo.repository.RolRepository;
import pruebas.demo.repository.TipoCredencialRepository;

@Service
public class ColaboradorService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ColaboradorRepository colaboradorRepository;
    private final RolRepository rolRepository;
    private final TipoCredencialRepository tipoCredencialRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository, RolRepository rolRepository,
            TipoCredencialRepository tipoCredencialRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.rolRepository = rolRepository;
        this.tipoCredencialRepository = tipoCredencialRepository;
    }

    public List<Colaborador> mostrarColaboradores() {
        return colaboradorRepository.findAll();
    }

    @Transactional
    public Colaborador agregarColaboradorDesdeDto(ColaboradorDTO dto) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNombreColaborador(dto.getNombreColaborador());
        colaborador.setTelefonoColaborador(dto.getTelefonoColaborador());

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        colaborador.setRol(rol);

        Credencial credencial = new Credencial();
        credencial.setCorreo(dto.getCorreo());
        credencial.setPassword(passwordEncoder.encode(dto.getPassword()));

        TipoCredencial tipoCredencial = tipoCredencialRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("ERROR"));

        credencial.setTipoCredencial(tipoCredencial);

        colaborador.setCredencial(credencial);

        return colaboradorRepository.save(colaborador);
    }
}
