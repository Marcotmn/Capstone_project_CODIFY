package marco.tumminia.capstone.codify.entities.recensione;

import java.util.UUID;

public class NuovaRecensioneResponsePayload {

    private UUID idRecensione;
    private String messaggio;

    public UUID getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(UUID idRecensione) {
        this.idRecensione = idRecensione;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
