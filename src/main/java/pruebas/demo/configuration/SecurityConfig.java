package pruebas.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Autowired
// private JwtFilter jwtFilter;

// @Autowired
// private CredencialDetailsService credencialDetailsService; // 👈 Usar la
// clase personalizada

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// public AuthenticationProvider authenticationProvider() {
// DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
// authProvider.setUserDetailsService(credencialDetailsService); // 👈 Asegúrate
// de usar tu clase
// authProvider.setPasswordEncoder(passwordEncoder());
// return authProvider;
// }

// @Bean
// public AuthenticationManager authenticationManager(AuthenticationProvider
// authenticationProvider) {
// return new ProviderManager(authenticationProvider); // 👈 Construcción manual
// del manager
// }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http,
// AuthenticationProvider authenticationProvider) throws Exception {
// return http
// .csrf(csrf -> csrf.disable())
// .authorizeHttpRequests(auth -> auth
// .requestMatchers("/auth/**").permitAll()
// .anyRequest().authenticated())
// .sessionManagement(sess ->
// sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .authenticationProvider(authenticationProvider)
// .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
// .build();
// }
// }
