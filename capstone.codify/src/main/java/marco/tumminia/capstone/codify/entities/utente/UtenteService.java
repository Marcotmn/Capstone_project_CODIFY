package marco.tumminia.capstone.codify.entities.utente;

import java.util.List;

import marco.tumminia.capstone.codify.exceptions.IncorrectPasswordException;

import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public Utente save(NuovoUtentePayload payload) {
        Utente utente = new Utente();

        return utenteRepository.save(utente);
    }

    
    public Utente findByEmail(String email) {
		return utenteRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
    
    public List<Utente> getUsers() {
        return utenteRepository.findAll();
    }

    public Utente findById(UUID id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isPresent()) {
            return utente.get();
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + id); 
        }
    }

    public Utente findByIdAndUpdate(UUID id, NuovoUtentePayload body) {
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if (utenteOptional.isPresent()) {
            Utente utenteAggiornato = utenteOptional.get();
            
            return utenteRepository.save(utenteAggiornato);
        } else {
            
            return null; 
        }
    }

    public void findByIdAndDelete(UUID id) {
        if (utenteRepository.existsById(id)) {
            utenteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + id);  // Puoi gestire l'errore come preferisci
        }
    }
}
