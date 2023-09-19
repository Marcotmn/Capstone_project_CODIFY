package marco.tumminia.capstone.codify.entities.utente;

import java.util.List;
import marco.tumminia.capstone.codify.exceptions.BadRequestException;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
   
	//////////METODO SAVE UTENTE BASE CHE FA UN CONTROLLO DELL'EMAIL AL MOMENTO DELLA REGISTRAZIONE
	public Utente save(NuovoUtentePayload body) {
		utenteRepository.findByEmail(body.getEmail()).ifPresent(utente -> {
			throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
		});
		Utente newUtente = new Utente(body.getUsername(), body.getEmail(),body.getPassword(), body.getIndirizzo(), body.getNumeroTelefono(), body.getCartaDiCredito(), body.getRuolo());
		return utenteRepository.save(newUtente);
	}

	//////////METODO CHE RICERCA L'UTENTE TRAMITE L'EMAIL
    public Utente findByEmail(String email) {
		return utenteRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
    //////////METODO CHE RICERCA TUTTI GLI UTENTI
    public List<Utente> getUsers() {
        return utenteRepository.findAll();
    }
    //////////METODO CHE RICERCA L'UTENTE TRAMITE ID
    public Utente findById(UUID id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isPresent()) {
            return utente.get();
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + id); 
        }
    }
    //////////METODO CHE CERCA L'UTENTE TRAMITE ID E POI LO AGGIORNA
    public Utente findByIdAndUpdate(UUID id, NuovoUtentePayload body) {
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if (utenteOptional.isPresent()) {
            Utente utenteAggiornato = utenteOptional.get();
            
            return utenteRepository.save(utenteAggiornato);
        } else {
            
            return null; 
        }
    }
    //////////METODO CHE CERCA L'UTENTE TRAMITE ID E POI LO ELIMINA
    public void findByIdAndDelete(UUID id) {
        if (utenteRepository.existsById(id)) {
            utenteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + id);  // Puoi gestire l'errore come preferisci
        }
    }
}
