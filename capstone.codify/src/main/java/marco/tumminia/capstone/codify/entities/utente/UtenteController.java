package marco.tumminia.capstone.codify.entities.utente;

import java.util.List;


import marco.tumminia.capstone.codify.exceptions.IncorrectPasswordException;

import java.util.UUID;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioRepository;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioService;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @Autowired
    private AnnuncioService annuncioService;
    
    @PostMapping("/register")
    public ResponseEntity<Utente> registerUser(@RequestBody NuovoUtentePayload payload) {
        Utente createdUser = utenteService.save(payload);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Utente> getUsers() {
        return utenteService.getUsers();
    }

    @GetMapping("/{idUtente}")
    public Utente findById(@PathVariable UUID idUtente) {
        return utenteService.findById(idUtente);
    }
    
    @GetMapping("/utente/{utenteId}/annunci")
    public List<Annuncio> getAnnunciByUtente(@PathVariable UUID utenteId) {
        // Recupera l'utente dal repository in base all'ID fornito
        Utente utente = utenteService.findById(utenteId);

        // Recupera la lista degli annunci associati all'utente
        List<Annuncio> annunci = annuncioService.getAnnunciByUtente(utente);

        return annunci;
    }

    @PutMapping("/{idUtente}")
    public Utente updateUser(@PathVariable UUID idUtente, @RequestBody NuovoUtentePayload body) {
        return utenteService.findByIdAndUpdate(idUtente, body);
    }

    @DeleteMapping("/{idUtente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID idUtente) {
        utenteService.findByIdAndDelete(idUtente);
    }
}
