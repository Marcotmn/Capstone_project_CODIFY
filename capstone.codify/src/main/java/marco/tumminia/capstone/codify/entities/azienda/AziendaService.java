package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class AziendaService {

    @Autowired
    private AziendaRepository aziendaRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Azienda save(AziendaPayload payload) {
        Azienda azienda = new Azienda();

        azienda.setUsername(payload.getUsername());
        azienda.setEmail(payload.getEmail());
        azienda.setPassword(passwordEncoder.encode(payload.getPassword()));
        azienda.setIndirizzo(payload.getIndirizzo());
        azienda.setNumeroTelefono(payload.getNumeroTelefono());
        azienda.setCartaDiCredito(payload.getCartaDiCredito());
        azienda.setRuolo(payload.getRuolo());
        azienda.setNomeAzienda(payload.getNomeAzienda());
        azienda.setTipoAzienda(payload.getTipoAzienda());
        azienda.setPartitaIva(payload.getPartitaIva());
        azienda.setSitoWeb(payload.getSitoWeb());

        return aziendaRepository.save(azienda);
    }
    
    public Azienda findByEmail(String email) {
    	return aziendaRepository.findByEmail(email);
    }
    
    public Optional<Azienda> findFirstByOrderByIdAsc() {
        return aziendaRepository.findFirstByOrderByIdAsc();
    }


    public Azienda findById(UUID id) {
        return aziendaRepository.findById(id).orElseThrow(() -> new NotFoundException("Azienda non trovata con ID: " + id));
    }
    
    public List<Azienda> findByNomeAzienda(String nomeAzienda) {
        return aziendaRepository.findByNomeAzienda(nomeAzienda);
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

