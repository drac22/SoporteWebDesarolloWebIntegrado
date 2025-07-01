package pruebas.demo.controller;


public class AuthController {

    // @Autowired
    // private CredencialRepository credencialRepository;

    // @PostMapping("/login")
    // public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    //     Optional<Credencial> credencial = credencialRepository.findByCorreo(request.getCorreo());

    //     if (credencial.isPresent()) {
    //         if (credencial.get().getContrasenia().equals(request.getContrasenia())) {
    //             return ResponseEntity.ok("Inicio de sesión exitoso");
    //         } else {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
    //         }
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    //     }
    // }

    // @Autowired
    // private AuthenticationManager authManager;

    // @Autowired
    // private JwtService jwtService;

    // @Autowired
    // private CustomUserDetailsService userDetailsService;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestParam String correo, @RequestParam
    // String password) {
    // try {
    // Authentication auth = authManager.authenticate(
    // new UsernamePasswordAuthenticationToken(correo, password));

    // UserDetails userDetails = (UserDetails) auth.getPrincipal();

    // String token = jwtService.generateToken(userDetails.getUsername());

    // return ResponseEntity.ok(Map.of("token", token));
    // } catch (AuthenticationException ex) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales
    // inválidas");
    // }
    // }
}
