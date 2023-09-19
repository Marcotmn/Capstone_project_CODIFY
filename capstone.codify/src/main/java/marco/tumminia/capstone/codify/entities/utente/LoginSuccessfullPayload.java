package marco.tumminia.capstone.codify.entities.utente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class LoginSuccessfullPayload {
    private String token;
    private String message;

    public LoginSuccessfullPayload(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
