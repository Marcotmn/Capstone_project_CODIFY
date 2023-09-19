package marco.tumminia.capstone.codify.entities.utente;

import lombok.Data;

@Data

//RESTITUISCE MESSAGGIO DI AVVENUTA REGISTRAZIONE SU POSTMAN

public class RegistrationSuccessResponse {
    private String message;

    public RegistrationSuccessResponse(String message) {
        this.message = message;
    }

}
