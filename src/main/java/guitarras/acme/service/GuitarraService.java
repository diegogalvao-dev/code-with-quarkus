package guitarras.acme.service;

import guitarras.acme.dto.GuitarraAcusticaDTO;
import guitarras.acme.dto.GuitarraEletricaDTO;
import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.dto.GuitarrasResponseDTO;
import guitarras.acme.model.Guitarra;

import java.util.List;

public interface GuitarraService {

    GuitarrasResponseDTO createEletrica(GuitarraEletricaDTO dto);
    GuitarrasResponseDTO createAcustica(GuitarraAcusticaDTO dto);
    GuitarrasResponseDTO update(Long id, GuitarrasDTO dto);
    void delete(long id);

    List<GuitarrasResponseDTO> findAll();
    List<GuitarrasResponseDTO> findByNome(String nome);
    GuitarrasResponseDTO findById(Long id);

}
