package marco.tumminia.capstone.codify;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatorePayload;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.exceptions.EmailAlreadyExistsException;

    @Component
    public class SviluppatoreRunner implements CommandLineRunner {

        @Autowired
        private SviluppatoreService sviluppatoreService;

        @Override
        public void run(String... args) throws Exception {
            try {
                SviluppatorePayload payload = new SviluppatorePayload(
                    "sviluppatoreUsername", "sviluppatore5@mail.com", "password123", 
                    "Indirizzo", "1234567890", "1111222233334444", Ruolo.SVILUPPATORE, 
                    "Mario", "Rossi", "Sviluppatore Full Stack", 
                    "Sono uno sviluppatore con 5 anni di esperienza", 
                    "https://mario-rossi-portfolio.com", "Java, JavaScript, Python", "PIVA123456"
                );

                if (sviluppatoreService.findByEmail(payload.getEmail()) != null) {
                    throw new EmailAlreadyExistsException(payload.getEmail());
                }

                // Non hai bisogno di creare l'oggetto Sviluppatore qui,
                // poiché il metodo save del servizio già si occupa di questo.
               // sviluppatoreService.save(payload);
                
            } catch (EmailAlreadyExistsException e) {
                System.err.println(e.getMessage()); // Stampa l'errore ma non ferma l'app
            }
        }
        }
    
