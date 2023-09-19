package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;

import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioRepository;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreRepository;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.BadRequestException;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;

@Service
public class PropostaSviluppatoreService {

    @Autowired
    private PropostaSviluppatoreRepository propostaRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public PropostaSviluppatore createProposta(Sviluppatore sviluppatore, Annuncio annuncio, PropostaSviluppatorePayload payload) {
        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();

        // Verifica se l'utente è autenticato
        if (utenteAutenticato == null) {
            throw new UnauthorizedException("Utente non autenticato");
        }

        // Verifica se l'utente è uno sviluppatore
        if (!(utenteAutenticato instanceof Sviluppatore)) {
            throw new IllegalArgumentException("Solo gli sviluppatori possono inviare proposte");
        }

        Sviluppatore sviluppatoreAutenticato = (Sviluppatore) utenteAutenticato;

        // Verifica se lo sviluppatore ha già inviato una proposta per questo annuncio
        if (propostaRepository.findByAnnuncioAndSviluppatore(annuncio, sviluppatoreAutenticato).isPresent()) {
            throw new BadRequestException("Hai già inviato una proposta per questo annuncio");
        }

        PropostaSviluppatore proposta = new PropostaSviluppatore();
        proposta.setAnnuncio(annuncio);
        proposta.setSviluppatore(sviluppatoreAutenticato);
        proposta.setDescrizione(payload.getDescrizione());
        proposta.setImportoProposto(payload.getImportoProposto());
        proposta.setDataProposta(LocalDate.now());
        proposta.setStatoProposta(StatoProposta.IN_ATTESA);

        return saveProposta(proposta);
    }

    public List<PropostaSviluppatore> getProposteInAttesa(Utente utente) {
        if (!(utente instanceof Azienda || utente instanceof Privato)) {
            throw new UnauthorizedException("Solo aziende o privati possono accedere alle proposte in attesa.");
        }

        return propostaRepository.findByUtenteAndStatoProposta(utente, StatoProposta.IN_ATTESA);
    }

    public void accettaProposta(UUID idProposta) {
        PropostaSviluppatore proposta = findById(idProposta)
                .orElseThrow(() -> new NotFoundException("Proposta non trovata"));

        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();
        Annuncio annuncio = proposta.getAnnuncio();

        if (utenteAutenticato == null || !Objects.equals(utenteAutenticato, annuncio.getUtente())) {
            throw new UnauthorizedException("Non hai il permesso per accettare questa proposta");
        }

        proposta.setStatoProposta(StatoProposta.ACCETTATA);
        saveProposta(proposta);
    }

    public void rifiutaProposta(UUID idProposta) {
        PropostaSviluppatore proposta = findById(idProposta)
                .orElseThrow(() -> new NotFoundException("Proposta non trovata"));

        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();
        Annuncio annuncio = proposta.getAnnuncio();

        if (utenteAutenticato == null || !Objects.equals(utenteAutenticato, annuncio.getUtente())) {
            throw new UnauthorizedException("Non hai il permesso per rifiutare questa proposta");
        }

        proposta.setStatoProposta(StatoProposta.RIFIUTATA);
        saveProposta(proposta);
    }

    public Optional<PropostaSviluppatore> findById(UUID idProposta) {
        return propostaRepository.findById(idProposta);
    }

    public List<PropostaSviluppatore> findBySviluppatore(Sviluppatore sviluppatore) {
        return propostaRepository.findBySviluppatore(sviluppatore);
    }

    public void deleteById(UUID idProposta) {
        propostaRepository.deleteById(idProposta);
    }

    public PropostaSviluppatore saveProposta(PropostaSviluppatore proposta) {
        return propostaRepository.save(proposta);
    }
}
