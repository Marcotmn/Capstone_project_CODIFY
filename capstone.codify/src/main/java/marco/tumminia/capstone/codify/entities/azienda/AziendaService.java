package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.utente.RegistrationSuccessResponse;
import marco.tumminia.capstone.codify.exceptions.EmailAlreadyExistsException;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class AziendaService {

    @Autowired
    private AziendaRepository aziendaRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public RegistrationSuccessResponse save(AziendaPayload payload) {
        try {
            String email = payload.getEmail();

            // VERIFICA EMAIL GIA UTILIZZATA
            if (aziendaRepository.findByEmail(email) != null) {
                throw new EmailAlreadyExistsException(email);
            }

            Azienda azienda = new Azienda();

            azienda.setUsername(payload.getUsername());
            azienda.setEmail(payload.getEmail());
            azienda.setPassword(passwordEncoder.encode(payload.getPassword()));
            azienda.setIndirizzo(payload.getIndirizzo());
            azienda.setNumeroTelefono(payload.getNumeroTelefono());
            azienda.setCartaDiCredito(payload.getCartaDiCredito());
            azienda.setRuolo(payload.getRuolo());
            azienda.setNomeAzienda(payload.getNomeAzienda());
            azienda.setTipoAzienda(payload.getTipoAzienda());
            azienda.setPartitaIva(payload.getPartitaIva());
            azienda.setSitoWeb(payload.getSitoWeb());

            azienda = aziendaRepository.save(azienda); 

          //MESSAGGIO DI CONFERMA SU POSTMAN
            String successMessage = ("L'utente è stato registrato con successo.");

            return new RegistrationSuccessResponse(successMessage);
            
            
          //ECCEZIONE NON BLOCCANTE SE EMAIL GIA STATA UTILIZZATA
        } catch (EmailAlreadyExistsException e) {
            System.err.println(e.getMessage()); // Stampa l'errore ma non ferma l'app
        }

        return null; 
    }

    
    public Azienda findByEmail(String email) {
    	return aziendaRepository.findByEmail(email);
    }
    
    public Optional<Azienda> findFirstByOrderByIdAsc() {
        return aziendaRepository.findFirstByOrderByIdAsc();
    }


    public Azienda findById(UUID id) {
        return aziendaRepository.findById(id).orElseThrow(() -> new NotFoundException("Azienda non trovata con ID: " + id));
    }
    
    public List<Azienda> findByNomeAzienda(String nomeAzienda) {
        return aziendaRepository.findByNomeAzienda(nomeAzienda);
    }
    

    public Azienda updateAzienda(UUID id, Azienda updatedAziendaData) {
        Azienda existingAzienda = findById(id);
        
        existingAzienda.setNomeAzienda(updatedAziendaData.getNomeAzienda());
        existingAzienda.setTipoAzienda(updatedAziendaData.getTipoAzienda());
        existingAzienda.setPartitaIva(updatedAziendaData.getPartitaIva());
        existingAzienda.setSitoWeb(updatedAziendaData.getSitoWeb());
        existingAzienda.setEmail(updatedAziendaData.getEmail());
        existingAzienda.setIndirizzo(updatedAziendaData.getIndirizzo());
        existingAzienda.setNumeroTelefono(updatedAziendaData.getNumeroTelefono());
        existingAzienda.setCartaDiCredito(updatedAziendaData.getCartaDiCredito());
        
        return aziendaRepository.save(existingAzienda);
    }
    

    public void deleteAzienda(UUID id) {
        if (!aziendaRepository.existsById(id)) {
            throw new NotFoundException("Azienda non trovata con ID: " + id);
        }
        aziendaRepository.deleteById(id);
    }
}

