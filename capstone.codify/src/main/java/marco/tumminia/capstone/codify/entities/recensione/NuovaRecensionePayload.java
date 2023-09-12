package marco.tumminia.capstone.codify.entities.recensione;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class NuovaRecensionePayload {
	 private UUID idUtenteRecensito;
	    private PunteggioRecensione punteggio;
	    private String testoRecensione;

}
