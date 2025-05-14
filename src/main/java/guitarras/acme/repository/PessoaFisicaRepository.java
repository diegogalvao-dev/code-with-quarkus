package guitarras.acme.repository;

import guitarras.acme.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {

    public List<PessoaFisica> findByName(String name) {
        return find("nome LIKE ?1 ", "%" + name + "%").list();
    }

    public PessoaFisica findByCpf(String cpf) {
        return find("cpf = ?1 ", cpf).firstResult();
    }

}
