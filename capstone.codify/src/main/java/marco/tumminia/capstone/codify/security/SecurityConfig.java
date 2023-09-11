package marco.tumminia.capstone.codify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JWTAuthFilter jwtFilter;
    @Autowired
    CorsFilter corsFilter; 
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(); // attiva la configurazione CORS predefinita, se non vuoi utilizzare un filtro CORS personalizzato.
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // Se stai utilizzando un filtro CORS personalizzato, descomenta la riga seguente.
        // http.addFilterBefore(corsFilter, JWTAuthFilter.class);

        http.authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/utenti/**").authenticated()
            .antMatchers("/fatture/**").authenticated()
            // aggiungi le altre route che desideri proteggere qui
            .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
