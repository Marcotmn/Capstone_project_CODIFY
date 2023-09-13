package marco.tumminia.capstone.codify.entities.sviluppatore;

import lombok.AllArgsConstructor;
import lombok.Data;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;

@Data
@AllArgsConstructor

public class SviluppatorePayload {

    private String username;
    private String email;
    private String password;
    private String indirizzo;
    private String numeroTelefono;
    private String cartaDiCredito;
    private Ruolo ruolo;

    private String nome;
    private String cognome;
    private String titolo;
    private String bio;
    private String linkPortfolio;
    private String competenze;
    private String partitaIva;
}