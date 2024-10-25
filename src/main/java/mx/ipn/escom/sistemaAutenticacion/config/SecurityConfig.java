package mx.ipn.escom.sistemaAutenticacion.config;

import mx.ipn.escom.sistemaAutenticacion.service.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
public class SecurityConfig {

    // Bean para el CustomAuthenticationSuccessHandler
    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/home").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler())  // Usar el handler personalizado
                .permitAll()
            )
            .logout((logout) -> logout.permitAll())
            .exceptionHandling()
            .accessDeniedHandler(new AccessDeniedHandlerImpl())  // Manejador de acceso denegado
            .accessDeniedPage("/accessDenied")  // Redirigir a la página de acceso denegado
            .and()
            .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
