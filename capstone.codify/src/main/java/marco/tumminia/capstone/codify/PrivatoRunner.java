package marco.tumminia.capstone.codify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.privato.PrivatoPayload;
import marco.tumminia.capstone.codify.entities.privato.PrivatoService;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.exceptions.EmailAlreadyExistsException;

@Component
public class PrivatoRunner implements CommandLineRunner {

    @Autowired
    private PrivatoService privatoService;

    @Override
    public void run(String... args) throws Exception {
        try {
            PrivatoPayload payload = new PrivatoPayload(
                "usernamePrivato", "privato@example.com", "passwordPrivata", 
                "Via Privata 123", "+39000123456", "1234-5678-9012-6789", 
                Ruolo.PRIVATO, "Nome", "Cognome", "CF1234567890"
            );

            if (privatoService.findByEmail(payload.getEmail()) != null) {
                throw new EmailAlreadyExistsException(payload.getEmail());
            }

            Privato nuovoPrivato = new Privato(
                payload.getUsername(), payload.getEmail(), payload.getPassword(), 
                payload.getIndirizzo(), payload.getNumeroTelefono(), payload.getCartaDiCredito(), payload.getRuolo(), 
                payload.getNome(), payload.getCognome(), payload.getCodiceFiscale()
            );

            privatoService.save(nuovoPrivato);
            
        } catch (EmailAlreadyExistsException e) {
            System.err.println(e.getMessage()); // Stampa l'errore ma l'applicazione non si ferma
        }
    }
}
