package marco.tumminia.capstone.codify.entities.azienda;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

public class Azienda extends Utente {
	
	@OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Annuncio> annunci = new ArrayList<>();

	
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
	public String toString() {
	    return "Azienda{" +
	            "nomeAzienda='" + nomeAzienda + '\'' +
	            ", tipoAzienda='" + tipoAzienda + '\'' +
	            ", partitaIva='" + partitaIva + '\'' +
	            ", sitoWeb='" + sitoWeb + '\'' +
	            "} " + super.toString();
	}

	
}
