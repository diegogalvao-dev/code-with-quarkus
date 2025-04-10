package guitarras.acme.service;


import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.dto.EstojoResponseDTO;
import guitarras.acme.model.EstiloCase;
import guitarras.acme.model.Estojo;
import guitarras.acme.model.Guitarra;
import guitarras.acme.model.Modelos;
import guitarras.acme.repository.EstojoRepository;
import guitarras.acme.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EstojoServiceImpl implements EstojoService {

    @Inject
    EstojoRepository estojoRepository;

    @Inject
    GuitarraRepository guitarraRepository;

    @Override
    @Transactional
    public EstojoResponseDTO create(EstojoDTO dto) {
        Estojo novoEstojo = new Estojo();
        novoEstojo.setMaterial(dto.material());

        novoEstojo.setEstiloCase(EstiloCase.valueOf(dto.idEstiloCase()));

        estojoRepository.persist(novoEstojo);

        return EstojoResponseDTO.valueOf(novoEstojo);

    }

    @Override
    @Transactional
    public void update(long id, EstojoDTO dto) {
        Estojo edicaoEstojo = estojoRepository.findById(id);

        edicaoEstojo.setMaterial(dto.material());
        edicaoEstojo.setEstiloCase(EstiloCase.valueOf(dto.idEstiloCase()));

    }

    @Override
    @Transactional
    public void delete(long id) {
        estojoRepository.deleteById(id);
    }

    @Override
    public List<EstojoResponseDTO> findByCase(EstiloCase idEstiloCase) {
        return estojoRepository.findByCase(idEstiloCase).stream().map(e -> EstojoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstojoResponseDTO> findAll() {
        return estojoRepository.findAll().stream().map(e -> EstojoResponseDTO.valueOf(e)).toList();
    }

}
