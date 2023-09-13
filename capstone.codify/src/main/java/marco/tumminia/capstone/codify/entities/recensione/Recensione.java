package marco.tumminia.capstone.codify.entities.recensione;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Data
@Table (name = "recensioni")
@NoArgsConstructor

public class Recensione {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "id_sviluppatore")
	 private Sviluppatore sviluppatore;
	
	@Enumerated(EnumType.STRING)
	private PunteggioRecensione punteggio;
	
	private String testoRecensione;
	
	private LocalDate dataRecensione;
	
	public Recensione (PunteggioRecensione punteggio, String testoRecensione, LocalDate dataRecensione) {

		this.punteggio = punteggio;
		this.testoRecensione = testoRecensione;
		this.dataRecensione = dataRecensione;
	}
}
