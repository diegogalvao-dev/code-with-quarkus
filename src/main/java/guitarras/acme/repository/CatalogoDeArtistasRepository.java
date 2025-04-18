package guitarras.acme.repository;

import guitarras.acme.model.CatalogoDeArtistas;
import guitarras.acme.model.Corda;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatalogoDeArtistasRepository implements PanacheRepository<CatalogoDeArtistas> {

    public CatalogoDeArtistas findByName(String name){
        return find("SELECT c FROM CatalogoDeArtistas c WHERE c. name LIKE ?1", "%" + name + "%").firstResult();
    }

}
