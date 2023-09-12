package marco.tumminia.capstone.codify.entities.privato;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivatoRepository extends JpaRepository<Privato, UUID> {
	 List<Privato> findByNome(String nome);
}
