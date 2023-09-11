package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Data
@NoArgsConstructor

public class Fattura {
	
	@Id
	@GeneratedValue
	private UUID idFattura;
	
	private int anno;
	
	@ManyToOne
	@JoinColumn(name = "idUtente")
	private Utente utente;

	private double numeroFattura;
	private LocalDate dataFattura;
	private Double importo;
	
	private StatoFattura statoFattura;
	
	public Fattura(int anno, Utente utente, double numeroFattura, LocalDate dataFattura, Double importo, StatoFattura statoFattura) {
	    this.anno = anno;
		this.utente = utente;
		this.numeroFattura = numeroFattura;
	    this.dataFattura = dataFattura;
	    this.importo = importo;
	    this.statoFattura = statoFattura;
	}

}


