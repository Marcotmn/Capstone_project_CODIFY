package marco.tumminia.capstone.codify.entities.proposta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Repository
public interface PropostaSviluppatoreRepository extends JpaRepository<PropostaSviluppatore, UUID> {
    List<PropostaSviluppatore> findBySviluppatore(Sviluppatore sviluppatore);
    Optional<PropostaSviluppatore> findByAnnuncioAndSviluppatore(Annuncio annuncio, Sviluppatore sviluppatore);
    List<PropostaSviluppatore> findByUtenteAndStatoProposta(Utente utente, StatoProposta statoProposta);

}

