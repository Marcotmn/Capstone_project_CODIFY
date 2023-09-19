package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SviluppatoreRepository extends JpaRepository<Sviluppatore, UUID> {
Sviluppatore findByEmail(String email);
 
}
