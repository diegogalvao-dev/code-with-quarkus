package guitarras.acme.repository;

import guitarras.acme.model.EstiloCase;
import guitarras.acme.model.Estojo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EstojoRepository implements PanacheRepository<Estojo> {

    public List<Estojo> findByMaterial(String material){
        return list("UPPER(material) LIKE UPPER(?1)", "%" + material + "%");
    }

    public List<Estojo> findByCase(EstiloCase estiloCase){
        return list("estiloCase", estiloCase);
    }

}
