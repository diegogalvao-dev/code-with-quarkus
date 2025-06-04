package guitarras.acme.repository;

import guitarras.acme.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByUsuario(String username){
        return find("SELECT p FROM Pedido p WHERE p.usuario.username = ?1", username).list();
    }

}
