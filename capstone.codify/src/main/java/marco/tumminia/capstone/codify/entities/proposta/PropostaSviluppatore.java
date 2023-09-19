package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Table(name = "proposte_sviluppatori")
@Data
@NoArgsConstructor

public class PropostaSviluppatore {
	
	@Id
	@GeneratedValue
	private UUID id;
	
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
    
	@ManyToOne
	@JoinColumn(name = "id_sviluppatore")
	private Sviluppatore sviluppatore;
	
	@ManyToOne
	@JoinColumn(name = "id_annuncio")
	private Annuncio annuncio;
	
	private String descrizione;
	private Double importoProposto;
	@Enumerated(EnumType.STRING)
	private StatoProposta statoProposta;
	private LocalDate dataProposta;
	
	public PropostaSviluppatore(Sviluppatore sviluppatore, Annuncio annuncio, String descrizione, Double importoProposto, StatoProposta statoProposta, LocalDate dataProposta) {
		this.sviluppatore = sviluppatore;
		this.annuncio = annuncio;
		this.descrizione = descrizione;
		this.importoProposto = importoProposto;
		this.statoProposta = statoProposta;
		this.dataProposta = dataProposta;
	}
}
