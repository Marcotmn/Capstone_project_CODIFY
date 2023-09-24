package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fatture")
public class Fattura {
	
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate dataFattura;
    private String descrizionePrestazione;
    private double totaleFattura;
    private String email;
    private String numeroTelefono;
    private String indirizzo;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String nomeAzienda;
    private String partitaIva;

    // Costruttore aggiornato
    public Fattura(LocalDate dataFattura, String descrizionePrestazione,
                   double totaleFattura, String email, String numeroTelefono, String indirizzo, String nome, 
                   String cognome, String codiceFiscale, String nomeAzienda, String partitaIva) {

        this.dataFattura = dataFattura;
        this.descrizionePrestazione = descrizionePrestazione;
        this.totaleFattura = totaleFattura;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.nomeAzienda = nomeAzienda;
        this.partitaIva = partitaIva;
    }

}
