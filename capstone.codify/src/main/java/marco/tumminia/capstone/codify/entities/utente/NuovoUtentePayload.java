package marco.tumminia.capstone.codify.entities.utente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NuovoUtentePayload {

	private String username;
	private String email;
	private String password;
	private String indirizzo;
	private String numeroTelefono;
	private String cartaDiCredito;
	private Ruolo ruolo;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String nomeAzienda;
    private String tipoAzienda;
    private String partitaIva; 
    private String sitoWeb; 
    private String titolo; 
    private String bio;
    private String linkPortfolio;
    private String competenze;

}
