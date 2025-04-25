package guitarras.acme.service;

import guitarras.acme.dto.CatalogoDeArtistasDTO;
import guitarras.acme.dto.CatalogoDeArtistasResponseDTO;
import guitarras.acme.model.CatalogoDeArtistas;

import java.util.List;

public interface CatalogoDeArtistasService {

    CatalogoDeArtistas create(CatalogoDeArtistasDTO catalogoDeArtistas);
    CatalogoDeArtistas update(long id, CatalogoDeArtistasDTO catalogoDeArtistas);
    void delete(long id);

    CatalogoDeArtistas addGuitarra(Long idArtista, Long idGuitarra);

    CatalogoDeArtistasResponseDTO findById(long id);

//    CatalogoDeArtistasResponseDTO findByName(String name);
    List<CatalogoDeArtistasResponseDTO> findAll();

}
