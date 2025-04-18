package guitarras.acme.dto;

import guitarras.acme.model.CatalogoDeArtistas;
import guitarras.acme.model.EstiloMusical;
import guitarras.acme.model.Guitarra;

import java.util.List;

public record CatalogoDeArtistasResponseDTO(Long id, String name, List<GuitarrasResponseDTO> idguitarras, EstiloMusical estiloMusical) {

    public static CatalogoDeArtistasResponseDTO valueOf(CatalogoDeArtistas catalogoDeArtistas){

        if (catalogoDeArtistas == null){
            return null;
        }

        return new CatalogoDeArtistasResponseDTO(catalogoDeArtistas.getId(),catalogoDeArtistas.getName(), catalogoDeArtistas.getGuitarras().stream().map(GuitarrasResponseDTO::valueOf).toList(), catalogoDeArtistas.getEstiloMusical());

    }

}