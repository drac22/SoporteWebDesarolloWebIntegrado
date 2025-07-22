package pruebas.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pruebas.demo.model.Credencial;
import pruebas.demo.repository.CredencialRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String password) {
        Optional<Credencial> opt = credencialRepository.findByCorreo(correo);

        if (opt.isEmpty()) {
            return ResponseEntity.status(401).body("Correo incorrecto");
        }

        Credencial credencial = opt.get();

        if (!passwordEncoder.matches(password, credencial.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        Map<String, Object> datos = new HashMap<>();
        Long tipoId = credencial.getTipoCredencial().getIdTipoCredencial();

        datos.put("correo", credencial.getCorreo());

        if (tipoId == 1) {
            datos.put("tipo", "usuario");
            datos.put("id", credencial.getUsuario().getIdUsuario());
            datos.put("nombre", credencial.getUsuario().getNombreUsuario());
        } else if (tipoId == 3) {
            datos.put("tipo", "colaborador");
            datos.put("id", credencial.getColaborador().getIdColaborador());
            datos.put("nombre", credencial.getColaborador().getNombreColaborador());
        } else if (tipoId == 2) {
            datos.put("tipo", "admin");
            datos.put("id", null); // o lo que necesites para admin
            datos.put("nombre", "Administrador");
        } else {
            return ResponseEntity.status(400).body("Tipo de credencial desconocido");
        }

        return ResponseEntity.ok(datos);
    }

    // @PostMapping("/logout")
    // public ResponseEntity<String> logout(HttpServletRequest request) {
    // request.getSession().invalidate(); // Cierra la sesión
    // return ResponseEntity.ok("Sesión cerrada");
    // }

    // @Autowired
    // private AuthenticationManager authenticationManager;

    // @Autowired
    // private CredencialDetailsService userDetailsService;

    // @Autowired
    // private JwtUtil jwtUtil;

    // @Autowired
    // private CredencialRepository credencialRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    // try {
    // // 🔎 DEBUG TEMPORAL
    // Credencial credencial =
    // credencialRepository.findByCorreo(authRequest.getCorreo())
    // .orElseThrow(() -> new RuntimeException("Correo no encontrado"));

    // System.out.println("🔐 Password enviado: " + authRequest.getPassword());
    // System.out.println("🔐 Password en BD: " + credencial.getPassword());
    // System.out.println(
    // "🔐 ¿Coincide? " + passwordEncoder.matches(authRequest.getPassword(),
    // credencial.getPassword()));

    // // ⚠️ Solo si coincide, intenta autenticar
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(authRequest.getCorreo(),
    // authRequest.getPassword()));

    // UserDetails userDetails =
    // userDetailsService.loadUserByUsername(authRequest.getCorreo());
    // String token = jwtUtil.generateToken(userDetails);

    // return ResponseEntity.ok(new AuthResponse(token));
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales
    // inválidas");
    // }
    // }
}
