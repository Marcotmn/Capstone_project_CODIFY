package marco.tumminia.capstone.codify.entities.privato;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class PrivatoService {

    @Autowired
    private PrivatoRepository privatoRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Privato save(PrivatoPayload payload) {
        Privato privato = new Privato();

        privato.setUsername(payload.getUsername());
        privato.setEmail(payload.getEmail());
        privato.setPassword(passwordEncoder.encode(payload.getPassword()));
        privato.setIndirizzo(payload.getIndirizzo());
        privato.setNumeroTelefono(payload.getNumeroTelefono());
        privato.setCartaDiCredito(payload.getCartaDiCredito());
        privato.setRuolo(payload.getRuolo());
        privato.setNome(payload.getNome());
        privato.setCognome(payload.getCognome());
        privato.setCodiceFiscale(payload.getCodiceFiscale());

        return privatoRepository.save(privato);
    }
    
    public Optional<Privato> findFirstByOrderByIdAsc() {
        return privatoRepository.findFirstByOrderByIdAsc();
    }

    public Privato findById(UUID id) {
        return privatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Privato non trovato con ID: " + id));
    }
    
    public Privato findByUsername(String username) {
        return privatoRepository.findByUsername(username);
    }
    
    public Privato findByEmail(String email) {
    	return privatoRepository.findByEmail(email);
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
