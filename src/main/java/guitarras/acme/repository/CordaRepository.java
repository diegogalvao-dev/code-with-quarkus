package guitarras.acme.repository;

import guitarras.acme.model.Corda;
import guitarras.acme.model.Guitarra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CordaRepository implements PanacheRepository<Corda> {

    public List<Corda> findByCalibre(String calibre){
        return find("SELECT c FROM Corda c WHERE c. calibre LIKE ?1", "%" + calibre + "%").list();
    }

    public long deleteByIdGuitarra(Long idGuitarra){
        return delete("guitarra.id", idGuitarra);
    }

    public List<Corda> findByIdGuitarra(Long idGuitarra) {
        return list("guitarra.id", idGuitarra);
    }

}
