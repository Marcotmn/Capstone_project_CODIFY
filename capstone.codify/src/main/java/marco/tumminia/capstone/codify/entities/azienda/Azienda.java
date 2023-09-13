package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("AZIENDA")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)  
@Table(name = "aziende")

public class Azienda extends Utente {
	
	@OneToMany(mappedBy = "azienda")  
	private List<Annuncio> annunci;
	
	private String nomeAzienda;
	private String tipoAzienda;
	private String partitaIva;
	private String sitoWeb;
	
	public Azienda(String username, String email, String password, String indirizzo, String numeroTelefono, String cartaDiCredito, Ruolo ruolo, String nomeAzienda, String tipoAzienda, String partitaIva, String sitoWeb) {
		super(username, email, password, indirizzo, numeroTelefono, cartaDiCredito, ruolo);
		
		this.nomeAzienda = nomeAzienda;
		this.tipoAzienda = tipoAzienda;
		this.partitaIva = partitaIva;
		this.sitoWeb = sitoWeb;
	}
	
	@Override
	public String getDisplayName() {
	    return this.nomeAzienda;
	}


}
