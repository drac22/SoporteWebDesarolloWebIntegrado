package pruebas.demo.controller;

// @RestController
// @RequestMapping("/api/auth")
public class AuthController {

    // @Autowired
    // private AuthenticationManager authManager;

    // @Autowired
    // private JwtService jwtService;

    // @Autowired
    // private CustomUserDetailsService userDetailsService;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String password) {
    //     try {
    //         Authentication auth = authManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(correo, password));

    //         UserDetails userDetails = (UserDetails) auth.getPrincipal();

    //         String token = jwtService.generateToken(userDetails.getUsername());

    //         return ResponseEntity.ok(Map.of("token", token));
    //     } catch (AuthenticationException ex) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    //     }
    // }
}
