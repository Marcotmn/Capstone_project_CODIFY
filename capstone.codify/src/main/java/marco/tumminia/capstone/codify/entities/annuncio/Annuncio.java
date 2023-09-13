package marco.tumminia.capstone.codify.entities.annuncio;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Table(name = "annunci")
@Data
@NoArgsConstructor



public class Annuncio {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "id_utente", nullable = false)
	private Utente utente;
	
	@OneToMany(mappedBy = "annuncio")
	private List<PropostaSviluppatore> proposte;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_privato")
    private Privato privato;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_azienda")
    private Azienda azienda;


	
	private String titolo;
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private CategoriaAnnuncio categoria;
	
	@Enumerated(EnumType.STRING)
	private StatoAnnuncio annuncio;
	
	private Double budgetPrevisto;
	private LocalDate dataPubblicazione;
	private LocalDate dataChiusura;
	
	public Annuncio (Utente utente, String titolo, String descrizione, CategoriaAnnuncio categoria, StatoAnnuncio annuncio, Double budgetPrevisto, LocalDate dataPubblicazione, LocalDate dataChiusura) {
		this.utente = utente;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.annuncio = annuncio;
		this.budgetPrevisto = budgetPrevisto;
		this.dataPubblicazione = dataPubblicazione;
		this.dataChiusura = dataChiusura;
	}
	
}
