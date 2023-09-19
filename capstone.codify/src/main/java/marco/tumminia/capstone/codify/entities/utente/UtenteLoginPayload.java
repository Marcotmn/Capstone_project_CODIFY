package marco.tumminia.capstone.codify.entities.utente;

import lombok.Getter;

@Getter
//////////QUESTA CLASSE SERVE PER IL LOGIN, INDICA GLI ATTRIBUTI NECESSARI
public class UtenteLoginPayload {
	String email;
	String password;
}
