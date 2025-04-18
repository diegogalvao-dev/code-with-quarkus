package guitarras.acme.dto;


import guitarras.acme.model.EstiloMusical;

public record CatalogoDeArtistasDTO(String name, Long idGuitarra, Integer idEstiloMusical) {

}