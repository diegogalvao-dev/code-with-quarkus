package guitarras.acme.service;

import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.dto.GuitarrasResponseDTO;
import guitarras.acme.model.Guitarra;

import java.util.List;

public interface GuitarraService {

    GuitarrasResponseDTO create(GuitarrasDTO guitarra);
    void update(long id, GuitarrasDTO guitarra);
    void delete(long id);
    GuitarrasResponseDTO findById(long id);
    GuitarrasResponseDTO findByTipo(String tipo);
    List<GuitarrasResponseDTO> findAll();

}
