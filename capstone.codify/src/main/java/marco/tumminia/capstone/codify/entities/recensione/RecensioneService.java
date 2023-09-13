package marco.tumminia.capstone.codify.entities.recensione;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.entities.utente.UtenteService;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@Service
public class RecensioneService {
	
	@Autowired
	RecensioneRepository recensioneRepository;
	
	@Autowired
	UtenteService utenteService;
	

	
	public Recensione saveRecensione (Recensione recensione) {
		return recensioneRepository.save(recensione);
	}
	
	public Optional<Recensione> findById(UUID idRecensione) {
        return recensioneRepository.findById(idRecensione);
    }
	
	
	public void deleteById(UUID idRecensione) {
        recensioneRepository.deleteById(idRecensione);
    }
	
	
}

