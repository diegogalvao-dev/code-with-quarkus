package guitarras.acme.repository;

import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.EstacaoTeste;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@ApplicationScoped
public class EstacaoTesteRepository implements PanacheRepository<EstacaoTeste> {

    public List<EstacaoTeste> findByOcupada(boolean ocupada){
        return list("ocupada", ocupada);
    }

    public Optional<EstacaoTeste> findByName(String name){
        return find("name", name).singleResultOptional();
    }

}
