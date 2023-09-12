package marco.tumminia.capstone.codify.entities.utente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordPayload {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
