package marco.tumminia.capstone.codify.entities.privato;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;

@RestController
@RequestMapping("/privati")
public class PrivatoController {

    @Autowired
    private PrivatoService privatoService;


    @GetMapping("/{idPrivato}")
    public Privato getPrivatoById(@PathVariable UUID idPrivato) {
        return privatoService.findById(idPrivato);
    }

    @GetMapping("/nome/{nomePrivato}")
    public List<Privato> getPrivatoByNome(@PathVariable String nomePrivato) {
        return privatoService.findByNome(nomePrivato);
    }

    @GetMapping("/{idPrivato}/annunci")
    public List<Annuncio> getAnnunciByPrivato(@PathVariable UUID idPrivato) {
        return privatoService.getAnnunciPerPrivato(idPrivato);
    }
    
    @PutMapping("/{idPrivato}")
    public Privato updatePrivato(@PathVariable UUID idPrivato, @RequestBody Privato updatedData) {
        return privatoService.updatePrivato(idPrivato, updatedData);
    }
    
    @DeleteMapping("/{idPrivato}")
    public ResponseEntity<Void> deletePrivato(@PathVariable UUID idPrivato) {
        privatoService.deletePrivato(idPrivato);
        return ResponseEntity.noContent().build();
    }
    
}
