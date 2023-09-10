package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.Date;
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
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Table(name = "annunci")
@Data
@NoArgsConstructor



public class Annuncio {
	
	@Id
	@GeneratedValue
	private UUID idAnnuncio;
	
	@ManyToOne
	@JoinColumn(name = "id_utente, nullable = false")
	private Utente cliente;
	
	private String titolo;
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private CategoriaAnnuncio categoria;
	
	@Enumerated(EnumType.STRING)
	private StatoAnnuncio annuncio;
	
	private Double budgetPrevisto;
	private Date dataPubblicazione;
	private Date dataChiusura;
	
	
	
}
