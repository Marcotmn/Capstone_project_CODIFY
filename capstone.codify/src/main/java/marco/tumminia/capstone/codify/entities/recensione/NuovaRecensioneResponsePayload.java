package marco.tumminia.capstone.codify.entities.recensione;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class NuovaRecensioneResponsePayload {

    private PunteggioRecensione punteggio;
    private String testoRecensione;
    private LocalDate dataRecensione;
    private String nomeUtenteRecensore;  
    private String nomeUtenteRecensito;  
    private Double mediaStelleUtenteRecensito;  
    private Integer numeroTotaleRecensioniUtenteRecensito; 


}
