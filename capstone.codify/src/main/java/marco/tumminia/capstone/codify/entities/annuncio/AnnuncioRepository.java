package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;


@Repository
public interface AnnuncioRepository extends JpaRepository<Annuncio, UUID> {
    List<Annuncio> findByTitolo(String titolo);
    List<Annuncio> findByPrivato(Privato privato);
    List<Annuncio> findByAzienda(Azienda azienda);
    List<Annuncio> findByCategoria(CategoriaAnnuncio categoria);
}

