package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, UUID> {
    List<Azienda> findByNomeAzienda(String nomeAzienda);
}
