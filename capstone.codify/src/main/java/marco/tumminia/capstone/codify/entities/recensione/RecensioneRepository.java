package marco.tumminia.capstone.codify.entities.recensione;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RecensioneRepository extends JpaRepository<Recensione, UUID> {

}
