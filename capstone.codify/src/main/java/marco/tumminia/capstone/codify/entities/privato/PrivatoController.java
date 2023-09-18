package marco.tumminia.capstone.codify.entities.privato;

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

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatorePayload;

@RestController
@RequestMapping("/privato")
public class PrivatoController {

    @Autowired
    private PrivatoService privatoService;

    @PostMapping("/register")
    public ResponseEntity<Privato> registerPrivato(@RequestBody PrivatoPayload payload) {
        Privato createdPrivato = privatoService.save(payload);
        return new ResponseEntity<>(createdPrivato, HttpStatus.CREATED);
    }
    
    @GetMapping("/{idPrivato}")
    public Privato getPrivatoById(@PathVariable UUID id) {
        return privatoService.findById(id);
    }

    @GetMapping("/nome/{nomePrivato}")
    public List<Privato> getPrivatoByNome(@PathVariable String nomePrivato) {
        return privatoService.findByNome(nomePrivato);
    }

    @PutMapping("/{idPrivato}")
    public Privato updatePrivato(@PathVariable UUID id, @RequestBody Privato updatedData) {
        return privatoService.updatePrivato(id, updatedData);
    }
    
    @DeleteMapping("/{idPrivato}")
    public ResponseEntity<Void> deletePrivato(@PathVariable UUID id) {
        privatoService.deletePrivato(id);
        return ResponseEntity.noContent().build();
    }
    
}
