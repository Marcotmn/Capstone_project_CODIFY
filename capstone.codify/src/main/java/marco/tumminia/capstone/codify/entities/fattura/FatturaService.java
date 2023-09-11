package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRepository;
	
	public Fattura saveFattura(Fattura fattura) {
        return fatturaRepository.save(fattura);
    }

    public Optional<Fattura> getFatturaById(UUID idFattura) {
        return fatturaRepository.findById(idFattura);
    }

    public void deleteFattura(UUID idFattura) {
        fatturaRepository.deleteById(idFattura);
    }

    public Page<Fattura> getAllFatture(Pageable pageable) {
        return fatturaRepository.findAll(pageable);
    }

    public Page<Fattura> findByUtente_IdUtente(UUID idUtente, Pageable pageable) {
        return fatturaRepository.findByUtente_IdUtente(idUtente, pageable);
    }

    public Page<Fattura> findByStatoFattura(StatoFattura statoFattura, Pageable pageable) {
        return fatturaRepository.findByStatoFattura(statoFattura, pageable);
    }

    public Page<Fattura> findByData(LocalDate dataFattura, Pageable pageable) {
        return fatturaRepository.findByData(dataFattura, pageable);
    }

    public Page<Fattura> findByAnno(int anno, Pageable pageable) {
        return fatturaRepository.findByAnno(anno, pageable);
    }

    public Page<Fattura> findByImportoBetween(double minImporto, double maxImporto, Pageable pageable) {
        return fatturaRepository.findByImportoBetween(minImporto, maxImporto, pageable);
    }
}

