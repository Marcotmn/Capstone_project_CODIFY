package marco.tumminia.capstone.codify.pagamento;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamenti")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/{idAnnuncio}/effettuaPagamento")
    public ResponseEntity<Pagamento> effettuaPagamento(
            @PathVariable UUID idAnnuncio, 
            @RequestBody PagamentoRequest pagamentoRequest) {
        
        Pagamento pagamento = pagamentoService.effettuaPagamento(idAnnuncio, pagamentoRequest);
        return ResponseEntity.ok(pagamento);
    }
}
