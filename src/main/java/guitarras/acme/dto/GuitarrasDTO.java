package guitarras.acme.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GuitarrasDTO(

        @NotNull(message = "O nome da guitarra não pode ser nulo ou vazio.")
        @Size(max = 60, message = "O nome não pode exceder 60 caracteres.")
        String nome,

        String tipo,

        @NotNull(message = "O modelo não pode ser vazio")
        Integer idModelo) {


}