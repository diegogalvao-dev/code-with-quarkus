package guitarras.acme.repository;

import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.EstacaoTeste;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstacaoTesteRepository implements PanacheRepository<EstacaoTeste> {



}
