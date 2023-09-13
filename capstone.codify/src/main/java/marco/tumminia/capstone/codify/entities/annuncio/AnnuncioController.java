package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annunci")
public class AnnuncioController {

    @Autowired
    private AnnuncioService annuncioService;

    @GetMapping("/{idAnnuncio}")
    public Annuncio getAnnuncioById(@PathVariable UUID id) {
        return annuncioService.findById(id);
    }

    @GetMapping("/titolo/{titoloAnnuncio}")
    public List<Annuncio> getAnnunciByTitolo(@PathVariable String titoloAnnuncio) {
        return annuncioService.findByTitolo(titoloAnnuncio);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Annuncio> getAnnunciByCategoria(@PathVariable CategoriaAnnuncio categoria) {
        return annuncioService.findByCategoria(categoria);
    }

}