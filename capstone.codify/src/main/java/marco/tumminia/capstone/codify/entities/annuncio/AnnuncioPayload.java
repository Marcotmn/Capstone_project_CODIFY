package marco.tumminia.capstone.codify.entities.annuncio;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnnuncioPayload {
    private String titolo;
    private String descrizione;
    private CategoriaAnnuncio categoria;
    private StatoAnnuncio statoAnnuncio;
    private Double budgetPrevisto;
    private LocalDate dataPubblicazione;
    private LocalDate dataChiusura;
}
