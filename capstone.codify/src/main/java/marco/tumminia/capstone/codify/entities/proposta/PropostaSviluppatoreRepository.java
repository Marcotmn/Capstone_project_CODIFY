package marco.tumminia.capstone.codify.entities.proposta;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;

@Repository
public interface PropostaSviluppatoreRepository extends JpaRepository<PropostaSviluppatore, UUID> {
    List<PropostaSviluppatore> findBySviluppatore(Sviluppatore sviluppatore);
}

