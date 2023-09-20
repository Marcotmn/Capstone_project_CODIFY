package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.recensione.Recensione;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("SVILUPPATORE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sviluppatore extends Utente {
	
    @OneToMany(mappedBy = "sviluppatore")
    private List<Recensione> recensioniRicevute;
	
	private String nome;
	private String cognome;
    private String titolo; 
    private String bio;
    private String linkPortfolio;
    private String competenze; 
    private String partitaIva;

    public Sviluppatore(String username, String email, String password, String indirizzo, String numeroTelefono, String cartaDiCredito, Ruolo ruolo, 
                        String nome, String cognome, String titolo, String bio, String linkPortfolio, String competenze, String partitaIva) {
        super(username, email, password, indirizzo, numeroTelefono, cartaDiCredito, ruolo);
       
        this.nome = nome;
        this.cognome = cognome;
        this.titolo = titolo;
        this.bio = bio;
        this.linkPortfolio = linkPortfolio;
        this.competenze = competenze;
        this.partitaIva = partitaIva;
    }
    
    @Override
    public String getDisplayName() {
        return this.nome;
    }
    
    @Override
    public String toString() {
        return "Sviluppatore{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", titolo='" + titolo + '\'' +
                ", bio='" + bio + '\'' +
                ", linkPortfolio='" + linkPortfolio + '\'' +
                ", competenze='" + competenze + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                "} " + super.toString();
    }


}


