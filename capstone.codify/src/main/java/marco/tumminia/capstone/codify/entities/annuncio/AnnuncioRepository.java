package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.utente.Utente;


@Repository
	public interface AnnuncioRepository extends JpaRepository<Annuncio, UUID> {
		List<Annuncio> findByUtente(Utente utente);
	    List<Annuncio> findByTitolo(String titolo);
	    List<Annuncio> findByCategoria(CategoriaAnnuncio categoria);
	    Optional<Annuncio> findById(UUID id);
	    Optional<Annuncio> findFirstByOrderByIdAsc();
	}



