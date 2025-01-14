package mx.ipn.escom.sistemaAutenticacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
<<<<<<< Updated upstream
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/register", "/login").permitAll()  // Permitir acceso sin autenticación a login y registro
                .requestMatchers("/admin").hasRole("ADMIN")          // Solo los admins pueden acceder a /admin
                .requestMatchers("/home").authenticated()            // Cualquier usuario autenticado puede acceder a /home
                .anyRequest().authenticated()                        // Otras rutas deben estar autenticadas
=======
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/error").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
>>>>>>> Stashed changes
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf().disable(); // Deshabilitar CSRF si no es necesario

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
