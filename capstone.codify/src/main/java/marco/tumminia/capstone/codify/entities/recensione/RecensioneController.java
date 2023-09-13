package marco.tumminia.capstone.codify.entities.recensione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.entities.utente.UtenteService;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;
    
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private RecensioneRepository recensioneRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recensione creaRecensione(@RequestBody Recensione recensione) {
        return recensioneService.saveRecensione(recensione);
    }

   // @GetMapping
   // public List<Recensione> getTutteLeRecensioni() {
   //     return recensioneService.findAll();
   // }
    




    
    @GetMapping("/{id}")
    public Recensione getRecensioneById(@PathVariable UUID id) {
        return recensioneService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecensione(@PathVariable UUID id) {
        recensioneService.deleteById(id);
    }
}
