package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Getter
@AllArgsConstructor
public class FatturaPayload {
	private UUID idFattura;
	private int anno;
	private LocalDate data;
	private double importo;
	private StatoFattura statoFattura;
	private Utente cliente;
}
