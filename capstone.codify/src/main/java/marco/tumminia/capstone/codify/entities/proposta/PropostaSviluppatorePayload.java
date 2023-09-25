package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PropostaSviluppatorePayload {

    private String descrizione;
    private Double importoProposto;
    private LocalDate dataProposta;
}
