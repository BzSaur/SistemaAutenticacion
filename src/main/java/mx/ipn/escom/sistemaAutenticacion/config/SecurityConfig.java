package mx.ipn.escom.sistemaAutenticacion.config;
import mx.ipn.escom.sistemaAutenticacion.service.CustomAuthenticationSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/register", "/login").permitAll()  // Permitir acceso sin autenticación a login y registro
                .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")           // Solo los admins pueden acceder a /admin
                .requestMatchers("/home").authenticated()            // Cualquier usuario autenticado puede acceder a /home
                .anyRequest().authenticated()                        // Otras rutas deben estar autenticadas
            )
            .formLogin((form) -> form
                .loginPage("/login")                               // Página de login personalizada
                .successHandler(customAuthenticationSuccessHandler())  // Usar el handler personalizado
                .permitAll()
            )
            .logout((logout) -> logout.permitAll())
            .exceptionHandling()
            .accessDeniedPage("/accessDenied")  // Página de acceso denegado
            .and()
            .csrf().disable();  // Deshabilitar CSRF si no es necesario

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usar un codificador adecuado
    }
}
