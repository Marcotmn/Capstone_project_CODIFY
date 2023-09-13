package marco.tumminia.capstone.codify.entities.recensione;

import org.springframework.data.jpa.repository.JpaRepository;

import marco.tumminia.capstone.codify.entities.utente.Utente;

import java.util.List;
import java.util.UUID;

public interface RecensioneRepository extends JpaRepository<Recensione, UUID> {


}
