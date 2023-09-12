package marco.tumminia.capstone.codify.entities.privato;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class PrivatoService {

    @Autowired
    private PrivatoRepository privatoRepository;

    public Privato findById(UUID id) {
        return privatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Privato non trovato con ID: " + id));
    }
    
    public List<Privato> findByNome(String nome) {
        return privatoRepository.findByNome(nome);
    }

    public List<Annuncio> getAnnunciPerPrivato(UUID idPrivato) {
        Privato privato = findById(idPrivato);
        return privato.getAnnunci();
    }
    
    public Privato updatePrivato(UUID id, Privato updatedPrivatoData) {
        Privato existingPrivato = findById(id);
        
        existingPrivato.setNome(updatedPrivatoData.getNome());
        existingPrivato.setCognome(updatedPrivatoData.getCognome());
        existingPrivato.setCodiceFiscale(updatedPrivatoData.getCodiceFiscale());
        existingPrivato.setNumeroTelefono(updatedPrivatoData.getNumeroTelefono());
        existingPrivato.setIndirizzo(updatedPrivatoData.getIndirizzo());
        existingPrivato.setCartaDiCredito(updatedPrivatoData.getCartaDiCredito());
        existingPrivato.setEmail(updatedPrivatoData.getEmail());
       
        
        return privatoRepository.save(existingPrivato);
    }

    public void deletePrivato(UUID id) {
        if (!privatoRepository.existsById(id)) {
            throw new NotFoundException("Privato non trovato con ID: " + id);
        }
        privatoRepository.deleteById(id);
    }

   
}
