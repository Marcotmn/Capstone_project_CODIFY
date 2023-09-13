package marco.tumminia.capstone.codify.entities.fattura;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
	  
	Page<Fattura> findByUtente_IdUtente(UUID idUtente, Pageable pageable);
    Page<Fattura> findByStatoFattura(StatoFattura statoFattura, Pageable pageable);
    Page<Fattura> findByDataFattura(LocalDate dataFattura, Pageable pageable);
    Page<Fattura> findByAnno(int anno, Pageable pageable);
    Page<Fattura> findByImportoBetween(double minImporto, double maxImporto, Pageable pageable);
	
}
