package marco.tumminia.capstone.codify.entities.recensione;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class NuovaRecensionePayload {
	    private PunteggioRecensione punteggio;
	    private String testoRecensione;

}
