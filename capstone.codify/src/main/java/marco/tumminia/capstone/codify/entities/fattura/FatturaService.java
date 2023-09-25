package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.pagamento.Pagamento;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private SviluppatoreService sviluppatoreService;

    public Fattura creaFattura(Pagamento pagamento, Utente utente) {

        Sviluppatore sviluppatore = sviluppatoreService.findByEmail(pagamento.getEmailSviluppatore());

        Fattura fattura = new Fattura();
        fattura.setIdPagamento(pagamento.getId());  
        fattura.setImporto(pagamento.getImporto());
        fattura.setEmailSviluppatore(sviluppatore.getEmail());
        fattura.setDataFattura(LocalDate.now());

        if (utente instanceof Azienda) {
            Azienda azienda = (Azienda) utente;
            fattura.setNomeAzienda(azienda.getNomeAzienda());
            fattura.setEmail(utente.getEmail());  
            fattura.setCategoriaAzienda(azienda.getTipoAzienda());
            fattura.setIdentificativoFiscale(azienda.getPartitaIva());
        } else if (utente instanceof Privato) {
            Privato privato = (Privato) utente;
            fattura.setNomeUtente(privato.getNome());
            fattura.setCognomeCliente(privato.getCognome());
            fattura.setEmail(utente.getEmail());  
            fattura.setIdentificativoFiscale(privato.getCodiceFiscale());
        }

        return fatturaRepository.save(fattura);
    } 
    
    public List<Fattura> findFattureByUtente(Utente utente) {
        return fatturaRepository.findByEmail(utente.getEmail());
    }
    
    public Fattura findFatturaById(UUID id) {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata con ID: " + id));
    }

}
