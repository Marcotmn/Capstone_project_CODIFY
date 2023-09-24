package marco.tumminia.capstone.codify.pagamento;

import java.util.UUID;

import lombok.Data;


@Data
public class PagamentoRequest {
    private double importo;
    private String emailSviluppatore;

}
