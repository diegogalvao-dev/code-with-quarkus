package guitarras.acme.repository;

import guitarras.acme.model.Guitarra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GuitarraRepository implements PanacheRepository<Guitarra> {

    public List<Guitarra> findByTipo(String tipo) {
        return find("SELECT e FROM Guitarra e WHERE e.tipo LIKE ?1 ", "%" + tipo).list();
    }

}
