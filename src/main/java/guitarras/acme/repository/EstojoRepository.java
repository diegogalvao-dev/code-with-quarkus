package guitarras.acme.repository;

import guitarras.acme.model.EstiloCase;
import guitarras.acme.model.Estojo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EstojoRepository implements PanacheRepository<Estojo> {

    public List<Estojo> findByMaterial(String material){
        return find("SELECT t FROM Estojo t WHERE t. material LIKE ?1", "%" + material + "%").list();
    }

    public List<Estojo> findByCase(EstiloCase estiloCase){
        return list("estiloCase", estiloCase);
    }

}
