package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class AziendaService {

    @Autowired
    private AziendaRepository aziendaRepository;

    public Azienda findById(UUID id) {
        return aziendaRepository.findById(id).orElseThrow(() -> new NotFoundException("Azienda non trovata con ID: " + id));
    }
    
    public List<Azienda> findByNomeAzienda(String nomeAzienda) {
        return aziendaRepository.findByNomeAzienda(nomeAzienda);
    }
    
    public List<Annuncio> getAnnunciPerAzienda(UUID idAzienda) {
        Azienda azienda = findById(idAzienda);
        return azienda.getAnnunci();
    }

    public Azienda updateAzienda(UUID id, Azienda updatedAziendaData) {
        Azienda existingAzienda = findById(id);
        
        existingAzienda.setNomeAzienda(updatedAziendaData.getNomeAzienda());
        existingAzienda.setTipoAzienda(updatedAziendaData.getTipoAzienda());
        existingAzienda.setPartitaIva(updatedAziendaData.getPartitaIva());
        existingAzienda.setSitoWeb(updatedAziendaData.getSitoWeb());
        existingAzienda.setEmail(updatedAziendaData.getEmail());
        existingAzienda.setIndirizzo(updatedAziendaData.getIndirizzo());
        existingAzienda.setNumeroTelefono(updatedAziendaData.getNumeroTelefono());
        existingAzienda.setCartaDiCredito(updatedAziendaData.getCartaDiCredito());
        
        return aziendaRepository.save(existingAzienda);
    }

    public void deleteAzienda(UUID id) {
        if (!aziendaRepository.existsById(id)) {
            throw new NotFoundException("Azienda non trovata con ID: " + id);
        }
        aziendaRepository.deleteById(id);
    }
}

