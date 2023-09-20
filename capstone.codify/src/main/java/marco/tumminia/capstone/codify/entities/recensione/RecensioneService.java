package marco.tumminia.capstone.codify.entities.recensione;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.entities.utente.UtenteService;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;

@Service
public class RecensioneService {
    @Autowired
    RecensioneRepository recensioneRepository;

    @Autowired
    UtenteService utenteService;
    
    @Autowired
    private AuthenticationFacade authenticationFacade;

    public Recensione saveRecensione(Recensione recensione) {
        Utente recensore = authenticationFacade.getAuthenticatedUser();

        if (recensore == null || !(recensore instanceof Azienda || recensore instanceof Privato)) {
            throw new UnauthorizedException("Solo aziende o privati possono lasciare recensioni.");
        }

        Sviluppatore sviluppatoreRecensito = recensione.getSviluppatore();

        if (sviluppatoreRecensito == null) {
            throw new NotFoundException("Sviluppatore non trovato.");
        }

        return recensioneRepository.save(recensione);
    }

    public Optional<Recensione> findById(UUID idRecensione) {
        return recensioneRepository.findById(idRecensione);
    }

    public void deleteById(UUID idRecensione) {
        recensioneRepository.deleteById(idRecensione);
    }
}

