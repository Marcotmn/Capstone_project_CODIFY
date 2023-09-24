package marco.tumminia.capstone.codify.pagamento;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioRepository;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioService;
import marco.tumminia.capstone.codify.entities.annuncio.StatoAnnuncio;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AnnuncioService annuncioService;

    @Autowired
    private AuthenticationFacade authenticationFacade;
    
    @Autowired
    AnnuncioRepository annuncioRepository;
    
    @Autowired
    SviluppatoreService sviluppatoreService;

    public Pagamento effettuaPagamento(UUID idAnnuncio, PagamentoRequest pagamentoRequest) {
      
        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();

        // Verifica se l'utente è autenticato
        if (utenteAutenticato == null) {
            throw new UnauthorizedException("Utente non autenticato");
        }

        // Verifica se l'utente è un Privato o una Azienda
        if (!(utenteAutenticato instanceof Privato || utenteAutenticato instanceof Azienda)) {
            throw new IllegalArgumentException("Solo Privati o Aziende possono effettuare pagamenti");
        }
        
        ///RECUPERA L'ANNUNCIO
        Annuncio annuncio = annuncioRepository.findById(idAnnuncio)
                .orElseThrow(() -> new EntityNotFoundException("Annuncio non trovato"));
        
        ////VERIFICA CHE L'UTENTE AUTENTICATO SIA EFFETTIVAMENTE IL PROPRIETARIO DELL'ANNUNCIO;
        if (!utenteAutenticato.getId().equals(annuncio.getUtenteId())) {
            throw new UnauthorizedException("Non autorizzato ad effettuare pagamenti su questo annuncio");
        }
       
        
        
     // Verifica se l'annuncio è stato completato
        if (!annuncio.getStatoAnnuncio().equals(StatoAnnuncio.COMPLETATO)) {
            throw new IllegalStateException("Il pagamento può essere effettuato solo su annunci completati");
        }
        
        //recupera info sviluppatore
        Sviluppatore sviluppatore = sviluppatoreService.findByEmail(pagamentoRequest.getEmailSviluppatore());
        if (sviluppatore == null) {
            throw new EntityNotFoundException("Sviluppatore non trovato");
        }


        Pagamento pagamento = new Pagamento();
        pagamento.setIdAnnuncio(annuncio.getId());
        pagamento.setNomeAnnuncio(annuncio.getTitolo());  // assumendo che Annuncio abbia un metodo getNome()
        pagamento.setImporto(pagamentoRequest.getImporto());
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setEmailSviluppatore(pagamentoRequest.getEmailSviluppatore());
        pagamento.setPartitaIvaSviluppatore(sviluppatore.getPartitaIva());

        return pagamentoRepository.save(pagamento);
    }
}
