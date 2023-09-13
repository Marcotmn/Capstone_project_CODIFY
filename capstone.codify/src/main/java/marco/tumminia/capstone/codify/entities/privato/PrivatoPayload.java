package marco.tumminia.capstone.codify.entities.privato;

import lombok.AllArgsConstructor;
import lombok.Data;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;

@Data
@AllArgsConstructor

public class PrivatoPayload {

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
}