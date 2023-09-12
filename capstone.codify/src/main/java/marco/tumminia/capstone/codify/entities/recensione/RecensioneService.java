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
	
	public List<Recensione> findByUtenteRecensito(Utente utenteRecensito) {
        return recensioneRepository.findByUtenteRecensito(utenteRecensito);
    }

	public List<Recensione> findByUtenteRecensore(Utente utenteRecensore) {
        return recensioneRepository.findByUtenteRecensore(utenteRecensore);
    }
	
	public List<Recensione> findByUtenteRecensitoAndPunteggio(UUID utenteId, PunteggioRecensione punteggio) {
	    Utente utenteRecensito = null;
	    try {
	        utenteRecensito = utenteService.findById(utenteId);
	    } catch (RuntimeException ex) {
	        throw new NotFoundException("Utente non trovato");
	    }
	    
	    if (punteggio != null) {
	        return recensioneRepository.findByUtenteRecensitoAndPunteggio(utenteRecensito, punteggio);
	    }
	    return recensioneRepository.findByUtenteRecensito(utenteRecensito);
	}

	public void deleteById(UUID idRecensione) {
        recensioneRepository.deleteById(idRecensione);
    }
	
	public NuovaRecensioneResponsePayload creaResponsePayloadPerRecensione(Recensione recensione) {
	    Utente utenteRecensito = recensione.getUtenteRecensito();
	    double mediaPunteggio = calcolaMediaStelle(utenteRecensito);
	    int totaleRecensioni = contaRecensioni(utenteRecensito);

	    return mapToResponsePayload(recensione, mediaPunteggio, totaleRecensioni);
	}


	// Nuovi metodi
	public NuovaRecensioneResponsePayload mapToResponsePayload(Recensione recensione, double mediaPunteggio, int totaleRecensioni) {
	    return new NuovaRecensioneResponsePayload(
	            recensione.getPunteggio(),
	            recensione.getTestoRecensione(),
	            recensione.getDataRecensione(),
	            recensione.getUtenteRecensore().getDisplayName(),
	            recensione.getUtenteRecensito().getDisplayName(),
	            mediaPunteggio,
	            totaleRecensioni
	    );
	}


	private double calcolaMediaStelle(Utente utenteRecensito) {
		List<Recensione> recensioni = recensioneRepository.findByUtenteRecensito(utenteRecensito);
		return recensioni.stream()
			.mapToInt(recensione -> recensione.getPunteggio().getValore())
			.average()
			.orElse(0.0);
	}

	private int contaRecensioni(Utente utenteRecensito) {
		return recensioneRepository.findByUtenteRecensito(utenteRecensito).size();
	}
}

