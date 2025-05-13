package guitarras.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisicaRepository> {

    public List<PessoaFisicaRepository> findByNome(String nome) {
        return find("nome LIKE ?1 ", "%" + nome + "%").list();
    }

    public PessoaFisicaRepository findByCpf(String cpf) {
        return find("cpf = ?1 ", cpf).firstResult();
    }

}
