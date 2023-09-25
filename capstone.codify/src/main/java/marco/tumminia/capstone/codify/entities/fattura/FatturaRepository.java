package marco.tumminia.capstone.codify.entities.fattura;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
    	Optional<Fattura> findById(UUID id);

	    List<Fattura> findByEmail(String email);


	}



