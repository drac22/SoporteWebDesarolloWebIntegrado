package pruebas.demo.configuration;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import pruebas.demo.service.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Autowired
    // private JwtFilter jwtAuthFilter;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http.csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/api/auth/**").permitAll()
    // .anyRequest().hasRole("ADMIN") // solo ADMIN accede a todo
    // )
    // .sessionManagement(session ->
    // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    // return http.build();
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers(HttpMethod.POST, "/api/agregarSolicitud").hasRole("USUARIO")
    // .requestMatchers(HttpMethod.GET,
    // "/api/mostrarSolicitudesByIdUsuario/**")
    // .hasRole("USUARIO")
    // .requestMatchers(HttpMethod.POST,
    // "/api/registrarActividad").hasRole("COLABORADOR")
    // .anyRequest().hasRole("ADMIN"))
    // .httpBasic(Customizer.withDefaults());
    // return http.build();
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // ❗ Permitir todo sin autenticación
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
