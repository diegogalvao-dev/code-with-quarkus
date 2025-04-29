package guitarras.acme.repository;

import guitarras.acme.model.CatalogoDeArtistas;
import guitarras.acme.model.ConfiguracaoAudio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfiguracaoAudioRepository implements PanacheRepository<ConfiguracaoAudio> {



}
