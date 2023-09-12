package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;

@Repository
public interface SviluppatoreRepository extends JpaRepository<Sviluppatore, UUID> {
    List<PropostaSviluppatore> findProposteBySviluppatoreId(UUID idSviluppatore);
    List<Sviluppatore> findByNome(String nome);
    
    @Query("SELECT s FROM Sviluppatore s JOIN s.recensioni r GROUP BY s ORDER BY AVG(r.punteggio.valore) DESC")
    List<Sviluppatore> findAllOrderByAverageRatingDesc();
    
    
}
