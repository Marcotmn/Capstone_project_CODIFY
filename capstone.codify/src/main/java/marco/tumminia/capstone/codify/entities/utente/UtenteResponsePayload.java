package marco.tumminia.capstone.codify.entities.utente;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Costruttore senza argomenti potrebbe essere utile
public class UtenteResponsePayload {
    private UUID id;
    private String username;
    private String email;
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

    // Metodi factory per semplificare la creazione di risposte specifiche:
    public static UtenteResponsePayload fromSviluppatore(Sviluppatore s) {
        return new UtenteResponsePayload(
            s.getIdUtente(),
            s.getUsername(),
            s.getEmail(),
            s.getIndirizzo(),
            s.getNumeroTelefono(),
            s.getCartaDiCredito(),
            s.getRuolo(),
            s.getNome(),
            s.getCognome(),
            null, // codiceFiscale non è pertinente per Sviluppatore
            null, // nomeAzienda non è pertinente per Sviluppatore
            null, // tipoAzienda non è pertinente per Sviluppatore
            s.getPartitaIva(),
            null, // sitoWeb non è pertinente per Sviluppatore
            s.getTitolo(),
            s.getBio(),
            s.getLinkPortfolio(),
            s.getCompetenze()
        );
    }

    public static UtenteResponsePayload fromPrivato(Privato p) {
        return new UtenteResponsePayload(
            p.getIdUtente(),
            p.getUsername(),
            p.getEmail(),
            p.getIndirizzo(),
            p.getNumeroTelefono(),
            p.getCartaDiCredito(),
            p.getRuolo(),
            p.getNome(),
            p.getCognome(),
            p.getCodiceFiscale(),
            null, // nomeAzienda non è pertinente per Privato
            null, // tipoAzienda non è pertinente per Privato
            null, // partitaIva non è pertinente per Privato
            null, // sitoWeb non è pertinente per Privato
            null, // titolo non è pertinente per Privato
            null, // bio non è pertinente per Privato
            null, // linkPortfolio non è pertinente per Privato
            null  // competenze non sono pertinenti per Privato
        );
    }

    public static UtenteResponsePayload fromAzienda(Azienda a) {
        return new UtenteResponsePayload(
            a.getIdUtente(),
            a.getUsername(),
            a.getEmail(),
            a.getIndirizzo(),
            a.getNumeroTelefono(),
            a.getCartaDiCredito(),
            a.getRuolo(),
            null, // nome non è pertinente per Azienda
            null, // cognome non è pertinente per Azienda
            null, // codiceFiscale non è pertinente per Azienda
            a.getNomeAzienda(),
            a.getTipoAzienda(),
            a.getPartitaIva(),
            a.getSitoWeb(),
            null, // titolo non è pertinente per Azienda
            null, // bio non è pertinente per Azienda
            null, // linkPortfolio non è pertinente per Azienda
            null  // competenze non sono pertinenti per Azienda
        );
    }
}
