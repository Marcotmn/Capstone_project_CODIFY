package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;
import marco.tumminia.capstone.codify.entities.recensione.Recensione;
import marco.tumminia.capstone.codify.entities.utente.RegistrationSuccessResponse;

@RestController
@RequestMapping("/sviluppatore")
public class SviluppatoreController {

    @Autowired
    SviluppatoreService sviluppatoreService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerSviluppatore(@RequestBody SviluppatorePayload payload) {
        //MESSAGGIO DI CONFERMA SU POSTMAN
        RegistrationSuccessResponse response = sviluppatoreService.save(payload);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
        	//MESSAGGIO DI ERRORE REGISTRAZIONE SU POSTMAN

            return new ResponseEntity<>("L'email inserita è già stata utilizzata", HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/{idSviluppatore}")
    public Sviluppatore getSviluppatoreById(@PathVariable UUID idSviluppatore) {
        return sviluppatoreService.findById(idSviluppatore);
    }



    @PutMapping("/{idSviluppatore}")
    public ResponseEntity<Void> updateSviluppatore(@PathVariable UUID idSviluppatore, @RequestBody Sviluppatore updatedSviluppatoreData) {
        sviluppatoreService.updateSviluppatore(idSviluppatore, updatedSviluppatoreData);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{idSviluppatore}")
    public ResponseEntity<Void> deleteSviluppatore(@PathVariable UUID idSviluppatore) {
        sviluppatoreService.deleteSviluppatore(idSviluppatore);
        return ResponseEntity.noContent().build();
    }

}
