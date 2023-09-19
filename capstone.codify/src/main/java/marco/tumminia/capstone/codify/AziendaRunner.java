package marco.tumminia.capstone.codify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.azienda.AziendaPayload;
import marco.tumminia.capstone.codify.entities.azienda.AziendaService;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.exceptions.EmailAlreadyExistsException;

@Component
public class AziendaRunner implements CommandLineRunner {

	@Autowired
	private AziendaService aziendaService;

	@Override
	public void run(String... args) throws Exception {
	    try {
	        AziendaPayload payload = new AziendaPayload(
	            "usernameEsempio", "email@example.com", "passwordSegreta", 
	            "Indirizzo 123", "+39000123456", "1234-5678-9012-3456", Ruolo.AZIENDA, 
	            "Nome Azienda", "Tipo Azienda", "PIVA123456", "www.example.com"
	        );

	        if (aziendaService.findByEmail(payload.getEmail()) != null) {
	            throw new EmailAlreadyExistsException(payload.getEmail());
	        }

	        // Chiamiamo direttamente il metodo save con il payload
	      //  aziendaService.save(payload);
	        
	    } catch (EmailAlreadyExistsException e) {
	        System.err.println(e.getMessage()); // Stampa l'errore ma l'applicazione non si ferma
	    }
	}

}