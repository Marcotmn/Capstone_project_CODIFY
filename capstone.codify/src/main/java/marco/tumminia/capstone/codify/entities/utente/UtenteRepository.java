package marco.tumminia.capstone.codify.entities.utente;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//////////LE REPOSITORY ESTENDONO L'INTERFACCIA DI JPA REPOSITORY E SI OCCUPA DI TUTTI QUEI METODI DI RICERCA(FINDBY) 
//CHE PERMETTONO LA CREAZIONE DI QUERY
@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
	Optional<Utente> findByEmail(String email);
	
}
