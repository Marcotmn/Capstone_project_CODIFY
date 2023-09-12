package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayload {

	private int anno;
	private LocalDate data;
	private double importo;
	private StatoFattura statoFattura;

}
