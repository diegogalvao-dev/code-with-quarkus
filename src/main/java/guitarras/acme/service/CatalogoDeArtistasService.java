package guitarras.acme.service;

import guitarras.acme.dto.CatalogoDeArtistasDTO;
import guitarras.acme.dto.CatalogoDeArtistasResponseDTO;

import java.util.List;

public interface CatalogoDeArtistasService {

    CatalogoDeArtistasResponseDTO create(CatalogoDeArtistasDTO catalogoDeArtistas);
    void update(long id, CatalogoDeArtistasDTO catalogoDeArtistas);
    void delete(long id);

    CatalogoDeArtistasResponseDTO findById(long id);

    CatalogoDeArtistasResponseDTO findByName(String name);
    List<CatalogoDeArtistasResponseDTO> findAll();

}
