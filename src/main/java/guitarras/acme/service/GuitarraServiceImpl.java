package guitarras.acme.service;

import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.dto.GuitarrasResponseDTO;
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
    public GuitarrasResponseDTO create(GuitarrasDTO guitarras) {
        Guitarra novaGuitarra = new Guitarra();
        novaGuitarra.setNome(guitarras.nome());
        novaGuitarra.setTipo(guitarras.tipo());

        novaGuitarra.setModelos(Modelos.valueOf(guitarras.idModelo()));

        guitarraRepository.persist(novaGuitarra);

        return GuitarrasResponseDTO.valueOf(novaGuitarra);
    }

    @Override
    @Transactional
    public void update(long id, GuitarrasDTO guitarra) {
        Guitarra edicaoGuitarra = guitarraRepository.findById(id);

        edicaoGuitarra.setNome(guitarra.nome());
        edicaoGuitarra.setTipo(guitarra.tipo());
        edicaoGuitarra.setModelos(Modelos.valueOf(guitarra.idModelo()));

    }

    @Override
    @Transactional
    public void delete(long id) {
        guitarraRepository.deleteById(id);
    }

    @Override
    public GuitarrasResponseDTO findById(long id) {
        return GuitarrasResponseDTO.valueOf(guitarraRepository.findById(id));
    }

    @Override
    public List<GuitarrasResponseDTO> findByTipo(String tipo) {
        return guitarraRepository.findByTipo(tipo).stream().map(GuitarrasResponseDTO::valueOf).toList();
    }

    @Override
    public List<GuitarrasResponseDTO> findAll() {
        return guitarraRepository.findAll().stream().map(e -> GuitarrasResponseDTO.valueOf(e)).toList();
    }

}
