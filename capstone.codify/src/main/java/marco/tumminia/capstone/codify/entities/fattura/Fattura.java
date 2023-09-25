package marco.tumminia.capstone.codify.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID idPagamento;
    private String nomeUtente;
    private String nomeAzienda;
    private String cognomeCliente;
    private String CategoriaAzienda;
    private String email;
    private String identificativoFiscale;
    private double importo;
    private String emailSviluppatore;
    private LocalDate dataFattura;
}
