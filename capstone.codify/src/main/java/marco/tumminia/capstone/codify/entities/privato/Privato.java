package marco.tumminia.capstone.codify.entities.privato;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("PRIVATO")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)  

public class Privato extends Utente {

	private String nome;
	private String cognome;
	private String codiceFiscale;
	
	public Privato(String username, String email, String password, String indirizzo, String numeroTelefono, String cartaDiCredito, Ruolo ruolo, String nome, String cognome, String codiceFiscale) {
		super(username, email, password, indirizzo, numeroTelefono, cartaDiCredito, ruolo);
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}
	
	@Override
	public String toString() {
	    return "Privato{" +
	            "nome='" + nome + '\'' +
	            ", cognome='" + cognome + '\'' +
	            ", codiceFiscale='" + codiceFiscale + '\'' +
	            "} " + super.toString();
	}
}
