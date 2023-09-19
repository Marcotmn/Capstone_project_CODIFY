package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;
import marco.tumminia.capstone.codify.entities.recensione.Recensione;
import marco.tumminia.capstone.codify.entities.utente.RegistrationSuccessResponse;
import marco.tumminia.capstone.codify.exceptions.EmailAlreadyExistsException;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class SviluppatoreService {

    @Autowired
    private SviluppatoreRepository sviluppatoreRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
//LOGICA PER SALVARE GLI SVILUPPATORI
    
    public RegistrationSuccessResponse save(SviluppatorePayload payload) {
        try {
            String email = payload.getEmail();

            // VERIFICA EMAIL GIA UTILIZZATA
            if (sviluppatoreRepository.findByEmail(email) != null) {
                throw new EmailAlreadyExistsException(email);
            }

            Sviluppatore sviluppatore = new Sviluppatore();

            sviluppatore.setUsername(payload.getUsername());
            sviluppatore.setEmail(payload.getEmail());
            sviluppatore.setPassword(passwordEncoder.encode(payload.getPassword()));
            sviluppatore.setIndirizzo(payload.getIndirizzo());
            sviluppatore.setNumeroTelefono(payload.getNumeroTelefono());
            sviluppatore.setCartaDiCredito(payload.getCartaDiCredito());
            sviluppatore.setRuolo(payload.getRuolo());
            sviluppatore.setNome(payload.getNome());
            sviluppatore.setCognome(payload.getCognome());
            sviluppatore.setTitolo(payload.getTitolo());
            sviluppatore.setBio(payload.getBio());
            sviluppatore.setLinkPortfolio(payload.getLinkPortfolio());
            sviluppatore.setCompetenze(payload.getCompetenze());
            sviluppatore.setPartitaIva(payload.getPartitaIva());

            sviluppatore = sviluppatoreRepository.save(sviluppatore);
            
            //MESSAGGIO DI CONFERMA SU POSTMAN
            String successMessage = "L'account è stato registrato con successo.";

            return new RegistrationSuccessResponse(successMessage);

            //ECCEZIONE NON BLOCCANTE SE EMAIL GIA STATA UTILIZZATA
        } catch (EmailAlreadyExistsException e) {
            System.err.println(e.getMessage()); // Stampa l'errore ma non ferma l'app
            return null;
        }
    }


    public Sviluppatore findById(UUID id) {
        return sviluppatoreRepository.findById(id).orElseThrow(() -> new NotFoundException("Sviluppatore non trovato con ID: " + id));
    }
    
    public List<Sviluppatore> findAll() {
        return sviluppatoreRepository.findAll();
    }
   

    public Sviluppatore findByEmail(String email) {
    	return sviluppatoreRepository.findByEmail(email);
    }


    public Sviluppatore updateSviluppatore(UUID id, Sviluppatore updatedSviluppatoreData) {
        Sviluppatore existingSviluppatore = findById(id);
        
        existingSviluppatore.setNome(updatedSviluppatoreData.getNome());
        existingSviluppatore.setCognome(updatedSviluppatoreData.getCognome());
        existingSviluppatore.setTitolo(updatedSviluppatoreData.getTitolo());
        existingSviluppatore.setBio(updatedSviluppatoreData.getBio());
        existingSviluppatore.setLinkPortfolio(updatedSviluppatoreData.getLinkPortfolio());
        existingSviluppatore.setCompetenze(updatedSviluppatoreData.getCompetenze());
        existingSviluppatore.setPartitaIva(updatedSviluppatoreData.getPartitaIva());
        existingSviluppatore.setNumeroTelefono(updatedSviluppatoreData.getNumeroTelefono());
        existingSviluppatore.setCartaDiCredito(updatedSviluppatoreData.getCartaDiCredito());
        existingSviluppatore.setEmail(updatedSviluppatoreData.getEmail());
        return sviluppatoreRepository.save(existingSviluppatore);
    }
    

 

    public void deleteSviluppatore(UUID id) {
        sviluppatoreRepository.deleteById(id);
    }

}
