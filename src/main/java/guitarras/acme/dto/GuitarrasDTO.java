package guitarras.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record GuitarrasDTO(

        @NotBlank(message = "O nome da guitarra não pode ser nulo ou em branco.")
        @Size(max = 60, message = "O nome da guitarra não pode exceder 60 caracteres.")
        String nome,

        @NotNull(message = "O preço não pode ser nulo.")
        @PositiveOrZero(message = "O preço deve ser um valor positivo ou zero.")
        Double price,

        @NotNull(message = "O estoque não pode ser nulo.")
        @PositiveOrZero(message = "O estoque deve ser um valor positivo ou zero.")
        Integer estoque,

        @NotBlank(message = "O tipo da guitarra não pode ser nulo ou em branco.")
        String tipo,

        @NotNull(message = "O ID do modelo não pode ser nulo.")
        Integer idModelo) {


}