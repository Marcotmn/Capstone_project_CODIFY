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

		http.cors(c -> c.disable());
		http.csrf(c -> c.disable());

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, JWTAuthFilter.class);
		
		//LE ROTTE PROTETTE O MENO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/sviluppatore/register").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/azienda/register").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/privato/register").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/nuovoAnnuncio").authenticated());
		//////////ENDPOINT PER LE PROPOSTE
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/pubblicaProposta/{annuncioId}").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/mieProposte").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/{id}").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/mieProposte").authenticated());
		
		///////////ENDPOINT PER LE RECENSIONI
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/recensioni/scriviRecensione/{idSviluppatore}").authenticated());
		
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/fattura/**").authenticated());
		return http.build();
	}

	//BEAN PER CRIPTARE LA PASSWORD
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
}
