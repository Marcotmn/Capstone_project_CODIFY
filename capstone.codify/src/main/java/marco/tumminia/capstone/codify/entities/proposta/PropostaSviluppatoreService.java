package marco.tumminia.capstone.codify.entities.proposta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;

@Service
public class PropostaSviluppatoreService {

    @Autowired
    private PropostaSviluppatoreRepository propostaRepository;

    public PropostaSviluppatore saveProposta(PropostaSviluppatore proposta) {
        return propostaRepository.save(proposta);
    }

    public Optional<PropostaSviluppatore> findById(UUID idProposta) {
        return propostaRepository.findById(idProposta);
    }

    public List<PropostaSviluppatore> findBySviluppatore(Sviluppatore sviluppatore) {
        return propostaRepository.findBySviluppatore(sviluppatore);
    }

    public void deleteById(UUID idProposta) {
        propostaRepository.deleteById(idProposta);
    }
}
