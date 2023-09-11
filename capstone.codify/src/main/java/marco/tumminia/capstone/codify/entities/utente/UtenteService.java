package marco.tumminia.capstone.codify.entities.utente;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente createUser(NuovoUtentePayload payload) {
        Utente utente;

        switch(payload.getRuolo()) {
            case SVILUPPATORE:
                utente = new Sviluppatore(payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword(),
                        payload.getIndirizzo(),
                        payload.getNumeroTelefono(),
                        payload.getCartaDiCredito(),
                        payload.getRuolo(),
                        payload.getNome(),
                        payload.getCognome(),
                        payload.getTitolo(),
                        payload.getBio(),
                        payload.getLinkPortfolio(),
                        payload.getCompetenze(),
                        payload.getPartitaIva());
                break;

            case PRIVATO:
                utente = new Privato(payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword(),
                        payload.getIndirizzo(),
                        payload.getNumeroTelefono(),
                        payload.getCartaDiCredito(),
                        payload.getRuolo(),
                        payload.getNome(),
                        payload.getCognome(),
                        payload.getCodiceFiscale());
                break;

            case AZIENDA:
                utente = new Azienda(payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword(),
                        payload.getIndirizzo(),
                        payload.getNumeroTelefono(),
                        payload.getCartaDiCredito(),
                        payload.getRuolo(),
                        payload.getNomeAzienda(),
                        payload.getTipoAzienda(),
                        payload.getPartitaIva(),
                        payload.getSitoWeb());
                break;

            default:
                throw new IllegalArgumentException("Ruolo non valido.");
        }

        return utenteRepository.save(utente);
    }
    
    public List<Utente> getUsers() {
        return utenteRepository.findAll();
    }

    public Utente findById(UUID idUtente) {
        Optional<Utente> utente = utenteRepository.findById(idUtente);
        if (utente.isPresent()) {
            return utente.get();
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + idUtente); 
        }
    }

    public Utente findByIdAndUpdate(UUID idUtente, NuovoUtentePayload body) {
        Optional<Utente> utenteOptional = utenteRepository.findById(idUtente);
        if (utenteOptional.isPresent()) {
            Utente utenteAggiornato = utenteOptional.get();
            
            return utenteRepository.save(utenteAggiornato);
        } else {
            
            return null; 
        }
    }

    public void findByIdAndDelete(UUID idUtente) {
        if (utenteRepository.existsById(idUtente)) {
            utenteRepository.deleteById(idUtente);
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + idUtente);  // Puoi gestire l'errore come preferisci
        }
    }
}
