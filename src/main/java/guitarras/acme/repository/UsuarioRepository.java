package guitarras.acme.repository;

import guitarras.acme.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findUserAndSenha(String username, String senha) {
        return find("username = ?1 AND senha = ?2", username, senha).firstResult();
    }

}
