package marco.tumminia.capstone.codify.entities.utente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.fattura.Fattura;
import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;
import marco.tumminia.capstone.codify.entities.recensione.Recensione;

@SuppressWarnings("serial")
@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@JsonIgnoreProperties ({ "password" })

public class Utente implements UserDetails {
	
	@Id
	@GeneratedValue
	private UUID idUtente;
	private String username;
	private String email;
	private String password;
	private String indirizzo;
	private String numeroTelefono;
	private String cartaDiCredito;
	
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;
	
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fattura> fatture = new ArrayList<>();
    
    @OneToMany(mappedBy = "utenteRecensore")
    private List<Recensione> recensioniLasciate = new ArrayList<>();

    @OneToMany(mappedBy = "utenteRecensito")
    private List<Recensione> recensioniRicevute = new ArrayList<>();

	
	public Utente(String username, String email, String password, String indirizzo, String numeroTelefono, String cartaDiCredito, Ruolo ruolo) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.indirizzo = indirizzo;
		this.numeroTelefono = numeroTelefono;
		this.cartaDiCredito = cartaDiCredito;
		this.ruolo = ruolo;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}

	//@Override
	//public String getPassword() {
	//	// TODO Auto-generated method stub
	//	return password;
	//}
	
	public UUID getIdUtente() {
	    return idUtente;
	}

	
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}