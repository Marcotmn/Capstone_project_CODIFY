package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;
import marco.tumminia.capstone.codify.entities.recensione.Recensione;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class SviluppatoreService {

    @Autowired
    private SviluppatoreRepository sviluppatoreRepository;

    public Sviluppatore findById(UUID id) {
        return sviluppatoreRepository.findById(id).orElseThrow(() -> new NotFoundException("Sviluppatore non trovato con ID: " + id));
    }
    
    public List<Sviluppatore> findAll() {
        return sviluppatoreRepository.findAll();
    }

    public List<Sviluppatore> findByNome(String nome) {
        return sviluppatoreRepository.findByNome(nome);
    }


    public List<PropostaSviluppatore> getPropostePerSviluppatore(UUID idSviluppatore) {
        Sviluppatore sviluppatore = findById(idSviluppatore);
        return sviluppatore.getProposte();
    }

    public Sviluppatore updateSviluppatore(UUID id, Sviluppatore updatedSviluppatoreData) {
        Sviluppatore existingSviluppatore = findById(id);
        
        existingSviluppatore.setNome(updatedSviluppatoreData.getNome());
        existingSviluppatore.setCognome(updatedSviluppatoreData.getCognome());
        existingSviluppatore.setTitolo(updatedSviluppatoreData.getTitolo());
        existingSviluppatore.setBio(updatedSviluppatoreData.getBio());
        existingSviluppatore.setLinkPortfolio(updatedSviluppatoreData.getLinkPortfolio());
        existingSviluppatore.setCompetenze(updatedSviluppatoreData.getCompetenze());
        existingSviluppatore.setPartitaIva(updatedSviluppatoreData.getPartitaIva());
        existingSviluppatore.setNumeroTelefono(updatedSviluppatoreData.getNumeroTelefono());
        existingSviluppatore.setCartaDiCredito(updatedSviluppatoreData.getCartaDiCredito());
        existingSviluppatore.setEmail(updatedSviluppatoreData.getEmail());
        return sviluppatoreRepository.save(existingSviluppatore);
    }
    
    public List<Sviluppatore> findTopSviluppatori() {
        return sviluppatoreRepository.findAllOrderByAverageRatingDesc();
    }
    
    public List<Recensione> getRecensioniPerSviluppatore(UUID idSviluppatore) {
        Sviluppatore sviluppatore = findById(idSviluppatore);
        // Supponendo che tu abbia un getter chiamato getRecensioni nella tua classe Sviluppatore che restituisce tutte le recensioni per uno sviluppatore.
        return sviluppatore.getRecensioni();
    }

    public void deleteSviluppatore(UUID id) {
        sviluppatoreRepository.deleteById(id);
    }

}
