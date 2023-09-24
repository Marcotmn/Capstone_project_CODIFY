package marco.tumminia.capstone.codify.entities.privato;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.utente.Utente;

@Repository
public interface PrivatoRepository extends JpaRepository<Privato, UUID> {
	 List<Privato> findByNome(String nome);
	 Privato findByUsername(String username);
	 Privato findByEmail(String email);
	 Optional<Privato> findFirstByOrderByIdAsc();
	 Privato findByCodiceFiscale(String codiceFiscale);
}
