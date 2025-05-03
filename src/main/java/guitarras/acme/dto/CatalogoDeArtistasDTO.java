package guitarras.acme.dto;


import jakarta.validation.constraints.NotNull;

public record CatalogoDeArtistasDTO(

        @NotNull(message = "O nome do artista não pode ser nulo ou vazio.")
        String name,

        @NotNull(message = "O ID do estilo musical não pode ser nulo.")
        Integer idEstiloMusical) {

}