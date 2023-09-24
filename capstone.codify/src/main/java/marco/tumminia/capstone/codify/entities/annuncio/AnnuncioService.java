package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;


@Service
public class AnnuncioService {

    @Autowired
    private AnnuncioRepository annuncioRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;
    
    public Annuncio createAnnuncio(AnnuncioPayload payload) {
        Annuncio annuncio = new Annuncio();

        // METODO PER OTTENERE L'UTENTE AUTENTICATO
        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();

        if (utenteAutenticato == null) {
            throw new UnauthorizedException("Utente non autenticato");
        }

        // CONTROLLO SULL'UTENTE AUTENTICATO
        if (utenteAutenticato instanceof Azienda || utenteAutenticato instanceof Privato) {
            // ASSOCIAZIONE DELL'UTENTE AUTENTICATO ALL'ANNUNCIO
            annuncio.setUtente(utenteAutenticato);
        } else {
            throw new IllegalArgumentException("L'utente autenticato non è un'azienda o un privato.");
        }

        annuncio.setTitolo(payload.getTitolo());
        annuncio.setDescrizione(payload.getDescrizione());
        annuncio.setCategoria(payload.getCategoria());
        annuncio.setStatoAnnuncio(payload.getStatoAnnuncio());
        annuncio.setBudgetPrevisto(payload.getBudgetPrevisto());
        annuncio.setDataPubblicazione(payload.getDataPubblicazione());
        annuncio.setDataChiusura(payload.getDataChiusura());

        return save(annuncio);
    }

    public Annuncio save(Annuncio annuncio) {
        return annuncioRepository.save(annuncio);
    }
    
    public Annuncio completaAnnuncio(UUID idAnnuncio) {
        Annuncio annuncio = annuncioRepository.findById(idAnnuncio)
                .orElseThrow(() -> new NotFoundException("Annuncio non trovato"));

        annuncio.setStatoAnnuncio(StatoAnnuncio.COMPLETATO);
        return annuncioRepository.save(annuncio);
    }

    public Annuncio findAnnuncioById(UUID id) {
        Annuncio annuncio = annuncioRepository.findById(id).orElse(null);
        if (annuncio == null) {
            throw new NotFoundException("Annuncio non trovato");
        }
        return annuncio;
    }

    public List<Annuncio> findByTitolo(String titolo) {
        return annuncioRepository.findByTitolo(titolo);
    }

    public List<Annuncio> findByCategoria(CategoriaAnnuncio categoria) {
        return annuncioRepository.findByCategoria(categoria);
    }
    
    public List<Annuncio> getAnnunciByUtente(Utente utente) {
        return annuncioRepository.findByUtente(utente);
    }

}
