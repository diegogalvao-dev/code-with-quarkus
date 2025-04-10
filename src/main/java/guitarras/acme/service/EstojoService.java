package guitarras.acme.service;

import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.dto.EstojoResponseDTO;
import guitarras.acme.model.EstiloCase;

import java.util.List;

public interface EstojoService {

    EstojoResponseDTO create(EstojoDTO estojo);
    void update(long id, EstojoDTO estojo);
    void delete(long id);

    List<EstojoResponseDTO> findByCase(EstiloCase estiloCase);
    List<EstojoResponseDTO> findAll();

}
