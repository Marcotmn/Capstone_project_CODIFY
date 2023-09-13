package marco.tumminia.capstone.codify.entities.azienda;

import lombok.AllArgsConstructor;
import lombok.Data;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;

@Data
@AllArgsConstructor

public class AziendaPayload {

	 private String username;
	    private String email;
	    private String password;
	    private String indirizzo;
	    private String numeroTelefono;
	    private String cartaDiCredito;
	    private Ruolo ruolo;
	    private String nomeAzienda;
	    private String tipoAzienda;
	    private String partitaIva;
	    private String sitoWeb;
}
