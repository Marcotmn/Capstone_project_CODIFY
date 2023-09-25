package marco.tumminia.capstone.codify.pagamento;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioRepository;
import marco.tumminia.capstone.codify.entities.annuncio.StatoAnnuncio;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.fattura.FatturaService;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;

@Service
@Transactional
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private AuthenticationFacade authenticationFacade;
    
    @Autowired
    AnnuncioRepository annuncioRepository;
    
    @Autowired
    SviluppatoreService sviluppatoreService;

    public Pagamento effettuaPagamento(UUID idAnnuncio, PagamentoRequest pagamentoRequest) {
      
        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();

        ////VERIFICA CHE L'UTENTE SIA AUTENTICATO
        if (utenteAutenticato == null) {
            throw new UnauthorizedException("Utente non autenticato");
        }

        ////VERIFICA CHE L'UTENTE AUTENTICATO SIA UN PRIVATO O UN'AZIENDA
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
        
        ////VERIFICA SE L'ANNUNCIO è STATO PUBBLICATO
        if (!annuncio.getStatoAnnuncio().equals(StatoAnnuncio.COMPLETATO)) {
            throw new IllegalStateException("Il pagamento può essere effettuato solo su annunci completati");
        }
        
        ////RECUPERA INFO SVILUPPATORE
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
        pagamento.setEmailUtentePagante(utenteAutenticato.getEmail());


        pagamento = pagamentoRepository.save(pagamento);
        fatturaService.creaFattura(pagamento, utenteAutenticato); 
        return pagamento;
    }
    
    
    public Pagamento findPagamentoById(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento non trovato con ID: " + id));
    }
}
