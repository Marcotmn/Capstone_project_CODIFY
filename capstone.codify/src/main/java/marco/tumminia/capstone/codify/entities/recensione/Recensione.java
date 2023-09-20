package marco.tumminia.capstone.codify.entities.recensione;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.utente.Utente;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Data
@Table(name = "recensioni")
@NoArgsConstructor
public class Recensione {
	
	@ManyToOne
    private Utente recensore; // L'utente che ha scritto la recensione

    @ManyToOne
    private Sviluppatore sviluppatore; // Lo sviluppatore recensito

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PunteggioRecensione punteggio;

    private String testoRecensione;

    @CreationTimestamp
    private LocalDate dataRecensione; // Utilizza la data corrente al momento della creazione

    public Recensione(PunteggioRecensione punteggio, String testoRecensione) {
        this.punteggio = punteggio;
        this.testoRecensione = testoRecensione;
    }
}
