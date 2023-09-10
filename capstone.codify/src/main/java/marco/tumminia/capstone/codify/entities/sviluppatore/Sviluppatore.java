package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.utente.Ruolo;
import marco.tumminia.capstone.codify.entities.utente.Utente;

@SuppressWarnings("serial")
@Entity
@Table(name = "sviluppatori")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Per ereditare le proprietà dell'entità padre
public class Sviluppatore extends Utente {

    private String titolo; // Ad es. "Sviluppatore Full Stack", "Designer UI/UX", ecc.
    private String bio; // Una breve bio
    private String linkPortfolio; // Link portfolio

    private String competenze; 

  //  @OneToMany(mappedBy = "sviluppatore") // relazione con entità progetto
    //private List<Progetto> progettiRealizzati;

    public Sviluppatore(String username, String email, String password, String indirizzo, String numeroTelefono, Ruolo ruolo, 
                        String titolo, String bio, String linkPortfolio, String competenze) {
        super(username, email, password, indirizzo, numeroTelefono, ruolo);
        this.titolo = titolo;
        this.bio = bio;
        this.linkPortfolio = linkPortfolio;
        this.competenze = competenze;
    }

}


