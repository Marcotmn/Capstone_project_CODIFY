package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fs;

    // Recupera tutte le fatture
    @GetMapping
    public Page<Fattura> getAllFatture(@PageableDefault(size = 10) Pageable pageable) {
        return fs.getAllFatture(pageable);
    }

    // Crea una nuova fattura
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura createFattura(@RequestBody Fattura fattura) {
        return fs.saveFattura(fattura);
    }

    // Recupera una fattura per ID
    @GetMapping("/{id}")
    public Optional<Fattura> getFatturaById(@PathVariable UUID idFattura) {
        return fs.getFatturaById(idFattura);
    }

    // Elimina una fattura per ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFatturaById(@PathVariable UUID idFattura) {
        fs.deleteFattura(idFattura);
    }

    // Filtri
    @GetMapping("/filter/utente/{idUtente}")
    public Page<Fattura> getFattureByUtente(@PathVariable UUID idUtente, @PageableDefault(size = 10) Pageable pageable) {
        return fs.findByUtente_IdUtente(idUtente, pageable);
    }

    @GetMapping("/filter/statoFattura")
    public Page<Fattura> getFattureByStatoFattura(@RequestParam StatoFattura statoFattura, @PageableDefault(size = 10) Pageable pageable) {
        return fs.findByStatoFattura(statoFattura, pageable);
    }

    @GetMapping("/filter/dataFattura")
    public Page<Fattura> getFattureByDataFattura(@RequestParam LocalDate dataFattura, @PageableDefault(size = 10) Pageable pageable) {
        return fs.findByDataFattura(dataFattura, pageable);
    }

    @GetMapping("/filter/anno")
    public Page<Fattura> getFattureByAnno(@RequestParam int anno, @PageableDefault(size = 10) Pageable pageable) {
        return fs.findByAnno(anno, pageable);
    }

    @GetMapping("/filter/importRange")
    public Page<Fattura> getFattureByImportoRange(@RequestParam double minImporto, @RequestParam double maxImporto, @PageableDefault(size = 10) Pageable pageable) {
        return fs.findByImportoBetween(minImporto, maxImporto, pageable);
    }
}
