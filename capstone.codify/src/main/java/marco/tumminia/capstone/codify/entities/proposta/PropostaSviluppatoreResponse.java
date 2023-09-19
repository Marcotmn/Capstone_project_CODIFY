package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PropostaSviluppatoreResponse {
    private UUID id;
    private String descrizione;
    private Double importoProposto;
    private StatoProposta statoProposta;
    private LocalDate dataProposta;
    private Annuncio annuncio; 
    private Sviluppatore sviluppatore;
  
}
