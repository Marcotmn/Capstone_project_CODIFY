package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.azienda.AziendaService;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.privato.PrivatoService;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class AnnuncioService {

    @Autowired
    private AnnuncioRepository annuncioRepository;

    @Autowired
    private PrivatoService privatoService; // Assumendo che tu abbia un servizio per Privato

    @Autowired
    private AziendaService aziendaService; // Assumendo che tu abbia un servizio per Azienda

    public Annuncio findById(UUID idAnnuncio) {
        return annuncioRepository.findById(idAnnuncio).orElseThrow(() -> new NotFoundException("Annuncio non trovato con ID: " + idAnnuncio));
    }
    
    public Annuncio save(Annuncio annuncio) {
        return annuncioRepository.save(annuncio);
    }

    public List<Annuncio> findByTitolo(String titolo) {
        return annuncioRepository.findByTitolo(titolo);
    }

    public List<Annuncio> findByPrivato(UUID idPrivato) {
        Privato privato = privatoService.findById(idPrivato);
        return annuncioRepository.findByPrivato(privato);
    }

    public List<Annuncio> findByAzienda(UUID idAzienda) {
        Azienda azienda = aziendaService.findById(idAzienda);
        return annuncioRepository.findByAzienda(azienda);
    }
    
    public List<Annuncio> findByCategoria(CategoriaAnnuncio categoria) {
        return annuncioRepository.findByCategoria(categoria);
    }
    
    public Annuncio findFirst() {
        return annuncioRepository.findFirstByOrderByIdAsc().orElseThrow(() -> new NotFoundException("Nessun annuncio trovato."));
    }


}
