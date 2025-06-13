package guitarras.acme.dto;

import jakarta.validation.constraints.*;

public record GuitarraAcusticaDTO(
        @NotBlank(message = "O nome da guitarra acústica não pode ser nulo ou em branco.")
        @Size(max = 100, message = "O nome da guitarra acústica não pode exceder 100 caracteres.")
        String nome,

        @NotNull(message = "O ID do modelo da guitarra acústica não pode ser nulo.")
        Integer idModelo,

        @NotNull(message = "O preço da guitarra acústica não pode ser nulo.")
        @PositiveOrZero(message = "O preço da guitarra acústica deve ser positivo ou zero.")
        Double price,

        @NotBlank(message = "O tipo de madeira do tampo não pode ser nulo ou em branco.")
        @Size(max = 50, message = "O tipo de madeira do tampo não pode exceder 50 caracteres.")
        String tipoMadeiraTampo,

        @NotNull(message = "A informação sobre cutaway não pode ser nula.")
        Boolean possuiCutaway,

        @NotNull(message = "A informação se é eletroacústica não pode ser nula.")
        Boolean eletroacustica,

        @NotNull(message = "O estoque da guitarra acústica não pode ser nulo.")
        @Min(value = 0, message = "O estoque da guitarra acústica não pode ser negativo.")
        Integer estoque
) {
}