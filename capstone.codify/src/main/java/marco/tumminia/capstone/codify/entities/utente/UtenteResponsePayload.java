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
@NoArgsConstructor 
public class UtenteResponsePayload {
    private UUID id;
    private String username;
    private String email;
    private String indirizzo;
    private String numeroTelefono;
    private String cartaDiCredito;
    private Ruolo ruolo;

}