package pruebas.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pruebas.demo.model.Credencial;
import pruebas.demo.repository.CredencialRepository;

@Service
public class CredencialDetailsService implements UserDetailsService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Credencial credencial = credencialRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        return User.builder()
                .username(credencial.getCorreo())
                .password(credencial.getPassword())
                .roles("USER")
                .build();
    }
}