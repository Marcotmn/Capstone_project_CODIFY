package marco.tumminia.capstone.codify.entities.privato;

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
public class PrivatoService {

    @Autowired
    private PrivatoRepository privatoRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public RegistrationSuccessResponse save(PrivatoPayload payload) {
        try {
            String email = payload.getEmail();

            // VERIFICA EMAIL GIA UTILIZZATA
            if (privatoRepository.findByEmail(email) != null) {
                throw new EmailAlreadyExistsException(email);
            }

            Privato privato = new Privato();

            privato.setUsername(payload.getUsername());
            privato.setEmail(payload.getEmail());
            privato.setPassword(passwordEncoder.encode(payload.getPassword()));
            privato.setIndirizzo(payload.getIndirizzo());
            privato.setNumeroTelefono(payload.getNumeroTelefono());
            privato.setCartaDiCredito(payload.getCartaDiCredito());
            privato.setRuolo(payload.getRuolo());
            privato.setNome(payload.getNome());
            privato.setCognome(payload.getCognome());
            privato.setCodiceFiscale(payload.getCodiceFiscale());

            privato = privatoRepository.save(privato);
            
            //MESSAGGIO DI CONFERMA SU POSTMAN
            String successMessage = "L'account è stato registrato con successo.";

            return new RegistrationSuccessResponse(successMessage);
 
          //ECCEZIONE NON BLOCCANTE SE EMAIL GIA STATA UTILIZZATA
        } catch (EmailAlreadyExistsException e) {
            System.err.println(e.getMessage()); // Stampa l'errore ma non ferma l'app
            return null;
        }
    }
    
    public Optional<Privato> findFirstByOrderByIdAsc() {
        return privatoRepository.findFirstByOrderByIdAsc();
    }

    public Privato findById(UUID id) {
        return privatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Privato non trovato con ID: " + id));
    }
    
    public Privato findByUsername(String username) {
        return privatoRepository.findByUsername(username);
    }
    
    public Privato findByEmail(String email) {
    	return privatoRepository.findByEmail(email);
    }

    public List<Privato> findByNome(String nome) {
        return privatoRepository.findByNome(nome);
    }

    public Privato updatePrivato(UUID id, Privato updatedPrivatoData) {
        Privato existingPrivato = findById(id);
        
        existingPrivato.setNome(updatedPrivatoData.getNome());
        existingPrivato.setCognome(updatedPrivatoData.getCognome());
        existingPrivato.setCodiceFiscale(updatedPrivatoData.getCodiceFiscale());
        existingPrivato.setNumeroTelefono(updatedPrivatoData.getNumeroTelefono());
        existingPrivato.setIndirizzo(updatedPrivatoData.getIndirizzo());
        existingPrivato.setCartaDiCredito(updatedPrivatoData.getCartaDiCredito());
        existingPrivato.setEmail(updatedPrivatoData.getEmail());
       
        return privatoRepository.save(existingPrivato);
    }

    public void deletePrivato(UUID id) {
        if (!privatoRepository.existsById(id)) {
            throw new NotFoundException("Privato non trovato con ID: " + id);
        }
        privatoRepository.deleteById(id);
    } 
}
