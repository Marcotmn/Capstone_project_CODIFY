package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.utente.Utente;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, UUID> {
    List<Azienda> findByNomeAzienda(String nomeAzienda);
    Azienda findByEmail(String email);
    Optional<Azienda> findFirstByOrderByIdAsc();    
    Azienda findByPartitaIva(String partitaIva);
}
