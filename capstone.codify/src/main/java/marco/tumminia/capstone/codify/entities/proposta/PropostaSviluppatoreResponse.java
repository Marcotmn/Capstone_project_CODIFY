package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;


@Data
@AllArgsConstructor

public class PropostaSviluppatoreResponse {
    private UUID idProposta;
    private String descrizione;
    private Double importoProposto;
    private StatoProposta statoProposta;
    private LocalDate dataProposta;
    private Annuncio annuncio;  // o forse solo alcuni dettagli dell'annuncio
    private Sviluppatore sviluppatore;  // o forse solo alcuni dettagli dello sviluppatore
    // getters, setters, costruttori
}
