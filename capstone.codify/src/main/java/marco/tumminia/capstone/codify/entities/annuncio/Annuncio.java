package marco.tumminia.capstone.codify.entities.annuncio;

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
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Table(name = "annunci")
@Data
@NoArgsConstructor

public class Annuncio {
	
	@ManyToOne
    @JoinColumn(name = "utente_id") // Utente comune per azienda e privato
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "azienda_id") // Nome corretto della colonna
    private Azienda azienda;
    
    @ManyToOne
    @JoinColumn(name = "privato_id") // Nome corretto della colonna
    private Privato privato;
    
    
	@Id
	@GeneratedValue
	private UUID id;
		
	private String titolo;
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private CategoriaAnnuncio categoria;
	@Enumerated(EnumType.STRING)
	private StatoAnnuncio statoAnnuncio;
	private Double budgetPrevisto;
	private LocalDate dataPubblicazione;
	private LocalDate dataChiusura;
	
	public Annuncio (String titolo, String descrizione, CategoriaAnnuncio categoria, StatoAnnuncio statoAnnuncio, Double budgetPrevisto, LocalDate dataPubblicazione, LocalDate dataChiusura) {
	
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.statoAnnuncio = statoAnnuncio;
		this.budgetPrevisto = budgetPrevisto;
		this.dataPubblicazione = dataPubblicazione;
		this.dataChiusura = dataChiusura;
	}
	
	@Override
	public String toString() {
	    return "Annuncio{" +
	            "id=" + id +
	            ", titolo='" + titolo + '\'' +
	            ", descrizione='" + descrizione + '\'' +
	            ", categoria=" + categoria +
	            ", statoAnnuncio=" + statoAnnuncio +
	            ", budgetPrevisto=" + budgetPrevisto +
	            ", dataPubblicazione=" + dataPubblicazione +
	            ", dataChiusura=" + dataChiusura +
	            '}';
	}	
}
