package marco.tumminia.capstone.codify.entities.utente;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@JsonIgnoreProperties ({ "password" })

public class Utente implements UserDetails {
	
	@Id
	@GeneratedValue
	private UUID id;
	private String username;
	private String email;
	private String password;
	private String indirizzo;
	private String numeroTelefono;
	
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;
	
	public Utente(String username, String email, String password, String indirizzo, String numeroTelefono, Ruolo ruolo) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.indirizzo = indirizzo;
		this.numeroTelefono = numeroTelefono;
		this.ruolo = ruolo;
	}
	
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}