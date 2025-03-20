package guitarras.acme.service;

import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.model.Guitarra;
import guitarras.acme.model.Modelos;
import guitarras.acme.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class GuitarraServiceImpl implements GuitarraService {

    @Inject
    GuitarraRepository guitarraRepository;

    @Override
    @Transactional
    public Guitarra create(GuitarrasDTO guitarras) {
        Guitarra novaGuitarra = new Guitarra();
        novaGuitarra.setNome(guitarras.getNome());
        novaGuitarra.setTipo(guitarras.getTipo());

        Modelos modelos = null;
        for (Modelos x : Modelos.values()) {
            if (x.getId() == guitarras.getIdModelo())
                modelos = x;
        }

        novaGuitarra.setModelos(modelos);

        guitarraRepository.persist(novaGuitarra);

        return novaGuitarra;
    }

    @Override
    @Transactional
    public void update(long id, Guitarra guitarra) {
        Guitarra edicaoGuitarra = guitarraRepository.findById(id);

        edicaoGuitarra.setNome(guitarra.getNome());
        edicaoGuitarra.setTipo(guitarra.getTipo());
    }

    @Override
    @Transactional
    public void delete(long id) {
        guitarraRepository.deleteById(id);
    }

    @Override
    public Guitarra findById(long id) {
        return guitarraRepository.findById(id);
    }

    @Override
    public Guitarra findByTipo(String tipo) {
        return guitarraRepository.findByTipo(tipo);
    }

    @Override
    public List<Guitarra> findAll() {
        return guitarraRepository.findAll().list();
    }

}
