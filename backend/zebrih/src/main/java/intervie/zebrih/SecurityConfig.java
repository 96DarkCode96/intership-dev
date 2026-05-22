package intervie.zebrih; // DŮLEŽITÉ: Tady musí být přesně tvoje package!

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Vypne CSRF ochranu
            .formLogin(form -> form.disable()) // Vypne výchozí přihlašovací formulář
            .httpBasic(basic -> basic.disable()) // Vypne vyskakovací okno pro Basic Auth
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Povolí přístup na všechny stránky bez přihlášení
            );
        
        return http.build();
    }
}