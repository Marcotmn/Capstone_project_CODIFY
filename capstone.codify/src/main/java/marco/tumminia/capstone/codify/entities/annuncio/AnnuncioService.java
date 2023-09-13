package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.azienda.AziendaService;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.privato.PrivatoService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class AnnuncioService {

    @Autowired
    private AnnuncioRepository annuncioRepository;

    @Autowired
    private PrivatoService privatoService; // Assumendo che tu abbia un servizio per Privato

    @Autowired
    private AziendaService aziendaService; // Assumendo che tu abbia un servizio per Azienda
    
    
    public Annuncio save(Annuncio annuncio) {
        return annuncioRepository.save(annuncio);
    }

    public Annuncio findById(UUID id) {
        Annuncio annuncio = annuncioRepository.findById(id).orElse(null);
        if(annuncio == null) {
            throw new NotFoundException("Annuncio non trovato");
        }
        return annuncio;
    }
    
    public List<Annuncio> findByPubblicante(Utente utente) {
        return annuncioRepository.findByPubblicante(utente);
    }

    public List<Annuncio> findByTitolo(String titolo) {
        return annuncioRepository.findByTitolo(titolo);
    }

    public List<Annuncio> findByCategoria(CategoriaAnnuncio categoria) {
        return annuncioRepository.findByCategoria(categoria);
    }
    
}
